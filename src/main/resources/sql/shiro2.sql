/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : shiro2

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-10-16 15:33:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shiro_organization
-- ----------------------------
DROP TABLE IF EXISTS `shiro_organization`;
CREATE TABLE `shiro_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织机构id',
  `name` varchar(100) NOT NULL COMMENT '组织机构名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级组织机构id',
  `parent_ids` varchar(100) NOT NULL COMMENT '上级组织机构id集',
  `priority` tinyint(4) NOT NULL DEFAULT '0' COMMENT '序号',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  KEY `idx_shiro_organization_parent_id` (`parent_id`),
  KEY `idx_shiro_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of shiro_organization
-- ----------------------------
INSERT INTO `shiro_organization` VALUES ('1', '总公司', '0', '0/', '0', '0');
INSERT INTO `shiro_organization` VALUES ('2', '分公司1', '1', '0/1/', '0', '0');
INSERT INTO `shiro_organization` VALUES ('3', '分公司2', '1', '0/1/', '0', '0');
INSERT INTO `shiro_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '0', '0');

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `type` varchar(10) NOT NULL COMMENT '资源类型（menu：菜单；button：按钮）',
  `level` varchar(10) NOT NULL COMMENT '级别（group：分组；mode：模块；method：方法）',
  `url` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级资源id',
  `parent_ids` varchar(100) NOT NULL COMMENT '上级资源id集',
  `access` varchar(100) NOT NULL COMMENT '资源权限',
  `priority` tinyint(4) NOT NULL DEFAULT '0' COMMENT '序号',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  KEY `idx_shiro_resource_parent_id` (`parent_id`),
  KEY `idx_shiro_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES ('1', '资源', 'menu', '', '', '0', '0/', '', '0', '0');
INSERT INTO `shiro_permission` VALUES ('11', '组织机构管理', 'menu', '', '/organization', '1', '0/1/', 'organization:*', '0', '0');
INSERT INTO `shiro_permission` VALUES ('12', '组织机构新增', 'button', '', '', '11', '0/1/11/', 'organization:create', '0', '0');
INSERT INTO `shiro_permission` VALUES ('13', '组织机构修改', 'button', '', '', '11', '0/1/11/', 'organization:update', '0', '0');
INSERT INTO `shiro_permission` VALUES ('14', '组织机构删除', 'button', '', '', '11', '0/1/11/', 'organization:delete', '0', '0');
INSERT INTO `shiro_permission` VALUES ('15', '组织机构查看', 'button', '', '', '11', '0/1/11/', 'organization:view', '0', '0');
INSERT INTO `shiro_permission` VALUES ('21', '用户管理', 'menu', '', '/user', '1', '0/1/', 'user:*', '0', '0');
INSERT INTO `shiro_permission` VALUES ('22', '用户新增', 'button', '', '', '21', '0/1/21/', 'user:create', '0', '0');
INSERT INTO `shiro_permission` VALUES ('23', '用户修改', 'button', '', '', '21', '0/1/21/', 'user:update', '0', '0');
INSERT INTO `shiro_permission` VALUES ('24', '用户删除', 'button', '', '', '21', '0/1/21/', 'user:delete', '0', '0');
INSERT INTO `shiro_permission` VALUES ('25', '用户查看', 'button', '', '', '21', '0/1/21/', 'user:view', '0', '0');
INSERT INTO `shiro_permission` VALUES ('31', '资源管理', 'menu', '', '/resource', '1', '0/1/', 'resource:*', '0', '0');
INSERT INTO `shiro_permission` VALUES ('32', '资源新增', 'button', '', '', '31', '0/1/31/', 'resource:create', '0', '0');
INSERT INTO `shiro_permission` VALUES ('33', '资源修改', 'button', '', '', '31', '0/1/31/', 'resource:update', '0', '0');
INSERT INTO `shiro_permission` VALUES ('34', '资源删除', 'button', '', '', '31', '0/1/31/', 'resource:delete', '0', '0');
INSERT INTO `shiro_permission` VALUES ('35', '资源查看', 'button', '', '', '31', '0/1/31/', 'resource:view', '0', '0');
INSERT INTO `shiro_permission` VALUES ('41', '角色管理', 'menu', '', '/role', '1', '0/1/', 'role:*', '0', '0');
INSERT INTO `shiro_permission` VALUES ('42', '角色新增', 'button', '', '', '41', '0/1/41/', 'role:create', '0', '0');
INSERT INTO `shiro_permission` VALUES ('43', '角色修改', 'button', '', '', '41', '0/1/41/', 'role:update', '0', '0');
INSERT INTO `shiro_permission` VALUES ('44', '角色删除', 'button', '', '', '41', '0/1/41/', 'role:delete', '0', '0');
INSERT INTO `shiro_permission` VALUES ('45', '角色查看', 'button', '', '', '41', '0/1/41/', 'role:view', '0', '0');

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `perssion_ids` varchar(500) NOT NULL COMMENT '权限id集',
  `priority` tinyint(4) NOT NULL DEFAULT '0' COMMENT '序号',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  KEY `idx_shiro_role_resource_ids` (`perssion_ids`(255))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('1', 'admin', '超级管理员', '11,21,31,41', '0', '0');

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐',
  `organization_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组织机构id',
  `role_ids` varchar(100) NOT NULL COMMENT '角色id集',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：正常，1：锁定）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_shiro_user_username` (`username`),
  KEY `idx_shiro_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1', '0');
