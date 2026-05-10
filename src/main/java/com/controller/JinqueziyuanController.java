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

import com.entity.JinqueziyuanEntity;
import com.entity.view.JinqueziyuanView;

import com.service.JinqueziyuanService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 紧缺资源
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@RestController
@RequestMapping("/jinqueziyuan")
public class JinqueziyuanController {
    @Autowired
    private JinqueziyuanService jinqueziyuanService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JinqueziyuanEntity jinqueziyuan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JinqueziyuanEntity> ew = new EntityWrapper<JinqueziyuanEntity>();


        //查询结果
		PageUtils page = jinqueziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinqueziyuan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JinqueziyuanEntity jinqueziyuan,
                @RequestParam(required = false) Double ziyuanjiagestart,
                @RequestParam(required = false) Double ziyuanjiageend,
                @RequestParam(required = false) Double paiduirenshustart,
                @RequestParam(required = false) Double paiduirenshuend,
                @RequestParam(required = false) Double clicknumstart,
                @RequestParam(required = false) Double clicknumend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JinqueziyuanEntity> ew = new EntityWrapper<JinqueziyuanEntity>();
        if(ziyuanjiagestart!=null) ew.ge("ziyuanjiage", ziyuanjiagestart);
        if(ziyuanjiageend!=null) ew.le("ziyuanjiage", ziyuanjiageend);
        if(paiduirenshustart!=null) ew.ge("paiduirenshu", paiduirenshustart);
        if(paiduirenshuend!=null) ew.le("paiduirenshu", paiduirenshuend);
        if(clicknumstart!=null) ew.ge("clicknum", clicknumstart);
        if(clicknumend!=null) ew.le("clicknum", clicknumend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = jinqueziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinqueziyuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JinqueziyuanEntity jinqueziyuan){
       	EntityWrapper<JinqueziyuanEntity> ew = new EntityWrapper<JinqueziyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jinqueziyuan, "jinqueziyuan"));
        return R.ok().put("data", jinqueziyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JinqueziyuanEntity jinqueziyuan){
        EntityWrapper< JinqueziyuanEntity> ew = new EntityWrapper< JinqueziyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jinqueziyuan, "jinqueziyuan"));
		JinqueziyuanView jinqueziyuanView =  jinqueziyuanService.selectView(ew);
		return R.ok("查询紧缺资源成功").put("data", jinqueziyuanView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JinqueziyuanEntity jinqueziyuan = jinqueziyuanService.selectById(id);
        if(null==jinqueziyuan.getClicknum()){
            jinqueziyuan.setClicknum(0);
        }
		jinqueziyuan.setClicknum(jinqueziyuan.getClicknum()+1);
		jinqueziyuanService.updateById(jinqueziyuan);
        jinqueziyuan = jinqueziyuanService.selectView(new EntityWrapper<JinqueziyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinqueziyuan,deSens);
        return R.ok().put("data", jinqueziyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JinqueziyuanEntity jinqueziyuan = jinqueziyuanService.selectById(id);
        if(null==jinqueziyuan.getClicknum()){
            jinqueziyuan.setClicknum(0);
        }
		jinqueziyuan.setClicknum(jinqueziyuan.getClicknum()+1);
		jinqueziyuanService.updateById(jinqueziyuan);
        jinqueziyuan = jinqueziyuanService.selectView(new EntityWrapper<JinqueziyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinqueziyuan,deSens);
        return R.ok().put("data", jinqueziyuan);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增紧缺资源")
    public R save(@RequestBody JinqueziyuanEntity jinqueziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jinqueziyuan);
        jinqueziyuanService.insert(jinqueziyuan);
        return R.ok().put("data",jinqueziyuan.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增紧缺资源")
    @RequestMapping("/add")
    public R add(@RequestBody JinqueziyuanEntity jinqueziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jinqueziyuan);
        jinqueziyuanService.insert(jinqueziyuan);
        return R.ok().put("data",jinqueziyuan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改紧缺资源")
    public R update(@RequestBody JinqueziyuanEntity jinqueziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jinqueziyuan);
        //全部更新
        jinqueziyuanService.updateById(jinqueziyuan);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除紧缺资源")
    public R delete(@RequestBody Long[] ids){
        jinqueziyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
