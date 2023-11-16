package org.bs.jpa.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.bs.jpa.domain.Member;
import org.bs.jpa.dto.member.MemberDTO;
import org.bs.jpa.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional // roles - lazy loading 발생
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("loadUserByUsername: " + email);

        Optional<Member> info = memberRepository.findById(email);
        Member member = info.orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않는 회원입니다."));

        log.info(member);
        log.info("------------------------------");
        log.info("------------------------------");
        log.info(member.getRoles().stream().map(role -> role.getRole()
        ).collect(Collectors.toList()));
        log.info("------------------------------");
        log.info("------------------------------");

        MemberDTO memberDTO = new MemberDTO(email, member.getPassword(), member.getNickname(),
                member.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()));

        return memberDTO;
    }

}
