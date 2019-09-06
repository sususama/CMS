package edu.xalead.cms.dao;

import edu.xalead.cms.entity.User;
import edu.xalead.cms.tools.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User findUserByUserName(String user, Connection coon) {
        User u=null;
        String sql="select * from t_user where username=?";
        PreparedStatement prst=null;
        ResultSet rs;
        try {
            prst=coon.prepareStatement(sql);
            prst.setString(1,user);
            //只有executeQuery返回结果
            rs=prst.executeQuery();//得到查询结果
            if (rs.next()) {
                    u = new User();
                    u.setUid(rs.getInt("uid"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getNString("password"));
                    u.setCreateTime(rs.getDate("createTime"));
                    u.setUname(rs.getNString("uname"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
        return u;
    }
}
