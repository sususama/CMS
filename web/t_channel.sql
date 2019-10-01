/*
Navicat MySQL Data Transfer

Source Server         : mybendi
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-08-22 15:06:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `cid` int(11) NOT NULL, -- 频道id
  `cname` varchar(255) NOT NULL,
  `description` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
