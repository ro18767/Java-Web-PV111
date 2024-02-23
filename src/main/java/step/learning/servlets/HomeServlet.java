package step.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Singleton
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // демонстрація роботи з ресурсами (папка resources)
        // вміст папки входить у deploy у складі папки target/classes
        // звернутись до цієї папки можна через завантажувач класів
        // сам завантажувач можна взяти з будь-якого об'єкту
        try(InputStream reader = this.getClass().getClassLoader().getResourceAsStream("appsettings.json")){
            System.out.println(
                    readStreamToEnd(
                            Objects.requireNonNull(reader) ) ) ;
        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }

        req.setAttribute( "page-body", "home.jsp" ) ;
        req.getRequestDispatcher( "WEB-INF/_layout.jsp" )  // перенаправлення (внутрішнє)
                .forward(req, resp);
    }
    private String readStreamToEnd( InputStream inputStream ) throws IOException {
        final byte[] buffer = new byte[32 * 1024];  // 32k
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream() ;
        int len ;
        while( ( len = inputStream.read( buffer ) ) > -1 ) {
            byteBuilder.write( buffer, 0, len ) ;
        }
        return byteBuilder.toString() ;
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