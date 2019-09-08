package edu.xalead.cms.service.factory.抽象工厂;

import edu.xalead.cms.dao.ChannelDao;
import edu.xalead.cms.service.ChannelService;
/*
* 抽象工厂和工厂方法唯一的区别就是抽象工厂中可以定义多个方法，创建多个产品
* */
public abstract class ProductFactory {
    public abstract ChannelService createchannelService();
    public abstract ChannelDao createchannelDao();
}
