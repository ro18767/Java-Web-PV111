package step.learning.dal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.entity.Comment;
import step.learning.entity.News;
import step.learning.services.db.DbService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CommentDao {
    private final DbService dbService;
    private final Logger logger;

    @Inject
    public CommentDao(DbService dbService, Logger logger) {
        this.dbService = dbService;
        this.logger = logger;
    }

    public List<Comment> getNewsComments(News news) {
        return getNewsComments(news.getId().toString());
    }
    public List<Comment> getNewsComments(String newsId) {
        List<Comment> ret = new ArrayList<>();
        String sql = "SELECT * FROM Comments WHERE news_id=?";
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)) {
            prep.setString(1, newsId);
            ResultSet resultSet = prep.executeQuery();
            while( resultSet.next() ) {
                ret.add( new Comment(resultSet) ) ;
            }
            resultSet.close();
        }
        catch( SQLException ex ) {
            logger.log( Level.SEVERE, ex.getMessage() + " -- " + sql );
        }
        return ret;
    }
}
