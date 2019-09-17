package edu.xalead.cms.web.filter;

import edu.xalead.cms.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "TokenFilter",urlPatterns = "/*/backend/**/")
public class TokenFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //取令牌
        HttpServletRequest request=(HttpServletRequest)req;
        User u=(User)request.getSession().getAttribute("user");
        //创建规则字符串,排出login.jsp
        String regx="http:\\/\\/.+\\:8080\\/backend\\/login\\.jsp.*";
        //排出jgp,png,gif类型
        String regx1=".+(\\.jpg|\\.gif|\\.png|\\.css|\\.js)\\.*";
        //排出backend/login的路径
        String regx2="http:\\/\\/.+\\:8080\\/backend\\/login.*";
        String url=request.getRequestURL().toString();

        if (u!=null||
                url.matches(regx)||
                url.matches(regx1)||
                url.matches(regx2)){
            chain.doFilter(req, resp);
        }else {
            //转到登陆页面重新登陆
            request.setAttribute("error","请先登陆");
            request.getRequestDispatcher("/backend/login.jsp").forward(request,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
