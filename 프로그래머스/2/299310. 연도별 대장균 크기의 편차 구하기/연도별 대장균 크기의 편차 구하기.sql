select
    year(DIFFERENTIATION_DATE) as year,
    (select max(sub.SIZE_OF_COLONY) 
    from ecoli_data sub
    where year(ed.DIFFERENTIATION_DATE) = year(sub.DIFFERENTIATION_DATE)) - ed.size_of_colony as year_dev,
    id
from ecoli_data ed
order by year, year_dev