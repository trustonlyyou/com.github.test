-- 7�� 7���� SQL���α׷���

-- IF...ELSE
DROP PROCEDURE IF EXISTS ifProc; -- ������ �������� �ִٸ� ����
DELIMITER $$
CREATE PROCEDURE ifProc()
BEGIN
  DECLARE var1 INT;  -- var1 ��������
  SET var1 = 100;  -- ������ �� ����

  IF var1 = 100 THEN  -- ���� @var1�� 100�̶��,
	SELECT '100�Դϴ�.';
  ELSE
    SELECT '100�� �ƴմϴ�.';
  END IF;
END $$
DELIMITER ;
CALL ifProc();


DROP PROCEDURE IF EXISTS ifProc2; 
USE employees;

DELIMITER $$
CREATE PROCEDURE ifProc2()
BEGIN
	DECLARE hireDATE DATE; -- �Ի���
	DECLARE curDATE DATE; -- ����
	DECLARE days INT; -- �ٹ��� �ϼ�

	SELECT hire_date INTO hireDate -- hire_date ���� ����� hireDATE�� ����
	   FROM employees.employees
	   WHERE emp_no = 10001;

	SET curDATE = CURRENT_DATE(); -- ���� ��¥
	SET days =  DATEDIFF(curDATE, hireDATE); -- ��¥�� ����, �� ����

	IF (days/365) >= 5 THEN -- 5���� �����ٸ�
		  SELECT CONCAT('�Ի����� ', days, '���̳� �������ϴ�. �����մϴ�!');
	ELSE
		  SELECT '�Ի����� ' + days + '�Ϲۿ� �ȵǾ��׿�. ������ ���ϼ���.' ;
	END IF;
END $$
DELIMITER ;
CALL ifProc2();

-- CASE 
DROP PROCEDURE IF EXISTS ifProc3; 
DELIMITER $$
CREATE PROCEDURE ifProc3()
BEGIN
    DECLARE point INT ;
    DECLARE credit CHAR(1);
    SET point = 77 ;
    
    IF point >= 90 THEN
		SET credit = 'A';
    ELSEIF point >= 80 THEN
		SET credit = 'B';
    ELSEIF point >= 70 THEN
		SET credit = 'C';
    ELSEIF point >= 60 THEN
		SET credit = 'D';
    ELSE
		SET credit = 'F';
    END IF;
    SELECT CONCAT('�������==>', point), CONCAT('����==>', credit);
END $$
DELIMITER ;
CALL ifProc3();


DROP PROCEDURE IF EXISTS caseProc; 
DELIMITER $$
CREATE PROCEDURE caseProc()
BEGIN
    DECLARE point INT ;
    DECLARE credit CHAR(1);
    SET point = 77 ;
    
    CASE 
		WHEN point >= 90 THEN
			SET credit = 'A';
		WHEN point >= 80 THEN
			SET credit = 'B';
		WHEN point >= 70 THEN
			SET credit = 'C';
		WHEN point >= 60 THEN
			SET credit = 'D';
		ELSE
			SET credit = 'F';
    END CASE;
    SELECT CONCAT('�������==>', point), CONCAT('����==>', credit);
END $$
DELIMITER ;
CALL caseProc();


-- <�ǽ� 7> --

USE sqldb;
SELECT userID, SUM(price*amount) AS '�ѱ��ž�'
   FROM buytbl
   GROUP BY userID
   ORDER BY SUM(price*amount) DESC;

SELECT B.userID, U.name, SUM(price*amount) AS '�ѱ��ž�'
   FROM buytbl B
      INNER JOIN usertbl U
         ON B.userID = U.userID
   GROUP BY B.userID, U.name
   ORDER BY SUM(price*amount) DESC;

SELECT B.userID, U.name, SUM(price*amount) AS '�ѱ��ž�'
   FROM buytbl B
      RIGHT OUTER JOIN usertbl U
        ON B.userID = U.userID
   GROUP BY B.userID, U.name
   ORDER BY SUM(price*amount) DESC ;

SELECT U.userID, U.name, SUM(price*amount) AS '�ѱ��ž�'
   FROM buytbl B
      RIGHT OUTER JOIN usertbl U
         ON B.userID = U.userID
   GROUP BY U.userID, U.name
   ORDER BY SUM(price*amount) DESC ;
  
