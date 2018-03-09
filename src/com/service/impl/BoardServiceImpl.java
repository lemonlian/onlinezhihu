package com.service.impl;

import com.dao.IBoardDAO;
import com.dao.impl.BoardDAOImpl;
import com.model.Board;
import com.model.User;
import com.service.BoardService;
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

public class BoardServiceImpl implements BoardService {
    IBoardDAO boardDAO = null;
    public BoardServiceImpl() throws Exception {
        this.boardDAO = new BoardDAOImpl();
    }

    public boolean checkParam(String param){
        if(param == null || "".equals(param))
            return true;
        return false;
    }

    @Override
    public JSONObject addBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        Board board = new Board();
        String boardName = null;
        String boardCategory = null;
        String boardDescription = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
           boardName = req.getParameter( "boardName" );
           boardCategory = req.getParameter( "boardCategory" );
           boardDescription = req.getParameter( " boardDescription" );
            if (checkParam( boardName )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardName传入的参数为空", null );
            }
            if (checkParam( boardCategory )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " boardCategory传入的参数为空", null );
            }
            if (checkParam( boardDescription )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " boardDescription 传入的参数为空", null );
            }
            board.setBoardName( boardName );
            board.setBoardCategory(boardCategory);
            board.setBoardDescription( boardDescription );
           int result = 0;
            try {
                result = boardDAO.addBoard( board );
                if(result == 1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "添加成功", null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "添加失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject updateBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        Board board = new Board();
        String boardName = null;
        String boardId = null;
        String boardCategory = null;
        String boardDescription = null;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
            boardId = req.getParameter( "boardId" );
            boardName = req.getParameter( "boardName" );
            boardCategory = req.getParameter( "boardCategory" );
            boardDescription = req.getParameter( " boardDescription" );
            if (checkParam(boardId)) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardId传入的参数为空", null );
            }
            if (checkParam( boardName )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "boardName传入的参数为空", null );
            }
            if (checkParam( boardCategory )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " boardCategory传入的参数为空", null );
            }
            board.setBoardId( Integer.parseInt( boardId )    );
            board.setBoardName( boardName );
            board.setBoardCategory(boardCategory);
            board.setBoardDescription( boardDescription );
            int result = 0;
            try {
                result = boardDAO.updateBoard( board );
                if(result == 1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "修改成功", null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "修改失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject deleteBoard(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute( "user" );
        int boardId = 0;
        if (user == null)
            return new CommonUtil().constructResponse( EnumUtil.NOT_LOGIN, "用户未登录", null );
        try {
         boardId = Integer.parseInt( req.getParameter( "boardId" ) );
            if (checkParam( String.valueOf( boardId ) )) {
                return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, " boardDescription 传入的参数为空", null );
            }
            int result = 0;
            try {
                result = boardDAO.deleteBoard( boardId );
                if(result == 1)
                    return new CommonUtil().constructResponse(EnumUtil.OK, "删除成功", null);
                else
                    return new CommonUtil().constructResponse(EnumUtil.FAILURE, "删除失败", null);
            }catch (SQLException e) {
                return new CommonUtil().constructResponse(EnumUtil.SQL_FAILURE, "数据库处理异常", null);
            }
        } catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }

    @Override
    public JSONObject getBoardDetailed(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        try {
            req.setCharacterEncoding( "utf-8" );
            resp.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        List<Map<String,String>> result = new ArrayList <Map<String, String>>(  );
        try {
            result = boardDAO.getBoardDetailed();
            if (result==null){
                return new CommonUtil().constructResponse(EnumUtil.NO_DATA, "数据为空", null);
            }
			else {
                return new CommonUtil().constructResponse( EnumUtil.OK, "获取信息成功",result );
            }
        }catch (Exception e) {
            return new CommonUtil().constructResponse( EnumUtil.PARAM_ERROR, "无参数匹配或参数匹配异常", null );
        }
    }
}
