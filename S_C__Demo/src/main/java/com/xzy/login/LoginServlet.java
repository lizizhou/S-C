package com.xzy.login;

import com.xzy.bean.User;
import com.xzy.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String checkImg = req.getParameter("checkImg");
        String checkcode_session = (String) req.getSession().getAttribute("checkcode_session");
        if(checkImg!=null&&checkcode_session!=null){
            if(!checkcode_session.equals(checkImg)){
                req.setAttribute("loginInfo","验证码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                return;
            }
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            try {
                User user = query1(username, password);
                if(user==null){
                    req.setAttribute("loginInfo","用户名或密码错误");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                    return;

                }else{
                    resp.sendRedirect("/index.jsp");
                    req.getSession().setAttribute("user",user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public User query1( String username,String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),username,password );
         return user;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
