package com.dbc;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionByMySQL {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static  final String DBURL =  "jdbc:mysql://localhost:3306/onlinezhihu?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private  static final  String DBUSER = "root";
    private  static  final  String DBPASSWORD = "1";
    private Connection conn = null;
    public DatabaseConnectionByMySQL() throws  Exception{//在构造方法中进行数据库的连接
        try{
            Class.forName( DBDRIVER );
            this.conn = DriverManager.getConnection( DBURL,DBUSER,DBPASSWORD );//连接数据库
        }catch (Exception e){
            throw e;
        }
    }


    public Connection getConnection(){//取得数据库的连接
        return this.conn;
    }

    public void close() throws Exception{
        if (this.conn !=null){
            try{
                this.conn.close();//数据库的关闭
            }catch (Exception e){
                throw  e;
            }
        }
    }
}
