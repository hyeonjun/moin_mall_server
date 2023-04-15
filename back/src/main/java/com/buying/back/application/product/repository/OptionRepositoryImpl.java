package com.buying.back.application.product.repository;

import com.buying.back.application.product.domain.Option;
import com.buying.back.application.product.domain.Product;
import com.buying.back.application.product.service.vo.ItemVO;
import com.buying.back.application.product.service.vo.OptionVO;
import com.buying.back.util.querydsl.CustomQuerydslRepositorySupport;

import java.util.List;
import java.util.Set;

import static com.buying.back.application.product.domain.QOption.option;

public class OptionRepositoryImpl extends CustomQuerydslRepositorySupport implements OptionRepositoryCustom {
    public OptionRepositoryImpl() {
        super(Option.class);
    }

    @Override
    public List<OptionVO> findDistinctNameByProduct(Product product) {
        return null;
    }

    @Override
    public List<ItemVO> findOptionsByIdIn(Set<Long> ids) {
        return null;
    }

//    public List<ItemVO> findOptionsByIdIn(Set<Long> ids) {
//        return select(getItemOptionVO())
//                .from(option)
//                .where(option.id.in(ids))
//                .fetch();
//    }
//
//    public List<OptionVO> findDistinctNameByProduct(Product product) {
//        return select(getOptionDefaultVO())
//                .from(option)
//                .where(option.product.eq(product))
//                .orderBy(option.orderBy.asc())
//                .fetch();
//    }
//
//    private QOptionDefaultVO getOptionDefaultVO() {
//        return new QOptionDefaultVO(
//                option.id,
//                option.name,
//                option.orderBy
//        );
//    }
//
//    private QItemOptionVO getItemOptionVO() {
//        return new QItemOptionVO(
//                option
//        );
//    }

}
