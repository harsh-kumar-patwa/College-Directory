package com.example.collegedirectory.service.ServiceInterface;
import com.example.collegedirectory.model.User;
import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User saveUser(User user);
    void deleteUser(Long id);
    User findById(Long id);
    List<User> findAllUsers();
}