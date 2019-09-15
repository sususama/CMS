package edu.xalead.cms.tools;

import edu.xalead.cms.entity.Article;
import edu.xalead.cms.entity.Channel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection conn=null;
    private static PreparedStatement prst=null;
    private static ResultSet rs=null;
    private static ResultSetMetaData mata=null;
    /*
    * 获取数据库链接的方法
    * */
    private static String driverClass="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/cms";
    private static String username="root";
    private static String password="123456";

    /*
    * 获取数据库链接的方法
    * */
    public static Connection getConn(){
        try {
            Class.forName(driverClass);
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /*
    * 关闭数据库链接,语句对象，结果集对象等的方法
    * */
    public  static void close(AutoCloseable t){
            try {
                if (t!=null) {
                t.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    /*
    * 把结果集转换为频道离线集
    *
    * */
    public static List<Channel> resultSet2ChannelsList(ResultSet rs) {
        List<Channel> list=null;
        try {
            while (rs.next()){
                //防止结果集没有实例化
                if (list==null){
                    list=new ArrayList<>();
                }
                //把rs中当前记录封装到Channel对象
                Channel c=new Channel();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setDescription(rs.getNString("description"));
                //把当前对象放入频道列表
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Article> resultSet2ArticleList(ResultSet rs) {
        List<Article> list=null;
        try {
            while (rs.next()){
                //防止结果集没有实例化
                if (list==null){
                    list=new ArrayList<>();
                }
                //把rs中当前记录封装到Channel对象
                Article a=new Article();
                a.setAid(rs.getInt("aid"));
                a.setAuthor(rs.getString("author"));
                a.setCid(rs.getInt("cid"));
                a.setContent(rs.getString("content"));
                a.setSource(rs.getString("source"));
                a.setTitle(rs.getString("title"));
                a.setCreateTime(rs.getDate("createTime"));
                //把当前对象放入频道列表
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
