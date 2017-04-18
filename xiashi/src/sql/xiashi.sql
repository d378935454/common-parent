/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xiashi

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-17 13:32:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `express`
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_no` varchar(50) DEFAULT NULL COMMENT '物流编号',
  `people_name` varchar(255) DEFAULT NULL COMMENT '物流人员',
  `startdate` timestamp NULL DEFAULT NULL COMMENT '发送时间',
  `old_senddate` timestamp NULL DEFAULT NULL COMMENT '预计送达时间',
  `rel_senddate` timestamp NULL DEFAULT NULL COMMENT '实际送达时间',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='物流表';

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES ('1', 'asd', '阿斯达', null, '2017-04-16 18:01:08', '2017-04-16 18:01:08', '2017-04-16 18:00:53', '2017-04-16 18:01:08', '0');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_no` varchar(30) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(20) DEFAULT NULL COMMENT '商品名',
  `type_id` bigint(20) DEFAULT NULL,
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品组表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', null, '土鸡蛋', '1', '2017-04-16 17:20:36', '2017-04-16 17:22:27', '0');

-- ----------------------------
-- Table structure for `goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品组id',
  `goods_info_no` varchar(30) DEFAULT NULL COMMENT '商品编号',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格id',
  `old_price` decimal(10,0) DEFAULT NULL COMMENT '原价',
  `price` decimal(10,0) DEFAULT NULL COMMENT '现价',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1', '1', null, '1', null, null, '土鸡蛋100', '2017-04-16 17:20:11', '2017-04-16 17:24:02', '0');
INSERT INTO `goods_info` VALUES ('2', '1', null, '1', null, null, '土鸡蛋200', '2017-04-16 17:20:18', '2017-04-16 17:24:03', '0');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(20) DEFAULT NULL,
  `href` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL COMMENT '父级菜单',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, 'aaaa', '仓库', '0', '0', '2017-04-02 17:01:28', '2017-04-08 11:37:32', '0');
INSERT INTO `menu` VALUES ('2', null, 'as', '简易库存输入', '1', '1', '2017-04-02 17:16:53', '2017-04-08 11:37:39', '0');
INSERT INTO `menu` VALUES ('3', null, 'asd', '进入库信息', '1', '1', '2017-04-02 17:16:59', '2017-04-08 11:37:48', '0');
INSERT INTO `menu` VALUES ('4', null, '21', '生产信息', '1', '1', '2017-04-02 23:18:28', '2017-04-08 11:40:16', '0');
INSERT INTO `menu` VALUES ('5', null, '234', '损耗信息', '1', '1', '2017-04-02 23:18:35', '2017-04-08 11:40:19', '0');
INSERT INTO `menu` VALUES ('6', null, '水电费第三方', '供应链', '0', '0', '2017-04-02 23:18:37', '2017-04-08 11:40:31', '0');
INSERT INTO `menu` VALUES ('7', null, 'we', '确认采购单', '1', '6', '2017-04-07 21:57:02', '2017-04-08 11:40:41', '0');
INSERT INTO `menu` VALUES ('8', null, null, '确认物流单', '1', '6', '2017-04-08 11:40:53', '2017-04-08 11:40:53', '0');
INSERT INTO `menu` VALUES ('9', null, null, '物流', '0', '0', '2017-04-08 11:41:09', '2017-04-08 11:41:09', '0');
INSERT INTO `menu` VALUES ('10', null, null, '查看物流单据', '1', '9', '2017-04-08 11:41:16', '2017-04-08 11:41:55', '0');
INSERT INTO `menu` VALUES ('11', null, null, '输入质检信息', '1', '9', '2017-04-08 11:41:26', '2017-04-08 11:41:55', '0');
INSERT INTO `menu` VALUES ('12', null, null, '修改物流单据', '1', '9', '2017-04-08 11:41:32', '2017-04-08 11:41:55', '0');
INSERT INTO `menu` VALUES ('13', null, null, '输入到货时间', '1', '9', '2017-04-08 11:41:38', '2017-04-08 11:41:56', '0');
INSERT INTO `menu` VALUES ('14', null, null, '输入客户端收货凭证', '1', '9', '2017-04-08 11:41:48', '2017-04-08 11:41:57', '0');
INSERT INTO `menu` VALUES ('15', null, null, '夏实销售部', '0', '0', '2017-04-08 11:42:09', '2017-04-08 11:42:36', '0');
INSERT INTO `menu` VALUES ('16', null, null, '客户数据管理', '1', '15', '2017-04-08 11:42:17', '2017-04-08 11:42:42', '0');
INSERT INTO `menu` VALUES ('17', null, null, '订单输入输入', '1', '15', '2017-04-08 11:42:22', '2017-04-08 11:42:43', '0');
INSERT INTO `menu` VALUES ('18', null, null, '收货信息输入', '1', '15', '2017-04-08 11:42:28', '2017-04-08 11:42:43', '0');
INSERT INTO `menu` VALUES ('19', null, null, '收款凭证输入', '1', '15', '2017-04-08 11:42:33', '2017-04-08 11:42:44', '0');
INSERT INTO `menu` VALUES ('20', null, null, '夏实供应部', '0', '0', '2017-04-08 11:42:54', '2017-04-08 11:42:54', '0');
INSERT INTO `menu` VALUES ('21', null, null, '物流数据管理', '1', '20', '2017-04-08 11:42:59', '2017-04-08 11:51:15', '0');
INSERT INTO `menu` VALUES ('22', null, null, '供应商信息管理', '1', '20', '2017-04-08 11:43:05', '2017-04-08 11:51:17', '0');
INSERT INTO `menu` VALUES ('23', null, null, '物流单输入', '1', '20', '2017-04-08 11:43:10', '2017-04-08 11:51:17', '0');
INSERT INTO `menu` VALUES ('24', null, null, '采购单输入', '1', '20', '2017-04-08 11:43:15', '2017-04-08 11:51:18', '0');
INSERT INTO `menu` VALUES ('25', null, null, '付款凭证输入', '1', '20', '2017-04-08 11:43:21', '2017-04-08 11:51:19', '0');

-- ----------------------------
-- Table structure for `order_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '主订单id',
  `goods_info_id` bigint(20) NOT NULL COMMENT '商品id',
  `express_id` bigint(20) NOT NULL COMMENT '物流id',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格id',
  `old_price` decimal(10,0) NOT NULL COMMENT '原价',
  `price` decimal(10,0) NOT NULL COMMENT '实际价格',
  `all_num` int(11) NOT NULL COMMENT '要求的总数量',
  `check_num` int(11) DEFAULT NULL COMMENT '质检数量',
  `send_num` int(11) DEFAULT NULL COMMENT '送达数量',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_Delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='子订单';

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('1', '1', '2', '1', null, '2', '2', '100', null, null, '2017-04-16 16:39:57', '2017-04-16 17:22:43', '0');
INSERT INTO `order_info` VALUES ('2', '1', '3', '1', null, '2', '2', '12', null, null, '2017-04-16 16:40:08', '2017-04-16 17:22:56', '0');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `type` int(11) DEFAULT NULL COMMENT '角色类型',
  `admin_id` int(11) DEFAULT NULL COMMENT '属于哪个角色',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统管理员', 'admin', '1', null, '2017-04-02 16:32:28', '2017-04-08 11:46:04', '0');
