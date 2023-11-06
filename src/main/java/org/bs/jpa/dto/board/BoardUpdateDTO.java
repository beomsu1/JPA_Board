package org.bs.jpa.dto.board;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateDTO {

    private Long bno;
    private String title;
    private String content;

    @Builder.Default
    private List<String> files = new ArrayList<>();

}
