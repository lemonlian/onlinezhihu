package com.controller.topic;

import com.service.TopicService;
import com.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 模糊查找帖子
 */
@WebServlet(name = "SeekTopicServlet")
public class SeekTopicServlet extends HttpServlet {
    public TopicService topicService= null;
    public SeekTopicServlet() throws Exception {
        topicService = new TopicServiceImpl();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        try {
            pw.print(topicService.seekTopic( request,response ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
