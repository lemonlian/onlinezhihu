package com.controller.reply;

import com.service.ReplyService;
import com.service.impl.ReplyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "DeleteReplyServlet")
public class DeleteReplyServlet extends HttpServlet {
    public ReplyService replyService = null;

    public DeleteReplyServlet() throws Exception {
        replyService = new ReplyServiceImpl();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setCharacterEncoding( "utf-8" );
        response.setHeader( "Content-type", "text/html;charset=UTF-8" );
        PrintWriter pw = response.getWriter();
        try {
            pw.print( replyService.deleteReply( request, response ) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
