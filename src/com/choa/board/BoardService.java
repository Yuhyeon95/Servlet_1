package com.choa.board;

import java.util.ArrayList;

public class BoardService {

	private BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public ArrayList<BoardDTO> boardList() throws Exception {
		return boardDAO.boardList();
	}
	
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return boardDAO.boardWrite(boardDTO);
	}
}
