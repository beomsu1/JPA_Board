package org.bs.jpa.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentUpdateDTO {

    private Long cno;
    private String comments;
    private String commeters;
    private boolean updateFlag;

    public void updateFlagTrue() {
        this.updateFlag = true;
    }

}
