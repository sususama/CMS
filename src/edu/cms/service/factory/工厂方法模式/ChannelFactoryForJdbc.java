package edu.xalead.cms.service.factory.工厂方法模式;

import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.service.ChannelServiceImpl;

public class ChannelFactoryForJdbc extends ChannelFactory {
    @Override
    public ChannelService createchannelService() {
        return new ChannelServiceImpl();
    }
}
