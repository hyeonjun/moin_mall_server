package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByProduct(Product product);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Item item SET item.deleted = true, item.deletedAt = :now WHERE item.product.id = :productId")
    void deleteAllByProductIdQuery(@Param("productId") Long productId, @Param("now") LocalDateTime now);
/*
    @Query(value = "select item from Item item where item.product = :product")
    List<ItemDefaultVO> findItemsByProductId(@Param("product") Product product);

    @Query(value = "select item From Item item where item.product = :product")
    List<Item> findAllByProductId(@Param("product") Product product);*/
}