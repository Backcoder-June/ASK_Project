package ask;

import ask.Member.MemberDTO;
import ask.Member.MemberRepository;
import ask.Member.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    MemberRepository memberRepository;

    HttpSession httpSession;

    @Autowired
    public CustomOAuth2UserService(MemberRepository memberRepository, HttpSession httpSession) {
        this.memberRepository = memberRepository;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 서비스 구분을 위한 작업 ( 구글 / 네이버 )
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String email;
        Map<String, Object> response = oAuth2User.getAttributes();

        // 네이버
        if (registrationId.equals("naver")) {
            Map<String, Object> hash = (Map<String, Object>) response.get("response");
            email = (String) hash.get("email");
        //구글
        } else if (registrationId.equals("google")) {
            email = (String) response.get("email");
        } else {
            throw new OAuth2AuthenticationException("허용되지 않는 인증입니다.");
        }


        MemberDTO memberDTO;
        Optional<MemberDTO> optionalMmeberDTO = memberRepository.findByEmail(email);

        //이미 가입한 사람 => findbyEmail 로 찾은 DTO 가져옴
        if (optionalMmeberDTO.isPresent()) {
            System.out.println("이미가입한 사람!");
            memberDTO = optionalMmeberDTO.get();
        //새로 가입 => 새로 DTO 만들어서 저장
        } else {
            memberDTO = new MemberDTO();
            memberDTO.setEmail(email);
            memberDTO.setRole("ROLE_USER");
            System.out.println("오스 서비스에서 회원가입 실행");
            memberRepository.registerMember(memberDTO);
        }
        // 세션 등록
        httpSession.setAttribute("sessiondto", memberDTO);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(memberDTO.getRole()))
                , oAuth2User.getAttributes()
                , userNameAttributeName
        );

    }

}
