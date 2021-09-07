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
-- Table structure for table `mst_designation`
--

DROP TABLE IF EXISTS `mst_designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_designation` (
  `designation_id` int NOT NULL AUTO_INCREMENT,
  `designation_code` varchar(5) NOT NULL,
  `designation_name` varchar(25) NOT NULL,
  PRIMARY KEY (`designation_id`),
  UNIQUE KEY `UK_DESIG_CODE` (`designation_code`),
  UNIQUE KEY `UK_DESIG_NAME` (`designation_name`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_designation`
--

LOCK TABLES `mst_designation` WRITE;
/*!40000 ALTER TABLE `mst_designation` DISABLE KEYS */;
INSERT INTO `mst_designation` VALUES (1,'D001','Software Engineer'),(2,'D002','Senior Software Engineer'),(3,'D003','Team Lead'),(4,'D004','Project Manager'),(6,'D005','HR Manager'),(8,'D006','Accounts Manager'),(9,'D007','Data Entry Operator'),(18,'D008','Production Manager'),(19,'D009','Desig-09'),(20,'D010','DESIG-10'),(21,'D011','DESIG-11'),(22,'D012','DESIG-12'),(23,'D013','DESIG-13'),(24,'D014','Desig-14'),(25,'DG0','N/A'),(26,'D015','Desig-15'),(29,'D016','Desig-16'),(31,'D017','DESIG-17'),(32,'D018','Desig-18'),(34,'D019','Desig-19');
/*!40000 ALTER TABLE `mst_designation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 15:31:37
