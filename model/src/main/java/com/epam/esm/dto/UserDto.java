package com.epam.esm.dto;

import com.epam.esm.Role;
import com.epam.esm.Status;
import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends EntityDto<Long, UserDto> {

    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.USER_NAME, message = Message.USER_NAME)
    private String name;
    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.USER_SURNAME, message = Message.USER_SURNAME)
    private String surname;
    @Null(message = Message.DATE_CANNOT_DEFINE)
    private Instant createDate;
    @Null(message = Message.DATE_CANNOT_DEFINE)
    private Instant lastUpdateDate;

    @NotBlank(message = Message.NOT_BE_BLANK)
    @Pattern(regexp = Regex.PASSWORD, message = Message.PASSWORD)
    private String password;

    @NotNull(message = Message.MUST_BE_FILLED)
    @Email(message = Message.USER_USERNAME)
    private String username;
    @Null
    private Role role;
    @Null
    private Status status;
}
