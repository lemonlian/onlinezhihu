package com.test;

import com.dao.IReplyDAO;
import com.dao.impl.ReplyDAOImpl;
import com.model.Reply;
import com.util.IPTimeStamp;

public class TestReplyDAO {
    public static void main(String[] args) throws Exception {
        Reply reply = new Reply();
        IReplyDAO replyDAO = new ReplyDAOImpl();
        reply.setParentId( 1 );
        reply.setTopicId( 1 );
        reply.setUserId( 11);
        reply.setReplyModifyTime( new IPTimeStamp().getTimeStamp() );
        reply.setReplyPublishTime( new IPTimeStamp().getTimeStamp() );
        reply.setReplyContent( "食堂" );
        reply.setReplyId( 6);
       System.out.println("回复话题："+replyDAO.createReply( reply ));
        // System.out.println("更新回复"+replyDAO.updateReply( reply ));
        //System.out.println("删除回复："+replyDAO.deleteReply( reply.getReplyId() ));
        System.out.println("得到回复的内容"+replyDAO.getReplyDetailedById( reply.getReplyId() ));
    }

}
