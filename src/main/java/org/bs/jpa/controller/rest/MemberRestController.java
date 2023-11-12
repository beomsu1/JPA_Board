package org.bs.jpa.controller.rest;

import org.bs.jpa.dto.member.MemberCreateDTO;
import org.bs.jpa.dto.member.MemberDTO;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.dto.member.MemberUpdateDTO;
import org.bs.jpa.service.MemberService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/member/")
public class MemberRestController {

    private final MemberService memberService;

    // Member List
    @GetMapping("list")
    PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequestDTO) {

        log.info("GET | Member List Controller");

        return memberService.memberList(pageRequestDTO);
    }

    // Member Create
    @PostMapping("signup")
    String memberSignup(MemberCreateDTO memberCreateDTO) {

        log.info("POST | Member Signup Controller");

        memberService.memberCreate(memberCreateDTO);

        return "회원 가입 완료";
    }

    // Member Read
    @GetMapping("{email}")
    MemberDTO memberRead(@PathVariable("email") String email) {

        log.info("GET | Member Read Controller");

        return memberService.memberRead(email);
    }

    // Member Update
    @PutMapping("{email}")
    String memberUpdate(MemberUpdateDTO memberUpdateDTO) {

        log.info("PUT | Member Update Controller");

        memberService.memberUpdate(memberUpdateDTO);

        return "회원 수정 완료";
    }

    // Member Delete
    @DeleteMapping("{email}")
    String memberDelete(@PathVariable("email") String email) {
        
        log.info("DELETE | Member Delete Controller");

        memberService.memberDelete(email);

        return "회원 탈퇴 완료";
    }

}
