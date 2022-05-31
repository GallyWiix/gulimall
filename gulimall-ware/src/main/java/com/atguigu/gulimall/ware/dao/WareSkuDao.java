package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author hygod
 * @email jeremygit@163.com
 * @date 2022-05-31 19:39:56
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
