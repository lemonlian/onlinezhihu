package com.service.impl;

import com.dao.ITopicDAO;
import com.dao.impl.TopicDAOImpl;
import com.model.Board;
import com.model.Topic;
import com.model.User;
import com.service.TopicService;
import com.util.CommonUtil;
import com.util.EnumUtil;
import com.util.IPTimeStamp;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopicServiceImpl implements TopicService {
    ITopicDAO topicDAO = null;

    public TopicServiceImpl() throws Exception {
        this.topicDAO = new TopicDAOImpl();
    }

    public boolean checkParam(String param) {
        if (param == null || "".equals( param ))
            return true;
        return false;
    }

    @Override
    public JSONObject addTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        Topic topic = new Topic();
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
           // String boardId = request.getParameter( "boardId" );
            String topicTitle = request.getParameter( "topicTitle" );
            String topicContent = request.getParameter( "topicContent" );
            String userId = String.valueOf( user.getUserId() );
            String topicPublshTime = new IPTimeStamp().getTimeStamp();
//            if (topicPublshTime!=null){
//               request.getSession().setAttribute( "topicPublshTime",topicPublshTime );
//            }
            String topicModifyTime =new IPTimeStamp().getTimeStamp();
//            if (checkParam( boardId )) {
//                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardId传入的参数为空", null );
//            }
            if (checkParam( topicTitle )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicTitle传入的参数为空", null );
            }
            if (checkParam( topicContent )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicContent传入的参数为空", null );
            }
            if (checkParam( userId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "userId传入的参数为空", null );
            }
            //topic.setBoardId( Integer.parseInt( boardId ) );
            topic.setTopicTitle( topicTitle );
            topic.setTopicContent( topicContent );
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        int result = 0;
        try {
            result = topicDAO.addTopic( topic ,user.getUserId());
            if (result == 1)
                return new CommonUtil().constructResponse( EnumUtil.OK, "添加成功", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.FAILURE, "添加失败", null );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }

    }

    @Override
    public JSONObject updateTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        Topic topic = new Topic();
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            String topicId = request.getParameter( "topicId" );
            String boardId = request.getParameter( "boardId" );
            String topicTitle = request.getParameter( "topicTitle" );
            String topicContent = request.getParameter( "topicContent" );
            String userId = String.valueOf( user.getUserId() );
            String topicPublishTime = String.valueOf( request.getSession().getAttribute( "topicPublishTime" ) );
            String topicModifyTime =new IPTimeStamp().getTimeStamp();
            if (checkParam(topicId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
            if (checkParam( boardId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardId传入的参数为空", null );
            }
            if (checkParam( topicTitle )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicTitle传入的参数为空", null );
            }
            if (checkParam( topicContent )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicContent传入的参数为空", null );
            }
            if (checkParam( userId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "userId传入的参数为空", null );
            }
            topic.setTopicId( Integer.parseInt( topicId ) );
            topic.setBoardId( Integer.parseInt( boardId ) );
            topic.setTopicTitle( topicTitle );
            topic.setTopicContent( topicContent );
            topic.setUserId( user.getUserId() );
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        int result = 0;
        try {
            result = topicDAO.updateTopic( topic );
            if (result == 1)
                return new CommonUtil().constructResponse( EnumUtil.OK, "修改成功", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.FAILURE, "修改失败", null );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }

    }

    @Override
    public JSONObject deleteTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        String topicId = null;
        try {
           topicId = request.getParameter( "topicId" );
            if (checkParam( topicId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        int result = 0;
        try {
            result = topicDAO.deleteTopic( Integer.parseInt( topicId ) );
            if (result == 1)
                return new CommonUtil().constructResponse( EnumUtil.OK, "删除成功", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.FAILURE, "删除失败", null );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject getTopicDetailed(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        String boardId = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            boardId = request.getParameter( "boardId" );
            if (checkParam( boardId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardId传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        List <Map <String, String>> result = new ArrayList <Map <String, String>>();
        try {
            result = topicDAO.getTopicDetailedById( Integer.parseInt( boardId ) );
            if (result == null)
                return new CommonUtil().constructResponse( EnumUtil.NO_DATA, "数据为空", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功", result );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject getAllTopicDetailed(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        List <Map <String, String>> result = new ArrayList <Map <String, String>>();
        try {
            result = topicDAO.getAllTopicDetailed();
            if (result == null)
                return new CommonUtil().constructResponse( EnumUtil.NO_DATA, "数据为空", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功", result );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject getTopicById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        String topicId = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            topicId = request.getParameter( "topicId" );
            if (checkParam( topicId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        List <Map <String, String>> result = new ArrayList <Map <String, String>>();
        try {
            result = topicDAO.getTopicById( Integer.parseInt( topicId ) );
            if (result == null)
                return new CommonUtil().constructResponse( EnumUtil.NO_DATA, "数据为空", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功", result );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject getNewestTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        List <Map <String, String>> result = new ArrayList <Map <String, String>>();
        try {
            result = topicDAO.getNewestTopic();
            if (result == null)
                return new CommonUtil().constructResponse( EnumUtil.NO_DATA, "数据为空", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功", result );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject collectTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        String topicId = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            topicId = request.getParameter( "topicId" );
            if (checkParam( topicId )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        int result = 0;
        try {
            result = topicDAO.collectTopic( Integer.parseInt( topicId ), user.getUserId() );
            if (result == 1)
                return new CommonUtil().constructResponse( EnumUtil.OK, "收藏成功", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.FAILURE, "收藏失败", null );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }

    @Override
    public JSONObject seekTopic(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute( "user" );
        String keyWord = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            keyWord = request.getParameter( "keyWord" );
            if (checkParam( keyWord )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "keyWord传入的参数为空", null );
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
        List <Map <String, String>> result = new ArrayList <Map <String, String>>();
        try {
            result = topicDAO.seekTopic( keyWord );
            if (result == null)
                return new CommonUtil().constructResponse( EnumUtil.NO_DATA, "数据为空", null );
            else
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功", result );
        } catch (SQLException e) {
            return new CommonUtil().constructResponse( EnumUtil.SQL_FAILURE, "数据库处理异常", null );
        }
    }
}
