insert into car(name, model, price, number_of_days, horse_power, sec_to_hundred)
values ('Golf', '7R', 600, 30, 315, 4.8),
       ('Lamborghini', 'Huracan Evo', 1665, 30, 640, 2.9),
       ('Audi', 'RSQ8', 930, 30, 600, 3.8);

insert into reservation(is_car_free, date_from, date_to, car_id)
values (false, '2022-11-06', '2022-11-20', 1);
