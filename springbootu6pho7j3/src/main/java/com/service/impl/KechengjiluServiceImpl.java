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


import com.dao.KechengjiluDao;
import com.entity.KechengjiluEntity;
import com.service.KechengjiluService;
import com.entity.vo.KechengjiluVO;
import com.entity.view.KechengjiluView;

@Service("kechengjiluService")
public class KechengjiluServiceImpl extends ServiceImpl<KechengjiluDao, KechengjiluEntity> implements KechengjiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengjiluEntity> page = this.selectPage(
                new Query<KechengjiluEntity>(params).getPage(),
                new EntityWrapper<KechengjiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KechengjiluEntity> wrapper) {
		  Page<KechengjiluView> page =new Query<KechengjiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<KechengjiluVO> selectListVO(Wrapper<KechengjiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengjiluVO selectVO(Wrapper<KechengjiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengjiluView> selectListView(Wrapper<KechengjiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengjiluView selectView(Wrapper<KechengjiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<KechengjiluEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<KechengjiluEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<KechengjiluEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
