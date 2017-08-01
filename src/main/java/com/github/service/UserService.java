package com.github.service;

import java.util.List;

import com.github.bo.UserBO;
import com.github.po.User;

/**
 * 用户
 * 
 * @author jiangyf
 * @date 2017年7月31日 下午12:30:19
 */
public interface UserService {

	User getUser(UserBO userBO);

	User checkGetUser(UserBO userBO);

	List<User> listUser(UserBO userBO);

	List<String> listRole(Long userId);

	List<String> listPermission(Long userId);

}
