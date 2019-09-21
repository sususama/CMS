package edu.xalead.cms.web.servlet;

import edu.xalead.cms.entity.Channel;
import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.service.ChannelServiceImpl;
import edu.xalead.cms.service.factory.PropertyFactory;
import edu.xalead.cms.vo.pageVo;
import edu.xalead.cms.web.servlet.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "ChannelServlet",urlPatterns = "/backend/channel")
public class ChannelServlet extends BaseServlet {
    private ChannelService channelService;
    public void  setChannelService(ChannelService channelService){
        this.channelService=channelService;
    }

    @Override
    public void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pageVo<Channel> vo = new pageVo<>();
        vo.setOffset((Integer)request.getAttribute("offset"));
        vo.setPagesize((Integer)request.getAttribute("pagesize"));
//        ChannelService channelServiceImpl = new SimpleFactory().createChannelService(2);
//        ChannelService channelServiceImpl = new ProductFactoryImpl().createchannelService();
//        ChannelService channelServiceImpl= (ChannelService)( (PropertyFactory)request.getServletContext().getAttribute("factory")).getBean("channelService");
        //查询频道总记录数
        int count = channelService.findChannelCount();
        vo.setCount(count);
        //查询当前页的所有频道
        List<Channel> channels = channelService.findPageChannel(vo.getOffset(),vo.getPagesize());//返回所有频道
        vo.setDatas(channels);
        //把频道列表放入request
        request.setAttribute("vo",vo);
        //转发频道列表页
        request.getRequestDispatcher("/backend/channel/list.jsp").forward(request,response);
    }
    public void addInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/backend/channel/addinput.jsp").forward(request,response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname=request.getParameter("cname");
        String description=request.getParameter("description");

        Channel channel=new Channel();
        channel.setCname(cname);
        channel.setDescription(description);

        ChannelServiceImpl channelServiceImpl =new ChannelServiceImpl();
        channelServiceImpl.addChannel(channel);
        //转到成功页面
        request.getRequestDispatcher("/backend/channel/success.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String [] cids=request.getParameterValues("cid");
        if (cids!=null&&cids.length>0) {
            ChannelServiceImpl service = new ChannelServiceImpl();
            for (String cid : cids) {
                service.deleteBycid(Integer.parseInt(cid));
            }
        }
        request.getRequestDispatcher("/backend/channel/success.jsp").forward(request,response);
    }
    public void updateInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int cid=Integer.parseInt(request.getParameter("cid"));
        ChannelServiceImpl channelServiceImpl =new ChannelServiceImpl();
        Channel channel= channelServiceImpl.findChannelByid(cid);
        //把查询到的数据放入attribute中
        request.setAttribute("c",channel);
        request.getRequestDispatcher("/backend/channel/updateinput.jsp").forward(request,response);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //先取出频道id
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");
        //先查询出，我们要更新的对象
        ChannelServiceImpl channelServiceImpl = new ChannelServiceImpl();
        Channel channel = channelServiceImpl.findChannelByid(cid);
        //判断提交频道名称和我们查询到的频道名称是否不相等
        if(cname != null && !cname.equals(channel.getCname())){
            //不相等，则更新
            channel.setCname(cname);
        }
        //判断提交频道描述和我们查询到的频道名称是否不相等
        if(description != null && !description.equals(channel.getDescription())){
            //不相等，则更新
            channel.setDescription(description);
        }
        //更新
        channelServiceImpl.updateChannel(channel);
        //转到成功页面
        request.getRequestDispatcher("/backend/channel/success.jsp")
                .forward(request,response);
    }
}
