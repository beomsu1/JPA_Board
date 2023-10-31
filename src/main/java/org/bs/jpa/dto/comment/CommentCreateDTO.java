package org.bs.jpa.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentCreateDTO {

    private Long bno;
    private String commnets;
    private String commenter;
    
}
