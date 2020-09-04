package com.managesys.permissions.serviceImpl;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.managesys.permissions.dao.ILoginDao;
import com.managesys.permissions.entity.SysUserEntity;
import com.managesys.permissions.service.ILoginService;
import com.managesys.permissions.service.IPermisManageServcie;
import com.managesys.permissions.utils.MD5Util;
import com.managesys.permissions.utils.SysConstant;


@Service("loginService")
public class LoginServiceImpl  implements ILoginService{
   @Resource
    private ILoginDao  loginDao;
   @Resource
    private IPermisManageServcie permisServcie;
	public Map<String, Object> login(SysUserEntity sysUserEntity) {
		Map<String, Object> map =new HashMap<String, Object>();
		sysUserEntity.setStatus(SysConstant.DATA_VALID);
		SysUserEntity  entity = loginDao.findByUserName(sysUserEntity);
		if(null == entity){
			//用户不存在
			map.put("status", "0");
			map.put("message", "用户名不存在");
		}else if(!entity.getPassword().equals(MD5Util.encode(sysUserEntity.getPassword()).toLowerCase())){
			//密码不正确
			map.put("status", "0");
			map.put("message", "用户密码不正确");
		}else{
			//正确获取其下面的所有权限集合
		   map.put("status", "1");
		   map.put("message", "登录成功");
		   map.put("user", entity);
		   Map<String, Object>   map1 =   permisServcie.getUSerMenus(entity.getId());
		   map.put("paths", map1.get("paths"));
		   map.put("menuList", map1.get("menuList"));
		}
		return map;
	}
	
	
    
}
