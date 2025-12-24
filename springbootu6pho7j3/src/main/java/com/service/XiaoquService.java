package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiaoquEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiaoquVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaoquView;


/**
 * 校区
 *
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public interface XiaoquService extends IService<XiaoquEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiaoquVO> selectListVO(Wrapper<XiaoquEntity> wrapper);
   	
   	XiaoquVO selectVO(@Param("ew") Wrapper<XiaoquEntity> wrapper);
   	
   	List<XiaoquView> selectListView(Wrapper<XiaoquEntity> wrapper);
   	
   	XiaoquView selectView(@Param("ew") Wrapper<XiaoquEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiaoquEntity> wrapper);

   	

}

