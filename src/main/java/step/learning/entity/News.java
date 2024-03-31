package step.learning.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class News {
    private UUID id ;
    private String title ;
    private String spoiler ;
    private UUID authorUserId ;
    private String text ;
    private String imageUrl ;
    private Date createDt ;
    private Date deleteDt ;

    public static News fromResultSet(ResultSet resultSet) throws SQLException {
        News ret = new News() ;
        ret.setId( UUID.fromString( resultSet.getString("id") ) );
        ret.setTitle( resultSet.getString( "title" ) ) ;
        ret.setSpoiler( resultSet.getString( "spoiler" ) ) ;

        String StringAuthorUserId = resultSet.getString( "author_user_id" );

        if(StringAuthorUserId != null) {
            try {
                ret.setAuthorUserId(UUID.fromString(StringAuthorUserId));
            } catch (IllegalArgumentException ex) {

            }
        }

        ret.setText( resultSet.getString( "text" ) ) ;
        ret.setImageUrl( resultSet.getString( "image_url" ) ) ;
        Timestamp timestamp = resultSet.getTimestamp( "created_dt" ) ;
        if( timestamp != null ) {
            ret.setCreateDt( new Date( timestamp.getTime() ) );
        }
        timestamp = resultSet.getTimestamp( "deleted_dt" ) ;
        if( timestamp != null ) {
            ret.setDeleteDt( new Date( timestamp.getTime() ) );
        }
        return ret;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(String spoiler) {
        this.spoiler = spoiler;
    }

    public UUID getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(UUID authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getDeleteDt() {
        return deleteDt;
    }

    public void setDeleteDt(Date deleteDt) {
        this.deleteDt = deleteDt;
    }
}
