package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexijiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexijiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexijiluView;


/**
 * 学习记录
 *
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
public interface XuexijiluService extends IService<XuexijiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexijiluVO> selectListVO(Wrapper<XuexijiluEntity> wrapper);
   	
   	XuexijiluVO selectVO(@Param("ew") Wrapper<XuexijiluEntity> wrapper);
   	
   	List<XuexijiluView> selectListView(Wrapper<XuexijiluEntity> wrapper);
   	
   	XuexijiluView selectView(@Param("ew") Wrapper<XuexijiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XuexijiluEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<XuexijiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<XuexijiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<XuexijiluEntity> wrapper);



}

