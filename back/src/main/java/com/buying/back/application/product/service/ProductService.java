package com.buying.back.application.product.service;

import com.buying.back.application.account.code.exception.BrandException;
import com.buying.back.application.account.code.exception.BrandException.BrandExceptionCode;
import com.buying.back.application.account.domain.Brand;
import com.buying.back.application.account.repository.BrandRepository;
import com.buying.back.application.category.code.exception.CategoryException;
import com.buying.back.application.category.domain.Category;
import com.buying.back.application.category.repository.CategoryRepository;
import com.buying.back.application.common.exception.code.AuthenticationException;
import com.buying.back.application.common.exception.code.AuthenticationException.AuthenticationExceptionCode;
import com.buying.back.application.product.code.ProductExceptionCode;
import com.buying.back.application.product.controller.dto.ProductDto;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.exception.ProductException;
import com.buying.back.application.product.helper.ProductItemHelper;
import com.buying.back.application.product.repository.ProductRepository;
import com.buying.back.application.product.repository.param.SearchProductListParam;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.ProductVO;
import com.buying.back.application.product.service.vo.ProductItemVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
  private final CategoryRepository categoryRepository;
  private final BrandRepository brandRepository;
  private final ProductRepository productRepository;
  private final ProductItemHelper productItemHelper;


  public ProductVO getProduct(Long brandId, Long productId) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    Product product = productRepository.findById(productId)
      .orElseThrow(() -> new ProductException(ProductExceptionCode.NOT_FOUND_PRODUCT));

    if (!product.getBrand().getId().equals(brand.getId())) {
      throw new AuthenticationException(AuthenticationExceptionCode.NOT_AUTHORIZED);
    }

    return ProductVO.valueOf(product);
  }

  public ProductItemVO getProductItem(Long brandId, Long productId) {
    return new ProductItemVO();
  }

  public Page<ProductVO> getProductList(Long brandId, ProductDto.Search dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    SearchProductListParam param = SearchProductListParam.valueOf(dto);
    param.setDeleted(dto.getDeleted());
    param.setBrandId(brand.getId());

    return productRepository.findAllByBrand(dto.getPageRequest(), param);
  }

  @Transactional
  public ProductItemVO createProduct(Long brandId, ProductDto.Create dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    Category category = categoryRepository.findById(dto.getCategoryId())
      .orElseThrow(() -> new CategoryException(
        CategoryException.CategoryExceptionCode.NOT_FOUND_CATEGORY));

    Product product = Product.create(dto, brand, category);
    productRepository.save(product);

    List<ItemVO> itemVOList = productItemHelper.createItem(product, dto.getItemsDto());

    return ProductItemVO.valueOf(product, itemVOList);
  }

  @Transactional
  public ProductVO updateProduct(Long brandId, Long productId, ProductDto.Update dto) {
    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    Product product = productRepository.findById(productId)
      .orElseThrow(() -> new ProductException(ProductExceptionCode.NOT_FOUND_PRODUCT));

    // 브랜드 검증
    if (!brand.equals(product.getBrand())) {
      throw new AuthenticationException(AuthenticationExceptionCode.NOT_AUTHORIZED);
    }

    // 상품 정보 및 카테고리 변경
    Category originCategory = product.getCategory();
    Category targetCategory = null;
    // 같은 카테고리인 경우 변경할 필요없음
    if (!originCategory.getId().equals(dto.getCategoryId())) {
      targetCategory = categoryRepository.findById(dto.getCategoryId())
        .orElseThrow(() -> new CategoryException(CategoryException.CategoryExceptionCode.NOT_FOUND_CATEGORY));
    }
    product.update(dto, targetCategory);
    productRepository.save(product);
    return ProductVO.valueOf(product);
  }

  @Transactional
  public void deleteProduct(Long brandId, Long productId) {
    Product product = productRepository.findById(productId)
      .orElseThrow(() -> new ProductException(ProductExceptionCode.NOT_FOUND_PRODUCT));

    Brand brand = brandRepository.findById(brandId)
      .orElseThrow(() -> new BrandException(BrandExceptionCode.NOT_FOUND_BRAND));

    if (!brand.equals(product.getBrand())) {
      throw new AuthenticationException(AuthenticationExceptionCode.NOT_AUTHORIZED);
    }

    // 상품 삭제
    product.delete();
    productRepository.save(product);

    // 상품에 속한 아이템 및 아이팀의 옵션 삭제 -> Async
    productItemHelper.deleteItemByProduct(product);
  }
}

