package org.bs.jpa.controller;

import org.bs.jpa.dto.board.BoardCreateDTO;
import org.bs.jpa.dto.board.BoardDTO;
import org.bs.jpa.dto.board.BoardListDTO;
import org.bs.jpa.dto.board.BoardUpdateDTO;
import org.bs.jpa.service.BoardService;
import org.bs.jpa.util.Page.PageRequestDTO;
import org.bs.jpa.util.Page.PageResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board/")
@EnableMethodSecurity
public class BoardController {
    
    private final BoardService boardService;

    // List
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getBoardList(Model model, PageRequestDTO pageRequestDTO){

        log.info("GET | Board List Controller");

       PageResponseDTO<BoardListDTO> list = boardService.boardList(pageRequestDTO);

        model.addAttribute("boardList", list);

    }

    // Get Create
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getBoardCreate(){

        log.info("GET : boardCreate");
    }

    // Get Read
    @GetMapping("read/{bno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getBoardRead(@PathVariable("bno") Long bno, Model model){

        log.info("GET | Board Read Controller");

        BoardDTO boardDTO = boardService.boardReadOne(bno);

        model.addAttribute("boardRead", boardDTO);

        return "/board/read";

    }

    // Get Update
    @GetMapping("update/{bno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getBoardUpdate(@PathVariable("bno") Long bno, Model model){

        log.info("GET | Board Update Controller");

        BoardDTO boardDTO = boardService.boardReadOne(bno);

        model.addAttribute("boardRead", boardDTO);

        return "/board/update";

    }

    // Post Create
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postBoardCreate(BoardCreateDTO boardCreateDTO, RedirectAttributes attributes){

        log.info("POST | Board Create Controller");

        boardService.boardCreate(boardCreateDTO);

        attributes.addFlashAttribute("message", "게시글이 생성되었습니다.");

        return "redirect:/board/list";

    }

    // Post Update
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postBoardUpdate(BoardUpdateDTO boardUpdateDTO, RedirectAttributes attributes){

        log.info("POST | Board Update Controller");

        boardService.boardUpdate(boardUpdateDTO);

        attributes.addFlashAttribute("message", "게시글이 수정되었습니다.");

        return "redirect:/board/read" + boardUpdateDTO.getBno();
    }

    //Post Delete
    @PostMapping("delete/{bno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postBoardDelete(@PathVariable("bno") Long bno, RedirectAttributes attributes){

        log.info("POST | Board Delete Controller");

        boardService.boardDelete(bno);

        attributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");

        return "redirect:/board/list";

    }

}
