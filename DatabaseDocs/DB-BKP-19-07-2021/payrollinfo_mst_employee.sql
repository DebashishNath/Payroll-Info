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
-- Table structure for table `mst_employee`
--

DROP TABLE IF EXISTS `mst_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_code` varchar(10) NOT NULL,
  `emp_first_name` varchar(25) NOT NULL,
  `emp_middle_name` varchar(25) DEFAULT NULL,
  `emp_last_name` varchar(25) NOT NULL,
  `emp_image_path` varchar(1000) DEFAULT NULL,
  `gender` varchar(1) NOT NULL,
  `dob` date NOT NULL,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  `district_id` int NOT NULL,
  `pin` varchar(10) NOT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `emp_doj` date DEFAULT NULL,
  `aadhar_no` varchar(15) DEFAULT NULL,
  `PAN_No` varchar(15) DEFAULT NULL,
  `PF_No` varchar(30) DEFAULT NULL,
  `ESI_No` varchar(30) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `designation_id` int DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `UK_EMP_CODE` (`emp_code`),
  KEY `FK_EMP_LOCATION_ID` (`location_id`),
  KEY `FK_EMP_DISTRICT_ID` (`district_id`),
  KEY `FK_EMP_CATEGORY_ID` (`category_id`),
  KEY `FK_EMP_DEPARTMENT_ID` (`department_id`),
  KEY `FK_EMP_DESIGNATION_ID` (`designation_id`),
  CONSTRAINT `FK_EMP_CATEGORY_ID` FOREIGN KEY (`category_id`) REFERENCES `mst_category` (`category_id`),
  CONSTRAINT `FK_EMP_DEPARTMENT_ID` FOREIGN KEY (`department_id`) REFERENCES `mst_department` (`department_id`),
  CONSTRAINT `FK_EMP_DESIGNATION_ID` FOREIGN KEY (`designation_id`) REFERENCES `mst_designation` (`designation_id`),
  CONSTRAINT `FK_EMP_DISTRICT_ID` FOREIGN KEY (`district_id`) REFERENCES `mst_district` (`district_id`),
  CONSTRAINT `FK_EMP_LOCATION_ID` FOREIGN KEY (`location_id`) REFERENCES `mst_location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_employee`
--

