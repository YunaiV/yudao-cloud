SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(10) NOT NULL COMMENT '真实名字',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `status` tinyint(4) NOT NULL COMMENT '在职状态',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登陆账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '加密后的密码',
  `password_salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码的盐',
  `create_admin_id` int(11) NOT NULL COMMENT '创建管理员编号',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建 IP',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='管理员';

-- ----------------------------
-- Table structure for admin_department
-- ----------------------------
DROP TABLE IF EXISTS `admin_department`;
CREATE TABLE `admin_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父级部门编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门';

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token` (
  `id` varchar(32) NOT NULL COMMENT '访问令牌',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
  `refresh_token` varchar(32) NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建 IP',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_userId` (`user_id`) USING BTREE,
  KEY `idx_refreshToken` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问令牌';

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token` (
  `id` varchar(32) NOT NULL COMMENT '编号，刷新令牌',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建 IP',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_userId` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='刷新令牌';

-- ----------------------------
-- Table structure for permission_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_admin_role`;
CREATE TABLE `permission_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(11) NOT NULL COMMENT '管理员编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='管理员角色';

-- ----------------------------
-- Table structure for permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource`;
CREATE TABLE `permission_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` int(11) NOT NULL COMMENT '资源类型',
  `sort` int(11) NOT NULL COMMENT '排序',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父级资源编号(外键：{@link ResourceDO#id})',
  `route` varchar(50) DEFAULT NULL COMMENT '前端路由',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `view` varchar(50) DEFAULT NULL COMMENT '前端界面',
  `create_admin_id` int(11) NOT NULL COMMENT '创建管理员编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名',
  `code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `type` tinyint(4) NOT NULL COMMENT '角色类型',
  `create_admin_id` int(11) NOT NULL COMMENT '创建管理员编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for permission_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_role_resource`;
CREATE TABLE `permission_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(11) NOT NULL DEFAULT '-1' COMMENT '角色编号(外键：{@link RoleDO}',
  `resource_id` int(11) NOT NULL DEFAULT '-1' COMMENT '资源编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=860 DEFAULT CHARSET=utf8mb4 COMMENT='角色资源';

-- ----------------------------
-- Table structure for system_access_log
-- ----------------------------
DROP TABLE IF EXISTS `system_access_log`;
CREATE TABLE `system_access_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `trace_id` varchar(64) DEFAULT NULL COMMENT '链路追踪编号',
  `application_name` varchar(50) NOT NULL COMMENT '应用名',
  `uri` varchar(4096) NOT NULL DEFAULT '' COMMENT '访问地址',
  `query_string` varchar(4096) NOT NULL DEFAULT '' COMMENT '参数',
  `method` varchar(50) NOT NULL DEFAULT '' COMMENT 'http 方法',
  `user_agent` varchar(1024) NOT NULL DEFAULT '' COMMENT 'userAgent',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip',
  `start_time` datetime NOT NULL COMMENT '请求时间',
  `response_time` int(11) NOT NULL COMMENT '响应时长 -- 毫秒级',
  `error_code` int(11) NOT NULL COMMENT '错误码',
  `error_message` varchar(512) DEFAULT NULL COMMENT '错误提示',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87141 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问日志';

-- ----------------------------
-- Table structure for system_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_data_dict`;
CREATE TABLE `system_data_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `enum_value` varchar(50) NOT NULL DEFAULT '' COMMENT '大类枚举值',
  `value` varchar(50) NOT NULL DEFAULT '' COMMENT '小类数值',
  `display_name` varchar(50) NOT NULL DEFAULT '' COMMENT '展示名',
  `sort` int(11) NOT NULL DEFAULT '-1' COMMENT '排序值',
  `memo` varchar(50) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Table structure for system_error_code
-- ----------------------------
CREATE TABLE `system_error_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '错误码编号',
  `code` int(11) NOT NULL DEFAULT '0' COMMENT '错误码编码',
  `message` varchar(255) NOT NULL DEFAULT '' COMMENT '错误码错误提示',
  `type` tinyint(4) NOT NULL COMMENT '错误码类型',
  `group` varchar(64) NOT NULL COMMENT '错误码分组',
  `memo` varchar(255) DEFAULT NULL COMMENT '错误码备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8mb4 COMMENT='错误码';

-- ----------------------------
-- Table structure for system_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `system_exception_log`;
CREATE TABLE `system_exception_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `trace_id` varchar(64) NOT NULL COMMENT '链路追踪编号\n     *\n     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。',
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
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4 COMMENT='系统异常日志';

SET FOREIGN_KEY_CHECKS = 1;
