USE sakila;
-- Level 3:
-- 1.Write a SQL query to return the average rental duration for each combination of actor and
-- category in the database, excluding actors who have not appeared in any films in a category.

-- ?????????
SELECT fa.actor_id, fc.category_id
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN film_category fc ON fa.film_id = fc.film_id
# INNER JOIN
GROUP BY fa.actor_id, fc.category_id;


-- 2.Write a SQL query to return the names of all actors who have appeared in a film
-- with a rating of 'R' and a length of more than 2 hours, but have never appeared in a film with a rating of 'G'.
SELECT CONCAT(a.first_name, ' ', a.last_name) AS `full_name`
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN film f ON fa.film_id = f.film_id
WHERE f.rating = 'R' AND f.length > 120 AND a.actor_id NOT IN (
    SELECT a.actor_id
    FROM actor a
    INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
    INNER JOIN film f ON fa.film_id = f.film_id
    WHERE f.rating = 'G'
    )
GROUP BY a.actor_id;

SELECT f.film_id, GROUP_CONCAT(fa.actor_id)
FROM film f
JOIN film_actor fa ON f.film_id = fa.film_id
WHERE f.rating = 'G'
GROUP BY f.film_id;


-- Cách 2:
# SELECT DISTINCT CONCAT(a.first_name, ' ', a.last_name) AS full_name
# FROM actor a
# JOIN film_actor fa ON a.actor_id = fa.actor_id
# JOIN film f ON fa.film_id = f.film_id where f.rating = 'R' AND f.length > 120
# AND NOT EXISTS (SELECT 1 FROM film f2 JOIN film_actor fa2 ON f2.film_id = fa2.film_id
#     JOIN actor a2 ON fa2.actor_id = a2.actor_id
#     WHERE f2.rating = 'G' AND a.actor_id = a2.actor_id
# );


-- 3.Write a SQL query to return the names of all customers who have rented more than 10 films
-- in a single transaction, along with the number of films they rented and the total rental fee.
SELECT c.customer_id, CONCAT(c.first_name,' ',c.last_name) AS full_name, COUNT(r.rental_id) AS total_film_rental, SUM(p.amount) AS total_fee
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN payment p ON r.rental_id = p.rental_id
GROUP BY c.customer_id,full_name
HAVING COUNT(r.rental_id) > 10;


-- 4.Write a SQL query to return the names of all customers who have rented every film in a category,
-- along with the total number of films rented and the total rental fee.
SELECT c.category_id, GROUP_CONCAT(DISTINCT i.film_id), COUNT(DISTINCT i.film_id)
FROM category c
INNER JOIN film_category fc ON c.category_id = fc.category_id
INNER JOIN inventory i ON fc.film_id = i.film_id
-- INNER JOIN rental r ON i.inventory_id = r.inventory_id
-- INNER JOIN customer c2 ON r.customer_id = c2.customer_id
GROUP BY c.category_id;

SELECT c.customer_id, GROUP_CONCAT(DISTINCT fc.film_id), COUNT(DISTINCT fc.film_id)
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film_category fc ON i.film_id = fc.film_id
WHERE fc.category_id = 1
GROUP BY c.customer_id;

SELECT CONCAT(c.first_name, ' ', c.last_name) as name
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film_category fc ON i.film_id = fc.film_id
WHERE fc.category_id = 1
GROUP BY c.customer_id
HAVING COUNT(r.rental_id) = (
    SELECT COUNT(*)
    FROM film_category fc2
    INNER JOIN category c2 ON fc2.category_id = c2.category_id
    WHERE c2.category_id = 1
    )
;


-- 5.Write a SQL query to return the titles of all films in the database that have been rented by the same customer
-- more than once in a single day, along with the names of the customers who rented them and the number of times they were rented.
SELECT c.customer_id, f.film_id, COUNT(r.rental_id)
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film f ON i.film_id = f.film_id
-- WHERE DATE(r.rental_date) = STR_TO_DATE('22,8,2005','%d,%m,%Y')
GROUP BY c.customer_id, f.film_id
HAVING COUNT(r.rental_id) > 1
;


SELECT c.customer_id, f.film_id, r.rental_date
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film f ON i.film_id = f.film_id
ORDER BY c.customer_id, f.film_id, rental_date;



# SELECT STR_TO_DATE('21,5,2013','%d,%m,%Y');
# SELECT DATE(r.rental_date)
# FROM rental r
# WHERE r.rental_date =



