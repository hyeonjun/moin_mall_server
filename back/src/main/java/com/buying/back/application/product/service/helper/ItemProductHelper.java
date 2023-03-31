package com.buying.back.application.product.service.helper;

import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.exception.ProductException;
import com.buying.back.application.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemProductHelper {
    private final ProductRepository productRepository;

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductException(ProductException.ProductExceptionCode.NOT_FOUND_PRODUCT));
    }
}
