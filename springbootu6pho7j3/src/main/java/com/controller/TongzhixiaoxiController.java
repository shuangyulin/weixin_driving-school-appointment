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

import com.entity.TongzhixiaoxiEntity;
import com.entity.view.TongzhixiaoxiView;

import com.service.TongzhixiaoxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 通知消息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
@RestController
@RequestMapping("/tongzhixiaoxi")
public class TongzhixiaoxiController {
    @Autowired
    private TongzhixiaoxiService tongzhixiaoxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TongzhixiaoxiEntity tongzhixiaoxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xueyuan")) {
			tongzhixiaoxi.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<TongzhixiaoxiEntity> ew = new EntityWrapper<TongzhixiaoxiEntity>();


        //查询结果
		PageUtils page = tongzhixiaoxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tongzhixiaoxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,TongzhixiaoxiEntity tongzhixiaoxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<TongzhixiaoxiEntity> ew = new EntityWrapper<TongzhixiaoxiEntity>();

        //查询结果
		PageUtils page = tongzhixiaoxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tongzhixiaoxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TongzhixiaoxiEntity tongzhixiaoxi){
       	EntityWrapper<TongzhixiaoxiEntity> ew = new EntityWrapper<TongzhixiaoxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tongzhixiaoxi, "tongzhixiaoxi")); 
        return R.ok().put("data", tongzhixiaoxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TongzhixiaoxiEntity tongzhixiaoxi){
        EntityWrapper< TongzhixiaoxiEntity> ew = new EntityWrapper< TongzhixiaoxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tongzhixiaoxi, "tongzhixiaoxi")); 
		TongzhixiaoxiView tongzhixiaoxiView =  tongzhixiaoxiService.selectView(ew);
		return R.ok("查询通知消息成功").put("data", tongzhixiaoxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TongzhixiaoxiEntity tongzhixiaoxi = tongzhixiaoxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tongzhixiaoxi,deSens);
        return R.ok().put("data", tongzhixiaoxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TongzhixiaoxiEntity tongzhixiaoxi = tongzhixiaoxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tongzhixiaoxi,deSens);
        return R.ok().put("data", tongzhixiaoxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TongzhixiaoxiEntity tongzhixiaoxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tongzhixiaoxi);
        tongzhixiaoxiService.insert(tongzhixiaoxi);
        return R.ok().put("data",tongzhixiaoxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TongzhixiaoxiEntity tongzhixiaoxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tongzhixiaoxi);
        tongzhixiaoxiService.insert(tongzhixiaoxi);
        return R.ok().put("data",tongzhixiaoxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TongzhixiaoxiEntity tongzhixiaoxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tongzhixiaoxi);
        //全部更新
        tongzhixiaoxiService.updateById(tongzhixiaoxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tongzhixiaoxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
