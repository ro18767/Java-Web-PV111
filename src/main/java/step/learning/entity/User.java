package step.learning.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id ;
    private String name ;
    private String phone ;
    private String email ;
    private String avatar ;
    private String passwordSalt ;
    private String passwordDk ;  // Derived Key (https://datatracker.ietf.org/doc/html/rfc2898)

    private List<Role> roles;

    public User() {
    }

    public User(ResultSet resultSet) throws SQLException {
        String id;
        try {
            id = resultSet.getString( "user_id" ) ;
        }
        catch( Exception ignore ) {
            id = resultSet.getString( "id" );
        }
        setId( UUID.fromString( id ) ) ;
        setName( resultSet.getString( "name" ) );
        setPhone( resultSet.getString( "phone" ) );
        setEmail( resultSet.getString( "email" ) );
        setAvatar( resultSet.getString( "avatar" ) );
        setPasswordSalt( resultSet.getString( "salt" ) );
        setPasswordDk( resultSet.getString( "dk" ) );
    }

    public User includeRoles(ResultSet resultSet) throws SQLException {
        this.roles = new ArrayList<>();
        while(resultSet.next()) {
            roles.add( new Role(resultSet) ) ;
        }
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setId( String id ) {
        this.id = UUID.fromString( id ) ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordDk() {
        return passwordDk;
    }

    public void setPasswordDk(String passwordDk) {
        this.passwordDk = passwordDk;
    }
}
// userName, userPhone, userPassword, userEmail, savedFilename