package com.epam.esm.criteria_info;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TagCriteriaInfo extends CriteriaInfo {

    @Pattern(regexp = Regex.TAG_NAME, message = Message.TAG_NAME)
    private String name;

    public TagCriteriaInfo(){}

}
