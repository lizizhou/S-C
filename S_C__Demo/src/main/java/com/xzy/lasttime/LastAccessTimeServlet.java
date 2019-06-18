package com.xzy.lasttime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/lasttime")
public class LastAccessTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        resp.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd---hh:mm:ss");
        String nowTime = simpleDateFormat.format(date);

        Cookie cookie = new Cookie("lastAccessTime",nowTime);

        resp.addCookie(cookie);

        String lastTime = null;
        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            for (Cookie cookie1 : cookies) {
                if("lastAccessTime".equals(cookie1.getName())) {
                    lastTime = cookie1.getValue();
                }
            }
        }
        if(lastTime==null){
            resp.getWriter().write("您是第一次访问！");
        }else{
            resp.getWriter().write("您上次访问时间是："+lastTime);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req,resp);
    }
}
