package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.ProductService;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/pub/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "상품에 대한 옵션과 각 옵션별 아이템들을 등록합니다.")
    @PostMapping
    public CommonResponse<Product> createProduct(@RequestBody ProductDto.Create dto) {
        log.info("???????어디로 가고 있냐");
        log.info("{}", dto);
        Product product = productService.createProduct(dto);
        return new CommonResponse<>(product, CommonResponseCode.SUCCESS);
    }

    @Operation(summary = "상품 수정", description = "상품 정보 수정 아이템은 수정 불가")
    @PostMapping("/{product_id}")
    public CommonResponse<Product> updateProduct(@PathVariable("product_id") Long productId, @RequestBody ProductDto.Update dto) {
        /*
         * 카테고리는 수정 가능 = 오입력
         * 브랜드는 수정 불가능
         * 옵션과 아이템이 문제
         * 지그재그는 기존 옵션으로 인한 아이템은 삭제 불가 조치 추가만 가능함
         * 위의 이유로 옵션 삭제 불가능하고
         * 옵션 추가만 가능
         */
        return new CommonResponse<>(null, CommonResponseCode.SUCCESS);
    }
}
