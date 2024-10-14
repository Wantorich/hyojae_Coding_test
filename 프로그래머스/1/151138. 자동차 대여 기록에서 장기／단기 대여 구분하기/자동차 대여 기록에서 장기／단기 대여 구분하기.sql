-- 코드를 입력하세요
SELECT history_id, car_id, date_format(start_date, "%Y-%m-%d"), date_format(end_date, "%Y-%m-%d"), 
    case 
        when datediff(end_date, start_date) >= 29 then '장기 대여'
        else '단기 대여'
    end rent_type
from CAR_RENTAL_COMPANY_RENTAL_HISTORY c
where extract(year_month from start_date) = '202209'
order by history_id desc