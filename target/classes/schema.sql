insert into product (id,product_id,name,unitprice,is_discount_applicable) values (1,'001','ROLEX',100.0,'true');
insert into product (id,product_id,name,unitprice,is_discount_applicable) values (2,'002','Michael Kors',80.0,'true');
insert into product (id,product_id,name,unitprice,is_discount_applicable) values (3,'003','Swatch',50.0,'false');
insert into product (id,product_id,name,unitprice,is_discount_applicable) values (4,'004','Casio',30.0,'false');
insert into discount (id, discounted_product_id,percentage,quantity_needed_to_avail_discount,discounted_amount) values (1, 1,0,3,200);
insert into discount (id, discounted_product_id,percentage,quantity_needed_to_avail_discount,discounted_amount) values (2, 2,0,2,120);


