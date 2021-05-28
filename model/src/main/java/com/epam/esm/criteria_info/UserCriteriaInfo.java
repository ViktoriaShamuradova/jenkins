package com.epam.esm.criteria_info;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserCriteriaInfo extends CriteriaInfo {

    @Pattern(regexp = Regex.USER_NAME, message = Message.USER_NAME)
    private String name;

    @Pattern(regexp = Regex.USER_SURNAME, message = Message.USER_SURNAME)
    private String surname;
}

