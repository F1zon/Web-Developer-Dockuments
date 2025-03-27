package com.example.webdev.service;

import com.example.webdev.db.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
