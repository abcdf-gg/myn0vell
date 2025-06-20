package com.entity.view;

import com.entity.DiscussxiaoshuoxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 小说信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 */
@TableName("discussxiaoshuoxinxi")
public class DiscussxiaoshuoxinxiView  extends DiscussxiaoshuoxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussxiaoshuoxinxiView(){
	}
 
 	public DiscussxiaoshuoxinxiView(DiscussxiaoshuoxinxiEntity discussxiaoshuoxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, discussxiaoshuoxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
