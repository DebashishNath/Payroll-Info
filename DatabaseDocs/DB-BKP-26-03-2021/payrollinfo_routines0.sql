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
/*!50003 DROP PROCEDURE IF EXISTS `DisplayAllStates` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayAllStates`()
BEGIN
	SELECT s.state_id,s.state_code,s.state_name,
    c.country_id,c.country_code,c.country_name
    FROM mst_state s
    INNER JOIN mst_country c
    ON s.country_id=c.country_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
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
/*!50003 DROP PROCEDURE IF EXISTS `GenerateMonthlyAttendance` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GenerateMonthlyAttendance`(p_month INT,p_year INT,
p_emp_id INT )
BEGIN
	DECLARE p_day INT;
	DECLARE p_start_date date;
    DECLARE p_end_date date;
    DECLARE p_attendance_date date;
    DECLARE p_end_day INT;
    DECLARE p_total_cnt INT;
    DECLARE p_cnt INT;
    
    SET p_start_date=CONCAT(CONCAT(CONCAT(p_year,'-'),p_month),'-01');
    
    IF p_month=1 or p_month=3 or p_month=5 or p_month=7 
		or p_month=8 or p_month=10 or p_month=12
	THEN
		SET p_end_day=31;
	ELSEIF p_month=2
    THEN
		SET p_end_day=28;
	ELSEIF p_month=4 or p_month=6 or p_month=9 or p_month=11
    THEN
		SET p_end_day=30;
	END IF;
    
    DELETE FROM trn_emp_attendance 
    WHERE month=p_month and year=p_year and emp_id=p_emp_id;
    
    SET p_day=1;
   	WHILE p_day <= 31 DO 
		IF p_day=1 THEN
			SET p_attendance_date=p_start_date;
		else
			SET p_attendance_date=date_add(p_attendance_date,INTERVAL 1 DAY);
        END IF;
        INSERT INTO trn_emp_attendance (month,year,emp_id,attendance_date,attendance_type_code,remarks)
        VALUES(p_month,p_year,p_emp_id,p_attendance_date,'P','');
        SET  p_day = p_day + 1;
	 END WHILE;
     SET p_end_date=p_attendance_date;
     
	 UPDATE trn_emp_attendance t,mst_holiday m
     SET t.attendance_type_code='H'
     WHERE t.attendance_type_code='P' 
     AND t.emp_id=p_emp_id
     AND t.attendance_date =m.holiday_date;
      
     UPDATE payrollinfo.trn_emp_attendance t,trn_emp_leave tel
	 SET t.attendance_type_code='L',
		 t.leave_type_code=tel.leave_type_code 
	 WHERE t.emp_id=p_emp_id AND t.emp_id=tel.emp_id
	 AND t.attendance_date>=tel.from_date
	 AND t.attendance_date<=tel.to_date;
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

-- Dump completed on 2021-04-01 20:13:23
