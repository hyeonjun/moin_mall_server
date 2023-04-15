package com.buying.back.application.product.controller;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.ItemService;
import com.buying.back.application.product.service.helper.ItemProductHelper;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pub/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemProductHelper itemProductHelper;

    @GetMapping("/{product-id}")
    public CommonResponse<List<ItemDefaultVO>> getAllByProduct(@PathVariable("product-id") Long productId) {
        Product product = itemProductHelper.getProduct(productId);

        List<ItemDefaultVO> allByProduct = itemService.getAllByProduct(product);
        return new CommonResponse<>(allByProduct, CommonResponseCode.SUCCESS);
    }
    @PostMapping("/{item-id}")
    public CommonResponse<ItemDefaultVO> updateItem(@PathVariable("item-id") Long itemId, @RequestBody ItemDto.Update update) {
        ItemDefaultVO itemDefaultVO = itemService.updateItem(update);
        return new CommonResponse<>(itemDefaultVO, CommonResponseCode.SUCCESS);
    }
}
