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


import com.dao.XiaoquDao;
import com.entity.XiaoquEntity;
import com.service.XiaoquService;
import com.entity.vo.XiaoquVO;
import com.entity.view.XiaoquView;

@Service("xiaoquService")
public class XiaoquServiceImpl extends ServiceImpl<XiaoquDao, XiaoquEntity> implements XiaoquService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiaoquEntity> page = this.selectPage(
                new Query<XiaoquEntity>(params).getPage(),
                new EntityWrapper<XiaoquEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiaoquEntity> wrapper) {
		  Page<XiaoquView> page =new Query<XiaoquView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XiaoquVO> selectListVO(Wrapper<XiaoquEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiaoquVO selectVO(Wrapper<XiaoquEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiaoquView> selectListView(Wrapper<XiaoquEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiaoquView selectView(Wrapper<XiaoquEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
