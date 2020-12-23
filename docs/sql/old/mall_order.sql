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
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
