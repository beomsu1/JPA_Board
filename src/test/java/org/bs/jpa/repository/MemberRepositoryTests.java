package org.bs.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.bs.jpa.domain.Member;
import org.bs.jpa.domain.MemberRole;
import org.bs.jpa.dto.member.MemberCreateDTO;
import org.bs.jpa.dto.member.MemberUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Member Create
    @Test
    @DisplayName("회원 가입 테스트")
    @Transactional
    public void memberCreateRepositoryTest() {

        // Given
        log.info("Member Create Repository Test Start");

        MemberCreateDTO memberCreateDTO = MemberCreateDTO.builder()
                .email("beomsu_1221@naver.com")
                .password("1111")
                .nickname("beomsu")
                .phoneNumber("010-1111-1111")
                .build();

        if (memberRepository.existsById(memberCreateDTO.getEmail())) {
            log.info("중복된 이메일 주소입니다");
            return;
        }

        // When
        Member member = Member.builder()
                .email(memberCreateDTO.getEmail())
                .password(passwordEncoder.encode(memberCreateDTO.getPassword()))
                .nickname(memberCreateDTO.getNickname())
                .phoneNumber(memberCreateDTO.getPhoneNumber())
                .build();

        MemberRole user = MemberRole.builder()
                .role("ROLE_USER")
                .member(member)
                .build();

        MemberRole admin = MemberRole.builder()
                .role("ROLE_ADMIN")
                .member(member)
                .build();

        member.getRoles().add(user);
        member.getRoles().add(admin);

        memberRepository.save(member);

        // Then
        assertEquals(member.getEmail(), memberCreateDTO.getEmail());
        assertEquals(member.getNickname(), memberCreateDTO.getNickname());
        assertEquals(member.getPhoneNumber(), memberCreateDTO.getPhoneNumber());
        assertEquals(member.getRoles().get(0).getRole(), user.getRole());
        assertEquals(member.getRoles().get(1).getRole(), admin.getRole());

        log.info("member: " + member);
        member.getRoles().forEach(roles -> log.info("role: " + roles.getRole()));

        log.info("Member Create Repository Test Complete");
    }

    // Member Read
    @Test
    @DisplayName("회원 조회 테스트")
    @Transactional
    public void memberReadRepositoryTest() {

        // Given
        log.info("Member Read Repository Test Start");
        String email = "beomsu_1221@naver.com";

        // When
        Optional<Member> info = memberRepository.findById(email);
        Member member = info.orElseThrow();

        // Then
        if (member.isDeleteFlag()) {
            log.info("탈퇴한 아이디입니다.");
        } else {
            log.info(member);
            member.getRoles().forEach(role -> log.info(role.getRole()));
        }

        log.info("Member Read Repository Test Complete");

    }

    // Member Update
    @Test
    @DisplayName("회원 수정 테스트")
    @Transactional
    public void memberUpdateRepositoryTest() {

        // Given
        log.info("Member Update Repository Test Start");
        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .email("beomsu_1221@naver.com")
                .password("0903")
                .nickname("beomsu")
                .phoneNumber("010-1544-1234")
                .build();

        // When
        Optional<Member> info = memberRepository.findById(memberUpdateDTO.getEmail());
        Member member = info.orElseThrow();

        member.memberUpdate(passwordEncoder.encode(memberUpdateDTO.getPassword()), memberUpdateDTO.getNickname(),
                memberUpdateDTO.getPhoneNumber());

        // Then
        log.info(member);
        member.getRoles().forEach(roles -> log.info("role: " + roles.getRole()));

        log.info("Member Update Repository Test Complete");

    }

    // Member Delete
    @Test
    @DisplayName("회원 탈퇴 테스트")
    @Transactional
    public void memberDeleteRepositoryTest() {

        // Given
        log.info("Member Delete Repository Test Start");
        String email = "beomsu_1221@naver.com";

        // When
        Optional<Member> info = memberRepository.findById(email);
        Member member = info.orElseThrow();

        member.memberDelete();
        memberRepository.save(member);

        // Then
        assertEquals(member.isDeleteFlag(), true);
        log.info("삭제 여부: " + member.isDeleteFlag());

        log.info("Member Delete Repository Test Complete");
    }

    // Member List
    @Test
    @DisplayName("회원 리스트")
    @Transactional
    public void memberListRepositoryTest() {

        // Given
        log.info("Member List Repository Test Start");

        // When
        Pageable pageable = PageRequest.of(0, 10, Sort.by("regDate").descending());
        Page<Member> page = memberRepository.findAll(pageable);

        // Then
        List<Member> list = page.getContent();

        log.info("page: " + page);
        log.info("list: " + list);
        list.get(0).getRoles().forEach(r -> log.info(r.getRole()));

        log.info("Member List Repository Test Complete");
    }

}
