package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public Board write(BoardDto.Write dto, String logonId) {
		Board board = dto.toEntity().addWriter(logonId);
		boardDao.save(board);
		return board;
	}
	
	
}
