package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.User;
import com.example.nhahang.model.request.ChangePasswordRequest;
import com.example.nhahang.model.request.CreateUserRequest;
import com.example.nhahang.model.request.UpdateProfileRequest;

public interface UserService {
    
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);
    String verifyAccount(String email, String otp); 
    String regenerateOtp(String email); // Add this method to the interface
    // 
    List<User> getListuser();
    void deleleUser(long id);
    User getUser(long id);
    User updatesUser(long id,CreateUserRequest request);
}
