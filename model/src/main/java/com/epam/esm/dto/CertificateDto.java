package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"tags"})
public class CertificateDto extends EntityDto<Long, CertificateDto>{

    @Pattern(regexp = Regex.CERTIFICATE_NAME, message = Message.CERTIFICATE_NAME)
    private String name;

    @Pattern(regexp = Regex.CERTIFICATE_DESCRIPTION, message = Message.CERTIFICATE_DESCRIPTION)
    private String description;

    @Digits(integer = Regex.CERTIFICATE_PRICE_INTEGER, fraction = Regex.CERTIFICATE_PRICE_FRACTION)
    @DecimalMin(value = "0", message = Message.ENTER_PRICE)
    private BigDecimal price;

    @Min(Regex.MIN_CERTIFICATE_DURATION)
    @Max(Regex.MAX_CERTIFICATE_DURATION)
    private Integer duration;

    @JsonFormat(pattern = Regex.CERTIFICATE_DATE, timezone = Regex.TIMEZONE)
    @Null(message = Message.DATE_CANNOT_DEFINE)
    private Instant createDate;

    @JsonFormat(pattern = Regex.CERTIFICATE_DATE, timezone = Regex.TIMEZONE)
    @Null(message = Message.DATE_CANNOT_DEFINE)
    private Instant updateLastDate;

    private Set<TagDto> tags = new HashSet<>();

    @JsonCreator
    public CertificateDto() {
    }
}
