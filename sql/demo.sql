#
# Structure for table "diit_role"
#

DROP TABLE IF EXISTS `diit_role`;
CREATE TABLE `diit_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `num` bigint(20) DEFAULT NULL COMMENT '序号',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "diit_role"
#

INSERT INTO `diit_role` VALUES (1,0,'a','2018-04-20 16:32:58','a'),(2,0,'b','2018-04-20 16:33:02','b');

#
# Structure for table "diit_user"
#

DROP TABLE IF EXISTS `diit_user`;
CREATE TABLE `diit_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `account` varchar(50) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` smallint(2) DEFAULT NULL COMMENT '性别(1:男 2:女)',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `status` smallint(2) DEFAULT NULL COMMENT '状态(1:启用 2:冻结 3:删除)',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "diit_user"
#

INSERT INTO `diit_user` VALUES (1,NULL,'admin','c4ca4238a0b923820dcc509a6f75849b','管理员','2010-10-10 00:00:00',1,'shaowei3@staff.weibo.com','15810340311',1,'2018-04-20 00:00:00','管理员'),(2,NULL,'a','c4ca4238a0b923820dcc509a6f75849b','a','2018-04-05 00:00:00',1,'a@a.com','a',1,'2018-04-20 16:30:36','a'),(3,NULL,'b','c4ca4238a0b923820dcc509a6f75849b','b','2018-04-07 00:00:00',1,'b@b.com','b',1,'2018-04-20 16:31:16','bb'),(6,NULL,'c','c4ca4238a0b923820dcc509a6f75849b','c',NULL,1,'','',1,'2018-04-20 16:39:26','cc');
