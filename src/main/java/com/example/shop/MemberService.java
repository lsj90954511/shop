package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void saveMember(String username, String password, String displayName) {
        Member member = new Member();

        //비번 해싱
        password = new BCryptPasswordEncoder().encode(password);

        member.setUsername(username);
        member.setPassword(password);
        member.setDisplayName(displayName);

        memberRepository.save(member);
    }
}
