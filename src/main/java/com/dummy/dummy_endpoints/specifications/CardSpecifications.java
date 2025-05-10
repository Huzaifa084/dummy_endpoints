package com.dummy.dummy_endpoints.specifications;

import com.dummy.dummy_endpoints.dto.CardFilterDto;
import com.dummy.dummy_endpoints.model.Card;
import com.dummy.dummy_endpoints.model.CardCategory;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Component
public class CardSpecifications {

    private CardSpecifications() {
        // Private constructor to prevent instantiation
    }

    public static Specification<Card> withFilters(CardFilterDto filter) {
        return Specification
                .where(hasTitle(filter.getTitle()))
                .and(hasDescription(filter.getDescription()))
                .and(hasIsActive(filter.getIsActive()))
                .and(hasPriority(filter.getPriority()))
                .and(hasCategoryId(filter.getCategoryId()));
    }

    private static Specification<Card> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Card> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(description)) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + description.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Card> hasIsActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            if (isActive == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("isActive"), isActive);
        };
    }

    private static Specification<Card> hasPriority(Enum<?> priority) {
        return (root, query, criteriaBuilder) -> {
            if (priority == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("priority"), priority);
        };
    }

    private static Specification<Card> hasCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            Join<Card, CardCategory> categoryJoin = root.join("category");
            return criteriaBuilder.equal(categoryJoin.get("id"), categoryId);
        };
    }

//    public static Specification<Card> withFilters(CardFilterDto filterDto) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (StringUtils.hasText(filterDto.getTitle())) {
//                predicates.add(criteriaBuilder.like(
//                        criteriaBuilder.lower(root.get("title")),
//                        "%" + filterDto.getTitle().toLowerCase() + "%"));
//            }
//
//            if (StringUtils.hasText(filterDto.getDescription())) {
//                predicates.add(criteriaBuilder.like(
//                        criteriaBuilder.lower(root.get("description")),
//                        "%" + filterDto.getDescription().toLowerCase() + "%"));
//            }
//
//            if (filterDto.getIsActive() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("isActive"), filterDto.getIsActive()));
//            }
//
//            if (filterDto.getCategoryId() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), filterDto.getCategoryId()));
//            }
//
//            if (filterDto.getStartDate() != null && filterDto.getEndDate() != null) {
//                predicates.add(criteriaBuilder.between(
//                        root.get("createdAt"),
//                        filterDto.getStartDate(),
//                        filterDto.getEndDate()));
//            } else if (filterDto.getStartDate() != null) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
//                        root.get("createdAt"),
//                        filterDto.getStartDate()));
//            } else if (filterDto.getEndDate() != null) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(
//                        root.get("createdAt"),
//                        filterDto.getEndDate()));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
}