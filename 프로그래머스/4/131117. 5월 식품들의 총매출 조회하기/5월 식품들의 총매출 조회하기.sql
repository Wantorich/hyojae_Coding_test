# select food_order.product_id, product_name, count(food_order.product_id) * price as total_sales
# from food_order join food_product using(product_id)
# where date_format(produce_date, '%Y-%m') = '2022-05'
# group by food_order.product_id
# order by total_sales desc, product_id

SELECT 
    fp.PRODUCT_ID,
    fp.PRODUCT_NAME,
    SUM(fp.PRICE * fo.AMOUNT) AS TOTAL_SALES
FROM FOOD_PRODUCT fp
JOIN FOOD_ORDER fo 
    ON fp.PRODUCT_ID = fo.PRODUCT_ID
WHERE DATE_FORMAT(fo.PRODUCE_DATE, '%Y-%m') = '2022-05'
GROUP BY fp.PRODUCT_ID, fp.PRODUCT_NAME
ORDER BY TOTAL_SALES DESC, fp.PRODUCT_ID ASC;