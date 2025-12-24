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

import com.entity.KechengfenleiEntity;
import com.entity.view.KechengfenleiView;

import com.service.KechengfenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
@RestController
@RequestMapping("/kechengfenlei")
public class KechengfenleiController {
    @Autowired
    private KechengfenleiService kechengfenleiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();


        //查询结果
		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KechengfenleiEntity kechengfenlei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();

        //查询结果
		PageUtils page = kechengfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengfenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengfenleiEntity kechengfenlei){
       	EntityWrapper<KechengfenleiEntity> ew = new EntityWrapper<KechengfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei")); 
        return R.ok().put("data", kechengfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengfenleiEntity kechengfenlei){
        EntityWrapper< KechengfenleiEntity> ew = new EntityWrapper< KechengfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengfenlei, "kechengfenlei")); 
		KechengfenleiView kechengfenleiView =  kechengfenleiService.selectView(ew);
		return R.ok("查询课程分类成功").put("data", kechengfenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengfenleiEntity kechengfenlei = kechengfenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengfenlei,deSens);
        return R.ok().put("data", kechengfenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.selectCount(new EntityWrapper<KechengfenleiEntity>().eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.insert(kechengfenlei);
        return R.ok().put("data",kechengfenlei.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.selectCount(new EntityWrapper<KechengfenleiEntity>().eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //ValidatorUtils.validateEntity(kechengfenlei);
        kechengfenleiService.insert(kechengfenlei);
        return R.ok().put("data",kechengfenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengfenleiEntity kechengfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengfenlei);
        //验证字段唯一性，否则返回错误信息
        if(kechengfenleiService.selectCount(new EntityWrapper<KechengfenleiEntity>().ne("id", kechengfenlei.getId()).eq("kechengfenlei", kechengfenlei.getKechengfenlei()))>0) {
            return R.error("课程分类已存在");
        }
        //全部更新
        kechengfenleiService.updateById(kechengfenlei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengfenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