INSERT INTO `permission` VALUES ('2', '仓库', '22', '2', '1', '2017-04-02 16:32:35', '2017-04-08 11:59:27', '0');
INSERT INTO `permission` VALUES ('3', '供应链', null, null, '1', '2017-04-08 11:59:39', '2017-04-08 12:00:22', '0');
INSERT INTO `permission` VALUES ('4', '物流', null, null, '1', '2017-04-08 11:59:45', '2017-04-08 12:00:23', '0');
INSERT INTO `permission` VALUES ('5', '夏实销售部', null, null, '1', '2017-04-08 11:59:53', '2017-04-08 12:00:24', '0');
INSERT INTO `permission` VALUES ('6', '夏实供应部', null, null, '1', '2017-04-08 12:00:01', '2017-04-08 12:00:25', '0');
INSERT INTO `permission` VALUES ('7', null, null, null, null, '2017-04-08 12:00:06', '2017-04-08 12:00:06', '0');

-- ----------------------------
-- Table structure for `permission_menu`
-- ----------------------------
DROP TABLE IF EXISTS `permission_menu`;
CREATE TABLE `permission_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL COMMENT '角色表id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='角色与菜单关系表';

-- ----------------------------
-- Records of permission_menu
-- ----------------------------
INSERT INTO `permission_menu` VALUES ('1', '1', '1');
INSERT INTO `permission_menu` VALUES ('2', '1', '2');
INSERT INTO `permission_menu` VALUES ('3', '1', '3');
INSERT INTO `permission_menu` VALUES ('4', '1', '4');
INSERT INTO `permission_menu` VALUES ('5', '1', '5');
INSERT INTO `permission_menu` VALUES ('6', '1', '6');
INSERT INTO `permission_menu` VALUES ('7', '1', '7');
INSERT INTO `permission_menu` VALUES ('8', '1', '8');
INSERT INTO `permission_menu` VALUES ('9', '1', '9');
INSERT INTO `permission_menu` VALUES ('10', '1', '10');
INSERT INTO `permission_menu` VALUES ('11', '1', '11');
INSERT INTO `permission_menu` VALUES ('12', '1', '12');
INSERT INTO `permission_menu` VALUES ('13', '1', '13');
INSERT INTO `permission_menu` VALUES ('14', '1', '14');
INSERT INTO `permission_menu` VALUES ('15', '1', '15');
INSERT INTO `permission_menu` VALUES ('16', '1', '16');
INSERT INTO `permission_menu` VALUES ('17', '1', '17');
INSERT INTO `permission_menu` VALUES ('18', '1', '18');
INSERT INTO `permission_menu` VALUES ('19', '1', '19');
INSERT INTO `permission_menu` VALUES ('20', '1', '20');
INSERT INTO `permission_menu` VALUES ('21', '1', '21');
INSERT INTO `permission_menu` VALUES ('22', '1', '22');
INSERT INTO `permission_menu` VALUES ('23', '1', '23');
INSERT INTO `permission_menu` VALUES ('24', '1', '24');
INSERT INTO `permission_menu` VALUES ('25', '1', '25');
INSERT INTO `permission_menu` VALUES ('26', '2', '1');
INSERT INTO `permission_menu` VALUES ('27', '2', '2');
INSERT INTO `permission_menu` VALUES ('28', '2', '3');
INSERT INTO `permission_menu` VALUES ('29', '2', '4');
INSERT INTO `permission_menu` VALUES ('30', '2', '5');
INSERT INTO `permission_menu` VALUES ('31', '3', '6');
INSERT INTO `permission_menu` VALUES ('32', '3', '7');
INSERT INTO `permission_menu` VALUES ('33', '3', '8');
INSERT INTO `permission_menu` VALUES ('34', '4', '9');
INSERT INTO `permission_menu` VALUES ('35', '4', '10');
INSERT INTO `permission_menu` VALUES ('36', '4', '11');
INSERT INTO `permission_menu` VALUES ('37', '4', '12');
INSERT INTO `permission_menu` VALUES ('38', '4', '13');
INSERT INTO `permission_menu` VALUES ('39', '4', '14');
INSERT INTO `permission_menu` VALUES ('40', '5', '15');
INSERT INTO `permission_menu` VALUES ('41', '5', '16');
INSERT INTO `permission_menu` VALUES ('42', '5', '17');
INSERT INTO `permission_menu` VALUES ('43', '5', '18');
INSERT INTO `permission_menu` VALUES ('44', '5', '19');
INSERT INTO `permission_menu` VALUES ('45', '6', '20');
INSERT INTO `permission_menu` VALUES ('46', '6', '21');
INSERT INTO `permission_menu` VALUES ('47', '6', '22');
INSERT INTO `permission_menu` VALUES ('48', '6', '23');
INSERT INTO `permission_menu` VALUES ('49', '6', '24');
INSERT INTO `permission_menu` VALUES ('50', '6', '25');

-- ----------------------------
-- Table structure for `spec`
-- ----------------------------
DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spec` varchar(255) DEFAULT NULL COMMENT '规格内容',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='规格表';

