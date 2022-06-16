SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `mobile` varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '加密后的密码',
  `password_salt` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '密码的盐',
  `create_ip` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '注册 IP',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_mobile` (`mobile`) USING BTREE COMMENT '手机号'
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Table structure for user_sms_code
-- ----------------------------
CREATE TABLE `user_sms_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `create_ip` varchar(15) NOT NULL COMMENT '创建 IP',
  `scene` tinyint(4) NOT NULL COMMENT '发送场景',
  `today_index` tinyint(4) NOT NULL COMMENT '今日发送的第几条',
  `used` tinyint(4) NOT NULL COMMENT '是否使用',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `used_ip` varchar(255) DEFAULT NULL COMMENT '使用 IP',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_mobile` (`mobile`) USING BTREE COMMENT '手机号'
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=utf8mb4 COMMENT='手机验证码';

-- ----------------------------
-- Table structure for user_sms_code
-- ----------------------------
CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收件地址编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '收件人名称',
  `mobile` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `area_code` int(11) NOT NULL COMMENT '地区编码',
  `detail_address` varchar(250) COLLATE utf8mb4_bin NOT NULL COMMENT '收件详细地址',
  `type` tinyint(4) NOT NULL COMMENT '地址类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户收件地址';
