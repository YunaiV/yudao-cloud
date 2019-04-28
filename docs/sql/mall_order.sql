/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_order

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 28/04/2019 18:05:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，唯一自增。',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态\n     *\n     * 1-正常\n     * 2-主动删除\n     * 3-下单删除',
  `delete_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '商品在购物车中的删除时间',
  `selected` bit(1) NOT NULL COMMENT '是否选中',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `spu_id` int(11) NOT NULL COMMENT '商品 SPU 编号',
  `sku_id` int(11) NOT NULL COMMENT '商品 SKU 编号',
  `quantity` int(11) NOT NULL COMMENT '商品购买数量',
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `order_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单创建时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='cart_item';

-- ----------------------------
-- Records of cart_item
-- ----------------------------
BEGIN;
INSERT INTO `cart_item` VALUES (1, 2, '2019-04-17 20:45:25', b'1', 1, 29, 33, 6, NULL, '2019-04-11 17:00:37', '2019-04-11 17:00:37', '2019-04-17 20:45:25');
INSERT INTO `cart_item` VALUES (2, 2, '2019-04-14 01:27:15', b'1', 1, 28, 32, 1, NULL, '2019-04-13 21:28:36', '2019-04-13 21:28:37', '2019-04-14 01:27:15');
INSERT INTO `cart_item` VALUES (3, 2, '2019-04-20 19:57:31', b'1', 1, 29, 33, 3, NULL, '2019-04-14 01:37:45', '2019-04-14 01:37:45', '2019-04-20 19:57:31');
INSERT INTO `cart_item` VALUES (4, 2, '2019-04-20 19:57:31', b'1', 1, 32, 34, 1, NULL, '2019-04-17 10:23:55', '2019-04-17 10:23:56', '2019-04-20 19:57:31');
INSERT INTO `cart_item` VALUES (5, 1, '2019-04-19 20:35:36', b'1', 29, 32, 34, 1, NULL, '2019-04-19 20:35:36', '2019-04-19 20:35:37', '2019-04-19 20:35:36');
INSERT INTO `cart_item` VALUES (6, 1, '2019-04-19 20:35:46', b'1', 29, 28, 31, 4, NULL, '2019-04-19 20:35:46', '2019-04-19 20:35:46', '2019-04-19 20:35:46');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id，自增长',
  `user_id` int(11) NOT NULL COMMENT '用户id\n',
  `order_no` varchar(50) NOT NULL COMMENT '订单单号',
  `buy_price` int(11) NOT NULL COMMENT '订单金额(总金额)',
  `discount_price` int(11) NOT NULL COMMENT '优惠总金额，单位：分',
  `logistics_price` int(11) NOT NULL COMMENT '物流金额',
  `present_price` int(11) NOT NULL COMMENT '最终金额，单位：分',
  `pay_amount` int(10) NOT NULL COMMENT '金额(分)',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `closing_time` datetime DEFAULT NULL COMMENT '成交时间',
  `has_return_exchange` smallint(6) NOT NULL COMMENT '是否退换货',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` smallint(2) NOT NULL COMMENT '状态(如果有多个商品分开发货需要全部商品发完才会改变状态) 0、待付款 1、待发货 2、待收货 3、已完成 4、已关闭',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` smallint(6) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (47, 29, '29635cff9cc8419', 10, 0, 0, 0, 10, NULL, NULL, NULL, NULL, 1, '添加一个测试备注', 5, '2019-04-11 22:15:32', NULL, 0);
