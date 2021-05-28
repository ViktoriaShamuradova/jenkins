package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagDto extends EntityDto<Long, TagDto> {

    @Pattern(regexp = Regex.TAG_NAME, message = Message.TAG_NAME)
    private String name;
}
