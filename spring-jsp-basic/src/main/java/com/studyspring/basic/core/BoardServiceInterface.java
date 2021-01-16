package com.studyspring.basic.core;

import java.util.List;
import java.util.Optional;

import com.studyspring.basic.provider.dto.BoardDTO;

public interface BoardServiceInterface {
	public void registerBoard(BoardDTO board);
	public Optional<List<BoardDTO>> selectBoard(int courseIdx);
}
