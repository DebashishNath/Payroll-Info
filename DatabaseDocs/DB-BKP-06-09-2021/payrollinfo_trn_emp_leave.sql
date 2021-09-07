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
-- Table structure for table `trn_emp_leave`
--

DROP TABLE IF EXISTS `trn_emp_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_emp_leave` (
  `leave_application_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `leave_application_no` varchar(15) NOT NULL,
  `leave_application_date` date NOT NULL,
  `leave_type_code` varchar(2) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `leave_application_details` varchar(1024) NOT NULL,
  `is_approved` varchar(1) NOT NULL,
  `approved_by` int DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`leave_application_id`),
  UNIQUE KEY `leave_application_no_UNIQUE` (`leave_application_no`),
  KEY `trn_emp_leave_emp_id_idx` (`emp_id`),
  KEY `trn_emp_leave_leave_type_code_idx` (`leave_type_code`),
  KEY `trn_emp_leave_approved_by_idx` (`approved_by`),
  CONSTRAINT `trn_emp_leave_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `mst_employee` (`emp_id`),
  CONSTRAINT `trn_emp_leave_leave_type_code` FOREIGN KEY (`leave_type_code`) REFERENCES `mst_leave_type` (`leave_type_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_emp_leave`
--

LOCK TABLES `trn_emp_leave` WRITE;
/*!40000 ALTER TABLE `trn_emp_leave` DISABLE KEYS */;
INSERT INTO `trn_emp_leave` VALUES (1,2,'07/001','2021-07-06','EL','2021-07-07','2021-07-09','Dear Sir,\n   I want to take earn leave for 3 days.\n   Please approve it.\n\nThanks & regards,\nNihar Ranjan Pramanik','Y',5,'Leave Granted'),(2,2,'A/E002/07-1','2021-07-06','EL','2021-07-15','2021-07-19','Dear Sir.....I want to take leave\n\n\n\ngsddhh','Y',6,'Leave granted'),(3,2,'AAFAG','2021-07-07','SL','2021-07-21','2021-07-24','GSDGDG-afafsfsaf','Y',18,'GSDGDGD'),(4,8,'safa','2021-06-01','CL','2021-06-02','2021-06-04','aagaggd','Y',18,''),(5,8,'gdhdf','2021-06-18','EL','2021-07-20','2021-07-23','wtwtwe','Y',12,''),(6,5,'E005/1','2021-03-10','EL','2021-03-16','2021-03-18','ABCD','Y',11,'DAD'),(8,2,'A/E002/1','2021-04-01','EL','2021-04-05','2021-04-07','aff-sfassaga','Y',11,'sfsafsafsfsfsf'),(9,3,'E/04/1','2021-04-02','CL','2021-04-13','2021-04-16','Dear Sir, I want 4 days leave.','Y',18,'Leave granted'),(10,11,'004-1','2021-04-02','CL','2021-04-06','2021-04-08','Dear Sir','Y',6,'Leave Guranted'),(11,11,'05/0001','2021-05-01','CL','2021-05-04','2021-05-08','Dear Sir/Madam, I want to take leave for 5 days','Y',19,'Leave Guranted');
/*!40000 ALTER TABLE `trn_emp_leave` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 12:51:05
