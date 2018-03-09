package com.controller.user;

import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "updatePersonalDetailedServlet")
public class updatePersonalDetailedServlet extends HttpServlet {
    public UserService userService = null;
    public updatePersonalDetailedServlet(){
        userService = new UserServiceImpl();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        try {
            pw.print(userService.updatePersonalDetailed( request,response ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
