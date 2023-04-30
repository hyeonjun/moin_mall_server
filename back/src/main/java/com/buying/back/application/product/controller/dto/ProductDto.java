package com.buying.back.application.product.controller.dto;

import com.buying.back.application.common.dto.PagingDTO;
import com.buying.back.util.verify.VerifyLengthUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class ProductDto {

  @Getter
  @Setter
  public static class Create {

    @NotNull
    private Long categoryId;
    @NotBlank
    @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
    private String name;
    @NotNull
    @Min(value = 0)
    private Integer price;
    @Valid
    @Size(min = 1)
    private List<ItemDto.Create> itemsDto = new ArrayList<>();
  }

  @Getter
  @Setter
  public static class Update {

    @Length(min = 1, max = VerifyLengthUtil.MAX_NAME_LENGTH)
    private String name;
    private Long categoryId;
    @Min(value = 0)
    private Integer price;
  }

  @Getter
  @Setter
  public static class Search extends PagingDTO {

    private String productName;
    private Long categoryId;
    private Integer priceFrom;
    private Integer priceTo;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private Boolean deleted;
  }
}
