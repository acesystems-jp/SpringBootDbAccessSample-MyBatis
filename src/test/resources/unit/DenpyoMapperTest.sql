
insert into item(code, name, basic_tanka, last_update_datetime, last_update_employee_id)
 values('A001', 'マニピュレータ-指', 120, '2020-3-5 16:21:00', 1);
insert into item(code, name, basic_tanka, last_update_datetime, last_update_employee_id)
 values('A002', 'マニピュレータ-手首', 600, '2020-3-5 16:21:00', 1);


insert into denpyo(id, denpyo_no, eda_no, denpyo_date, last_update_datetime, last_update_employee_id)
 values (1, 1001, 0, '2020-03-25', '2020-3-5 16:21:00', 1);

insert into denpyo(id, denpyo_no, eda_no, denpyo_date, last_update_datetime, last_update_employee_id)
 values (2, 1002, 0, '2020-03-25', '2020-3-5 16:21:00', 1);

insert into denpyo_detail(id, denpyo_id, row_no, item_code, suryo, tanka, last_update_datetime, last_update_employee_id)
 values (1, 1, 1, 'A001', 5, 100.0, '2020-3-5 16:21:00', 1);
 
insert into denpyo_detail(id, denpyo_id, row_no, item_code, suryo, tanka, last_update_datetime, last_update_employee_id)
 values (2, 1, 2, 'A002', 1, 600.0, '2020-3-5 16:21:00', 1);

 