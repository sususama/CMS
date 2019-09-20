package edu.xalead.cms.web.servlet.base;

import edu.xalead.cms.service.factory.PropertyFactory;
import edu.xalead.cms.tools.SystemContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet  extends AbstractBaseServlet {
    @Override
    public void init(){
        PropertyFactory factory= SystemContext.getFactory();
        Class c=this.getClass();
        Method[] ms=c.getMethods();
        for (Method m :ms){
            String methodName=m.getName();
            if (m.getName().startsWith("set")){
                String key=methodName.substring(3,4).toLowerCase()+methodName.substring(4);
                Object obj=factory.getBean(key);
                if (obj!=null) {
                    try {
                        m.invoke(this,obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        try {
            if (methodName==null|| "".equals(methodName)){
                proccess(req, resp);
            }else {
                Class c = this.getClass();
                //得到所有方法的反射类
                Method [] ms =c.getMethods();
                for (Method m:ms){
                    if (methodName.equals(m.getName())){
                        m.invoke(this,req,resp);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public abstract void proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
