package com.studyspring.basic.provider.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studyspring.basic.core.BoardDAOInterface;
import com.studyspring.basic.provider.dto.BoardDTO;

@Repository
public class BoardDAO implements BoardDAOInterface {
	private SqlSessionTemplate session;
	
	@Autowired
	public BoardDAO(SqlSessionTemplate sqlSessionTemplate) {
		this.session = sqlSessionTemplate;
	}
	@Override
	public void insertBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardDTO> selectBoard(int courseIdx) {
		List<BoardDTO> boards = session.selectList("boardTable.selectBoard", courseIdx);
		return boards;
	}
	
}
