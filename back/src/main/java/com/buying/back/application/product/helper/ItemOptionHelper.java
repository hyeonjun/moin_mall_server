package com.buying.back.application.product.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemOptionHelper {

//  private final OptionRepository optionRepository;

//  public List<OptionVO> getAllOptionByItem(Set<Long> optionIds) {
//    return optionRepository.findAllOptionIdIn(optionIds);
//  }

//  @Transactional
//  public List<OptionVO> createAllOption(Product product, List<CreateBrandItemDTO> dto) {
//    List<Option> options = optionRepository.saveAll(optionsDto.stream()
//        .map(dto -> Option.create(dto, product))
//        .collect(Collectors.toList()));
//
//    return options.stream().map(OptionVO::valueOf)
//      .collect(Collectors.toList());
//  }

//  @Transactional
//  public void deleteOptionByProduct(Set<Long> optionIds) {
//    optionRepository.deleteAllByOptionIdsInQuery(optionIds, LocalDateTime.now());
//  }

}
