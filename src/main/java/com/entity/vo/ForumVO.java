package com.entity.vo;

import com.entity.ForumEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 交流论坛
 * 手机端接口返回实体辅助类 
 * @author
 * @email 
 */
public class ForumVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 帖子内容
	 */
	
	private String content;
		
	/**
	 * 父节点id
	 */
	
	private Long parentid;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
		
	/**
	 * 用户名
	 */
	
	private String username;
		
	/**
	 * 状态
	 */
	
	private String isdone;
				
	
	/**
	 * 设置：帖子内容
	 */
	 
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取：帖子内容
	 */
	public String getContent() {
		return content;
	}
				
	
	/**
	 * 设置：父节点id
	 */
	 
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	
	/**
	 * 获取：父节点id
	 */
	public Long getParentid() {
		return parentid;
	}
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
				
	
	/**
	 * 设置：用户名
	 */
	 
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：状态
	 */
	 
	public void setIsdone(String isdone) {
		this.isdone = isdone;
	}
	
	/**
	 * 获取：状态
	 */
	public String getIsdone() {
		return isdone;
	}
			
}
