package org.bs.jpa.service;

import org.bs.jpa.dto.member.MemberCreateDTO;
import org.bs.jpa.dto.member.MemberDTO;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.dto.member.MemberUpdateDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    // Member Create
    @Test
    @DisplayName("회원 가입 서비스 테스트")
    // @Transactional
    public void memberCreateServiceTest(){

        // Given
        log.info("Member Create Service Test Start");

        MemberCreateDTO memberCreateDTO = MemberCreateDTO.builder()
        .email("beomsu_1221@gmail.com")
        .password("1111")
        .nickname("beomsu")
        .phoneNumber("010-1111-1111")
        .build();

        // When
        memberService.memberCreate(memberCreateDTO);

        // Then
        log.info("Member Create Service Test Complete");
    }

    // Member Read
    @Test
    @DisplayName("회원 조회 서비스 테스트")
    @Transactional
    public void memberReadServiceTest(){

        // Given
        log.info("Member Read Service Test Start");
        String email = "beomsu_1221@daum.net";

        // When
        MemberDTO memberDTO = memberService.memberRead(email);

        // Then
        log.info(memberDTO.toString());
        log.info("Member Read Service Test Complete");
    }

    // Member Update
    @Test
    @DisplayName("회원 수정 서비스 테스트")
    @Transactional
    public void memberUpdateServiceTest(){

        // Given
        log.info("Member Update Service Test Start");

        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
        .email("beomsu_1221@daum.net")
        .password("1234")
        .nickname("beomsu1")
        .password("010-1234-1234")
        .build();

        // When
        memberService.memberUpdate(memberUpdateDTO);

        // Then
        log.info("Member Update Service Test Complete");
    }

    // Member Delete
    @Test
    @DisplayName("회원 탈퇴 서비스 테스트")
    @Transactional
    public void memberDeleteServiceTest(){

        // Given
        log.info("Member Delete Service Test Start");
        String email = "beomsu_1221@daum.net";

        // When
        memberService.memberDelete(email);

        // Then
        log.info("Member Delete Service Test Complete");
    }
    
    // Member List
    @Test
    @DisplayName("회원 리스트,검색 서비스 테스트")
    @Transactional
    public void memberListServiceTest(){

        // Given
        log.info("Member List Service Test Start");

        String type = "enpr";
        String keyword = "beomsu";

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .type(type)
        .keyword(keyword)
        .build();

        // When
        PageResponseDTO<MemberListDTO> list = memberService.memberList(pageRequestDTO);

        // Then
        log.info(list.toString());

        log.info("Member List Service Test Complete");
    }
}
