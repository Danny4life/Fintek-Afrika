package com.osiki.finteckafrika.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailServiceModel {

    private String name;
    private String emailAddress;
    private String message;
    private String subject;

    @Override
    public String toString(){
        return "UserDto [name=" + name + ", address = " + emailAddress + ", message = " + message + ", subject = " + subject + "]";
    }
}
