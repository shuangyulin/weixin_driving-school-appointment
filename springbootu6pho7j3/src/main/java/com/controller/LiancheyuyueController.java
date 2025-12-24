package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.LiancheyuyueEntity;
import com.entity.view.LiancheyuyueView;

import com.service.LiancheyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 练车预约
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
@RestController
@RequestMapping("/liancheyuyue")
public class LiancheyuyueController {
    @Autowired
    private LiancheyuyueService liancheyuyueService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LiancheyuyueEntity liancheyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaolian")) {
			liancheyuyue.setGonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			liancheyuyue.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();


        //查询结果
		PageUtils page = liancheyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, liancheyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LiancheyuyueEntity liancheyuyue, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();

        //查询结果
		PageUtils page = liancheyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, liancheyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LiancheyuyueEntity liancheyuyue){
       	EntityWrapper<LiancheyuyueEntity> ew = new EntityWrapper<LiancheyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( liancheyuyue, "liancheyuyue")); 
        return R.ok().put("data", liancheyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LiancheyuyueEntity liancheyuyue){
        EntityWrapper< LiancheyuyueEntity> ew = new EntityWrapper< LiancheyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( liancheyuyue, "liancheyuyue")); 
		LiancheyuyueView liancheyuyueView =  liancheyuyueService.selectView(ew);
		return R.ok("查询练车预约成功").put("data", liancheyuyueView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(liancheyuyue,deSens);
        return R.ok().put("data", liancheyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(liancheyuyue,deSens);
        return R.ok().put("data", liancheyuyue);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(liancheyuyue);
        liancheyuyueService.insert(liancheyuyue);
        return R.ok().put("data",liancheyuyue.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(liancheyuyue);
        liancheyuyueService.insert(liancheyuyue);
        return R.ok().put("data",liancheyuyue.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LiancheyuyueEntity liancheyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(liancheyuyue);
        //全部更新
        liancheyuyueService.updateById(liancheyuyue);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<LiancheyuyueEntity> list = new ArrayList<LiancheyuyueEntity>();
        for(Long id : ids) {
            LiancheyuyueEntity liancheyuyue = liancheyuyueService.selectById(id);
            liancheyuyue.setSfsh(sfsh);
            liancheyuyue.setShhf(shhf);
            list.add(liancheyuyue);
        }
        liancheyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        liancheyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
