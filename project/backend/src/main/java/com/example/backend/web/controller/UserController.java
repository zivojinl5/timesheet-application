package com.example.backend.web.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.model.User;
import com.example.backend.domain.model.req_model.RegisterRequest;
import com.example.backend.domain.service.IUserService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.dto.UserDTO;
import com.example.backend.web.dto.req_dto.RegisterRequestDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final IUserService userService;
    private final GenericMapper genericMapper;

    @GetMapping("/pagination/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<UserDTO>> getAllUsersWithPaginationAndSorting(@PathVariable int pageNumber,
            @PathVariable int pageSize, @PathVariable String sortingProperty, @PathVariable String sortingDirection) {
        Direction direction;
        direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> userPage = userService.getAllUsersWithPaginationAndSorting(pageRequest);
        Page<UserDTO> userDTOPage = genericMapper.mapPage(userPage, UserDTO.class);
        ResponseEntity<Page<UserDTO>> responseEntity = ResponseEntity.ok(userDTOPage);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = genericMapper.mapList(users, UserDTO.class);
        ResponseEntity<List<UserDTO>> responseEntity = ResponseEntity.ok(userDTOs);
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(
            @RequestBody RegisterRequestDTO requestDTO) {
        RegisterRequest request = genericMapper.mapType(requestDTO, RegisterRequest.class);
        System.out.println(request);
        User user = genericMapper.mapType(request, User.class);
        System.out.println(user);
        User registeredUser = userService.createUser(user);
        UserDTO registeredUserDTO = genericMapper.mapType(registeredUser, UserDTO.class);
        ResponseEntity<UserDTO> responseEntity = ResponseEntity.ok(registeredUserDTO);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,
            @RequestBody UserDTO details) {
        details.setId(id);
        User user = genericMapper.mapType(details, User.class);
        User updatedUser = userService.updateUser(user);

        UserDTO updatedUserDTO = genericMapper.mapType(updatedUser, UserDTO.class);
        ResponseEntity<UserDTO> responseEntity = ResponseEntity.ok(updatedUserDTO);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("User deleted");
        return responseEntity;
    }

}
