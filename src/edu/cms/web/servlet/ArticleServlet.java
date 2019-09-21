package edu.xalead.cms.web.servlet;

import edu.xalead.cms.entity.Article;
import edu.xalead.cms.entity.Channel;
import edu.xalead.cms.service.ArticleService;
import edu.xalead.cms.service.ArticleServiceImpl;
import edu.xalead.cms.service.ChannelService;
import edu.xalead.cms.vo.pageVo;
import edu.xalead.cms.web.servlet.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ArticleServlet",urlPatterns = "/backend/article")
public class ArticleServlet extends BaseServlet {
    private ArticleService articleService;

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    private ChannelService channelService;
    public void  setArticleService(ArticleService articleService){
        this.articleService=articleService;
    }
    @Override
    public void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pageVo<Article> vo = new pageVo<>();
        vo.setOffset((Integer)request.getAttribute("offset"));
        vo.setPagesize((Integer)request.getAttribute("pagesize"));
        int count = articleService.findArticleeCount();
        vo.setCount(count);

        List<Article> datas = articleService.findPageArticlee(vo.getOffset(),vo.getPagesize());
        vo.setDatas(datas);

        request.setAttribute("vo",vo);

        request.getRequestDispatcher("/backend/article/list.jsp").forward(request,response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String aid=request.getParameter("aid");
        String cid=request.getParameter("cid");
        String author=request.getParameter("author");
        String content=request.getParameter("content");
        String source=request.getParameter("source");
        String title=request.getParameter("title");
        String createTime=request.getParameter("createTime");
        Article article=new Article();
        article.setAid(Integer.parseInt(aid));
        article.setAuthor(author);
        article.setCid(Integer.parseInt(cid));
        article.setContent(content);
        article.setSource(source);
        article.setTitle(title);
        article.setCreateTime(new SimpleDateFormat().parse(createTime));

        ArticleServiceImpl articleServiceImpl =new ArticleServiceImpl();
        articleServiceImpl.addArticlee(article);
        //转到成功页面
        request.getRequestDispatcher("/backend/article/success.jsp").forward(request,response);
    }
    public void addInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article =new Article();
        article.setAid(Integer.parseInt(request.getParameter("aid")));
        article.setAuthor(request.getParameter("author"));
        article.setCid(Integer.parseInt(request.getParameter("cid")));
        article.setContent(request.getParameter("content"));
        article.setCreateTime(new Date());
        article.setTitle(request.getParameter("title"));
        article.setSource(request.getParameter("source"));
        request.getRequestDispatcher("/backend/article/addinput.jsp").forward(request,response);
    }
    public void updateInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Channel> channels =channelService.findAllChannel();
        request.setAttribute("c",channels);

        int aid=Integer.parseInt(request.getParameter("aid"));
        ArticleServiceImpl articleService =new ArticleServiceImpl();
        Article article= articleService.findArticleeByid(aid);
        //把查询到的数据放入attribute中
        request.setAttribute("a",article);
        request.getRequestDispatcher("/backend/channel/updateinput.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] aids=request.getParameterValues("aid");
        if (aids!=null&&aids.length>0) {
            ArticleServiceImpl service = new ArticleServiceImpl();
            for (String aid : aids) {
                service.deleteBycid(Integer.parseInt(aid));
            }
        }
        request.getRequestDispatcher("/backend/article/success.jsp").forward(request,response);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Channel> channels =channelService.findAllChannel();
        request.setAttribute("c",channels);

        int aid = Integer.parseInt(request.getParameter("aid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        String author=request.getParameter("author");
        String content=request.getParameter("content");
        String source=request.getParameter("source");
        String title=request.getParameter("title");
        String createTime=request.getParameter("createTime");
        //先查询出，我们要更新的对象
        ArticleServiceImpl channelServiceImpl = new ArticleServiceImpl();
        Article article = channelServiceImpl.findArticleeByid(aid);
        //判断提交频道名称和我们查询到的频道名称是否不相等
        if(title != null && !title.equals(article.getTitle())){
            //不相等，则更新
            article.setTitle(title);
        }
        //判断提交频道描述和我们查询到的频道名称是否不相等
        if(author != null && !author.equals(article.getAuthor())){
            //不相等，则更新
            article.setAuthor(author);
        }
        //更新
        channelServiceImpl.updateArticlee(article);
        //转到成功页面
        request.getRequestDispatcher("/backend/article/success.jsp")
                .forward(request,response);
    }
}
