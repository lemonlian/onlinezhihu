package com.dao;

import com.model.Topic;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITopicDAO  {
    /**
     * 发布话题
     * @param topic
     * @return
     * @throws SQLException
     */
    public int addTopic(Topic topic,int userId) throws SQLException;

    /**
     * 更新话题
     * @param topic
     * @return
     * @throws SQLException
     */
    public int updateTopic(Topic topic) throws  SQLException;

    /**
     * 删除话题
     * @param topicId,userId
     * @return
     * @throws SQLException
     */
    public int deleteTopic(int topicId) throws SQLException;

    /**
     * 查看一个版面下的所有话题
     * @param boardId
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getTopicDetailedById(int boardId) throws SQLException;

    /**
     * 得到所有话题的信息
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getAllTopicDetailed() throws SQLException;

    /**
     * 根据用户Id查看话题
     * @param topicId
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getTopicById(int topicId) throws SQLException;

    /**
     * 得到最新的帖子
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getNewestTopic() throws SQLException;

    /**
     * 收藏帖子
     * @param topicId
     * @param userId
     * @return
     * @throws SQLException
     */
    public  int collectTopic(int topicId,int userId) throws SQLException;

    /**
     * 通过模糊查询搜索相关的帖子
     * @param keyWord
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> seekTopic(String keyWord) throws SQLException;

}
