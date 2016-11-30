-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: lost-pick-spring-hibernate
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` char(20) NOT NULL,
  `password` char(100) NOT NULL,
  `userName` char(50) NOT NULL,
  `academy` char(30) NOT NULL,
  `sex` char(4) NOT NULL,
  `phonecode` char(11) DEFAULT NULL,
  `QQNumber` char(11) DEFAULT NULL,
  `email` char(30) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('001','123456a','我是谁','物理科学与信息工程学院','男','1111','1111111','1111111111@qq.com'),('002','1234567a','谈是啊','','男','12345678901','1111111112','11123@qq.com'),('0021111','12345611','我是谁111','天津','1','1111','1111','11@11.com'),('1','1','11','音乐学院','男','12345678901','1111111111','11123@qq.com'),('11','123456','11','11','1','12131231','123213','123'),('111','11','11','é³ä¹å­¦é¢','ç·','11111111111','1111111111','11123@qq.com'),('1234','2121','211','é³ä¹å­¦é¢','ç·','21','1223445651','11123@qq.com'),('2222','3104d0c1a2784558638d2271f1cad2c5cec5fa5efbcc77892b1fb60413cc9146cae8ed882b39f9ea','2222','æå­¦é¢','å¯','22222222222','11111111','111111@qq.com'),('3333','a194079cd4d2a4a64b36dd25afbaf2fde5eda82cfa85a2226d0c85a2bc6860661c5340c161b37798','33333','音乐学院','保密,密','33333333333','11111111','111111@qq.com'),('3333333','12345611','我是谁111','天津','1','1111','1111','11@11.com'),('444444444','12345611','我是谁111','天津','1','1111','1111','11@11.com'),('99999999','12345611','我是谁111','天津','1','1111','1111','11@11.com'),('chubanse','123456,123456','出版社','音乐学院','保密','12345678901','1111111','1111111111@qq.com'),('henvealf111','123456','你好吗','国际教育交流学','保密','12345678901','122344565','11123@qq.com'),('henvealf123','1234567','henvee','文学院','保密,密','12345678911','11111111','111111@qq.com'),('henvealf333','e10adc3949ba59abbe56e057f20f883e','我的天呐','国际教育交流学院','密','12345678901','11111111','111111@qq.com'),('henvealf444','c7b914a3b3a774dc2120a73900b152596d17f60e39d95173ee95c01aad0061ce82ea806766ba1330','11有有去','政治与公共管理学院','保密,密','11111111111','11111111','111111@qq.com'),('henvealf555','dafb9df710749dc197b3502abb6802226fcbd9edd49df8a892c2d5519f66500e34e122889c6deb44','无名','国际教育交流学','密','12345678901','11111111','111111@qq.com'),('henvealf666','cb15ffeff0ba7df31d2def96feb5d1610740b5646a66a3f52dbbd34e2b0f659c75ac23566c61f3c6','出版社','国际教育交流学院','密','12345678901','11111111','111111@qq.com'),('henvealf9','1234567a','哈哈','音乐学院','男','12345678901','1111111111','11123@qq.com'),('qqqq','a043a7b87b5f231f284ed5c4371cde947987a7ac08a36e69517d2ddbd531037e1fe1c53b49aac71c','qqqq','国际教育交流学院','密','12345678901','11111111','111111@qq.com'),('qwerty','123456','1111','音乐学院','密','11111111111','1111111111','11123@qq.com'),('servlet','123456','jsp','é³ä¹å­¦é¢','å¯','11111111111','1111111111','12312321312312@22.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-08 21:57:22
