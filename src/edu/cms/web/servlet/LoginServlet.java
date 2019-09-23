package edu.xalead.cms.web.servlet;

import edu.xalead.cms.entity.User;
import edu.xalead.cms.service.LoginService;
import edu.xalead.cms.web.servlet.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet",urlPatterns = "/backend/login")
public class LoginServlet extends BaseServlet {
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    //比较验证码的方法
    private boolean isEq(ArrayList<String> chk,String chknum){
        for (int i=0;i<chk.size();i++){
            //取出session中的第i个字符
            String ck=chk.get(i);
            String cn=String.valueOf(chknum.charAt(i));
            if (!ck.equals(cn))
                return false;
        }
        return true;
    }
    @Override
    public void proccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user=request.getParameter("user");
        String password=request.getParameter("pwd");
        //取出验证码
        String chkNumber=request.getParameter("chknumber");
        //取出session的验证码
        ArrayList<String> serchk= (ArrayList<String>) request.getSession().getAttribute("checkcode");
        if (chkNumber!=null&& chkNumber!=""&&isEq(serchk,chkNumber)){
            //校验用户
//            LoginService loginService=new LoginService();
            User u= loginService.chkUser(user);
            if (u!=null){
                //验证密码
                if (password!=null&&password.equals(u.getPassword())){
                    //存储一个通行证，用来区分是否登陆过
                    request.getSession().setAttribute("user",u);
                    //通过验证，转到后台页面
                    request.getRequestDispatcher("/backend/main.jsp").forward(request,response);
                }else {
                    request.setAttribute("error","密码错误，请确认密码");
                    request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
                }
            }else {
                request.setAttribute("error","用户不存在，请确认用户名");
                request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("error","验证码错误，请重新输入");
            request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
        }
    }
    public void logInout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //删除令牌属性
        request.getSession().removeAttribute("user");
        //转发到登陆页面
        request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
    }
}
