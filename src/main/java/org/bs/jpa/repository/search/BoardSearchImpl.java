package org.bs.jpa.repository.search;

import org.bs.jpa.domain.Board;
import org.bs.jpa.domain.QBoard;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    // 생성자를 만들어줘서 오류를 해결하자 - 컴파일 에러 해결
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO) {

        QBoard board = QBoard.board;

        // Board 엔티티를 참조하는 JQPL 쿼리 생성
        JPQLQuery<Board> query = from(board);

        // keyword, type 생성
        String keyword = pageRequestDTO.getKeyword();
        String type = pageRequestDTO.getType();

        if (keyword != null && type != null) {

            String[] arr = type.split("");

            // 우선 연산자 생성
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String arrType : arr) {

                switch (arrType) {
                    case "t" -> booleanBuilder.or(board.title.contains(keyword));
                    case "c" -> booleanBuilder.or(board.content.contains(keyword));
                    case "w" -> booleanBuilder.or(board.writer.contains(keyword));
                }
            }

            // where 절에 type 추가
            query.where(booleanBuilder);
        }

        // pageable 생성
        Pageable pageable = makePageable(pageRequestDTO);

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<BoardListDTO> listQuery = query.select(Projections.bean(
                // DTO로 매핑
                BoardListDTO.class,
                board.bno,
                board.title,
                board.content,
                board.writer,
                board.regDate,
                board.modDate));

        // listQuery를 실행 후 결과를 dtoList에 담기
        List<BoardListDTO> dtoList = listQuery.fetch();

        // 갯수 담기
        Long totalCount = listQuery.fetchCount();
        
        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);

    }

}
