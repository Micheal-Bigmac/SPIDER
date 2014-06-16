/*
Navicat MySQL Data Transfer

Source Server         : ROOT
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : webdata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-06-09 02:40:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `unknow`
-- ----------------------------
DROP TABLE IF EXISTS `unknow`;
CREATE TABLE `unknow` (
  `ID` bigint(20) NOT NULL,
  `root` varchar(100) NOT NULL,
  `url` varchar(100) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `topic` varchar(10) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `source` varchar(1000) DEFAULT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `content` text,
  `like_count` int(11) DEFAULT NULL,
  `hate_count` int(11) DEFAULT NULL,
  `click_count` int(11) DEFAULT NULL,
  `share_count` int(11) DEFAULT NULL,
  `favor_count` int(11) DEFAULT NULL,
  `reply_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unknow
-- ----------------------------
