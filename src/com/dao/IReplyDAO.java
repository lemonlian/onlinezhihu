package com.dao;


import com.model.Reply;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IReplyDAO {
    /**
     * 创建回复
     * @param reply
     * @return
     * @throws SQLException
     */
    public int createReply(Reply reply) throws SQLException;

    /**
     * 更新回复
     * @param
     * @return
     * @throws SQLException
     */
    public int updateReply(Reply reply) throws SQLException;

    /**
     * 删除回复
     * * @param replyId
     * @return
     * @throws SQLException
     */
    public int deleteReply(int replyId) throws SQLException;

    /**
     * 获得回复的具体信息
     * @param topicId
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getReplyDetailedById(int topicId) throws SQLException;
}
