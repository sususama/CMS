package edu.xalead.cms.service.factory.简单工厂;

import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.service.ChannelServiceImpl;

public class SimpleFactory {
    public static final int CHANNEL_SREVICE_FOR_ORACLE=1;
    public static final int CHANNEL_SREVICE_FOR_MYSQL=2;
    public static ChannelService createChannelService(int type){
    if (type==CHANNEL_SREVICE_FOR_ORACLE){
        return new ChannelServiceForOracle();
    }
    if (type==CHANNEL_SREVICE_FOR_MYSQL){
        return new ChannelServiceImpl();
    }
        return null;
    }
}
