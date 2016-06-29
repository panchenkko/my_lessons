select car.name, t.name, cb.name, e.name from car
left join transmission as t on car.transmission_id = t.id
left join carBody as cb on car.carBody_id = cb.id
left join engine as e on car.engine_id = e.id;

select t.name as "Transmission" from car
right join transmission as t on car.transmission_id = t.id
where car.transmission_id is null;

select cb.name as "Car body" from car
right join carBody as cb on car.carBody_id = cb.id
where car.carBody_id is null;

select e.name as "Engine" from car
right join engine as e on car.engine_id = e.id
where car.engine_id is null