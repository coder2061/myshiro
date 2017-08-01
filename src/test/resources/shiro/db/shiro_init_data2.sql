/*
Navicat MySQL Data Transfer

Source Server         : newhope
Source Server Version : 50636
Source Host           : 106.75.145.149:3306
Source Database       : jeecms

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-08-01 13:50:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission` varchar(64) NOT NULL COMMENT '权限',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：禁用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'menu:create', '创建菜单', '0');
INSERT INTO `sys_permission` VALUES ('2', 'menu:read', '查看菜单', '0');
INSERT INTO `sys_permission` VALUES ('3', 'menu:update', '修改菜单', '0');
INSERT INTO `sys_permission` VALUES ('4', 'menu:delete', '删除菜单', '0');
INSERT INTO `sys_permission` VALUES ('5', 'user:create', '创建用户', '0');
INSERT INTO `sys_permission` VALUES ('6', 'user:read', '查看用户', '0');
INSERT INTO `sys_permission` VALUES ('7', 'user:update', '修改用户', '0');
INSERT INTO `sys_permission` VALUES ('8', 'user:delete', '删除用户', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role` varchar(64) NOT NULL COMMENT '角色',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：禁用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '0');
INSERT INTO `sys_role` VALUES ('2', 'user', '用户', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('2', '5');
INSERT INTO `sys_role_permission` VALUES ('2', '6');
INSERT INTO `sys_role_permission` VALUES ('2', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '8');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'rambo', '31bcb253328987d2f44419451f5f3daf', 'b24fac9eeb421a81af13964c40e504e6', '0');
INSERT INTO `sys_user` VALUES ('2', 'jack', '98dbe38d9d528a184e5274f94d9bf595', 'aff4a21b2439113b0052148ef62463cf', '0');
INSERT INTO `sys_user` VALUES ('3', 'tom', '0a951870704e767bed64698472bd6d79', '7367ac5138de2b497d55a4eb4b5e5262', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
