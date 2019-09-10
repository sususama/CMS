package edu.xalead.cms.service.factory.简单工厂;

import edu.xalead.cms.entity.Channel;
import edu.xalead.cms.service.ChannelService;

import java.util.List;

public class ChannelServiceForOracle implements ChannelService {
    @Override
    public List<Channel> findPageChannel(int offset, int pagesize) {
        return null;
    }

    @Override
    public int findChannelCount() {
        return 0;
    }

    @Override
    public void updateChannel(Channel channel) {

    }

    @Override
    public Channel findChannelByid(int cid) {
        return null;
    }

    @Override
    public void deleteBycid(int cid) {

    }

    @Override
    public void addChannel(Channel channel) {

    }

    @Override
    public List<Channel> findAllChannel() {
        return null;
    }
}