/*  
CASE  
     WHEN (�ѱ��ž�  >= 1500) THEN  '�ֿ����'
     WHEN (�ѱ��ž�  >= 1000) THEN  '�����'
     WHEN (�ѱ��ž� >= 1 ) THEN '�Ϲݰ�'
     ELSE '���ɰ�'
END 
*/

SELECT U.userID, U.name, SUM(price*amount) AS '�ѱ��ž�',
       CASE  
           WHEN (SUM(price*amount)  >= 1500) THEN '�ֿ����'
           WHEN (SUM(price*amount)  >= 1000) THEN '�����'
           WHEN (SUM(price*amount) >= 1 ) THEN '�Ϲݰ�'
           ELSE '���ɰ�'
        END AS '�����'
   FROM buytbl B
      RIGHT OUTER JOIN usertbl U
         ON B.userID = U.userID
   GROUP BY U.userID, U.name 
   ORDER BY sum(price*amount) DESC ;

-- </�ǽ� 7> --

DROP PROCEDURE IF EXISTS whileProc; 
DELIMITER $$
CREATE PROCEDURE whileProc()
BEGIN
	DECLARE i INT; -- 1���� 100���� ������ ����
	DECLARE hap INT; -- ���� ���� ������ ����
    SET i = 1;
    SET hap = 0;

	WHILE (i <= 100) DO
		SET hap = hap + i;  -- hap�� ������ ���� i�� ���ؼ� �ٽ� hap�� ������� �ǹ�
		SET i = i + 1;      -- i�� ������ ���� 1�� ���ؼ� �ٽ� i�� ������� �ǹ�
	END WHILE;

	SELECT hap;   
END $$
DELIMITER ;
CALL whileProc();


DROP PROCEDURE IF EXISTS whileProc2; 
DELIMITER $$
CREATE PROCEDURE whileProc2()
BEGIN
    DECLARE i INT; -- 1���� 100���� ������ ����
    DECLARE hap INT; -- ���� ���� ������ ����
    SET i = 1;
    SET hap = 0;

    myWhile: WHILE (i <= 100) DO  -- While���� label�� ����
	IF (i%7 = 0) THEN
		SET i = i + 1;     
		ITERATE myWhile; -- ������ label������ ���� ��� ����
	END IF;
        
        SET hap = hap + i; 
        IF (hap > 1000) THEN 
		LEAVE myWhile; -- ������ label���� ����. ��, While ����.
	END IF;
        SET i = i + 1;
    END WHILE;

    SELECT hap;   
END $$
DELIMITER ;
CALL whileProc2();

DROP PROCEDURE IF EXISTS errorProc; 
DELIMITER $$
CREATE PROCEDURE errorProc()
BEGIN
    DECLARE CONTINUE HANDLER FOR 1146 SELECT '���̺��� �����Ф�' AS '�޽���';
    SELECT * FROM noTable;  -- noTable�� ����.  
END $$
DELIMITER ;
CALL errorProc();

DROP PROCEDURE IF EXISTS errorProc2; 
DELIMITER $$
CREATE PROCEDURE errorProc2()
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
	SHOW ERRORS; -- ���� �޽����� ���� �ش�.
	SELECT '������ �߻��߳׿�. �۾��� ��ҽ��׽��ϴ�.' AS '�޽���'; 
	ROLLBACK; -- ���� �߻��� �۾��� �ѹ��Ų��.
    END;
    INSERT INTO usertbl VALUES('LSG', '�̻�', 1988, '����', NULL, 
		NULL, 170, CURRENT_DATE()); -- �ߺ��Ǵ� ���̵��̹Ƿ� ���� �߻�
END $$
DELIMITER ;
CALL errorProc2();


use sqldb;
PREPARE myQuery FROM 'SELECT * FROM usertbl WHERE userID = "EJW"';
EXECUTE myQuery;
DEALLOCATE PREPARE myQuery;


USE sqldb;
DROP TABLE IF EXISTS myTable;
CREATE TABLE myTable (id INT AUTO_INCREMENT PRIMARY KEY, mDate DATETIME);
SET @curDATE = CURRENT_TIMESTAMP(); -- ���� ��¥�� �ð�
PREPARE myQuery FROM 'INSERT INTO myTable VALUES(NULL, ?)';
EXECUTE myQuery USING @curDATE;
DEALLOCATE PREPARE myQuery;
SELECT * FROM myTable;