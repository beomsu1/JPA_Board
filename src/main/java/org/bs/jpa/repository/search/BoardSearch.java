package org.bs.jpa.repository.search;

import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BoardSearch {

    PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO);

    // pageable 생성 메소드 - default는 인터페이스에서 메소드 정의 가능
    default Pageable makePageable(PageRequestDTO pageRequestDTO) {

        // getPage() -1 -> 0부터 시작하기 위함
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        return pageable;

    }

}
