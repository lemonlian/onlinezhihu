package com.dao;

import com.model.Board;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBoardDAO {
    /**
     * 添加版面信息
     * @param board
     * @return
     * @throws SQLException
     */
    public int addBoard(Board board) throws SQLException;

    /**
     * 修改版面的信息
     * @param board
     * @return
     * @throws SQLException
     */
    public  int updateBoard(Board board) throws  SQLException;

    /**
     * 删除版面信息
     * @param boardId
     * @return
     * @throws SQLException
     */
    public  int deleteBoard(int boardId) throws  SQLException;

    /**
     * 获得版面的具体信息
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getBoardDetailed() throws SQLException;
}
