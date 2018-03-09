package com.dao.impl;

import com.dao.IBoardDAO;
import com.dbc.DatabaseConnectionByMySQL;
import com.model.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAOImpl implements IBoardDAO {
    private Connection con = null;//数据库连接对象
    private PreparedStatement psmt = null;//数据库操作对象
    private ResultSet rs = null;//数据库结果集
    public BoardDAOImpl() throws Exception {
        this.con = new DatabaseConnectionByMySQL().getConnection();//通过构造方法得到数据库的连接
    }
    @Override
    public int addBoard(Board board) throws SQLException {
        int flag = 0;
        String addBoardSql = "insert into board(board_name,board_description) values(?,?)";
        try {
            System.out.println(addBoardSql);
            psmt = con.prepareStatement( addBoardSql );
            psmt.setString( 1,board.getBoardName() );
            psmt.setString( 2,board.getBoardDescription());
            int result = psmt.executeUpdate();
            if (result>0){
                flag = 1;
            }
            return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("addBoard预处理异常");
            throw e;
        }
    }

    @Override
    public int updateBoard(Board board) throws SQLException {
        int flag = 0;
        String updateBoardSql = "update board set board_name=?,board_description=? where board_id = ?";
        try {
            psmt = con.prepareStatement( updateBoardSql );
            psmt.setString( 1,board.getBoardName() );
            psmt.setString( 2,board.getBoardDescription());
            psmt.setString( 3, String.valueOf( board.getBoardId() ) );
            int result = psmt.executeUpdate();
            if (result>0){
                flag = 1;
            }
            return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("updateBoard预处理异常");
            throw e;
        }
    }

    @Override
    public int deleteBoard(int boardId) throws SQLException {
        int flag = 0;
        String deleteBoardSql = "delete from board where board_id = ?";
        try {
            psmt = con.prepareStatement( deleteBoardSql );
            psmt.setInt( 1,boardId );
            int result = psmt.executeUpdate();
            if (result>0){
                flag = 1;
            }
            return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("deleteBoard预处理异常");
            throw e;
        }
    }

    @Override
    public List<Map<String, String>> getBoardDetailed() throws SQLException {
        List<Map<String,String>> list = new ArrayList <Map<String, String>>(  );
        Map<String,String> map = null;
        String getBoardDetailedSql = "select * from board ";
        try{
            psmt = con.prepareStatement( getBoardDetailedSql );
            rs = psmt.executeQuery();
            while (rs.next()){
                map = new HashMap <String, String>(  );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "boardName",rs.getString( "board_name" ) );
                map.put( "boardDescription",rs.getString( "board_description" ) );
                list.add( map );
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("getBoardDetailed预处理异常");
            throw e;
        }
    }

}
