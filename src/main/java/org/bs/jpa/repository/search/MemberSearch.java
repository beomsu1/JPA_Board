package org.bs.jpa.repository.search;

import org.bs.jpa.dto.member.MemberListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface MemberSearch {

    PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequestDTO);

    default Pageable makePageable(PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("regDate").descending());

        return pageable;
    }

}
