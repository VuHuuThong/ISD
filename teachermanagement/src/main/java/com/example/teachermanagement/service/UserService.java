//package com.example.teachermanagement.service;
//
//import com.example.teachermanagement.Utils.MessageKeys;
//import com.example.teachermanagement.components.JwtTokenUtils;
//import com.example.teachermanagement.components.LocalizationUtils;
//import com.example.teachermanagement.dtos.UserDTO;
//import com.example.teachermanagement.exceptions.DataNotFoundException;
//import com.example.teachermanagement.exceptions.PermissionDenyException;
//import com.example.teachermanagement.model.Role;
//import com.example.teachermanagement.model.User;
//import com.example.teachermanagement.repositories.RoleRepository;
//import com.example.teachermanagement.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService  implements IUserService{
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private LocalizationUtils localizationUtils;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtTokenUtils jwtTokenUtils;
//
//    @Override
//    public User createUser(UserDTO userDTO) throws Exception {
//        String email = userDTO.getEmail();
//        //kiểm tra xem email đã tồn tại chưa
//        if(userRepository.existsByEmail(email)){
//            throw new Exception("Email Already Exists");
//        }
//        //Xác định role của tài khoản
//        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(()-> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_DOES_NOT_EXISTS)));
//        if(role.getName().equalsIgnoreCase("ADMIN")){
//            throw new PermissionDenyException("You can not register an admin account");
//        }
//        User newUser =User.builder().fullName(userDTO.getFullName())
//                .email(email)
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .address(userDTO.getAddress())
//                .active(true)
//                .dateOfBirth(userDTO.getDateOfBirth())
//                .role(role)
//                .build();
//
//
//
//        return userRepository.save(newUser);
//    }
//
//    @Override
//    public String login(String email, String password, Long roleId) throws Exception {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if(optionalUser.isEmpty()){
//            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.WRONG_EMAIL_PASSWORD));
//        }
//        User existingUser = optionalUser.get();
//        if(!passwordEncoder.matches(password, existingUser.getPassword())) {
//            throw new BadCredentialsException(localizationUtils.getLocalizedMessage(MessageKeys.WRONG_EMAIL_PASSWORD));
//        }
//        Optional<Role> optionalRole = roleRepository.findById(roleId);
//        if(optionalRole.isEmpty() || !roleId.equals(existingUser.getRole().getId())) {
//            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_DOES_NOT_EXISTS));
//        }
//        if(!existingUser.isActive()) {
//            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.USER_IS_LOCKED));
//        }
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                email, password,
//                existingUser.getAuthorities()
//        );
//        authenticationManager.authenticate(authenticationToken);
//        return jwtTokenUtils.generateToken(existingUser);
//
//    }
//}
