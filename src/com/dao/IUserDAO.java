package com.dao;

//定义DAO的操作标准

import com.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IUserDAO {

    /**
     * 用户的注册
     * @param  userName:用户名
     * @param   userPassWord：密码
     * @return  注册成功：1 注册失败：0
     * @throws Exception 有异常交给被调用处处理
     */
    public  int  userRegister(String userName, String userPassWord) throws SQLException;

    /**
     * 检查用户名是否已经被注册了
     * @param userName
     * @return
     */

    public int  userRegisterCheckName(String userName) throws  SQLException;

    /**
     * 用户的登录
     * @param  userName
     * @param  userPassWord
     * @return 登录成功：1 登录失败：0
     * @throws Exception 有异常交给被调用处处理
     */
     public User userLogin(String userName, String userPassWord)throws  SQLException;

    /**
     * 根据用户的id号码来返回用户的账号信息
     * @param userId 查询关键字
     * @return 返回对应的全部数据
     * @throws  Exception 有异常交给被调用处处理
     */

    public List<Map<String,String>>  userDetailedById(int userId) throws SQLException;

    /**
     * 修改用户的账号信息
     * @param userId
     * @param userName
     * @param  userPassWord
     * @return 修改成功：1  修改失败：0
     * @throws  Exception 有异常交给被调用处处理
     */
    public int updateUserDetailed(int userId, String userName, String userPassWord) throws  SQLException;

    /**
     * 添加用户个人信息
     * @param user
     * @return
     * @throws SQLException
     */
    public int addPersonalDetailed(User user) throws SQLException;

    /**
     * 更新用户的个人信息
     * @param user
     * @return
     * @throws SQLException
     */
    public int updatePersonalDetailed(User user) throws SQLException;

    /**
     *删除用户的个人信息
     * @param userId
     * @return
     * @throws SQLException
     */
    public int deletePersonDetailed(int userId) throws SQLException;

    /**
     * 查看用户的个人信息
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> getPersonalDetailed() throws SQLException;

}
