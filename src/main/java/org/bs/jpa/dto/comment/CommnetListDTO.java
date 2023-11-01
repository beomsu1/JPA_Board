package org.bs.jpa.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommnetListDTO {

    private Long cno;
    private String commnets;
    private String commenter;
    private String regDate;
    
}
