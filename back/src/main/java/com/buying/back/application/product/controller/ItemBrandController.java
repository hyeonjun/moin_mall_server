package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.ItemService;
import com.buying.back.application.product.helper.ItemProductHelper;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/brd/items")
@RequiredArgsConstructor
public class ItemBrandController {

  private final ItemService itemService;
  private final ItemProductHelper itemProductHelper;

  @GetMapping("/{product-id}")
  public CommonResponse<List<ItemVO>> getAllByProduct(
    @PathVariable("product-id") Long productId) {
    Product product = itemProductHelper.getProduct(productId);

    List<ItemVO> allByProduct = itemService.getAllByProduct(product);
    return new CommonResponse<>(allByProduct, CommonResponseCode.SUCCESS);
  }

  @PostMapping("/{item-id}")
  public CommonResponse<ItemVO> updateItem(@PathVariable("item-id") Long itemId,
    @RequestBody ItemDto.Update update) {
    ItemVO itemVO = null; // itemService.updateItem(update);
    return new CommonResponse<>(itemVO, CommonResponseCode.SUCCESS);
  }
}
