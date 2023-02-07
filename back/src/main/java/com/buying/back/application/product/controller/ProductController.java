package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.ProductService;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/pub/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "상품에 대한 옵션과 각 옵션별 아이템들을 등록합니다.")
    @PostMapping
    public CommonResponse<ProductDefaultVO> createProduct(@RequestBody ProductDto.Create dto) {
        ProductDefaultVO product = productService.createProduct(dto);
        return new CommonResponse<>(product, CommonResponseCode.SUCCESS);
    }

    @Operation(summary = "상품 수정", description = "상품 정보 수정 아이템은 수정 불가")
    @PostMapping("/{product_id}")
    public CommonResponse<ProductDefaultVO> updateProduct(@PathVariable("product_id") Long productId, @RequestBody ProductDto.Update dto) {
        /*
         * 카테고리는 수정 가능
         * 브랜드는 수정 불가능
         * 옵션과 아이템이 문제
         * 지그재그는 기존 옵션으로 인한 아이템은 삭제 불가 조치 추가만 가능함
         * 기존 옵션에 추가 옵션만 가능
         * 아이템은 재고, 가격, 할인가, 할인율, 상품상태(추가예정) 만 가능하게
         */
        ProductDefaultVO productDefaultVO = productService.updateProduct(productId, dto);
        return new CommonResponse<>(productDefaultVO, CommonResponseCode.SUCCESS);
    }

    @DeleteMapping("/{product_id}")
    public CommonResponse<Product> deleteProduct(@PathVariable("product_id") Long productId) {
        productService.deleteProduct(productId);

        return new CommonResponse<>(null, CommonResponseCode.SUCCESS);
    }
}
