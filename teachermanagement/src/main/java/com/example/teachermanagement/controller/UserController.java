//package com.example.teachermanagement.controller;
//
//import com.example.teachermanagement.Utils.MessageKeys;
//import com.example.teachermanagement.components.LocalizationUtils;
//import com.example.teachermanagement.dtos.UserDTO;
//import com.example.teachermanagement.dtos.UserLoginDTO;
//import com.example.teachermanagement.model.User;
//import com.example.teachermanagement.responses.LoginResponse;
//import com.example.teachermanagement.responses.RegisterResponse;
//import com.example.teachermanagement.service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("${api.prefix}/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private LocalizationUtils localizationUtils;
//
//    @PostMapping("/regiter")
//    public ResponseEntity<RegisterResponse> createUser(
//            @Valid @RequestBody UserDTO userDTO , BindingResult result)
//    {
//        RegisterResponse registerResponse = new RegisterResponse();
//        if (result.hasErrors()) {
//            List<String> errorMessages = result.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .toList();
//
//            registerResponse.setMessage(errorMessages.toString());
//            return ResponseEntity.badRequest().body(registerResponse);
//        }
//        if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
//            registerResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORD_NOT_MATCH));
//            return ResponseEntity.badRequest().body(registerResponse);
//        }
//
//        try {
//            User user = userService.createUser(userDTO);
//            registerResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.REGISTER_SUCCESSFULLY));
//            registerResponse.setUser(user);
//            return ResponseEntity.ok(registerResponse);
//        } catch (Exception e) {
//            registerResponse.setMessage(e.getMessage());
//            return ResponseEntity.badRequest().body(registerResponse);
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(
//            @Valid @RequestBody UserLoginDTO userLoginDTO
//    ) {
//        // Kiểm tra thông tin đăng nhập và sinh token
//        try {
//            String token = userService.login(
//                    userLoginDTO.getPhoneNumber(),
//                    userLoginDTO.getPassword(),
//                    userLoginDTO.getRoleId()
//            );
//            // Trả về token trong response
//            return ResponseEntity.ok(LoginResponse.builder()
//                    .message(localizationUtils.getLocalizedMessage(MessageKeys.LOGIN_SUCCESSFULLY))
//                    .token(token)
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    LoginResponse.builder()
//                            .message(localizationUtils.getLocalizedMessage(MessageKeys.LOGIN_FAILED, e.getMessage()))
//                            .build()
//            );
//        }
//    }
//}
