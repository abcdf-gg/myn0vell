package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XiaoshuofenleiEntity;
import com.entity.view.XiaoshuofenleiView;

import com.service.XiaoshuofenleiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 小说分类
 * 后端接口
 * @author 
 * @email 
 */
@RestController
@RequestMapping("/xiaoshuofenlei")
public class XiaoshuofenleiController {
    @Autowired
    private XiaoshuofenleiService xiaoshuofenleiService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiaoshuofenleiEntity xiaoshuofenlei,
		HttpServletRequest request){
        EntityWrapper<XiaoshuofenleiEntity> ew = new EntityWrapper<XiaoshuofenleiEntity>();
		PageUtils page = xiaoshuofenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuofenlei), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiaoshuofenleiEntity xiaoshuofenlei, 
		HttpServletRequest request){
        EntityWrapper<XiaoshuofenleiEntity> ew = new EntityWrapper<XiaoshuofenleiEntity>();
		PageUtils page = xiaoshuofenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuofenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiaoshuofenleiEntity xiaoshuofenlei){
       	EntityWrapper<XiaoshuofenleiEntity> ew = new EntityWrapper<XiaoshuofenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiaoshuofenlei, "xiaoshuofenlei")); 
        return R.ok().put("data", xiaoshuofenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiaoshuofenleiEntity xiaoshuofenlei){
        EntityWrapper< XiaoshuofenleiEntity> ew = new EntityWrapper< XiaoshuofenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiaoshuofenlei, "xiaoshuofenlei")); 
		XiaoshuofenleiView xiaoshuofenleiView =  xiaoshuofenleiService.selectView(ew);
		return R.ok("查询小说分类成功").put("data", xiaoshuofenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiaoshuofenleiEntity xiaoshuofenlei = xiaoshuofenleiService.selectById(id);
        return R.ok().put("data", xiaoshuofenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiaoshuofenleiEntity xiaoshuofenlei = xiaoshuofenleiService.selectById(id);
        return R.ok().put("data", xiaoshuofenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiaoshuofenleiEntity xiaoshuofenlei, HttpServletRequest request){
    	xiaoshuofenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuofenlei);
        xiaoshuofenleiService.insert(xiaoshuofenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiaoshuofenleiEntity xiaoshuofenlei, HttpServletRequest request){
    	xiaoshuofenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuofenlei);
        xiaoshuofenleiService.insert(xiaoshuofenlei);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiaoshuofenleiEntity xiaoshuofenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiaoshuofenlei);
        xiaoshuofenleiService.updateById(xiaoshuofenlei);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiaoshuofenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<XiaoshuofenleiEntity> wrapper = new EntityWrapper<XiaoshuofenleiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = xiaoshuofenleiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}

}
