package step.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, String> errorMessages = (Map<String, String>) session.getAttribute( "form-status" ) ;
        if( errorMessages != null ) {
            req.setAttribute("errorMessages", errorMessages ) ;
            session.removeAttribute( "form-status" ) ;
        }
        req.setAttribute( "page-body", "signup.jsp" ) ;
        req.getRequestDispatcher( "WEB-INF/_layout.jsp" ).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // API - JSON
        // Web - Redirect
        Map<String, String> errorMessages = new HashMap<>() ;
        String userName = req.getParameter( "user-name" ) ;
        if( userName == null || userName.isEmpty() ) {
            errorMessages.put( "user-name", "Не може бути порожнім name" ) ;
        }

        String userPhone = req.getParameter( "user-phone" ) ;
        if( userPhone == null || userPhone.isEmpty() ) {
            errorMessages.put( "user-phone", "Не може бути порожнім phone" ) ;
        }

        String userPassword = req.getParameter( "user-password" ) ;
        if( userPassword == null || userPassword.isEmpty() ) {
            errorMessages.put( "user-password", "Не може бути порожнім password" ) ;
        }

        String userEmail = req.getParameter( "user-email" ) ;
        if( userEmail == null || userEmail.isEmpty() ) {
            errorMessages.put( "user-email", "Не може бути порожнім email" ) ;
        }

        HttpSession session = req.getSession();
        session.setAttribute( "form-status", errorMessages );
        resp.sendRedirect( req.getRequestURI() );
    }
}
