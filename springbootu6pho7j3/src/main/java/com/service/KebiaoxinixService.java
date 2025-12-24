package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KebiaoxinixEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KebiaoxinixVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KebiaoxinixView;


/**
 * 课表信息
 *
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public interface KebiaoxinixService extends IService<KebiaoxinixEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KebiaoxinixVO> selectListVO(Wrapper<KebiaoxinixEntity> wrapper);
   	
   	KebiaoxinixVO selectVO(@Param("ew") Wrapper<KebiaoxinixEntity> wrapper);
   	
   	List<KebiaoxinixView> selectListView(Wrapper<KebiaoxinixEntity> wrapper);
   	
   	KebiaoxinixView selectView(@Param("ew") Wrapper<KebiaoxinixEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KebiaoxinixEntity> wrapper);

   	

}

