package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    String join(Model model) {
        return "join.html";
    }

    @PostMapping("/join")
    String addMember(String username, String password, String displayName) {

        memberService.saveMember(username, password, displayName);

        return "redirect:/list";
    }
}
