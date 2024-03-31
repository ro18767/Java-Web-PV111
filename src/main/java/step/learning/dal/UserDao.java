package step.learning.dal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.entity.User;
import step.learning.services.db.DbService;
import step.learning.services.kdf.KdfService;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class UserDao {
    private final KdfService kdfService ;
    private final DbService dbService;
    private final Logger logger ;

    @Inject
    public UserDao(KdfService kdfService, DbService dbService, Logger logger) {
         this.kdfService = kdfService;
         this.dbService = dbService;
         this.logger = logger;
    }

    public User getUserById( String id ) {
        /*
        * Д.З. Створити метод UserDao::getUserById
        * Перевірити його працездатність шляхом переходу на
        * сторінки профілів різних користувачів (як автентифікованого, так і ні)
        *
        * Узгодження з попереднім ДЗ - при виводі автора новини додати посилання
        * на його профіль */
        return null ;
    }
    private User includeRoles(User user) {
        String sql = String.format(
                "SELECT * FROM User_Roles ur JOIN Roles r ON ur.role_id = r.id" +
                        " WHERE ur.user_id='%s' ",
                user.getId() ) ;
        try(Statement statement = dbService.getConnection().createStatement()) {
            user.includeRoles( statement.executeQuery( sql ) ) ;
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() + " -- " + sql );
        }
        return user;
    }
    public User getUserByCredentials(String email, String password) {
        String sql = "SELECT * FROM Users u WHERE u.email=?" ;
        try( PreparedStatement prep = dbService.getConnection().prepareStatement( sql ) ) {
            prep.setString( 1, email ) ;
            ResultSet resultSet = prep.executeQuery();
            if( resultSet.next() ) {
                String salt = resultSet.getString("salt") ;
                String dk = resultSet.getString("dk") ;
                if( kdfService.dk(password, salt).equals( dk ) ) {
                    return includeRoles( new User( resultSet ) ) ;
                }
            }
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() + " -- " + sql );
        }
        return null ;
    }
    public boolean signupUser(String userName, String userPhone, String userPassword, String userEmail, String savedFilename) {
        String sql = "INSERT INTO Users(name,phone,salt,dk,email,avatar) VALUES(?,?,?,?,?,?)" ;
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)) {
            String salt = kdfService.dk(UUID.randomUUID().toString(), UUID.randomUUID().toString());

            prep.setString(1, userName);
            prep.setString(2, userPhone);
            prep.setString(3, salt);
            prep.setString(4, kdfService.dk(userPassword,salt));
            prep.setString(5, userEmail);
            prep.setString(6, savedFilename);

            prep.executeUpdate() ;
            return true ;
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() + " -- " + sql );
            return false ;
        }
    }

    public boolean installTable() {
        String sql = "CREATE TABLE Users(" +
                "id     CHAR(36)     PRIMARY KEY DEFAULT( UUID() )," +
                "name   VARCHAR(64)  NOT NULL," +
                "phone  VARCHAR(16)  NOT NULL," +
                "salt   CHAR(32)     NOT NULL," +
                "dk     CHAR(32)     NOT NULL," +
                "email  VARCHAR(128) NOT NULL," +
                "avatar VARCHAR(64)  NULL" +
            ") ENGINE = InnoDB, DEFAULT CHARSET = utf8mb4";
        try(Statement statement = dbService.getConnection().createStatement()) {
            statement.executeUpdate( sql ) ;
            return true ;
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage() + " -- " + sql );
            return false ;
        }
    }
}
