package com.buying.back.application.product.helper;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductOptionHelper {

  private final OptionService optionService;

//  public List<OptionVO> getProductOptions(Product product) {
//    return optionService.getProductOptions(product);
//  }

//  public List<Option> createOptionAll(Product product, List<OptionDto.Create> optionsDto) {
//    return optionService.createAll(product, optionsDto);
//  }

//  public void deleteOptionByProduct(Product product) {
//    optionService.deleteByProduct(product);
//  }
}
