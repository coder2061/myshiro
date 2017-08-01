package com.github.dao;

import com.github.bo.UserBO;
import com.github.po.User;
import com.github.po.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> selectUser(UserBO userBO);

	List<String> selectRoleByUserId(Long userId);

	List<String> selectPermissionByUserId(Long userId);
}