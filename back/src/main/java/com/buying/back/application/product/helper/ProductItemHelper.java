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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
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

  @Async
  @Transactional // 여기서 실패되어도 상위 메서드는 롤백되지 않음을 주의
  public void deleteItemByProduct(Product product) {
    List<Item> items = itemRepository.findAllByProduct(product);
    items.stream()
      .peek(Item::deleteItem) // 아이템 삭제
      .forEach(item -> itemOptionHelper.deleteOptionByProduct(item.getOptionIds())); // 아이템에 대한 옵션 삭제
    itemRepository.saveAll(items);
  }
}
