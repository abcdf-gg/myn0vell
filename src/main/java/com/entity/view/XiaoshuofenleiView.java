package com.entity.view;

import com.entity.XiaoshuofenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 小说分类
 * 后端返回视图实体辅助类   
 * @author
 * @email 
 */
@TableName("xiaoshuofenlei")
public class XiaoshuofenleiView  extends XiaoshuofenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XiaoshuofenleiView(){
	}
 
 	public XiaoshuofenleiView(XiaoshuofenleiEntity xiaoshuofenleiEntity){
 	try {
			BeanUtils.copyProperties(this, xiaoshuofenleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
