package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OptionRepository extends JpaRepository<Option, Long>, OptionRepositoryCustom {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Option option SET option.deleted = true, option.deletedAt = :now WHERE option.id in :optionIds")
    void deleteAllByOptionIdsInQuery(Set<Long> optionIds, LocalDateTime now);
}
