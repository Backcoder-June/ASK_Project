package ask.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String loginHome() {
        return "member/login";
    }


    @GetMapping("/join")
    public String joinPage() {
        return "member/join";
    }







}
