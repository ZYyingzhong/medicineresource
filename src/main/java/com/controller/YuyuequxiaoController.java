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

import com.entity.YuyuequxiaoEntity;
import com.entity.view.YuyuequxiaoView;

import com.service.YuyuequxiaoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 预约取消
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@RestController
@RequestMapping("/yuyuequxiao")
public class YuyuequxiaoController {
    @Autowired
    private YuyuequxiaoService yuyuequxiaoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuequxiaoEntity yuyuequxiao,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date quxiaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date quxiaoshijianend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huanzhe")) {
			yuyuequxiao.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yisheng")) {
			yuyuequxiao.setYishenggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
        if(quxiaoshijianstart!=null) ew.ge("quxiaoshijian", quxiaoshijianstart);
        if(quxiaoshijianend!=null) ew.le("quxiaoshijian", quxiaoshijianend);


        //查询结果
		PageUtils page = yuyuequxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequxiao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YuyuequxiaoEntity yuyuequxiao,
                @RequestParam(required = false) Double ziyuanjiagestart,
                @RequestParam(required = false) Double ziyuanjiageend,
                @RequestParam(required = false) Double ziyuanshuliangstart,
                @RequestParam(required = false) Double ziyuanshuliangend,
                @RequestParam(required = false) Double tuidingjinestart,
                @RequestParam(required = false) Double tuidingjineend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date quxiaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date quxiaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
        if(ziyuanjiagestart!=null) ew.ge("ziyuanjiage", ziyuanjiagestart);
        if(ziyuanjiageend!=null) ew.le("ziyuanjiage", ziyuanjiageend);
        if(ziyuanshuliangstart!=null) ew.ge("ziyuanshuliang", ziyuanshuliangstart);
        if(ziyuanshuliangend!=null) ew.le("ziyuanshuliang", ziyuanshuliangend);
        if(tuidingjinestart!=null) ew.ge("tuidingjine", tuidingjinestart);
        if(tuidingjineend!=null) ew.le("tuidingjine", tuidingjineend);
        if(quxiaoshijianstart!=null) ew.ge("quxiaoshijian", quxiaoshijianstart);
        if(quxiaoshijianend!=null) ew.le("quxiaoshijian", quxiaoshijianend);

        //查询结果
		PageUtils page = yuyuequxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuequxiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuequxiaoEntity yuyuequxiao){
       	EntityWrapper<YuyuequxiaoEntity> ew = new EntityWrapper<YuyuequxiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuequxiao, "yuyuequxiao"));
        return R.ok().put("data", yuyuequxiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuequxiaoEntity yuyuequxiao){
        EntityWrapper< YuyuequxiaoEntity> ew = new EntityWrapper< YuyuequxiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuequxiao, "yuyuequxiao"));
		YuyuequxiaoView yuyuequxiaoView =  yuyuequxiaoService.selectView(ew);
		return R.ok("查询预约取消成功").put("data", yuyuequxiaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuyuequxiao,deSens);
        return R.ok().put("data", yuyuequxiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuyuequxiao,deSens);
        return R.ok().put("data", yuyuequxiao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增预约取消")
    public R save(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuequxiao);
        yuyuequxiaoService.insert(yuyuequxiao);
        return R.ok().put("data",yuyuequxiao.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增预约取消")
    @RequestMapping("/add")
    public R add(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuequxiao);
        yuyuequxiaoService.insert(yuyuequxiao);
        return R.ok().put("data",yuyuequxiao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改预约取消")
    public R update(@RequestBody YuyuequxiaoEntity yuyuequxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuequxiao);
        //全部更新
        yuyuequxiaoService.updateById(yuyuequxiao);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核预约取消")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuequxiaoEntity> list = new ArrayList<YuyuequxiaoEntity>();
        for(Long id : ids) {
            YuyuequxiaoEntity yuyuequxiao = yuyuequxiaoService.selectById(id);
            yuyuequxiao.setSfsh(sfsh);
            yuyuequxiao.setShhf(shhf);
            list.add(yuyuequxiao);
        }
        yuyuequxiaoService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除预约取消")
    public R delete(@RequestBody Long[] ids){
        yuyuequxiaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
