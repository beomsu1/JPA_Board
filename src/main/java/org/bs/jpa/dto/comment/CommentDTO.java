package org.bs.jpa.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {

    private Long bno;
    private Long cno;
    private String commnets;
    private String commenter;
    private String regDate;
    private boolean deleteFlag;
    private boolean updateFlag;

}
