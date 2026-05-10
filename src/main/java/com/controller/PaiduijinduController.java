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

import com.entity.PaiduijinduEntity;
import com.entity.view.PaiduijinduView;

import com.service.PaiduijinduService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 排队进度
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@RestController
@RequestMapping("/paiduijindu")
public class PaiduijinduController {
    @Autowired
    private PaiduijinduService paiduijinduService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PaiduijinduEntity paiduijindu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			paiduijindu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			paiduijindu.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PaiduijinduEntity> ew = new EntityWrapper<PaiduijinduEntity>();


        //查询结果
		PageUtils page = paiduijinduService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiduijindu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,PaiduijinduEntity paiduijindu,
                @RequestParam(required = false) Double ziyuanjiagestart,
                @RequestParam(required = false) Double ziyuanjiageend,
                @RequestParam(required = false) Double paiduixuhaostart,
                @RequestParam(required = false) Double paiduixuhaoend,
                @RequestParam(required = false) Double muqianpaixustart,
                @RequestParam(required = false) Double muqianpaixuend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date gengxinshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date gengxinshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PaiduijinduEntity> ew = new EntityWrapper<PaiduijinduEntity>();
        if(ziyuanjiagestart!=null) ew.ge("ziyuanjiage", ziyuanjiagestart);
        if(ziyuanjiageend!=null) ew.le("ziyuanjiage", ziyuanjiageend);
        if(paiduixuhaostart!=null) ew.ge("paiduixuhao", paiduixuhaostart);
        if(paiduixuhaoend!=null) ew.le("paiduixuhao", paiduixuhaoend);
        if(muqianpaixustart!=null) ew.ge("muqianpaixu", muqianpaixustart);
        if(muqianpaixuend!=null) ew.le("muqianpaixu", muqianpaixuend);
        if(gengxinshijianstart!=null) ew.ge("gengxinshijian", gengxinshijianstart);
        if(gengxinshijianend!=null) ew.le("gengxinshijian", gengxinshijianend);

        //查询结果
		PageUtils page = paiduijinduService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiduijindu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PaiduijinduEntity paiduijindu){
       	EntityWrapper<PaiduijinduEntity> ew = new EntityWrapper<PaiduijinduEntity>();
      	ew.allEq(MPUtil.allEQMapPre( paiduijindu, "paiduijindu"));
        return R.ok().put("data", paiduijinduService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PaiduijinduEntity paiduijindu){
        EntityWrapper< PaiduijinduEntity> ew = new EntityWrapper< PaiduijinduEntity>();
 		ew.allEq(MPUtil.allEQMapPre( paiduijindu, "paiduijindu"));
		PaiduijinduView paiduijinduView =  paiduijinduService.selectView(ew);
		return R.ok("查询排队进度成功").put("data", paiduijinduView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PaiduijinduEntity paiduijindu = paiduijinduService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(paiduijindu,deSens);
        return R.ok().put("data", paiduijindu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PaiduijinduEntity paiduijindu = paiduijinduService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(paiduijindu,deSens);
        return R.ok().put("data", paiduijindu);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增排队进度")
    public R save(@RequestBody PaiduijinduEntity paiduijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduijindu);
        paiduijinduService.insert(paiduijindu);
        return R.ok().put("data",paiduijindu.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增排队进度")
    @RequestMapping("/add")
    public R add(@RequestBody PaiduijinduEntity paiduijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduijindu);
        paiduijinduService.insert(paiduijindu);
        return R.ok().put("data",paiduijindu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改排队进度")
    public R update(@RequestBody PaiduijinduEntity paiduijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiduijindu);
        //全部更新
        paiduijinduService.updateById(paiduijindu);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除排队进度")
    public R delete(@RequestBody Long[] ids){
        paiduijinduService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, @PathVariable("type") String type, HttpServletRequest request) {

        Wrapper<PaiduijinduEntity> wrapper = new EntityWrapper<PaiduijinduEntity>();

        Map<String, Object> params = CommonUtil.parseRequest(request);
        // 从map中获取remindStart和remindEnd，组装提醒过滤条件
        Object remindStart = params.get("remindstart") != null ? params.get("remindstart").toString() : null;
        Object remindEnd = params.get("remindend") != null ? params.get("remindend").toString() : null;
        params.remove("remindstart");
        params.remove("remindend");
        params.keySet().forEach(key -> {
            if (params.get(key) != null && params.get(key) != "") {
                wrapper.eq(key, params.get(key));
            }
        });
        switch (type) {
            case "1":
                if (remindStart != null && remindEnd != null) {
                    if (Double.parseDouble(remindStart.toString()) < Double.parseDouble(remindEnd.toString())) {
                        wrapper.ge(columnName, remindStart).le(columnName, remindEnd);
                    } else {
                        wrapper.ge(columnName, remindStart).or().le(columnName, remindEnd);
                    }
                } else if (remindStart == null && remindEnd != null) {
                    wrapper.le(columnName, remindEnd);
                } else if (remindStart != null && remindEnd == null) {
                    wrapper.ge(columnName, remindStart);
                }
                break;
            case "2":
                Date startDate = null;
                Date endDate = null;
                if (null != remindStart) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindStart.toString()));
                    startDate = c.getTime();
                }
                if (null != remindEnd) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindEnd.toString()));
                    endDate = c.getTime();
                }
                if (startDate != null && endDate != null) {
                    if (startDate.before(endDate)) {
                        wrapper.ge(columnName, startDate).le(columnName, endDate);
                    } else {
                        wrapper.ge(columnName, startDate).or().le(columnName, endDate);
                    }
                } else if (startDate == null && endDate != null) {
                    wrapper.le(columnName, endDate);
                } else if (startDate != null && endDate == null) {
                    wrapper.ge(columnName, startDate);
                }
                break;
            case "3":
                wrapper.in(columnName, Arrays.asList(remindStart.toString().replace("，",",").split(",")));
                break;
        }

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			wrapper.andNew().eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			wrapper.andNew().eq("yishenggonghao", (String)request.getSession().getAttribute("username"));
		}
        //根据条件，查询结果
        List<PaiduijinduEntity> list = paiduijinduService.selectList(wrapper);
        Map<String,Object> res= new HashMap<>();
        res.put("count", list.size());
        res.put("data",list.stream().map(PaiduijinduEntity::getZiyuanmingcheng).collect(Collectors.toList()));
        return R.ok(res);
	}









}
