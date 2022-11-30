insert into car(name, model, price_for_day, horse_power, sec_to_hundred, isofix, tank_capacity, type_of_fuel)
values ('Golf', '7R', 600, 315, 4.8, true, 70, 'GAS'),
       ('Lamborghini', 'Huracan Evo', 1665, 640, 2.9, false, 60, 'GAS'),
       ('Audi', 'RSQ8', 930, 600, 3.8, true, 100, 'GAS');

insert into reservation(date_from, date_to, total_price, car_id)
values ('2022-11-06', '2022-11-20', 100, 1),
       ('2022-11-06', '2022-11-20', 2000, 2),
       ('2022-11-05', '2022-11-06', 2453, 3);

insert into accessory(name, description, reservation_id)
values ('Full tank of fuel',
        'Dont worry about refueling. Car has full tank of fuel - you can return car with an empty tank',  1),
       ('Without deposit', 'By this accessory you dont have to pay for ',  1);

insert into price_for_rent(car_id, price_for_day)
values (1, 400.0),
       (2, 5000.0),
       (3, 2800.0);
