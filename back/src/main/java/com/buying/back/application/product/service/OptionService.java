package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.OptionDto;
import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OptionService {
    private final OptionRepository optionRepository;

    @Transactional
    public Option create(Product product, OptionDto.Create dto) {
        Option option = Option.create(dto);
        option.setProduct(product);
        return optionRepository.save(option);
    }

    @Transactional
    public List<Option> createAll(Product product, List<OptionDto.Create> optionsDto) {
        return optionRepository.saveAll(optionsDto.stream()
                .map(optionDto -> {
                    Option option = Option.create(optionDto);
                    option.setProduct(product);
                    return option;
                })
                .collect(Collectors.toList()));
    }


}
