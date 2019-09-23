package edu.xalead.cms.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//生成验证码，响应给客户端
@WebServlet(name = "edu.xalead.cms.web.servlet.CheckCodeServlet",urlPatterns = "/backend/images/checkcodes.png")
public class CheckCodeServlet extends HttpServlet {

    //创建生成随机数的函数
    private int generRandomint(int start,int range){
        int index=new Random().nextInt(range-start)+start;
        return index;

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //随机生成一个验证码
        //创建一个包含所有的随机验证码中的所有要出现的字符的字符串
        String str="a";
        int len=str.length();
        //创建一个字符列表，存验证码
        ArrayList<String> buf=new ArrayList<>();
        for (int i=0;i<4;i++) {
            //随机生成 一个0-len之间的随机数
            int index;
            index = generRandomint(0,len);
//            System.out.println("运行了"+i+"次,index的值:"+index);
            char c=str.charAt(index);
//            System.out.println("c的值为："+c);
            buf.add(String.valueOf(c));
        }
        //将验证码存入session
        request.getSession().setAttribute("checkcode",buf);
        //生成验证码图片
        //生成图片缓存
        BufferedImage img=new BufferedImage(44,20,BufferedImage.TYPE_4BYTE_ABGR);
        //用画笔拿到引用
        Graphics2D g2= (Graphics2D) img.getGraphics();
        //改变背景色
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,44,20);
        Font myfont=g2.getFont();
        Font fsib30 = new Font("宋体", Font.BOLD + Font.ITALIC, 16);
        g2.setFont(fsib30);
        //字符的初始偏移量
        int offset =5;
        //字符占的宽度
        int width=8;
        //随机生成位置
        int x=offset;
        int y=18;
        //在背景上画出验证码
        int r;
        int g;
        int b;
        for (int i=0;i<4;i++){
            //随机生成R，G，B，A，四个数字，每个值是0-255之间的随机数
//            System.out.println("运行了"+i+"次");
            r = generRandomint(256/2,256);
            g = generRandomint(128,256);
            b = generRandomint(128,256);
            int a=255;/*generRandomint(255,256);*/
            Color c=new Color(r,g,b,a);
            //改变画笔颜色

            g2.setColor(c);
            //画字符
            g2.drawString(buf.get(i),x+(width*i),y);
        }
        g2.setFont(myfont);
        //设置mime类型
        response.setContentType("iamge/png");
        //生成图片写到客户端
        ImageIO.write(img,"png",response.getOutputStream());

    }
}
