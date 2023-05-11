/*
Navicat MySQL Data Transfer

Source Server         : 服务器mysql
Source Server Version : 50739
Source Host           : localhost:3306
Source Database       : lzg

Target Server Type    : MYSQL
Target Server Version : 50739
File Encoding         : 65001

Date: 2023-05-11 19:15:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) DEFAULT NULL,
  `content` mediumtext,
  `time` varchar(32) DEFAULT NULL,
  `imageSize` int(2) DEFAULT NULL,
  `name` varchar(8) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL,
  `commentCount` int(8) DEFAULT '0',
  `loveCount` int(8) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('358', '123456', '666', '2022-12-16/16:31:28', '1', '轮子哥', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/111111.jpg', '0', '0');
INSERT INTO `article` VALUES ('359', '123456', '多图测试', '2022-12-16/16:31:43', '2', '轮子哥', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/111111.jpg', '0', '2');
INSERT INTO `article` VALUES ('360', '123456', '3张图测试', '2022-12-16/16:32:11', '3', '轮子哥', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/111111.jpg', '0', '4');
INSERT INTO `article` VALUES ('362', '123456', '测试', '2022-12-18/10:27:20', '4', '轮子哥666', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/123456.jpg', '0', '13');
INSERT INTO `article` VALUES ('363', '666666', '我是小号', '2022-12-21/10:29:20', '2', null, null, '0', '15');
INSERT INTO `article` VALUES ('364', '6152212', '轮子哥', '2022-12-21/15:19:09', '4', null, null, '0', '1');
INSERT INTO `article` VALUES ('365', '123456', 'hahaha', '2023-05-11/09:14:36', '1', '轮子哥666', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/123456.jpg', '0', '0');

-- ----------------------------
-- Table structure for cron
-- ----------------------------
DROP TABLE IF EXISTS `cron`;
CREATE TABLE `cron` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `time` varchar(16) NOT NULL,
  `message` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cron
-- ----------------------------
INSERT INTO `cron` VALUES ('1', '0 0/2 * * * ?', '这是数据库cron表定义的message');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `time` varchar(32) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '2022-8-13  12:32:12', '测试通知', '官方公告消息：将于xxxx.xx.xx进行更新，请到http://xxx/xxx下载最新版本，或者通过【我的】->【右上方菜单栏】->【版本更新进行更新】');
INSERT INTO `notice` VALUES ('2', '2022-8-13  12:32:12', '浙江皮革厂', '好消息！好消息！浙江温州皮革厂倒闭啦，九块九，九块九，统统九块九，清仓大甩卖！');
INSERT INTO `notice` VALUES ('3', '2022-8-13  12:38:38', '欢迎', '欢迎来到轮子哥app');

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(64) DEFAULT NULL,
  `oje` decimal(16,4) DEFAULT NULL,
  `time` varchar(32) DEFAULT NULL,
  `tradeNo` varchar(64) DEFAULT NULL,
  `paySource` varchar(16) DEFAULT NULL,
  `body` varchar(64) DEFAULT NULL,
  `mode` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES ('41', '63a2b3336bba3', '6.6600', '2022-12-21 15:18:19', '2022122122001419091413743237', '321***@qq.com', '6152212', 'authentication');

-- ----------------------------
-- Table structure for privatekey
-- ----------------------------
DROP TABLE IF EXISTS `privatekey`;
CREATE TABLE `privatekey` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `keyValue` mediumtext NOT NULL,
  `tip` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of privatekey
-- ----------------------------
INSERT INTO `privatekey` VALUES ('1', 'phone', '', '阿里云手机号验证私钥');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `count` decimal(16,4) DEFAULT NULL,
  `time` varchar(32) DEFAULT NULL,
  `message` varchar(32) DEFAULT NULL,
  `source` varchar(32) DEFAULT NULL,
  `account` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('54', '-10.0000', '2022-12-17/20:59:27', '任务消耗', '任务包', '123456');
INSERT INTO `record` VALUES ('55', '-100.0000', '2022-12-17/20:59:29', '任务消耗', '任务包', '123456');
INSERT INTO `record` VALUES ('56', '4.5300', '2022-12-18/00:35:59', '每日任务', '任务包', '123456');
INSERT INTO `record` VALUES ('57', '0.2265', '2022-12-18/00:35:59', '邀请奖励', '用户70b461', '111111');
INSERT INTO `record` VALUES ('58', '-10.0000', '2022-12-21/15:11:05', '任务消耗', '任务包', '123456');
INSERT INTO `record` VALUES ('59', '-1000.0000', '2022-12-21/15:11:15', '任务消耗', '任务包', '123456');
INSERT INTO `record` VALUES ('60', '47.5900', '2022-12-22/17:32:26', '每日任务', '任务包', '123456');

-- ----------------------------
-- Table structure for taskpack
-- ----------------------------
DROP TABLE IF EXISTS `taskpack`;
CREATE TABLE `taskpack` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) DEFAULT NULL,
  `type` int(8) DEFAULT NULL,
  `surplus` int(16) DEFAULT NULL,
  `time` varchar(32) DEFAULT NULL,
  `quota` decimal(16,4) DEFAULT NULL,
  `frequency` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskpack
-- ----------------------------
INSERT INTO `taskpack` VALUES ('60', '123456', '1', '10', '2022-12-22/17:32:26', '0.4000', '28');
INSERT INTO `taskpack` VALUES ('61', '123456', '3', '100', '2022-12-22/17:32:26', '4.1300', '28');
INSERT INTO `taskpack` VALUES ('62', '123456', '1', '10', '2022-12-22/17:32:26', '0.4000', '29');
INSERT INTO `taskpack` VALUES ('63', '123456', '5', '1000', '2022-12-22/17:32:26', '42.6600', '29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `password` varchar(128) NOT NULL,
  `payPassword` varchar(8) DEFAULT NULL,
  `name` varchar(8) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `cardName` varchar(16) DEFAULT NULL,
  `cardId` varchar(18) DEFAULT NULL,
  `ma` varchar(8) NOT NULL,
  `invitation` varchar(8) DEFAULT NULL,
  `dateTime` varchar(32) NOT NULL,
  `balance` decimal(16,4) DEFAULT '20.0000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '123456', '$2a$10$QNDttELouO51PnTSilu4huVDQ1Nt4pmW5nDRYe3CWwiXvpxhTlhrm', '999333', '轮子哥666', 'https://47image.oss-cn-heyuan.aliyuncs.com/head/123456.jpg', '广东省潮州市饶平县', '12323323872', '', '', '70b461', '96e792', '2022-09-24/18:16:07', '999041.1200');
INSERT INTO `user` VALUES ('60', '282282', '$2a$10$sc720TqZBfQw5k0JzVAo6ODboOqYmfe.ug4P.bWflJS2Mh5xwnwRa', null, null, null, null, '10323323872', null, null, 'BA5BB192', '', '2023-01-06/01:00:04', '0.0000');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `versionCode` float(8,2) DEFAULT NULL,
  `choice` varchar(8) DEFAULT NULL,
  `log` mediumtext,
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('1', '1.01', 'false', '修复Bug，\r\n优化用户体验\r\n更多详情关注轮子哥', 'https://47image.oss-cn-heyuan.aliyuncs.com/%E5%85%B1%E4%BA%AB%E6%95%B0%E5%88%9B.apk');
