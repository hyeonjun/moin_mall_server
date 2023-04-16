package com.buying.back.application.product.controller;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.helper.OptionProductHelper;
import com.buying.back.application.product.service.vo.OptionVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/brd/options")
@RequiredArgsConstructor
public class OptionBrandController {
    private final OptionService optionService;
    private final OptionProductHelper optionProductHelper;

//    @GetMapping("/{product-id}")
//    public CommonResponse<List<OptionVO>> getProductOptions(@PathVariable("product-id") Long productId) {
//        Product product = optionProductHelper.getProduct(productId);
//        List<OptionVO> productOptions = optionService.getProductOptions(product);
//        return new CommonResponse<>(productOptions, CommonResponseCode.SUCCESS);
//    }

//    @PostMapping
//    public CommonResponse<OptionVO> createProductOptions(@RequestParam Long productId, @RequestBody OptionDto.Create create) {
//        Product product;
//        OptionVO optionVo;
//
//        if (productId != null) {
//            product = optionProductHelper.getProduct(productId);
//            optionVo = optionService.create(product, create);
//        } else {
//            optionVo = optionService.create(create);
//        }
//
//        return new CommonResponse<>(optionVo, CommonResponseCode.SUCCESS);
//    }
}
