package com.dao.impl;

import com.dao.IUserDAO;
import com.dbc.DatabaseConnectionByMySQL;
import com.model.User;
import com.util.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements IUserDAO {
    private Connection conn = null;//数据库连接对象
    private PreparedStatement pstmt = null;//数据库操作对象
    private ResultSet rs = null;//数据库结果集
    public UserDAOImpl() throws Exception {
        this.conn  = new DatabaseConnectionByMySQL().getConnection();//通过构造方法得到数据库的连接
    }

    @Override
    public int userRegister(String userName, String userPassWord) throws SQLException {
        int flag = 0;//定义标志位
        String userRegisterSql= "insert into user(user_name,user_passWord) values (?,?)";
        this.pstmt = this.conn.prepareStatement( userRegisterSql );//实例化PreparedStatement对象
        this.pstmt.setString( 1,userName );
        this.pstmt.setString( 2, MD5Util.calc(userPassWord  ));
        if (this.pstmt.executeUpdate()>0){//更新记录的行大于0
            flag = 1;//修改标志位
        }
        this.pstmt.close();//关闭PreparedStatement操作
        return flag;
    }

    @Override
    public int userRegisterCheckName(String userName) throws SQLException {
        int flag = 0;//定义标志位
        String userRegisterCheckNameSql= "select * from user where user_name=?";
        this.pstmt = this.conn.prepareStatement( userRegisterCheckNameSql );//实例化PreparedStatement对象
        this.pstmt.setString( 1,userName );
        if (this.pstmt.executeQuery().next()){//查询到有内容
            flag = 1;//修改标志位
        }
        this.pstmt.close();//关闭PreparedStatement操作
        return flag;
    }

    @Override
    public User userLogin(String userName, String userPassWord) throws SQLException {
        User user  = null;//定义标志位
        String userLoginSql= "select * from user where user_name=? and user_passWord = ?";
        this.pstmt = this.conn.prepareStatement( userLoginSql );//实例化PreparedStatement对象
        this.pstmt.setString( 1,userName );
        this.pstmt.setString( 2,MD5Util.calc(userPassWord  ) );
        rs = this.pstmt.executeQuery();//初始化结果集
        if (rs.next()){//查询到有内容
           user = new User();
           user.setUserId( rs.getInt( "user_id" ) );
           user.setUserName(rs.getString( "user_name" ));
           user.setUserPassWord( rs.getString( "user_passWord" ) );
        }
        this.pstmt.close();//关闭PreparedStatement操作
        this.rs.close();//关闭ResultSet对象
         return user;
    }

    @Override
    public List<Map<String, String>> userDetailedById(int userId) throws SQLException {
        List<Map<String ,String>> result = null;//定义标志位
        Map <String, String> userMap = null;
        String userDetailedByIdSql = "select * from user where user_id = ?";
        pstmt = conn.prepareStatement( userDetailedByIdSql );
        pstmt.setInt( 1,userId );
        rs = pstmt.executeQuery();
        if (rs.next()){
            result = new ArrayList <>(  );
            userMap = new HashMap <String,String>( );
            userMap.put( "user_id", String.valueOf( rs.getInt( "user_id" ) ) );
            userMap.put( "user_name",rs.getString( "user_name" ) );
            userMap.put( "user_passWord",null);
            userMap.put( "user_status", String.valueOf( rs.getInt( "user_status" ) ) );
            result.add( userMap );
        }
        return result;
    }

    @Override
    public int updateUserDetailed(int userId, String userName, String userPassWord) throws SQLException {
        int flag = 0;//定义标志位
        String updateUserDetailedSql= "update user set user_name = ?, user_passWord = ?  where user_id = ?";
        this.pstmt = this.conn.prepareStatement( updateUserDetailedSql );//实例化PreparedStatement对象
        this.pstmt.setString( 1,userName );
        this.pstmt.setString( 2,MD5Util.calc(userPassWord  ));
        this.pstmt.setInt( 3,userId );
        if (this.pstmt.executeUpdate()>0){//更新记录的行大于0
            flag = 1;//修改标志位
        }
        this.pstmt.close();//关闭PreparedStatement操作
        return flag;
    }

    @Override
    public int addPersonalDetailed(User user) throws SQLException {
        int flag = 0;
        String addUserDetailedSql = "INSERT INTO person values(?,?,?,?,?,?,?,?,?,?)";
        try {
            this.pstmt = this.conn.prepareStatement( addUserDetailedSql );
            this.pstmt.setInt( 1,user.getUserId() );
            this.pstmt.setString( 2,user.getUserSex() );
            this.pstmt.setString( 3,user.getUserIntroduction() );
            this.pstmt.setString( 4,user.getUserAddress() );
            this.pstmt.setString( 5,user.getUserIndustry() );
            this.pstmt.setString( 6,user.getUserProfessional() );
            this.pstmt.setString( 7,user.getUserResume() );
            this.pstmt.setString( 8,user.getUserEducation() );
            this.pstmt.setString( 9,user.getUserImg() );
            this.pstmt.setString( 10,user.getUserEmail() );
            if (pstmt.executeUpdate()>0){
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("addPersonalDetailed预处理异常");
            throw e;

        }
    }

    @Override
    public int updatePersonalDetailed(User user) throws SQLException {
        int flag = 0;
        String updateUserDetailedSql = "update person set person_sex=?,person_introduction=?," +
                "person_address=?, person_industry=?, person_ professional=?, " +
                "person_resume=?, person_Education=? ," +
                " person_email=? where user_id= ? ";
        try {
            this.pstmt = this.conn.prepareStatement( updateUserDetailedSql );
            this.pstmt.setString( 1,user.getUserSex() );
            this.pstmt.setString( 2,user.getUserIntroduction() );
            this.pstmt.setString( 3,user.getUserAddress() );
            this.pstmt.setString( 4,user.getUserIndustry() );
            this.pstmt.setString( 5,user.getUserProfessional() );
            this.pstmt.setString( 6,user.getUserResume() );
            this.pstmt.setString( 7,user.getUserEducation() );
            this.pstmt.setString( 8,user.getUserEmail() );
            this.pstmt.setInt( 9,user.getUserId() );
            if (this.pstmt.executeUpdate()>0){
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("updatePersonalDetailed预处理异常");
            throw e;

        }
    }

    @Override
    public int deletePersonDetailed(int userId) throws SQLException {
        int flag = 0;
        String deletePersonDetailedSql = "delete from person where user_id = ?";
        try {
            this.pstmt = this.conn.prepareStatement( deletePersonDetailedSql );
            this.pstmt.setInt( 1,userId );
            if (pstmt.executeUpdate()>0){
                flag = 1;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("deletePersonDetailed预处理异常");
            throw e;

        }
    }

    @Override
    public List <Map <String, String>> getPersonalDetailed() throws SQLException {
        List<Map<String, String>> list = new ArrayList<>();
       String  getPersonalDetailedSql = "SELECT * from person";
        try {
            this.pstmt = this.conn.prepareStatement( getPersonalDetailedSql );
            this.rs = this.pstmt.executeQuery();
            while(rs.next()){
                Map<String, String> map = new HashMap<>();
                map.put("userSex", rs.getString( "person_sex" ));
                map.put("userIntroduction", rs.getString("person_introduction"));
                map.put("userAddress", rs.getString("person_address"));
                map.put("userIndustry", rs.getString("person_industry"));
                map.put("userProfessional", rs.getString("person_ professional"));
                map.put("userResume", rs.getString("person_resume"));
                map.put("userEducation", rs.getString("person_Education"));
                map.put("userImg", rs.getString("person_img"));
                map.put("userEmail", rs.getString("person_email"));
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getPersonalDetailed预处理异常");
            throw e;
        }
    }


}
