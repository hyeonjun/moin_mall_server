package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.OptionDto;
import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.service.helper.OptionProductHelper;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pub/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;
    private final OptionProductHelper optionProductHelper;

    @GetMapping("/{product-id}")
    public CommonResponse<List<OptionDefaultVO>> getProductOptions(@PathVariable("product-id") Long productId) {
        Product product = optionProductHelper.getProduct(productId);
        List<OptionDefaultVO> productOptions = optionService.getProductOptions(product);
        return new CommonResponse<>(productOptions, CommonResponseCode.SUCCESS);
    }

    @PostMapping
    public CommonResponse<OptionDefaultVO> createProductOptions(@RequestParam Long productId, @RequestBody OptionDto.Create create) {
        Product product;
        OptionDefaultVO optionDefaultVO;

        if (productId != null) {
            product = optionProductHelper.getProduct(productId);
            optionDefaultVO = optionService.create(product, create);
        } else {
            optionDefaultVO = optionService.create(create);
        }

        return new CommonResponse<>(optionDefaultVO, CommonResponseCode.SUCCESS);
    }
}
