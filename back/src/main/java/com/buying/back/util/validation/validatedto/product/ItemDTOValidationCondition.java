package com.buying.back.util.validation.validatedto.product;

import static com.buying.back.application.product.service.ItemService.ITEM_OPTION_DELIMITER;

import javax.validation.constraints.Size;
import org.springframework.validation.Errors;

public class ItemDTOValidationCondition {

  public static void checkOptionSize(Errors errors, String options) {
    if (options.split(ITEM_OPTION_DELIMITER).length > 3) {
      errors.rejectValue("options", Size.class.getSimpleName(),
        "options size is over 3");
    }
  }

}
