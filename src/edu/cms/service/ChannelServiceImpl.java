package edu.xalead.cms.service;

import edu.xalead.cms.dao.ChannelDao;
import edu.xalead.cms.dao.ChannelDaoImpl;
import edu.xalead.cms.entity.Channel;
import edu.xalead.cms.tools.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChannelServiceImpl extends BaseService implements ChannelService {

    private ChannelDao channelDao;
    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
    public ChannelServiceImpl(){
        super();
    }

    public List<Channel> findAllChannel(){
        List<Channel> list=null;
        Connection coon= DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            list= channelDao.findAllChannel(coon);

            coon.commit();
        } catch (Exception e) {
            try {
                coon.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                DB.close(coon);
                System.out.println();
            }
        }
        return list;
    }
    public void addChannel(Channel channel){
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            channelDao.addChannel(channel,coon);
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
    }

    public void deleteBycid(int cid) {
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            channelDao.deleteChannelByid(cid,coon);
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
    }

    public Channel findChannelByid(int cid) {
        Channel channel=null;
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            channel= channelDao.findChannelByid(cid,coon);
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
        return channel;
    }
    public  void  updateChannel(Channel channel){
        Connection coon=DB.getConn();
        try {
            coon.setAutoCommit(false);
            //dao
//            ChannelDao channelDao =new ChannelDaoImpl();
            channelDao.updateChannel(channel,coon);
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

    }

    public int findChannelCount() {
        int count = 0;
        Connection conn = DB.getConn();
        try {
//            dao
//            ChannelDao channelDao = new ChannelDaoImpl();
            count = channelDao.findChannelcount(conn);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(conn);
        }
        return count;
    }

    public List<Channel> findPageChannel(int offset, int pagesize) {
        List<Channel> channels = null;
        Connection conn = DB.getConn();
        //dao
//        ChannelDao channelDao = new ChannelDaoImpl();
        channels = channelDao.findPageChannel(offset,pagesize,conn);

        return channels;
    }
}

