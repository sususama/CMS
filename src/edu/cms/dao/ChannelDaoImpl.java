package edu.xalead.cms.dao;

import edu.xalead.cms.entity.Channel;
import edu.xalead.cms.tools.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChannelDaoImpl implements ChannelDao {
        //查询频道列表
    @Override
    public List<Channel> findAllChannel(Connection coon) {
        List<Channel> channels=null;
        String sql="select * from t_channel";
        ResultSet rs=null;
        PreparedStatement prst=null;
        try {
            prst= coon.prepareStatement(sql);
            rs=prst.executeQuery();
            channels=DB.resultSet2ChannelsList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
        return channels;
    }
    @Override
    public void addChannel(Channel channel, Connection conn){
        String sql="insert into t_channel(cname,description) values(?,?)";
        PreparedStatement prst=null;
        try {
            prst=conn.prepareStatement(sql);
            prst.setString(1,channel.getCname());
            prst.setString(2,channel.getDescription());
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
    }

    @Override
    public void deleteChannelByid(int cid, Connection conn) {
        String sql="delete  from t_channel where cid=?";
        PreparedStatement prst=null;
        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,cid);
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
    }

    @Override
    public Channel findChannelByid(int cid, Connection conn) {
        Channel channel=null;
        String sql="select * from t_channel where cid=?";
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,cid);
            rs=prst.executeQuery();
            if (rs.next()){
                channel=new Channel();
                channel.setCid(rs.getInt("cid"));
                channel.setCname(rs.getString("cname"));
                channel.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.close(prst);
        }
        return channel;
    }

    @Override
    public void updateChannel(Channel channel, Connection conn) {
        String sql = "update t_channel set cname = ? , description = ? where cid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement(sql);
            prst.setString(1,channel.getCname());
            prst.setString(2,channel.getDescription());
            prst.setInt(3,channel.getCid());
            prst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
    }

    @Override
    public int findChannelcount(Connection conn) {
        int count = 0;
        String sql = "select count(*) from t_channel";

        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
        return count;
    }

    @Override
    public List<Channel> findPageChannel(int offset, int pagesize, Connection conn) {
        List<Channel> list = null;
        String sql = "select * from t_channel limit ? , ?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1,offset);
            prst.setInt(2,pagesize);
            rs = prst.executeQuery();
            while(rs.next()){
                if(list == null){
                    list = new ArrayList<>();
                }
                Channel channel = new Channel();
                channel.setCid(rs.getInt("cid"));
                channel.setCname(rs.getString("cname"));
                channel.setDescription(rs.getString("description"));
                list.add(channel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(prst);
        }
        return list;
    }
}
