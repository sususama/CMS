package edu.xalead.cms.web;

import edu.xalead.cms.service.BaseService;
import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.web.servlet.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends BaseServlet {
    private ChannelService channelService;

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    /*
    *
    * */
    public void channelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
