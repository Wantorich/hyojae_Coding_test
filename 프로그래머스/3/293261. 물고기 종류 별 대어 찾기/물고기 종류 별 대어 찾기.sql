select id, fn.fish_name, sub.length
from fish_info fi 
join fish_name_info fn using(fish_type)
join 
    (select fish_type, max(length) length
    from fish_info
    group by fish_type) sub
on fi.fish_type = sub.fish_type
and fi.length = sub.length