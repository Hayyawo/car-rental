insert into car(name, model, is_car_free_now, price_for_day, horse_power, sec_to_hundred, isofix, tank_capacity, petrol)
values ('Golf', '7R', false, 600, 315, 4.8, true, 70, true),
       ('Lamborghini', 'Huracan Evo', false, 1665, 640, 2.9, false, 60, true),
       ('Audi', 'RSQ8', false, 930, 600, 3.8, true, 100, true);

insert into reservation(date_from, date_to, price_for_reservation, car_id)
values ('2022-11-06', '2022-11-20', 100, 1),
       ('2022-11-06', '2022-11-20', 2000, 2),
       ('2022-11-05', '2022-11-06', 2453, 3);

insert into accessory(name, description, price, paid_daily, reservation_id)
values ('Full tank of fuel', 'Dont worry about refueling. Car has full tank of fuel - you can return car with an empty tank', 6.5, false, 1),
       ('Without deposit', 'By this accessory you dont have to pay for ', 250, true, 1);


-- //2800 przy jednym dniu 2800
-- 5000 za dwa dni         2500
-- 7500 za 3               2500
-- 11750                   2350
--                          933,33