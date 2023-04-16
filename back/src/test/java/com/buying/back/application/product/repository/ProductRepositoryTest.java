package com.buying.back.application.product.repository;

import com.buying.back.application.product.repository.param.SearchProductListParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void findAllByBrand() {
    SearchProductListParam param = new SearchProductListParam();
    param.setDeleted(null);
    param.setBrandId(1L);

    Pageable pageable = PageRequest.of(0, 10);
    productRepository.findAllByBrand(pageable, param);

    /*
      select
          product0_.product_id as col_0_0_,
          product0_.product_name as col_1_0_,
          product0_.product_price as col_2_0_,
          brand1_.brand_id as col_3_0_,
          category2_.category_id as col_4_0_,
          category2_.name as col_5_0_
      from
          product product0_
      inner join
          brand brand1_
              on product0_.brand_id=brand1_.brand_id
      inner join
          category category2_
              on product0_.category_id=category2_.category_id
      where
          product0_.brand_id=?
      order by
          product0_.product_id desc limit ?
     */
  }

}
