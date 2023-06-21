package akers.mongodb.services;

import akers.mongodb.exceptions.NotFoundException;
import akers.mongodb.model.User;
import akers.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
   
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public User getById(String id) {
        return userRepository.findById(id).get();
    }
    public void update(String id, User user) {
        Optional<User> check = userRepository.findById(id);
        if(check.isPresent()){
            userRepository.save(new User(
                    id,
                    user.getName(),
                    user.getUsername(),
                    user.getPassword()
                    )
            );
        }else{
            throw new NotFoundException("User not found, update fail");
        }
    }
   
    public void delete(String id) {
        Optional<User> check = userRepository.findById(id);
        if(check.isPresent()){
            userRepository.deleteById(id);
            System.out.println("Delete sucessfully "+id);
        }else{
            throw new NotFoundException("User not found, delete fail");
        }
    }
    public boolean existID(String id){
       return userRepository.existsById(id);
    }
    public void add(User user) {
        userRepository.save(user);
    }
}
