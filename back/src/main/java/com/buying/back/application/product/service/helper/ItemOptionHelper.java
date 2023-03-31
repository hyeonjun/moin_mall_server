package com.buying.back.application.product.service.helper;

import com.buying.back.application.product.domain.Item;
import com.buying.back.application.product.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemOptionHelper {
  private final OptionRepository optionRepository;

}
