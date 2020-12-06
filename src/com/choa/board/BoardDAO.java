package com.choa.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.choa.util.DBConnector;

public class BoardDAO {

	private DBConnector dbConnector;
	
	public BoardDAO() {
		dbConnector = new DBConnector();
	}
	
	public ArrayList<BoardDTO> boardList() throws Exception {
		Connection con = dbConnector.getConnector();
		ArrayList<BoardDTO> ar=  new ArrayList<>();
		String sql = "select * from board";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setNum(rs.getLong("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setRegdate(rs.getDate("regdate"));
			boardDTO.setHit(rs.getLong("hit"));
			ar.add(boardDTO);
		}
		rs.close();
		st.close();
		con.close();
		return ar;
	}
	

	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dbConnector.getConnector();
		String sql = "insert into board values(notice.nextval, ?, ?, ?, sysdate, 0)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getWriter());
		st.setString(3, boardDTO.getContents());
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}


	
}
