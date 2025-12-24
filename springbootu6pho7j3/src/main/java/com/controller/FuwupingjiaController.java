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

import com.entity.FuwupingjiaEntity;
import com.entity.view.FuwupingjiaView;

import com.service.FuwupingjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 服务评价
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-19 00:31:49
 */
@RestController
@RequestMapping("/fuwupingjia")
public class FuwupingjiaController {
    @Autowired
    private FuwupingjiaService fuwupingjiaService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FuwupingjiaEntity fuwupingjia,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaolian")) {
			fuwupingjia.setGonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			fuwupingjia.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<FuwupingjiaEntity> ew = new EntityWrapper<FuwupingjiaEntity>();


        //查询结果
		PageUtils page = fuwupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwupingjia), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FuwupingjiaEntity fuwupingjia, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FuwupingjiaEntity> ew = new EntityWrapper<FuwupingjiaEntity>();

        //查询结果
		PageUtils page = fuwupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwupingjia), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FuwupingjiaEntity fuwupingjia){
       	EntityWrapper<FuwupingjiaEntity> ew = new EntityWrapper<FuwupingjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fuwupingjia, "fuwupingjia")); 
        return R.ok().put("data", fuwupingjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FuwupingjiaEntity fuwupingjia){
        EntityWrapper< FuwupingjiaEntity> ew = new EntityWrapper< FuwupingjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fuwupingjia, "fuwupingjia")); 
		FuwupingjiaView fuwupingjiaView =  fuwupingjiaService.selectView(ew);
		return R.ok("查询服务评价成功").put("data", fuwupingjiaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FuwupingjiaEntity fuwupingjia = fuwupingjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fuwupingjia,deSens);
        return R.ok().put("data", fuwupingjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FuwupingjiaEntity fuwupingjia = fuwupingjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fuwupingjia,deSens);
        return R.ok().put("data", fuwupingjia);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FuwupingjiaEntity fuwupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fuwupingjia);
        fuwupingjiaService.insert(fuwupingjia);
        return R.ok().put("data",fuwupingjia.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FuwupingjiaEntity fuwupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fuwupingjia);
        fuwupingjiaService.insert(fuwupingjia);
        return R.ok().put("data",fuwupingjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FuwupingjiaEntity fuwupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fuwupingjia);
        //全部更新
        fuwupingjiaService.updateById(fuwupingjia);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fuwupingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
