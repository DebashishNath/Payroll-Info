-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: payrollinfo
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
-- Dumping events for database 'payrollinfo'
--

--
-- Dumping routines for database 'payrollinfo'
--
/*!50003 DROP PROCEDURE IF EXISTS `DisplayEmpSalStructure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayEmpSalStructure`(p_emp_id INT)
BEGIN
	SELECT tess.emp_id,me.emp_code,
concat(me.emp_first_name,' ',
CASE WHEN LENGTH(me.emp_middle_name)>0 THEN CONCAT(me.emp_middle_name,' ') 
ELSE me.emp_middle_name END,me.emp_last_name) AS emp_name,
tess.earn_ded_id,medc.earn_ded_code,medc.earn_ded_name,medc.earn_ded_type,
medc.earn_ded_priority,tess.earn_ded_amount
FROM payrollinfo.trn_emp_salary_structure tess
INNER JOIN mst_employee me 
ON tess.emp_id=me.emp_id
INNER JOIN mst_earn_ded_components medc
ON tess.earn_ded_id=medc.earn_ded_id
WHERE tess.emp_id=p_emp_id
ORDER BY medc.earn_ded_type DESC,
medc.earn_ded_priority;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DisplayMonthlyEmpSalary` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayMonthlyEmpSalary`(p_year INT,p_month INT,p_emp_id INT)
BEGIN
	SELECT tmes.year,tmes.month, tmes.emp_id,me.emp_code,
concat(me.emp_first_name,' ',
CASE WHEN LENGTH(me.emp_middle_name)>0 THEN CONCAT(me.emp_middle_name,' ') 
ELSE me.emp_middle_name END,me.emp_last_name) AS emp_name,
tmes.earn_ded_id,medc.earn_ded_code,medc.earn_ded_name,medc.earn_ded_type,
medc.earn_ded_priority,tmes.earn_ded_amount
FROM payrollinfo.trn_monthly_emp_salary tmes
INNER JOIN payrollinfo.trn_emp_salary_structure tess
ON tmes.emp_id=tess.emp_id AND tmes.earn_ded_id=tess.earn_ded_id
INNER JOIN mst_employee me 
ON tmes.emp_id=me.emp_id
INNER JOIN mst_earn_ded_components medc
ON tess.earn_ded_id=medc.earn_ded_id
WHERE tmes.emp_id=p_emp_id AND tmes.month=p_month AND tmes.year=p_year
ORDER BY medc.earn_ded_type DESC,
medc.earn_ded_priority;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-26  3:59:27
