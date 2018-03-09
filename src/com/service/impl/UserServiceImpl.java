package com.service.impl;

import com.dao.IUserDAO;
import com.dao.impl.UserDAOImpl;
import com.model.User;
import com.service.UserService;
import com.util.CommonUtil;
import com.util.EnumUtil;
import com.util.FileUploadTools;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 确定每一个功能可能返回的JSON内容，分情况讨论（总体）
 * （1）没有参数的匹配
 * （2）参数为空的
 * （3）已经被注册
 */


public class UserServiceImpl implements UserService {
    IUserDAO userDAO = null;

    public UserServiceImpl(){
        try {
            userDAO = new UserDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断参数是否是空值
    public boolean checkParam(String param){
       boolean flag = false;//设置标志位
        if (param==null||"".equals( param )){
            flag =true;
        }
        return flag;
    }

    @Override
    public JSONObject userRegister(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String userName = null;
        String userPassWord = null;
        try {//设置编码的方式
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       try{//对每种可能的情况进行考虑
          userName = req.getParameter( "userName" );
          userPassWord = req.getParameter( "userPassWord" );
           if (this.checkParam( userName )){
               return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR,"userName传入的参数是空值",null);
           }
           if (this.checkParam( userPassWord )){
               return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR,"userPassWord传入的参数是空值",null);
           }
       }catch (Exception e){
       return new CommonUtil().constructResponse( EnumUtil.NO_DATA,"没有参数",null);
       }
       int result = 0;
       try {
            result = userDAO.userRegisterCheckName( userName );
            if (result == 1){
                return  new CommonUtil().constructResponse(EnumUtil.REPETITION_DATA, "该用户已经注册", null);
            }
       }catch (Exception e){
           return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
       }
       try {
          result = userDAO.userRegister( userName,userPassWord );
          if (result==1){
              return new CommonUtil().constructResponse(EnumUtil.OK, "注册成功", null);
          }
          else {
              return new CommonUtil().constructResponse(EnumUtil.FAILURE, "注册失败", null);
          }
       }catch (Exception e){
           return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
       }
    }

    @Override
    public JSONObject userLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String userName = null;
        String userPassWord = null;
        try{
            userName = req.getParameter("userName");
            userPassWord = req.getParameter("userPassWord");
            if(checkParam(userName)){
                return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "userName传入的参数为空", null);
            }
            if(checkParam( userPassWord )){
                return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, " userPassWord 传入的参数为空", null);
            }
        }catch (Exception e) {
            return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "无参数匹配", null);
        }
        User user = null;
        try{
            user = userDAO.userLogin( userName, userPassWord  );
            if (user!=null){
                req.getSession().setAttribute( "user",user );
                return new CommonUtil().constructResponse(EnumUtil.OK, "登录成功", user);
            }
            else {
                return new CommonUtil().constructResponse(EnumUtil.FAILURE, "登录失败", null);
            }
        }catch (Exception e){
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
        }
    }

    @Override
    public JSONObject userDetailedById(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = null;
        user = (User) req.getSession().getAttribute( "user" );
        if (user==null){
            return new CommonUtil().constructResponse(EnumUtil.NOT_LOGIN, "用户未登录", null);
        }
        List<Map<String,String>> list = null;
        try{
            list = new ArrayList <>(  );
            list = userDAO.userDetailedById( user.getUserId() );
            if(list == null || list.size() <=0)
                return new CommonUtil().constructResponse(EnumUtil.NO_DATA, "无数据", null);
            else
                return new CommonUtil().constructResponse(EnumUtil.OK, "获取信息成功", list);
        }catch (Exception e){
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
        }
    }

    @Override
    public JSONObject updateUserDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = null;
        user = (User) req.getSession().getAttribute( "user" );
        if (user == null) {
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        }
        String userName = null;
        String  userPassWord = null;
        try {
            userName = req.getParameter( "userName" );
            userPassWord  = req.getParameter( "userPassWord" );
            if (checkParam( userName )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "userName传入的参数为空", null );
            }
            if (checkParam(  userPassWord )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "userPassWord传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配", null );
        }
        int result = 0;
        try {
            result = userDAO.updateUserDetailed( user.getUserId(), userName, userPassWord );
            if (result == 1) {
                return new CommonUtil().constructResponse( EnumUtil.OK, "更新成功", null );
            } else {
                return new CommonUtil().constructResponse( EnumUtil.FAILURE, "更新失败", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject addPersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("user");
        if(user == null)
            return new CommonUtil().constructResponse(EnumUtil.NOT_LOGIN, "用户未登录", null);
        FileUploadTools fileUploadTools = null;
        List<String> photo = null;
        try {
            fileUploadTools = new FileUploadTools(req, 3*1024*1024, "E:/代码类/onlinezhihu/web/images/");
            String name = fileUploadTools.getParameter("name");
            photo =fileUploadTools.saveAll("E:/代码类/onlinezhihu/web/images/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String userSex =  fileUploadTools.getParameter("userSex");
            String userIntroduction = fileUploadTools.getParameter("userIntroduction");
            String userAddress = fileUploadTools.getParameter("userAddress");
            String userIndustry = fileUploadTools.getParameter("userIndustry");
            String userProfessional= fileUploadTools.getParameter("userProfessional");
            String userResume  = fileUploadTools.getParameter("userResume ");
            String userEducation =  fileUploadTools.getParameter("userEducation");
            String userImg =  "http://localhost:8080/onlinezhihu/web/images/"+photo.get(0);
            String userEmail = fileUploadTools.getParameter("userEmail");
            user.setUserSex( userSex );
            user.setUserIntroduction( userIntroduction );
            user.setUserAddress( userAddress );
            user.setUserIndustry( userIndustry );
            user.setUserEducation( userEducation );
            user.setUserProfessional( userProfessional );
            user.setUserResume( userResume );
            user.setUserImg( userImg );
            user.setUserEmail( userEmail );
        } catch (Exception e) {
            return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null);
        }
        int reslut = 0;
        try {
            reslut = userDAO.addPersonalDetailed( user );
            if(reslut == 1)
                return new CommonUtil().constructResponse(EnumUtil.OK, "添加成功", null);
            else
                return new CommonUtil().constructResponse(EnumUtil.FAILURE, "添加失败", null);
        }catch (SQLException e) {
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
        }

    }

    @Override
    public JSONObject updatePersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("user");
        if(user == null)
            return new CommonUtil().constructResponse(EnumUtil.NOT_LOGIN, "用户未登录", null);
        FileUploadTools fileUploadTools = null;
        List<String> photo = null;
        try {
            fileUploadTools = new FileUploadTools(req, 3*1024*1024, "E:/代码类/onlinezhihu/web/images/");
            String name = fileUploadTools.getParameter("name");
            photo =fileUploadTools.saveAll("E:/代码类/onlinezhihu/web/images/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String userSex =  fileUploadTools.getParameter("userSex");
            String userIntroduction = fileUploadTools.getParameter("userIntroduction");
            String userAddress = fileUploadTools.getParameter("userAddress");
            String userIndustry = fileUploadTools.getParameter("userIndustry");
            String userProfessional= fileUploadTools.getParameter("userProfessional");
            String userResume  = fileUploadTools.getParameter("userResume ");
            String userEducation =  fileUploadTools.getParameter("userEducation");
            String userImg =  "http://localhost:8080/onlinezhihu/web/images/"+photo.get(0);
            String userEmail = fileUploadTools.getParameter("userEmail");
            user.setUserSex( userSex );
            user.setUserIntroduction( userIntroduction );
            user.setUserAddress( userAddress );
            user.setUserIndustry( userIndustry );
            user.setUserEducation( userEducation );
            user.setUserProfessional( userProfessional );
            user.setUserResume( userResume );
            user.setUserImg( userImg );
            user.setUserEmail( userEmail );
        } catch (Exception e) {
            return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null);
        }
        int reslut = 0;
        try {
            reslut =userDAO.updatePersonalDetailed( user );
            if(reslut == 1)
                return new CommonUtil().constructResponse(EnumUtil.OK, "修改成功", null);
            else
                return new CommonUtil().constructResponse(EnumUtil.FAILURE, "修改失败", null);
        }catch (SQLException e) {
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
        }
    }

    @Override
    public JSONObject deletePersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("user");
        if(user == null)
            return new CommonUtil().constructResponse(EnumUtil.NOT_LOGIN, "用户未登录", null);
        if(user.getUserStatus() != 2)
            return new CommonUtil().constructResponse(EnumUtil.FAILURE, "没有管理员权限", null);
        int id = 0;
        try{
            String userId = req.getParameter("userId");
            if(checkParam(userId)){
                return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "userId传入的参数为空", null);
            }
            id = Integer.parseInt(userId);
        } catch (Exception e) {
            return new CommonUtil().constructResponse(EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null);
        }

        int result = 0;
        try{
            result = userDAO.deletePersonDetailed( id );
        }catch (Exception e) {
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库异常", null);
        }
        if(result == 1)
            return new CommonUtil().constructResponse(EnumUtil.OK, "删除成功", null);
        return new CommonUtil().constructResponse(EnumUtil.FAILURE, "删除失败", null);
    }

    @Override
    public JSONObject getPersonalDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        List<Map<String, String >> list = null;
        try{
            list =userDAO.getPersonalDetailed();
            if(list == null)
                return new CommonUtil().constructResponse(EnumUtil.NO_DATA, "数据为空", null);
            else
                return new CommonUtil().constructResponse(EnumUtil.OK, "获取信息成功", list);
        }catch (SQLException e) {
            return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
        }
    }
}
