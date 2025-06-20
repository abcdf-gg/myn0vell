package com.entity.view;

import com.entity.HuiyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 会员
 * 后端返回视图实体辅助类   
 * @author
 * @email 
 */
@TableName("huiyuan")
public class HuiyuanView  extends HuiyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public HuiyuanView(){
	}
 
 	public HuiyuanView(HuiyuanEntity huiyuanEntity){
 	try {
			BeanUtils.copyProperties(this, huiyuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
