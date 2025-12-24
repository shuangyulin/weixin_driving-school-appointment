package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TongzhixiaoxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TongzhixiaoxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TongzhixiaoxiView;


/**
 * 通知消息
 *
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
public interface TongzhixiaoxiService extends IService<TongzhixiaoxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TongzhixiaoxiVO> selectListVO(Wrapper<TongzhixiaoxiEntity> wrapper);
   	
   	TongzhixiaoxiVO selectVO(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);
   	
   	List<TongzhixiaoxiView> selectListView(Wrapper<TongzhixiaoxiEntity> wrapper);
   	
   	TongzhixiaoxiView selectView(@Param("ew") Wrapper<TongzhixiaoxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TongzhixiaoxiEntity> wrapper);

   	

}

