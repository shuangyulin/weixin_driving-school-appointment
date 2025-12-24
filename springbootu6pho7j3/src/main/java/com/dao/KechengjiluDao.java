package com.dao;

import com.entity.KechengjiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KechengjiluVO;
import com.entity.view.KechengjiluView;


/**
 * 课程记录
 * 
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public interface KechengjiluDao extends BaseMapper<KechengjiluEntity> {
	
	List<KechengjiluVO> selectListVO(@Param("ew") Wrapper<KechengjiluEntity> wrapper);
	
	KechengjiluVO selectVO(@Param("ew") Wrapper<KechengjiluEntity> wrapper);
	
	List<KechengjiluView> selectListView(@Param("ew") Wrapper<KechengjiluEntity> wrapper);

	List<KechengjiluView> selectListView(Pagination page,@Param("ew") Wrapper<KechengjiluEntity> wrapper);

	
	KechengjiluView selectView(@Param("ew") Wrapper<KechengjiluEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengjiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengjiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<KechengjiluEntity> wrapper);



}
