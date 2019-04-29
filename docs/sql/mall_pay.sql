/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_pay

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 28/04/2019 18:05:23
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='pay_app';

-- ----------------------------
-- Records of app
-- ----------------------------
BEGIN;
INSERT INTO `app` VALUES ('POd4RC6a', '商城订单', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', 1, '2019-03-13 11:10:05', '2019-04-26 22:55:57');
COMMIT;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='pay_transaction_notify_log';

-- ----------------------------
-- Records of notify_log
-- ----------------------------
BEGIN;
INSERT INTO `notify_log` VALUES (1, 3, '{\"appId\":\"POd4RC6a\",\"id\":3,\"notifyTimes\":0,\"notifyUrl\":\"cn.iocoder.mall.order.api.OrderService#updatePaySuccess\",\"orderId\":\"135\",\"transactionId\":50}', 'success', 2, '2019-04-27 00:44:12', '2019-04-27 00:44:12');
INSERT INTO `notify_log` VALUES (20, 10, '{\"appId\":\"POd4RC6a\",\"id\":10,\"notifyTimes\":0,\"notifyUrl\":\"cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess\",\"orderId\":\"135\",\"refundId\":10,\"transactionId\":50}', 'success', 2, '2019-04-27 01:38:08', '2019-04-27 01:38:08');
COMMIT;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='transaction_notify_task';

-- ----------------------------
-- Records of notify_task
-- ----------------------------
BEGIN;
INSERT INTO `notify_task` VALUES (1, 'POd4RC6a', 1, 1, '2019-04-27 00:39:44', NULL, 0, 9, '{\"orderId\":\"133\",\"transactionExtensionId\":77,\"transactionId\":48}', NULL, '2019-04-27 00:39:31', '2019-04-27 00:39:31');
INSERT INTO `notify_task` VALUES (2, 'POd4RC6a', 1, 1, '2019-04-27 00:43:28', NULL, 0, 9, '{\"orderId\":\"134\",\"transactionExtensionId\":78,\"transactionId\":49}', NULL, '2019-04-27 00:43:13', '2019-04-27 00:43:13');
INSERT INTO `notify_task` VALUES (3, 'POd4RC6a', 2, 1, '2019-04-27 00:44:27', '2019-04-27 00:44:12', 1, 9, '{\"orderId\":\"135\",\"transactionExtensionId\":79,\"transactionId\":50}', NULL, '2019-04-27 00:44:12', '2019-04-27 00:44:12');
INSERT INTO `notify_task` VALUES (4, 'POd4RC6a', 1, 2, '2019-04-27 01:23:21', NULL, 0, 9, NULL, '{\"orderId\":\"135\",\"refundId\":6,\"transactionId\":50}', '2019-04-27 01:23:05', '2019-04-27 01:23:05');
INSERT INTO `notify_task` VALUES (5, 'POd4RC6a', 1, 2, '2019-04-27 01:24:54', NULL, 0, 9, NULL, '{\"orderId\":\"135\",\"refundId\":3,\"transactionId\":50}', '2019-04-27 01:24:38', '2019-04-27 01:24:38');
INSERT INTO `notify_task` VALUES (6, 'POd4RC6a', 1, 2, '2019-04-27 01:28:18', NULL, 0, 9, NULL, '{\"orderId\":\"135\",\"refundId\":7,\"transactionId\":50}', '2019-04-27 01:28:03', '2019-04-27 01:28:03');
INSERT INTO `notify_task` VALUES (10, 'POd4RC6a', 2, 2, '2019-04-27 01:38:14', '2019-04-27 01:37:59', 1, 9, NULL, '{\"orderId\":\"135\",\"refundId\":10,\"transactionId\":50}', '2019-04-27 01:37:59', '2019-04-27 01:38:08');
COMMIT;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='pay_refund';

-- ----------------------------
-- Records of refund
-- ----------------------------
BEGIN;
INSERT INTO `refund` VALUES (1, 47, '', 'POd4RC6a', '132', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundFinish', NULL, 9999, NULL, NULL, NULL, '2019-04-26 16:16:21', '2019-04-26 16:16:20');
INSERT INTO `refund` VALUES (2, 50, 0x3230313930343237303035333035313832383232, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', NULL, 9999, NULL, NULL, NULL, '2019-04-27 00:53:05', '2019-04-27 00:53:05');
INSERT INTO `refund` VALUES (3, 50, 0x3230313930343237303131363537383432363937, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 3, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', '{\"id\":\"evt_400190427011658181610003\",\"created\":1556299018,\"livemode\":false,\"type\":\"refund.succeeded\",\"data\":{\"object\":{\"id\":\"re_9Oebj5LKmTmTmT0az9fTqPyP\",\"object\":\"refund\",\"order_no\":\"9Oebj5LKmTmTmT0az9fTqPyP\",\"amount\":1,\"created\":1556299018,\"succeed\":true,\"status\":\"succeeded\",\"time_succeed\":1556299018,\"description\":\"测试下退款\",\"failure_code\":null,\"failure_msg\":null,\"metadata\":{\"refundCode\":\"20190427011657842697\"},\"charge\":\"ch_y1iXjLnDS4G4OO4uT4a5C4W1\",\"charge_order_no\":\"20190427004410165545\",\"transaction_no\":\"201904270116584452782\",\"extra\":{}}},\"object\":\"event\",\"request\":\"iar_v1aTW19O0OO0f1ev5SGqLSK4\",\"pending_webhooks\":0}', 9999, NULL, NULL, NULL, '2019-04-27 01:16:58', '2019-04-27 01:24:38');
INSERT INTO `refund` VALUES (4, 50, 0x3230313930343237303131393137333731303832, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', NULL, 9999, NULL, NULL, NULL, '2019-04-27 01:19:17', '2019-04-27 01:19:17');
INSERT INTO `refund` VALUES (5, 50, 0x3230313930343237303132313030383239363939, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', NULL, 9999, NULL, NULL, NULL, '2019-04-27 01:21:01', '2019-04-27 01:21:00');
INSERT INTO `refund` VALUES (6, 50, 0x3230313930343237303132333034363332363038, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 3, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', '{\"id\":\"evt_400190427012305181712903\",\"created\":1556299385,\"livemode\":false,\"type\":\"refund.succeeded\",\"data\":{\"object\":{\"id\":\"re_mTGGWLzD8SePCGWbX19KSSaP\",\"object\":\"refund\",\"order_no\":\"mTGGWLzD8SePCGWbX19KSSaP\",\"amount\":1,\"created\":1556299385,\"succeed\":true,\"status\":\"succeeded\",\"time_succeed\":1556299385,\"description\":\"测试下退款\",\"failure_code\":null,\"failure_msg\":null,\"metadata\":{\"refundCode\":\"20190427012304632608\"},\"charge\":\"ch_y1iXjLnDS4G4OO4uT4a5C4W1\",\"charge_order_no\":\"20190427004410165545\",\"transaction_no\":\"201904270123051790753\",\"extra\":{}}},\"object\":\"event\",\"request\":\"iar_rP80WDL4Ku9KOaXLCOmPWTKC\",\"pending_webhooks\":0}', 9999, NULL, NULL, NULL, '2019-04-27 01:23:05', '2019-04-27 01:23:05');
INSERT INTO `refund` VALUES (7, 50, 0x3230313930343237303132383032383531333238, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 3, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', '{\"id\":\"evt_400190427012802341778702\",\"created\":1556299682,\"livemode\":false,\"type\":\"refund.succeeded\",\"data\":{\"object\":{\"id\":\"re_nrLmT85aTCyLeDyjv1LmH0e5\",\"object\":\"refund\",\"order_no\":\"nrLmT85aTCyLeDyjv1LmH0e5\",\"amount\":1,\"created\":1556299682,\"succeed\":true,\"status\":\"succeeded\",\"time_succeed\":1556299682,\"description\":\"测试下退款\",\"failure_code\":null,\"failure_msg\":null,\"metadata\":{\"refundCode\":\"20190427012802851328\"},\"charge\":\"ch_y1iXjLnDS4G4OO4uT4a5C4W1\",\"charge_order_no\":\"20190427004410165545\",\"transaction_no\":\"201904270128023511588\",\"extra\":{}}},\"object\":\"event\",\"request\":\"iar_1aXPePubTCm5GW1Ky5DajTyL\",\"pending_webhooks\":0}', 9999, NULL, NULL, NULL, '2019-04-27 01:28:02', '2019-04-27 01:28:03');
INSERT INTO `refund` VALUES (8, 50, 0x3230313930343237303133343338323832343330, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', NULL, 9999, NULL, NULL, NULL, '2019-04-27 01:34:39', '2019-04-27 01:34:38');
INSERT INTO `refund` VALUES (9, 50, 0x3230313930343237303133363031363230353231, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 1, NULL, 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', NULL, 9999, NULL, NULL, NULL, '2019-04-27 01:36:01', '2019-04-27 01:36:01');
INSERT INTO `refund` VALUES (10, 50, 0x3230313930343237303133373538363732313237, 'POd4RC6a', '135', '127.0.0.1', '测试下退款', 1, 3, '2019-04-27 01:38:09', 'cn.iocoder.mall.order.api.OrderReturnService#updateRefundSuccess', '{\"id\":\"evt_400190427013758341915902\",\"created\":1556300278,\"livemode\":false,\"type\":\"refund.succeeded\",\"data\":{\"object\":{\"id\":\"re_qLWXT4bnbXTG5u90i14Cmb1G\",\"object\":\"refund\",\"order_no\":\"qLWXT4bnbXTG5u90i14Cmb1G\",\"amount\":1,\"created\":1556300278,\"succeed\":true,\"status\":\"succeeded\",\"time_succeed\":1556300278,\"description\":\"测试下退款\",\"failure_code\":null,\"failure_msg\":null,\"metadata\":{\"refundCode\":\"20190427013758672127\"},\"charge\":\"ch_y1iXjLnDS4G4OO4uT4a5C4W1\",\"charge_order_no\":\"20190427004410165545\",\"transaction_no\":\"201904270137588767780\",\"extra\":{}}},\"object\":\"event\",\"request\":\"iar_fTKCeHLajbjPnb1inDj5mbf5\",\"pending_webhooks\":0}', 9999, NULL, NULL, NULL, '2019-04-27 01:37:58', '2019-04-27 01:38:08');
COMMIT;

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_orderId_appId` (`app_id`,`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COMMENT='pay_transaction';

-- ----------------------------
-- Records of transaction
-- ----------------------------
BEGIN;
INSERT INTO `transaction` VALUES (1, 'POd4RC6a', '127.0.0.1', '1', '商品名', '商品描述', '商品备注', 10, 2, '2019-03-13 13:36:32', NULL, 'TODO', 15, 9999, '2019-03-13 17:30:21', '2019-03-13 17:30:22', '1285076745201903130468978518', 4, '2019-03-13 13:36:32', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (2, 'POd4RC6a', '127.0.0.1', '2', '商品名', '商品描述', '商品备注', 10, 2, '2019-03-14 16:08:14', '2019-03-14 22:09:20', 'TODO', 22, 9999, '2019-03-14 16:13:58', '2019-03-14 16:13:58', '1282396661201903145620538794', 4, '2019-03-14 16:08:15', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (3, 'POd4RC6a', '127.0.0.1', '91', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 01:46:59', NULL, 'TODO', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-20 23:47:01', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (5, 'POd4RC6a', '127.0.0.1', '92', '农夫山泉', '测试描述', '测试备注', 1000, 1, '2019-04-21 04:13:47', NULL, 'TODO', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 02:13:52', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (8, 'POd4RC6a', '127.0.0.1', '93', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 04:15:14', NULL, 'TODO', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 02:15:14', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (9, 'POd4RC6a', '127.0.0.1', '94', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 04:15:17', NULL, 'TODO', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 02:15:17', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (10, 'POd4RC6a', '127.0.0.1', '95', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 04:17:24', NULL, 'TODO', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 02:17:24', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (11, 'POd4RC6a', '127.0.0.1', '97', 'kafka 实战', '测试描述', '测试备注', 160, 2, '2019-04-21 16:22:49', '2019-04-21 14:25:40', 'cn.iocoder.mall.pay.api.PayDemoService#updatePaySuccess', 37, 9999, '2019-04-21 14:23:04', '2019-04-21 14:23:15', '1214878818201904214744765608', 4, '2019-04-21 14:22:49', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (12, 'POd4RC6a', '127.0.0.1', '98', 'kafka 实战', '测试描述', '测试备注', 160, 2, '2019-04-21 17:01:50', NULL, 'cn.iocoder.mall.pay.api.PayDemoService#updatePaySuccess', 38, 9999, '2019-04-21 15:01:56', '2019-04-21 15:01:57', '1242451315201904216613366351', 4, '2019-04-21 15:01:50', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (13, 'POd4RC6a', '127.0.0.1', '99', 'kafka 实战', '测试描述', '测试备注', 160, 2, '2019-04-21 17:05:21', NULL, 'cn.iocoder.mall.order.api.OrderServicee#updatePaySuccess', 39, 9999, '2019-04-21 15:05:27', '2019-04-21 15:05:28', '1232163188201904219935216641', 4, '2019-04-21 15:05:22', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (14, 'POd4RC6a', '127.0.0.1', '100', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 17:06:08', NULL, 'cn.iocoder.mall.order.api.OrderServicee#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 15:06:12', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (17, 'POd4RC6a', '127.0.0.1', '101', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 17:06:30', NULL, 'cn.iocoder.mall.order.api.OrderServicee#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 15:06:30', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (18, 'POd4RC6a', '127.0.0.1', '102', '测试商品', '测试描述', '测试备注', 100, 2, '2019-04-21 17:10:39', NULL, 'cn.iocoder.mall.order.api.OrderServicee#updatePaySuccess', 40, 9999, '2019-04-21 15:11:13', '2019-04-21 15:11:14', '1262352890201904219469841110', 4, '2019-04-21 15:10:39', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (19, 'POd4RC6a', '127.0.0.1', '103', '测试商品', '测试描述', '测试备注', 100, 2, '2019-04-21 17:14:17', '2019-04-21 15:21:26', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 41, 9999, '2019-04-21 15:14:22', '2019-04-21 15:14:23', '1248585474201904212137778537', 4, '2019-04-21 15:14:17', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (20, 'POd4RC6a', '127.0.0.1', '104', '测试商品', '测试描述', '测试备注', 100, 2, '2019-04-21 17:19:19', '2019-04-21 15:20:16', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 42, 9999, '2019-04-21 15:19:24', '2019-04-21 15:19:25', '1214125122201904212507946245', 4, '2019-04-21 15:19:19', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (21, 'POd4RC6a', '124.77.208.137', '105', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 20:59:24', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 18:59:24', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (22, 'POd4RC6a', '124.77.208.137', '106', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 21:08:18', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 19:08:18', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (23, 'POd4RC6a', '124.77.208.137', '107', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-21 21:22:19', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 19:22:19', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (24, 'POd4RC6a', '124.77.208.137', '109', '测试商品', '测试描述', '测试备注', 100, 1, '2019-04-21 21:57:14', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 19:57:14', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (25, 'POd4RC6a', '127.0.0.1', '110', '测试商品', '测试描述', '测试备注', 100, 2, '2019-04-21 22:17:04', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 54, 9999, '2019-04-21 20:17:15', '2019-04-21 20:17:19', '1263683564201904219993710538', 4, '2019-04-21 20:17:04', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (26, 'POd4RC6a', '124.77.208.137', '111', '农夫山泉', '测试描述', '测试备注', 1000, 1, '2019-04-21 22:24:05', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-21 20:24:05', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (27, 'POd4RC6a', '124.77.208.137', '112', '农夫山泉', '测试描述', '测试备注', 1000, 2, '2019-04-21 22:37:45', '2019-04-22 19:32:35', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 63, 9999, '2019-04-22 19:32:30', '2019-04-22 19:32:31', '1244224811201904228521103170', 4, '2019-04-21 20:37:45', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (28, 'POd4RC6a', '124.77.208.137', '113', '农夫山泉', '测试描述', '测试备注', 1000, 2, '2019-04-21 22:51:38', '2019-04-22 19:35:58', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 64, 9999, '2019-04-22 19:35:57', '2019-04-22 19:35:58', '1213285149201904222299145413', 4, '2019-04-21 20:51:38', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (29, 'POd4RC6a', '124.77.208.137', '114', '农夫山泉', '测试描述', '测试备注', 1000, 2, '2019-04-22 00:38:24', '2019-04-21 22:38:34', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 60, 9999, '2019-04-21 22:38:32', '2019-04-21 22:38:33', '1290815313201904211598433725', 4, '2019-04-21 22:38:24', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (30, 'POd4RC6a', '124.77.208.137', '115', 'kafka 实战', '测试描述', '测试备注', 160, 2, '2019-04-22 01:29:21', '2019-04-22 19:36:16', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 65, 9999, '2019-04-22 19:36:15', '2019-04-22 19:36:16', '1245834775201904220995332009', 4, '2019-04-21 23:29:21', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (31, 'POd4RC6a', '127.0.0.1', '118', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-22 19:06:00', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 17:06:00', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (32, 'POd4RC6a', '180.167.213.26', '119', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-22 21:31:53', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 19:31:57', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (33, 'POd4RC6a', '180.167.213.26', '120', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-22 21:31:55', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 19:31:57', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (37, 'POd4RC6a', '180.167.213.26', '121', 'Oracle', '测试描述', '测试备注', 44000, 1, '2019-04-22 21:35:45', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 19:35:45', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (38, 'POd4RC6a', '114.87.158.59', '122', 'kafka 实战', '测试描述', '测试备注', 160, 2, '2019-04-22 22:51:39', '2019-04-22 20:51:47', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 66, 9999, '2019-04-22 20:51:45', '2019-04-22 20:51:46', '1209794355201904229853980782', 4, '2019-04-22 20:51:39', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (39, 'POd4RC6a', '127.0.0.1', '124', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-23 00:19:58', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:19:58', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (40, 'POd4RC6a', '127.0.0.1', '125', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-23 00:21:26', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:21:26', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (41, 'POd4RC6a', '127.0.0.1', '126', 'kafka 实战', '测试描述', '测试备注', 160, 1, '2019-04-23 00:27:03', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:27:03', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (42, 'POd4RC6a', '127.0.0.1', '127', 'kafka 实战', '测试描述', '测试备注', 10, 1, '2019-04-23 00:28:21', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:28:21', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (43, 'POd4RC6a', '127.0.0.1', '128', 'kafka 实战', '测试描述', '测试备注', 10, 1, '2019-04-23 00:29:16', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:29:16', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (44, 'POd4RC6a', '127.0.0.1', '129', 'kafka 实战', '测试描述', '测试备注', 10, 1, '2019-04-23 00:37:18', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', NULL, NULL, NULL, NULL, NULL, 4, '2019-04-22 22:37:19', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (45, 'POd4RC6a', '127.0.0.1', '130', 'kafka 实战', '测试描述', '测试备注', 10, 2, '2019-04-23 00:37:23', '2019-04-22 22:37:27', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 68, 9999, '2019-04-22 22:37:25', '2019-04-22 22:37:26', '1257829370201904225516383887', 4, '2019-04-22 22:37:23', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (46, 'POd4RC6a', '124.77.208.137', '131', 'kafka 实战', '测试描述', '测试备注', 10, 2, '2019-04-23 02:22:22', '2019-04-23 00:22:28', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 69, 9999, '2019-04-23 00:22:27', '2019-04-23 00:22:27', '1295079419201904238932409449', 4, '2019-04-23 00:22:22', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (47, 'POd4RC6a', '114.87.158.59', '132', 'kafka 实战', '测试描述', '测试备注', 10, 2, '2019-04-23 12:03:50', '2019-04-23 10:03:55', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 70, 9999, '2019-04-23 10:03:53', '2019-04-23 10:03:54', '1244341374201904238178164740', 4, '2019-04-23 10:03:50', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (48, 'POd4RC6a', '127.0.0.1', '133', 'Kafka 书籍汇总', '测试描述', '测试备注', 10000, 2, '2019-04-27 02:02:20', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 77, 9999, '2019-04-27 00:39:25', '2019-04-27 00:39:26', '1298384458201904270239520896', 4, '2019-04-27 00:02:20', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (49, 'POd4RC6a', '127.0.0.1', '134', 'Kafka 书籍汇总', '测试描述', '测试备注', 10000, 2, '2019-04-27 02:43:06', NULL, 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 78, 9999, '2019-04-27 00:43:09', '2019-04-27 00:43:10', '1230418317201904273841336004', 4, '2019-04-27 00:43:06', '2019-04-27 01:37:59');
INSERT INTO `transaction` VALUES (50, 'POd4RC6a', '127.0.0.1', '135', 'Kafka 书籍汇总', '测试描述', '测试备注', 10000, 2, '2019-04-27 02:44:09', '2019-04-27 00:44:12', 'cn.iocoder.mall.order.api.OrderService#updatePaySuccess', 79, 9999, '2019-04-27 00:44:11', '2019-04-27 00:44:12', '1267784917201904270679599868', 4, '2019-04-27 00:44:09', '2019-04-27 01:37:59');
COMMIT;

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
  PRIMARY KEY (`id`),
  KEY `idx_transaction_code` (`transaction_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='transaction_extension';

-- ----------------------------
-- Records of transaction_extension
-- ----------------------------
BEGIN;
INSERT INTO `transaction_extension` VALUES (1, 1, 9999, '20190313143449394595', NULL, '127.0.0.1', 1, '2019-03-13 14:34:49', '2019-03-13 14:34:49');
INSERT INTO `transaction_extension` VALUES (2, 1, 9999, '20190313143626850384', NULL, '127.0.0.1', 1, '2019-03-13 14:36:26', '2019-03-13 14:36:26');
INSERT INTO `transaction_extension` VALUES (3, 1, 9999, '20190313143709988585', NULL, '127.0.0.1', 1, '2019-03-13 14:37:09', '2019-03-13 14:37:09');
INSERT INTO `transaction_extension` VALUES (4, 1, 9999, '20190313170637270597', NULL, '127.0.0.1', 1, '2019-03-13 17:06:37', '2019-03-13 17:06:37');
INSERT INTO `transaction_extension` VALUES (5, 1, 9999, '20190313171109616337', NULL, '127.0.0.1', 1, '2019-03-13 17:11:09', '2019-03-13 17:11:09');
INSERT INTO `transaction_extension` VALUES (6, 1, 9999, '20190313171241800588', NULL, '127.0.0.1', 1, '2019-03-13 17:12:41', '2019-03-13 17:12:41');
INSERT INTO `transaction_extension` VALUES (7, 1, 9999, '20190313171503236852', NULL, '127.0.0.1', 1, '2019-03-13 17:15:03', '2019-03-13 17:15:03');
INSERT INTO `transaction_extension` VALUES (8, 1, 9999, '20190313171700279790', NULL, '127.0.0.1', 1, '2019-03-13 17:17:00', '2019-03-13 17:17:00');
INSERT INTO `transaction_extension` VALUES (9, 1, 9999, '20190313171824113222', NULL, '127.0.0.1', 1, '2019-03-13 17:18:24', '2019-03-13 17:18:24');
INSERT INTO `transaction_extension` VALUES (10, 1, 9999, '20190313171953205276', NULL, '127.0.0.1', 1, '2019-03-13 17:19:53', '2019-03-13 17:19:53');
INSERT INTO `transaction_extension` VALUES (11, 1, 9999, '20190313172111877048', NULL, '127.0.0.1', 1, '2019-03-13 17:21:11', '2019-03-13 17:21:11');
INSERT INTO `transaction_extension` VALUES (12, 1, 9999, '20190313172339490890', '{\"id\":\"evt_400190313172341499184603\",\"created\":1552469021,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_OunLWDSKS8KO5ezfb1i9uLSC\",\"object\":\"charge\",\"created\":1552469020,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190313172339490890\",\"client_ip\":\"127.0.0.1\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"商品名\",\"body\":\"商品描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1552469021,\"time_expire\":1552476220,\"time_settle\":null,\"transaction_no\":\"1259319182201903139181631168\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_OunLWDSKS8KO5ezfb1i9uLSC/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"商品备注\"}},\"object\":\"event\",\"request\":\"iar_4WLiHCajnjbTrXbb1OXnjnPG\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-03-13 17:23:39', '2019-03-13 17:23:41');
INSERT INTO `transaction_extension` VALUES (13, 1, 9999, '20190313172431242571', NULL, '127.0.0.1', 1, '2019-03-13 17:24:31', '2019-03-13 17:24:31');
INSERT INTO `transaction_extension` VALUES (14, 1, 9999, '20190313172707704111', NULL, '127.0.0.1', 1, '2019-03-13 17:27:07', '2019-03-13 17:27:07');
INSERT INTO `transaction_extension` VALUES (15, 1, 9999, '20190313173019968025', '{\"id\":\"evt_400190313173021499408903\",\"created\":1552469421,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_GmT4W94uvPOO4q9ibDa1Sm5O\",\"object\":\"charge\",\"created\":1552469419,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190313173019968025\",\"client_ip\":\"127.0.0.1\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"商品名\",\"body\":\"商品描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1552469421,\"time_expire\":1552476619,\"time_settle\":null,\"transaction_no\":\"1285076745201903130468978518\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_GmT4W94uvPOO4q9ibDa1Sm5O/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"商品备注\"}},\"object\":\"event\",\"request\":\"iar_vfHyL0r58yvPrH0e9C084GGO\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-03-13 17:30:19', '2019-03-13 17:30:21');
INSERT INTO `transaction_extension` VALUES (16, 2, 9999, '20190314160852733791', NULL, '127.0.0.1', 1, '2019-03-14 16:08:52', '2019-03-14 16:08:52');
INSERT INTO `transaction_extension` VALUES (17, 2, 9999, '20190314161024311302', NULL, '127.0.0.1', 1, '2019-03-14 16:10:24', '2019-03-14 16:10:24');
INSERT INTO `transaction_extension` VALUES (18, 2, 9999, '20190314161232723387', NULL, '127.0.0.1', 1, '2019-03-14 16:12:32', '2019-03-14 16:12:32');
INSERT INTO `transaction_extension` VALUES (19, 2, 9999, '20190314161232883479', NULL, '127.0.0.1', 1, '2019-03-14 16:12:32', '2019-03-14 16:12:32');
INSERT INTO `transaction_extension` VALUES (20, 2, 9999, '20190314161232705499', NULL, '127.0.0.1', 1, '2019-03-14 16:12:32', '2019-03-14 16:12:32');
INSERT INTO `transaction_extension` VALUES (21, 2, 9999, '20190314161232832483', NULL, '127.0.0.1', 1, '2019-03-14 16:12:32', '2019-03-14 16:12:32');
INSERT INTO `transaction_extension` VALUES (22, 2, 9999, '20190314161353474808', '{\"id\":\"evt_400190314161358532476503\",\"created\":1552551238,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_GK8Cm5vj1m1OK0evD4DKWbzL\",\"object\":\"charge\",\"created\":1552551233,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190314161353474808\",\"client_ip\":\"127.0.0.1\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"商品名\",\"body\":\"商品描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1552551238,\"time_expire\":1552558433,\"time_settle\":null,\"transaction_no\":\"1282396661201903145620538794\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_GK8Cm5vj1m1OK0evD4DKWbzL/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"商品备注\"}},\"object\":\"event\",\"request\":\"iar_jvbj5KSS4Sy1KmzHGGOaPe5S\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-03-14 16:13:53', '2019-03-14 16:13:58');
INSERT INTO `transaction_extension` VALUES (23, 3, 9999, '20190421014711249244', NULL, '127.0.0.1', 1, '2019-04-21 01:47:11', '2019-04-21 01:47:11');
INSERT INTO `transaction_extension` VALUES (24, 3, 9999, '20190421014735167627', NULL, '127.0.0.1', 1, '2019-04-21 01:47:35', '2019-04-21 01:47:35');
INSERT INTO `transaction_extension` VALUES (25, 3, 9999, '20190421014758838219', NULL, '127.0.0.1', 1, '2019-04-21 01:47:58', '2019-04-21 01:47:58');
INSERT INTO `transaction_extension` VALUES (26, 3, 9999, '20190421014819399499', NULL, '127.0.0.1', 1, '2019-04-21 01:48:19', '2019-04-21 01:48:19');
INSERT INTO `transaction_extension` VALUES (27, 3, 9999, '20190421015427658470', NULL, '127.0.0.1', 1, '2019-04-21 01:54:27', '2019-04-21 01:54:27');
INSERT INTO `transaction_extension` VALUES (28, 3, 9999, '20190421015436543822', NULL, '127.0.0.1', 1, '2019-04-21 01:54:37', '2019-04-21 01:54:37');
INSERT INTO `transaction_extension` VALUES (29, 3, 9999, '20190421015615102559', NULL, '127.0.0.1', 1, '2019-04-21 01:56:15', '2019-04-21 01:56:15');
INSERT INTO `transaction_extension` VALUES (30, 3, 9999, '20190421015623565992', NULL, '127.0.0.1', 1, '2019-04-21 01:56:23', '2019-04-21 01:56:23');
INSERT INTO `transaction_extension` VALUES (31, 3, 9999, '20190421015811819687', NULL, '127.0.0.1', 1, '2019-04-21 01:58:11', '2019-04-21 01:58:11');
INSERT INTO `transaction_extension` VALUES (32, 3, 9999, '20190421015910105184', NULL, '127.0.0.1', 1, '2019-04-21 01:59:10', '2019-04-21 01:59:10');
INSERT INTO `transaction_extension` VALUES (33, 3, 9999, '20190421015930928739', NULL, '127.0.0.1', 1, '2019-04-21 01:59:30', '2019-04-21 01:59:30');
INSERT INTO `transaction_extension` VALUES (34, 3, 9999, '20190421020334246354', NULL, '127.0.0.1', 1, '2019-04-21 02:03:34', '2019-04-21 02:03:34');
INSERT INTO `transaction_extension` VALUES (35, 3, 9999, '20190421021251245269', NULL, '127.0.0.1', 1, '2019-04-21 02:12:51', '2019-04-21 02:12:51');
INSERT INTO `transaction_extension` VALUES (36, 10, 9999, '20190421021728641632', NULL, '127.0.0.1', 1, '2019-04-21 02:17:28', '2019-04-21 02:17:28');
INSERT INTO `transaction_extension` VALUES (37, 11, 9999, '20190421142300962227', '{\"id\":\"evt_400190421142304143197602\",\"created\":1555827784,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_4qTib5zrHePSWzPyv5mjTunT\",\"object\":\"charge\",\"created\":1555827780,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421142300962227\",\"client_ip\":\"127.0.0.1\",\"amount\":160,\"amount_settle\":160,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555827784,\"time_expire\":1555834980,\"time_settle\":null,\"transaction_no\":\"1214878818201904214744765608\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_4qTib5zrHePSWzPyv5mjTunT/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_XX184OWrHuvLm1uT84KSm1a5\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 14:23:00', '2019-04-21 14:23:11');
INSERT INTO `transaction_extension` VALUES (38, 12, 9999, '20190421150154263163', '{\"id\":\"evt_400190421150156144555702\",\"created\":1555830116,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_0y9i9SGaHyX9u14mT0m9qb58\",\"object\":\"charge\",\"created\":1555830114,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421150154263163\",\"client_ip\":\"127.0.0.1\",\"amount\":160,\"amount_settle\":160,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555830116,\"time_expire\":1555837314,\"time_settle\":null,\"transaction_no\":\"1242451315201904216613366351\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_0y9i9SGaHyX9u14mT0m9qb58/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_m5ifTOGCWjzTP8mbn5WvfzfD\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 15:01:54', '2019-04-21 15:01:57');
INSERT INTO `transaction_extension` VALUES (39, 13, 9999, '20190421150525625103', '{\"id\":\"evt_400190421150527957166903\",\"created\":1555830327,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_ePGqLSWLCqjHS8GKuPan1Gi9\",\"object\":\"charge\",\"created\":1555830326,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421150525625103\",\"client_ip\":\"127.0.0.1\",\"amount\":160,\"amount_settle\":160,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555830327,\"time_expire\":1555837526,\"time_settle\":null,\"transaction_no\":\"1232163188201904219935216641\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_ePGqLSWLCqjHS8GKuPan1Gi9/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_TOmXv11yznr9X1q9SSvbvrH4\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 15:05:25', '2019-04-21 15:05:28');
INSERT INTO `transaction_extension` VALUES (40, 18, 9999, '20190421151112297666', '{\"id\":\"evt_400190421151113957401603\",\"created\":1555830673,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_KCWL84nffXX5yjTK48eXD8a1\",\"object\":\"charge\",\"created\":1555830672,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421151112297666\",\"client_ip\":\"127.0.0.1\",\"amount\":100,\"amount_settle\":100,\"currency\":\"cny\",\"subject\":\"测试商品\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555830673,\"time_expire\":1555837872,\"time_settle\":null,\"transaction_no\":\"1262352890201904219469841110\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_KCWL84nffXX5yjTK48eXD8a1/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_COirb1GefXvTD40aH0ufDGi9\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 15:11:12', '2019-04-21 15:11:14');
INSERT INTO `transaction_extension` VALUES (41, 19, 9999, '20190421151421784758', '{\"id\":\"evt_400190421151423144979702\",\"created\":1555830862,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_Tqj9uT1mHm144CyvH8rP4y90\",\"object\":\"charge\",\"created\":1555830861,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421151421784758\",\"client_ip\":\"127.0.0.1\",\"amount\":100,\"amount_settle\":100,\"currency\":\"cny\",\"subject\":\"测试商品\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555830862,\"time_expire\":1555838061,\"time_settle\":null,\"transaction_no\":\"1248585474201904212137778537\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_Tqj9uT1mHm144CyvH8rP4y90/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_DOefD0inTiz5bbnvv11i1O04\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 15:14:21', '2019-04-21 15:14:23');
INSERT INTO `transaction_extension` VALUES (42, 20, 9999, '20190421151923205372', '{\"id\":\"evt_400190421151924957716203\",\"created\":1555831164,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_HOuvfD4SCCCCzfHOuHzPqbrP\",\"object\":\"charge\",\"created\":1555831163,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421151923205372\",\"client_ip\":\"127.0.0.1\",\"amount\":100,\"amount_settle\":100,\"currency\":\"cny\",\"subject\":\"测试商品\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555831164,\"time_expire\":1555838363,\"time_settle\":null,\"transaction_no\":\"1214125122201904212507946245\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_HOuvfD4SCCCCzfHOuHzPqbrP/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_mr1G4Gy1ubbDDGWPGOibXTO8\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 15:19:23', '2019-04-21 15:19:25');
INSERT INTO `transaction_extension` VALUES (43, 21, 9999, '20190421185933879667', NULL, '124.77.208.137', 1, '2019-04-21 18:59:33', '2019-04-21 18:59:33');
INSERT INTO `transaction_extension` VALUES (44, 21, 9999, '20190421190040275638', NULL, '124.77.208.137', 1, '2019-04-21 19:00:40', '2019-04-21 19:00:40');
INSERT INTO `transaction_extension` VALUES (45, 21, 9999, '20190421190049129322', NULL, '124.77.208.137', 1, '2019-04-21 19:00:49', '2019-04-21 19:00:49');
INSERT INTO `transaction_extension` VALUES (46, 21, 9999, '20190421190148386359', NULL, '124.77.208.137', 1, '2019-04-21 19:01:48', '2019-04-21 19:01:48');
INSERT INTO `transaction_extension` VALUES (47, 21, 9999, '20190421190156394078', NULL, '124.77.208.137', 1, '2019-04-21 19:01:56', '2019-04-21 19:01:56');
INSERT INTO `transaction_extension` VALUES (48, 21, 9999, '20190421190202674025', NULL, '124.77.208.137', 1, '2019-04-21 19:02:02', '2019-04-21 19:02:02');
INSERT INTO `transaction_extension` VALUES (49, 21, 9999, '20190421190325916992', NULL, '124.77.208.137', 1, '2019-04-21 19:03:25', '2019-04-21 19:03:25');
INSERT INTO `transaction_extension` VALUES (50, 22, 9999, '20190421190820811877', NULL, '124.77.208.137', 1, '2019-04-21 19:08:20', '2019-04-21 19:08:20');
INSERT INTO `transaction_extension` VALUES (51, 23, 9999, '20190421192223484940', NULL, '124.77.208.137', 1, '2019-04-21 19:22:23', '2019-04-21 19:22:23');
INSERT INTO `transaction_extension` VALUES (52, 24, 9999, '20190421195724705545', NULL, '124.77.208.137', 1, '2019-04-21 19:57:24', '2019-04-21 19:57:24');
INSERT INTO `transaction_extension` VALUES (53, 24, 9999, '20190421200329944775', NULL, '124.77.208.137', 1, '2019-04-21 20:03:29', '2019-04-21 20:03:29');
INSERT INTO `transaction_extension` VALUES (54, 25, 9999, '20190421201713787629', '{\"id\":\"evt_400190421201715971291603\",\"created\":1555849035,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_90Smv5KaXXHGyDynDOzDe9eD\",\"object\":\"charge\",\"created\":1555849034,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421201713787629\",\"client_ip\":\"127.0.0.1\",\"amount\":100,\"amount_settle\":100,\"currency\":\"cny\",\"subject\":\"测试商品\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555849035,\"time_expire\":1555856234,\"time_settle\":null,\"transaction_no\":\"1263683564201904219993710538\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_90Smv5KaXXHGyDynDOzDe9eD/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_LCS8uDPajzP8v94yfDKurfvP\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-21 20:17:13', '2019-04-21 20:17:19');
INSERT INTO `transaction_extension` VALUES (55, 24, 9999, '20190421201835497879', NULL, '124.77.208.137', 1, '2019-04-21 20:18:35', '2019-04-21 20:18:35');
INSERT INTO `transaction_extension` VALUES (56, 24, 9999, '20190421202220830224', NULL, '124.77.208.137', 1, '2019-04-21 20:22:20', '2019-04-21 20:22:20');
INSERT INTO `transaction_extension` VALUES (57, 26, 9999, '20190421202410194081', NULL, '124.77.208.137', 1, '2019-04-21 20:24:10', '2019-04-21 20:24:10');
INSERT INTO `transaction_extension` VALUES (58, 27, 9999, '20190421203748920380', NULL, '124.77.208.137', 1, '2019-04-21 20:37:48', '2019-04-21 20:37:48');
INSERT INTO `transaction_extension` VALUES (59, 28, 9999, '20190421205144641928', NULL, '124.77.208.137', 1, '2019-04-21 20:51:44', '2019-04-21 20:51:44');
INSERT INTO `transaction_extension` VALUES (60, 29, 9999, '20190421223828915607', '{\"id\":\"evt_400190421223832162174102\",\"created\":1555857512,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_1innTSWL88C4Lybvj5qDWn5G\",\"object\":\"charge\",\"created\":1555857510,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190421223828915607\",\"client_ip\":\"124.77.208.137\",\"amount\":1000,\"amount_settle\":1000,\"currency\":\"cny\",\"subject\":\"农夫山泉\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555857512,\"time_expire\":1555864710,\"time_settle\":null,\"transaction_no\":\"1290815313201904211598433725\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_1innTSWL88C4Lybvj5qDWn5G/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_Cy5eXDK8SqDOn9Siv1n5Cm5K\",\"pending_webhooks\":0}', '124.77.208.137', 2, '2019-04-21 22:38:28', '2019-04-21 22:38:32');
INSERT INTO `transaction_extension` VALUES (61, 27, 9999, '20190422193220947110', NULL, '180.167.213.26', 1, '2019-04-22 19:32:20', '2019-04-22 19:32:20');
INSERT INTO `transaction_extension` VALUES (62, 27, 9999, '20190422193220963179', NULL, '180.167.213.26', 1, '2019-04-22 19:32:20', '2019-04-22 19:32:20');
INSERT INTO `transaction_extension` VALUES (63, 27, 9999, '20190422193228831286', '{\"id\":\"evt_400190422193230188658202\",\"created\":1555932750,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_88KOiLK0SCKC9yjDyHbXTGi9\",\"object\":\"charge\",\"created\":1555932748,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190422193228831286\",\"client_ip\":\"180.167.213.26\",\"amount\":1000,\"amount_settle\":1000,\"currency\":\"cny\",\"subject\":\"农夫山泉\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555932750,\"time_expire\":1555939948,\"time_settle\":null,\"transaction_no\":\"1244224811201904228521103170\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_88KOiLK0SCKC9yjDyHbXTGi9/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_OivrT0mDC0S0ebH084WfDurH\",\"pending_webhooks\":0}', '180.167.213.26', 2, '2019-04-22 19:32:28', '2019-04-22 19:32:30');
INSERT INTO `transaction_extension` VALUES (64, 28, 9999, '20190422193555407790', '{\"id\":\"evt_400190422193557007553903\",\"created\":1555932957,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_bnHaj9OCCmHO0COi10PC84yH\",\"object\":\"charge\",\"created\":1555932956,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190422193555407790\",\"client_ip\":\"180.167.213.26\",\"amount\":1000,\"amount_settle\":1000,\"currency\":\"cny\",\"subject\":\"农夫山泉\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555932957,\"time_expire\":1555940156,\"time_settle\":null,\"transaction_no\":\"1213285149201904222299145413\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_bnHaj9OCCmHO0COi10PC84yH/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_DmDWfDTKm5G48S4e1CzrLCqL\",\"pending_webhooks\":0}', '180.167.213.26', 2, '2019-04-22 19:35:55', '2019-04-22 19:35:57');
INSERT INTO `transaction_extension` VALUES (65, 30, 9999, '20190422193613519208', '{\"id\":\"evt_400190422193615007567003\",\"created\":1555932975,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_Wr5ibDPO8CSCyv58OKzvz5SS\",\"object\":\"charge\",\"created\":1555932973,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190422193613519208\",\"client_ip\":\"180.167.213.26\",\"amount\":160,\"amount_settle\":160,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555932975,\"time_expire\":1555940173,\"time_settle\":null,\"transaction_no\":\"1245834775201904220995332009\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_Wr5ibDPO8CSCyv58OKzvz5SS/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_f5efrHa948CSrXnfHSSe5aDC\",\"pending_webhooks\":0}', '180.167.213.26', 2, '2019-04-22 19:36:13', '2019-04-22 19:36:15');
INSERT INTO `transaction_extension` VALUES (66, 38, 9999, '20190422205144153198', '{\"id\":\"evt_400190422205145191862702\",\"created\":1555937505,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_94GyH4ePiTKOurLmzPjvHyfD\",\"object\":\"charge\",\"created\":1555937504,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190422205144153198\",\"client_ip\":\"114.87.158.59\",\"amount\":160,\"amount_settle\":160,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555937505,\"time_expire\":1555944704,\"time_settle\":null,\"transaction_no\":\"1209794355201904229853980782\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_94GyH4ePiTKOurLmzPjvHyfD/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_ujPebL4uXzfDDKyvP8OinDaD\",\"pending_webhooks\":0}', '114.87.158.59', 2, '2019-04-22 20:51:44', '2019-04-22 20:51:46');
INSERT INTO `transaction_extension` VALUES (67, 43, 9999, '20190422222919577859', NULL, '127.0.0.1', 1, '2019-04-22 22:29:19', '2019-04-22 22:29:19');
INSERT INTO `transaction_extension` VALUES (68, 45, 9999, '20190422223724521213', '{\"id\":\"evt_400190422223726196045502\",\"created\":1555943845,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_vfXjrLzfvzv1D8aD0GvHW5mH\",\"object\":\"charge\",\"created\":1555943844,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190422223724521213\",\"client_ip\":\"127.0.0.1\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555943845,\"time_expire\":1555951044,\"time_settle\":null,\"transaction_no\":\"1257829370201904225516383887\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_vfXjrLzfvzv1D8aD0GvHW5mH/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_1CCSmHmjnT4San5yD44SmDiH\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-22 22:37:24', '2019-04-22 22:37:26');
INSERT INTO `transaction_extension` VALUES (69, 46, 9999, '20190423002223556779', '{\"id\":\"evt_400190423002227198896602\",\"created\":1555950147,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_ifn1y5bDqjL0rbTe54eLazPC\",\"object\":\"charge\",\"created\":1555950144,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190423002223556779\",\"client_ip\":\"124.77.208.137\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555950147,\"time_expire\":1555957344,\"time_settle\":null,\"transaction_no\":\"1295079419201904238932409449\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_ifn1y5bDqjL0rbTe54eLazPC/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_LKKun1S4KGS4L4Cmv980iznL\",\"pending_webhooks\":0}', '124.77.208.137', 2, '2019-04-23 00:22:23', '2019-04-23 00:22:27');
INSERT INTO `transaction_extension` VALUES (70, 47, 9999, '20190423100352158401', '{\"id\":\"evt_400190423100354205607502\",\"created\":1555985033,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_DCGyXTmDGuHKb1C0yTzjPOGC\",\"object\":\"charge\",\"created\":1555985032,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190423100352158401\",\"client_ip\":\"114.87.158.59\",\"amount\":10,\"amount_settle\":10,\"currency\":\"cny\",\"subject\":\"kafka 实战\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1555985033,\"time_expire\":1555992232,\"time_settle\":null,\"transaction_no\":\"1244341374201904238178164740\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_DCGyXTmDGuHKb1C0yTzjPOGC/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_4e9mPODW5ujPqLen5OOmvL8S\",\"pending_webhooks\":0}', '114.87.158.59', 2, '2019-04-23 10:03:52', '2019-04-23 10:03:54');
INSERT INTO `transaction_extension` VALUES (71, 48, 9999, '20190427000222965600', NULL, '127.0.0.1', 1, '2019-04-27 00:02:22', '2019-04-27 00:02:22');
INSERT INTO `transaction_extension` VALUES (72, 48, 9999, '20190427001401428599', NULL, '127.0.0.1', 1, '2019-04-27 00:14:01', '2019-04-27 00:14:01');
INSERT INTO `transaction_extension` VALUES (73, 48, 9999, '20190427002729520230', NULL, '127.0.0.1', 1, '2019-04-27 00:27:29', '2019-04-27 00:27:29');
INSERT INTO `transaction_extension` VALUES (74, 48, 9999, '20190427003029372252', NULL, '127.0.0.1', 1, '2019-04-27 00:30:29', '2019-04-27 00:30:29');
INSERT INTO `transaction_extension` VALUES (75, 48, 9999, '20190427003306933611', NULL, '127.0.0.1', 1, '2019-04-27 00:33:06', '2019-04-27 00:33:06');
INSERT INTO `transaction_extension` VALUES (76, 48, 9999, '20190427003703859860', NULL, '127.0.0.1', 1, '2019-04-27 00:37:03', '2019-04-27 00:37:03');
INSERT INTO `transaction_extension` VALUES (77, 48, 9999, '20190427003923419775', '{\"id\":\"evt_400190427003925180938603\",\"created\":1556296765,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_CGiLSC1OSubPOGSS0Ofv9urT\",\"object\":\"charge\",\"created\":1556296763,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190427003923419775\",\"client_ip\":\"127.0.0.1\",\"amount\":10000,\"amount_settle\":10000,\"currency\":\"cny\",\"subject\":\"Kafka 书籍汇总\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1556296765,\"time_expire\":1556303963,\"time_settle\":null,\"transaction_no\":\"1298384458201904270239520896\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_CGiLSC1OSubPOGSS0Ofv9urT/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_Lu14aDujv5i9OKyTa9jDGKm9\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-27 00:39:23', '2019-04-27 00:39:25');
INSERT INTO `transaction_extension` VALUES (78, 49, 9999, '20190427004307995529', '{\"id\":\"evt_400190427004309341045702\",\"created\":1556296989,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_ebH8u5a9mzL80qDqzP8qPybP\",\"object\":\"charge\",\"created\":1556296988,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190427004307995529\",\"client_ip\":\"127.0.0.1\",\"amount\":10000,\"amount_settle\":10000,\"currency\":\"cny\",\"subject\":\"Kafka 书籍汇总\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1556296989,\"time_expire\":1556304188,\"time_settle\":null,\"transaction_no\":\"1230418317201904273841336004\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_ebH8u5a9mzL80qDqzP8qPybP/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_GebHeP1effjL0OSOqDzLurHG\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-27 00:43:07', '2019-04-27 00:43:10');
INSERT INTO `transaction_extension` VALUES (79, 50, 9999, '20190427004410165545', '{\"id\":\"evt_400190427004411341065302\",\"created\":1556297051,\"livemode\":false,\"type\":\"charge.succeeded\",\"data\":{\"object\":{\"id\":\"ch_y1iXjLnDS4G4OO4uT4a5C4W1\",\"object\":\"charge\",\"created\":1556297050,\"livemode\":false,\"paid\":true,\"refunded\":false,\"reversed\":false,\"app\":\"app_aTyfXDjrvzDSbLuz\",\"channel\":\"wx_pub\",\"order_no\":\"20190427004410165545\",\"client_ip\":\"127.0.0.1\",\"amount\":10000,\"amount_settle\":10000,\"currency\":\"cny\",\"subject\":\"Kafka 书籍汇总\",\"body\":\"测试描述\",\"extra\":{\"open_id\":\"just_for_test\",\"bank_type\":\"your bank type\"},\"time_paid\":1556297051,\"time_expire\":1556304250,\"time_settle\":null,\"transaction_no\":\"1267784917201904270679599868\",\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_y1iXjLnDS4G4OO4uT4a5C4W1/refunds\",\"has_more\":false,\"data\":[]},\"amount_refunded\":0,\"failure_code\":null,\"failure_msg\":null,\"metadata\":{},\"credential\":{},\"description\":\"测试备注\"}},\"object\":\"event\",\"request\":\"iar_0GW50C9mfnjLLuX5WDWXHGmD\",\"pending_webhooks\":0}', '127.0.0.1', 2, '2019-04-27 00:44:10', '2019-04-27 00:44:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
