package com.btth.specification;

import com.btth.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasName(String keyword) {
        return (root, query, cb) ->
                keyword == null ? null :
                        cb.like(root.get("productName"), "%" + keyword + "%");
    }

    public static Specification<Product> minPrice(Double min) {
        return (root, query, cb) ->
                min == null ? null :
                        cb.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Product> maxPrice(Double max) {
        return (root, query, cb) ->
                max == null ? null :
                        cb.lessThanOrEqualTo(root.get("price"), max);
    }
}
