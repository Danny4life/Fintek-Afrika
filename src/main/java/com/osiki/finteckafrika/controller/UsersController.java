package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
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


    @PostMapping("/register")
    public ResponseEntity<String> createUserAccount(@Valid @RequestBody UserRegistrationRequestModel
                                                            registrationRequestModel) throws JSONException {
        return new ResponseEntity<>(registrationService.createUser(registrationRequestModel), HttpStatus.CREATED);

    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token){
        return new ResponseEntity<>(registrationService.confirmToken(token), HttpStatus.OK);

    }


}
