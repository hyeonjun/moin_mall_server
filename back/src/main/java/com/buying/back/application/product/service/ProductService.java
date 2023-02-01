package com.buying.back.application.product.service;

import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.repository.ItemRepository;
import com.buying.back.application.product.repository.OptionRepository;
import com.buying.back.application.product.repository.ProductRepository;
import com.buying.back.application.product.service.vo.ProductDefaultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;
    private final ProductItemHelperService productItemHelperService;

    @Transactional
    public Product createProduct(ProductDto.Create dto) {
        List<ItemDto.Create> itemsDto = dto.getItemsDto();

        Product product = Product.create(dto);
        productRepository.save(product);

        List<Item> items = itemsDto.stream().map(itemDto -> {
            StringBuilder itemName = new StringBuilder();
            StringBuilder itemOptions = new StringBuilder();

            List<Option> options = itemDto.getOptionsDto().stream().map(info -> {
                itemName.append(info.getOptionValue())
                        .append("/");
                Option option = Option.create(info);
                option.setProduct(product);
                return option;
            }).collect(Collectors.toList());

            optionRepository.saveAll(options);
            options.forEach(option -> itemOptions.append(option.getId()).append("/"));

            itemDto.setName(itemName.toString());
            itemDto.setOptions(itemOptions.toString());

            Item item = Item.create(itemDto);
            item.setProduct(product);
            return item;
        }).collect(Collectors.toList());
        itemRepository.saveAll(items);

        return product;
    }

    @Transactional
    public ProductDefaultVO updateProduct(Long productId, ProductDto.Update dto) {
        productItemHelperService.updateItems(dto.getItemsDto());

        Product product = productRepository.findById(productId).orElseThrow();
        product.update(dto);
        productRepository.save(product);

        return new ProductDefaultVO();
    }
}

