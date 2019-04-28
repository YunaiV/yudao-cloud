/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_admin

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 28/04/2019 18:05:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(16) NOT NULL COMMENT '登陆账号',
  `nickname` varchar(10) NOT NULL COMMENT '昵称',
  `password` varchar(32) NOT NULL COMMENT '密码\n     *\n     * TODO 芋艿 暂时最简单的 MD5',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(11) NOT NULL COMMENT '账号状态',
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='admin_d_o';

-- ----------------------------
-- Table structure for admin_access_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_access_log`;
CREATE TABLE `admin_access_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(11) NOT NULL DEFAULT '-1' COMMENT '管理员编号.',
  `uri` varchar(4096) NOT NULL DEFAULT '' COMMENT '访问地址',
  `query_string` varchar(4096) NOT NULL DEFAULT '' COMMENT '参数',
  `method` varchar(50) NOT NULL DEFAULT '' COMMENT 'http 方法',
  `user_agent` varchar(1024) NOT NULL DEFAULT '' COMMENT 'userAgent',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip',
  `start_time` datetime NOT NULL COMMENT '请求时间',
  `response_time` int(11) NOT NULL COMMENT '响应时长 -- 毫秒级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5874 DEFAULT CHARSET=utf8mb4 COMMENT='admin_access_log';

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(11) NOT NULL COMMENT '管理员编号(外键：{@link AdminDO}',
  `role_id` int(11) NOT NULL COMMENT '角色编号(外键：{@link RoleDO}',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='admin_role_d_o';

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `enum_value` varchar(50) NOT NULL DEFAULT '' COMMENT '大类枚举值',
  `display_name` varchar(50) NOT NULL DEFAULT '' COMMENT '展示名',
  `sort` int(11) NOT NULL DEFAULT '-1' COMMENT '排序值',
  `memo` varchar(50) DEFAULT '' COMMENT '备注',
  `value` varchar(50) NOT NULL DEFAULT '' COMMENT '小类数值',
  `deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='data_dict';

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token` (
  `id` varchar(32) NOT NULL,
  `refresh_token` varchar(32) DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `valid` tinyint(4) DEFAULT NULL,
  `expires_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token` (
  `id` varchar(32) NOT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `valid` tinyint(4) DEFAULT NULL,
  `expires_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '资源名字',
  `type` int(11) NOT NULL DEFAULT '-1' COMMENT '资源类型',
  `sort` int(11) NOT NULL DEFAULT '-1' COMMENT '排序',
  `display_name` varchar(50) NOT NULL DEFAULT '' COMMENT '展示名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `pid` int(11) NOT NULL DEFAULT '-1' COMMENT '父级资源编号(外键：{@link ResourceDO#id})',
  `handler` varchar(50) DEFAULT '' COMMENT '操作\n     *\n     * 当资源类型为【菜单】时，handler 配置为界面 URL ，或者前端组件名\n     * 当资源类型为【操作】时，handler 配置为后端 URL 。举个例子，如果有一个「创建管理员」的表单，那么前端界面上的按钮可以根据这个 url 判断是否展示，后端接收到该 url 的请求时会判断是否有权限。',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='resource_d_o';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='role_d_o';

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(11) NOT NULL DEFAULT '-1' COMMENT '角色编号(外键：{@link RoleDO}',
  `resource_id` int(11) NOT NULL DEFAULT '-1' COMMENT '资源编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=287 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='role_resource_d_o';

SET FOREIGN_KEY_CHECKS = 1;
