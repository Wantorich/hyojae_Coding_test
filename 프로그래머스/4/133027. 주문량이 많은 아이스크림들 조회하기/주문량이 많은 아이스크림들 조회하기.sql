select sub.flavor
from first_half join 
    (select sum(total_order) subtotal, flavor
    from july
    group by flavor) sub
on first_half.flavor = sub.flavor
order by total_order + subtotal desc
limit 3