package com.buying.back.application.product.service;

import com.buying.back.application.product.service.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OptionService {
//    private final OptionRepository optionRepository;


    public List<ItemVO> getItemOptions(Set<Long> ids) {
        return null; // optionRepository.findOptionsByIdIn(ids);
    }

//    @Transactional
//    public OptionVO create(OptionDto.Create dto) {
//        Option option = Option.create(dto);
//
//        optionRepository.save(option);
//
//        return OptionVO.valueOf(option);
//    }

//    @Transactional
//    public OptionVO create(Product product, OptionDto.Create dto) {
//        Option option = Option.create(dto);
//        option.setProduct(product);
//
//        optionRepository.save(option);
//
//        return OptionVO.valueOf(option);
//    }

//    @Transactional
//    public List<Option> createAll(Product product, List<OptionDto.Create> optionsDto) {
//        return optionRepository.saveAll(optionsDto.stream()
//                .map(optionDto -> {
//                    return Option.create(optionDto);
//                })
//                .collect(Collectors.toList()));
//    }

//    @Transactional
//    public void deleteByProduct(Product product) {
//        optionRepository.deleteByProduct(product);
//    }
}
