package com.btth.service;

import com.btth.model.Product;
import com.btth.repository.ProductRepository;
import com.btth.specification.ProductSpecification;
import com.btth.projection.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getByCategory(String category) {
        return repo.findByCategoryAndStatus(category, true);
    }

    public Page<Product> getHot(int page, int size) {
        if (page < 0) page = 0;
        return repo.findHotProducts(PageRequest.of(page, size));
    }

    public Page<ProductView> mobile(int page, int size) {
        if (page < 0) page = 0;
        return repo.findByStatusTrue(PageRequest.of(page, size));
    }

    public Page<Product> search(String keyword, Double min, Double max, int page, int size) {

        if (page < 0) page = 0;

        if (min != null && max != null && min > max) {
            throw new RuntimeException("Khoảng giá không hợp lệ");
        }

        Specification<Product> spec = Specification
                .where(ProductSpecification.hasName(keyword))
                .and(ProductSpecification.minPrice(min))
                .and(ProductSpecification.maxPrice(max));

        return repo.findAll(spec, PageRequest.of(page, size));
    }
}
