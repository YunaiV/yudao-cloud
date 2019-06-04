/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_pay

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/06/2019 07:57:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` varchar(50) NOT NULL COMMENT '应用编号',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '应用名',
  `notify_url` varchar(255) NOT NULL DEFAULT '' COMMENT '异步通知地址',
  `refund_notify_url` varchar(255) NOT NULL COMMENT '退款异步通知地址',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='pay_app';

-- ----------------------------
-- Table structure for notify_log
-- ----------------------------
DROP TABLE IF EXISTS `notify_log`;
CREATE TABLE `notify_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志编号，自增',
  `notify_id` int(11) NOT NULL COMMENT '通知编号',
  `request` varchar(5000) NOT NULL COMMENT '请求参数',
  `response` varchar(5000) NOT NULL COMMENT '响应结果',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COMMENT='pay_transaction_notify_log';

-- ----------------------------
-- Table structure for notify_task
-- ----------------------------
DROP TABLE IF EXISTS `notify_task`;
CREATE TABLE `notify_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，自增',
  `app_id` varchar(50) NOT NULL COMMENT '应用编号',
  `status` tinyint(4) NOT NULL COMMENT '通知状态',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `next_notify_time` datetime DEFAULT NULL COMMENT '最后一次通知时间',
  `last_execute_time` datetime DEFAULT NULL COMMENT '最后执行时间',
  `notify_times` tinyint(4) NOT NULL COMMENT '当前通知次数',
  `max_notify_times` tinyint(4) NOT NULL COMMENT '最大可通知次数',
  `transaction` varchar(255) DEFAULT NULL COMMENT '支付数据',
  `refund` varchar(255) DEFAULT NULL COMMENT '退款数据',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='transaction_notify_task';

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，自增',
  `transaction_id` int(11) NOT NULL COMMENT '支付交易编号',
  `refund_code` varbinary(50) NOT NULL COMMENT '生成传输给第三方的退款号',
  `app_id` varchar(50) NOT NULL COMMENT '应用编号\n     *\n     * 不同业务线分配不同的 appId\n     * 举个例子，\n     * 1. 电商系统的订单，appId = 1024\n     * 2. 活动系统的订单，appId = 2048',
  `order_id` varchar(50) NOT NULL COMMENT '业务线的订单编号\n     *\n     * 1. 使用 String 的原因是，业务线可能使用 String 做为编号\n     * 2. 每个 appId 下，orderId 唯一',
  `create_ip` varchar(50) NOT NULL COMMENT '发起交易的 IP',
  `order_description` varchar(50) NOT NULL COMMENT '业务退款描述',
  `price` int(11) NOT NULL COMMENT '退款金额，单位：分。',
  `status` tinyint(4) NOT NULL COMMENT '退款状态\n     *\n     * @see cn.iocoder.mall.pay.api.constant.PayRefundStatus',
  `finish_time` datetime DEFAULT NULL COMMENT '回调业务线完成时间',
  `notify_url` varchar(255) NOT NULL COMMENT '异步通知地址',
  `extension_data` varchar(1024) DEFAULT NULL COMMENT '扩展内容\n     *\n     * 异步通知的时候填充回调的数据',
  `refund_channel` int(11) NOT NULL COMMENT '退款渠道',
  `refund_time` datetime DEFAULT NULL COMMENT '第三方退款成功的时间',
  `notify_time` datetime DEFAULT NULL COMMENT '收到第三方系统通知的时间\n     *\n     * 一般情况下，即第三方系统的异步通知',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '第三方的流水号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='pay_refund';

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，自增',
  `app_id` varchar(50) NOT NULL DEFAULT '' COMMENT '应用编号',
  `create_ip` varchar(50) NOT NULL DEFAULT '' COMMENT '发起交易的 IP',
  `order_id` varchar(50) NOT NULL DEFAULT '' COMMENT '业务线的订单编号',
  `order_subject` varchar(50) NOT NULL COMMENT '订单商品名',
  `order_description` varchar(50) NOT NULL COMMENT '订单商品描述',
  `order_memo` varchar(50) DEFAULT NULL COMMENT '订单备注',
  `price` int(11) NOT NULL COMMENT '支付金额，单位：分。',
  `status` tinyint(4) NOT NULL COMMENT '订单状态',
  `expire_time` datetime DEFAULT NULL COMMENT '交易过期时间',
  `finish_time` datetime DEFAULT NULL COMMENT '回调业务线完成时间',
  `notify_url` varchar(255) NOT NULL DEFAULT '' COMMENT '异步通知地址',
  `extension_id` int(11) DEFAULT NULL COMMENT '成功支付的交易拓展编号',
  `pay_channel` int(11) DEFAULT NULL COMMENT '支付成功的支付渠道',
  `payment_time` datetime DEFAULT NULL COMMENT '第三方支付成功的时间',
  `notify_time` datetime DEFAULT NULL COMMENT '收到第三方系统通知的时间',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '第三方的流水号',
  `refund_total` int(11) DEFAULT '0' COMMENT '退款总金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_orderId_appId` (`app_id`,`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COMMENT='pay_transaction';

-- ----------------------------
-- Table structure for transaction_extension
-- ----------------------------
DROP TABLE IF EXISTS `transaction_extension`;
CREATE TABLE `transaction_extension` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，自增',
  `transaction_id` int(11) NOT NULL COMMENT '交易编号}',
  `pay_channel` int(11) NOT NULL COMMENT '选择的支付渠道',
  `transaction_code` varchar(50) NOT NULL COMMENT '生成传输给第三方的订单号',
  `extension_data` varchar(1024) DEFAULT NULL COMMENT '扩展内容',
  `create_ip` varchar(50) NOT NULL COMMENT '发起交易的 IP',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_transaction_code` (`transaction_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COMMENT='transaction_extension';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
