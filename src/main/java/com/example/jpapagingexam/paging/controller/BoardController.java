package com.example.jpapagingexam.paging.controller;

import com.example.jpapagingexam.paging.entity.Board;
import com.example.jpapagingexam.paging.entity.BoardRepository;
import com.example.jpapagingexam.paging.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	private final BoardRepository repository;

	@PostConstruct
	public void testBoardSetting() {
		for (long i = 0; i < 100; i++) {
			Board board = new Board("LEE", "글 제목 " + i, LocalDateTime.now().plusMinutes(i));
			repository.save(board);
		}
	}


	@GetMapping(value = {"/board/list", "/board/list/{offset}"})
	public String loadMessageView(@PathVariable("offset") Optional<Integer> optionalOffset,
								  Model model) {

		int offset = service.getOffset(optionalOffset);

		PageRequest pageRequest = PageRequest.of(offset, 5, Sort.Direction.DESC, "createdAt");

		Page<Board> boardList = repository.findByAuthor(pageRequest, "LEE");
		int pageNumber = boardList.getNumber() + 1;
		LocalDateTime createAt = boardList.getContent().get(0).getCreatedAt();

		model.addAttribute("boardList", boardList);
		model.addAttribute("createAt", createAt);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("maxPage", 5);

		return "board/boardList";
	}

}
