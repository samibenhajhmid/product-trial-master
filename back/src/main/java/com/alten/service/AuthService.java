package com.alten.service;

import com.alten.DTO.AccountRequest;
import com.alten.DTO.LoginRequest;

public interface AuthService {
     void registerUser(AccountRequest request);
     String authenticateUser(LoginRequest loginRequest);
}
