package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    void deleteByProduct(Product product);
    List<Option> findDistinctByProduct(Product product);
}
