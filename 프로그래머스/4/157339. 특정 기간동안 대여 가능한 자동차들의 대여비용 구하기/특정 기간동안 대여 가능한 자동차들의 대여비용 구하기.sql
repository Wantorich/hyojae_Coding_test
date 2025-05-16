SELECT 
  car.car_id,
  car.car_type,
  FLOOR(car.daily_fee * 30 * (100 - discount.discount_rate) / 100) AS fee
FROM CAR_RENTAL_COMPANY_CAR car
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN discount 
  ON car.car_type = discount.car_type
  AND discount.duration_type = '30일 이상'
LEFT JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY history
  ON car.car_id = history.car_id
  AND history.start_date <= '2022-11-30'
  AND history.end_date >= '2022-11-01'
WHERE car.car_type IN ('세단', 'SUV')
  AND history.car_id IS NULL
HAVING fee >= 500000 AND fee < 2000000
ORDER BY fee DESC, car.car_type ASC, car.car_id DESC;
