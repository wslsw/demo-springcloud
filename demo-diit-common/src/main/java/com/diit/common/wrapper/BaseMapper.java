package com.diit.common.wrapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 自定义BaseMapper
 *
 * @author lishw
 * @date 2017-01-18
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T> {
    
}
