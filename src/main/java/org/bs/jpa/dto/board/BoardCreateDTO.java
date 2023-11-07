package org.bs.jpa.dto.board;

import java.util.ArrayList;
import java.util.List;

import org.bs.jpa.domain.Board;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardCreateDTO {

    private String title;
    private String content;
    private String writer;

    @Builder.Default
    private List<String> files = new ArrayList<>();

    @Builder.Default
    private List<MultipartFile> multipartFiles = new ArrayList<>();
    
    // DTO -> Entity
    public Board toEntity(){

        Board board = Board.builder()
        .title(this.getTitle())
        .content(this.getContent())
        .writer(this.getWriter())
        .build();

        return board;
    }

}