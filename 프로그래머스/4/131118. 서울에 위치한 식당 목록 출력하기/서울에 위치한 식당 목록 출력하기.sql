-- rest_id를 그룹으로 평균점수 구하기
select a.rest_id, a.rest_name, a.food_type, a.favorites, a.address, b.score
from rest_info a join (select rest_id, round(avg(review_score), 2) score, sum(favorites) fsum
    from rest_info join rest_review using(rest_id)
    where left(address, 2) = '서울'
    group by rest_id) b
on a.rest_id = b.rest_id
order by b.score desc, a.favorites desc

