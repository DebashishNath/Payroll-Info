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
-- Table structure for table `trn_emp_leave_entitlement`
--

DROP TABLE IF EXISTS `trn_emp_leave_entitlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_emp_leave_entitlement` (
  `emp_leave_entitlement_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `fin_year_id` int NOT NULL,
  `leave_type_code` varchar(2) NOT NULL,
  `no_leaves` decimal(5,2) NOT NULL,
  `carry_forward_leaves` decimal(5,2) NOT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_leave_entitlement_id`),
  KEY `fk_leave_entitlement_emp_id_idx` (`emp_id`),
  KEY `fk_leave_entitlement_fin_year_id_idx` (`fin_year_id`),
  KEY `fk_leave_entitlement_leave_type_code_idx` (`leave_type_code`),
  CONSTRAINT `fk_leave_entitlement_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `mst_employee` (`emp_id`),
  CONSTRAINT `fk_leave_entitlement_fin_year_id` FOREIGN KEY (`fin_year_id`) REFERENCES `mst_fin_year` (`fin_year_id`),
  CONSTRAINT `fk_leave_entitlement_leave_type_code` FOREIGN KEY (`leave_type_code`) REFERENCES `mst_leave_type` (`leave_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_emp_leave_entitlement`
--

LOCK TABLES `trn_emp_leave_entitlement` WRITE;
/*!40000 ALTER TABLE `trn_emp_leave_entitlement` DISABLE KEYS */;
/*!40000 ALTER TABLE `trn_emp_leave_entitlement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 15:31:35
