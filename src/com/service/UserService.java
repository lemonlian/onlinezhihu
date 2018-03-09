package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public interface UserService{
	/**
     * 用户注册
     */
    JSONObject userRegister(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    
    /**
     * 用户登录
     */
    JSONObject userLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    
    /**
     * 根据用户ID 返回用户的账号详情
     */
    JSONObject userDetailedById(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    
    /**
     * 修改用户账号信息
     */
    JSONObject updateUserDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     *增加用户个人信息
     */
    JSONObject addPersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

    /**
     * 修改用户个人信息
     */
    JSONObject  updatePersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    /**
     * 删除用户个人信息
     */
    JSONObject  deletePersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    /**
     * 查看用户个人信息
     */
    JSONObject getPersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
