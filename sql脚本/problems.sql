/*
MySQL Data Transfer
Source Host: localhost
Source Database: problems
Target Host: localhost
Target Database: problems
Date: 2019/8/16 10:16:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for question
-- ----------------------------
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `qimg` varchar(300) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0 未解决   1已解决',
  `answer` varchar(500) DEFAULT NULL,
  `animg` varchar(100) DEFAULT NULL,
  `creationtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `question` VALUES ('1', 'Layui是如何实现模块化编程的？', 'Layui', '/image/fb1ee5ab-0b9c-4c85-8f54-339bedb54dc6.png#', '0', '0', '通过layui.define定义和输出模块。\n通过layui.use()加载所需模块。', '/image/fb5ab3b3-b47d-4bbe-b078-8e43c83e136a.png#', '2019-08-02 08:56:27');
INSERT INTO `question` VALUES ('4', 'Layui如何使用日期组件', 'Javascript', null, '0', '0', null, null, '2019-08-19 09:07:57');
INSERT INTO `question` VALUES ('5', 'Layui如何使用分页条件查询', 'java多线程', null, '0', '0', null, null, '2019-08-30 09:08:11');
INSERT INTO `user` VALUES ('78', '王五', '64564');
INSERT INTO `user` VALUES ('123', '张三', '123');
INSERT INTO `user` VALUES ('456', '李四', '456');
