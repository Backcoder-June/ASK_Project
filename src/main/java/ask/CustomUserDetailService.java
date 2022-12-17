package ask;

import java.io.Console;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import ask.Member.MemberDTO;
import ask.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
	HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("inserted id : " + email);
        Optional<MemberDTO> user = memberRepository.findByEmail(email);
        System.out.println(user.get().getPw());
        
        if (user == null) {
            return null;
        }
        // session 에 set만 해두면 자동으로 successURL 로 넘어감
        session.setAttribute("sessiondto", user.get());

        return User.builder()
                .username(email)
                .password(user.get().getPw())
                .roles(user.get().getRole())
                .build();
    }
}