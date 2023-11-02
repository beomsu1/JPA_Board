package org.bs.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "bs_board_file")
public class Fileupload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    private String uuid;
    private String fname;
    private int ord;
    
    // ordRefresh
    public void ordRefresh(int ord){
        this.ord = ord;
    }


}
