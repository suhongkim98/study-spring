package com.studyspring.basic.provider.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyspring.basic.core.BoardServiceInterface;
import com.studyspring.basic.provider.dao.BoardDAO;
import com.studyspring.basic.provider.dto.BoardDTO;

@Service
public class BoardService implements BoardServiceInterface{
	private BoardDAO boardDAO;
	@Autowired
	public BoardService(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void registerBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<List<BoardDTO>> selectBoard(int courseIdx) {
		List<BoardDTO> boards = boardDAO.selectBoard(courseIdx);
		return Optional.ofNullable(boards);
	}
}
