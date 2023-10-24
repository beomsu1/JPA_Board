package org.bs.jpa.domain;

import java.time.LocalDateTime;

import org.bs.jpa.dto.board.BoardDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Java 클래스를 데이터베이스 테이블에 매핑할 수 있는 엔터티로 정의
@Table(name = "bs_board") // Table 생성
@AllArgsConstructor // 모든 필드를 초기화하는 생성자를 자동으로 생성
@NoArgsConstructor // 매개변수 없는 기본 생성자가 생성
@Builder
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) // 생명주기 이벤트를 리스닝하기 위해 사용
public class Board {
    
    @Id // primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Long bno;
    private String title;
    private String content;
    private String writer;

    @CreatedDate // 생성시간 기준
    private LocalDateTime regDate;

    @LastModifiedDate // 수정시간 기준
    private LocalDateTime modDate;

    // Board Update Method
    public void update(String title, String content){

        this.title = title;
        this.content = content;

    }

    // Entity -> BoardDTO
    public void dtoTOEntity(BoardDTO boardDTO){

        this.bno = boardDTO.getBno();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.writer = boardDTO.getWriter();
        this.regDate = boardDTO.getRegDate();
        this.modDate = boardDTO.getModDate();

    }

}