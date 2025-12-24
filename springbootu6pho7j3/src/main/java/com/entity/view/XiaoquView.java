package com.entity.view;

import com.entity.XiaoquEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 校区
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
@TableName("xiaoqu")
public class XiaoquView  extends XiaoquEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XiaoquView(){
	}
 
 	public XiaoquView(XiaoquEntity xiaoquEntity){
 	try {
			BeanUtils.copyProperties(this, xiaoquEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
