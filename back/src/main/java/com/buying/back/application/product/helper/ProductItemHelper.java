package com.buying.back.application.product.helper;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.service.ItemService;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.OptionVO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductItemHelper {

  private final ItemOptionHelper itemOptionHelper;
  private final OptionService optionService;
  private final ItemService itemService;
  private final ItemRepository itemRepository;

  public List<ItemVO> getItemsByProduct(Product product) {
    List<Item> items = itemRepository.findAllByProduct(product);

    return null;
//      items.stream().map(item -> {
//      Set<Long> optionIds = item.getOptionIds();
//      List<ItemOptionVO> itemOptions = optionService.getItemOptions(optionIds);
//
//      return new ItemDetailVO(item, itemOptions);
//    }).collect(Collectors.toList());
  }

  public List<ItemVO> createItem(Product product, List<ItemDto.Create> itemDtoList) {
    return itemDtoList.stream().map(itemDto -> {
        // 해당 상품에 대한 옵션 생성
        List<OptionVO> options = itemOptionHelper.createOptionAll(product, itemDto.getOptionsDto());
        String itemOptions = options.stream()
          .map(option -> String.valueOf(option.getId()))
          .collect(Collectors.joining(","));

        // 상품에 대한 아이템 생성
        Item item = Item.create(itemDto, product, itemOptions);
        itemRepository.save(item);

        return ItemVO.valueOf(item, options);
      }).collect(Collectors.toList());
  }

  public ItemVO updateItem(ItemDto.Update itemDto) {
    return null; // itemService.updateItem(itemDto);
  }

  public void deleteItemByProduct(Product product) {
    itemService.deleteByProduct(product);
  }
}
