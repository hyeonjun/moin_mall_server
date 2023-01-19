package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}