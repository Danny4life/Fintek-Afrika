package com.osiki.finteckafrika.controller;

import com.osiki.finteckafrika.model.UserRegistrationRequestModel;
import com.osiki.finteckafrika.service.RegistrationService;
import com.osiki.finteckafrika.service.UsersService;
import com.osiki.finteckafrika.service.serviceImpl.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UsersService usersService;
    private final RegistrationServiceImpl registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> createUserAccount(@Valid @RequestBody UserRegistrationRequestModel
                                                            registrationRequestModel) throws JSONException {
        return new ResponseEntity<>(registrationService.createUser(registrationRequestModel), HttpStatus.CREATED);

    }
}
