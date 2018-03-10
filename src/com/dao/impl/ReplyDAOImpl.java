package com.dao.impl;

import com.dao.IReplyDAO;
import com.dbc.DatabaseConnectionByMySQL;
import com.model.Reply;
import com.util.IPTimeStamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplyDAOImpl implements IReplyDAO{
    private Connection con = null;//数据库连接对象
    private PreparedStatement psmt = null;//数据库操作对象
    private ResultSet rs = null;//数据库结果集
    public ReplyDAOImpl() throws Exception {
        this.con = new DatabaseConnectionByMySQL().getConnection();//通过构造方法得到数据库的连接
    }
    @Override
    public int createReply(Reply reply) throws SQLException {
        int flag = 0;
        String  createReplySql = "insert into reply(reply_content,reply_publishtime,reply_modifytime,user_id,topic_id,parent_id) values(?,?,?,?,?,?)";
        try{
       psmt = con.prepareStatement( createReplySql );
       psmt.setString( 1,reply.getReplyContent() );
       psmt.setString(2, new IPTimeStamp().getTimeStamp() );
       psmt.setString( 3,new IPTimeStamp().getTimeStamp() );
       psmt.setInt(4,reply.getUserId());
       psmt.setInt( 5,reply.getTopicId() );
       psmt.setInt( 6,reply.getParentId());
       int result = psmt.executeUpdate();
       if (result>0){
           flag = 1;
       }
       return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(" createReply预处理异常！");
            throw  e;
        }
    }

    @Override
    public int updateReply(Reply reply) throws SQLException {
        int flag = 0;
        String updateReplySql = "update reply set reply_content=?,reply_modifytime=? where reply_id=?";
        try{
            psmt = con.prepareStatement( updateReplySql );
            psmt.setString( 1,reply.getReplyContent() );
            psmt.setString( 2, new IPTimeStamp().getTimeStamp() );
            psmt.setInt( 3,reply.getReplyId() );
            int result = psmt.executeUpdate();
            if (result>0){
                flag = 1;
            }
            return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("updateReply预处理异常！");
            throw  e;
        }
    }

    @Override
    public int deleteReply(int replyId) throws SQLException {
        int flag = 0;
        String updateReplySql = "delete from reply where reply_id = ? ";
        try{
            psmt = con.prepareStatement( updateReplySql );
            psmt.setInt( 1,replyId );
            int result = psmt.executeUpdate();
            if (result>0){
                flag = 1;
            }
            return flag;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("deleteReply预处理异常！");
            throw  e;
        }
    }

    @Override
    public List<Map<String,String>> getReplyDetailedById(int topicId) throws SQLException{
       List<Map<String,String>> list  = new ArrayList <>(  );
       Map<String,String> map = null;
       String  getReplyDetailedByIdSql = "select * from reply where topic_id = ?  ";
        try{
            psmt = con.prepareStatement( getReplyDetailedByIdSql);
            psmt.setInt( 1,topicId );
           rs = psmt.executeQuery();
           while (rs.next()){
               map = new HashMap <>(  );
               map.put( "replyId", String.valueOf( rs.getInt( "reply_id" ) ) );
               map.put( "replyContent",rs.getString( "reply_content" ) );
               map.put( "replyPublishTime" ,rs.getString( "reply_publishtime" ));
               map.put( "replyModifyTime",rs.getString( "reply_modifytime" ) );
               map.put( "userId", String.valueOf( rs.getInt( "user_id" ) ) );
               map.put( "topicId", String.valueOf( rs.getInt( "topic_id" ) ) );
               map.put( "parentId", String.valueOf( rs.getInt( "parent_id" ) ) );
               list.add( map );
           }
            return  list;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(" getReplyDetailedById预处理异常！");
            throw  e;
        }
    }

}
