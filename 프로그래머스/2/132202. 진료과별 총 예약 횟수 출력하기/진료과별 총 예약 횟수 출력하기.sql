-- 코드를 입력하세요
SELECT MCDP_CD, COUNT(*) 5월예약건수
FROM APPOINTMENT
WHERE EXTRACT(YEAR_MONTH FROM APNT_YMD) = '202205'
GROUP BY MCDP_CD
ORDER BY 5월예약건수, MCDP_CD

