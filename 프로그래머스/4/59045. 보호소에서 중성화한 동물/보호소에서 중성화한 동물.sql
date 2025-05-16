select ins.animal_id, ins.animal_type, ins.name
from animal_ins ins join animal_outs outs
on ins.animal_id = outs.animal_id
where substring_index(ins.sex_upon_intake, ' ', 1) = 'Intact' 
and (substring_index(outs.sex_upon_outcome, ' ', 1) = 'Spayed'
     or substring_index(outs.sex_upon_outcome, ' ', 1) = 'Neutered'
     )
order by ins.animal_id