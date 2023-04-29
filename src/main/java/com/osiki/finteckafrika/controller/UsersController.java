package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.model.AuthResponse;
import com.osiki.finteckafrika.model.LoginRequestPayload;
import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.response.UserResponse;
import com.osiki.finteckafrika.service.LoginService;
import com.osiki.finteckafrika.service.RegistrationService;
import com.osiki.finteckafrika.service.UsersService;
import com.osiki.finteckafrika.service.serviceImpl.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UsersService usersService;
    private final RegistrationServiceImpl registrationService;

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequestPayload loginRequestPayload) throws Exception{
        String token = loginService.authenticate(loginRequestPayload);
        AuthResponse authResponse = new AuthResponse(token);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<String> createUserAccount(@Valid @RequestBody UserRegistrationRequestModel
                                                            registrationRequestModel) throws JSONException {
        return new ResponseEntity<>(registrationService.createUser(registrationRequestModel), HttpStatus.CREATED);

    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token){
        return new ResponseEntity<>(registrationService.confirmToken(token), HttpStatus.OK);

    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponse> getUser(){
        UserResponse userResponse = usersService.getUser();

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


}
