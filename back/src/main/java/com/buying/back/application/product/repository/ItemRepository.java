package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteByProduct(Product product);
}