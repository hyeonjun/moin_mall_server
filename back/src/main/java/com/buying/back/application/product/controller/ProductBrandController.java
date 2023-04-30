package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.controller.dto.brand.CreateBrandProductDTO;
import com.buying.back.application.product.service.ProductService;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.ProductItemVO;
import com.buying.back.application.product.service.vo.ProductVO;
import com.buying.back.infra.config.security.loginuser.LoginUser;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/brd/products")
@RequiredArgsConstructor
public class ProductBrandController {

  private final ProductService productService;

  // 상품 단건 조회 시 동시에 해당 상품의 아이템을 조회 -> 아이템: 아이템 정보 + 옵션 정보
  @Operation(summary = "상품 단건 조회", description = "상품 정보만 조회")
  @GetMapping("/{product-id}")
  public CommonResponse<ProductVO> getProduct(
    @AuthenticationPrincipal LoginUser loginUser, @PathVariable("product-id") Long productId) {
    ProductVO vo = productService.getProduct(loginUser.getBrandId(), productId);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

  @GetMapping("/{product-id}/items")
  public CommonResponse<ProductItemVO> getProductItem(
    @AuthenticationPrincipal LoginUser loginUser, @PathVariable("product-id") Long productId) {
    ProductItemVO vo = productService.getProductItem(loginUser.getBrandId(), productId);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

  @GetMapping
  public CommonResponse<Page<ProductVO>> getProductList(
    @AuthenticationPrincipal LoginUser loginUser, ProductDto.Search dto) {
    Page<ProductVO> page = productService.getProductList(loginUser.getBrandId(), dto);
    return new CommonResponse<>(page, CommonResponseCode.SUCCESS);
  }

  @Operation(summary = "상품 등록", description = "상품에 대한 옵션과 각 옵션별 아이템들을 등록합니다.")
  @PostMapping
  public CommonResponse<ProductItemVO> createProduct(
    @AuthenticationPrincipal LoginUser loginUser, @RequestBody @Valid CreateBrandProductDTO dto) {
    ProductItemVO vo = productService.createProduct(loginUser.getBrandId(), dto);
    return new CommonResponse<>(vo, CommonResponseCode.SUCCESS);
  }

  @Operation(summary = "상품 수정", description = "상품 정보 수정")
  @PutMapping("/{product-id}")
  public CommonResponse<ProductVO> updateProduct( // 상품의 정보만 수정하는 API
    @AuthenticationPrincipal LoginUser loginUser,
    @PathVariable(value = "product-id") Long productId, @RequestBody @Valid ProductDto.Update dto) {
    /*
     * 상품 정보 수정
     * 내부 아이템 수정
      * 아이템 정보 수정
      * 아이템의 옵션 정보에 대해 수정 / 옵션 추가 가능 / 옵션 삭제는 따로
        * 수정 시 아이디는 그대로니까 수정 가능
        * 추가 시 기존 String.concat(,Id)
        * 삭제 시 해당 아이템 아이디를 받아서 해야된다
     * 아이템은 재고, 가격, 할인가, 할인율, 상품상태(추가예정) 만 가능하게
     */
    ProductVO productVO = productService.updateProduct(loginUser.getBrandId(), productId, dto);
    return new CommonResponse<>(productVO, CommonResponseCode.SUCCESS);
  }

  // 상품의 아이템 단일 수정
  @PutMapping("/{product-id}/items/{item-id}")
  public CommonResponse<ItemVO> updateItem(
    @AuthenticationPrincipal LoginUser loginUser,
    @PathVariable(value = "product-id") Long productId,
    @PathVariable(value = "item-id") Long itemId, @RequestBody @Valid ItemDto.Update dto) {
    ItemVO itemVO = null; // productService.updateItem(loginUser.getBrandId(), productId, itemId, dto);
    return new CommonResponse<>(itemVO, CommonResponseCode.SUCCESS);
  }

  // 상품의 아이템들 추가

  // 상품의 아이템의 옵션 단일 수정

  // 상품의 아이템 단일 삭제

  // 상품의 아이템에 옵션 추가

  // 상품의 아이템의 옵션 단일 수정



  @Operation(summary = "상품 삭제", description = "상품에 대한 옵션들과 각 옵션별 아이템들을 삭제합니다.")
  @DeleteMapping("/{product-id}")
  public CommonResponse<Void> deleteProduct(
    @AuthenticationPrincipal LoginUser loginUser, @PathVariable(value = "product-id") Long productId) {
    productService.deleteProduct(loginUser.getBrandId(), productId);
    return new CommonResponse<>(CommonResponseCode.SUCCESS);
  }
}
