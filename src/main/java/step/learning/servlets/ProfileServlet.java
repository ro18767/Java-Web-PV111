package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.entity.News;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ProfileServlet   extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("page-body", "profile.jsp");
        req.getRequestDispatcher( "../WEB-INF/_layout.jsp" ).forward(req, resp);
    }
}
