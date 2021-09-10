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
-- Table structure for table `mst_menu_item`
--

DROP TABLE IF EXISTS `mst_menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_menu_item` (
  `menu_item_id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL,
  `menu_item_name` varchar(50) NOT NULL,
  `menu_item_label` varchar(50) NOT NULL,
  `menu_item_route` varchar(50) NOT NULL,
  `menu_item_action` varchar(15) NOT NULL,
  PRIMARY KEY (`menu_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_menu_item`
--

LOCK TABLES `mst_menu_item` WRITE;
/*!40000 ALTER TABLE `mst_menu_item` DISABLE KEYS */;
INSERT INTO `mst_menu_item` VALUES (1,1,'company','Company','/company','onClick'),(2,1,'category','Category','/category','onClick'),(3,1,'department','Department','/department','onClick'),(4,1,'designation','Designation','/designation','onClick'),(5,1,'holiday','Holiday','/holiday','onClick'),(6,1,'earndedcomponents','Earn Ded Component','/earndedcomponents','onClick'),(7,1,'employee','Employee','/list_employees','onClick'),(8,2,'salarystructure','Salary Structure','/salarystructure','onClick'),(9,2,'listempleave','Leave','/listempleave','onClick'),(10,2,'generateattendance','Generate Attendance','/generateattendance','onClick'),(11,2,'generatepayslip','Generate Payslip','/generatepayslip','onClick'),(12,3,'ptaxmonthly','PTAX Monthly','/ptaxmonthly','onClick'),(13,3,'attendance','Attendance','/attendance','onClick'),(14,3,'payslip','Payslip','/printpayslip','onClick'),(15,3,'paysheet','Paysheet','/printpaysheet','onClick'),(16,3,'printemployeelist','Employee List','/printemployeelist','onClick'),(17,3,'test','Test','/test','onClick');
/*!40000 ALTER TABLE `mst_menu_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-10 17:18:23
