package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationUserDto {

    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.USER_NAME, message = Message.USER_NAME)
    private String name;
    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.USER_SURNAME, message = Message.USER_SURNAME)
    private String surname;
    @NotNull(message = Message.MUST_BE_FILLED)
    @Email(message = Message.USER_USERNAME)
    private String email;
    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.PASSWORD, message = Message.PASSWORD)
    private String password;
}
