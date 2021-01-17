package com.studyspring.basic.core;

import java.util.List;

import com.studyspring.basic.provider.dto.BoardDTO;

public interface BoardDAOInterface {
	public void insertBoard(BoardDTO board);
	public List<BoardDTO> selectBoardAll(int courseIdx);
	public BoardDTO selectBoard(int boardIdx);
}
