package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    public void updateItems(List<ItemDto.Update> itemsDto) {
        // TODO: 2023-02-02 updateItems 작성
    }
}
