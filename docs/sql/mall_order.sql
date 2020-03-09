/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 127.0.0.1:3306
 Source Schema         : mall_order

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/06/2019 07:57:22
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
  `order_create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='cart_item';

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`) USING BTREE
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
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for order_comment
-- ----------------------------

DROP TABLE IF EXISTS `order_comment`;

CREATE TABLE `order_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_no` varchar(50) NOT NULL COMMENT '订单编号',
  `product_spu_id` int(11) NOT NULL COMMENT '商品SPU',
  `product_spu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品SPU名称 SPU这两个字段待考量是否加入',
  `product_sku_id` int(11) NOT NULL COMMENT '商品的sku',
  `product_sku_attrs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品规格属性',
  `product_sku_price` int(11) NOT NULL COMMENT '商品价格，单位：分',
  `product_sku_pic_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的图片地址',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
  `user_nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `star` int(3) DEFAULT '5' COMMENT '评价星数:0->5',
  `product_description_star` int(3) DEFAULT '5' COMMENT '商品描述:0->5',
  `logistics_star` int(3) DEFAULT '5' COMMENT '物流星数:0->5',
  `merchant_star` int(3) DEFAULT '5' COMMENT '商家星数:0->5',
  `replay_count` int(11) DEFAULT '0' COMMENT '回复的条数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞的个数',
  `comment_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论的内容',
  `comment_pics` varchar(1000) DEFAULT NULL COMMENT '评论的图片地址 按照,分割',
  `comment_state` int(3) DEFAULT '0' COMMENT '评论的状态 0待评论 1已评论',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_comment_replay
-- ----------------------------

DROP TABLE IF EXISTS `order_comment_replay`;

CREATE TABLE `order_comment_replay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `comment_id` int(11) NOT NULL COMMENT '评论id',
  `reply_type` int(1) NOT NULL COMMENT '回复的类型:0基于评论的回复1基于回复的回复',
  `parent_id` int(11) NOT NULL COMMENT '父id: parent_id=comment_id 基于评论的回复,否则基于回复的回复',
  `parent_user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回复目标用户id',
  `parent_user_nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回复目标用户的真实姓名',
  `parent_user_avatar` varchar(255) DEFAULT NULL COMMENT '回复目标的头像',
  `reply_content` varchar(255) DEFAULT NULL COMMENT '回复的内容',
  `reply_user_id` int(11) NOT NULL COMMENT '回复用户id',
  `reply_user_nick_name` varchar(255) NOT NULL COMMENT '回复用户昵称',
  `reply_user_avatar` varchar(255) NOT NULL COMMENT '回复用户头像',
  `user_type` int(3) DEFAULT '0' COMMENT '回复用户的身份:0普通用户,1商家',
  `reply_like_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复的点赞数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '回复更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  `refuse_time` datetime DEFAULT NULL COMMENT '拒绝时间',
  `logistics_time` datetime DEFAULT NULL COMMENT '物流时间（填写物流单号时间）',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `closing_time` datetime DEFAULT NULL COMMENT '成交时间',
  `service_type` int(2) DEFAULT NULL COMMENT ' 1、退货 2、退款',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(2) NOT NULL COMMENT '状态 申请换货、申请成功、申请失败、退货中、退货成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
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
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
