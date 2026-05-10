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
import com.service.TokenService;
import com.entity.TokenEntity;
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

import com.entity.HuanzheEntity;
import com.entity.view.HuanzheView;

import com.service.HuanzheService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 患者
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@RestController
@RequestMapping("/huanzhe")
public class HuanzheController {
    @Autowired
    private HuanzheService huanzheService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", username));
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), username,"huanzhe",  "患者" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody HuanzheEntity huanzhe){
    	//ValidatorUtils.validateEntity(huanzhe);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同用户账号，否则返回错误信息
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
		Long uId = new Date().getTime();
		huanzhe.setId(uId);
        //设置登录密码md5方式加密
        huanzhe.setMima(EncryptUtil.md5(huanzhe.getMima()));
        //保存用户
        huanzheService.insert(huanzhe);
        return R.ok();
    }



	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        HuanzheEntity u = huanzheService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        huanzheService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe){
        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();
        Wrapper<HuanzheEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params);
        List<Map> list = huanzheService.selectList(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getYonghuzhanghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();


        //查询结果
		PageUtils page = huanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe,
                @RequestParam(required = false) Double nianlingstart,
                @RequestParam(required = false) Double nianlingend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();
        if(nianlingstart!=null) ew.ge("nianling", nianlingstart);
        if(nianlingend!=null) ew.le("nianling", nianlingend);

        //查询结果
		PageUtils page = huanzheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuanzheEntity huanzhe){
       	EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huanzhe, "huanzhe"));
        return R.ok().put("data", huanzheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuanzheEntity huanzhe){
        EntityWrapper< HuanzheEntity> ew = new EntityWrapper< HuanzheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huanzhe, "huanzhe"));
		HuanzheView huanzheView =  huanzheService.selectView(ew);
		return R.ok("查询患者成功").put("data", huanzheView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huanzhe,deSens);
        return R.ok().put("data", huanzhe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(huanzhe,deSens);
        return R.ok().put("data", huanzhe);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增患者")
    public R save(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        //ValidatorUtils.validateEntity(huanzhe);
        //验证账号唯一性，否则返回错误信息
        HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	huanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		huanzhe.setId(new Date().getTime());
        //密码使用md5方式加密
        huanzhe.setMima(EncryptUtil.md5(huanzhe.getMima()));
        huanzheService.insert(huanzhe);
        return R.ok().put("data",huanzhe.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增患者")
    @RequestMapping("/add")
    public R add(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        //ValidatorUtils.validateEntity(huanzhe);
        //验证账号唯一性，否则返回错误信息
        HuanzheEntity u = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	huanzhe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		huanzhe.setId(new Date().getTime());
        //密码使用md5方式加密
        huanzhe.setMima(EncryptUtil.md5(huanzhe.getMima()));
        huanzheService.insert(huanzhe);
        return R.ok().put("data",huanzhe.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        HuanzheEntity huanzhe = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("yonghuzhanghao", username));
        return R.ok().put("data", huanzhe);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huanzhe);
        //验证字段唯一性，否则返回错误信息
        if(huanzheService.selectCount(new EntityWrapper<HuanzheEntity>().ne("id", huanzhe.getId()).eq("yonghuzhanghao", huanzhe.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
	    HuanzheEntity huanzheEntity = huanzheService.selectById(huanzhe.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(huanzhe.getMima()) && !huanzhe.getMima().equals(huanzheEntity.getMima())) {
            //密码使用md5方式加密
            huanzhe.setMima(EncryptUtil.md5(huanzhe.getMima()));
        }
        //全部更新
        huanzheService.updateById(huanzhe);
        if(null!=huanzhe.getYonghuzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(huanzhe.getYonghuzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", huanzhe.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除患者")
    public R delete(@RequestBody Long[] ids){
        huanzheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }








    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,HuanzheEntity huanzhe, HttpServletRequest request){
        EntityWrapper<HuanzheEntity> ew = new EntityWrapper<HuanzheEntity>();
        int count = huanzheService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huanzhe), params), params));
        return R.ok().put("data", count);
    }


}
