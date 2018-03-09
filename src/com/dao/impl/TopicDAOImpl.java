package com.dao.impl;

import com.dao.ITopicDAO;
import com.dbc.DatabaseConnectionByMySQL;
import com.model.Topic;
import com.util.IPTimeStamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicDAOImpl implements ITopicDAO {
    private Connection con = null;//数据库连接对象
    private PreparedStatement psmt = null;//数据库操作对象
    private ResultSet rs = null;//数据库结果集

    public TopicDAOImpl() throws Exception {
        this.con = new DatabaseConnectionByMySQL().getConnection();//通过构造方法得到数据库的连接
    }

    @Override
    public int addTopic(Topic topic,int userId) throws SQLException {
        int flag = 0;
        String addTopicSql = "insert into topic(board_id,topic_title,topic_content,user_id,topic_publishtime,topic_modifytime) values(?,?,?,?,?,?)";
        try {
            psmt = con.prepareStatement( addTopicSql );
            psmt.setInt( 1, topic.getBoardId() );
            psmt.setString( 2, topic.getTopicTitle() );
            psmt.setString( 3, topic.getTopicContent() );
            psmt.setInt( 4, userId );
            psmt.setString( 5, new IPTimeStamp().getTimeStamp() );
            psmt.setString( 6, new IPTimeStamp().getTimeStamp() );
            int result = psmt.executeUpdate();
            if (result > 0) {
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "addTopic预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public int updateTopic(Topic topic) throws SQLException {
        int flag = 0;
        String updateTopicSql = "update topic set topic_title=?,topic_content=?,board_id=?,topic_modifytime=? where topic_id=?";
        try {
            psmt = con.prepareStatement( updateTopicSql );
            psmt.setString( 1, topic.getTopicTitle() );
            psmt.setString( 2, topic.getTopicContent() );
            psmt.setInt( 3, topic.getBoardId() );
            psmt.setString( 4, new IPTimeStamp().getTimeStamp() );
            psmt.setInt( 5,topic.getTopicId() );
            int result = psmt.executeUpdate();
            if (result > 0) {
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "updateTopic预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public int deleteTopic(int topicId) throws SQLException {
        int flag = 0;
        String deleteTopicSql = "delete from topic where topic_id = ?  ";
        try {
            psmt = con.prepareStatement( deleteTopicSql );
            psmt.setInt( 1, topicId );
            int result = psmt.executeUpdate();
            if (result > 0) {
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "deleteTopic预处理出现了异常！" );
            throw e;
        }
    }

    @Override
    public List <Map <String, String>> getTopicDetailedById(int boardId) throws SQLException {
        List <Map <String, String>> list = new ArrayList <>(  );
        Map <String, String> map = null;
        String getTopicDetailedByIdSql = "select * from topic where board_id = ?";
        try {
            psmt = con.prepareStatement( getTopicDetailedByIdSql );
            psmt.setInt( 1,boardId );
            rs = psmt.executeQuery();
            while (rs.next()) {
                map = new HashMap <>();
                map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "topicTitle", rs.getString( "topic_title" ) );
                map.put( "topicContent", rs.getString( "topic_content" ) );
                map.put( "userId", rs.getString( "user_id" ) );
                map.put( "topicPublishtime", rs.getString( "topic_publishtime" ) );
                map.put( "topicModifytime", rs.getString( "topic_modifytime" ) );
                map.put( "boardId", String.valueOf( boardId ) );
                list.add( map );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "getTopicDetailedById预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public List <Map <String, String>> getAllTopicDetailed() throws SQLException {
        List <Map <String, String>> list = new ArrayList <>(  );
        Map <String, String> map = null;
        String getAllTopicDetailedSql = "select * from topic ";
        try {
            psmt = con.prepareStatement( getAllTopicDetailedSql );
            rs = psmt.executeQuery();
            while (rs.next()) {
                map = new HashMap <>();
                map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "topicTitle", rs.getString( "topic_title" ) );
                map.put( "topicContent", rs.getString( "topic_content" ) );
                map.put( "userId", rs.getString( "user_id" ) );
                map.put( "topicPublishTime", rs.getString( "topic_publishtime" ) );
                map.put( "topicModifytTime", rs.getString( "topic_modifytime" ) );
                list.add( map );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "getAllTopicDetailed预处理出现异常！" );
            throw e;
        }
    }



    @Override
    public List <Map <String, String>> getTopicById(int topicId) throws SQLException {
        List <Map <String, String>> list = new ArrayList <>(  );
        Map <String, String> map = null;
        String getTopicDetailedByIdSql = "select * from topic where topic_id = ?";
        try {
            psmt = con.prepareStatement( getTopicDetailedByIdSql );
            psmt.setInt( 1,topicId);
            rs = psmt.executeQuery();
            while (rs.next()) {
                map = new HashMap <>();
                map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "topicTitle", rs.getString( "topic_title" ) );
                map.put( "topicContent", rs.getString( "topic_content" ) );
                map.put( "userId", rs.getString( "user_id" ) );
                map.put( "topicPublishtime", rs.getString( "topic_publishtime" ) );
                map.put( "topicModifytime", rs.getString( "topic_modifytime" ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                list.add( map );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "getTopicById预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public List <Map <String, String>> getNewestTopic() throws SQLException {
        List <Map <String, String>> list = new ArrayList <>(  );
        Map <String, String> map = null;
        String getNewestTopicSql = "SELECT * FROM topic ORDER BY topic_id  DESC LIMIT 5";
        try {
            psmt = con.prepareStatement( getNewestTopicSql );
            rs = psmt.executeQuery();
            while (rs.next()) {
                map = new HashMap <>();
                map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "topicTitle", rs.getString( "topic_title" ) );
                map.put( "topicContent", rs.getString( "topic_content" ) );
                map.put( "userId", rs.getString( "user_id" ) );
                map.put( "topicPublishtime", rs.getString( "topic_publishtime" ) );
                map.put( "topicModifytime", rs.getString( "topic_modifytime" ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                list.add( map );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "getNewestTopic预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public int collectTopic(int topicId, int userId) throws SQLException {
        int flag = 0;
        String  collectTopicSql = "insert into collectedTopic(topic_id,user_id) values(?,?)";
        try {
            psmt = con.prepareStatement(  collectTopicSql );
            psmt.setInt( 1, topicId );
            psmt.setInt( 2, userId );
            int result = psmt.executeUpdate();
            if (result > 0) {
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "collectTopic预处理出现异常！" );
            throw e;
        }
    }

    @Override
    public List <Map <String, String>> seekTopic(String keyWord) throws SQLException {
        List <Map <String, String>> list = new ArrayList <>();
        Map <String, String> map = null;
        StringBuffer seekTopicSql = new StringBuffer( "SELECT * FROM topic  WHERE 1 = 1" );
        if(!(keyWord == null || (keyWord = keyWord.trim()).length() == 0)){
           seekTopicSql.append(" and topic_content like'%" +keyWord + "%'");
        }
        String seekTopicSql1 = seekTopicSql.toString();
       // System.out.println(seekTopicSql1);
        try {
            psmt = con.prepareStatement( seekTopicSql1 );
            rs = psmt.executeQuery();
            while (rs.next()) {
                map = new HashMap <>();
                map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                map.put( "topicTitle", rs.getString( "topic_title" ) );
                map.put( "topicContent", rs.getString( "topic_content" ) );
                map.put( "userId", rs.getString( "user_id" ) );
                map.put( "topicPublishtime", rs.getString( "topic_publishtime" ) );
                map.put( "topicModifytime", rs.getString( "topic_modifytime" ) );
                map.put( "boardId", String.valueOf( rs.getInt( "board_id" ) ) );
                list.add( map );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "getNewestTopic预处理出现异常！" );
            throw e;
        }
    }
}