-- 6.Write a SQL query to return the names of all actors who have appeared in at least one film with
-- every other actor in the database, along with the number of films they appeared in together.
SELECT f.film_id, group_concat(a.actor_id), count(a.actor_id)
FROM actor a
INNER JOIN film_actor fa
    ON a.actor_id = fa.actor_id
INNER JOIN film f
    ON fa.film_id = f.film_id
INNER JOIN actor a2
    ON a.actor_id = a2.actor_id
INNER JOIN film_actor fa2
    ON a2.actor_id <> fa2.actor_id
-- WHERE a.actor_id
GROUP BY a.actor_id, f.film_id;
-- HAVING COUNT(DISTINCT a.actor_id) = (SELECT COUNT(actor_id) FROM actor )




-- 7.Write a SQL query to return the names of all customers who have rented at least one film from
-- each category in the database, along with the number of films rented from each category.
SELECT c.customer_id, concat(c.first_name, ' ', c.last_name) as customer_name,
       group_concat(DISTINCT f.title) as list_film, count(DISTINCT f.film_id) as quantity_film
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film_category fc ON i.film_id = fc.film_id
INNER JOIN film f ON i.film_id = f.film_id
GROUP BY c.customer_id -- , fc.category_id
HAVING count(DISTINCT fc.category_id) = (
    SELECT count(*)
    FROM category
);



-- 8.Write a SQL query to return the titles of all films in the database that have been rented more than 100 times,
-- but have never been rented by any customer who has rented a film with a rating of 'G'.
# SELECT c.customer_id
# FROM customer c
# INNER JOIN rental r ON c.customer_id = r.customer_id
# WHERE NOT exists(
#     SELECT 1
#     FROM inventory i
#     INNER JOIN film f ON i.film_id = f.film_id
#     WHERE
# )
# GROUP BY c.customer_id
# HAVING count(r.rental_id) > 30;

SELECT f.title
FROM film f
INNER JOIN inventory i on f.film_id = i.film_id
INNER JOIN rental r on i.inventory_id = r.inventory_id
WHERE r.customer_id NOT IN (
    SELECT DISTINCT r2.customer_id
    FROM rental r2
    INNER JOIN inventory i2 on r2.inventory_id = i2.inventory_id
    INNER JOIN film f2 on i2.film_id = f2.film_id
    WHERE f2.rating = 'G'
)
GROUP BY f.film_id
HAVING count(r.rental_id) > 1;

SELECT r2.customer_id
FROM rental r2
INNER JOIN inventory i2 on r2.inventory_id = i2.inventory_id
INNER JOIN film f2 on i2.film_id = f2.film_id
WHERE f2.rating = 'G';



-- 9.Write a SQL query to return the names of all customers who have rented a film from a category
-- they have never rented from before, and have also never rented a film longer than 3 hours.
SELECT concat(c.first_name,' ',c.last_name) AS customer_name
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film_category fc ON fc.film_id = i.film_id
JOIN film f ON f.film_id = fc.film_id
WHERE f.length <= 180 AND fc.category_id NOT IN (
    SELECT fc1.category_id
    FROM film_category fc1
    INNER JOIN inventory i1 ON fc1.film_id = i1.film_id
    INNER JOIN rental r1 ON i1.inventory_id = r1.inventory_id
    INNER JOIN customer c1 ON r1.customer_id = c1.customer_id
    WHERE c1.customer_id = c.customer_id
);





-- 10.Write a SQL query to return the names of all actors who have appeared in a film with a rating of'PG-13' and a
-- length of more than 2 hours, and have also appeared in a film with a rating of 'R' and a length of less than 90 minutes.
SELECT a.actor_id, concat(a.first_name,' ',a.last_name) AS actor_name
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN film f ON fa.film_id = f.film_id
WHERE f.rating = 'PG-13'
    AND f.length > 120
    AND a.actor_id IN (
        SELECT a.actor_id
        FROM actor a
        INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
        INNER JOIN film f ON fa.film_id = f.film_id
        WHERE f.rating = 'R' AND f.length <90
    )
GROUP BY a.actor_id;









