package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.service.BoardService;

@SpringBootTest
public class BoardServiceTest {
	@Autowired
	private BoardService service;
	//@Test
	public void writeTest() {
		BoardDto.Write dto=BoardDto.Write.builder().title("zzz").content("zzzz").build();
		Board board = service.write(dto, "spring");
		assertNotNull(board.getBno());
	}
	//@Test
	public void readTest() {
		Assertions.assertThrows(BoardNotFoundException.class,
				()->service.read(1000,"spring"));
		BoardDto.Read dto1=service.read(4, null);
		BoardDto.Read dto2=service.read(4, "fall");
		BoardDto.Read dto3= service.read(4,"spring");
	}
	//@Test
	public void listTest() {
		BoardDto.Page dto1=service.list(1,"spring");
	}
	
	@Test
	public void updateTest() {
		BoardDto.Update dto1=BoardDto.Update.builder().title("변경된 제목").content("내용내용").bno(4).build();
	}
	
	
}
