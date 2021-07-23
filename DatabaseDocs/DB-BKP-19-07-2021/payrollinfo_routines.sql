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
/*!50003 DROP PROCEDURE IF EXISTS `DisplayMasterData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayMasterData`(formName VARCHAR(20))
BEGIN
	IF(formName = 'Department') 
    THEN
		SELECT department_id AS masterId,department_code AS masterCode,department_name AS masterName
        FROM mst_department
        WHERE trim(department_name)!='N/A';
	END IF;
    
    IF(formName = 'Category') 
	THEN
		SELECT category_id AS masterId,category_code AS masterCode,category_name AS masterName
        FROM mst_category
        WHERE trim(category_name)!='N/A';
    END IF;
    
    IF(formName = 'Designation') 
	THEN
		SELECT designation_id AS masterId,designation_code AS masterCode,designation_name AS masterName
        FROM mst_designation
        WHERE trim(designation_name)!='N/A';
    END IF;
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
/*!50003 DROP PROCEDURE IF EXISTS `GeneratePaySlip` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GeneratePaySlip`(IN p_month INT,IN p_year INT,
				IN p_salary_date Date,OUT p_return_message VARCHAR(500))
proc_label:BEGIN
	DECLARE p_cnt INT;
	DECLARE p_return_code INT;
    DECLARE p_company_id INT;
    DECLARE p_no_earn_cols INT;
    DECLARE p_no_ded_cols INT;
    DECLARE p_pay_sheet_columns VARCHAR(2096);
	
    DECLARE exit handler for sqlexception
	BEGIN
		GET DIAGNOSTICS CONDITION 1
		p_return_code=RETURNED_SQLSTATE,p_return_message=MESSAGE_TEXT;
        /*SET p_return_message=CONCAT('Payslip generation failed');*/
		SET p_return_message=CONCAT(CONCAT(p_return_code,'-'),p_return_message);
		ROLLBACK;
	END;
		   
	DECLARE exit handler for sqlwarning
	BEGIN
		GET DIAGNOSTICS CONDITION 1
		p_return_code=RETURNED_SQLSTATE,p_return_message=MESSAGE_TEXT;
        /*SET p_return_message=CONCAT('Payslip generation failed');*/
        SET p_return_message=CONCAT(CONCAT(p_return_code,'-'),p_return_message);
		ROLLBACK;
	END;
    
    START TRANSACTION;
	SET SQL_SAFE_UPDATES = 0;
    
    SET p_cnt=(SELECT COUNT(DISTINCT emp_id) FROM trn_emp_attendance
				WHERE month=p_month AND year=p_year);
	IF p_cnt=0 THEN
		SET p_return_code=5;
        SET p_return_message=CONCAT(CONCAT('Employee attendance has not been generated for ',DATE_FORMAT(p_salary_date, "%M %Y")),', hence payslip generation is not possible');
        SET p_return_message=CONCAT(CONCAT(p_return_code,'-'),p_return_message);
        LEAVE proc_label;
    END IF;
	   
    DELETE FROM trn_monthly_emp_salary_summary
    WHERE month=p_month AND year=p_year;
    
    DELETE FROM trn_monthly_emp_salary_details
    WHERE month=p_month AND year=p_year;
    
	INSERT INTO trn_monthly_emp_salary_details 
    SELECT p_month,p_year,tess.emp_id,
		   tess.earn_ded_id,tess.earn_ded_amount
    FROM trn_emp_salary_structure tess
    INNER JOIN 
    (SELECT DISTINCT emp_id FROM trn_emp_attendance 
    WHERE month=p_month AND year=p_year) tea
    ON tess.emp_id=tea.emp_id;
    
    set p_company_id=(SELECT company_id FROM payrollinfo.mst_company limit 1);
    
    INSERT INTO trn_monthly_emp_salary_summary
    (month,year,emp_id,company_id,salary_date,total_earn_amount,
    total_ded_amount,net_amount,pay_sheet_values,pay_sheet_columns,remarks)
    SELECT tsd.month,tsd.year,tsd.emp_id,p_company_id,p_salary_date,
	SUM(tsd.earn_ded_amount),0,0,'','',''
	FROM trn_monthly_emp_salary_details tsd
	INNER JOIN mst_earn_ded_components mc
	ON tsd.earn_ded_id=mc.earn_ded_id and mc.earn_ded_type='E'
    WHERE tsd.month=p_month AND tsd.year=p_year
	group by tsd.month,tsd.year,tsd.emp_id;

	UPDATE trn_monthly_emp_salary_summary tsd1
    SET tsd1.total_ded_amount=(SELECT 
    CASE WHEN SUM(tsd.earn_ded_amount) IS NULL THEN 0
    ELSE SUM(tsd.earn_ded_amount) END
    FROM trn_monthly_emp_salary_details tsd
    INNER JOIN mst_earn_ded_components mc
	ON tsd.earn_ded_id=mc.earn_ded_id and mc.earn_ded_type='D'
    WHERE tsd.emp_id=tsd1.emp_id AND tsd.month=p_month 
    AND tsd.year=p_year
	group by tsd.month,tsd.year,tsd.emp_id),
    tsd1.net_amount=tsd1.total_earn_amount - 
    CASE WHEN tsd1.total_ded_amount IS NULL THEN 0
    ELSE tsd1.total_ded_amount END
    WHERE tsd1.month=p_month AND tsd1.year=p_year;
    
	UPDATE trn_monthly_emp_salary_summary tsd1
	SET tsd1.total_earn_amount=0
    WHERE tsd1.total_earn_amount IS NULL;
    
	UPDATE trn_monthly_emp_salary_summary tsd1
    SET tsd1.total_ded_amount=0
    WHERE tsd1.total_ded_amount IS NULL;
    
    SET p_no_earn_cols=(SELECT COUNT(*) AS no_earn_cols FROM mst_earn_ded_components
						WHERE earn_ded_type='E');
	SET p_no_ded_cols=(SELECT COUNT(*) AS no_earn_cols FROM mst_earn_ded_components
						WHERE earn_ded_type='D');
    
    UPDATE trn_monthly_emp_salary_summary tsd1
    SET tsd1.pay_sheet_values=(SELECT 
			JSON_ARRAYAGG(JSON_OBJECT('earn_ded_id', tesd.earn_ded_id,
				'amount', tesd.earn_ded_amount )) AS Emp_Salary
			from payrollinfo.trn_monthly_emp_salary_details tesd
            WHERE tsd1.emp_id=tesd.emp_id AND
				  tsd1.month=tesd.month AND tsd1.year=tesd.year)
    WHERE tsd1.month=p_month AND tsd1.year=p_year;
    
    SET p_pay_sheet_columns=(SELECT JSON_ARRAYAGG(JSON_OBJECT('earn_ded_id', medc.earn_ded_id,
			'earn_ded_code',medc.earn_ded_code,
            'earn_ded_type',medc.earn_ded_type,
			'earn_ded_priority',medc.earn_ded_priority,
            'no_earn_cols',p_no_earn_cols,
			'no_ded_cols',p_no_ded_cols)) AS EarnDedComponents
		FROM payrollinfo.mst_earn_ded_components medc);
        
    UPDATE trn_monthly_emp_salary_summary tsd1
    SET tsd1.pay_sheet_columns=p_pay_sheet_columns
    WHERE tsd1.month=p_month AND tsd1.year=p_year;

	UPDATE trn_monthly_emp_salary_summary tsd1
    SET tsd1.emp_detail_values=(
		SELECT JSON_ARRAYAGG(JSON_OBJECT(
			'emp_code',me.emp_code,
            'emp_name',	CONCAT(me.emp_first_name,
            CASE WHEN LENGTH(me.emp_middle_name)=0 THEN 
            CONCAT(' ',me.emp_last_name)
            ELSE 
            CONCAT(CONCAT(CONCAT(' ',me.emp_middle_name),' '),me.emp_last_name)
            END))) AS EmpDetails
		FROM payrollinfo.mst_employee me
        WHERE tsd1.emp_id=me.emp_id)
    WHERE tsd1.month=p_month AND tsd1.year=p_year;

    SET p_return_code=0;
    SET p_return_message=CONCAT('Payslip has been generated for ',DATE_FORMAT(p_salary_date, "%M %Y"));
    SET p_return_message=CONCAT(CONCAT(p_return_code,'-'),p_return_message);
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetHolidayMonthWise` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHolidayMonthWise`(p_month Int,p_year Int)
BEGIN
	SELECT 	Holiday_Id,
			Holiday_Code,
            Holiday_Name,
            Holiday_Date
    FROM payrollinfo.mst_holiday
	WHERE month(holiday_date)=p_month 
    and year(holiday_date)=p_year;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ManageMasterData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ManageMasterData`(formName VARCHAR(20),masterId INT,masterCode VARCHAR(5),masterName VARCHAR(25))
BEGIN
	IF(formName = 'Department') 
		AND NOT EXISTS (SELECT 1 FROM mst_department WHERE department_id=masterId)
        THEN
		BEGIN
			INSERT INTO mst_department(department_code,department_name)
			VALUES(masterCode,masterName);
		END;
	ELSE
		BEGIN
			UPDATE mst_department
            SET department_code=masterCode,department_name=masterName
            WHERE department_id=masterId;
        END;
    END IF;
    
    IF(formName = 'Category') 
		AND NOT EXISTS (SELECT 1 FROM mst_category WHERE category_id=masterId)
        THEN
		BEGIN
			INSERT INTO mst_category(category_code,category_name)
			VALUES(masterCode,masterName);
		END;
	ELSE
		BEGIN
			UPDATE mst_category
            SET category_code=masterCode,category_name=masterName
            WHERE category_id=masterId;
        END;
    END IF;
    
    IF(formName = 'Designation') 
		AND NOT EXISTS (SELECT 1 FROM mst_designation WHERE designation_id=masterId)
        THEN
		BEGIN
			INSERT INTO mst_designation(designation_code,designation_name)
			VALUES(masterCode,masterName);
		END;
	ELSE
		BEGIN
			UPDATE mst_designation
            SET designation_code=masterCode,
				designation_name=masterName
            WHERE designation_id=masterId;
        END;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ManagePTAXMonthly` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ManagePTAXMonthly`(p_month INT,p_year INT)
BEGIN
	DECLARE p_date DATE;
    DECLARE pMonth VARCHAR(2);
    DECLARE p_fin_year_id INT;
       
    IF p_month<=9 THEN
    BEGIN
		SET pMonth=CONCAT('0',p_month);
    END;
    ELSE
		SET pMonth=p_month;
    END IF;
    SET p_date =CONCAT(CONCAT(CONCAT(CONCAT(p_year,'-'),p_month),'-'),'01');
    
    SET SQL_SAFE_UPDATES = 0;
    
    SET p_fin_year_id=(SELECT fin_year_id FROM payrollinfo.mst_fin_year 
				WHERE p_date BETWEEN fin_year_start and fin_year_end);
	
	DELETE FROM trn_ptax_monthly
    WHERE month=p_month AND year=p_year;
	
    INSERT INTO trn_ptax_monthly
    SELECT 	tpss.month,tpss.year,tpss.ptax_slab_id,
		tpss.no_employees,tpss.ptax_rate * tpss.no_employees AS ptax_Amount
	FROM
	(SELECT p_month as month,p_year as year,tps.ptax_slab_id,tps.ptax_rate,
		(SELECT COUNT(tmesd.emp_id) 
			FROM trn_monthly_emp_salary_details tmesd
			INNER JOIN mst_earn_ded_components medc
			ON tmesd.earn_ded_id=medc.earn_ded_id
			INNER JOIN trn_monthly_emp_salary_summary tmess
			ON tmesd.month=tmess.month AND tmesd.year=tmess.year AND tmesd.emp_id=tmess.emp_id
			WHERE tmesd.month=p_month AND tmesd.year=p_year AND tps.fin_year_id=p_fin_year_id
            AND medc.earn_ded_tagname='PTAX'
			AND tmess.net_amount>tps.ptax_start_range AND tmess.net_amount<=tps.ptax_end_range
		) AS no_employees
	FROM trn_ptax_slab tps) AS tpss;
    
    SELECT 	month,year,ptax_slab_id,
			no_employees,ptax_Amount 
    FROM trn_ptax_monthly
    WHERE month=p_month AND year=p_year;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PrintSinglePaySlip` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PrintSinglePaySlip`(p_user_id INT,p_month INT,p_year INT,p_emp_id INT)
BEGIN
	SET SQL_SAFE_UPDATES = 0;
	DELETE FROM trn_print_pay_slip;
	INSERT INTO trn_print_pay_slip(user_id,company_id,company_name,
		company_address1,company_address2,district_name,company_pin,
		company_contact_number,company_email,company_tan_no,
		pay_year,pay_month,emp_id,emp_code,emp_name,earn_ded_id,
		earn_ded_code,earn_ded_name,earn_ded_type,
		earn_ded_priority,earn_ded_amount,salary_date,
		total_earn_amount,total_ded_amount,
		net_amount,remarks)
    SELECT p_user_id,
    tmess.company_id,mc.company_name,mc.company_address1,
    mc.company_address2,md.district_name,mc.company_pin,
    mc.company_contact_number,mc.company_email,mc.company_tan_no,
    tmes.year,tmes.month, tmes.emp_id,me.emp_code,
	concat(me.emp_first_name,' ',
	CASE WHEN LENGTH(me.emp_middle_name)>0 THEN CONCAT(me.emp_middle_name,' ') 
	ELSE me.emp_middle_name END,me.emp_last_name) AS emp_name,
	tmes.earn_ded_id,medc.earn_ded_code,medc.earn_ded_name,medc.earn_ded_type,
	medc.earn_ded_priority,tmes.earn_ded_amount,tmess.salary_date,
    tmess.total_earn_amount,tmess.total_ded_amount,
    tmess.net_amount,tmess.remarks
	FROM payrollinfo.trn_monthly_emp_salary_details tmes
	INNER JOIN payrollinfo.trn_emp_salary_structure tess
	ON tmes.emp_id=tess.emp_id AND tmes.earn_ded_id=tess.earn_ded_id
	INNER JOIN mst_employee me 
	ON tmes.emp_id=me.emp_id
	INNER JOIN mst_earn_ded_components medc
	ON tess.earn_ded_id=medc.earn_ded_id
	INNER JOIN trn_monthly_emp_salary_summary tmess ON
	tmes.month=tmess.month AND tmes.year=tmess.year and tmes.emp_id=tmess.emp_id
	INNER JOIN mst_company mc ON
	tmess.company_id=mc.company_id
    INNER JOIN mst_district md ON
	mc.company_district_id=md.district_id
	WHERE tmes.emp_id=p_emp_id AND tmes.month=p_month AND tmes.year=p_year
	ORDER BY medc.earn_ded_type DESC,
	medc.earn_ded_priority;
    
    SELECT * FROM trn_print_pay_slip;
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

-- Dump completed on 2021-07-23 11:25:36
