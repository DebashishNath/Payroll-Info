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
  CONSTRAINT `trn_emp_leave_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `mst_employee` (`emp_id`),
  CONSTRAINT `trn_emp_leave_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `mst_employee` (`emp_id`),
  CONSTRAINT `trn_emp_leave_leave_type_code` FOREIGN KEY (`leave_type_code`) REFERENCES `mst_leave_type` (`leave_type_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_emp_leave`
--

LOCK TABLES `trn_emp_leave` WRITE;
/*!40000 ALTER TABLE `trn_emp_leave` DISABLE KEYS */;
INSERT INTO `trn_emp_leave` VALUES (1,2,'SFSAF','2021-07-06','EL','2021-07-07','2021-07-09','ASFSAF','Y',5,'SFSFSF'),(2,2,'SFSFSF','2021-07-06','EL','2021-07-15','2021-07-19','SAFSAF-\ngvgd\n\n\ngsddhh','Y',6,'zvxzvxzzbz-bbxc'),(3,2,'AAFAG','2021-07-07','SL','2021-07-21','2021-07-24','GSDGDG','Y',18,'GSDGDGD'),(4,8,'safa','2021-06-01','CL','2021-06-02','2021-06-04','aagaggd','Y',18,''),(5,8,'gdhdf','2021-06-18','EL','2021-07-20','2021-07-23','wtwtwe','Y',12,''),(6,5,'E005/1','2021-03-10','EL','2021-03-16','2021-03-18','ABCD','Y',11,'DAD');
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

-- Dump completed on 2021-07-30  1:01:59
