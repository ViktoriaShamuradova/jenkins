package com.epam.esm.security.model;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = Message.MUST_BE_FILLED)
    @Email(message = Message.USER_USERNAME)
    private String email;

    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.PASSWORD, message = Message.PASSWORD)
    private String password;
}