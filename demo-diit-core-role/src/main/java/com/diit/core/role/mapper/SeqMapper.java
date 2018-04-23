package com.diit.core.role.mapper;

import com.diit.common.wrapper.BaseMapper;
import com.diit.core.role.pojo.Role;

/**
 * 序列号生成器-接口
 *
 * @author lishw
 * @date 2018-01-09
 */
public interface SeqMapper extends BaseMapper<Role>{
	
	public Long getBySeqName(String name);
	public Long getSeqId();
	
}
