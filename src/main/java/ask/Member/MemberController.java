package ask.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MemberController {


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @GetMapping("/login")
    public String loginHome(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,  Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "member/login";
    }

    //PostMapping 로그인은 Spring Security Config 에서 처리


    // 최종적으로 /oauth2/authorization/naver||google 로 보내서 oauth 처리
    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2) {
        return "redirect:/oauth2/authorization/" + oauth2;
    }

    // 로그인 접근 권한 실패시 페이지
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "member/accessDenied";
    }

    @GetMapping("/loginFail")
    public String loginFail() {
        return "member/loginFail";
    }


    @GetMapping("/join")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(MemberDTO memberDTO) throws Exception {
        // PW 암호화
        memberDTO.setPw(bCryptPasswordEncoder.encode(memberDTO.getPw()));
        memberRepository.registerMember(memberDTO);
        return "redirect:/login";
    }

    @ResponseBody
    @PostMapping(value = "/idCheck")
    public String idCheck(@RequestParam(value = "email", required = true) String email) {
        System.out.println("유저아이디 : " + email);
        Optional<MemberDTO> findByEmail = memberRepository.findByEmail(email);
        String idcheck = "false";
        if (findByEmail.isPresent()) {
            idcheck = "true";
        }
        return idcheck;
    }

    @ResponseBody
    @PostMapping(value = "/nicknameCheck")
    public String nicknameCheck(@RequestParam(value = "name", required = true) String name) {
        Optional<String> findByName = memberRepository.findByName(name);
        String nameCheck = "false";
        if (findByName.isPresent()) {
            nameCheck = "true";
        }
        return nameCheck;
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
        return "redirect:/allboard";
    }
}