-- câu 3: trả về tên của tất cả khách hàng đã thuê hơn 10 bộ phim trong một giao dịch, cùng với số lượng phim họ đã thuê và tổng phí thuê.
select c.customer_id, concat(c.first_name,' ',c.last_name) as full_name,
COUNT(r.rental_id) as total_film_rental,
sum(p.amount) as total_fee
from customer c join rental r on c.customer_id = r.customer_id
join payment p on r.rental_id = p.rental_id group by c.customer_id,full_name
HAVING count(r.rental_id)>10;
-- câu 4:  trả về tên của tất cả các khách hàng đã thuê mọi bộ phim trong một danh mục, cùng với tổng số phim đã thuê và tổng phí thuê.
select concat(c.first_name,' ',c.last_name) as full_name,  count(distinct i.film_id) as total_film_rental
, sum(p.amount) as total_fee
from customer c join rental r on c.customer_id = r.customer_id
join payment p on r.rental_id = p.rental_id
join inventory i on r.inventory_id = i.inventory_id
join film_category fc on fc.film_id = i.film_id
where fc.category_id = 2
group by c.customer_id , full_name;
-- câu 5: trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được cùng một khách hàng thuê nhiều lần trong một ngày,
-- cùng với tên của những khách hàng đã thuê phim và số lần họ thuê.
SELECT f.title , concat(c.first_name,' ',c.last_name) as full_name, count(*) as total_rental
from film f join inventory i on f.film_id = i.film_id
join rental r on i.inventory_id= r.inventory_id
join customer c on c.customer_id = r.customer_id
WHERE  date(r.rental_date)= date(now())
GROUP BY f.title,full_name
having count(*)>1;
-- câu 6: trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim cùng với mọi diễn viên khác trong cơ sở dữ liệu,
-- cùng với số lượng phim họ đã xuất hiện cùng nhau.
select concat(a1.first_name,' ',a1.last_name) as actor_name1 ,
concat(a2.first_name,' ',a2.last_name) as actor_name2,
count(DISTINCT f1.film_id) as total_show_together
from actor a1
join film_actor fa1 on a1.actor_id = fa1.actor_id
join film f1 on fa1.film_id = f1.film_id
join film_actor fa2 on fa1.film_id = fa2.film_id and fa1.actor_id != fa2.actor_id
join actor a2 on a2.actor_id  = fa2.actor_id
group by actor_name1, actor_name2
order by total_show_together desc;
-- câu 7: trả về tên của tất cả khách hàng đã thuê ít nhất một phim từ mỗi danh mục trong cơ sở dữ liệu,
-- cùng với số lượng phim đã thuê từ mỗi danh mục.
select concat(c.first_name,' ',c.last_name) as customer_name,
count(DISTINCT r.inventory_id) as total_film_rentaled,
count(DISTINCT fc.category_id)  as total_category_rentaled
from customer c
join rental r on c.customer_id = r.customer_id
join  inventory i on r.inventory_id = i.inventory_id
join film_category fc on fc.film_id = i.film_id
group by c.customer_id , customer_name
having count(distinct fc.category_id) = (select count(*) from category);
-- câu 8: trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 30 lần,
-- nhưng chưa bao giờ được thuê bởi bất kỳ khách hàng nào đã thuê phim có xếp hạng 'G'.
select DISTINCT(f.title) as title_of_film from film f
join inventory i on i.film_id = f.film_id
left join rental r on i.inventory_id = r.inventory_id
left join (select r2.inventory_id from rental r2 join inventory i2 on i2.inventory_id = r2.inventory_id
join film f2  on f2.film_id =i2.film_id where f2.rating = 'G') g on i.inventory_id = g.inventory_id
where g.inventory_id is null
group by f.film_id,f.title having count(r.inventory_id)>30;
-- câu 9: trả về tên của tất cả các khách hàng đã thuê phim từ danh mục mà họ chưa bao giờ thuê trước đây
-- và cũng chưa bao giờ thuê phim dài hơn 3 giờ.
select concat(c.first_name,' ',c.last_name) as customer_name from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film_category fc on fc.film_id = i.film_id
join film f on f.film_id = fc.film_id where f.length <=180
	and fc.category_id not in (
     select fc1.category_id
    from film_category fc1
    join inventory i1 ON fc1.film_id = i1.film_id
    join rental r1 ON i1.inventory_id = r1.inventory_id
    JOIN customer c1 ON r1.customer_id = c1.customer_id
    where c1.customer_id = c.customer_id);
-- câu 10: trả về tên của tất cả các diễn viên đã xuất hiện trong một bộ phim có xếp hạng 'PG-13' và thời lượng hơn 2 giờ,
-- đồng thời cũng đã xuất hiện trong một bộ phim có xếp hạng 'R' và thời lượng dưới 90 phút.
select concat(a.first_name,'',a.last_name) as actor_name from actor a
join film_actor fa on a.actor_id = fa.actor_id join film f on f.film_id = fa.film_id
where f.rating = 'PG-13' and f.length >120
and exists (select 1
FROM actor a1 join film_actor fa1 on a1.actor_id = fa1.actor_id
join film f1 on f1.film_id = fa1.film_id
where f1.rating = 'R' and f1.length < 90);

