package org.bs.jpa.repository.search;

import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;

public interface MemberSearch {

    PageResponseDTO<MemberListDTO> memberList (PageRequestDTO pageRequestDTO);
    
}
