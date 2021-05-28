package com.epam.esm.persistence.impl;

import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.entity.Tag;
import com.epam.esm.persistence.TagDao;
import com.epam.esm.persistence.dataspecification.TagSpecification;
import com.epam.esm.persistence.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagDaoImpl implements TagDao {

    private final TagRepository tagRepository;
    private final EntityManager entityManager;

    private final static String SELECT_USED_TAG = "SELECT t.name, t.id, count(*) as count FROM mjs.orders AS o " +
            "JOIN mjs.order_items ot ON ot.id_order=o.id " +
            "JOIN mjs.certificates_tags ct ON ct.certificates_id=ot.id_certificate " +
            "JOIN mjs.tags t ON t.id=ct.tags_id " +
            "WHERE o.total_sum=(SELECT MAX(total_sum) FROM mjs.orders) GROUP BY t.name, t.id ORDER BY count(*) DESC ";

    @Override
    public Page<Tag> findAll(TagCriteriaInfo criteriaInfo, Pageable pageable) {
        TagSpecification specification = new TagSpecification(criteriaInfo);
        return tagRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Tag> find(TagCriteriaInfo criteriaInfo) {
        TagSpecification specification = new TagSpecification(criteriaInfo);
        return tagRepository.findOne(specification);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public Map<Tag, Integer> getTagsOfUserWithHighestCostOfOrders() {
        Map<Tag, Integer> results = new HashMap<>();
        List<Object[]> resultList = entityManager.createNativeQuery(SELECT_USED_TAG).getResultList();
        for (Object[] borderTypes : resultList) {
            BigInteger id = (BigInteger) borderTypes[1];
            String name = (String) borderTypes[0];
            Tag t = new Tag(id.longValue(), name);
            BigInteger count = (BigInteger) borderTypes[2];
            results.put(t, count.intValue());
        }
        return results;
    }
}
