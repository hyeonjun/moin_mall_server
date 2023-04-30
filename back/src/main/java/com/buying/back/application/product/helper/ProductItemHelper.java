package com.buying.back.application.product.helper;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.brand.CreateBrandItemDTO;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.service.vo.ItemVO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductItemHelper {

  private final ItemOptionHelper itemOptionHelper;
  private final ItemRepository itemRepository;

//  public List<ItemVO> getItemsByProduct(Product product) {
//    return itemRepository.findAllByProduct(product).stream()
//      .map(item -> {
//        List<OptionVO> itemOptions = itemOptionHelper.getAllOptionByItem(item.getOptionIds());
//        return ItemVO.valueOf(item, itemOptions);
//      }).collect(Collectors.toList());
//  }

  @Transactional
  public List<ItemVO> createItem(Product product, List<CreateBrandItemDTO> itemList) {
    List<Item> items = itemList.stream()
      .map(dto -> Item.create(dto, product))
      .collect(Collectors.toList());

    itemRepository.saveAll(items);
    return items.stream()
        .map(ItemVO::valueOf).collect(Collectors.toList());
  }

  public ItemVO updateItem(ItemDto.Update itemDto) {
    return null; // itemService.updateItem(itemDto);
  }

//  @Async
//  @Transactional // 여기서 실패되어도 상위 메서드는 롤백되지 않음을 주의
//  public void deleteItemByProduct(Product product) {
//    List<Item> items = itemRepository.findAllByProduct(product);
//    items.stream()
//      .peek(Item::deleteItem) // 아이템 삭제
//      .forEach(item -> itemOptionHelper.deleteOptionByProduct(item.getOptionIds())); // 아이템에 대한 옵션 삭제
//    itemRepository.saveAll(items);
//  }
}
