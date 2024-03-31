package step.learning.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import step.learning.dal.CommentDao;
import step.learning.dal.NewsDao;
import step.learning.entity.Comment;
import step.learning.entity.News;
import step.learning.entity.User;
import step.learning.services.form_parse.FormParseResult;
import step.learning.services.form_parse.FormParseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CommentServlet extends HttpServlet {
    private final CommentDao commentDao;

    private final FormParseService formParseService;

    private final Logger logger ;

    @Inject
    public CommentServlet(CommentDao commentDao, FormParseService formParseService, Logger logger) {
        this.commentDao = commentDao;
        this.formParseService = formParseService;
        this.logger = logger;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");

        User user = (User) req.getAttribute("auth-user");


        FormParseResult formParseResult ;
        try {
            formParseResult = formParseService.parse(req) ;
        }
        catch (ParseException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            sendRest(resp, "error", "Data composition error");
            return;
        }
        Map<String,String> fields = formParseResult.getFields() ;

        String newsText = fields.get( "comment" ) ;
        if( newsText == null || newsText.isEmpty() ) {
            sendRest(resp, "error", "Missing required data: 'comment'");
            return;
        }

        String newsIdSting = fields.get( "newsId" ) ;

        if( newsIdSting == null || newsIdSting.isEmpty() ) {
            sendRest(resp, "error", "Missing required data: 'newsId'");
            return;
        }
        Comment comment = new Comment();

        comment.setNewsId(UUID.fromString(newsIdSting));

        if(user != null) {
            comment.setUserId(user.getId());
        }

        comment.setText(newsText);
        comment.setCreateDt(new Date());

        if( commentDao.addComment(comment) ) {
            sendRest(resp, "success", "Comment created");
        }
        else {
            sendRest(resp, "error", "Internal error.");
        }
    }


    private void sendRest(HttpServletResponse resp, String status, String message) throws IOException {
        Gson gson = new Gson();
        JsonObject res = new JsonObject() ;
        res.addProperty("status", status);
        res.addProperty("message", message);
        resp.getWriter().print( gson.toJson(res) );
    }

    private String readStreamToEnd( InputStream inputStream ) throws IOException {
        final byte[] buffer = new byte[32 * 1024];  // 32k
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream() ;
        int len ;
        while( ( len = inputStream.read( buffer ) ) > -1 ) {
            byteBuilder.write( buffer, 0, len ) ;
        }
        return byteBuilder.toString( StandardCharsets.UTF_8.name() ) ;
    }
}
/*
Д.З. Коментарі:
у випадку якщо користувач має право на видалення додати кнопку "видалення"
для кожного з коментарів. Реалізувати JS функціональність для збирання даних
щодо коментаря, який видаляється. На даному етапі достатньо вивести у консоль.
 */