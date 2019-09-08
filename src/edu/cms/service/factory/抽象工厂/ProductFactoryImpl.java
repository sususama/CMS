package edu.xalead.cms.service.factory.抽象工厂;

import edu.xalead.cms.dao.ChannelDao;
import edu.xalead.cms.service.ChannelService;

public class ProductFactoryImpl extends ProductFactory {
    @Override
    public ChannelService createchannelService() {
        return null;
    }

    @Override
    public ChannelDao createchannelDao() {
        return null;
    }
}
