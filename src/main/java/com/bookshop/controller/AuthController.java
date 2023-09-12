package com.bookshop.controller;

import com.bookshop.dto.user.UserLoginRequestDto;
import com.bookshop.dto.user.UserLoginResponseDto;
import com.bookshop.dto.user.UserRegistrationRequestDto;
import com.bookshop.dto.user.UserRegistrationResponseDto;
import com.bookshop.exception.RegistrationException;
import com.bookshop.res.Openapi;
import com.bookshop.security.AuthenticationService;
import com.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private static final String INCORRECT_DATA = Openapi.INCORRECT_DATA;

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "JWT token was generated successfully",
            content = {@Content(mediaType = "application/json",
                    examples = {@ExampleObject(value = INCORRECT_DATA)}
                    )}
            ),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = {@Content(mediaType = "application/json",
                    examples = {@ExampleObject(value = INCORRECT_DATA)}
                    )}
            )
    })
    @Operation(summary = "login method",
            description = "returns you a JWT token if authentication was successful")
    @PostMapping("/login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "registration was successful"),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = {@Content(mediaType = "application/json",
                    examples = {@ExampleObject(value = INCORRECT_DATA)}
                    )}
            )
    })
    @Operation(summary = "register", description = "register a new user in system")
    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }
}