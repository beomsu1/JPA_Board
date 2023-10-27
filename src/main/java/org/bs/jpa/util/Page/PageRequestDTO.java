package org.bs.jpa.util.Page;

import lombok.Data;

@Data
public class PageRequestDTO {

    private int page = 1;
    private int size = 10;

    private String keyword;
    private String type;

    // 기본적인 페이지 설정
    public PageRequestDTO() {
        this(1, 10);
    }

    // 페이지 및 크기를 사용자 지정 가능
    public PageRequestDTO(int page, int size) {
        this(page, size, null, null);
    }

    // 페이지, 크기 외에도 유형 및 키워드와 같은 추가 정보를 설정
    public PageRequestDTO(int page, int size, String keyword, String type) {

        // page 크기 제한
        this.page = page < 0 ? 1 : page;

        // size 크기 제한
        this.size = size < 0 || size > 100 ? 10 : size;
        this.keyword = keyword;
        this.type = type;
    }

    

    
}
