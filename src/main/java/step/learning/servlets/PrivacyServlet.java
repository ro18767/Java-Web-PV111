package step.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class PrivacyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute( "page-body", "privacy.jsp" ) ;
        req.getRequestDispatcher( "WEB-INF/_layout.jsp" )  // перенаправлення (внутрішнє)
                .forward(req, resp);
    }
}
/* req.getServletContext().getRealPath("/")
Сервлет - клас для мережних задач,
HttpServlet - аналог контролерів у веб-проєктах
Д.З. Створити веб-проєкт, добитись його запуску
Реалізувати сервлет для нової сторінки "privacy",
забезпечити її відображення через шаблон (layout)
Додати посилання на неї до головної сторінки.
* наповнити сторінку "privacy" контентом
 */