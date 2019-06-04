/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_promotion

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/06/2019 07:57:50
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
-- Table structure for promotion_activity
-- ----------------------------
DROP TABLE IF EXISTS `promotion_activity`;
CREATE TABLE `promotion_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '活动标题',
  `activity_type` tinyint(4) NOT NULL COMMENT '活动类型\n     *\n     * 参见 {@link cn.iocoder.mall.promotion.api.constant.PromotionActivityTypeEnum} 枚举',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '活动状态\n     *\n     * 参见 {@link cn.iocoder.mall.promotion.api.constant.PromotionActivityStatusEnum} 枚举',
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

SET FOREIGN_KEY_CHECKS = 1;
