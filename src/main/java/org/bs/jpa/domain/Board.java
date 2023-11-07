package org.bs.jpa.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@ToString(exclude = "files")
@EntityListeners(AuditingEntityListener.class) // 생명주기 이벤트를 리스닝하기 위해 사용
public class Board {

    @Id // primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Long bno;
    private String title;
    private String content;
    private String writer;

    @CreatedDate // 생성시간 기준
    private String regDate;

    @LastModifiedDate // 수정시간 기준
    private String modDate;

    // FileUpload
    @Builder.Default
    @JoinColumn(name = "board_bno")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fileupload> files = new ArrayList<>();

    // 생성 시간 포맷 변경
    @PrePersist
    public void onPrePersist() {

        // 현재 날짜와 시간 가져오기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 사용할 형식 지정 (날짜, 오후/오전, 시간)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd a h:mm");

        // 날짜와 시간을 문자열로 형식화
        String formattedDateTime = currentDateTime.format(formatter);

        // 날짜와 시간을 저장
        this.regDate = formattedDateTime;
        this.modDate = this.regDate;
    }

    // 수정 후 시간 포맷 변경
    @PreUpdate // 엔티티가 변경될 때 실행
    public void onPreUpdate() {

        // 현재 날짜와 시간 가져오기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 사용할 형식 지정 (날짜, 오후/오전, 시간)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd a h:mm");

        // 날짜와 시간을 문자열로 형식화
        String formattedDateTime = currentDateTime.format(formatter);

        this.modDate = formattedDateTime;
    }

    // Board Update Method
    public void update(BoardUpdateDTO boardUpdateDTO) {

        this.title = boardUpdateDTO.getTitle();
        this.content = boardUpdateDTO.getContent();

    }

    // Entity -> BoardDTO
    public void dtoTOEntity(BoardDTO boardDTO) {

        this.bno = boardDTO.getBno();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.writer = boardDTO.getWriter();
        this.regDate = boardDTO.getRegDate();
        this.modDate = boardDTO.getModDate();

    }

    // file save
    public void fileSave(String fname){

        Fileupload fileupload = Fileupload.builder()
        .uuid(UUID.randomUUID().toString())
        .fname(fname)
        .build();

        fileupload.ordRefresh(files.size());

        files.add(fileupload);
    }

    // file clear
    public void fileClear(){
        files.clear();
    }

}