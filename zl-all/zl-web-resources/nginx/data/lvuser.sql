/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : zl

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-06-01 23:42:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `lvuser`
-- ----------------------------
DROP TABLE IF EXISTS `lvuser`;
CREATE TABLE `lvuser` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL COMMENT '登录名',
  `nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `idxcode` varchar(15) DEFAULT NULL COMMENT '靓号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `coin` bigint(11) DEFAULT '0' COMMENT '用户钢镚数量',
  `praise` bigint(11) DEFAULT '0' COMMENT '用户被赞次数',
  `photo` varchar(150) DEFAULT NULL COMMENT '用户头像地址（相对地址）',
  `mail` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '用户电话',
  `qq` varchar(15) DEFAULT NULL COMMENT '用户QQ帐号',
  `flag` int(11) DEFAULT '0' COMMENT '扩展标位',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态，1：启用，2：禁用',
  `thirduser` tinyint(4) DEFAULT '0' COMMENT '是否是第三方用户，0：不是，1：是',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lvuser
-- ----------------------------
INSERT INTO `lvuser` VALUES ('38', 'A12345', 'A12345', '3501060000132', '111111', '0', '0', 'userphotos/system/211.jpg', null, null, null, '0', '1', '0', '2015-02-06 00:00:14');
INSERT INTO `lvuser` VALUES ('39', 'B12345', 'B12345', '5501060003333', '111111', '0', '0', 'userphotos/system/246.jpg', null, null, null, '0', '1', '0', '2015-02-06 00:03:33');
