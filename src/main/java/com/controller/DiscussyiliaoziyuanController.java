package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.utils.*;
import com.service.*;
import com.entity.*;
import com.entity.view.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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
import com.annotation.SysLog;

import com.entity.DiscussyiliaoziyuanEntity;
import com.entity.view.DiscussyiliaoziyuanView;

import com.service.DiscussyiliaoziyuanService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 医疗资源评论
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
@RestController
@RequestMapping("/discussyiliaoziyuan")
public class DiscussyiliaoziyuanController {
    @Autowired
    private DiscussyiliaoziyuanService discussyiliaoziyuanService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyiliaoziyuanEntity discussyiliaoziyuan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyiliaoziyuanEntity> ew = new EntityWrapper<DiscussyiliaoziyuanEntity>();


        //查询结果
		PageUtils page = discussyiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyiliaoziyuan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyiliaoziyuanEntity discussyiliaoziyuan,
                @RequestParam(required = false) Double thumbsupnumstart,
                @RequestParam(required = false) Double thumbsupnumend,
                @RequestParam(required = false) Double crazilynumstart,
                @RequestParam(required = false) Double crazilynumend,
                @RequestParam(required = false) Double istopstart,
                @RequestParam(required = false) Double istopend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyiliaoziyuanEntity> ew = new EntityWrapper<DiscussyiliaoziyuanEntity>();
        if(thumbsupnumstart!=null) ew.ge("thumbsupnum", thumbsupnumstart);
        if(thumbsupnumend!=null) ew.le("thumbsupnum", thumbsupnumend);
        if(crazilynumstart!=null) ew.ge("crazilynum", crazilynumstart);
        if(crazilynumend!=null) ew.le("crazilynum", crazilynumend);
        if(istopstart!=null) ew.ge("istop", istopstart);
        if(istopend!=null) ew.le("istop", istopend);

        //查询结果
		PageUtils page = discussyiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyiliaoziyuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyiliaoziyuanEntity discussyiliaoziyuan){
       	EntityWrapper<DiscussyiliaoziyuanEntity> ew = new EntityWrapper<DiscussyiliaoziyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyiliaoziyuan, "discussyiliaoziyuan"));
        return R.ok().put("data", discussyiliaoziyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyiliaoziyuanEntity discussyiliaoziyuan){
        EntityWrapper< DiscussyiliaoziyuanEntity> ew = new EntityWrapper< DiscussyiliaoziyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyiliaoziyuan, "discussyiliaoziyuan"));
		DiscussyiliaoziyuanView discussyiliaoziyuanView =  discussyiliaoziyuanService.selectView(ew);
		return R.ok("查询医疗资源评论成功").put("data", discussyiliaoziyuanView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyiliaoziyuanEntity discussyiliaoziyuan = discussyiliaoziyuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyiliaoziyuan,deSens);
        return R.ok().put("data", discussyiliaoziyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyiliaoziyuanEntity discussyiliaoziyuan = discussyiliaoziyuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyiliaoziyuan,deSens);
        return R.ok().put("data", discussyiliaoziyuan);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增医疗资源评论")
    public R save(@RequestBody DiscussyiliaoziyuanEntity discussyiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyiliaoziyuan);
        discussyiliaoziyuanService.insert(discussyiliaoziyuan);
        return R.ok().put("data",discussyiliaoziyuan.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增医疗资源评论")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyiliaoziyuanEntity discussyiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyiliaoziyuan);
        discussyiliaoziyuanService.insert(discussyiliaoziyuan);
        return R.ok().put("data",discussyiliaoziyuan.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyiliaoziyuanEntity discussyiliaoziyuan = discussyiliaoziyuanService.selectOne(new EntityWrapper<DiscussyiliaoziyuanEntity>().eq("", username));
        return R.ok().put("data", discussyiliaoziyuan);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyiliaoziyuanEntity discussyiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyiliaoziyuan);
        //全部更新
        discussyiliaoziyuanService.updateById(discussyiliaoziyuan);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除医疗资源评论")
    public R delete(@RequestBody Long[] ids){
        discussyiliaoziyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyiliaoziyuanEntity discussyiliaoziyuan, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyiliaoziyuanEntity> ew = new EntityWrapper<DiscussyiliaoziyuanEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussyiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyiliaoziyuan), params), params));
        return R.ok().put("data", page);
    }









}
