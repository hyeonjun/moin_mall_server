package com.buying.back.application.product.helper;

import com.buying.back.application.product.controller.dto.OptionDto;
import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.OptionRepository;
import com.buying.back.application.product.service.vo.OptionVO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemOptionHelper {

  private final OptionRepository optionRepository;

  @Transactional
  public List<OptionVO> createOptionAll(Product product, List<OptionDto.Create> optionsDto) {
    List<Option> options = optionRepository.saveAll(optionsDto.stream()
        .map(dto -> Option.create(dto, product))
        .collect(Collectors.toList()));

    return options.stream().map(OptionVO::valueOf)
      .collect(Collectors.toList());
  }

}
