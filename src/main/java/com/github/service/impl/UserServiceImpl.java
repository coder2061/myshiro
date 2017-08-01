package com.github.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bo.UserBO;
import com.github.dao.UserDAO;
import com.github.po.User;
import com.github.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUser(UserBO userBO) {
		List<User> users = listUser(userBO);
		// TODO 用户不存在
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public User checkGetUser(UserBO userBO) {
		List<User> users = listUser(userBO);
		// TODO 用户不存在
		if (CollectionUtils.isEmpty(users)) {

		}
		return users.get(0);
	}

	@Override
	public List<User> listUser(UserBO userBO) {
		return userDAO.selectUser(userBO);
	}

	@Override
	public List<String> listRole(Long userId) {
		return userDAO.selectRoleByUserId(userId);
	}

	@Override
	public List<String> listPermission(Long userId) {
		return userDAO.selectPermissionByUserId(userId);
	}

}
