package com.epam.esm.service;

import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.dto.TagDto;

import java.util.List;

public interface TagService extends CrudService<TagDto, Long, TagCriteriaInfo> {

    TagDto find(String name);

    boolean delete(String name);

    List<TagDto> getMostUsedTagOfUserWithHighestCostOfOrders();
}
