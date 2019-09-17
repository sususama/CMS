package edu.xalead.cms.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PageFilter",urlPatterns = "/backend/*")
public class PageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //试着取一下当前页码的值
       /* 这有问题,
        vo.setPagesize((Integer)(request.getAttribute("pagesize")));
        vo.setOffset((Integer)(request.getAttribute("offset")));
        这俩拿不到数据*/
//        String regx = ".+\\/backend\\/.+List.*";
        String regx = ".+\\/backend.*";
        String regx1 = ".+\\/backend\\/.+list\\.jsp.*";
        String url = ((HttpServletRequest)req).getRequestURL().toString();
        if(url.matches(regx) || url.matches(regx1)) {
            //在request中存储每页记录数
            req.setAttribute("pagesize", 3);
            //试着取一下，当前页码的值
            String of = req.getParameter("pager.offset");

            //如果为空，说明是第一次访问，页码置为0，也就是第一页的第一条记录的索引
            if (of == null || "".equals(of)) {
                req.setAttribute("offset", 0);
            }else {
                //把提交上来的当前偏移量保存在request的offset属性中
                req.setAttribute("offset", Integer.parseInt(of));
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
