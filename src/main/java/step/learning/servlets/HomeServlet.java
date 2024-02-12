package step.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute( "page-body", "home.jsp" ) ;
        req.getRequestDispatcher( "WEB-INF/_layout.jsp" )  // перенаправлення (внутрішнє)
                .forward(req, resp);
    }
}
/*
Сервлет - клас для мережних задач,
HttpServlet - аналог контролерів у веб-проєктах
Д.З. Створити веб-проєкт, добитись його запуску
Реалізувати сервлет для нової сторінки "privacy",
забезпечити її відображення через шаблон (layout)
Додати посилання на неї до головної сторінки.
* наповнити сторінку "privacy" контентом
 */