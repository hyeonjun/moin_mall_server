package com.buying.back.application.product.helper;

import static com.buying.back.application.product.service.ItemService.ITEM_OPTION_DELIMITER;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.service.ItemService;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.OptionVO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductItemHelper {

  private final ItemOptionHelper itemOptionHelper;
  private final ItemRepository itemRepository;

  public List<ItemVO> getItemsByProduct(Product product) {
    return itemRepository.findAllByProduct(product).stream()
      .map(item -> {
        List<OptionVO> itemOptions = itemOptionHelper.getAllOptionByItem(item.getOptionIds());
        return ItemVO.valueOf(item, itemOptions);
      }).collect(Collectors.toList());
  }

  @Transactional
  public List<ItemVO> createItem(Product product, List<ItemDto.Create> itemDtoList) {
    return itemDtoList.stream().map(itemDto -> {
        // 해당 상품에 대한 옵션 생성
        List<OptionVO> options = itemOptionHelper.createAllOption(product, itemDto.getOptionsDto());
        String itemOptions = options.stream()
          .map(option -> String.valueOf(option.getOptionId()))
          .collect(Collectors.joining(ITEM_OPTION_DELIMITER));

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
