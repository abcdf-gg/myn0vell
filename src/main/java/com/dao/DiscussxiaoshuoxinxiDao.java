package com.dao;

import com.entity.DiscussxiaoshuoxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussxiaoshuoxinxiVO;
import com.entity.view.DiscussxiaoshuoxinxiView;


/**
 * 小说信息评论表
 * 
 * @author 
 * @email 
 */
public interface DiscussxiaoshuoxinxiDao extends BaseMapper<DiscussxiaoshuoxinxiEntity> {
	
	List<DiscussxiaoshuoxinxiVO> selectListVO(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
	
	DiscussxiaoshuoxinxiVO selectVO(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
	
	List<DiscussxiaoshuoxinxiView> selectListView(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);

	List<DiscussxiaoshuoxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
	
	DiscussxiaoshuoxinxiView selectView(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
	

}
