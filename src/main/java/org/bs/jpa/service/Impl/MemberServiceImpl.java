package org.bs.jpa.service.Impl;

import java.util.Optional;

import org.bs.jpa.domain.Member;
import org.bs.jpa.domain.MemberRole;
import org.bs.jpa.dto.member.MemberCreateDTO;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.dto.member.MemberReadDTO;
import org.bs.jpa.dto.member.MemberUpdateDTO;
import org.bs.jpa.exception.EmailErrorException.emailError;
import org.bs.jpa.repository.MemberRepository;
import org.bs.jpa.service.MemberService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;



    // Member Create
    @Override
    public void memberCreate(MemberCreateDTO memberCreateDTO) {

        log.info("Member Create Service Start");

        if(memberRepository.existsById(memberCreateDTO.getEmail())){

            throw new emailError("이미 가입된 이메일입니다.");
        }

        Member member = Member.builder()
                .email(memberCreateDTO.getEmail())
                .password(passwordEncoder.encode(memberCreateDTO.getPassword()))
                .nickname(memberCreateDTO.getNickname())
                .phoneNumber(memberCreateDTO.getPhoneNumber())
                .build();

        MemberRole role = MemberRole.builder()
                .role("ROLE_USER")
                .member(member) // 넣어줘야 DB에 값 들어감
                .build();

        member.getRoles().add(role);

        memberRepository.save(member);

    }

    // Member Read
    @Override
    public MemberReadDTO memberRead(String email) {

        log.info("Member Read Service Start");

        Optional<Member> info = memberRepository.findById(email);
        Member member = info.orElseThrow(() -> new emailError("이메일이 존재하지 않는 회원입니다."));

        if (member.isDeleteFlag()) {
            throw new emailError("삭제된 회원입니다.");
        }

        MemberReadDTO memberReadDTO = new MemberReadDTO();
        memberReadDTO.entityToDTO(member);

        return memberReadDTO;

    }

    // Member Update
    @Override
    public void memberUpdate(MemberUpdateDTO memberUpdateDTO) {

        log.info("Member Update Service Start");

        Optional<Member> info = memberRepository.findById(memberUpdateDTO.getEmail());
        Member member = info.orElseThrow(() -> new emailError("이메일이 존재하지 않는 회원입니다."));

        if (member.isDeleteFlag()) {
            throw new emailError("삭제된 회원입니다.");
        }

        member.memberUpdate(passwordEncoder.encode(memberUpdateDTO.getPassword()), memberUpdateDTO.getNickname(),
                memberUpdateDTO.getPhoneNumber());

    }

    // Member Delete
    @Override
    public void memberDelete(String email) {

        log.info("Member Delete Service Start");

        Optional<Member> info = memberRepository.findById(email);
        Member member = info.orElseThrow(() -> new emailError("이메일이 존재하지 않는 회원입니다."));

        if (member.isDeleteFlag()) {
            throw new emailError("삭제된 회원입니다.");
        }

        member.memberDelete();
        memberRepository.save(member);
    }

    // Member List
    @Override
    public PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequestDTO) {

        log.info("Member List Service Start");

        return memberRepository.memberList(pageRequestDTO);
    }

}
