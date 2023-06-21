package akers.mongodb.services;

import akers.mongodb.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User getById(String id);
    void update(String id,User user);
    void delete(String id);
    void add(User user);
    boolean existID(String id);
}
