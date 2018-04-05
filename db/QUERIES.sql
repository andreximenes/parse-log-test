--Load log file into table log;

LOAD DATA INFILE '<COMPLETE LOG PATH>' INTO TABLE log
COLUMNS TERMINATED BY '|' LINES TERMINATED BY '\r\n' 
(@col1, @col2, @col3, @col4, @col5)
set 
id = null,
date_time = @col1,
ip = @col2,
protocol = REPLACE(TRIM(@col3), '"', '') ,
status = @col4,
detail = TRIM(REPLACE(@col5, '"', '')); 
-----------------------------------------------------------------------------------------------------------------------------------

--Query for select ip's that will be marked as blocked in table ip_blocked
--1?= startDate, 2?= startDate, 3?= threshold
--DURATION***: daily = DAY, hourly = HOUR

SELECT ip FROM log WHERE date_time BETWEEN ? AND (? + INTERVAL 1 HOUR) GROUP BY ip HAVING COUNT(*) > ?;
-----------------------------------------------------------------------------------------------------------------------------------


--Find and insert ip blocked
--1?= block cause, 2?= startDate, 3?= startDate, 4?= threshold
--DURATION***: daily = DAY, hourly = HOUR

INSERT INTO ip_blocked (ip, info, block_date)
SELECT ip, ?, CURRENT_TIMESTAMP FROM log 
WHERE date_time BETWEEN ? AND (? + INTERVAL 1 HOUR) 
GROUP BY ip HAVING COUNT(*) > ?;
-----------------------------------------------------------------------------------------------------------------------------------

--Select ip_blocked
SELECT ip, info, block_date FROM ip_blocked;
-----------------------------------------------------------------------------------------------------------------------------------