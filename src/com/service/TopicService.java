package com.service;

import com.sun.deploy.net.HttpResponse;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface TopicService {
    /**
     * 添加话题
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject addTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    /**
     * 更新话题
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject updateTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    /**
     * 删除话题
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject deleteTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    /**
     * 查看话题的具体内容
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject getTopicDetailed(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    /**
     * 得到所有的话题信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject getAllTopicDetailed(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    /**
     * 通过用户ID查看话题信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public JSONObject getTopicById(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
