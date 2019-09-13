package edu.xalead.cms.service;

import edu.xalead.cms.entity.Channel;

import java.util.List;

public interface ChannelService {
    public List<Channel> findPageChannel(int offset, int pagesize);
    public int findChannelCount();
    public  void  updateChannel(Channel channel);
    public Channel findChannelByid(int cid);
    public void deleteBycid(int cid);
    public void addChannel(Channel channel);
    public List<Channel> findAllChannel();
}
