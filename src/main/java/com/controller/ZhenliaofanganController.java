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

import com.entity.ZhenliaofanganEntity;
import com.entity.view.ZhenliaofanganView;

import com.service.ZhenliaofanganService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 诊疗方案
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@RestController
@RequestMapping("/zhenliaofangan")
public class ZhenliaofanganController {
    @Autowired
    private ZhenliaofanganService zhenliaofanganService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhenliaofanganEntity zhenliaofangan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			zhenliaofangan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			zhenliaofangan.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZhenliaofanganEntity> ew = new EntityWrapper<ZhenliaofanganEntity>();


        //查询结果
		PageUtils page = zhenliaofanganService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenliaofangan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZhenliaofanganEntity zhenliaofangan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhenliaofanganEntity> ew = new EntityWrapper<ZhenliaofanganEntity>();

        //查询结果
		PageUtils page = zhenliaofanganService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhenliaofangan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhenliaofanganEntity zhenliaofangan){
       	EntityWrapper<ZhenliaofanganEntity> ew = new EntityWrapper<ZhenliaofanganEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhenliaofangan, "zhenliaofangan"));
        return R.ok().put("data", zhenliaofanganService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhenliaofanganEntity zhenliaofangan){
        EntityWrapper< ZhenliaofanganEntity> ew = new EntityWrapper< ZhenliaofanganEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhenliaofangan, "zhenliaofangan"));
		ZhenliaofanganView zhenliaofanganView =  zhenliaofanganService.selectView(ew);
		return R.ok("查询诊疗方案成功").put("data", zhenliaofanganView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhenliaofanganEntity zhenliaofangan = zhenliaofanganService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhenliaofangan,deSens);
        return R.ok().put("data", zhenliaofangan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhenliaofanganEntity zhenliaofangan = zhenliaofanganService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhenliaofangan,deSens);
        return R.ok().put("data", zhenliaofangan);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增诊疗方案")
    public R save(@RequestBody ZhenliaofanganEntity zhenliaofangan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhenliaofangan);
        zhenliaofanganService.insert(zhenliaofangan);
        return R.ok().put("data",zhenliaofangan.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增诊疗方案")
    @RequestMapping("/add")
    public R add(@RequestBody ZhenliaofanganEntity zhenliaofangan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhenliaofangan);
        zhenliaofanganService.insert(zhenliaofangan);
        return R.ok().put("data",zhenliaofangan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改诊疗方案")
    public R update(@RequestBody ZhenliaofanganEntity zhenliaofangan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhenliaofangan);
        //全部更新
        zhenliaofanganService.updateById(zhenliaofangan);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除诊疗方案")
    public R delete(@RequestBody Long[] ids){
        zhenliaofanganService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
