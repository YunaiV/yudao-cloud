DROP TABLE `order`;
DROP TABLE `order_item`;
DROP TABLE `order_exchange`;
DROP TABLE `order_return`;
DROP TABLE `order_logistics`;
DROP TABLE `order_logistics_detail`;

CREATE TABLE `order` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'Id，自增长',
`order_logistics_id` int NOT NULL COMMENT '物流id',
`order_no` varchar(50) NOT NULL COMMENT '订单单号',
`price` int(10) NULL COMMENT '金额(分)',
`payment_time` datetime NULL COMMENT '付款时间',
`delivery_time` datetime NULL COMMENT '发货时间',
`receiver_time` datetime NULL COMMENT '收货时间',
`closing_time` datetime NULL COMMENT '成交时间',
`has_return_exchange` smallint NULL COMMENT '是否退换货',
`remark` varchar(255) NULL COMMENT '备注',
`status` smallint(2) NULL COMMENT '状态(如果有多个商品分开发货需要全部商品发完才会改变状态) 0、待付款 1、待发货 2、待收货 3、已完成 4、已关闭',
`create_time` datetime NULL COMMENT '订单创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`deleted` smallint NULL COMMENT '删除状态',
PRIMARY KEY (`id`) 
);
CREATE TABLE `order_item` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
`order_id` int NOT NULL COMMENT '订单编号',
`order_no` varchar(50) NOT NULL COMMENT '订单号',
`sku_id` int NOT NULL COMMENT '商品编号',
`quantity` int(3) NOT NULL COMMENT '商品数量',
`price` int(255) NOT NULL COMMENT '金额',
`payment_time` datetime NULL COMMENT '付款时间',
`delivery_time` datetime NULL COMMENT '发货时间',
`receiver_time` datetime NULL COMMENT '收货时间',
`closing_time` datetime NULL,
`has_return_exchange` int NULL COMMENT '是否退换货',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`status` smallint(2) NOT NULL COMMENT '状态：0、代发货 1、已发货 2、已收货 20、换货中 21、换货成功 40、退货中 41、已退货',
`delete` smallint(2) NOT NULL COMMENT '删除状态',
PRIMARY KEY (`id`) 
);

CREATE TABLE `order_exchange` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`order_id` int(11) NOT NULL,
`order_no` varchar(50) NOT NULL,
`sku_id` int(11) NOT NULL,
`exchange_sku_id` int(11) NOT NULL COMMENT '换货商品id',
`exchange_order_logistics_id` int(11) NOT NULL COMMENT '换货物流id',
`receiver_order_logistics_id` int(11) NOT NULL COMMENT '收件地址',
`order_reason_id` int(11) NULL COMMENT '换货原因',
`reason` varchar(255) NULL COMMENT '换货原因 (其他的时候)',
`payment_time` datetime NULL COMMENT '付款时间',
`delivery_time` datetime NULL COMMENT '发货时间',
`receiver_time` datetime NULL COMMENT '收货时间',
`closing_time` datetime NULL COMMENT '成交时间',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`delete` smallint(2) NULL COMMENT '删除状态',
`order_type` int(2) NULL COMMENT '订单类型 0、为 Order 订单 1、为 OrderItem 订单',
`status` int(2) NULL COMMENT '状态 申请换货、申请成功、申请失败、换货中、换货成功',
PRIMARY KEY (`id`)
);


CREATE TABLE `order_return` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
`order_id` int NOT NULL COMMENT '订单编号',
`order_no` varchar(50) NOT NULL COMMENT '订单号',
`sku_id` int NOT NULL COMMENT '商品编号',
`order_item_id` int(11) NOT NULL COMMENT '订单item id',
`order_logistics_id` int(11) NOT NULL COMMENT '物流 id',

`order_reason_id` int(11) NULL COMMENT '退货原因',
`reason` varchar(255) NULL COMMENT '换货原因 (其他的时候)',
`create_time` datetime NULL COMMENT '创建时间',
`approval_time` datetime NULL COMMENT '同意时间',
`logistics_time` datetime NULL COMMENT '物流时间（填写物流单号时间）',
`receiver_time` datetime NULL COMMENT '收货时间',
`closing_time` datetime NULL COMMENT '成交时间',
`order_type` int(2) NULL COMMENT '订单类型 0、为 Order 订单 1、为 OrderItem 订单',

`update_time` datetime NULL COMMENT '更新时间',
`delete` smallint(2) NULL COMMENT '删除状态',
`status` int(2) NULL COMMENT '状态 申请换货、申请成功、申请失败、退货中、退货成功',
PRIMARY KEY (`id`) 
);


CREATE TABLE `order_logistics` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
`area_no` int NOT NULL COMMENT '订单编号',
`name` VARCHAR(20) NOT NULL COMMENT '名称',
`mobile` VARCHAR(20) NOT NULL COMMENT '手机号',
`address` VARCHAR(255) NOT NULL COMMENT '详细地址',
`logistics_no` VARCHAR(20) NOT NULL COMMENT '物流单号',
PRIMARY KEY (`id`)
);


CREATE TABLE `order_logistics_detail` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
`order_logistics_id` int NOT NULL COMMENT '物流编号',
`logistics_time` datetime NOT NULL COMMENT '物流时间',
`logistics_information` VARCHAR(20) NOT NULL COMMENT '物流信息',
PRIMARY KEY (`id`)
);













