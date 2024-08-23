package com.project.ecommerce.specification;

import com.project.ecommerce.model.ProductModel;
import com.project.ecommerce.specification.criteria.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecification implements Specification<ProductModel> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.ge(
                    root.get(criteria.getKey()), (Number) criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.le(
                    root.get(criteria.getKey()), (Number) criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("%")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
