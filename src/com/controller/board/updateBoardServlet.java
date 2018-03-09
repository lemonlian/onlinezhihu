package com.controller.board;

import com.service.BoardService;
import com.service.impl.BoardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "updateBoardServlet")
public class updateBoardServlet extends HttpServlet {
    public BoardService boardService= null;
    public updateBoardServlet() throws Exception {
        boardService = new BoardServiceImpl();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        try {
            pw.print(boardService.updateBoard( request,response ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