LOCK TABLES `mst_employee` WRITE;
/*!40000 ALTER TABLE `mst_employee` DISABLE KEYS */;
INSERT INTO `mst_employee` VALUES (1,'E001','Alok','','Chatterjee','d:\\images\\alok','M','1970-12-12','9/2 R.L. Road,Kamalgazi','Sonarpur',NULL,1,'700084','8976390321','alok.chatterjee@gmail.com','2020-02-01','7890 5678 3215','','','',1,1,1),(2,'E002','Nihar','Ranjan','Pramanik','d:\\images\\nihar','M','1972-02-04','9/2 Garia Road','Sonarpur',NULL,1,'600034','35325325','Nihar.pramanik@gmail.com',NULL,NULL,NULL,NULL,NULL,1,1,1),(3,'E003','Proloy','Kumar','Sen','','M','1970-01-05','11/6 KL Road','Garia',NULL,8,'7800422','980762341','proloy.sen@gmail.com',NULL,'3452 9801 7643','','','',1,1,1),(4,'E004','Suresh','','Mehata','d:\\images\\Suresh','M','1960-02-07','112/6 A.K.L Road','kHIRPUR',NULL,1,'355008','908761234','suresh.mehata@gmail.com','2021-01-11','','','','',1,1,18),(5,'E005','Brijesh','','Patel','d:\\images\\Brijesh','M','1962-03-04','12/9 PL Road','Jalpaiguri',NULL,1,'53535545','5745757','brijesh.patel@gmail.com',NULL,'57567 5671 0965','DRTY09873','PKT56231HJ','ESI90453G',50,3,2),(6,'E006','Mithilesh','Kumar','Tiwary','','M','1972-01-05','12/9 KHL Road','Asansol',NULL,3,'35237266','56846422','mithilesh224@gmail.com',NULL,'','','','',49,35,1),(8,'E007','Rajesh','Kumar','Chauhan','','M','1978-06-02','15/19 G.H Road','Alipurduar',NULL,8,'33590612','12099-87342','rajesh.chauhan@gmail.com',NULL,'9826 7650 6750','KJLP9087','PKY70034','ESI69032',1,1,2),(9,'E008','Amitabha','','Bose','d:\\images\\amitabha','M','1970-07-09','161/19 K.L Road','Jalpaiguri',NULL,1,'33352533','12567-67521','amitabha.Bose@gmail.com',NULL,'5752 0983 4444','PLKI092','LRT02226883','24443PO',1,1,2),(11,'E009','Tarit','','Topdar','d:\\images\\Tarit','M','1980-06-11','D.S. Road','Udaigiri',NULL,1,'3353213','98054-83221','Tarit0987@gmail.com',NULL,'5673 0933 3490','PLKU0987','LOPU22268','29843PO',1,2,2),(12,'E010','Jasprit','','Bumra','d:\\images\\jasprit','M','1978-05-09','M.P. Road','Udaigiri',NULL,1,'98076221','58024-23421','BUMRA678@gmail.com',NULL,'5227 4533 5290','P3437','L23568','22243PO',1,3,3),(15,'E011','Rohit','Kumar','Sharma','d:\\images\\rohit','M','1990-05-10','B.P. Road','Pondicherry',NULL,1,'23093257','78390-23421','rohit@gmail.com',NULL,'5490 4554 5330','P33r3437','L23568','22243PO',1,2,2),(17,'E012','Jishu','Kumar','Sengupta','d:\\images\\jishu','M','1980-08-11','T.P. Road','Hyderabad',NULL,1,'23093257','78390-23421','rohit@gmail.com',NULL,'5490 4554 5330','KPLO9876','PFKY7832','ESI0923',1,1,3),(18,'E013','Eknath','','Solkar','','M','1980-01-20','2/3 B.M. Road','Near Ultadanga',NULL,8,'700094','6754321290','ek@gmail.com',NULL,'9876 2314 8056','DFOPN9087V','PKY0001234','ESI092142',52,11,2),(19,'E014','Dipika','','Padukone','','F','1989-04-01','44/1 T.B. Road','EM Byepass',NULL,13,'700093','8017638264','dp@gmail.com',NULL,'6710 9084 7731','PLKJ09873','PKY234101','ESI083213',2,4,2),(20,'E015','Ron','','Malhotra','','M','1970-05-07','56/1 M.B. Road','Near Gul karkhana factory',NULL,1,'700085','1234-098712','rm@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'E016','Manotosh','Kumar','Ghosh','d:\\images\\manotosh','M','1978-08-12','K.P. Road','Bhuveneshwar',NULL,1,'3553222','78987-34218','manotosh@gmail.com',NULL,'5490 4554 5330','KJU8760','SFQEQ352','ESI0623',1,1,3),(23,'E017','Karanjit','Kumar','Singh','','M','1974-06-02','B.P. Road','Mumbai',NULL,15,'34560987','78987-34218','manotosh@gmail.com',NULL,'5340 1254 3421','K2248760','PF-02221','ESI0623',1,1,3),(24,'E018','Ipsita','','Ghosh','','F','1980-06-01','P-1, Diamond Harbour Road,','Falta',NULL,3,'700034','221422455','debasis.nath@gmail.com',NULL,'','','','',1,2,25),(25,'E000','N/A','','   ','','M','1990-03-02','gdsg','sgsg',NULL,1,'225533','7975942642','anodiam.dn@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'ad','sad','','safdsaf','','M','1981-02-02','zczxc','',NULL,1,'253','fasf','sfsaf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `mst_employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19 16:11:23
