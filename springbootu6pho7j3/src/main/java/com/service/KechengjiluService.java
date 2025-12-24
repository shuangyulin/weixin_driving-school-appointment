package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KechengjiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KechengjiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KechengjiluView;


/**
 * 课程记录
 *
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public interface KechengjiluService extends IService<KechengjiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengjiluVO> selectListVO(Wrapper<KechengjiluEntity> wrapper);
   	
   	KechengjiluVO selectVO(@Param("ew") Wrapper<KechengjiluEntity> wrapper);
   	
   	List<KechengjiluView> selectListView(Wrapper<KechengjiluEntity> wrapper);
   	
   	KechengjiluView selectView(@Param("ew") Wrapper<KechengjiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KechengjiluEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<KechengjiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<KechengjiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<KechengjiluEntity> wrapper);



}

