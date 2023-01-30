package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}