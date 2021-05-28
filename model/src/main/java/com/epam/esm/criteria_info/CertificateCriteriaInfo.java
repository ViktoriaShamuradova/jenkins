package com.epam.esm.criteria_info;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Class containing information about certificate from url to build desired query
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CertificateCriteriaInfo extends CriteriaInfo {

    private List <@Pattern(regexp = Regex.TAG_NAME, message = Message.TAG_NAME)String> tagNames;

    @Pattern(regexp = Regex.CERTIFICATE_DESCRIPTION, message = Message.CERTIFICATE_DESCRIPTION_OR_NAME)
    private String partOfNameOrDescription;

    @Pattern(regexp = Regex.CERTIFICATE_NAME, message = Message.CERTIFICATE_NAME)
    private String name;
}
