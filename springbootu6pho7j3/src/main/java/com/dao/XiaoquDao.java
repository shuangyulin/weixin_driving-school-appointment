package com.dao;

import com.entity.XiaoquEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiaoquVO;
import com.entity.view.XiaoquView;


/**
 * 校区
 * 
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public interface XiaoquDao extends BaseMapper<XiaoquEntity> {
	
	List<XiaoquVO> selectListVO(@Param("ew") Wrapper<XiaoquEntity> wrapper);
	
	XiaoquVO selectVO(@Param("ew") Wrapper<XiaoquEntity> wrapper);
	
	List<XiaoquView> selectListView(@Param("ew") Wrapper<XiaoquEntity> wrapper);

	List<XiaoquView> selectListView(Pagination page,@Param("ew") Wrapper<XiaoquEntity> wrapper);

	
	XiaoquView selectView(@Param("ew") Wrapper<XiaoquEntity> wrapper);
	

}
