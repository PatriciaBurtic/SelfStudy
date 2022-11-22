package com.pitechplus.selfstudy.model.dao.specification;

import com.pitechplus.selfstudy.model.dao.specification.criteria.SearchCriteria;
import com.pitechplus.selfstudy.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserSpecification implements Specification<User> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperation()) {

            case GT -> {
                return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case GE -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case LT -> {
                return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case LE -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case E -> {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case NE -> {
                return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            case LIKE -> {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue().toString());
                }
            }
            default -> {
                return null;
            }
        }
    }
}
