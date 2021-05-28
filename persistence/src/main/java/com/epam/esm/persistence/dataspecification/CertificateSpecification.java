package com.epam.esm.persistence.dataspecification;

import com.epam.esm.criteria_info.CertificateCriteriaInfo;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.metamodel.Certificate_;
import com.epam.esm.entity.metamodel.Tag_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CertificateSpecification implements Specification<Certificate> {

    private final CertificateCriteriaInfo criteriaInfo;
    private final List<Predicate> conditions = new ArrayList<>();

    public CertificateSpecification(CertificateCriteriaInfo criteriaInfo) {
        this.criteriaInfo = criteriaInfo;
    }

    public static Specification<Certificate> nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Certificate_.NAME), name);
    }

    public static Specification<Certificate> nameOrDescriptionLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.like(root.get(Certificate_.NAME), "%" + name + "%"),
                criteriaBuilder.like(root.get(Certificate_.DESCRIPTION), "%" + name + "%"));
    }

    public static Specification<Certificate> tagNameEqual(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal((root.join(Certificate_.TAGS).get(Tag_.NAME)), name.trim());
    }

    public static Specification<Certificate> tagNamesEqual(List<String> tagNames) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = tagNames.stream()
                    .map(name -> tagNameEqual(name).toPredicate(root, query, criteriaBuilder))
                    .collect(Collectors.toList());

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<Certificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        addPredicateName(root, query, criteriaBuilder);
        addPredicateNameOrDescription(root, query, criteriaBuilder);
        addPredicateTagNames(root, query, criteriaBuilder);
        System.out.println(conditions);
        return finalPredicate(criteriaBuilder);
    }

    private Predicate finalPredicate(CriteriaBuilder criteriaBuilder) {
        Predicate and = criteriaBuilder.and(conditions.toArray(new Predicate[0]));
        System.out.println(and);
        return and;
    }

    private void addPredicateTagNames(Root<Certificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getTagNames() == null || criteriaInfo.getTagNames().isEmpty()) return;
        conditions.add(tagNamesEqual(criteriaInfo.getTagNames()).toPredicate(root, query, criteriaBuilder));

    }

    private void addPredicateNameOrDescription(Root<Certificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getPartOfNameOrDescription() == null) return;
        conditions.add(nameOrDescriptionLike(criteriaInfo.getPartOfNameOrDescription()).toPredicate(root, query, criteriaBuilder));
    }

    private void addPredicateName(Root<Certificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getName() == null) return;
        conditions.add(nameEquals(criteriaInfo.getName()).toPredicate(root, query, criteriaBuilder));
    }
}
