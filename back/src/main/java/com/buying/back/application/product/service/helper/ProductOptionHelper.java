package com.buying.back.application.product.service.helper;

import com.buying.back.application.product.controller.dto.OptionDto;
import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.OptionRepository;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductOptionHelper {
    private final OptionService optionService;
    private final OptionRepository optionRepository;

    public List<OptionDefaultVO> getProductOptions(Product product) {
        return optionRepository.findDistinctNameByProduct(product);
    }

    public List<ItemOptionVO> getItemOptions(Set<Long> ids) {
        return optionRepository.findOptionsByIdIn(ids);
    }

    @Transactional
    public Option createOption(Product product, OptionDto.Create optionDto) {
        Option option = Option.create(optionDto);
        option.setProduct(product);
        return optionRepository.save(option);
    }

    @Transactional
    public List<Option> createOptionAll(Product product, List<OptionDto.Create> optionsDto) {
        List<Option> optionList = optionsDto.stream()
                .map(optionDto -> {
                    Option option = Option.create(optionDto);
                    option.setProduct(product);
                    return option;
                })
                .collect(Collectors.toList());
        return optionRepository.saveAll(optionList);
    }
}
