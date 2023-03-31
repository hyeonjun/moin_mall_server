package com.buying.back.application.product.service;

import com.buying.back.application.product.code.ProductExceptionCode;
import com.buying.back.application.product.controller.dto.ItemDto;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.exception.ProductException;
import com.buying.back.application.product.repository.ProductRepository;
import com.buying.back.application.product.service.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductItemHelperService productItemHelperService;
    private final ProductOptionHelperService productOptionHelperService;

    public ProductDefaultVO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.NOT_FOUND_PRODUCT));

        List<ItemDetailVO> itemsByProduct = productItemHelperService.getItemsByProduct(product);
        List<OptionDefaultVO> productOptions = productOptionHelperService.getProductOptions(product);

        return new ProductDetailVO(product, itemsByProduct, productOptions);
    }

    @Transactional
    public ProductDefaultVO createProduct(ProductDto.Create dto) {
        List<ItemDto.Create> itemsDto = dto.getItemsDto();

        Product product = Product.create(dto);
        productRepository.save(product);

        return createItemsAndOptions(product, itemsDto);
    }

    @Transactional
    public ProductDefaultVO updateProduct(Long productId, ProductDto.Update dto) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.update(dto);
        productRepository.save(product);

        List<ItemDto.Create> newItemsDto = dto.getNewItemsDto();
        if(!newItemsDto.isEmpty()) {
            createItemsAndOptions(product, newItemsDto);
        }
        dto.getItemsDto().forEach(productItemHelperService::updateItem);

        List<ItemDetailVO> itemsByProduct = productItemHelperService.getItemsByProduct(product);
        List<OptionDefaultVO> productOptions = productOptionHelperService.getProductOptions(product);

        return new ProductDetailVO(product, itemsByProduct, productOptions);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("NOT FOND PRODUCT"));
        productItemHelperService.deleteItemByProduct(product);
        productOptionHelperService.deleteOptionByProduct(product);
        productRepository.delete(product);
    }

    // TODO: 2023-03-31 나중에 코드 다시 읽고 여기 전부 고치기 기억이 안나네
    private ProductDefaultVO createItemsAndOptions(Product product, List<ItemDto.Create> itemsDto) {
        List<OptionDefaultVO> productOptions = new ArrayList<>();
        List<ItemDetailVO> itemDefaultVOList = itemsDto.stream().map(itemDto -> {
            StringBuilder itemOptions = new StringBuilder();

            List<ItemOptionVO> itemOptionsVO = productOptionHelperService.createOptionAll(product, itemDto.getOptionsDto()).stream()
                    .map(item -> {
                        ItemOptionVO itemOptionVO = new ItemOptionVO(item);
                        productOptions.add(itemOptionVO);
                        return itemOptionVO;
                    })
                    .collect(Collectors.toList());

            for (int i = 0; i < itemOptionsVO.size(); i++) {
                itemOptions.append(itemOptionsVO.get(i).getId());
                if (itemOptionsVO.size() - 1 != i) {
                    itemOptions.append("/");
                }
            }
            itemDto.setOptions(itemOptions.toString());
            return new ItemDetailVO(productItemHelperService.createItem(product, itemDto), itemOptionsVO);
        }).collect(Collectors.toList());

        List<OptionDefaultVO> optionDefaultVOList = productOptions.stream().distinct().collect(Collectors.toList());

        return new ProductDetailVO(product, itemDefaultVOList, optionDefaultVOList);
    }
}

