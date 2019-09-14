package edu.xalead.cms.service;

import edu.xalead.cms.dao.UserDao;
import edu.xalead.cms.entity.User;
import edu.xalead.cms.tools.DB;

import java.sql.Connection;
import java.sql.SQLException;

//事务的边界
public class LoginService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User chkUser(String user) {

        Connection coon= DB.getConn();
        User u=null;
        try {
            coon.setAutoCommit(false);
            //dao
//            UserDao userDao=new UserDao();
            u=userDao.findUserByUserName(user,coon);
            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
            }
        }
        return u;
    }
}
