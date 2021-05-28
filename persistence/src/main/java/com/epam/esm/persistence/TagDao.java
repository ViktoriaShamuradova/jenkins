package com.epam.esm.persistence;

import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface TagDao {

    Page<Tag> findAll(TagCriteriaInfo criteriaInfo, Pageable pageable);

    Optional<Tag> find(TagCriteriaInfo criteriaInfo);

    Tag save(Tag tag);

    Optional<Tag> findById(Long id);

    void delete(Tag tag);

    Map<Tag, Integer> getTagsOfUserWithHighestCostOfOrders();
}
