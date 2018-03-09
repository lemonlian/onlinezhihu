package com.service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ReplyService {
    /**
     * 创建回复
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject createReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 修改回复
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject updateReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 删除回复
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject deleteReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 查看回复
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public JSONObject getReplyDetailedById(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
