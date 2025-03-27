select member_name, review_text, left(review_date, 10)
from rest_review join member_profile
using(member_id)
where member_id = (select member_id
                    from rest_review
                    group by member_id
                    order by count(member_id) desc
                    limit 1)
order by review_date, review_text                    