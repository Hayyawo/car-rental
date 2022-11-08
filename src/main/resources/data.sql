insert into car(name, model, is_car_free_now, price_for_day, horse_power, sec_to_hundred)
values ('Golf', '7R', false, 600,  315, 4.8),
       ('Lamborghini', 'Huracan Evo', false, 1665,  640, 2.9),
       ('Audi', 'RSQ8', false, 930,  600, 3.8);

insert into reservation(date_from, date_to, price_for_reservation, car_id)
values ('2022-11-06', '2022-11-20',100, 1),
       ('2022-11-06', '2022-11-20',2000, 2),
       ('2022-11-05', '2022-11-06',2453, 3);


-- //2800 przy jednym dniu 2800
-- 5000 za dwa dni         2500
-- 7500 za 3               2500
-- 11750                   2350
--                          933,33