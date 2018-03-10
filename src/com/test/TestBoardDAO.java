package com.test;

import com.dao.IBoardDAO;
import com.dao.impl.BoardDAOImpl;
import com.model.Board;

import java.sql.SQLException;

public class TestBoardDAO {
    public static void main(String[] args) throws Exception {
        Board board1 = new Board();
        BoardDAOImpl boardDAO = new BoardDAOImpl();
        board1.setBoardId( 3 );
        board1.setBoardName( "生活" );
        board1.setBoardDescription( "这是一个描述生活的小话题" );
        //System.out.println(boardDAO.addBoard( board1 ));
        //System.out.println(boardDAO.updateBoard( board1 ));
        //System.out.println(boardDAO.deleteBoard( board1.getBoardId() ));
        //System.out.println(boardDAO.getBoardDetailed());
    }
}
