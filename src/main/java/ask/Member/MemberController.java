package ask.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MemberController {


    MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String loginHome() {
        return "member/login";
    }

    //PostMapping 로그인은 Spring Security Config 에서 처리


    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2) {
        return "redirect:/oauth2/authorization/" + oauth2;
    }

    // 로그인 접근 권한 실패시 페이지
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "member/accessDenied";
    }


    @GetMapping("/join")
    public String joinPage() {

        return "member/join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(MemberDTO memberDTO) throws Exception {
        System.out.println(memberDTO);
        memberRepository.registerMember(memberDTO);
        return "redirect:/login";
    }

    @ResponseBody
    @PostMapping(value = "/idCheck")
    public String idCheck(@RequestParam(value = "email", required = true) String email) {
        System.out.println("유저아이디 : " + email);
        Optional<MemberDTO> findByEmail = memberRepository.findByEmail(email);
        String idcheck = "false";
        if (!findByEmail.isEmpty()) {
            idcheck = "true";
        }
        return idcheck;
    }

    @GetMapping("/nicknameRegister")
    public String nicknameRegister() {
        return "member/nicknameRegister";
    }

    @PostMapping("/nicknameRegisterProcess")
    public String nicknameRegisterProcess(HttpSession session, String nickname) {
        MemberDTO sessiondto = (MemberDTO) session.getAttribute("sessiondto");
        sessiondto.setNickname(nickname);
        memberRepository.setNickname(nickname, sessiondto.getId());
        return "redirect:/test";
    }
}
