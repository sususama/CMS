/*
Navicat MySQL Data Transfer

Source Server         : mybendi
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-08-22 15:06:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article  文章表
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `aid` int(11) NOT NULL,-- 文章表主键
  `title` varchar(255) NOT NULL, --文章标题
  `content` varchar(2550) NOT NULL, --文章内容
  `author` varchar(255) DEFAULT NULL, --文章作者
  `source` varchar(255) DEFAULT NULL, -- 文章来源
  `createTime` datetime DEFAULT NULL, --文章创建时间
  `cid` int(11) DEFAULT NULL, --文章频道所属id
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
