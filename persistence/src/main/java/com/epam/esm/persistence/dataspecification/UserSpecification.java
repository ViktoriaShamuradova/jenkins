package com.epam.esm.persistence.dataspecification;

import com.epam.esm.criteria_info.UserCriteriaInfo;
import com.epam.esm.entity.User;
import com.epam.esm.entity.metamodel.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

    private final UserCriteriaInfo criteriaInfo;
    private final List<Predicate> conditions = new ArrayList<>();

    public UserSpecification(UserCriteriaInfo criteriaInfo) {
        this.criteriaInfo = criteriaInfo;
    }

    public static Specification<User> nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.NAME), name);
    }

    public static Specification<User> surnameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.SURNAME), name);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        addPredicateName(root, query, criteriaBuilder);
        addPredicateSurname(root, query, criteriaBuilder);

        return finalPredicate(criteriaBuilder);
    }

    private void addPredicateSurname(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getSurname() == null) return;
        conditions.add(surnameEquals(criteriaInfo.getSurname()).toPredicate(root, query, criteriaBuilder));
    }

    private void addPredicateName(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteriaInfo.getName() == null) return;
        conditions.add(nameEquals(criteriaInfo.getName()).toPredicate(root, query, criteriaBuilder));
    }

    private Predicate finalPredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(conditions.toArray(new Predicate[0]));
    }
}
