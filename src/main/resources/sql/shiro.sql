/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-10-13 14:37:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission` varchar(64) NOT NULL COMMENT '权限',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：禁用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shiro_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES ('1', 'menu:create', '创建菜单', '0');
INSERT INTO `shiro_permission` VALUES ('2', 'menu:read', '查看菜单', '0');
INSERT INTO `shiro_permission` VALUES ('3', 'menu:update', '修改菜单', '0');
INSERT INTO `shiro_permission` VALUES ('4', 'menu:delete', '删除菜单', '0');
INSERT INTO `shiro_permission` VALUES ('5', 'user:create', '创建用户', '0');
INSERT INTO `shiro_permission` VALUES ('6', 'user:read', '查看用户', '0');
INSERT INTO `shiro_permission` VALUES ('7', 'user:update', '修改用户', '0');
INSERT INTO `shiro_permission` VALUES ('8', 'user:delete', '删除用户', '0');

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role` varchar(64) NOT NULL COMMENT '角色',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：禁用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shiro_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('1', 'admin', '管理员', '0');
INSERT INTO `shiro_role` VALUES ('2', 'user', '用户', '0');

-- ----------------------------
-- Table structure for shiro_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_permission`;
CREATE TABLE `shiro_role_permission` (
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of shiro_role_permission
-- ----------------------------
INSERT INTO `shiro_role_permission` VALUES ('1', '1');
INSERT INTO `shiro_role_permission` VALUES ('1', '2');
INSERT INTO `shiro_role_permission` VALUES ('1', '3');
INSERT INTO `shiro_role_permission` VALUES ('1', '4');
INSERT INTO `shiro_role_permission` VALUES ('1', '5');
INSERT INTO `shiro_role_permission` VALUES ('1', '6');
INSERT INTO `shiro_role_permission` VALUES ('1', '7');
INSERT INTO `shiro_role_permission` VALUES ('1', '8');
INSERT INTO `shiro_role_permission` VALUES ('2', '5');
INSERT INTO `shiro_role_permission` VALUES ('2', '6');
INSERT INTO `shiro_role_permission` VALUES ('2', '7');
INSERT INTO `shiro_role_permission` VALUES ('2', '8');

-- ----------------------------
-- Table structure for shiro_session
-- ----------------------------
DROP TABLE IF EXISTS `shiro_session`;
CREATE TABLE `shiro_session` (
  `id` varchar(100) NOT NULL COMMENT '会话id',
  `session` varchar(2000) NOT NULL COMMENT '会话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会话表';

-- ----------------------------
-- Records of shiro_session
-- ----------------------------
INSERT INTO `shiro_session` VALUES ('51c1fe7a-f56b-4d03-86b5-5dff0b6d7234', 'rO0ABXNyAC9jb20uZ2l0aHViLnNoaXJvLnNlc3Npb24uaW5zdGFuY2UuT25saW5lU2Vzc2lvbhzwa0JYMsGRAwADTAAGc3RhdHVzdAApTGNvbS9naXRodWIvc2hpcm8vZW51bXMvT25saW5lU3RhdHVzRW51bTtMAApzeXN0ZW1Ib3N0dAASTGphdmEvbGFuZy9TdHJpbmc7TAAJdXNlckFnZW50cQB+AAJ4cgAqb3JnLmFwYWNoZS5zaGlyby5zZXNzaW9uLm1ndC5TaW1wbGVTZXNzaW9unRyhuNWMYm4DAAB4cHcCANt0ACQ1MWMxZmU3YS1mNTZiLTRkMDMtODZiNS01ZGZmMGI2ZDcyMzRzcgAOamF2YS51dGlsLkRhdGVoaoEBS1l0GQMAAHhwdwgAAAFfC0f9/3hxAH4AB3cZAAAAAAAbd0AADzA6MDowOjA6MDowOjA6MXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAPMDowOjA6MDowOjA6MDoxc3IAG2NvbS5naXRodWIuYmVhbi5SZXF1ZXN0SW5mb6Bcdw64ALVwAgADTAAFY291bnR0ABNMamF2YS9sYW5nL0ludGVnZXI7TAAKY3JlYXRlVGltZXQAEExqYXZhL2xhbmcvTG9uZztMAAJpcHEAfgACeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXNyAA5qYXZhLmxhbmcuTG9uZzuL5JDMjyPfAgABSgAFdmFsdWV4cQB+ABAAAAFfC0f+U3EAfgAKeHh+cgAnY29tLmdpdGh1Yi5zaGlyby5lbnVtcy5PbmxpbmVTdGF0dXNFbnVtAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAGT05MSU5FdAAUMDowOjA6MDowOjA6MDoxOjgwODB0AEhNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0OyBydjo1Ni4wKSBHZWNrby8yMDEwMDEwMSBGaXJlZm94LzU2LjB3AgADcQB+ABlxAH4AFng=');
INSERT INTO `shiro_session` VALUES ('a7d8f8d1-952e-4217-b415-921b25beeb92', 'rO0ABXNyAC9jb20uZ2l0aHViLnNoaXJvLnNlc3Npb24uaW5zdGFuY2UuT25saW5lU2Vzc2lvbhzwa0JYMsGRAwADTAAGc3RhdHVzdAApTGNvbS9naXRodWIvc2hpcm8vZW51bXMvT25saW5lU3RhdHVzRW51bTtMAApzeXN0ZW1Ib3N0dAASTGphdmEvbGFuZy9TdHJpbmc7TAAJdXNlckFnZW50cQB+AAJ4cgAqb3JnLmFwYWNoZS5zaGlyby5zZXNzaW9uLm1ndC5TaW1wbGVTZXNzaW9unRyhuNWMYm4DAAB4cHcCANt0ACRhN2Q4ZjhkMS05NTJlLTQyMTctYjQxNS05MjFiMjViZWViOTJzcgAOamF2YS51dGlsLkRhdGVoaoEBS1l0GQMAAHhwdwgAAAFfC00PH3hxAH4AB3cZAAAAAAAbd0AADzA6MDowOjA6MDowOjA6MXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAPMDowOjA6MDowOjA6MDoxc3IAG2NvbS5naXRodWIuYmVhbi5SZXF1ZXN0SW5mb6Bcdw64ALVwAgADTAAFY291bnR0ABNMamF2YS9sYW5nL0ludGVnZXI7TAAKY3JlYXRlVGltZXQAEExqYXZhL2xhbmcvTG9uZztMAAJpcHEAfgACeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXNyAA5qYXZhLmxhbmcuTG9uZzuL5JDMjyPfAgABSgAFdmFsdWV4cQB+ABAAAAFfC00Pk3EAfgAKeHh+cgAnY29tLmdpdGh1Yi5zaGlyby5lbnVtcy5PbmxpbmVTdGF0dXNFbnVtAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAGT05MSU5FdAAUMDowOjA6MDowOjA6MDoxOjgwODB0AEhNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0OyBydjo1Ni4wKSBHZWNrby8yMDEwMDEwMSBGaXJlZm94LzU2LjB3AgADcQB+ABlxAH4AFng=');
INSERT INTO `shiro_session` VALUES ('cd244ec4-40d9-4bab-982d-1b67b2bc64e9', 'rO0ABXNyAC9jb20uZ2l0aHViLnNoaXJvLnNlc3Npb24uaW5zdGFuY2UuT25saW5lU2Vzc2lvbhzwa0JYMsGRAwADTAAGc3RhdHVzdAApTGNvbS9naXRodWIvc2hpcm8vZW51bXMvT25saW5lU3RhdHVzRW51bTtMAApzeXN0ZW1Ib3N0dAASTGphdmEvbGFuZy9TdHJpbmc7TAAJdXNlckFnZW50cQB+AAJ4cgAqb3JnLmFwYWNoZS5zaGlyby5zZXNzaW9uLm1ndC5TaW1wbGVTZXNzaW9unRyhuNWMYm4DAAB4cHcCANt0ACRjZDI0NGVjNC00MGQ5LTRiYWItOTgyZC0xYjY3YjJiYzY0ZTlzcgAOamF2YS51dGlsLkRhdGVoaoEBS1l0GQMAAHhwdwgAAAFfC04fVXhzcQB+AAZ3CAAAAV8LYl46eHcZAAAAAAAbd0AADzA6MDowOjA6MDowOjA6MXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAAPMDowOjA6MDowOjA6MDoxc3IAG2NvbS5naXRodWIuYmVhbi5SZXF1ZXN0SW5mb6Bcdw64ALVwAgADTAAFY291bnR0ABNMamF2YS9sYW5nL0ludGVnZXI7TAAKY3JlYXRlVGltZXQAEExqYXZhL2xhbmcvTG9uZztMAAJpcHEAfgACeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXNyAA5qYXZhLmxhbmcuTG9uZzuL5JDMjyPfAgABSgAFdmFsdWV4cQB+ABEAAAFfC2JeW3QADzA6MDowOjA6MDowOjA6MXh4fnIAJ2NvbS5naXRodWIuc2hpcm8uZW51bXMuT25saW5lU3RhdHVzRW51bQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQABk9OTElORXQAFDA6MDowOjA6MDowOjA6MTo4MDgwdABITW96aWxsYS81LjAgKFdpbmRvd3MgTlQgNi4xOyBXT1c2NDsgcnY6NTYuMCkgR2Vja28vMjAxMDAxMDEgRmlyZWZveC81Ni4wdwIAA3EAfgAbcQB+ABh4');

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shiro_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('1', 'rambo', 'cfd5815005cdbde61de52d0b6136c7c7', '8a8f330b67cf827f60225d690c634e11', '0');
INSERT INTO `shiro_user` VALUES ('2', 'jack', '98dbe38d9d528a184e5274f94d9bf595', 'aff4a21b2439113b0052148ef62463cf', '0');
INSERT INTO `shiro_user` VALUES ('3', 'tom', '0a951870704e767bed64698472bd6d79', '7367ac5138de2b497d55a4eb4b5e5262', '0');

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role` (
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES ('1', '1');
INSERT INTO `shiro_user_role` VALUES ('2', '2');
INSERT INTO `shiro_user_role` VALUES ('3', '2');
