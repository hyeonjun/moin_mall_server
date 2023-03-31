package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import com.buying.back.application.product.service.vo.ItemDetailVO;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductItemHelperService {
    private final OptionService optionService;
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    public List<ItemDetailVO> getItemsByProduct(Product product) {
        List<Item> items = itemRepository.findAllByProduct(product);

        return items.stream().map(item -> {
            Set<Long> optionIds = item.getOptionIds();
            List<ItemOptionVO> itemOptions = optionService.getItemOptions(optionIds);

            return new ItemDetailVO(item, itemOptions);
        }).collect(Collectors.toList());
    }

    public Item createItem(Product product, ItemDto.Create dto) {
        return itemService.createItem(product, dto);
    }

    public ItemDefaultVO updateItem(ItemDto.Update itemDto) {
        return itemService.updateItem(itemDto);
    }

    public void deleteItemByProduct(Product product) {
        itemService.deleteByProduct(product);
    }
}
