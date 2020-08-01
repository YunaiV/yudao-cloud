SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_spu
-- ----------------------------
CREATE TABLE `product_spu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'SPU 编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'SPU 名字',
  `sell_point` varchar(50) NOT NULL DEFAULT '' COMMENT '卖点',
  `description` text NOT NULL COMMENT '描述',
  `cid` int(11) NOT NULL DEFAULT '-1' COMMENT '分类编号',
  `pic_urls` varchar(1024) NOT NULL DEFAULT '' COMMENT '商品主图地址\n     *\n     * 数组，以逗号分隔\n     *\n     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张',
  `visible` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否上架商品（是否可见）。\n     *\n     * true 为已上架\n     * false 为已下架',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `price` int(11) NOT NULL COMMENT '价格',
  `quantity` int(11) NOT NULL COMMENT '库存数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COMMENT='商品 SPU';


-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
CREATE TABLE `product_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sku 编号',
  `spu_id` int(11) NOT NULL DEFAULT '-1' COMMENT '商品编号',
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT '状态\n     *\n     * 1-正常\n     * 2-禁用',
  `pic_url` varchar(50) DEFAULT '' COMMENT '图片地址',
  `attrs` varchar(50) NOT NULL DEFAULT '' COMMENT '规格值({@link ProductAttrDO})数组\n     *\n     * 数组，以逗号分隔',
  `price` int(11) NOT NULL DEFAULT '-1' COMMENT '价格，单位：分',
  `quantity` int(11) NOT NULL DEFAULT '-1' COMMENT '库存数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COMMENT='商品 SKU';


-- ----------------------------
-- Table structure for product_attr_key
-- ----------------------------
CREATE TABLE `product_attr_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格键编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '规格键名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格键';


-- ----------------------------
-- Table structure for product_attr_value
-- ----------------------------
CREATE TABLE `product_attr_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格值编号',
  `attr_key_id` int(11) NOT NULL COMMENT '规格键编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '规格值名字',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格值';


-- ----------------------------
-- Table structure for product_category
-- ----------------------------
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `pid` int(11) NOT NULL COMMENT '父分类编号',
  `name` varchar(16) NOT NULL COMMENT '分类名称',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '分类图片',
  `sort` int(11) NOT NULL COMMENT '分类排序',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=801 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';


-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
CREATE TABLE `product_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌编号',
  `name` varchar(50) NOT NULL COMMENT '品牌名称',
  `description` varchar(255) DEFAULT NULL COMMENT '品牌描述',
  `pic_url` varchar(1024) DEFAULT NULL COMMENT '品牌名图片',
  `status` tinyint(4) NOT NULL COMMENT '状态 1开启 2禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌';
