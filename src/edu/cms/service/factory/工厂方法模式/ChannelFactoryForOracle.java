package edu.xalead.cms.service.factory.工厂方法模式;

import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.service.factory.简单工厂.ChannelServiceForOracle;

public class ChannelFactoryForOracle extends ChannelFactory {

    @Override
    public ChannelService createchannelService() {
        return new ChannelServiceForOracle();
    }
}
