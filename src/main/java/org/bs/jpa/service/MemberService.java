package org.bs.jpa.service;

import org.bs.jpa.dto.member.MemberCreateDTO;
import org.bs.jpa.dto.member.MemberDTO;
import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.dto.member.MemberUpdateDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {

    void memberCreate(MemberCreateDTO memberCreateDTO);

    MemberDTO memberRead(String email);

    void memberUpdate(MemberUpdateDTO memberUpdateDTO);

    void memberDelete(String email);

    PageResponseDTO<MemberListDTO> memberList (PageRequestDTO pageRequestDTO);

}
