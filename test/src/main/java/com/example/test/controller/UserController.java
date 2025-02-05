package com.example.test.controller;

import com.example.test.controller.exceptions.ContentNotFoundException;
import com.example.test.controller.exceptions.ErrorCode;
import com.example.test.controller.http.CustomHeaders;
import com.example.test.controller.http.GetAllUsersResponse;
import com.example.test.dto.UserDTO;
import com.example.test.dto.UserResponseDTO;
import com.example.test.models.Pagination;
import com.example.test.models.PaginationLinks;
import com.example.test.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final String apiPath = "http://my-server:8083/api/v2";

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetAllUsersResponse<List<UserResponseDTO>>> getAllUsers(@RequestHeader(CustomHeaders.X_REQUEST_ID) String xRequestId,
                                                                                  @RequestParam(required = false) String nickname,
                                                                                  @RequestParam(required = false) String nif,
                                                                                  @RequestParam(required = false) String status,
                                                                                  @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                                  @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                                                  @RequestParam(required = false, defaultValue = "id") String sortBy)
    {

        if(offset < 0 || limit < 0) throw new IllegalArgumentException();

        Page<UserResponseDTO> userPage = userService.getAllUsers(nickname, nif, status, offset, limit, sortBy);

        List<UserResponseDTO> userResponseDTOS = userPage.stream().toList();

        PaginationLinks links = new PaginationLinks(
                apiPath + "/users",
                apiPath + "/users?offset=" + (offset == 0 ? offset : (offset - 1)) + "&limit=" + limit,
                apiPath + "/users?offset=" + offset + "&limit=" + limit,
                apiPath + "/users?offset=" + (offset + 1) + "&limit=" + limit,
                apiPath + "/users?offset=" + (userPage.getTotalPages() - 1) + "&limit=" + limit
        );

        Pagination pagination = new Pagination(
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                links
        );

        GetAllUsersResponse<List<UserResponseDTO>> getAllUsersResponse = new GetAllUsersResponse<>(userResponseDTOS, pagination);
        return ResponseEntity.
                ok().
                header(CustomHeaders.X_REQUEST_ID, xRequestId).
                body(getAllUsersResponse);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveUser(@RequestHeader(CustomHeaders.X_REQUEST_ID) String xRequestId,
                                         @Valid @RequestBody UserDTO userDTO) {

        var savedUser = userService.saveUser(userDTO);

        return ResponseEntity.
                created(URI.create(apiPath + "/users/" + savedUser.getId())).
                header(CustomHeaders.X_REQUEST_ID, xRequestId).
                build();
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> getUserById(@RequestHeader(CustomHeaders.X_REQUEST_ID) String xRequestId,
                                                       @PathVariable Integer userId) {

        if(userId < 0) throw new IllegalArgumentException();

        UserResponseDTO userResponseDTO = userService.getUserById(userId);

        if (userResponseDTO == null) throw new ContentNotFoundException(ErrorCode.OBJECT_NOT_FOUND);

        return ResponseEntity.
                ok().
                header(CustomHeaders.X_REQUEST_ID, xRequestId).
                body(userResponseDTO);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUserById(@RequestHeader(CustomHeaders.X_REQUEST_ID) String xRequestId,
                                               @PathVariable Integer userId) {

        if(userId < 0) throw new IllegalArgumentException();

        userService.deleteUserById(userId);

        return ResponseEntity.
                noContent().
                header(CustomHeaders.X_REQUEST_ID, xRequestId).
                build();
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateUserById(@RequestHeader(CustomHeaders.X_REQUEST_ID) String xRequestId,
                                               @PathVariable Integer userId,
                                               @Valid @RequestBody UserDTO userDTO) {

        if(userId < 0) throw new IllegalArgumentException();

        userService.updateUserById(userId, userDTO);

        return ResponseEntity.
                noContent().
                header(CustomHeaders.X_REQUEST_ID, xRequestId).
                build();
    }
}
