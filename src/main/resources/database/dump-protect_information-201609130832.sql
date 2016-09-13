-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: protect_information
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

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
-- Table structure for table `login_data`
--

DROP TABLE IF EXISTS `login_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `login_time` int(11) NOT NULL,
  `ip` varchar(100) NOT NULL,
  `status` enum('error','success') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_data`
--

LOCK TABLES `login_data` WRITE;
/*!40000 ALTER TABLE `login_data` DISABLE KEYS */;
INSERT INTO `login_data` VALUES (27,'sadfasdf','adsfadsf',1473595796,'127.0.0.1','error'),(28,'dsafadsf','adsfasdf',1473595825,'127.0.0.1','error'),(29,'dfsadfasd','adsfas',1473595886,'127.0.0.1','error'),(30,'Asdfasd','dsafds',1473596077,'127.0.0.1','error'),(31,'fdsafasd','sdafds',1473596088,'127.0.0.1','error'),(32,'fadsf','fsdafd',1473596133,'127.0.0.1','error'),(33,'ADMIN','-',1473601080,'127.0.0.1','success'),(34,'ADMIN','-',1473601211,'127.0.0.1','success'),(35,'ADMIN','-',1473601243,'127.0.0.1','success'),(36,'ADMIN','-',1473601252,'127.0.0.1','success'),(37,'ADMIN','12345dfs',1473601287,'127.0.0.1','error'),(38,'ADMIN','-',1473601293,'127.0.0.1','success'),(39,'ADMIN','-',1473602414,'127.0.0.1','success'),(40,'ADMIN','-',1473602608,'127.0.0.1','success'),(41,'ADMIN','-',1473602689,'127.0.0.1','success'),(42,'ADMIN','-',1473602865,'127.0.0.1','success'),(43,'ADMIN','-',1473603263,'127.0.0.1','success'),(44,'ADMIN','-',1473603415,'127.0.0.1','success'),(45,'ADMIN','-',1473604638,'127.0.0.1','success'),(46,'ADMIN','-',1473737327,'127.0.0.1','success'),(47,'ADMIN','-',1473737526,'127.0.0.1','success'),(48,'ADMIN','-',1473737837,'127.0.0.1','success'),(49,'ADMIN','-',1473737875,'127.0.0.1','success'),(50,'ADMIN','-',1473738030,'127.0.0.1','success'),(51,'ADMIN','-',1473739269,'127.0.0.1','success'),(52,'ADMIN','-',1473739767,'127.0.0.1','success'),(53,'ADMIN','-',1473739978,'127.0.0.1','success'),(54,'ADMIN','-',1473740236,'127.0.0.1','success'),(55,'ADMIN','-',1473740405,'127.0.0.1','success'),(56,'ADMIN','-',1473741288,'127.0.0.1','success'),(57,'ADMIN','-',1473741350,'127.0.0.1','success'),(58,'ADMIN','-',1473741585,'127.0.0.1','success'),(59,'ADMIN','-',1473742047,'127.0.0.1','success');
/*!40000 ALTER TABLE `login_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(3,'BLOCKED');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `role` (`role`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ADMIN','827ccb0eea8a706c4c34a16891f84e7b',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'protect_information'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-13  8:32:11
