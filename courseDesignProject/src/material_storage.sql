/*
Navicat MySQL Data Transfer

Source Server         : 3306
Source Server Version : 50128
Source Host           : 127.0.0.1:3306
Source Database       : material_storage

Target Server Type    : MYSQL
Target Server Version : 50128
File Encoding         : 65001

Date: 2019-07-11 12:48:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `员工姓名` varchar(20) NOT NULL,
  `电话` int(100) NOT NULL,
  `邮箱` varchar(30) NOT NULL,
  `住址` varchar(50) DEFAULT NULL,
  `出生日期` date DEFAULT NULL,
  `性别` varchar(1) NOT NULL,
  `工号` int(20) NOT NULL,
  `备注` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`工号`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('小张', '123456789', '1111111111@163.com', '1', '2019-07-09', '女', '1', '11');
INSERT INTO `employee` VALUES ('小李', '123456987', '1111111122@163.com', '2', '2019-07-01', '女', '2', '');
INSERT INTO `employee` VALUES ('小王', '123456879', '1111111133@163.com', '3', '2019-07-03', '男', '3', '');
INSERT INTO `employee` VALUES ('小赵', '123459876', '1111111144@163.com', '4', '2019-07-05', '男', '4', '');

-- ----------------------------
-- Table structure for expart
-- ----------------------------
DROP TABLE IF EXISTS `expart`;
CREATE TABLE `expart` (
  `出库备件编码` varchar(20) NOT NULL,
  `入库单号` varchar(20) NOT NULL,
  `库位` varchar(20) NOT NULL,
  `出库数量` varchar(255) DEFAULT NULL,
  `出库单价` varchar(255) DEFAULT NULL,
  `出库时间` datetime NOT NULL,
  `经办人` varchar(100) NOT NULL,
  `备注` varchar(100) DEFAULT NULL,
  `出库单号` varchar(20) NOT NULL,
  PRIMARY KEY (`出库单号`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of expart
-- ----------------------------
INSERT INTO `expart` VALUES ('123321124', '201701110004', 'A', '200', '', '2019-07-11 10:19:44', '3', '', 'a201907110004');
INSERT INTO `expart` VALUES ('123321125', '201707110003', 'A', '200', '', '2019-07-11 10:19:19', '3', '', 'a201907110003');
INSERT INTO `expart` VALUES ('123321126', '201707110008', 'D', '50', '', '2019-07-11 10:17:24', '4', '', 'a201907110002');
INSERT INTO `expart` VALUES ('123321126', '201707110007', 'D', '20', '10', '2019-07-11 10:16:47', '4', '', 'a201907110001');

-- ----------------------------
-- Table structure for inpart
-- ----------------------------
DROP TABLE IF EXISTS `inpart`;
CREATE TABLE `inpart` (
  `入库单号` varchar(20) NOT NULL,
  `入库备件编码` varchar(20) NOT NULL,
  `数量` varchar(255) NOT NULL,
  `进货价` varchar(255) NOT NULL,
  `默认库位` varchar(255) NOT NULL,
  `供货单位` varchar(255) DEFAULT NULL,
  `经办人` varchar(255) DEFAULT NULL,
  `备注` varchar(255) DEFAULT NULL,
  `入库时间` datetime NOT NULL,
  PRIMARY KEY (`入库单号`),
  KEY `备件名称` (`入库备件编码`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of inpart
-- ----------------------------
INSERT INTO `inpart` VALUES ('201707110006', '123321123', '1000', '0.1', 'A', '公司1', '2', '', '2019-07-11 10:04:37');
INSERT INTO `inpart` VALUES ('201707110009', '123321126', '100', '5', 'D', '公司4', '4', '', '2019-07-11 10:14:47');
INSERT INTO `inpart` VALUES ('201707110008', '123321126', '100', '5', 'D', '公司4', '4', '', '2019-07-11 10:13:29');
INSERT INTO `inpart` VALUES ('201707110007', '123321126', '100', '5', 'D', '公司4', '4', '', '2019-07-11 10:13:16');
INSERT INTO `inpart` VALUES ('201707110003', '123321125', '500', '1', 'A', '公司3', '1', '', '2019-07-11 10:00:00');
INSERT INTO `inpart` VALUES ('201701110004', '123321124', '1000', '0.1', 'A', '公司1', '2', '', '2019-07-11 10:00:31');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `库存备件编码` varchar(20) DEFAULT NULL,
  `剩余库存` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('123321125', '300');
INSERT INTO `inventory` VALUES ('123321124', '800');
INSERT INTO `inventory` VALUES ('123321126', '230');
INSERT INTO `inventory` VALUES ('123321123', '1000');

-- ----------------------------
-- Table structure for spare_parts
-- ----------------------------
DROP TABLE IF EXISTS `spare_parts`;
CREATE TABLE `spare_parts` (
  `备件名称` varchar(255) NOT NULL,
  `备件编码` varchar(12) NOT NULL,
  `备件单位` varchar(255) NOT NULL,
  `存放库区` varchar(10) NOT NULL,
  `备件单价` varchar(255) NOT NULL,
  `备件质量` varchar(255) NOT NULL,
  `备件规格` varchar(255) NOT NULL,
  `安全库存` varchar(255) NOT NULL,
  `原始库存` varchar(255) NOT NULL,
  `初始年需求量` varchar(255) NOT NULL,
  `订货成本` varchar(255) NOT NULL,
  `单位库存成本` varchar(255) NOT NULL,
  PRIMARY KEY (`备件编码`),
  KEY `备件名称` (`备件名称`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of spare_parts
-- ----------------------------
INSERT INTO `spare_parts` VALUES ('扳手', '123321126', '个', 'A', '10', '2', '*', '100', '200', '50', '2000', '20');
INSERT INTO `spare_parts` VALUES ('火花塞', '123321125', '个', 'B', '10', '1', '*', '50', '100', '30', '500', '10');
INSERT INTO `spare_parts` VALUES ('螺母', '123321124', '个', 'B', '0.1', '0.1', '*', '100', '200', '100', '20', '2');
INSERT INTO `spare_parts` VALUES ('螺栓', '123321123', '个', 'A', '0.1', '0.1', '*', '100', '200', '100', '20', '1');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `供应商编号` varchar(20) NOT NULL,
  `供应商名称` varchar(100) NOT NULL,
  `供应商电话` varchar(20) NOT NULL,
  `负责人工号` varchar(20) DEFAULT NULL,
  `供应商备注` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`供应商编号`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('aa', '公司1', '111111', '1', null);
INSERT INTO `supplier` VALUES ('bb', '公司2', '222222', '2', null);
INSERT INTO `supplier` VALUES ('cc', '公司3', '333333', '3', null);
INSERT INTO `supplier` VALUES ('dd', '公司4', '444444', '4', null);

-- ----------------------------
-- Table structure for tuser
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `Uname` varchar(255) NOT NULL,
  `Upwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Uname`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tuser
-- ----------------------------
INSERT INTO `tuser` VALUES ('1', '1');
INSERT INTO `tuser` VALUES ('2', '2');
INSERT INTO `tuser` VALUES ('3', '3');
INSERT INTO `tuser` VALUES ('4', '4');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `仓库编号` varchar(20) NOT NULL,
  `仓库负责人工号` varchar(2) NOT NULL,
  `仓库电话` varchar(20) NOT NULL,
  `仓库备注` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`仓库编号`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('A', '1', '11', '11');
INSERT INTO `warehouse` VALUES ('B', '2', '22', null);
INSERT INTO `warehouse` VALUES ('C', '3', '33', null);
INSERT INTO `warehouse` VALUES ('D', '4', '44', null);
