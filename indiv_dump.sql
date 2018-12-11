-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: individual
-- ------------------------------------------------------
-- Server version	5.7.24-log

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `date_sent` datetime NOT NULL,
  `sentBY` varchar(45) DEFAULT NULL,
  `sentTO` varchar(45) DEFAULT NULL,
  `isREAD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `sentby_fk_idx` (`sentBY`),
  KEY `sentto_fk_idx` (`sentTO`),
  CONSTRAINT `sentby_fk` FOREIGN KEY (`sentBY`) REFERENCES `users` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `sentto_fk` FOREIGN KEY (`sentTO`) REFERENCES `users` (`username`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Cinema','LEts go to the cinema','2018-11-19 03:15:35','akis','takis','1'),(2,'sea','lets go to the sea','2018-11-19 03:15:42','akis','lakis','1'),(3,'cinema','pame cinema aurio?','2018-11-19 03:16:59','akis','makis','1'),(4,'winner','nikisa sto kino','2018-11-19 03:17:22','akis','lakis','1'),(5,'123test','pare me thlefono','2018-11-19 03:17:41','akis','lakis','1'),(6,'titlos','123123123','2018-11-19 03:18:03','takis','lakis','1'),(7,'justTITLE','123456','2018-11-19 03:18:16','takis','lakis','1'),(8,'asdasd','gogogogo','2018-11-19 03:18:28','takis','sakis','1'),(9,'xaxa','pare me til na sou pw ','2018-11-19 03:24:05','lakis','akis','1'),(10,'akous','123123123123','2018-11-19 03:25:41','akis','makis','0'),(11,'walk','lets go for a walk','2018-11-19 03:27:21','akis','mary','0');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin','admin'),('akis','123456','B'),('froyd','123456','B'),('jack','123456','B'),('james','123456','B'),('john','123456','B'),('lakis','123456','C'),('makis','123456','B'),('mary','123456','C'),('sakis','123456','C'),('takis','123456','C');
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

-- Dump completed on 2018-11-19  3:30:49
