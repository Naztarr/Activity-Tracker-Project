package com.naz.activitytracker.controller;

import com.naz.activitytracker.dto.UserRequestDTO;
import com.naz.activitytracker.services.serviceImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO) {
    String username = userRequestDTO.getUserName();
    String password = userRequestDTO.getPassWord();

    return userService.login(username, password);
    }
}























//    @PostMapping("/signup")
//    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO userRequestDTO) {
//    UserResponseDTO newUser = userService.register(userRequestDTO);
//    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }





//    @PostMapping("/signup")
//    public ResponseEntity<Map<String, UserResponseDTO>> signUp(@RequestBody UserRequestDTO userRequestDTO) {
//        System.out.println(userRequestDTO);
//        UserResponseDTO newUser = userService.register(userRequestDTO);
//        Map<String, UserResponseDTO> map = new HashMap<>();
//        map.put("details", newUser);
//        return new ResponseEntity<>(map, HttpStatus.CREATED);
//    }







//    @PostMapping("/login")
//    public ResponseEntity<UserResponseDTO> login(@RequestParam String username, @RequestParam String password) {
//        UserResponseDTO user = userService.login(username, password);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
////        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
//        String username = userRequestDTO.getUserName();
//        String password = userRequestDTO.getPassWord();
//
//        UserResponseDTO user = userService.login(username, password);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }




















//    @PostMapping("/signup")
//    public ResponseEntity<Map<String, UserResponseDTO>> signUp(@RequestBody UserRequestDTO userRequestDTO) {
//        UserResponseDTO newUser = userService.register(userRequestDTO);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(Collections.singletonMap("details", newUser));
//    }




