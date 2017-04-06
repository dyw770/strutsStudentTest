# strutsStudentTest

#修改数据库用户名及密码
cn.dyw.utils.ConGet.ConGet()
这个方法中修改

#创建数据库语句:

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(12) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `interesters` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `instructor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

