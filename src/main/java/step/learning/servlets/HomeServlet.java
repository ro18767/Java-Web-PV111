package step.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute( "page-body", "home.jsp" ) ;
    }
}
/*
Сервлет - клас для мережних задач,
HttpServlet - аналог контролерів у веб-проєктах
 */