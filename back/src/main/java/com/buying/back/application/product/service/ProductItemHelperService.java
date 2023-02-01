package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductItemHelperService {
    private final ItemService itemService;

    public void updateItems(List<ItemDto.Update> dto) {
        itemService.updateItems(dto);
    }
}
