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
import com.utils.UserBasedCollaborativeFiltering;
import com.algorithm.recommend.RecommendAlgorithmFactory;

import com.entity.YiliaoziyuanEntity;
import com.entity.view.YiliaoziyuanView;

import com.service.YiliaoziyuanService;
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
 * 医疗资源
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@RestController
@RequestMapping("/yiliaoziyuan")
public class YiliaoziyuanController {
    @Autowired
    private YiliaoziyuanService yiliaoziyuanService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YiliaoziyuanEntity yiliaoziyuan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YiliaoziyuanEntity> ew = new EntityWrapper<YiliaoziyuanEntity>();


        //查询结果
		PageUtils page = yiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yiliaoziyuan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YiliaoziyuanEntity yiliaoziyuan,
                @RequestParam(required = false) Double ziyuanjiagestart,
                @RequestParam(required = false) Double ziyuanjiageend,
                @RequestParam(required = false) Double ziyuanshuliangstart,
                @RequestParam(required = false) Double ziyuanshuliangend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimeend,
                @RequestParam(required = false) Double clicknumstart,
                @RequestParam(required = false) Double clicknumend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YiliaoziyuanEntity> ew = new EntityWrapper<YiliaoziyuanEntity>();
        if(ziyuanjiagestart!=null) ew.ge("ziyuanjiage", ziyuanjiagestart);
        if(ziyuanjiageend!=null) ew.le("ziyuanjiage", ziyuanjiageend);
        if(ziyuanshuliangstart!=null) ew.ge("ziyuanshuliang", ziyuanshuliangstart);
        if(ziyuanshuliangend!=null) ew.le("ziyuanshuliang", ziyuanshuliangend);
        if(clicktimestart!=null) ew.ge("clicktime", clicktimestart);
        if(clicktimeend!=null) ew.le("clicktime", clicktimeend);
        if(clicknumstart!=null) ew.ge("clicknum", clicknumstart);
        if(clicknumend!=null) ew.le("clicknum", clicknumend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = yiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yiliaoziyuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YiliaoziyuanEntity yiliaoziyuan){
       	EntityWrapper<YiliaoziyuanEntity> ew = new EntityWrapper<YiliaoziyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yiliaoziyuan, "yiliaoziyuan"));
        return R.ok().put("data", yiliaoziyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YiliaoziyuanEntity yiliaoziyuan){
        EntityWrapper< YiliaoziyuanEntity> ew = new EntityWrapper< YiliaoziyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yiliaoziyuan, "yiliaoziyuan"));
		YiliaoziyuanView yiliaoziyuanView =  yiliaoziyuanService.selectView(ew);
		return R.ok("查询医疗资源成功").put("data", yiliaoziyuanView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YiliaoziyuanEntity yiliaoziyuan = yiliaoziyuanService.selectById(id);
        if(null==yiliaoziyuan.getClicknum()){
            yiliaoziyuan.setClicknum(0);
        }
		yiliaoziyuan.setClicknum(yiliaoziyuan.getClicknum()+1);
		yiliaoziyuanService.updateById(yiliaoziyuan);
        yiliaoziyuan = yiliaoziyuanService.selectView(new EntityWrapper<YiliaoziyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yiliaoziyuan,deSens);
        return R.ok().put("data", yiliaoziyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YiliaoziyuanEntity yiliaoziyuan = yiliaoziyuanService.selectById(id);
        if(null==yiliaoziyuan.getClicknum()){
            yiliaoziyuan.setClicknum(0);
        }
		yiliaoziyuan.setClicknum(yiliaoziyuan.getClicknum()+1);
		yiliaoziyuanService.updateById(yiliaoziyuan);
        yiliaoziyuan = yiliaoziyuanService.selectView(new EntityWrapper<YiliaoziyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yiliaoziyuan,deSens);
        return R.ok().put("data", yiliaoziyuan);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增医疗资源")
    public R save(@RequestBody YiliaoziyuanEntity yiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yiliaoziyuan);
        yiliaoziyuanService.insert(yiliaoziyuan);
        return R.ok().put("data",yiliaoziyuan.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增医疗资源")
    @RequestMapping("/add")
    public R add(@RequestBody YiliaoziyuanEntity yiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yiliaoziyuan);
        yiliaoziyuanService.insert(yiliaoziyuan);
        return R.ok().put("data",yiliaoziyuan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改医疗资源")
    public R update(@RequestBody YiliaoziyuanEntity yiliaoziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yiliaoziyuan);
        //全部更新
        yiliaoziyuanService.updateById(yiliaoziyuan);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除医疗资源")
    public R delete(@RequestBody Long[] ids){
        yiliaoziyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, @PathVariable("type") String type, HttpServletRequest request) {

        Wrapper<YiliaoziyuanEntity> wrapper = new EntityWrapper<YiliaoziyuanEntity>();

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

        //根据条件，查询结果
        List<YiliaoziyuanEntity> list = yiliaoziyuanService.selectList(wrapper);
        Map<String,Object> res= new HashMap<>();
        res.put("count", list.size());
        res.put("data",list.stream().map(YiliaoziyuanEntity::getZiyuanmingcheng).collect(Collectors.toList()));
        return R.ok(res);
	}
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YiliaoziyuanEntity yiliaoziyuan, HttpServletRequest request,String pre){
        EntityWrapper<YiliaoziyuanEntity> ew = new EntityWrapper<YiliaoziyuanEntity>();
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
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = yiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yiliaoziyuan), params), params));
        return R.ok().put("data", page);
    }



    /**
     * 协同算法（基于用户的协同算法：购买、收藏、关注）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,YiliaoziyuanEntity yiliaoziyuan, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        //用户行为数据
        List<RecommendAlgorithmFactory.UserBehavior> userBehaviors = new ArrayList<>();
        // 查询收藏/关注数据
        userBehaviors.addAll(storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1 ).eq("tablename", "yiliaoziyuan")).stream().map(storeup -> {
            return new RecommendAlgorithmFactory.UserBehavior(storeup.getUserid(), storeup.getRefid());
        }).collect(Collectors.toList()));
        // 根据物品推荐用户
        List<Long> recommendations = new ArrayList<>();
        // 用协 算法推荐物品id
        RecommendAlgorithmFactory.UserItemMatrixRecommender recommender = RecommendAlgorithmFactory.buildUserItemMatrix(userBehaviors);
        // 输出矩阵
        recommender.printCollectionMatrix();
        // 输出相似度矩阵
        recommender.printSimilarityMatrix();
        // 输出指定用户的余弦相似度详情
        recommender.printDetailedCosineSimilarity(Long.parseLong(userId));
        // 获取推荐结果（带得分）
        List<Map<String, Object>> itemIds = recommender.recommendItemsWithDetails(Long.parseLong(userId), limit);
        recommendations = recommender.recommendItems(Long.parseLong(userId), limit);
        // 输出推荐结果
        System.out.println("Recommendations for " + userId + ":");
        recommendations.forEach(recommendation -> System.out.println(recommendation));
        EntityWrapper<YiliaoziyuanEntity> ew = new EntityWrapper<YiliaoziyuanEntity>();

        if(recommendations!=null && recommendations.size()>0) {
            ew.in("id", recommendations);
            ew.last("order by FIELD(id, "+String.join(",", recommendations.stream().map(String::valueOf).collect(Collectors.toList()))+")");
        }
        // 根据协同结果查询结果并返回
        PageUtils page = yiliaoziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yiliaoziyuan), params), params));
        List<YiliaoziyuanEntity> pageList = (List<YiliaoziyuanEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<YiliaoziyuanEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(yiliaoziyuanService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);
        return R.ok().put("data", page);
    }






}