-- ----------------------------
-- Records of spec
-- ----------------------------
INSERT INTO `spec` VALUES ('1', '克', '2017-04-16 17:24:27', '2017-04-16 17:24:27', '0');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '种类名',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='种类表';

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '鸡蛋', '2017-04-16 17:20:52', '2017-04-16 17:21:17', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(24) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `real_name` varchar(10) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL COMMENT '角色id',
  `email` varchar(30) DEFAULT NULL,
  `last_app_login_date` datetime DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `id_card` char(18) DEFAULT NULL,
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='账号表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', 'aaaa', '123', null, '1', null, null, null, null, '2017-04-02 15:39:00', '2017-04-03 18:29:20', '0');
INSERT INTO `user` VALUES ('2', 'b', 'b', 'b', null, '1', '2', null, null, null, null, '2017-04-02 16:31:49', '2017-04-02 16:32:15', '0');

-- ----------------------------
-- Table structure for `xs_order`
-- ----------------------------
DROP TABLE IF EXISTS `xs_order`;
CREATE TABLE `xs_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) DEFAULT NULL COMMENT '订单状态',
  `old_price` decimal(10,0) DEFAULT NULL COMMENT '原价格',
  `price` decimal(10,0) DEFAULT NULL COMMENT '实际价格',
  `send_address` varchar(50) DEFAULT NULL COMMENT '送达地点',
  `send_date` timestamp NULL DEFAULT NULL COMMENT '预计送达时间',
  `rel_send_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '实际送达时间',
  `get_address` varchar(50) DEFAULT NULL COMMENT '取货地点',
  `get_date` timestamp NULL DEFAULT NULL COMMENT '取货时间',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '凭证的照片地址',
  `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xs_order
-- ----------------------------
INSERT INTO `xs_order` VALUES ('1', null, null, null, null, null, null, null, null, null, '2017-04-16 16:39:24', '2017-04-16 16:39:24', '0');
