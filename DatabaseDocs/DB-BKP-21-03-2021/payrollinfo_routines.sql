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
/*!50003 DROP PROCEDURE IF EXISTS `ManageEmpSalStruct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ManageEmpSalStruct`(trnType VARCHAR(1),p_emp_salary_structure_id INT,p_emp_id INT,p_earn_ded_id INT,
p_earn_ded_amount DOUBLE,OUT returnMessage VARCHAR(50))
BEGIN
	IF trnType='I' THEN
		BEGIN
		INSERT INTO `payrollinfo`.`trn_emp_salary_structure`
		 (`emp_id`,`earn_ded_id`,`earn_ded_amount`)
		VALUES (p_emp_id,p_earn_ded_id,p_earn_ded_amount);
		END;
		SET returnMessage = 'Record Added Successfully';
	ELSEIF trnType ='U' THEN
        BEGIN
			UPDATE `payrollinfo`.`trn_emp_salary_structure`
			SET emp_id = p_emp_id,
				earn_ded_id = p_earn_ded_id,
				earn_ded_amount = p_earn_ded_amount
			WHERE emp_salary_structure_id = p_emp_salary_structure_id;
        END;
        SET returnMessage = 'Record Updated Successfully';
	ELSEIF trnType='D' THEN
		BEGIN
			DELETE FROM `payrollinfo`.`trn_emp_salary_structure` 
			WHERE emp_salary_structure_id= p_emp_salary_structure_id;
		END;
        SET returnMessage = 'Record Deleted Successfully';
	END IF;
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

-- Dump completed on 2021-03-21  7:15:29