-- Level4
-- câu 1: cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 100 lần,
-- đặt giá thuê mới cao hơn 10% so với giá hiện tại.
update film
inner join(
select f.film_id from film f join inventory i on f.film_id = i.film_id
join rental r on r.inventory_id = i.inventory_id
group by f.film_id having count(r.rental_id) > 100)
AS subquery ON film.film_id = subquery.film_id
SET rental_rate = rental_rate * 1.1;
-- câu 2: cập nhật thời lượng thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 5 lần,
-- đặt thời lượng mới dài hơn 5% so với thời lượng hiện tại.
update film set
length = length+ (length*0.05)
where film_id in(
select film_id from rental GROUP BY film_id having count(*)>5);
-- câu 3: cập nhật giá thuê của tất cả các phim trong danh mục 'Hành động' được phát hành trước năm 2005,
-- đặt giá mới cao hơn 20% so với giá hiện tại.
update film f join(
select f.film_id from film f join film_category fc on f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
where c.name ='Action' and f.release_year<2005
) s on f.film_id = s.film_id set f.rental_rate = f.rental_rate+(f.rental_rate*1.2);
-- câu 4: cập nhật địa chỉ email của tất cả khách hàng đã thuê phim từ danh mục 'Kinh dị' trong tháng 10 năm 2022,
-- đặt địa chỉ email mới là sự kết hợp giữa địa chỉ email hiện tại của họ và chuỗi 'kinh dị'.
UPDATE customer
SET email = CONCAT(email, 'horrorlover')
WHERE customer_id IN (
SELECT customer_id
FROM (SELECT c.customer_id FROM customer c
join rental r ON c.customer_id = r.customer_id
join inventory i ON r.inventory_id = i.inventory_id
join film f ON i.film_id = f.film_id
join film_category fc ON f.film_id = fc.film_id
join category cat ON fc.category_id = cat.category_id	where cat.name = 'Horror'
AND YEAR(r.rental_date) = 2022 AND MONTH(r.rental_date) = 10
) subquery);
-- câu 5: cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được hơn 10 khách hàng thuê,
-- đặt giá mới cao hơn 5% so với giá hiện tại, nhưng không cao hơn $4,00.
update film set rental_rate = case when rental_rate*1.05<=4 then rental_rate * 1.05 else 4.00 end
where film_id in (
select film_id from rental GROUP BY film_id
having count(DISTINCT customer_id)>10);
-- câu 6: cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'PG-13'
-- và thời lượng hơn 2 giờ, đặt giá mới là $3,5.
update film set rental_rate = 3.5
where rating = 'PG-13' AND length > 120;
-- câu 7: cập nhật thời lượng cho thuê của tất cả các phim trong danh mục 'Khoa học viễn tưởng' được phát hành vào năm 2010
-- ,đặt thời lượng mới bằng với thời lượng của phim tính bằng phút.
update film f join (
select f.film_id
from film f join film_category fc on f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
where c.name = 'Sci-Fi' and f.release_year = 2010 ) s
on f.film_id = s.film_id
SET rental_duration = length;
-- câu 8: cập nhật địa chỉ của tất cả các khách hàng sống trong cùng thành phố với một khách hàng khác có cùng họ
-- đặt địa chỉ mới là phần nối của địa chỉ hiện tại của họ và chuỗi 'samecity'.
update customer as c1
join customer AS c2 ON c1.last_name = c2.last_name AND c1.customer_id <> c2.customer_id
join address AS a1 ON c1.address_id = a1.address_id
join address AS a2 ON c2.address_id = a2.address_id
set a1.address = CONCAT(a1.address, ' samecity')
where a1.city_id = a2.city_id;
-- câu 9: cập nhật giá thuê của tất cả các phim trong danh mục 'Comedy' được phát hành vào năm 2007 trở đi,
-- đặt giá mới thấp hơn 15% so với giá hiện tại.
update film
set rental_rate = rental_rate * 0.85
where film_id IN (
select film.film_id
from film join film_category ON film.film_id = film_category.film_id
join category ON film_category.category_id = category.category_id
where category.name = 'Comedy' AND YEAR(film.release_year) >= 2007
);
-- câu 10: cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'G' và thời lượng dưới 1 giờ, đặt giá mới là $1,50.
UPDATE film
SET rental_rate = 1.50
WHERE rating = 'G' AND length < 60;