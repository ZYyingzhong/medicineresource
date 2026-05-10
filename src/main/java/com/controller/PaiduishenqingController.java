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

import com.entity.PaiduishenqingEntity;
import com.entity.view.PaiduishenqingView;

import com.service.PaiduishenqingService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 排队申请
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@RestController
@RequestMapping("/paiduishenqing")
public class PaiduishenqingController {
    @Autowired
    private PaiduishenqingService paiduishenqingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PaiduishenqingEntity paiduishenqing,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			paiduishenqing.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			paiduishenqing.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PaiduishenqingEntity> ew = new EntityWrapper<PaiduishenqingEntity>();


        //查询结果
		PageUtils page = paiduishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiduishenqing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,PaiduishenqingEntity paiduishenqing,
                @RequestParam(required = false) Double ziyuanjiagestart,
                @RequestParam(required = false) Double ziyuanjiageend,
                @RequestParam(required = false) Double paiduirenshustart,
                @RequestParam(required = false) Double paiduirenshuend,
                @RequestParam(required = false) Double paiduixuhaostart,
                @RequestParam(required = false) Double paiduixuhaoend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shenqingshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shenqingshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PaiduishenqingEntity> ew = new EntityWrapper<PaiduishenqingEntity>();
        if(ziyuanjiagestart!=null) ew.ge("ziyuanjiage", ziyuanjiagestart);
        if(ziyuanjiageend!=null) ew.le("ziyuanjiage", ziyuanjiageend);
        if(paiduirenshustart!=null) ew.ge("paiduirenshu", paiduirenshustart);
        if(paiduirenshuend!=null) ew.le("paiduirenshu", paiduirenshuend);
        if(paiduixuhaostart!=null) ew.ge("paiduixuhao", paiduixuhaostart);
        if(paiduixuhaoend!=null) ew.le("paiduixuhao", paiduixuhaoend);
        if(shenqingshijianstart!=null) ew.ge("shenqingshijian", shenqingshijianstart);
        if(shenqingshijianend!=null) ew.le("shenqingshijian", shenqingshijianend);

        //查询结果
		PageUtils page = paiduishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiduishenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PaiduishenqingEntity paiduishenqing){
       	EntityWrapper<PaiduishenqingEntity> ew = new EntityWrapper<PaiduishenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( paiduishenqing, "paiduishenqing"));
        return R.ok().put("data", paiduishenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PaiduishenqingEntity paiduishenqing){
        EntityWrapper< PaiduishenqingEntity> ew = new EntityWrapper< PaiduishenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( paiduishenqing, "paiduishenqing"));
		PaiduishenqingView paiduishenqingView =  paiduishenqingService.selectView(ew);
		return R.ok("查询排队申请成功").put("data", paiduishenqingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PaiduishenqingEntity paiduishenqing = paiduishenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(paiduishenqing,deSens);
        return R.ok().put("data", paiduishenqing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PaiduishenqingEntity paiduishenqing = paiduishenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(paiduishenqing,deSens);
        return R.ok().put("data", paiduishenqing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增排队申请")
    public R save(@RequestBody PaiduishenqingEntity paiduishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduishenqing);
        paiduishenqingService.insert(paiduishenqing);
        return R.ok().put("data",paiduishenqing.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增排队申请")
    @RequestMapping("/add")
    public R add(@RequestBody PaiduishenqingEntity paiduishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduishenqing);
        paiduishenqingService.insert(paiduishenqing);
        return R.ok().put("data",paiduishenqing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改排队申请")
    public R update(@RequestBody PaiduishenqingEntity paiduishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduishenqing);
        //全部更新
        paiduishenqingService.updateById(paiduishenqing);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除排队申请")
    public R delete(@RequestBody Long[] ids){
        paiduishenqingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
