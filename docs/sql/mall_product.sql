/*
 Navicat Premium Data Transfer

 Source Server         : onemall
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.88.14:3306
 Source Schema         : mall_product

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/03/2020 07:37:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_attr
-- ----------------------------
DROP TABLE IF EXISTS `product_attr`;
CREATE TABLE `product_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='product_attr';

-- ----------------------------
-- Records of product_attr
-- ----------------------------
BEGIN;
INSERT INTO `product_attr` VALUES (1, '颜色', 1, '2019-03-05 15:40:56', '2019-03-05 15:40:58', b'0');
INSERT INTO `product_attr` VALUES (2, '尺寸', 1, '2019-03-05 17:23:07', '2019-03-05 17:23:07', b'0');
INSERT INTO `product_attr` VALUES (3, '测试规格', 1, '2019-03-06 10:29:58', '2019-03-07 00:29:58', b'0');
INSERT INTO `product_attr` VALUES (4, '厮大牛逼规格', 1, '2019-03-06 10:31:01', '2019-03-07 00:33:53', b'0');
INSERT INTO `product_attr` VALUES (5, '狼哥规格', 2, '2019-03-06 10:31:47', '2019-03-07 00:34:27', b'0');
INSERT INTO `product_attr` VALUES (6, '老徐规格', 1, '2019-03-06 10:48:42', '2019-03-07 00:48:41', b'0');
INSERT INTO `product_attr` VALUES (7, '徐妈规格', 1, '2019-03-06 10:53:37', '2019-03-07 00:53:37', b'0');
INSERT INTO `product_attr` VALUES (8, '粉色', 1, '2019-05-02 22:05:21', '2019-05-02 22:05:21', b'0');
COMMIT;

-- ----------------------------
-- Table structure for product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_value`;
CREATE TABLE `product_attr_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格编号',
  `attr_id` int(11) DEFAULT NULL COMMENT '规格编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COMMENT='product_attr';

-- ----------------------------
-- Records of product_attr_value
-- ----------------------------
BEGIN;
INSERT INTO `product_attr_value` VALUES (1, 1, '蓝色', 1, '2019-03-05 15:41:09', '2019-03-05 15:41:09', b'0');
INSERT INTO `product_attr_value` VALUES (2, 1, '绿色', 1, '2019-03-05 15:41:14', '2019-03-05 15:42:58', b'0');
INSERT INTO `product_attr_value` VALUES (3, 2, 'XL', 1, '2019-03-05 17:23:19', '2019-03-05 17:23:19', b'0');
INSERT INTO `product_attr_value` VALUES (4, 2, 'L', 1, '2019-03-05 17:23:25', '2019-03-05 17:23:25', b'0');
INSERT INTO `product_attr_value` VALUES (5, 1, '红色', 1, '2019-03-05 20:59:48', '2019-03-05 20:59:48', b'0');
INSERT INTO `product_attr_value` VALUES (6, 4, '狼哥规格', 1, '2019-03-06 19:55:38', '2019-03-07 09:55:38', b'0');
INSERT INTO `product_attr_value` VALUES (7, 4, '狼哥规格1', 1, '2019-03-06 19:56:26', '2019-03-07 09:56:25', b'0');
INSERT INTO `product_attr_value` VALUES (8, 4, '狼哥规格2', 1, '2019-03-06 19:57:19', '2019-03-07 09:57:19', b'0');
INSERT INTO `product_attr_value` VALUES (9, 4, '狼哥规格345', 1, '2019-03-06 20:00:02', '2019-03-07 10:01:18', b'0');
INSERT INTO `product_attr_value` VALUES (10, 9, '狼哥规格34', 2, '2019-03-06 20:00:59', '2019-03-07 10:02:43', b'0');
INSERT INTO `product_attr_value` VALUES (11, 1, '粉色', 1, '2019-05-02 22:08:22', '2019-05-02 22:08:21', b'0');
INSERT INTO `product_attr_value` VALUES (12, 1, '黄色', 1, '2019-05-02 22:10:30', '2019-05-02 22:10:30', b'0');
INSERT INTO `product_attr_value` VALUES (13, 1, '橙色', 1, '2019-05-02 22:12:20', '2019-05-02 22:12:19', b'0');
INSERT INTO `product_attr_value` VALUES (14, 1, '青色', 1, '2019-05-02 22:13:12', '2019-05-02 22:13:11', b'0');
INSERT INTO `product_attr_value` VALUES (15, 1, '海蓝色', 1, '2019-05-02 22:15:47', '2019-05-02 22:15:47', b'0');
INSERT INTO `product_attr_value` VALUES (16, 1, '浅蓝色', 1, '2019-05-02 22:16:25', '2019-05-02 22:16:25', b'0');
INSERT INTO `product_attr_value` VALUES (17, 1, '天蓝色', 1, '2019-05-02 22:17:36', '2019-05-02 22:17:35', b'0');
INSERT INTO `product_attr_value` VALUES (18, 9, '不破不灭', 1, '2019-05-02 22:18:24', '2019-05-02 22:30:29', b'0');
INSERT INTO `product_attr_value` VALUES (19, 9, '各种浪', 1, '2019-05-02 22:19:17', '2019-05-02 22:30:27', b'0');
INSERT INTO `product_attr_value` VALUES (20, 9, '测试01', 1, '2019-05-02 22:21:21', '2019-05-02 22:30:27', b'0');
INSERT INTO `product_attr_value` VALUES (21, 9, '测试02', 1, '2019-05-02 22:23:17', '2019-05-02 22:30:25', b'0');
INSERT INTO `product_attr_value` VALUES (22, 9, '测试03', 1, '2019-05-02 22:24:13', '2019-05-02 22:30:24', b'0');
INSERT INTO `product_attr_value` VALUES (23, 9, 'biubiubiu', 1, '2019-05-02 22:24:42', '2019-05-02 22:30:23', b'0');
INSERT INTO `product_attr_value` VALUES (24, 9, '特特', 1, '2019-05-02 22:35:35', '2019-05-02 22:35:42', b'0');
INSERT INTO `product_attr_value` VALUES (25, 9, '01', 1, '2019-05-02 22:37:24', '2019-05-02 22:37:56', b'0');
INSERT INTO `product_attr_value` VALUES (26, 9, 'liubi', 1, '2019-05-02 22:38:02', '2019-05-02 22:38:14', b'0');
INSERT INTO `product_attr_value` VALUES (27, 9, 'zei6', 1, '2019-05-02 22:38:33', '2019-05-02 22:39:52', b'0');
INSERT INTO `product_attr_value` VALUES (28, 3, 'niubi', 1, '2019-05-02 22:52:03', '2019-05-02 22:52:03', b'0');
INSERT INTO `product_attr_value` VALUES (29, 3, 'wocao', 1, '2019-05-02 22:52:27', '2019-05-02 22:52:27', b'0');
INSERT INTO `product_attr_value` VALUES (30, 3, '666', 1, '2019-05-02 22:54:44', '2019-05-02 22:54:43', b'0');
INSERT INTO `product_attr_value` VALUES (31, 3, 'gouwazi', 1, '2019-05-02 22:58:52', '2019-05-02 22:58:51', b'0');
INSERT INTO `product_attr_value` VALUES (32, 3, 'abc', 1, '2019-05-02 22:59:46', '2019-05-02 22:59:46', b'0');
INSERT INTO `product_attr_value` VALUES (33, 3, 'qilin', 1, '2019-05-02 23:00:06', '2019-05-02 23:00:05', b'0');
INSERT INTO `product_attr_value` VALUES (34, 3, 'xigua', 1, '2019-05-02 23:00:43', '2019-05-02 23:00:43', b'0');
INSERT INTO `product_attr_value` VALUES (35, 3, 'zhanxiaolang', 1, '2019-05-02 23:01:07', '2019-05-02 23:01:07', b'0');
INSERT INTO `product_attr_value` VALUES (36, 1, '333', 1, '2019-05-02 23:33:55', '2019-05-02 23:33:55', b'0');
INSERT INTO `product_attr_value` VALUES (37, 6, '111', 1, '2019-05-31 18:55:56', '2019-05-31 18:55:55', b'0');
INSERT INTO `product_attr_value` VALUES (38, 3, '22', 1, '2019-05-31 18:55:59', '2019-05-31 18:55:58', b'0');
INSERT INTO `product_attr_value` VALUES (39, 2, '112', 1, '2019-05-31 18:56:06', '2019-05-31 18:56:05', b'0');
INSERT INTO `product_attr_value` VALUES (40, 7, 'kk', 1, '2019-05-31 19:53:01', '2019-05-31 19:53:00', b'0');
INSERT INTO `product_attr_value` VALUES (41, 7, 'nm', 1, '2019-05-31 19:53:05', '2019-05-31 19:53:04', b'0');
INSERT INTO `product_attr_value` VALUES (42, 6, 'sss', 1, '2019-06-02 12:22:44', '2019-06-02 12:22:43', b'0');
INSERT INTO `product_attr_value` VALUES (43, 6, 'sds', 1, '2019-06-02 12:22:46', '2019-06-02 12:22:45', b'0');
INSERT INTO `product_attr_value` VALUES (44, 2, '3333', 1, '2019-07-02 11:19:32', '2019-07-02 11:19:32', b'0');
INSERT INTO `product_attr_value` VALUES (45, 2, '123', 1, '2019-07-02 11:33:15', '2019-07-02 11:33:15', b'0');
INSERT INTO `product_attr_value` VALUES (46, 2, 'a', 1, '2019-07-05 15:40:51', '2019-07-05 15:40:51', b'0');
INSERT INTO `product_attr_value` VALUES (47, 2, '12312', 1, '2019-08-12 18:48:48', '2019-08-12 18:48:47', b'0');
INSERT INTO `product_attr_value` VALUES (48, 3, '222', 1, '2019-08-12 18:48:54', '2019-08-12 18:48:53', b'0');
INSERT INTO `product_attr_value` VALUES (49, 1, '金色', 1, '2019-09-05 10:38:55', '2019-09-05 10:38:55', b'0');
INSERT INTO `product_attr_value` VALUES (50, 2, '一克拉', 1, '2019-09-05 10:39:05', '2019-09-05 10:39:04', b'0');
COMMIT;

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '品牌编号（主键）',
  `name` varchar(50) DEFAULT NULL COMMENT '品牌名称',
  `description` varchar(255) DEFAULT NULL COMMENT '品牌描述',
  `pic_url` varchar(1024) DEFAULT NULL COMMENT '品牌名图片',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1开启 2禁用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除 0正常1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_brand
-- ----------------------------
BEGIN;
INSERT INTO `product_brand` VALUES (1, '安踏更新', '安踏拖鞋更新', 'http://www.iocoder.cn', 1, '2019-05-30 13:43:44', '2019-05-31 13:42:29', b'0');
INSERT INTO `product_brand` VALUES (2, '特步', '特步描述', '23232', 1, '2019-05-31 13:42:22', '2019-05-31 13:42:22', b'0');
COMMIT;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `pid` int(11) DEFAULT NULL COMMENT '父分类编号',
  `name` varchar(16) DEFAULT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `sort` smallint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'1' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=797 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_category
-- ----------------------------
BEGIN;
INSERT INTO `product_category` VALUES (90, 0, '电视影音', NULL, NULL, 0, '2019-02-21 18:35:00', '2019-03-03 20:42:01', 1, b'0');
INSERT INTO `product_category` VALUES (579, 0, '手机电脑', NULL, NULL, 0, '2019-02-21 18:33:26', '2019-03-03 20:42:03', 1, b'0');
INSERT INTO `product_category` VALUES (637, 90, '32-40英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_904483f8aa3bbaaa9f53e4aae2382275.jpeg', 1, '2019-02-21 18:35:20', '2019-03-03 20:42:05', 1, b'0');
INSERT INTO `product_category` VALUES (638, 90, '43英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_0f26088b420afbe2c63df02246b94a34.jpeg', 2, '2019-02-21 18:35:41', '2019-03-03 20:42:07', 1, b'0');
INSERT INTO `product_category` VALUES (639, 90, '49-50英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_c2c741283b2ce1c4bc8b0abe9ea4f65e.jpeg', 3, '2019-02-21 18:36:01', '2019-03-03 20:42:10', 1, b'0');
INSERT INTO `product_category` VALUES (783, 579, '小米系列', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_af3ae1e1444bc54af8b2251dd14aaa6b.jpeg', 1, '2019-02-21 18:33:56', '2019-03-03 20:42:12', 1, b'0');
INSERT INTO `product_category` VALUES (784, 579, '红米系列', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_72605808146ee82c9961f9e3be6d8696.jpeg', 2, '2019-02-21 18:34:29', '2019-03-03 20:42:14', 1, b'0');
INSERT INTO `product_category` VALUES (785, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:28:22', '2019-03-04 16:46:05', 2, b'1');
INSERT INTO `product_category` VALUES (786, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:31:46', '2019-03-15 22:08:04', 2, b'1');
INSERT INTO `product_category` VALUES (787, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:31:57', '2019-03-15 22:08:08', 2, b'1');
INSERT INTO `product_category` VALUES (788, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:04', '2019-03-25 15:38:38', 2, b'1');
INSERT INTO `product_category` VALUES (789, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:39', '2019-03-29 23:40:41', 2, b'1');
INSERT INTO `product_category` VALUES (790, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:54', '2019-03-29 23:40:46', 2, b'1');
INSERT INTO `product_category` VALUES (791, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:34:13', '2019-03-29 23:40:49', 2, b'1');
INSERT INTO `product_category` VALUES (792, 0, '大保健哟', '哈哈哈哈哈', NULL, 100, '2019-03-15 21:52:23', '2019-03-29 23:40:52', 2, b'1');
INSERT INTO `product_category` VALUES (793, 0, '图书', '书是个好东西，可惜看的少。', NULL, 3, '2019-05-05 16:12:39', '2019-05-05 16:12:39', 1, b'0');
INSERT INTO `product_category` VALUES (794, 793, 'Java', '半年一更，妥妥的', 'http://static.shop.iocoder.cn/5fd5709e-988d-4efd-b5d8-54a599ca538f', 1, '2019-05-05 16:35:03', '2019-05-06 23:05:53', 1, b'0');
INSERT INTO `product_category` VALUES (795, 793, '测试分类', '测试删除', 'http://static.shop.iocoder.cn/14a2169d-a2a2-4fc6-9f71-93dba32596c9', 3, '2019-05-05 16:45:51', '2019-05-05 16:47:14', 2, b'1');
INSERT INTO `product_category` VALUES (796, 579, '订单系统设计', 'sss', 'http://static.shop.iocoder.cn/3fd98e94-ad50-415a-8f17-8bd8a998e508', 5, '2019-06-26 20:28:35', '2019-06-26 20:28:34', 1, b'0');
COMMIT;

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sku 编号',
  `spu_id` int(11) NOT NULL DEFAULT '-1' COMMENT '商品编号',
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT '状态\n     *\n     * 1-正常\n     * 2-禁用',
  `pic_url` varchar(50) DEFAULT '' COMMENT '图片地址',
  `attrs` varchar(50) NOT NULL DEFAULT '' COMMENT '规格值({@link ProductAttrDO})数组\n     *\n     * 数组，以逗号分隔',
  `price` int(11) NOT NULL DEFAULT '-1' COMMENT '价格，单位：分',
  `quantity` int(11) NOT NULL DEFAULT '-1' COMMENT '库存数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COMMENT='product_sku';

-- ----------------------------
-- Records of product_sku
-- ----------------------------
BEGIN;
INSERT INTO `product_sku` VALUES (3, 8, 1, NULL, '1', 1, 100, '2019-03-05 02:48:52', '2019-03-05 16:48:52', b'0');
INSERT INTO `product_sku` VALUES (4, 8, 1, NULL, '2', 1, 100, '2019-03-05 02:48:52', '2019-03-05 16:48:52', b'0');
INSERT INTO `product_sku` VALUES (5, 9, 1, NULL, '1', 1, 100, '2019-03-05 02:51:22', '2019-03-05 16:51:21', b'0');
INSERT INTO `product_sku` VALUES (6, 9, 1, NULL, '2', 1, 100, '2019-03-05 02:51:22', '2019-03-05 16:51:21', b'0');
INSERT INTO `product_sku` VALUES (7, 10, 1, NULL, '1', 1, 100, '2019-03-05 02:52:25', '2019-03-05 16:52:25', b'0');
INSERT INTO `product_sku` VALUES (8, 10, 1, NULL, '2', 1, 100, '2019-03-05 02:52:25', '2019-03-05 16:52:25', b'0');
INSERT INTO `product_sku` VALUES (9, 13, 1, NULL, '1', 1, 100, '2019-03-05 03:22:24', '2019-03-05 17:22:26', b'0');
INSERT INTO `product_sku` VALUES (10, 13, 1, NULL, '2', 1, 100, '2019-03-05 03:22:24', '2019-03-05 17:22:26', b'0');
INSERT INTO `product_sku` VALUES (11, 14, 1, NULL, '1', 1, 100, '2019-03-05 03:22:33', '2019-03-05 17:22:40', b'0');
INSERT INTO `product_sku` VALUES (12, 14, 1, NULL, '2', 1, 100, '2019-03-05 03:22:33', '2019-03-05 17:22:40', b'0');
INSERT INTO `product_sku` VALUES (13, 20, 1, NULL, '1,3', 1, 100, '2019-03-05 03:25:39', '2019-03-05 17:25:39', b'0');
INSERT INTO `product_sku` VALUES (14, 20, 1, NULL, '1,4', 1, 100, '2019-03-05 03:25:39', '2019-03-05 17:25:39', b'0');
INSERT INTO `product_sku` VALUES (15, 21, 1, NULL, '1,3', 1, 100, '2019-03-05 03:34:07', '2019-03-05 17:34:06', b'0');
INSERT INTO `product_sku` VALUES (16, 21, 1, NULL, '1,4', 1, 100, '2019-03-05 03:34:07', '2019-03-05 17:34:06', b'0');
INSERT INTO `product_sku` VALUES (17, 22, 1, NULL, '1,3', 1, 100, '2019-03-05 03:34:50', '2019-03-05 17:34:49', b'0');
INSERT INTO `product_sku` VALUES (18, 22, 1, NULL, '1,4', 1, 100, '2019-03-05 03:34:50', '2019-03-05 17:34:49', b'0');
INSERT INTO `product_sku` VALUES (19, 23, 1, NULL, '1,3', 1, 1, '2019-03-05 03:37:03', '2019-03-26 23:51:23', b'0');
INSERT INTO `product_sku` VALUES (20, 23, 1, NULL, '1,4', 2, 10, '2019-03-05 03:37:03', '2019-03-26 23:51:25', b'0');
INSERT INTO `product_sku` VALUES (23, 25, 1, NULL, '1,3', 1, 100, '2019-03-05 03:43:30', '2019-03-05 17:43:30', b'0');
INSERT INTO `product_sku` VALUES (24, 25, 1, NULL, '1,4', 1, 100, '2019-03-05 03:43:30', '2019-03-05 17:43:30', b'0');
INSERT INTO `product_sku` VALUES (25, 26, 1, NULL, '1', 1, 100, '2019-03-05 07:00:38', '2019-03-05 21:00:38', b'0');
INSERT INTO `product_sku` VALUES (26, 27, 1, NULL, '1', 1, 100, '2019-03-05 07:01:33', '2019-03-05 21:10:52', b'1');
INSERT INTO `product_sku` VALUES (27, 27, 1, NULL, '2', 21, 200, '2019-03-05 07:01:33', '2019-03-05 21:10:52', b'0');
INSERT INTO `product_sku` VALUES (30, 27, 1, NULL, '3', 3, 300, '2019-03-05 07:10:52', '2019-03-05 21:10:52', b'0');
INSERT INTO `product_sku` VALUES (31, 28, 1, NULL, '1', 100, 100, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_sku` VALUES (32, 28, 1, NULL, '2', 200, 200, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_sku` VALUES (33, 29, 1, NULL, '1', 10000000, 20, '2019-03-18 17:50:01', '2019-05-05 23:48:10', b'0');
INSERT INTO `product_sku` VALUES (34, 32, 1, NULL, '1,3', 125000000, 20, '2019-04-16 13:45:13', '2019-05-09 10:43:55', b'0');
INSERT INTO `product_sku` VALUES (35, 32, 1, NULL, '1,4', 1410065408, 30, '2019-04-16 13:45:13', '2019-05-09 10:43:55', b'0');
INSERT INTO `product_sku` VALUES (36, 33, 1, NULL, '3', 10000, 100, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_sku` VALUES (37, 33, 1, NULL, '4', 20000, 20, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_sku` VALUES (38, 34, 1, NULL, '1', 2000, 100, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_sku` VALUES (39, 34, 1, NULL, '2', 3000, 200, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_sku` VALUES (40, 35, 1, NULL, '3', 500000, 11, '2019-04-22 13:14:33', '2019-08-12 23:38:32', b'0');
INSERT INTO `product_sku` VALUES (41, 35, 1, NULL, '4', 200000, 22, '2019-04-22 13:14:33', '2019-08-12 23:38:33', b'0');
INSERT INTO `product_sku` VALUES (42, 36, 1, NULL, '3', 5100, 11, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_sku` VALUES (43, 36, 1, NULL, '4', 2100, 22, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_sku` VALUES (44, 37, 1, NULL, '3,2', 2000, 40, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_sku` VALUES (45, 37, 1, NULL, '4,2', 3000, 50, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_sku` VALUES (46, 38, 1, NULL, '2', 1000, 20, '2019-04-25 15:44:56', '2019-04-25 15:44:56', b'0');
INSERT INTO `product_sku` VALUES (47, 39, 1, NULL, '1,3', 1000, 20, '2019-05-02 02:20:50', '2019-05-02 02:20:49', b'0');
INSERT INTO `product_sku` VALUES (48, 40, 1, NULL, '1', 1000, 20, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_sku` VALUES (49, 40, 1, NULL, '2', 3000, 40, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_sku` VALUES (50, 41, 1, NULL, '1', 1000, 20, '2019-05-02 17:44:05', '2019-05-02 17:44:05', b'0');
INSERT INTO `product_sku` VALUES (51, 29, 1, NULL, '2', 30000000, 40, '2019-05-02 18:19:15', '2019-05-05 23:48:10', b'0');
INSERT INTO `product_sku` VALUES (52, 42, 1, NULL, '1', 1000, 20, '2019-05-02 22:17:01', '2019-05-02 22:54:16', b'1');
INSERT INTO `product_sku` VALUES (53, 42, 1, NULL, '18', 3000, 40, '2019-05-02 22:18:57', '2019-05-02 22:23:26', b'1');
INSERT INTO `product_sku` VALUES (54, 42, 1, NULL, '2', 3000, 40, '2019-05-02 22:25:19', '2019-05-02 22:54:16', b'1');
INSERT INTO `product_sku` VALUES (55, 42, 1, NULL, '28', 1000, 20, '2019-05-02 22:54:17', '2019-05-02 22:54:16', b'0');
INSERT INTO `product_sku` VALUES (56, 42, 1, NULL, '35', 3000, 40, '2019-05-02 23:01:17', '2019-05-02 23:01:17', b'0');
INSERT INTO `product_sku` VALUES (57, 43, 1, NULL, '1,3', 111100, 1111, '2019-07-02 11:36:25', '2019-07-02 11:36:25', b'0');
INSERT INTO `product_sku` VALUES (58, 43, 1, NULL, '2,3', 111100, 1111, '2019-07-02 11:36:25', '2019-07-02 11:36:25', b'0');
INSERT INTO `product_sku` VALUES (59, 44, 1, NULL, '1,3', 111100, 1111, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_sku` VALUES (60, 44, 1, NULL, '2,3', 111100, 1111, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_sku` VALUES (61, 45, 1, NULL, '1,3,6', 11100, 111, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_sku` VALUES (62, 45, 1, NULL, '2,3,6', 11100, 111, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_sku` VALUES (63, 46, 1, NULL, '46', 1100, 1111, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_sku` VALUES (64, 46, 1, NULL, '4', 1100, 111, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_sku` VALUES (65, 47, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:29:31', '2019-07-09 20:29:30', b'0');
INSERT INTO `product_sku` VALUES (66, 48, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:29:40', '2019-07-09 20:29:40', b'0');
INSERT INTO `product_sku` VALUES (67, 49, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:32:27', '2019-07-09 20:32:27', b'0');
INSERT INTO `product_sku` VALUES (68, 50, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:48:58', '2019-08-12 18:48:58', b'0');
INSERT INTO `product_sku` VALUES (69, 51, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:49:03', '2019-08-12 18:49:02', b'0');
INSERT INTO `product_sku` VALUES (70, 52, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:49:19', '2019-08-12 18:49:19', b'0');
INSERT INTO `product_sku` VALUES (71, 53, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:50:48', '2019-08-12 18:50:48', b'0');
INSERT INTO `product_sku` VALUES (72, 54, 1, NULL, '1', 10000, 100, '2019-08-12 23:46:12', '2019-08-12 23:46:11', b'0');
INSERT INTO `product_sku` VALUES (73, 55, 1, NULL, '49,50', 10000, 100, '2019-09-05 10:41:24', '2019-09-05 10:41:24', b'0');
COMMIT;

-- ----------------------------
-- Table structure for product_spu
-- ----------------------------
DROP TABLE IF EXISTS `product_spu`;
CREATE TABLE `product_spu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'SPU 编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'SPU 名字',
  `sell_point` varchar(50) NOT NULL DEFAULT '' COMMENT '卖点',
  `description` text NOT NULL COMMENT '描述',
  `cid` int(11) NOT NULL DEFAULT '-1' COMMENT '分类编号',
  `pic_urls` varchar(1024) NOT NULL DEFAULT '' COMMENT '商品主图地址\n     *\n     * 数组，以逗号分隔\n     *\n     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张',
  `visible` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否上架商品（是否可见）。\n     *\n     * true 为已上架\n     * false 为已下架',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `quantity` int(11) DEFAULT NULL COMMENT '库存数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COMMENT='product_spu';

-- ----------------------------
-- Records of product_spu
-- ----------------------------
BEGIN;
INSERT INTO `product_spu` VALUES (7, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/5d4374f27d078462!400x400_big.jpg', 1, 0, 7, 10, '2019-03-05 02:45:44', '2019-04-24 11:47:20', b'1');
INSERT INTO `product_spu` VALUES (8, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/52c349020e992e04!400x400_big.jpg', 1, 0, 8, 20, '2019-03-05 02:48:52', '2019-04-24 11:47:22', b'1');
INSERT INTO `product_spu` VALUES (9, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/4ba4264eb3416283!400x400_big.jpg', 1, 0, 9, 30, '2019-03-05 02:51:22', '2019-04-24 11:47:23', b'1');
INSERT INTO `product_spu` VALUES (10, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b258b691c543172d!400x400_big.jpg', 1, 0, 10, 40, '2019-03-05 02:52:25', '2019-04-24 11:47:25', b'1');
INSERT INTO `product_spu` VALUES (11, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/976d2210182f6816!400x400_big.jpg', 1, 0, 11, 50, '2019-03-05 03:21:46', '2019-04-24 11:47:47', b'1');
INSERT INTO `product_spu` VALUES (12, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/02c433787860a74a!400x400_big.jpg', 1, 0, 12, NULL, '2019-03-05 03:22:02', '2019-04-24 11:47:48', b'1');
INSERT INTO `product_spu` VALUES (13, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/7ee3855f60009a09!400x400_big.jpg', 1, 0, 13, NULL, '2019-03-05 03:22:24', '2019-04-24 11:47:49', b'1');
INSERT INTO `product_spu` VALUES (14, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 14, NULL, '2019-03-05 03:22:33', '2019-04-24 11:47:50', b'1');
INSERT INTO `product_spu` VALUES (15, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 15, NULL, '2019-03-05 03:23:40', '2019-04-24 11:47:51', b'1');
INSERT INTO `product_spu` VALUES (16, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 16, NULL, '2019-03-05 03:23:50', '2019-04-24 11:47:50', b'1');
INSERT INTO `product_spu` VALUES (20, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 17, NULL, '2019-03-05 03:25:39', '2019-04-24 11:47:55', b'1');
INSERT INTO `product_spu` VALUES (21, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 18, NULL, '2019-03-05 03:34:07', '2019-04-24 11:47:57', b'1');
INSERT INTO `product_spu` VALUES (22, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 19, NULL, '2019-03-05 03:34:50', '2019-04-24 11:47:57', b'1');
INSERT INTO `product_spu` VALUES (23, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 0, 20, NULL, '2019-03-05 03:37:03', '2019-04-24 11:47:58', b'1');
INSERT INTO `product_spu` VALUES (25, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 21, NULL, '2019-03-05 03:43:30', '2019-04-24 11:47:59', b'1');
INSERT INTO `product_spu` VALUES (26, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 0, 22, NULL, '2019-03-05 07:00:38', '2019-04-24 11:48:00', b'1');
INSERT INTO `product_spu` VALUES (27, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 100, 23, NULL, '2019-03-05 07:01:33', '2019-04-24 11:48:00', b'1');
INSERT INTO `product_spu` VALUES (28, '测试商品', '又大又长', '<p>厮大牛逼</p>', 638, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1,http://static.shop.iocoder.cn/49ace70a-bebc-4d1c-b4e7-2115cedbf2a8', 1, 1, 100, 300, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_spu` VALUES (29, 'kafka 实战第一版', '最强', '<p>我是一个骚气的描述</p>', 637, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1,http://static.shop.iocoder.cn/ab34d8e5-3b28-4f74-bafa-48aa66b4cf58', 1, 2, 10000000, 60, '2019-03-18 17:50:00', '2019-05-06 15:31:21', b'0');
INSERT INTO `product_spu` VALUES (32, '农夫山泉', '有点甜', '<p>我就是一个水</p>', 637, 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 1, 125000000, 50, '2019-04-16 13:45:13', '2019-09-06 10:58:36', b'0');
INSERT INTO `product_spu` VALUES (33, 'Kafka 书籍汇总', '分布式发布订阅消息系统', 'kafka是一种高吞吐量的分布式发布订阅消息系统，她有如下特性：\n\n通过O(1)的磁盘数据结构提供消息的持久化，这种结构对于即使数以TB的消息存储也能够保持长时间的稳定性能。\n\n高吞吐量：即使是非常普通的硬件kafka也可以支持每秒数十万的消息。\n\n支持通过kafka服务器和消费机集群来分区消息。\n\n支持Hadoop并行数据加载。\n\n卡夫卡的目的是提供一个发布订阅解决方案，它可以处理消费者规模的网站中的所有动作流数据。 这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群机来提供实时的消费。', 783, 'http://static.iocoder.cn/kafka.png', 1, 0, 10000, 120, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_spu` VALUES (34, 'MySQL', '数据库服务器', 'MySQL是一个开放源码的小型关联式数据库管理系统，开发者为瑞典MySQL AB公司。目前MySQL被广泛地应用在Internet上的中小型网站中。由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，许多中小型网站为了降低网站总体拥有成本而选择了MySQL作为网站数据库。', 579, 'https://static.oschina.net/img/logo/mysql.png', 1, 0, 2000, 300, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_spu` VALUES (35, 'Oracle', '数据库服务器', '<p>Oracle是一个面向Internet计算环境的数据库。它是在数据库领域一直处于领先地位的Oracle（即甲骨文公司）的产品。可以说Oracle 关系数据库系统是目前世界上流行的关系数据库管理系统，系统可移植性好、使用方便、功能强，适用于各类大、中、小、微机环境。它是一种高效率、可靠性好的 适应高吞吐量的数据库解决方案。</p>', 639, 'https://static.oschina.net/img/logo/oracle.gif', 1, 0, 200000, 33, '2019-04-22 13:14:33', '2019-08-12 23:38:32', b'0');
INSERT INTO `product_spu` VALUES (36, 'Java', '编程语言', 'Java，是由Sun Microsystems公司于1995年5月推出的Java程序设计语言和Java平台的总称，最初推出的时候提出 “Write Once, Run Anywhere” 的理想愿景。\n\n    用 Java 实现的 HotJava 浏览器（支持Java applet）显示了 Java 的魅力：跨平台、动态的Web、Internet计算。从此，Java被广泛接受并推动了Web的迅速发展，常用的浏览器现在均支持Java applet。', 639, 'https://static.oschina.net/img/logo/java.png', 1, 0, 2100, 33, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_spu` VALUES (37, '狼哥很骚', '骚气十足', '狼哥，死于 JVM 之手', 637, 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 0, 2000, 90, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_spu` VALUES (38, '天天吃饭', '不吃不行', '美团外卖饿了么外卖', 783, 'https://static.oschina.net/img/logo/mysql.png', 1, 0, 1000, 20, '2019-04-25 15:44:56', '2019-04-25 15:44:56', b'0');
INSERT INTO `product_spu` VALUES (39, 'admin-server', '测试卖点', '30', 637, 'http://static.shop.iocoder.cn/8702680d-9145-490c-9bc1-13ed3337c4d1', 1, 0, 1000, 20, '2019-05-02 02:20:50', '2019-05-02 02:20:49', b'0');
INSERT INTO `product_spu` VALUES (40, '测试多规格商品', '我是多规格', 'nice', 637, 'http://static.shop.iocoder.cn/d434dc76-3766-4d82-bdb7-229d1db4749c', 1, 0, 1000, 60, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_spu` VALUES (41, 'kafka 实战', '很吊', '厮大牛逼', 637, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 0, 1000, 0, '2019-05-02 17:44:05', '2019-05-06 13:27:22', b'0');
INSERT INTO `product_spu` VALUES (42, '我和僵尸有个约会', '有点甜', '我就是描述', 637, 'http://static.shop.iocoder.cn/e3904ca3-d37c-47ae-b53f-3b46c5916e41', 0, 0, 1000, 60, '2019-05-02 22:17:01', '2019-05-06 13:27:05', b'0');
INSERT INTO `product_spu` VALUES (43, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 111100, 2222, '2019-07-02 11:36:25', '2019-07-02 11:36:24', b'0');
INSERT INTO `product_spu` VALUES (44, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 111100, 2222, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_spu` VALUES (45, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 11100, 222, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_spu` VALUES (46, 'aaaa', 'a', '<p>111111111111111111111111</p>', 784, 'http://static.shop.iocoder.cn/c7f1a2c8-4015-4c28-bc64-a92ad72fbe12', 1, 0, 1100, 1222, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_spu` VALUES (47, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/1b4195db-93b9-4f8d-b8bb-3a88df28459d', 1, 0, 21213300, 23123, '2019-07-09 20:29:30', '2019-07-09 20:29:30', b'0');
INSERT INTO `product_spu` VALUES (48, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/1b4195db-93b9-4f8d-b8bb-3a88df28459d', 1, 0, 21213300, 23123, '2019-07-09 20:29:40', '2019-07-09 20:29:40', b'0');
INSERT INTO `product_spu` VALUES (49, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/124519dc-5b11-4bf1-96e3-ac29d18a603b', 1, 0, 21213300, 23123, '2019-07-09 20:32:27', '2019-07-09 20:32:26', b'0');
INSERT INTO `product_spu` VALUES (50, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:48:58', '2019-08-12 18:48:58', b'0');
INSERT INTO `product_spu` VALUES (51, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:49:03', '2019-08-12 18:49:02', b'0');
INSERT INTO `product_spu` VALUES (52, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:49:19', '2019-08-12 18:49:19', b'0');
INSERT INTO `product_spu` VALUES (53, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:50:48', '2019-08-12 18:50:48', b'0');
INSERT INTO `product_spu` VALUES (54, 'Hadoop', '有点小贵', '<p>好看的不得了</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/d5dccd13-1f82-483e-bb87-fc47e14cb19f\"/></div><p></p>', 794, 'http://static.shop.iocoder.cn/4870d103-1c00-42e1-8eb7-177e227d5e03', 1, 0, 10000, 100, '2019-08-12 23:46:12', '2019-08-12 23:46:11', b'0');
INSERT INTO `product_spu` VALUES (55, '金坷垃', '农民伯伯都爱它', '<p>金坷拉</p>', 783, 'http://static.shop.iocoder.cn/8fb41fd6-2ace-4e66-870d-28cff91084ae', 1, 0, 10000, 100, '2019-09-05 10:41:24', '2019-09-05 10:41:24', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
