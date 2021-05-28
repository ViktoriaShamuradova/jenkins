package com.epam.esm.dto;

import com.epam.esm.constant.Message;
import com.epam.esm.constant.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("FieldMayBeFinal")
public class CertificateUpdateDto {

    @NotNull
    @Positive
    @Min(Regex.MIN_ID)
    private JsonNullable<Long> id = JsonNullable.undefined();

    @NotNull
    @Pattern(regexp = Regex.CERTIFICATE_NAME, message =Message.CERTIFICATE_NAME )
    private JsonNullable<String> name = JsonNullable.undefined();

    @Pattern(regexp = Regex.CERTIFICATE_DESCRIPTION, message = Message.CERTIFICATE_DESCRIPTION)
    private JsonNullable<String> description = JsonNullable.undefined();

    @NotNull
    @Min(Regex.MIN_CERTIFICATE_DURATION)
    @Max(Regex.MAX_CERTIFICATE_DURATION)
    private JsonNullable<Integer> duration = JsonNullable.undefined();

    @NotNull
    @Digits(integer = Regex.CERTIFICATE_PRICE_INTEGER, fraction = Regex.CERTIFICATE_PRICE_FRACTION)
    @DecimalMin(value = "0", message = Message.ENTER_PRICE)
    private JsonNullable<BigDecimal> price = JsonNullable.undefined();

    private JsonNullable<Set<@Pattern(regexp = Regex.TAG_NAME, message = Message.TAG_NAME) TagDto>> tags = JsonNullable.undefined();
}
