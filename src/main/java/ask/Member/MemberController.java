package ask.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MemberController {


    MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @GetMapping("/asklogin")
    public String loginHome() {
        return "member/login";
    }


    @GetMapping("/join")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(MemberDTO memberDTO) throws Exception {
        System.out.println(memberDTO);
        memberRepository.registerMember(memberDTO);

        return "redirect:/asklogin";
    }

    @ResponseBody
    @PostMapping(value="/idCheck")
    public String idCheck(@RequestParam(value="email",required=true)String email) {
        System.out.println("유저아이디 : "+email);
        Optional<MemberDTO> findByEmail = memberRepository.findByEmail(email);
        String idcheck = "false";
        if (!findByEmail.isEmpty()) {
            idcheck = "true";
        }
        return idcheck;
    }


}
