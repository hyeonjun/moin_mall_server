package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemOptionVO;
import com.buying.back.application.product.service.vo.OptionDefaultVO;
import com.buying.back.application.product.service.vo.QItemOptionVO;
import com.buying.back.application.product.service.vo.QOptionDefaultVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;

import java.util.List;
import java.util.Set;

import static com.buying.back.application.product.domain.QOption.option;

public class OptionRepositoryImpl extends CustomQuerydslRepositorySupport implements OptionRepositoryCustom {
    public OptionRepositoryImpl() {
        super(Option.class);
    }

    public List<ItemOptionVO> findOptionsByIdIn(Set<Long> ids) {
        return select(getItemOptionVO())
                .from(option)
                .where(option.id.in(ids))
                .fetch();
    }

    public List<OptionDefaultVO> findDistinctNameByProduct(Product product) {
        return select(getOptionDefaultVO())
                .from(option)
                .where(option.product.eq(product))
                .orderBy(option.orderBy.asc())
                .fetch();
    }

    private QOptionDefaultVO getOptionDefaultVO() {
        return new QOptionDefaultVO(
                option.id,
                option.name,
                option.orderBy
        );
    }

    private QItemOptionVO getItemOptionVO() {
        return new QItemOptionVO(
                option
        );
    }

}
