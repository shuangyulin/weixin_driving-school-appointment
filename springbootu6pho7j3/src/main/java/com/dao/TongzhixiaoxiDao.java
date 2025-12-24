package com.dao;

import com.entity.TongzhixiaoxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.TongzhixiaoxiVO;
import com.entity.view.TongzhixiaoxiView;


/**
 * 通知消息
 * 
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
public interface TongzhixiaoxiDao extends BaseMapper<TongzhixiaoxiEntity> {
	
	List<TongzhixiaoxiVO> selectListVO(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);
	
	TongzhixiaoxiVO selectVO(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);
	
	List<TongzhixiaoxiView> selectListView(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);

	List<TongzhixiaoxiView> selectListView(Pagination page,@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);

	
	TongzhixiaoxiView selectView(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);
	

}
