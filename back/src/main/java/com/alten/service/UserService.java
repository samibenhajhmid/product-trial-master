package com.alten.service;

import com.alten.DTO.AccountRequest;
import com.alten.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User saveUser(User user);

    public void deleteUser(Long id);

    Optional<User> findByEmail(String email);
    boolean isAdmin(User user);
}
