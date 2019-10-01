/*
Navicat MySQL Data Transfer

Source Server         : mybendi
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-08-21 16:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zs', 'zs', '2019-08-21 16:27:17', '2019-08-21 16:27:20', '张三');
INSERT INTO `t_user` VALUES ('2', 'ls', 'ls', '2019-08-21 16:28:36', '2019-08-21 16:28:38', '李四');
INSERT INTO `t_user` VALUES ('3', 'wmz', 'wmz', '2019-08-21 16:28:36', '2019-08-21 16:28:38', '王麻子');

