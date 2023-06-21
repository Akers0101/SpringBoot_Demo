package akers.mongodb.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.lang.annotation.Documented;
@Builder
@Document(collection ="user")
public class User {
    @Id
    final String id;
    final String name;
    final String username;
    final String password;

    public User(String id,String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }



    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUsername() { return username; }
    public String getPassword() {
        return password;
    }

}
