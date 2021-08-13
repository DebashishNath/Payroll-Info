-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: payrollinfo
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mst_department`
--

DROP TABLE IF EXISTS `mst_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_code` varchar(5) NOT NULL,
  `department_name` varchar(25) NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_code_UNIQUE` (`department_code`),
  UNIQUE KEY `department_name_UNIQUE` (`department_name`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_department`
--

LOCK TABLES `mst_department` WRITE;
/*!40000 ALTER TABLE `mst_department` DISABLE KEYS */;
INSERT INTO `mst_department` VALUES (1,'D001','Software Development'),(2,'D002','Human Resorce'),(3,'D003','Accounts'),(4,'D004','dept-4'),(5,'D005','dept-5'),(7,'D006','dept-6'),(10,'D007','Dept-7'),(11,'D011','DEPT-11'),(12,'DT65','Dept-65'),(14,'D500','DP-500'),(15,'D-15','dEPT-15'),(16,'d-16','DEPT-16'),(17,'D-17','DEPT-17'),(18,'d-66','Dept-66'),(20,'DP-70','Dept-70'),(21,'D-80','Dept-80'),(24,'D-805','Dept-805'),(25,'D-25','Dept-25'),(26,'D-100','Dept-100'),(27,'D-101','Dept-101'),(30,'D-102','Dept-102'),(34,'D-200','DEPT-200'),(35,'D00','N/A'),(36,'D-300','DEPT-300'),(37,'D-400','DEPT-400'),(38,'D-500','Dept-500'),(41,'D-510','Dept-510');
/*!40000 ALTER TABLE `mst_department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-13 11:36:54
