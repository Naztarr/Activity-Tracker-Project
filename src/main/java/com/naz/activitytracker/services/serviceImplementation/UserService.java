package com.naz.activitytracker.services.serviceImplementation;

import com.naz.activitytracker.dto.UserRequestDTO;
import com.naz.activitytracker.entities.Users;
import com.naz.activitytracker.repositories.UserRepository;
import com.naz.activitytracker.response.ApiResponse;
import com.naz.activitytracker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class UserService implements UserServices {
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public ResponseEntity<ApiResponse> register(UserRequestDTO userRequestDTO) {
        if (userRepository.usernameExists(userRequestDTO.getUserName())) {
           return ResponseEntity.badRequest().body(new ApiResponse("Apologies! The username you provided already exists.", false));
        } else {
            Users user = mapper.map(userRequestDTO, Users.class);
            userRepository.save(user);
            return ResponseEntity.ok(new ApiResponse("Successfully signed up", true));
        }
    }


    @Override
    public  ResponseEntity<ApiResponse>login(String userName, String password){
            Users user = userRepository.findByUserName(userName);
            if (user != null && user.getPassWord().equals(password)) {
                return ResponseEntity.ok(new ApiResponse("Successfully logged in", true));
            } else {
                return ResponseEntity.ok(new ApiResponse("incorrect username or password", false));
            }
    }

}























//    public UserResponseDTO register(UserRequestDTO userRequestDTO){
//        if(userRepository.findByUserName(userRequestDTO.getUserName())!=null){
//            throw new IllegalArgumentException("Apologies! the username you provided already exists.");
//        }
//        Users user = mapper.map(userRequestDTO, Users.class);
//        userRepository.save(user);
//        UserResponseDTO userResponseDTO = mapper.map(user, UserResponseDTO.class);
//        return userResponseDTO;
//    }



//    @Override
//    public UserResponseDTO login(String userName, String password){
//        try {
//            Users user = userRepository.findByUserName(userName);
//            if (user != null && user.getPassWord().equals(password)) {
//                UserResponseDTO userResponseDTO = mapper.map(user, UserResponseDTO.class);
//                return userResponseDTO;
//            }
//        } catch (Exception e){}
//        throw new IllegalArgumentException("Invalid username or password.");
//    }