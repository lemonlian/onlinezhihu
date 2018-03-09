package com.test;

import com.dao.impl.UserDAOImpl;
import com.model.User;
import com.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试用户的注册功能
 */
public class TestUserDAO {
    public static void main(String[] args) throws Exception {
          User user = new User();
          user.setUserId( 1 );
          user.setUserName( "admin" );
          user.setUserPassWord( "admin" );
      System.out.println("注册："+new UserDAOImpl().userRegister( user.getUserName(),user.getUserPassWord() ));
    // System.out.println("登录："+  new UserDAOImpl().userLogin( user.getUserName(),user.getUserPassWord() ));
//        System.out.println("是否存在："+new UserDAOImpl().userRegisterCheckName( user.getUserName() ));
//        System.out.println("更新："+new UserDAOImpl().updateUserDetailed( 1,"admin","dad" ));
//        List <Map <String, String>> list = new ArrayList <>(  );
//        System.out.println("获得数据:"+new UserDAOImpl().userDetailedById( 1 ));
          user.setUserSex( "女" );
          user.setUserAddress( "重庆市南岸区崇文路二号" );
          user.setUserEducation( "本科" );
          user.setUserEmail( "1944670179@qq.com" );
           //System.out.println("编辑个人信息："+new UserDAOImpl().addPersonalDetailed( user ));
           //  System.out.println("重编辑个人的信息"+new UserDAOImpl().updatePersonalDetailed( user ));
           //System.out.println("得到个人的信息："+new UserDAOImpl().getPersonalDetailed());
           //System.out.println("删除个人的信息："+new UserDAOImpl().deletePersonDetailed( user.getUserId() ));

    }
}
