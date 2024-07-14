package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/register")
    String join(Authentication auth) {
        if (auth != null && auth.isAuthenticated())
            return "list.html";
        else
            return "join.html";
    }

    @PostMapping("/join")
    String addMember(String username, String password, String displayName) {

        memberService.saveMember(username, password, displayName);

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){
        CustomUser result = (CustomUser)auth.getPrincipal();
        System.out.println(result.displayName);
        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new MemberDto(result.getUsername(), result.getDisplayName());
        return data;
    }

    class MemberDto {
        public String username;
        public String displayName;

        MemberDto(String a, String b){
            this.username = a;
            this.displayName = b;
        }
    }
}
