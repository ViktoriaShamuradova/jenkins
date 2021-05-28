package com.epam.esm.persistence.dataspecification;

import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.metamodel.Tag_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TagSpecification implements Specification<Tag> {

    private final TagCriteriaInfo criteriaInfo;
    private final List<Predicate> conditions = new ArrayList<>();

    public TagSpecification(TagCriteriaInfo criteriaInfo) {
        this.criteriaInfo = criteriaInfo;
    }

    public static Specification<Tag> nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Tag_.NAME), name);
    }

    @Override
    public Predicate toPredicate(Root<Tag> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        addPredicateName(root, query, criteriaBuilder);
        if (conditions.isEmpty())return null;
        return conditions.get(0);
    }

    private void addPredicateName(Root<Tag> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getName() == null) return;
        conditions.add(nameEquals(criteriaInfo.getName()).toPredicate(root, query, criteriaBuilder));
    }
}
