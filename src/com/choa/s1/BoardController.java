package com.choa.s1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.board.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
        boardService = new BoardService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();
		path = path.substring(path.lastIndexOf("/"));
		System.out.println(path);
		String info = "";
		ArrayList<BoardDTO> ar = null;
		if(path.equals("/boardList.board")) {
			System.out.println("Board List");
			try {
				ar = boardService.boardList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("list", ar);
			System.out.println(request.getAttribute("list"));
			info = "./board/boardList.jsp";
		}else if(path.equals("/boardWrite.board")) {
			System.out.println("Board Write");
			info = "./board/boardWrite.jsp";
		}
		else {
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(info);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();
		path = path.substring(path.lastIndexOf("/"));
		System.out.println(path);
		String info = "";
		
		if(path.equals("/boardWrite.board")) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle(title);
		boardDTO.setWriter(writer);
		boardDTO.setContents(contents);
		
		try {
			int result = boardService.boardWrite(boardDTO);
			if(result>0) {
				System.out.println("write success");
				response.sendRedirect("./boardList.board");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			
		}
		
	
	}

}
