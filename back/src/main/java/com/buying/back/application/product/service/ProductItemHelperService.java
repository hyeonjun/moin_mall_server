package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductItemHelperService {
    private final ItemService itemService;

    public Item createItem(Product product, ItemDto.Create dto) {
        return itemService.createItem(product, dto);
    }

    public void updateItems(List<ItemDto.Update> dto) {
        itemService.updateItems(dto);
    }
}
