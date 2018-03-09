package com.test;

import com.dao.ITopicDAO;
import com.dao.impl.TopicDAOImpl;
import com.model.Topic;
import com.util.IPTimeStamp;

public class TestTopicDAO {
    public static void main(String[] args) throws Exception {
        Topic topic = new Topic();
        ITopicDAO topicDAO = new TopicDAOImpl();
        topic.setTopicId( 3 );
        topic.setTopicTitle( "美食1" );
        topic.setTopicContent( "请问成都最好吃的食物是什么？" );
        topic.setBoardId( 1 );
        topic.setUserId( 1 );
        topic.setTopicModifytime( new IPTimeStamp().getTimeStamp() );
        topic.setTopicPublishtime( new IPTimeStamp().getTimeStamp() );
        String keyWord = "成都";
        // System.out.println("发布话题："+ topicDAO.addTopic( topic ));
        //System.out.println("重新编辑话题"+topicDAO.updateTopic( topic ));
       // System.out.println("删除话题："+topicDAO.deleteTopic( topic.getTopicId()));
        //System.out.println("得到所有的话题"+topicDAO.getAllTopicDetailed());
        //System.out.println("得到某个版块下的话题："+topicDAO.getTopicDetailedById( topic.getBoardId() ));
       // System.out.println("查看某一个话题的具体信息："+topicDAO.getTopicById( 1 ));
       // System.out.println("得到最新的帖子"+topicDAO.getNewestTopic());
       // System.out.println("收藏帖子"+topicDAO.collectTopic( 3,11 ));
        System.out.println("搜索"+topicDAO.seekTopic( keyWord ));
    }
}