INSERT INTO `order` VALUES (48, 29, '8472c75db7794b', 10, 0, 0, 0, 10, NULL, NULL, NULL, NULL, 1, '', 5, '2019-04-11 22:16:03', NULL, 0);
INSERT INTO `order` VALUES (49, 29, '00be8f5971c24b', 10, 0, 0, 0, 10, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-11 22:19:17', NULL, 0);
INSERT INTO `order` VALUES (50, 29, 'b4e42922b2634', 10, 0, 0, 0, 10, NULL, NULL, '2019-04-12 21:27:13', NULL, 1, '', 4, '2019-04-11 22:19:42', NULL, 0);
INSERT INTO `order` VALUES (51, -1, '76c163c61ffb465f', 362, 0, 0, 0, 362, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-14 01:27:15', NULL, 0);
INSERT INTO `order` VALUES (74, -1, '2186b8b4ca32442c', 1200, 40, 0, 1160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-20 19:57:27', NULL, 0);
INSERT INTO `order` VALUES (75, -1, '0668f5a6d168471c', 100, 0, 0, 100, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-20 19:58:10', NULL, 0);
INSERT INTO `order` VALUES (76, -1, '6f465f43b2f34a3f', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-20 22:19:28', NULL, 0);
INSERT INTO `order` VALUES (89, -1, 'd4867f5320bb4d07', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-20 23:42:56', NULL, 0);
INSERT INTO `order` VALUES (90, -1, '2667b89fbb5e49d9', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-20 23:44:43', NULL, 0);
INSERT INTO `order` VALUES (95, -1, '7aa0c5558746431b', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 02:17:24', NULL, 0);
INSERT INTO `order` VALUES (97, -1, '2dd9173348c94047', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 14:22:49', NULL, 0);
INSERT INTO `order` VALUES (98, -1, '5987e77245064f5c', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:01:50', NULL, 0);
INSERT INTO `order` VALUES (99, -1, '0aa0ff0bb2a74180', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:05:21', NULL, 0);
INSERT INTO `order` VALUES (101, -1, 'e7f4804c54774747', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:06:30', NULL, 0);
INSERT INTO `order` VALUES (102, -1, 'cdff09a25c724c02', 100, 0, 0, 100, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:10:39', NULL, 0);
INSERT INTO `order` VALUES (103, -1, '7de9b6251e434807', 100, 0, 0, 100, 100, '2019-04-21 15:21:26', NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:14:17', NULL, 0);
INSERT INTO `order` VALUES (104, -1, '80475d74fc1f4a56', 100, 0, 0, 100, 100, '2019-04-21 15:20:12', NULL, NULL, NULL, 1, '', 1, '2019-04-21 15:19:19', NULL, 0);
INSERT INTO `order` VALUES (105, -1, 'ce4966bed7104dd5', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 18:59:24', NULL, 0);
INSERT INTO `order` VALUES (106, -1, 'a72347c6571f4f74', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 19:08:18', NULL, 0);
INSERT INTO `order` VALUES (107, -1, '7dedc1e8c6484530', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 19:22:19', NULL, 0);
INSERT INTO `order` VALUES (109, -1, '0635a03e4ad8439d', 100, 0, 0, 100, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 19:57:13', NULL, 0);
INSERT INTO `order` VALUES (110, -1, '8cfb63d2f17445bb', 100, 0, 0, 100, 100, '2019-04-21 20:17:20', NULL, NULL, NULL, 1, '', 3, '2019-04-21 20:17:04', NULL, 0);
INSERT INTO `order` VALUES (111, -1, '11e64862f6d94ceb', 1000, 0, 0, 1000, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-21 20:24:05', NULL, 0);
INSERT INTO `order` VALUES (112, -1, '472994edfd90458d', 1000, 0, 0, 1000, 1000, '2019-04-22 19:32:35', NULL, '2019-04-22 19:32:42', NULL, 1, '', 4, '2019-04-21 20:37:45', NULL, 0);
INSERT INTO `order` VALUES (113, -1, 'aee52dfc8bef4603', 1000, 0, 0, 1000, 1000, '2019-04-22 19:35:58', NULL, NULL, NULL, 1, '', 3, '2019-04-21 20:51:38', NULL, 0);
INSERT INTO `order` VALUES (114, -1, '2da845ff0da74b8b', 1000, 0, 0, 1000, 1000, '2019-04-21 22:38:34', NULL, NULL, NULL, 1, '', 3, '2019-04-21 22:38:24', NULL, 0);
INSERT INTO `order` VALUES (115, -1, '23ce74ee3cbc49d8', 200, 40, 0, 160, 160, '2019-04-22 19:36:16', NULL, NULL, NULL, 1, '', 3, '2019-04-21 23:29:21', NULL, 0);
INSERT INTO `order` VALUES (118, -1, '6d73bb4e01c349d6', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 17:05:59', NULL, 0);
INSERT INTO `order` VALUES (120, -1, '6377703d238e4b88', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 19:31:55', NULL, 0);
INSERT INTO `order` VALUES (121, -1, '3dc33dcaf4b24724', 44000, 0, 0, 44000, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 19:35:45', NULL, 0);
INSERT INTO `order` VALUES (122, -1, '6074931dc45541f9', 200, 40, 0, 160, 160, '2019-04-22 20:51:46', NULL, NULL, NULL, 1, '', 3, '2019-04-22 20:51:39', NULL, 0);
INSERT INTO `order` VALUES (124, -1, 'fc6c662e66a24a86', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 22:19:58', NULL, 0);
INSERT INTO `order` VALUES (125, -1, '0c17c9bc441645f2', 200, 40, 0, 160, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 22:21:26', NULL, 0);
INSERT INTO `order` VALUES (127, -1, 'af5c4f40bc9c478d', 10, 0, 0, 10, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 22:28:21', NULL, 0);
INSERT INTO `order` VALUES (128, 1, '53cd76bff19242e8', 10, 0, 0, 10, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 22:29:16', NULL, 0);
INSERT INTO `order` VALUES (129, 1, 'a8a40abdb2cc430b', 10, 0, 0, 10, 0, NULL, NULL, NULL, NULL, 1, '', 1, '2019-04-22 22:37:18', NULL, 0);
INSERT INTO `order` VALUES (130, 1, 'e6d4fe973a404508', 10, 0, 0, 10, 10, '2019-04-22 22:37:27', NULL, NULL, NULL, 1, '', 3, '2019-04-22 22:37:23', NULL, 0);
INSERT INTO `order` VALUES (131, 1, 'e2edc556fff847c5', 10, 0, 0, 10, 10, '2019-04-23 00:22:28', NULL, NULL, NULL, 1, '', 2, '2019-04-23 00:22:22', NULL, 0);
INSERT INTO `order` VALUES (132, 1, 'a6d7da64863c499e', 10, 0, 0, 10, 10, '2019-04-23 10:03:55', NULL, NULL, NULL, 1, '', 2, '2019-04-23 10:03:50', NULL, 0);
INSERT INTO `order` VALUES (133, 1, 'e815710ad0014f35', 10000, 0, 0, 10000, 10000, '2019-04-27 00:43:50', NULL, NULL, NULL, 1, '', 2, '2019-04-27 00:02:20', NULL, 0);
INSERT INTO `order` VALUES (134, 1, 'a624ad4406554db5', 10000, 0, 0, 10000, 10000, '2019-04-27 00:43:17', NULL, NULL, NULL, 1, '', 2, '2019-04-27 00:43:06', NULL, 0);
INSERT INTO `order` VALUES (135, 1, 'ffff6dd443a844ee', 10000, 0, 0, 10000, 10000, '2019-04-27 00:44:12', NULL, NULL, NULL, 1, '', 2, '2019-04-27 00:44:09', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for order_cancel
-- ----------------------------
DROP TABLE IF EXISTS `order_cancel`;
CREATE TABLE `order_cancel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `reason` int(2) NOT NULL,
  `other_reason` varchar(100) DEFAULT NULL COMMENT '其他原因',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_cancel
-- ----------------------------
BEGIN;
INSERT INTO `order_cancel` VALUES (5, 11, 'd31a7b9e2bb2483787dc601127f94c4d', 2, NULL, '2019-03-30 17:48:17', NULL);
INSERT INTO `order_cancel` VALUES (6, 47, '29635cff9cc8419', 7, NULL, '2019-04-12 19:55:50', NULL);
INSERT INTO `order_cancel` VALUES (7, 48, '8472c75db7794b', 6, NULL, '2019-04-12 19:56:07', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_exchange
-- ----------------------------
DROP TABLE IF EXISTS `order_exchange`;
CREATE TABLE `order_exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `order_no` varchar(50) NOT NULL,
  `sku_id` int(11) NOT NULL,
  `exchange_sku_id` int(11) NOT NULL COMMENT '换货商品id',
  `exchange_order_logistics_id` int(11) NOT NULL COMMENT '换货物流id',
  `receiver_order_logistics_id` int(11) NOT NULL COMMENT '收件地址',
  `order_reason_id` int(11) DEFAULT NULL COMMENT '换货原因',
  `reason` varchar(255) DEFAULT NULL COMMENT '换货原因 (其他的时候)',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `closing_time` datetime DEFAULT NULL COMMENT '成交时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete` smallint(2) DEFAULT NULL COMMENT '删除状态',
  `order_type` int(2) DEFAULT NULL COMMENT '订单类型 0、为 Order 订单 1、为 OrderItem 订单',
  `status` int(2) DEFAULT NULL COMMENT '状态 申请换货、申请成功、申请失败、换货中、换货成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `order_logistics_id` int(11) DEFAULT NULL COMMENT '物流id',
  `sku_id` int(11) NOT NULL COMMENT '商品id',
  `sku_name` varchar(50) NOT NULL COMMENT '商品名字',
  `sku_image` varchar(250) NOT NULL COMMENT '图片名字',
  `quantity` int(3) NOT NULL COMMENT '商品数量',
  `origin_price` int(11) NOT NULL COMMENT '原始单价，单位：分',
  `buy_price` int(11) NOT NULL COMMENT '购买单价，单位：分',
  `present_price` int(11) NOT NULL COMMENT '最终价格，单位：分',
  `buy_total` int(11) NOT NULL COMMENT '购买总金额，单位：分',
  `discount_total` int(11) NOT NULL COMMENT '优惠总金额，单位：分',
  `present_total` int(11) NOT NULL COMMENT '最终总金额，单位：分',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `closing_time` datetime DEFAULT NULL,
  `has_return_exchange` int(11) DEFAULT NULL COMMENT '是否退换货',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `delivery_type` int(2) NOT NULL COMMENT '发送方式',
  `status` smallint(2) NOT NULL COMMENT '状态：0、代发货 1、已发货 2、已收货 20、换货中 21、换货成功 40、退货中 41、已退货',
  `deleted` smallint(2) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (19, 47, '29635cff9cc841948ef3c2cfc3fb7911', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, '2019-04-11 22:15:32', NULL, NULL, 1, 5, 0);
INSERT INTO `order_item` VALUES (20, 48, '8472c75db7794b158ce9c3146fbb4e02', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, '2019-04-11 22:16:03', NULL, NULL, 1, 5, 0);
INSERT INTO `order_item` VALUES (21, 49, '00be8f5971c24bbcbddf9014bb0d2310', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, '2019-04-11 22:19:17', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (22, 50, 'b4e42922b2634c1a94f01b2d66eeb8bf', 34, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 0, 0, 0, 0, NULL, NULL, '2019-04-12 21:27:14', NULL, 1, '2019-04-11 22:19:42', NULL, NULL, 1, 4, 0);
INSERT INTO `order_item` VALUES (23, 51, '76c163c61ffb465f', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 6, 10, 60, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, '2019-04-14 01:27:15', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (24, 51, '76c163c61ffb465f', NULL, 32, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 2, 2, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 1, '2019-04-14 01:27:15', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (25, 74, '2186b8b4ca32442c', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 3, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-20 19:57:27', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (26, 74, '2186b8b4ca32442c', NULL, 34, '农夫山泉', 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 125, 50, 50, 1000, 0, 1000, NULL, NULL, NULL, NULL, 1, '2019-04-20 19:57:27', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (27, 75, '0668f5a6d168471c', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-20 19:58:10', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (28, 76, '6f465f43b2f34a3f', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-20 22:19:28', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (41, 89, 'd4867f5320bb4d07', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-20 23:42:56', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (42, 90, '2667b89fbb5e49d9', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-20 23:44:43', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (47, 95, '7aa0c5558746431b', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 02:17:24', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (49, 97, '2dd9173348c94047', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 14:22:49', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (50, 98, '5987e77245064f5c', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:01:50', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (51, 99, '0aa0ff0bb2a74180', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:05:21', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (53, 101, 'e7f4804c54774747', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:06:30', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (54, 102, 'cdff09a25c724c02', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:10:39', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (55, 103, '7de9b6251e434807', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:14:17', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (56, 104, '80475d74fc1f4a56', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-21 15:19:19', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (57, 105, 'ce4966bed7104dd5', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 18:59:24', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (58, 106, 'a72347c6571f4f74', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 19:08:18', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (59, 107, '7dedc1e8c6484530', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 19:22:19', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (61, 109, '0635a03e4ad8439d', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-21 19:57:14', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (62, 110, '8cfb63d2f17445bb', NULL, 31, '测试商品', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 1, 1, 1, 100, 0, 100, NULL, NULL, NULL, NULL, 1, '2019-04-21 20:17:04', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (63, 111, '11e64862f6d94ceb', NULL, 34, '农夫山泉', 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 125, 50, 50, 1000, 0, 1000, NULL, NULL, NULL, NULL, 1, '2019-04-21 20:24:05', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (64, 112, '472994edfd90458d', NULL, 34, '农夫山泉', 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 125, 50, 50, 1000, 0, 1000, NULL, NULL, '2019-04-22 19:32:42', NULL, 1, '2019-04-21 20:37:45', NULL, NULL, 1, 4, 0);
INSERT INTO `order_item` VALUES (65, 113, 'aee52dfc8bef4603', NULL, 34, '农夫山泉', 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 125, 50, 50, 1000, 0, 1000, NULL, NULL, NULL, NULL, 1, '2019-04-21 20:51:38', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (66, 114, '2da845ff0da74b8b', NULL, 34, '农夫山泉', 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 125, 50, 50, 1000, 0, 1000, NULL, NULL, NULL, NULL, 1, '2019-04-21 22:38:24', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (67, 115, '23ce74ee3cbc49d8', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-21 23:29:21', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (70, 118, '6d73bb4e01c349d6', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-22 17:06:00', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (72, 120, '6377703d238e4b88', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-22 19:31:55', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (73, 121, '3dc33dcaf4b24724', NULL, 41, 'Oracle', 'https://static.oschina.net/img/logo/oracle.gif', 1, 2000, 2000, 2000, 44000, 0, 44000, NULL, NULL, NULL, NULL, 1, '2019-04-22 19:35:45', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (74, 122, '6074931dc45541f9', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-22 20:51:39', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (76, 124, 'fc6c662e66a24a86', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:19:58', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (77, 125, '0c17c9bc441645f2', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 8, 200, 40, 160, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:21:26', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (79, 127, 'af5c4f40bc9c478d', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:28:21', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (80, 128, '53cd76bff19242e8', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:29:16', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (81, 129, 'a8a40abdb2cc430b', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:37:18', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (82, 130, 'e6d4fe973a404508', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-22 22:37:23', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (83, 131, 'e2edc556fff847c5', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-23 00:22:22', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (84, 132, 'a6d7da64863c499e', NULL, 33, 'kafka 实战', 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 10, 10, 10, 10, 0, 10, NULL, NULL, NULL, NULL, 1, '2019-04-23 10:03:50', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (85, 133, 'e815710ad0014f35', NULL, 36, 'Kafka 书籍汇总', 'http://static.iocoder.cn/kafka.png', 1, 10000, 10000, 10000, 10000, 0, 10000, NULL, NULL, NULL, NULL, 1, '2019-04-27 00:02:20', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (86, 134, 'a624ad4406554db5', NULL, 36, 'Kafka 书籍汇总', 'http://static.iocoder.cn/kafka.png', 1, 10000, 10000, 10000, 10000, 0, 10000, NULL, NULL, NULL, NULL, 1, '2019-04-27 00:43:06', NULL, NULL, 1, 1, 0);
INSERT INTO `order_item` VALUES (87, 135, 'ffff6dd443a844ee', NULL, 36, 'Kafka 书籍汇总', 'http://static.iocoder.cn/kafka.png', 1, 10000, 10000, 10000, 10000, 0, 10000, NULL, NULL, NULL, NULL, 1, '2019-04-27 00:44:09', NULL, NULL, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for order_logistics
-- ----------------------------
DROP TABLE IF EXISTS `order_logistics`;
CREATE TABLE `order_logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `area_no` varchar(10) NOT NULL COMMENT '地区编号',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `address` varchar(255) NOT NULL COMMENT '详细地址',
  `logistics` int(2) NOT NULL COMMENT '物流商家',
  `logistics_no` varchar(20) NOT NULL COMMENT '物流单号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_logistics
-- ----------------------------
BEGIN;
INSERT INTO `order_logistics` VALUES (24, '110101', 'Andy', '13302925934', '中二环，光电大厦11F 前台收', 1, '23123124123', '2019-04-11 22:50:31', NULL);
INSERT INTO `order_logistics` VALUES (34, '110101', 'Andy', '13302925934', '中二环，光电大厦11F 前台收', 1, '314123123123', '2019-04-12 19:23:42', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_logistics_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_logistics_detail`;
CREATE TABLE `order_logistics_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `order_logistics_id` int(11) NOT NULL COMMENT '物流编号',
  `logistics_time` datetime NOT NULL COMMENT '物流时间',
  `logistics_information` varchar(20) NOT NULL COMMENT '物流信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_logistics_detail
-- ----------------------------
BEGIN;
INSERT INTO `order_logistics_detail` VALUES (1, 34, '2019-04-12 22:46:15', '已经到上海总部', '2019-04-12 22:46:27', NULL);
INSERT INTO `order_logistics_detail` VALUES (2, 24, '2019-04-12 22:46:42', '一道成都，正在发往长沙', '2019-04-12 22:46:58', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_recipient
-- ----------------------------
DROP TABLE IF EXISTS `order_recipient`;
CREATE TABLE `order_recipient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `area_no` varchar(20) NOT NULL COMMENT '区域编号',
  `name` varchar(20) NOT NULL COMMENT '收件人名称',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `type` int(2) NOT NULL COMMENT '快递方式',
  `address` varchar(250) NOT NULL COMMENT '地址详细',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_recipient
-- ----------------------------
BEGIN;
INSERT INTO `order_recipient` VALUES (22, 47, '110101', 'Andy', '13302925934', 1, '中二环，光电大厦11F 前台收', '2019-04-11 22:15:32', NULL);
INSERT INTO `order_recipient` VALUES (23, 48, '110101', 'Andy', '13302925934', 1, '中二环，光电大厦11F 前台收', '2019-04-11 22:16:03', NULL);
INSERT INTO `order_recipient` VALUES (24, 49, '110101', 'Andy', '13302925934', 1, '中二环，光电大厦11F 前台收', '2019-04-11 22:19:17', NULL);
INSERT INTO `order_recipient` VALUES (25, 50, '110101', 'Andy', '13302925934', 1, '中二环，光电大厦11F 前台收', '2019-04-11 22:19:42', NULL);
INSERT INTO `order_recipient` VALUES (26, 51, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-14 01:27:15', NULL);
INSERT INTO `order_recipient` VALUES (49, 74, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-20 19:57:27', NULL);
INSERT INTO `order_recipient` VALUES (50, 75, '22033', 'sin', '18132019023', 1, '湖南省深圳市北京3环56号', '2019-04-20 19:58:10', NULL);
INSERT INTO `order_recipient` VALUES (51, 76, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-20 22:19:28', NULL);
INSERT INTO `order_recipient` VALUES (64, 89, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-20 23:42:56', NULL);
INSERT INTO `order_recipient` VALUES (65, 90, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-20 23:44:43', NULL);
INSERT INTO `order_recipient` VALUES (70, 95, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 02:17:24', NULL);
INSERT INTO `order_recipient` VALUES (72, 97, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 14:22:49', NULL);
INSERT INTO `order_recipient` VALUES (73, 98, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:01:50', NULL);
INSERT INTO `order_recipient` VALUES (74, 99, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:05:21', NULL);
INSERT INTO `order_recipient` VALUES (76, 101, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:06:30', NULL);
INSERT INTO `order_recipient` VALUES (77, 102, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:10:39', NULL);
INSERT INTO `order_recipient` VALUES (78, 103, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:14:17', NULL);
INSERT INTO `order_recipient` VALUES (79, 104, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 15:19:19', NULL);
INSERT INTO `order_recipient` VALUES (80, 105, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 18:59:24', NULL);
INSERT INTO `order_recipient` VALUES (81, 106, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 19:08:18', NULL);
INSERT INTO `order_recipient` VALUES (82, 107, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 19:22:19', NULL);
INSERT INTO `order_recipient` VALUES (84, 109, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 19:57:14', NULL);
INSERT INTO `order_recipient` VALUES (85, 110, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 20:17:04', NULL);
INSERT INTO `order_recipient` VALUES (86, 111, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 20:24:05', NULL);
INSERT INTO `order_recipient` VALUES (87, 112, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 20:37:45', NULL);
INSERT INTO `order_recipient` VALUES (88, 113, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 20:51:38', NULL);
INSERT INTO `order_recipient` VALUES (89, 114, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 22:38:24', NULL);
INSERT INTO `order_recipient` VALUES (90, 115, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-21 23:29:21', NULL);
INSERT INTO `order_recipient` VALUES (93, 118, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 17:06:00', NULL);
INSERT INTO `order_recipient` VALUES (95, 120, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 19:31:55', NULL);
INSERT INTO `order_recipient` VALUES (96, 121, '22033', 'sin', '18132019023', 1, '湖南省深圳市北京3环56号', '2019-04-22 19:35:45', NULL);
INSERT INTO `order_recipient` VALUES (97, 122, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 20:51:39', NULL);
INSERT INTO `order_recipient` VALUES (99, 124, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:19:58', NULL);
INSERT INTO `order_recipient` VALUES (100, 125, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:21:26', NULL);
INSERT INTO `order_recipient` VALUES (102, 127, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:28:21', NULL);
INSERT INTO `order_recipient` VALUES (103, 128, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:29:16', NULL);
INSERT INTO `order_recipient` VALUES (104, 129, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:37:18', NULL);
INSERT INTO `order_recipient` VALUES (105, 130, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-22 22:37:23', NULL);
INSERT INTO `order_recipient` VALUES (106, 131, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-23 00:22:22', NULL);
INSERT INTO `order_recipient` VALUES (107, 132, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-23 10:03:50', NULL);
INSERT INTO `order_recipient` VALUES (108, 133, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-27 00:02:20', NULL);
INSERT INTO `order_recipient` VALUES (109, 134, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-27 00:43:06', NULL);
INSERT INTO `order_recipient` VALUES (110, 135, '230102', 'mi', '13302926050', 1, '湖南省深圳市北京去3环', '2019-04-27 00:44:09', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_return
-- ----------------------------
DROP TABLE IF EXISTS `order_return`;
CREATE TABLE `order_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `service_number` varchar(50) NOT NULL COMMENT '服务号',
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `order_logistics_id` int(11) DEFAULT NULL COMMENT '物流 id',
  `refund_price` int(11) NOT NULL COMMENT '退回金额',
  `reason` int(11) NOT NULL COMMENT '退货原因',
  `describe` varchar(255) DEFAULT NULL COMMENT '换货原因 (其他的时候)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `approval_time` datetime DEFAULT NULL COMMENT '同意时间',
  `logistics_time` datetime DEFAULT NULL COMMENT '物流时间（填写物流单号时间）',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `closing_time` datetime DEFAULT NULL COMMENT '成交时间',
  `service_type` int(2) DEFAULT NULL COMMENT ' 1、退货 2、退款',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(2) NOT NULL COMMENT '状态 申请换货、申请成功、申请失败、退货中、退货成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_return
-- ----------------------------
BEGIN;
INSERT INTO `order_return` VALUES (1, '1111111', 50, 'b4e42922b2634', 24, 1000, 38, '12312', '2019-04-26 00:14:10', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (2, '12312312', 50, 'b4e42922b2634', NULL, 0, 38, '123', '2019-04-26 00:15:29', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (3, '6754745', 50, 'b4e42922b2634', NULL, 0, 38, '123', '2019-04-26 00:15:31', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (4, '1234124', 50, 'b4e42922b2634', NULL, 0, 38, '123123', '2019-04-26 00:15:33', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (5, 'ga12355', 50, 'b4e42922b2634', NULL, 0, 38, '123123', '2019-04-26 00:15:34', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (6, 'dsgs1241', 50, 'b4e42922b2634', NULL, 0, 38, '123', '2019-04-26 00:16:21', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (7, 'gad12314', 50, 'b4e42922b2634', NULL, 0, 38, '123', '2019-04-26 00:16:24', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (8, 'ggasd123', 50, 'b4e42922b2634', NULL, 0, 38, '', '2019-04-26 00:17:10', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (9, 'amsfdl12314', 50, 'b4e42922b2634', NULL, 0, 38, '7', '2019-04-26 00:17:14', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (10, 'lo123', 50, 'b4e42922b2634', NULL, 0, 38, '6', '2019-04-26 00:17:18', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (11, 'sdfan231', 50, 'b4e42922b2634', NULL, 0, 38, '', '2019-04-26 00:18:08', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (12, 'kdau213j21', 50, 'b4e42922b2634', NULL, 0, 38, '', '2019-04-26 00:18:59', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (13, 'apdamfmn213', 50, 'b4e42922b2634', NULL, 0, 38, '1213', '2019-04-26 00:19:11', NULL, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `order_return` VALUES (14, 'gi2341', 50, 'b4e42922b2634', NULL, 0, 38, '', '2019-04-26 00:22:24', NULL, NULL, NULL, NULL, 1, NULL, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
