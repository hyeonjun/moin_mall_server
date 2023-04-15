package com.buying.back.application.product.service.helper;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.exception.ProductException;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.service.ItemService;
import com.buying.back.application.product.service.OptionService;
import com.buying.back.application.product.service.vo.ItemDefaultVO;
import com.buying.back.application.product.service.vo.ItemDetailVO;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductItemHelper {
  private final OptionService optionService;
  private final ItemService itemService;
  private final ItemRepository itemRepository;

  public List<ItemDetailVO> getItemsByProduct(Product product) {
    return itemRepository.findAllByProduct(product).stream().map(item -> {
      Set<Long> optionIds = item.getOptionIds();
      List<ItemOptionVO> itemOptions = optionService.getItemOptions(optionIds);

      ItemDetailVO vo = (ItemDetailVO) ItemDefaultVO.valueOf(item);
      vo.setOptions(itemOptions);

      return vo;
    }).collect(Collectors.toList());
  }

  @Transactional
  public Item createItem(Product product, ItemDto.Create dto) {
    Item item = Item.create(dto);
    item.setProduct(product);
    return itemRepository.save(item);
  }


  @Transactional
  public ItemDefaultVO updateItems(ItemDto.Update itemDto) {
    Item item = itemRepository.findById(itemDto.getItemId()).orElseThrow(() -> new ProductException(ProductException.ProductExceptionCode.NOT_FOUND_ITEM))
      .update(itemDto);
    itemRepository.save(item);
    return ItemDefaultVO.valueOf(item);
  }

  @Transactional
  public void deleteItemByProduct(Product product) {
    itemService.deleteByProduct(product);
  }
}
