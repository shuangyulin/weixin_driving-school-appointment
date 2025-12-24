package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.TongzhixiaoxiDao;
import com.entity.TongzhixiaoxiEntity;
import com.service.TongzhixiaoxiService;
import com.entity.vo.TongzhixiaoxiVO;
import com.entity.view.TongzhixiaoxiView;

@Service("tongzhixiaoxiService")
public class TongzhixiaoxiServiceImpl extends ServiceImpl<TongzhixiaoxiDao, TongzhixiaoxiEntity> implements TongzhixiaoxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TongzhixiaoxiEntity> page = this.selectPage(
                new Query<TongzhixiaoxiEntity>(params).getPage(),
                new EntityWrapper<TongzhixiaoxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TongzhixiaoxiEntity> wrapper) {
		  Page<TongzhixiaoxiView> page =new Query<TongzhixiaoxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<TongzhixiaoxiVO> selectListVO(Wrapper<TongzhixiaoxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TongzhixiaoxiVO selectVO(Wrapper<TongzhixiaoxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TongzhixiaoxiView> selectListView(Wrapper<TongzhixiaoxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TongzhixiaoxiView selectView(Wrapper<TongzhixiaoxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
