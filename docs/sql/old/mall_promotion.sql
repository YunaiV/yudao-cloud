/*
 Navicat Premium Data Transfer

 Source Server         : onemall
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.88.14:3306
 Source Schema         : mall_promotion

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/03/2020 07:37:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `url` varchar(255) NOT NULL COMMENT '跳转链接',
  `pic_url` varchar(255) NOT NULL COMMENT '跳转链接',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `memo` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='banner';

-- ----------------------------
-- Records of banner
-- ----------------------------
BEGIN;
INSERT INTO `banner` VALUES (1, '百度是最棒的', 'https://user-gold-cdn.xitu.io/2019/3/30/169cdcbb93d48ff1?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 'https://user-gold-cdn.xitu.io/2019/3/30/169cdcbb93d48ff1?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 0, 1, '哈哈哈哈', '2019-03-30 23:40:25', '2019-06-20 17:01:06', b'0');
INSERT INTO `banner` VALUES (2, '芋道源码', 'http://static.shop.iocoder.cn/8d9442ca-f34b-47f0-a4aa-f16e8721b932', 'http://static.shop.iocoder.cn/c3e1f77d-df16-48dd-8ed5-26950dff3944', 10, 1, '你猜呀哈哈哈哈', '2019-03-31 00:01:25', '2019-05-10 14:30:40', b'0');
INSERT INTO `banner` VALUES (3, '优惠劵活动', 'http://127.0.0.1:8080/#/coupon/fetch?id=1', 'https://img.youpin.mi-img.com/youpinoper/312b91dbab00d2d8d8aa25ac88532830.jpg?id=&w=1080&h=450', 5, 1, '都挺好牛逼', '2019-03-31 10:13:09', '2019-04-07 21:15:48', b'0');
INSERT INTO `banner` VALUES (4, '测试', 'https://www.baidu.com', 'http://static.shop.iocoder.cn/099f2870-2341-484c-a417-df23d39e3fdc', 4, 1, '测试', '2019-05-13 20:40:24', '2019-05-13 20:40:23', b'0');
COMMIT;

-- ----------------------------
-- Table structure for coupon_card
-- ----------------------------
DROP TABLE IF EXISTS `coupon_card`;
CREATE TABLE `coupon_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠劵编号',
  `template_id` int(11) NOT NULL COMMENT '优惠劵（码）分组编号，{@link CouponTemplateDO} 的 id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `status` tinyint(4) NOT NULL COMMENT '优惠码状态\n     *\n     * 1-未使用\n     * 2-已使用\n     * 3-已失效',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `take_type` tinyint(4) NOT NULL COMMENT '领取类型\n     *\n     * 1 - 用户主动领取\n     * 2 - 后台自动发放',
  `price_available` int(11) NOT NULL COMMENT '是否设置满多少金额可用，单位：分\n     *\n     * 0-不限制\n     * 大于0-多少金额可用',
  `valid_start_time` datetime NOT NULL COMMENT '生效开始时间',
  `valid_end_time` datetime NOT NULL COMMENT '生效结束时间',
  `preferential_type` tinyint(4) NOT NULL COMMENT '优惠类型\n     *\n     * 1-代金卷\n     * 2-折扣卷',
  `percent_off` tinyint(4) DEFAULT NULL COMMENT '折扣',
  `price_off` int(11) DEFAULT NULL COMMENT '优惠金额，单位：分。',
  `discount_price_limit` int(11) DEFAULT NULL COMMENT '折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。\n     *\n     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='coupon_card';

-- ----------------------------
-- Records of coupon_card
-- ----------------------------
BEGIN;
INSERT INTO `coupon_card` VALUES (1, 1, '优惠劵A', 2, 1, 1, 10000, '2019-04-08 00:00:00', '2019-04-10 00:00:00', 1, NULL, 1000, NULL, '2019-05-17 17:46:30', '2019-04-07 21:24:47', '2019-05-17 17:46:30');
INSERT INTO `coupon_card` VALUES (2, 1, '优惠劵B', 2, 1, 1, 2, '2019-04-08 00:00:00', '2019-04-10 00:00:00', 2, 49, 1000, NULL, '2019-05-17 14:21:17', '2019-04-07 21:25:05', '2019-05-17 14:21:17');
INSERT INTO `coupon_card` VALUES (3, 1, '优惠劵C', 2, 1, 1, 10, '2019-04-08 00:00:00', '2019-04-10 00:00:00', 1, NULL, 9, NULL, '2019-05-17 17:58:50', '2019-04-07 21:54:47', '2019-05-17 17:58:50');
COMMIT;

-- ----------------------------
-- Table structure for coupon_template
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模板编号，自增唯一。',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `description` varchar(50) DEFAULT NULL COMMENT '使用说明',
  `type` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '类型\n     *\n     * 1-优惠劵\n     * 2-优惠码',
  `code_type` tinyint(4) DEFAULT NULL COMMENT '码类型\n     *\n     * 1-一卡一码（UNIQUE）\n     * 2-通用码（GENERAL）\n     *\n     * 【优惠码独有】 @see CouponCodeDO',
  `status` tinyint(4) NOT NULL COMMENT '优惠码状态\n     *\n     * 1-开启中\n     * 2-禁用中\n     * 3-已过期\n     *\n     * 当优惠劵（码）开启中，可以手动操作，设置禁用中。',
  `quota` tinyint(4) DEFAULT NULL COMMENT '每人限领个数\n     *\n     * null - 则表示不限制',
  `total` int(11) DEFAULT NULL COMMENT '发行总量',
  `price_available` int(11) DEFAULT NULL COMMENT '是否设置满多少金额可用，单位：分\n     *\n     * 0-不限制\n     * 大于0-多少金额可用',
  `range_type` tinyint(4) NOT NULL COMMENT '可用范围的类型\n     *\n     * 10-全部（ALL）：所有可用\n     * 20-部分（PART）：部分商品可用，或指定商品可用\n     * 21-部分（PART）：部分商品不可用，或指定商品可用\n     * 30-部分（PART）：部分分类可用，或指定商品可用\n     * 31-部分（PART）：部分分类不可用，或指定商品可用',
  `range_values` varchar(50) DEFAULT NULL COMMENT '指定商品 / 分类列表，使用逗号分隔商品编号',
  `date_type` tinyint(4) NOT NULL COMMENT '生效日期类型\n     *\n     * 1-固定日期\n     * 2-领取日期：领到券 {@link #fixedTerm} 日开始 N 天内有效',
  `valid_start_time` datetime DEFAULT NULL COMMENT '固定日期-生效开始时间',
  `valid_end_time` datetime DEFAULT NULL COMMENT '固定日期-生效结束时间',
  `fixed_start_term` int(11) DEFAULT NULL COMMENT '领取日期-开始天数',
  `fixed_end_term` int(11) DEFAULT NULL COMMENT '领取日期-结束天数',
  `preferential_type` int(11) NOT NULL COMMENT '优惠类型\n     *\n     * 1-代金卷\n     * 2-折扣卷',
  `percent_off` tinyint(4) DEFAULT NULL COMMENT '折扣百分比。\n     *\n     * 例如，80% 为 80。\n     * 当 100% 为 100 ，则代表免费。',
  `price_off` int(11) DEFAULT NULL COMMENT '优惠金额，单位：分',
  `discount_price_limit` int(11) DEFAULT NULL COMMENT '折扣上限，仅在 {@link #preferentialType} 等于 2 时生效。\n     *\n     * 例如，折扣上限为 20 元，当使用 8 折优惠券，订单金额为 1000 元时，最高只可折扣 20 元，而非 80  元。',
  `stat_fetch_num` int(11) NOT NULL COMMENT '领取优惠券的次数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='coupon_template';

-- ----------------------------
-- Records of coupon_template
-- ----------------------------
BEGIN;
INSERT INTO `coupon_template` VALUES (1, '优惠劵A', '一个有味道的哈哈哈哈', 1, NULL, 1, 10, 100, 1000, 10, '', 2, NULL, NULL, 1, 2, 1, NULL, 1000, NULL, 1, '2019-04-05 11:26:23', '2019-05-12 00:37:34');
INSERT INTO `coupon_template` VALUES (2, '优惠劵B', '优惠劵B', 1, NULL, 1, 2, 10, 1000, 10, '10', 2, NULL, NULL, 3, 5, 1, NULL, 10000, NULL, 0, '2019-04-05 11:40:45', '2019-04-05 21:58:19');
INSERT INTO `coupon_template` VALUES (3, '优惠劵C', '优惠劵C', 1, NULL, 1, 1, 10, 100, 10, '10', 2, NULL, NULL, 2, 4, 1, NULL, 1000, NULL, 0, '2019-04-05 11:44:57', '2019-04-05 21:58:22');
INSERT INTO `coupon_template` VALUES (6, '优惠劵C', '优惠劵C', 1, NULL, 1, 1, 1000, 1000, 20, '29,32,36', 2, NULL, NULL, 1, 10, 1, NULL, 500, NULL, 0, '2019-04-05 11:54:01', '2019-05-07 19:20:19');
INSERT INTO `coupon_template` VALUES (7, '优惠劵D', '优惠劵D', 1, NULL, 1, 2, 10, 2000, 30, '784,637', 1, '2019-04-05 00:00:00', '2019-04-26 00:00:00', NULL, NULL, 1, NULL, 1000, NULL, 0, '2019-04-05 12:05:36', '2019-05-07 19:29:13');
COMMIT;

-- ----------------------------
-- Table structure for product_recommend
-- ----------------------------
DROP TABLE IF EXISTS `product_recommend`;
CREATE TABLE `product_recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `product_spu_id` int(11) NOT NULL COMMENT '商品 Spu 编号',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `memo` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='product_recommend';

-- ----------------------------
-- Records of product_recommend
-- ----------------------------
BEGIN;
INSERT INTO `product_recommend` VALUES (1, 1, 29, 1, 2, '牛逼', '2019-04-01 13:50:13', '2019-05-10 20:51:47', b'0');
INSERT INTO `product_recommend` VALUES (2, 2, 28, 2, 1, '你猜~', '2019-04-01 13:59:06', '2019-04-01 14:44:19', b'0');
INSERT INTO `product_recommend` VALUES (3, 1, 33, 10, 1, '牛逼', '2019-04-22 13:09:19', '2019-04-22 13:09:18', b'0');
INSERT INTO `product_recommend` VALUES (4, 2, 34, 30, 1, '木有', '2019-04-22 13:13:00', '2019-04-22 13:13:00', b'0');
INSERT INTO `product_recommend` VALUES (5, 1, 35, -1, 1, '13', '2019-04-22 13:16:00', '2019-04-22 13:16:00', b'0');
INSERT INTO `product_recommend` VALUES (6, 2, 36, 2000, 1, '哈哈哈哈', '2019-04-22 13:16:15', '2019-04-22 13:16:15', b'0');
INSERT INTO `product_recommend` VALUES (7, 1, 41, 10, 1, '厮大就是牛逼', '2019-05-07 16:48:19', '2019-05-07 16:48:18', b'0');
COMMIT;

-- ----------------------------
-- Table structure for promotion_activity
-- ----------------------------
DROP TABLE IF EXISTS `promotion_activity`;
CREATE TABLE `promotion_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '活动标题',
  `activity_type` tinyint(4) NOT NULL COMMENT '活动类型\n     *\n     * 参见 {@link cn.iocoder.mall.promotion.api.enums.PromotionActivityTypeEnum} 枚举',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '活动状态\n     *\n     * 参见 {@link cn.iocoder.mall.promotion.api.enums.PromotionActivityStatusEnum} 枚举',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `invalid_time` datetime DEFAULT NULL COMMENT '失效时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `time_limited_discount` varchar(2000) DEFAULT NULL COMMENT '限制折扣字符串，使用 JSON 序列化成字符串存储',
  `full_privilege` varchar(2000) DEFAULT NULL COMMENT '限制折扣字符串，使用 JSON 序列化成字符串存储',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='promotion_activity';

-- ----------------------------
-- Records of promotion_activity
-- ----------------------------
BEGIN;
INSERT INTO `promotion_activity` VALUES (1, '老板跑路了', 1, 20, '2019-04-16 15:07:49', '2019-07-25 15:07:49', NULL, NULL, '{\"items\":[{\"preferentialType\":2,\"preferentialValue\":40,\"spuId\":32}],\"quota\":0}', NULL, '2019-04-16 15:07:49', '2019-04-16 15:07:49');
INSERT INTO `promotion_activity` VALUES (2, '老四赶海', 2, 20, '2019-04-16 16:03:54', '2019-07-25 16:03:54', NULL, NULL, NULL, '{\n    \"cycled\": false, \n    \"privileges\": [\n        {\n            \"meetType\": 1, \n            \"meetValue\": 20, \n            \"preferentialType\": 2, \n            \"preferentialValue\": 80\n        }\n    ], \n    \"rangeType\": 20,\n    \"rangeValues\": [29],\n}', '2019-04-16 16:03:54', '2019-04-17 20:28:36');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
INSERT INTO `undo_log` VALUES (3, 2011282479, '192.168.43.240:8091:2011282477', 0x7B7D, 1, '2019-05-12 01:48:04', '2019-05-12 01:48:04', NULL);
INSERT INTO `undo_log` VALUES (5, 2011282482, '192.168.43.240:8091:2011282480', 0x7B7D, 1, '2019-05-12 01:50:02', '2019-05-12 01:50:02', NULL);
INSERT INTO `undo_log` VALUES (7, 2011282488, '192.168.43.240:8091:2011282485', 0x7B7D, 1, '2019-05-12 01:52:19', '2019-05-12 01:52:19', NULL);
INSERT INTO `undo_log` VALUES (8, 2011282487, '192.168.43.240:8091:2011282485', 0x7B7D, 1, '2019-05-12 01:52:19', '2019-05-12 01:52:19', NULL);
INSERT INTO `undo_log` VALUES (10, 2011282496, '192.168.43.240:8091:2011282493', 0x7B7D, 1, '2019-05-12 01:56:36', '2019-05-12 01:56:36', NULL);
INSERT INTO `undo_log` VALUES (11, 2011282495, '192.168.43.240:8091:2011282493', 0x7B7D, 1, '2019-05-12 01:56:36', '2019-05-12 01:56:36', NULL);
INSERT INTO `undo_log` VALUES (19, 2011404236, '192.168.3.26:8091:2011404235', 0x7B7D, 1, '2019-05-13 00:23:19', '2019-05-13 00:23:19', NULL);
INSERT INTO `undo_log` VALUES (21, 2011404238, '192.168.3.26:8091:2011404237', 0x7B7D, 1, '2019-05-13 00:29:33', '2019-05-13 00:29:33', NULL);
INSERT INTO `undo_log` VALUES (24, 2011404242, '192.168.3.26:8091:2011404241', 0x7B7D, 1, '2019-05-13 00:31:48', '2019-05-13 00:31:48', NULL);
INSERT INTO `undo_log` VALUES (26, 2011404244, '192.168.3.26:8091:2011404243', 0x7B7D, 1, '2019-05-13 00:34:00', '2019-05-13 00:34:00', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
