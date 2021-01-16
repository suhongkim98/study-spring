package com.studyspring.basic.provider.dto;

public class BoardCommentDTO {
	private int idx;
	private String date;
	private int ownerIdx;
	private int boardIdx;
	private String content;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOwnerIdx() {
		return ownerIdx;
	}
	public void setOwnerIdx(int ownerIdx) {
		this.ownerIdx = ownerIdx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
