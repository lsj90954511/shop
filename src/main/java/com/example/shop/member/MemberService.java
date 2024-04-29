package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(String username, String password, String displayName) {
        Member member = new Member();

        //아이디
        if (username.length() < 30 && username.length() > 5)
            member.setUsername(username);
        //비번
        if (password.length() < 30 && password.length() >= 8)
        {
            password = passwordEncoder.encode(password);
            member.setPassword(password);
        }

        member.setDisplayName(displayName);

        memberRepository.save(member);
    }
}
