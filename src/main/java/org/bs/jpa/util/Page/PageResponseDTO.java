package org.bs.jpa.util.Page;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
// 재사용 -> 제네릭 사용
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private long totalCount;

    private List<Integer> pageNum;

    private boolean prev;
    private boolean next;

    // 페이지 요청 정보를 저장하면서 응답 데이터와 함께 클라이언트에게 필요한 정보를 주기 위해 필드 선언
    private PageRequestDTO pageRequestDTO;

    private int page, size, start, end;

    // 데이터 , 전체 개수 , 페이지 요청 정보 초기화하는 생성자 생성
    public PageResponseDTO(List<E> dtoList, long totalCount, PageRequestDTO pageRequestDTO) {
        this.dtoList = dtoList;
        this.totalCount = totalCount;
        this.pageRequestDTO = pageRequestDTO;
        
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        // 범위에 해당하는 마지막 페이지 번호
        // page = 15/10.0 -> 1.5 -> ceil -> 2.0 * 10 -> 20
        int tempEnd = (int)(Math.ceil(page/10.0)*10);

        // 시작 페이지 번호
        this.start = tempEnd -9;

        // prev 활성화
        this.prev = start != 1;

        // 총 페이지 번호 - 올림(전체갯수/페이지 사이즈)
        int pageEnd = (int)(Math.ceil(totalCount/(double)size));

        // 범위에 해당하는 마지막 페이지 번호 저장
        this.end = tempEnd > pageEnd ? pageEnd : tempEnd ;

        // next 활성화
        this.next = (this.end * this.size) < totalCount;

        // pageNum
        this.pageNum = IntStream.rangeClosed(start, end).boxed().toList();
    }

    






    
}
