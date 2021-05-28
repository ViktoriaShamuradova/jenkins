package com.epam.esm.criteria_info;

import com.epam.esm.constant.Regex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CriteriaInfo {
    @Positive
    @Min(Regex.MIN_ID)
    private Long id;
}
