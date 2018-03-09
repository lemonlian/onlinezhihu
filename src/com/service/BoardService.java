package com.service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface BoardService  {
    /**
     * 添加版面
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject addBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 更新版面
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject updateBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 删除版面
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject deleteBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 查看版面的信息
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject getBoardDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
