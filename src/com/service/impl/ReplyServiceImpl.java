package com.service.impl;

import com.dao.IReplyDAO;
import com.dao.impl.ReplyDAOImpl;
import com.model.Reply;
import com.model.User;
import com.service.ReplyService;
import com.util.CommonUtil;
import com.util.EnumUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReplyServiceImpl implements ReplyService {
    IReplyDAO replyDAO = null;
    public ReplyServiceImpl() throws Exception {
        this.replyDAO = new ReplyDAOImpl();
    }

    public boolean checkParam(String param){
        if(param == null || "".equals(param))
            return true;
        return false;
    }

    @Override
    public JSONObject createReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            String replyContent = req.getParameter( "replyContent" );
            String userId = req.getParameter( "userId" );
            String topicId = req.getParameter( "topicId" );
            String parentId = req.getParameter( "parentId" );
            if (checkParam(replyContent)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "replyContent传入的参数为空", null );
            }
            if (checkParam( userId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " userId传入的参数为空", null );
            }
            if (checkParam(topicId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
            if (checkParam(parentId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "parentId传入的参数为空", null );
            }
            Reply reply = new Reply();
            int result = 0;
            reply.setReplyContent( replyContent );
            reply.setUserId( Integer.parseInt( userId ) );
            reply.setTopicId( Integer.parseInt( topicId ) );
            reply.setParentId( Integer.parseInt( parentId ) );
            try {
                result = replyDAO.createReply( reply );
                if(result == 1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "回复成功", null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "回复失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }

        }catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject updateReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            String replyId = req.getParameter( "replyId" );
            String replyContent = req.getParameter( "replyContent" );
            String userId = req.getParameter( "userId" );
            String topicId = req.getParameter( "topicId" );
            String parentId = req.getParameter( "parentId" );
            if (checkParam(replyId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "replyId传入的参数为空", null );
            }
            if (checkParam(replyContent)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "replyContent传入的参数为空", null );
            }
            if (checkParam( userId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " userId传入的参数为空", null );
            }
            if (checkParam(topicId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
            if (checkParam(parentId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "parentId传入的参数为空", null );
            }
            Reply reply = new Reply();
            int result = 0;
            reply.setReplyId( Integer.parseInt( replyId ) );
            reply.setReplyContent( replyContent );
            reply.setUserId( Integer.parseInt( userId ) );
            reply.setTopicId( Integer.parseInt( topicId ) );
            reply.setParentId( Integer.parseInt( parentId ) );
            try {
                result = replyDAO.updateReply( reply );
                if(result == 1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "重编辑成功", null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "重编辑失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }

        }catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject deleteReply(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        String replyId = null;
        try {
           replyId = req.getParameter( "replyId" );
            if (checkParam(replyId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "replyId传入的参数为空", null );
            }
            int result = 0;
            try {
                result = replyDAO.deleteReply( Integer.parseInt( replyId ) );
                if(result==1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "删除回复成功",null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "删除回复失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }
        }catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject getReplyDetailedById(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            String topicId = req.getParameter( "topicId" );
            if (checkParam(topicId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "topicId传入的参数为空", null );
            }
            Reply reply = new Reply();
            List<Map<String ,String>> result = new ArrayList <>(  );
            reply.setReplyId( Integer.parseInt( topicId ) );
            try {
                result = replyDAO.getReplyDetailedById(Integer.parseInt(topicId ));
                if(result !=null)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "获得数据成功", result);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "获得数据失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }

        }catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }
}
