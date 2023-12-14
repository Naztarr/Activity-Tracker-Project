package com.naz.activitytracker.services;

import com.naz.activitytracker.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserServices {
    ResponseEntity<?> register(UserRequestDTO userRequestDTO);

ResponseEntity<?> login(String userName, String password);
}
