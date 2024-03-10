package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dal.NewsDao;
import step.learning.services.db.DbService;
import step.learning.services.form_parse.FormParseService;
import step.learning.services.hash.HashService;
import step.learning.services.kdf.KdfService;
import step.learning.services.rnd.CodeGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Singleton
public class IocServlet extends HttpServlet {

    private final CodeGenerator codeGenerator;
    private final HashService hashService;

    @Inject
    public IocServlet(CodeGenerator codeGenerator, HashService hashService) {
        this.codeGenerator = codeGenerator;
        this.hashService = hashService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("gen-code", codeGenerator.newCode(10));
        req.setAttribute("gen-hash", hashService.hash((String) req.getAttribute("gen-code")));
        req.setAttribute("page-body", "ioc.jsp");
        req.getRequestDispatcher("WEB-INF/_layout.jsp")  // перенаправлення (внутрішнє)
                .forward(req, resp);
    }
}
/*
Д.З. Забезпечити випробування інжектора сервісів:
Вивести у складі сторінки "іос"
- випадкового коду (з сервіса кодів)
- його хешу (з сервіса хешування).
Дані мають змінюватись з оновленням сторінки.
 */