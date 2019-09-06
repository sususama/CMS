package edu.xalead.cms.dao;

import edu.xalead.cms.entity.Channel;

import java.sql.Connection;
import java.util.List;

public interface ChannelDao {
    //查询频道列表
    List<Channel> findAllChannel(Connection coon);

    void addChannel(Channel channel, Connection conn);

    void deleteChannelByid(int cid, Connection conn);

    Channel findChannelByid(int cid, Connection conn);

    void updateChannel(Channel channel, Connection conn);

    int findChannelcount(Connection conn);

    List<Channel> findPageChannel(int offset, int pagesize, Connection conn);
}
