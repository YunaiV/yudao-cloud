/*
 Navicat Premium Data Transfer

 Source Server         : mall_mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 180.167.213.26:13306
 Source Schema         : mall_admin

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/06/2019 07:54:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access_log
-- ----------------------------
DROP TABLE IF EXISTS `access_log`;
CREATE TABLE `access_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `trace_id` varchar(64) NOT NULL COMMENT '链路追踪编号',
  `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '用户编号.',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `application_name` varchar(50) DEFAULT NULL COMMENT '应用名',
  `uri` varchar(4096) NOT NULL DEFAULT '' COMMENT '访问地址',
  `query_string` varchar(4096) NOT NULL DEFAULT '' COMMENT '参数',
  `method` varchar(50) NOT NULL DEFAULT '' COMMENT 'http 方法',
  `user_agent` varchar(1024) NOT NULL DEFAULT '' COMMENT 'userAgent',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip',
  `start_time` datetime NOT NULL COMMENT '请求时间',
  `response_time` int(11) NOT NULL COMMENT '响应时长 -- 毫秒级',
  `error_code` int(11) NOT NULL COMMENT '错误码',
  `error_message` varchar(512) DEFAULT NULL COMMENT '错误提示',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28417 DEFAULT CHARSET=utf8mb4 COMMENT='admin_access_log';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(16) NOT NULL COMMENT '登陆账号',
  `nickname` varchar(10) NOT NULL COMMENT '昵称',
  `password` varchar(32) NOT NULL COMMENT '密码\n     *\n     * TODO 芋艿 暂时最简单的 MD5',
  `status` tinyint(11) NOT NULL COMMENT '账号状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='admin_d_o';

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(11) NOT NULL COMMENT '管理员编号(外键：{@link AdminDO}',
  `role_id` int(11) NOT NULL COMMENT '角色编号(外键：{@link RoleDO}',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COMMENT='admin_role_d_o';

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COMMENT='data_dict';

-- ----------------------------
-- Records of data_dict
-- ----------------------------
BEGIN;
INSERT INTO `data_dict` VALUES (1, 'gender', '男', 1, '性别 - 男（嗨）', '1', 0, '2019-03-03 13:03:46', '2019-03-15 16:51:45');
INSERT INTO `data_dict` VALUES (2, 'gender', '女', 2, '性别（女）', '2', 0, '2019-03-03 13:04:00', '2019-03-15 17:19:20');
INSERT INTO `data_dict` VALUES (3, 'gender', '未知', 10, NULL, '3', 1, '2019-03-02 23:09:19', '2019-03-03 13:11:47');
INSERT INTO `data_dict` VALUES (4, 'common_status', '开启', 1, '状态 - 开启', '1', 0, '2019-03-15 17:28:08', '2019-03-15 17:28:07');
INSERT INTO `data_dict` VALUES (5, 'common_status', '禁用', 2, '状态 - 禁用', '2', 0, '2019-03-15 17:28:54', '2019-03-15 17:28:54');
INSERT INTO `data_dict` VALUES (6, 'test', '测试', 1, '你猜', '1', 1, '2019-03-15 17:36:54', '2019-03-15 17:36:59');
INSERT INTO `data_dict` VALUES (7, 'test', '测试', 1, '1', '1', 1, '2019-03-15 17:37:13', '2019-03-15 18:46:21');
INSERT INTO `data_dict` VALUES (8, 'delete_status', '未删除', 0, '正常/未删除', '0', 0, '2019-03-22 21:20:59', '2019-03-22 21:20:59');
INSERT INTO `data_dict` VALUES (9, 'delete_status', '删除', 1, '删除', '1', 0, '2019-03-22 21:23:20', '2019-03-22 21:23:20');
INSERT INTO `data_dict` VALUES (10, 'order_status', '等待付款', 1, '订单状态', '1', 0, '2019-03-24 16:17:55', '2019-03-24 16:20:24');
INSERT INTO `data_dict` VALUES (11, 'order_status', '等待发货', 2, '	等待发货', '2', 0, '2019-03-24 16:18:39', '2019-03-24 16:20:14');
INSERT INTO `data_dict` VALUES (12, 'order_status', '已发货', 3, '已发货', '3', 0, '2019-03-24 16:19:14', '2019-03-24 16:20:07');
INSERT INTO `data_dict` VALUES (13, 'order_status', '已完成', 4, '	已完成', '4', 0, '2019-03-24 16:19:37', '2019-03-24 16:20:03');
INSERT INTO `data_dict` VALUES (14, 'order_status', '已关闭', 5, '已关闭', '5', 0, '2019-03-24 16:19:50', '2019-03-24 16:19:50');
INSERT INTO `data_dict` VALUES (15, 'order_has_return_exchange', '无退换货', 1, '.', '1', 0, '2019-03-24 16:51:27', '2019-03-24 16:51:26');
INSERT INTO `data_dict` VALUES (16, 'order_has_return_exchange', '退货', 2, '.', '2', 0, '2019-03-24 16:51:36', '2019-03-24 16:51:35');
INSERT INTO `data_dict` VALUES (17, 'order_has_return_exchange', '换货', 3, '.', '3', 0, '2019-03-24 16:51:45', '2019-03-24 16:51:44');
INSERT INTO `data_dict` VALUES (18, 'order_has_return_exchange', '退换货', 4, '。', '4', 0, '2019-03-24 16:52:01', '2019-03-24 16:52:00');
INSERT INTO `data_dict` VALUES (19, 'order_cancel_reasons', '无法联系上买家', 1, '.', '1', 0, '2019-03-30 15:19:18', '2019-03-30 15:19:17');
INSERT INTO `data_dict` VALUES (20, 'order_cancel_reasons', '买家误拍或重拍了', 2, ',', '2', 0, '2019-03-30 15:19:31', '2019-03-30 15:19:30');
INSERT INTO `data_dict` VALUES (21, 'order_cancel_reasons', '买家无诚意完成交易', 3, '.', '3', 0, '2019-03-30 15:19:42', '2019-03-30 15:19:41');
INSERT INTO `data_dict` VALUES (22, 'order_cancel_reasons', '已通过银行线下汇款', 4, '.', '4', 0, '2019-03-30 15:19:56', '2019-03-30 15:19:56');
INSERT INTO `data_dict` VALUES (23, 'order_cancel_reasons', '已通过同城见面交易', 5, '.', '5', 0, '2019-03-30 15:20:21', '2019-03-30 15:20:21');
INSERT INTO `data_dict` VALUES (24, 'order_cancel_reasons', '已通过货到付款交易', 6, '.', '6', 0, '2019-03-30 15:20:37', '2019-03-30 15:20:37');
INSERT INTO `data_dict` VALUES (25, 'order_cancel_reasons', '已通过网上银行直接汇款', 7, '.', '7', 0, '2019-03-30 15:20:48', '2019-03-30 15:20:47');
INSERT INTO `data_dict` VALUES (26, 'order_cancel_reasons', '已经缺货无法交易', 8, '.', '8', 0, '2019-03-30 15:21:01', '2019-03-30 15:21:01');
INSERT INTO `data_dict` VALUES (27, 'order_cancel_reasons', '其他', 20, '.', '20', 0, '2019-03-30 17:16:27', '2019-03-30 17:16:27');
INSERT INTO `data_dict` VALUES (28, 'logistics_company', '顺丰快递', 1, '.', '1', 0, '2019-04-05 16:26:22', '2019-04-05 16:26:22');
INSERT INTO `data_dict` VALUES (29, 'logistics_company', '顺风速运', 2, '.', '2', 0, '2019-04-05 16:26:40', '2019-04-05 16:27:48');
INSERT INTO `data_dict` VALUES (30, 'logistics_company', '圆通快递', 3, '.', '3', 0, '2019-04-05 16:26:57', '2019-04-05 16:26:56');
INSERT INTO `data_dict` VALUES (31, 'logistics_company', '申通快递', 4, '.', '4', 0, '2019-04-05 16:27:14', '2019-04-05 16:27:14');
INSERT INTO `data_dict` VALUES (32, 'logistics_company', 'EMS', 5, '.', '5', 0, '2019-04-05 16:28:00', '2019-04-05 16:28:00');
INSERT INTO `data_dict` VALUES (33, 'logistics_company', '申通快递', 6, '.', '6', 0, '2019-04-05 16:28:40', '2019-04-05 16:28:40');
INSERT INTO `data_dict` VALUES (34, 'logistics_company', '天天快递', 7, '.', '7', 0, '2019-04-05 16:28:51', '2019-04-05 16:28:51');
INSERT INTO `data_dict` VALUES (35, 'logistics_company', '环球速运', 8, '.', '8', 0, '2019-04-05 16:29:12', '2019-04-05 16:29:12');
INSERT INTO `data_dict` VALUES (36, 'testOne', 'ttest', 1, '1', 'testO', 0, '2019-04-11 17:48:29', '2019-04-11 17:48:28');
INSERT INTO `data_dict` VALUES (37, 'order_return_reason', '退货原因', 1, '退货原因', '0', 1, '2019-04-25 23:25:10', '2019-04-25 23:27:02');
INSERT INTO `data_dict` VALUES (38, 'order_return_reason', '不想要了', 1, '.', '1', 0, '2019-04-25 23:25:55', '2019-04-25 23:25:55');
INSERT INTO `data_dict` VALUES (39, 'order_return_reason', '多拍重拍', 2, '.', '2', 0, '2019-04-25 23:26:23', '2019-04-25 23:26:23');
INSERT INTO `data_dict` VALUES (40, 'order_return_reason', '少货', 3, '.', '3', 0, '2019-04-25 23:26:48', '2019-04-25 23:26:47');
INSERT INTO `data_dict` VALUES (41, 'order_return_return_type', '订单退货类型', 1, '.', '-1', 1, '2019-04-27 11:57:45', '2019-04-27 11:58:16');
INSERT INTO `data_dict` VALUES (42, 'order_return_return_type', '退货退款', 1, '.', '1', 1, '2019-04-27 11:58:05', '2019-04-27 12:12:01');
INSERT INTO `data_dict` VALUES (43, 'order_return_return_type', '退款', 2, '.', '2', 1, '2019-04-27 11:58:14', '2019-04-27 12:12:03');
INSERT INTO `data_dict` VALUES (44, 'order_return_service_type', '退货退款', 1, '.', '1', 0, '2019-04-27 12:12:26', '2019-04-27 12:12:25');
INSERT INTO `data_dict` VALUES (45, 'order_return_service_type', '退款', 2, '2', '2', 0, '2019-04-27 12:12:36', '2019-04-27 12:12:35');
INSERT INTO `data_dict` VALUES (46, 'order_return_status', '退货申请', 1, '1', '1', 0, '2019-05-06 22:50:06', '2019-05-06 22:50:05');
INSERT INTO `data_dict` VALUES (47, 'order_return_status', '2', 2, '2', '2', 1, '2019-05-06 22:50:46', '2019-05-06 22:51:57');
INSERT INTO `data_dict` VALUES (48, 'order_return_status', '1', 1, '1', '-1', 1, '2019-05-06 22:51:07', '2019-05-06 22:52:00');
INSERT INTO `data_dict` VALUES (49, 'order_return_status', '0', 0, '0', '0', 1, '2019-05-06 22:51:39', '2019-05-06 22:52:02');
INSERT INTO `data_dict` VALUES (50, 'order_return_status', '申请成功', 2, '.', '2', 0, '2019-05-06 22:52:13', '2019-05-06 22:52:12');
INSERT INTO `data_dict` VALUES (51, 'order_return_status', '申请失败', 3, '.', '3', 0, '2019-05-06 22:52:34', '2019-05-06 22:52:34');
INSERT INTO `data_dict` VALUES (52, 'order_return_status', '退货中', 4, '.', '4', 0, '2019-05-06 22:52:44', '2019-05-06 22:52:43');
INSERT INTO `data_dict` VALUES (53, 'order_return_status', '已收货', 5, '.', '5', 0, '2019-05-06 22:52:57', '2019-05-09 22:17:35');
INSERT INTO `data_dict` VALUES (54, 'order_return_status', '退款成功', 6, '.', '6', 0, '2019-05-09 22:17:52', '2019-05-09 22:17:51');
INSERT INTO `data_dict` VALUES (55, 'sms_platform', '云片', 1, '短信服务云片', '1', 0, '2019-05-26 15:09:37', '2019-05-26 15:09:37');
INSERT INTO `data_dict` VALUES (56, 'sms_platform', '阿里云', 2, '阿里云短信服务', '2', 0, '2019-05-26 15:09:56', '2019-05-26 15:09:56');
INSERT INTO `data_dict` VALUES (57, 'sms_apply_status', '审核中', 1, '审核中', '1', 0, '2019-05-26 15:11:27', '2019-05-26 15:11:27');
INSERT INTO `data_dict` VALUES (58, 'sms_apply_status', '审核成功', 2, '审核成功', '2', 0, '2019-05-26 15:11:40', '2019-05-26 15:11:40');
INSERT INTO `data_dict` VALUES (59, 'sms_apply_status', '审核失败', 10, '审核失败', '10', 0, '2019-05-26 15:12:10', '2019-05-26 15:12:10');
INSERT INTO `data_dict` VALUES (60, 'sms_type', '验证码', 1, '验证码', '1', 0, '2019-05-26 23:51:15', '2019-05-26 23:51:15');
INSERT INTO `data_dict` VALUES (61, 'sms_type', '通知', 2, '通知', '2', 0, '2019-05-26 23:51:29', '2019-05-26 23:51:28');
INSERT INTO `data_dict` VALUES (62, 'sms_type', '营销', 3, '营销', '3', 0, '2019-05-26 23:51:47', '2019-05-26 23:51:46');
COMMIT;

-- ----------------------------
-- Table structure for exception_log
-- ----------------------------
DROP TABLE IF EXISTS `exception_log`;
CREATE TABLE `exception_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `trace_id` varchar(64) NOT NULL COMMENT '链路追踪编号\n     *\n     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。',
  `user_id` int(11) NOT NULL COMMENT '用户编号.\n     *\n     * 当管理员为空时，该值为 {@link cn.iocoder.mall.admin.api.dto.AccessLogAddDTO#USER_ID_NULL}',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
  `application_name` varchar(50) NOT NULL COMMENT '应用名\n     *\n     * 目前读取 spring.application.name',
  `uri` varchar(4096) NOT NULL COMMENT '访问地址',
  `query_string` varchar(4096) NOT NULL COMMENT '参数',
  `method` varchar(50) NOT NULL COMMENT 'http 方法',
  `user_agent` varchar(1024) NOT NULL COMMENT 'userAgent',
  `ip` varchar(50) NOT NULL COMMENT 'ip',
  `exception_time` datetime NOT NULL COMMENT '异常发生时间',
  `exception_name` varchar(128) NOT NULL DEFAULT '' COMMENT '异常名\n     *\n     * {@link Throwable#getClass()} 的类全名',
  `exception_message` text NOT NULL COMMENT '异常导致的消息\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getMessage(Throwable)}',
  `exception_root_cause_message` text NOT NULL COMMENT '异常导致的根消息\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getRootCauseMessage(Throwable)}',
  `exception_stack_trace` text NOT NULL COMMENT '异常的栈轨迹\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getServiceException(Exception)}',
  `exception_class_name` varchar(512) NOT NULL COMMENT '异常发生的类全名\n     *\n     * {@link StackTraceElement#getClassName()}',
  `exception_file_name` varchar(512) NOT NULL COMMENT '异常发生的类文件\n     *\n     * {@link StackTraceElement#getFileName()}',
  `exception_method_name` varchar(512) NOT NULL COMMENT '异常发生的方法名\n     *\n     * {@link StackTraceElement#getMethodName()}',
  `exception_line_number` int(11) NOT NULL COMMENT '异常发生的方法所在行\n     *\n     * {@link StackTraceElement#getLineNumber()}',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=549 DEFAULT CHARSET=utf8mb4 COMMENT='exception_log';

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token` (
  `id` varchar(32) NOT NULL,
  `refresh_token` varchar(32) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(255) DEFAULT NULL COMMENT '用户类型',
  `valid` tinyint(4) DEFAULT NULL,
  `expires_time` datetime NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token` (
  `id` varchar(32) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(255) DEFAULT NULL COMMENT '用户类型',
  `valid` tinyint(4) DEFAULT NULL,
  `expires_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `type` int(11) NOT NULL DEFAULT '-1' COMMENT '资源类型',
  `sort` int(11) NOT NULL DEFAULT '-1' COMMENT '排序',
  `display_name` varchar(50) NOT NULL DEFAULT '' COMMENT '展示名',
  `pid` int(11) NOT NULL DEFAULT '-1' COMMENT '父级资源编号(外键：{@link ResourceDO#id})',
  `handler` varchar(50) DEFAULT '' COMMENT '操作\n     *\n     * 当资源类型为【菜单】时，handler 配置为界面 URL ，或者前端组件名\n     * 当资源类型为【操作】时，handler 配置为后端 URL 。举个例子，如果有一个「创建管理员」的表单，那么前端界面上的按钮可以根据这个 url 判断是否展示，后端接收到该 url 的请求时会判断是否有权限。',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限标识数组，使用逗号分隔',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COMMENT='resource_d_o';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='role_d_o';

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(11) NOT NULL DEFAULT '-1' COMMENT '角色编号(外键：{@link RoleDO}',
  `resource_id` int(11) NOT NULL DEFAULT '-1' COMMENT '资源编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=667 DEFAULT CHARSET=utf8mb4 COMMENT='role_resource_d_o';

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource` VALUES (1, 2, -1, '管理员信息', 0, '/admin/admin/info', NULL, NULL, '2019-02-24 09:07:43', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (2, 1, 1, '商品', 0, '', NULL, NULL, '2019-02-24 20:40:23', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (3, 1, 1, '商品管理', 2, '/product/list', NULL, NULL, '2019-02-27 23:34:36', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (8, 1, 1, '测试说明', 1, '123', NULL, NULL, '2019-02-28 09:11:15', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (9, 1, 1, '测试说明1', 0, NULL, NULL, NULL, '2019-02-28 10:33:57', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (10, 1, 1, '测试说明', 9, '/123', NULL, NULL, '2019-02-28 10:34:09', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (11, 1, 1, '测试', 9, '/admin/test', NULL, NULL, '2019-03-05 07:04:13', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (12, 1, 1, '首页', 0, NULL, NULL, NULL, '2019-03-10 03:56:58', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (13, 1, 8, '系统管理', 0, NULL, 'desktop', '', '2019-03-10 03:59:07', '2019-05-15 23:48:05', b'0');
INSERT INTO `resource` VALUES (14, 1, 0, '员工列表', 13, '/admin/admin-list', 'team', 'system.admin.page', '2019-03-10 03:59:29', '2019-05-16 10:47:59', b'0');
INSERT INTO `resource` VALUES (15, 1, 2, '权限列表', 13, '/admin/resource-list', NULL, NULL, '2019-03-10 03:59:56', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (16, 1, 1, '角色列表', 13, '/admin/role-list', NULL, NULL, '2019-03-10 04:00:35', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (17, 1, 1, '测试说明', 9, '/123', NULL, NULL, '2019-03-10 10:54:35', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (18, 1, 3, '角色管理', 13, '/admin/role-list', NULL, NULL, '2019-03-12 23:59:44', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (19, 1, 4, '数据字典', 13, '/admin/dictionary-list', 'select', 'system.dataDict.list', '2019-03-15 19:10:30', '2019-05-16 16:03:01', b'0');
INSERT INTO `resource` VALUES (20, 1, 4, '商品管理', 0, NULL, NULL, NULL, '2019-03-15 19:53:09', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (21, 1, 1, '商品列表', 20, '/product/product-spu-list', NULL, NULL, '2019-03-15 19:55:22', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (22, 1, 2, '展示类目', 20, '/product/product-category-list', NULL, NULL, '2019-03-15 19:56:42', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (23, 1, 7, '营销管理', 0, NULL, NULL, NULL, '2019-03-30 22:42:13', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (24, 1, 1, '首页广告', 23, '/promotion/banner-list', NULL, NULL, '2019-03-30 22:54:57', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (25, 1, 2, '商品推荐', 23, '/promotion/product-recommend-list', NULL, NULL, '2019-04-01 13:17:28', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (26, 1, 3, '优惠劵', 23, '/promotion/coupon-card-template-list', NULL, NULL, '2019-04-04 16:02:14', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (27, 1, 5, '订单管理', 0, NULL, NULL, NULL, '2019-04-06 12:53:55', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (28, 1, 1, '订单管理', 27, '/order/order-list', NULL, NULL, '2019-04-06 12:57:17', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (29, 1, 3, '商品品牌', 20, '/product/product-brand-list', NULL, NULL, '2019-04-09 17:58:36', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (30, 1, -99, '发布商品', 20, '/product/product-spu-add', NULL, NULL, '2019-05-01 21:01:38', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (31, 1, 0, '概述', 0, '/home', NULL, NULL, '2019-05-03 00:01:33', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (32, 1, 1, '数据分析', 0, NULL, NULL, NULL, '2019-05-03 00:02:08', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (33, 1, 2, '店铺资产', 0, NULL, 'money-collect', '', '2019-05-03 00:02:57', '2019-05-15 23:23:20', b'0');
INSERT INTO `resource` VALUES (34, 1, 6, '会员管理', 0, NULL, NULL, NULL, '2019-05-03 00:03:55', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (35, 1, 8, 'test', 0, NULL, NULL, NULL, '2019-05-03 13:23:46', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (36, 1, 1, '测试', 0, NULL, NULL, NULL, '2019-05-04 14:28:20', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (37, 1, 2, '测试2', 36, '/http://baidu.com', NULL, NULL, '2019-05-04 14:30:08', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (38, 2, 1, '策士3', 36, '/123213123123', NULL, NULL, '2019-05-04 14:30:37', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (39, 1, 1, 'ceshi', 0, NULL, NULL, NULL, '2019-05-04 14:41:06', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (40, 1, 1, '测试aa', 0, NULL, NULL, NULL, '2019-05-05 16:09:19', '2019-05-15 22:57:30', b'1');
INSERT INTO `resource` VALUES (41, 1, 24, '限时折扣', 23, '/promotion/time-limit-discount-list', NULL, NULL, '2019-05-07 22:34:30', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (42, 1, 25, '满减送', 23, '/promotion/full-privilege-list', NULL, NULL, '2019-05-08 00:05:20', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (43, 1, 1, '会员资料', 34, '/member/user-list', NULL, NULL, '2019-05-08 11:11:22', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (44, 1, 1, '支付单', 33, '/pay/transaction-list', NULL, NULL, '2019-05-08 14:17:15', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (45, 1, 2, '退款单', 33, '/pay/refund-list', NULL, NULL, '2019-05-08 16:58:05', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (46, 1, 1, '订单售后', 27, '/order/order-refunds', NULL, NULL, '2019-05-09 19:57:23', '2019-05-15 22:57:30', b'0');
INSERT INTO `resource` VALUES (47, 2, 1, '新建员工', 14, '', NULL, 'system.admin.add,system.admin.update', '2019-05-15 23:43:58', '2019-05-15 23:45:12', b'0');
INSERT INTO `resource` VALUES (48, 2, 1, '新建字典', 19, '', NULL, 'system.dataDict.add', '2019-05-16 16:00:57', '2019-05-16 16:00:56', b'0');
INSERT INTO `resource` VALUES (49, 2, 2, '编辑字典', 19, '', NULL, 'system.dataDict.update', '2019-05-16 16:01:18', '2019-05-16 16:01:18', b'0');
INSERT INTO `resource` VALUES (50, 2, 3, '删除字典', 19, '', NULL, 'system.dataDict.delete', '2019-05-16 16:01:36', '2019-05-16 16:01:36', b'0');
INSERT INTO `resource` VALUES (51, 1, -1, '短信ss', 0, '', 'user', '', '2019-05-26 12:00:31', '2019-06-03 13:54:54', b'0');
INSERT INTO `resource` VALUES (52, 1, 1, '短信签名', 51, '/sms/sign-list', 'user', '', '2019-05-26 12:01:56', '2019-05-26 12:01:56', b'0');
INSERT INTO `resource` VALUES (53, 1, 2, '短信模板', 51, '/sms/template-list', 'user', '', '2019-05-26 12:02:19', '2019-05-26 12:02:18', b'0');
COMMIT;

-- ----------------------------
-- Table structure for sms_sign
-- ----------------------------
DROP TABLE IF EXISTS `sms_sign`;
CREATE TABLE `sms_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign` varchar(20) NOT NULL COMMENT '签名 如： 【阿里云】',
  `platform` smallint(2) DEFAULT NULL COMMENT '短信平台',
  `apply_status` smallint(2) NOT NULL COMMENT '审核状态',
  `apply_message` varchar(250) DEFAULT NULL COMMENT '审核信息',
  `deleted` smallint(2) NOT NULL COMMENT '删除状态',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sms_sign_id` int(11) NOT NULL COMMENT '短信签名id',
  `template_code` varchar(50) NOT NULL COMMENT '模板code，第三方 code',
  `template` text NOT NULL COMMENT '短信模板',
  `platform` smallint(2) NOT NULL COMMENT '平台',
  `sms_type` smallint(2) NOT NULL COMMENT '短信类型：通知、验证码、营销',
  `apply_status` smallint(2) NOT NULL COMMENT '审核状态',
  `apply_message` varchar(250) DEFAULT NULL COMMENT '审核信息',
  `deleted` smallint(2) NOT NULL COMMENT '是否删除',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;



SET FOREIGN_KEY_CHECKS = 1;
