use sakila;

-- 1.Viết truy vấn SQL để trả về 10 khách hàng hàng đầu đã tạo ra nhiều doanh thu nhất cho cửa hàng,
--  bao gồm tên của họ và tổng doanh thu được tạo ra.
select * from customer;
SELECT c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS customer_name, SUM(p.amount) AS total_revenue
FROM customer c
JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, customer_name
ORDER BY total_revenue DESC
LIMIT 10;
-- -----------------------------------------------------------------
/*
With customer_total_revenue as(
	SELECT  c.store_id, c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS customer_name, SUM(p.amount) AS total_revenue
	FROM customer c
		JOIN payment p ON c.customer_id = p.customer_id
	GROUP BY c.customer_id, customer_name
)
select customer_id, customer_name,
		NTILE(10) OVER(PARTITION BY store_id ORDER BY total_revenue desc limit 10)
 from customer_total_revenue;
*/
(
  SELECT c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
		SUM(p.amount) AS total_revenue, c.store_id
  FROM customer c
  JOIN payment p ON c.customer_id = p.customer_id
  WHERE c.store_id = 1
  GROUP BY c.customer_id, customer_name, c.store_id
  ORDER BY total_revenue DESC
  LIMIT 10
)
UNION
(
  SELECT c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
		SUM(p.amount) AS total_revenue, c.store_id
  FROM customer c
  JOIN payment p ON c.customer_id = p.customer_id
  WHERE c.store_id = 2
  GROUP BY c.customer_id, customer_name, c.store_id
  ORDER BY total_revenue DESC
  LIMIT 10
);
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 2.Viết truy vấn SQL để trả về tên và thông tin liên hệ của tất cả khách hàng
-- đã thuê phim ở tất cả các danh mục trong cơ sở dữ liệu.
select * from customer;
select * from rental;
select * from inventory;
-- nếu không tồn tại bất kỳ danh mục nào mà khách hàng chưa thuê phim, thì khách hàng sẽ được chọn và trả về
SELECT c.customer_id, concat(first_name,', ',last_name) as customer_name, c.email
FROM customer c
WHERE NOT exists(
	-- trả về các category không ở trông truy vấn con
	select * from category
    where category_id NOT IN (
		-- trả về các category mà khách hàng đã thuê từ bảng rental
		SELECT distinct fa.category_id
        FROM rental r JOIN inventory i on r.inventory_id = i.inventory_id
						JOIN film_category fa on i.film_id = fa.film_id
                        where r.customer_id = c.customer_id -- đảm bảo rằng chỉ có dữ liệu liên quan đến khách hàng cụ thể được lấy
    )
);
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 3.Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu
-- đã được thuê ít nhất một lần nhưng không bao giờ trả lại.
select * from rental where return_date is null;
select title
from film
where film_id IN (
					Select i.film_id
					from rental r join inventory i on r.inventory_id = i.inventory_id
                    where return_date is null
					);
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 4.Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim trong mỗi danh mục trong cơ sở dữ liệu
-- tức là các diễn viên tham gia đầy đủ 16 danh mục
select count(*) from category;
select a.actor_id, concat(a.first_name,' ', a.last_name) as actor_name
from actor a join (	select fa.actor_id, count(distinct fc.category_id) as category_count
					from film_actor fa join film_category fc on fa.film_id = fc.film_id
					group by fa.actor_id) b on a.actor_id = b.actor_id
where b.category_count = (select count(distinct category_id) from category);
-- -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- ????????????????????????????
-- 5.Viết một truy vấn SQL để trả về tên của tất cả các khách hàng
-- đã thuê cùng một bộ phim nhiều lần trong một giao dịch, cùng với số lần họ đã thuê bộ phim đó.
-- tức là tìm khách hàng đã thuê cùng một bộ phim nhiều hơn 1 lần trong một giao dịch duy nhất
select * from inventory join rental using(inventory_id);
-- trả vể danh sách khách hàng đã thuê nhiều hơn một bộ phim trong một lần duy nhất
SELECT c.customer_id, c.first_name, c.last_name, COUNT(*) as rental_count
FROM customer c
	JOIN rental r1 ON c.customer_id = r1.customer_id
	JOIN rental r2 ON r1.customer_id = r2.customer_id AND r1.rental_id <> r2.rental_id AND r1.rental_date = r2.rental_date
	JOIN inventory i ON r1.inventory_id = i.inventory_id
GROUP BY c.customer_id
HAVING rental_count > 1;
-- trả về khách hàng đã thuê cùng một bộ phim nhiều hơn 1 lần trong một giao dịch duy nhất
SELECT c.customer_id,
		concat(c.first_name,' ', c.last_name) as customer_name,
        count(*) as rental_count
FROM customer c
	JOIN rental r1 on c.customer_id = r1.customer_id
    JOIN rental r2 on r1.rental_id = r2.rental_id and r1.inventory_id <> r2.inventory_id
    JOIN inventory i1 ON r1.inventory_id = i1.inventory_id
    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
WHERE i1.film_id = i2.film_id
GROUP BY c.customer_id
HAVING rental_count > 1;

SELECT c.customer_id,
		concat(c.first_name,' ', c.last_name) as customer_name,
        count(*) as rental_count
FROM customer c
	JOIN rental r1 on c.customer_id = r1.customer_id
    JOIN rental r2 on r1.rental_date = r2.rental_date and r1.inventory_id <> r2.inventory_id
    JOIN inventory i1 ON r1.inventory_id = i1.inventory_id
    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
WHERE i1.film_id = i2.film_id
GROUP BY c.customer_id
HAVING rental_count > 1;
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 6.Viết truy vấn SQL để trả về tổng doanh thu do mỗi diễn viên tạo ra trong cơ sở dữ liệu, dựa trên phí thuê phim mà họ đã xuất hiện.
select a.actor_id, concat(a.first_name ,' ', a.last_name) as actor_fullname, sum(p.amount) as actor_amount
from actor a join film_actor fa on a.actor_id = fa.actor_id
			join inventory i on fa.film_id = i.film_id
            join rental r on i.inventory_id = r.inventory_id
            join payment p on r.rental_id = p.rental_id
group by a.actor_id;
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 7.Viết một truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim có xếp hạng 'R',
-- nhưng chưa bao giờ xuất hiện trong một bộ phim có xếp hạng 'G'.
select * from film;
select a.actor_id, concat(a.first_name,' ',a.last_name) as actor_name, f.rating as film_rating
from actor a join film_actor fa on a.actor_id = fa.actor_id
			 join film f on fa.film_id = f.film_id
where f.rating = 'R' and a.actor_id not in (select fa.actor_id
											from film f join film_actor fa on f.film_id = fa.film_id
											where f.rating = 'G'
											group by actor_id)
group by a.actor_id;
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 8.Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê bởi hơn 50 khách hàng,
-- nhưng chưa bao giờ được thuê bởi cùng một khách hàng nhiều lần.
select f.film_id, f.title, count(distinct r.customer_id) as customer_rentals
from film f join inventory i on f.film_id = i.film_id
			join rental r on i.inventory_id = r.inventory_id
group by f.film_id
having customer_rentals > 50;
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 9.Viết truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê phim từ danh mục mà họ chưa từng thuê trước đó.
select c.customer_id, concat(c.first_name,' ', c.last_name) as customer_name
from customer c join rental r on c.customer_id = r.customer_id
				join inventory i on r.inventory_id = i.inventory_id
                join film_category fc on i.film_id = fc.film_id
                left join ( select r.customer_id, fa.category_id
							from rental r join inventory i on r.inventory_id = i.inventory_id
							join film_category fa on i.film_id = fa.film_id
							group by customer_id, fa.category_id) as ra
                            on c.customer_id = ra.customer_id and fc.category_id = ra.category_id
where ra.customer_id is null;
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 10.Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê bởi mọi khách hàng
--  đã từng thuê phim từ danh mục 'Action'.
select film_id , title
from film f
where not exists (
					select * from customer
					where customer_id not in (
												select customer_id
												from rental r join inventory i on r.inventory_id = i.inventory_id
															join film_category fc on i.film_id = fc.film_id
															join category c on fc.category_id = c.category_id
															where c.name = 'Action' and f.film_id = fc.film_id
															group by customer_id)
				  );
-- --------------------------------
SELECT film.title
FROM film
JOIN film_category ON film.film_id = film_category.film_id
JOIN rental ON film.film_id = rental.inventory_id
WHERE film_category.category_id = (
    SELECT category_id
    FROM category
    WHERE name = 'Action'
)
GROUP BY film.film_id, film.title
HAVING COUNT(DISTINCT rental.customer_id) = (
    SELECT COUNT(DISTINCT customer_id)
    FROM rental
);






-- ==================================================================================================================





use sakila;

-- cùng một customer và cùng một ngày thì xác định là một giao dịch.
-- ---------------------------------------------------------------------------------------------------------------------------------------
-- 1.Viết truy vấn SQL để trả về thời lượng thuê trung bình cho từng tổ hợp diễn viên và danh mục trong cơ sở dữ liệu,
--  ngoại trừ các diễn viên chưa xuất hiện trong bất kỳ phim nào trong danh mục
select concat(a.first_name,' ',a.last_name) as actor_name, c.name as category_name,
		avg(timestampdiff(hour, r.rental_date, r.return_date)) as avg_rental_duration
from actor a join film_actor fa on a.actor_id = fa.actor_id
			join film_category fc on fa.film_id = fc.film_id
            join category c on fc.category_id = c.category_id
            join inventory i on fc.film_id = i.film_id
            join rental r on i.inventory_id = r.inventory_id
group by actor_name, category_name
having count(distinct fc.film_id) > 0;

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- 2.Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong một bộ phim có xếp hạng 'R'
-- và thời lượng hơn 2 giờ, nhưng chưa bao giờ xuất hiện trong một bộ phim có xếp hạng 'G'.
select a.actor_id, concat(a.first_name,' ', a.last_name) as actor_name
from actor a join film_actor fa on a.actor_id = fa.actor_id
			join film f on f.film_id = fa.film_id
where f.rating = 'R' and a.actor_id not in (
											select a.actor_id
											from actor a join film_actor fa on a.actor_id = fa.actor_id
														 join film f on fa.film_id = f.film_id
											where f.rating = 'G'
											group by a.actor_id)
group by actor_id;
-- ----------------------------------------------------------------------------------------------------------------------------------------
-- ????????????????????????????????????????????????????????????????????
-- 3.Viết truy vấn SQL để trả về tên của tất cả khách hàng đã thuê hơn 10 bộ phim trong một giao dịch,
-- cùng với số lượng phim họ đã thuê và tổng phí thuê.
select c.customer_id,
		concat(c.first_name,' ',c.last_name) as customer_name,
        count(distinct r.inventory_id) as inventor_count,
        sum(p.amount) as total_rental_fee
from customer c join rental r on c.customer_id = r.customer_id
				join payment p on r.rental_id = p.rental_id
GROUP BY c.customer_id, date(r.rental_date)
having count(r.inventory_id) > 5;

SELECT customer_id, DATE(rental_date) AS transaction_date, COUNT(rental_id) AS number_of_rentals
FROM rental
GROUP BY customer_id, DATE(rental_date)
HAVING COUNT(rental_id) = 10;

SELECT c.customer_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       COUNT(DISTINCT r.rental_id) AS number_of_transactions,
       COUNT(r.inventory_id) AS number_of_rentals,
       SUM(p.amount) AS total_rental_fee
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN payment p ON r.rental_id = p.rental_id
GROUP BY c.customer_id, customer_name
HAVING COUNT(DISTINCT r.rental_id) > 10 AND COUNT(r.inventory_id) > 10;

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- ????????????????????????????
-- 4.Viết một truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê mọi bộ phim trong một danh mục,
-- cùng với tổng số phim đã thuê và tổng phí thuê
select category_id , count(film_id) from film_category group by category_id;
select c.customer_id, concat(c.first_name,' ',c.last_name) as customer_name,
		count( distinct i.inventory_id) as rented_film,
        sum(p.amount) as total_amount,
        fc.films
from customer c join rental r on c.customer_id = r.customer_id
				join inventory i on r.inventory_id = i.inventory_id
                join payment p on r.rental_id = p.rental_id
                join (select category_id , count(film_id) as films from film_category group by category_id) fc on rented_film = fc.films
group by customer_id;

SELECT c.customer_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       COUNT(DISTINCT r.inventory_id) AS total_rentals,
       SUM(p.amount) AS total_payment
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film_category fc ON i.film_id = fc.film_id
JOIN payment p ON r.rental_id = p.rental_id
WHERE fc.category_id = (SELECT category_id FROM category WHERE name = 'Action')
GROUP BY c.customer_id, customer_name
HAVING COUNT(DISTINCT r.inventory_id) = (SELECT COUNT(*) FROM film_category WHERE category_id = (SELECT category_id FROM category WHERE name = 'Action'));


-- 5.Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được cùng một khách hàng thuê nhiều lần trong một ngày,
--  cùng với tên của những khách hàng đã thuê phim và số lần họ được thuê.
select date(rental_date) from rental;
select f.film_id, f.title ,
		concat(c.first_name,' ',c.last_name) as customer_name,
        count(*) as rental_count
from film f join inventory i on f.film_id = i.film_id
			join rental r on i.inventory_id = r.inventory_id
            join customer c on r.customer_id = c.customer_id
where date(r.rental_date)  = '2005-05-25'
group by f.film_id, c.customer_id
having count(*)>1;

-- --------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 6.Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim
-- cùng với mọi diễn viên khác trong cơ sở dữ liệu, cùng với số lượng phim họ đã xuất hiện cùng nhau.
select concat(a1.first_name,' ',a1.last_name) as actor1_name,
	   concat(a2.first_name,' ',a2.last_name) as actor2_name,
       count(distinct fa1.film_id) as number_films
from actor a1 join film_actor fa1 on a1.actor_id = fa1.actor_id
			  join film_actor fa2 on fa1.film_id = fa2.film_id and fa1.actor_id <> fa2.actor_id
              join actor a2 on fa2.actor_id = a2.actor_id
group by actor1_name, actor2_name;

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 7.Viết truy vấn SQL để trả về tên của tất cả khách hàng đã thuê ít nhất một phim từ mỗi danh mục trong cơ sở dữ liệu,
-- cùng với số lượng phim đã thuê từ mỗi danh mục.
select c.customer_id, concat(c.first_name,' ',c.last_name) as customer_name,
		count(distinct i.film_id) as number_films
from customer c join rental r on c.customer_id = r.customer_id
				join inventory i on r.inventory_id = i.inventory_id
                join film_category fc on i.film_id = fc.film_id
group by c.customer_id
having count(distinct fc.category_id) = (select count(*) from category);

-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 8. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 100 lần,
--  nhưng chưa bao giờ được thuê bởi bất kỳ khách hàng nào đã thuê phim có xếp hạng 'G'.
select f.film_id, f.title , count( rental_id) as number_rentals
from film f join inventory i on f.film_id = i.film_id
			join rental r on i.inventory_id = r.inventory_id
where r.customer_id not in (
							-- id các khách hàng đã thuê phim có xếp hạng G
							select distinct customer_id
							from rental r join inventory i on r.inventory_id = i.inventory_id
										  join film f on i.film_id = f.film_id
							where rating = 'G')
group by f.film_id
having number_rentals > 100;
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 9.Viết truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê phim từ danh mục mà họ chưa bao giờ thuê trước đây
-- và cũng chưa bao giờ thuê phim dài hơn 3 giờ.
select c.customer_id, concat(c.first_name,' ',c.last_name) as customer_name
from customer c join rental r on c.customer_id = r.customer_id
				join inventory i on r.inventory_id = i.inventory_id
                join film_category fc on i.film_id = fc.film_id
where NOT EXISTS (
				select 1 from rental r2 join inventory i2 on r2.inventory_id = i2.inventory_id
										join film_category fc2 on i2.film_id = fc2.film_id
				where r2.customer_id = c.customer_id and fc2.category_id = fc.category_id and r2.rental_date < r.rental_date
                )
and c.customer_id in  (
				-- khách hàng thuê phim dài hơn 3 giờ
				select c.customer_id
				from customer c join rental r on c.customer_id = r.customer_id
								join inventory i on r.inventory_id = i.inventory_id
								join film f on i.film_id = f.film_id
				where f.length <= 180
				group by c.customer_id
				order by c.customer_id)
group by c.customer_id;

SELECT DISTINCT c.first_name, c.last_name
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category cat ON fc.category_id = cat.category_id
AND NOT EXISTS (
    SELECT 1
    FROM rental r2
    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
    JOIN film_category fc2 ON i2.film_id = fc2.film_id
    WHERE r2.customer_id = c.customer_id
    AND fc2.category_id = cat.category_id
    AND r2.rental_date < r.rental_date
);

-- -----------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 10.Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong một bộ phim có xếp hạng 'PG-13' và thời lượng hơn 2 giờ,
-- đồng thời cũng đã xuất hiện trong một bộ phim có xếp hạng 'R' và thời lượng dưới 90 phút
select a.actor_id, concat(a.first_name,' ',a.last_name) as actor_name
from actor a join film_actor fa on a.actor_id = fa.actor_id
			join film f on fa.film_id = f.film_id
where f.rating = 'PG-13' AND f.length > 120
						AND a.actor_id in ( select a.actor_id
											from actor a join film_actor fa on a.actor_id = fa.actor_id
														join film f on fa.film_id = f.film_id
											where rating = 'R' and f.length <90
											)
group by a.actor_id;



-- ----------------------------------------------------------------------------------------------------------------------------------

use sakila;

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- 1.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 100 lần,
-- đặt giá thuê mới cao hơn 10% so với giá hiện tại.
update film
set rental_rate = rental_rate * 110 /100
where film_id in (
					select f.film_id
                    from film f join inventory i on f.film_id = i.film_id
								join rental r on i.inventory_id = r.inventory_id
					group by f.film_id
                    having count(distinct r.rental_id) > 100
					); -- MySQL không cho phép thực hiện câu lệnh UPDATE trên một bange mà đã sử dụng trong câu lệnh con để lấy dữ liệu

UPDATE film AS f
JOIN (
    SELECT f.film_id
    FROM film f
    JOIN inventory i ON f.film_id = i.film_id
    JOIN rental r ON i.inventory_id = r.inventory_id
    GROUP BY f.film_id
    HAVING COUNT(DISTINCT r.rental_id) > 100
) AS subquery ON f.film_id = subquery.film_id
SET f.rental_rate = f.rental_rate * 110 / 100;

-- ------------
CREATE TEMPORARY TABLE temp_films AS
SELECT f.film_id
FROM film f
JOIN inventory i ON f.film_id = i.film_id
JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY f.film_id
HAVING COUNT(DISTINCT r.rental_id) > 100;

UPDATE film
SET rental_rate = rental_rate * 1.1
WHERE film_id IN (SELECT film_id FROM temp_films)
		AND film_id = film_id;

DROP TEMPORARY TABLE temp_films;
-- ----------- sử dụng bảng tạm thời thay vì truy vấn con

-- ---------------------------------------------------------------------------------------------------------------------------------
-- 2.Viết truy vấn SQL để cập nhật thời lượng thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 5 lần,
-- đặt thời lượng mới dài hơn 5% so với thời lượng hiện tại.
update film f join (
					select f.film_id
                    from film f join inventory i on f.film_id = i.film_id
								join rental r on i.inventory_id = r.inventory_id
					group by f.film_id
					having count(r.rental_id) > 5) sub on f.film_id = sub.film_id
set f.length = f.length * 1.05;
-- ---------------------------------------------------------------------------------------------------------------------------------------
-- 3.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong danh mục 'Action' được phát hành trước năm 2005,
-- đặt giá mới cao hơn 20% so với giá hiện tại.
update film f join(
					select f.film_id
					from film f join film_category fc on f.film_id =  fc.film_id
								join category c on fc.category_id = c.category_id
					where c.name = 'Action' and f.release_year < 2005) sub on f.film_id = sub.film_id
set f.rental_rate = f.rental_rate * 1.2;
-- --------------------------------------------------------------------------------------------------------------------------------------------
-- 4.Viết truy vấn SQL để cập nhật địa chỉ email của tất cả khách hàng đã thuê phim từ danh mục 'Horror' vào tháng 10 năm 2022,
-- đặt địa chỉ email mới là sự kết hợp giữa địa chỉ email hiện tại của họ và chuỗi 'Horror' .

update customer c join (
						-- id khách hàng thuê phim từ danh mục Horror vào tháng 10 năm 2022
						select c.customer_id
						from customer c join rental r on c.customer_id = r.customer_id
										join inventory i on r.inventory_id = i.inventory_id
										join film_category fc on i.film_id = fc.film_id
										join category ca on fc.category_id = ca.category_id
						where ca.name = 'Horror' and month(r.rental_date) = 10 and year(r.rental_date) = 2022 ) as sub
                        on c.customer_id = sub.customer_id
set c.email = concat(c.email, ' ', 'Horror');
-- ---------------------------------------------------------------------------------------------------------------------------------------------
-- 5.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được hơn 10 khách hàng thuê,
-- đặt giá mới cao hơn 5% so với giá hiện tại, nhưng không cao hơn $4,00.
update film f join (
						-- id film được hơn 10 khách hàng thuê
						select f.film_id
						from film f join inventory i on f.film_id = i.film_id
									join rental r on i.inventory_id = r.inventory_id
						group by f.film_id
						having count(distinct r.customer_id) > 10 ) sub
                        on f.film_id = sub.film_id
set f.rental_rate = case
						when f.rental_rate * 1.05 <= 4.00 then f.rental_rate * 1.05
                        else 4.00
					end;

-- ----------------------------------------------------------------------------------------------------------------------------------------------
-- 6.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'PG-13'
-- và thời lượng hơn 2 giờ, đặt giá mới là $3,5.
update film f join(
					select film_id from film where rating = 'PG-13' and length > 120
                    ) sub on f.film_id = sub.film_id
set rental_rate = 3.50;
-- --------------------------------------------------------------------------------------------------------------------------------------------------
-- 7.Viết truy vấn SQL để cập nhật thời lượng cho thuê của tất cả các phim trong danh mục 'Sci-Fi'
-- được phát hành vào năm 2010, đặt thời lượng mới bằng với thời lượng của phim tính bằng phút.
UPDATE film f join (
					select f.film_id
                    from film f join film_category fc on f.film_id = fc.film_id
								join category c on fc.category_id = c.category_id
					where c.name = 'Sci-Fi' and f.release_year = 2010 ) sub
                    on f.film_id = sub.film_id
SET rental_duration = length;

-- -------------------------------------------------------------------------------------------------------------------------------------------------
-- 8.Viết truy vấn SQL để cập nhật địa chỉ của tất cả các khách hàng sống trong cùng thành phố với một khách hàng khác có cùng họ,
-- đặt địa chỉ mới là phần nối của địa chỉ hiện tại của họ và chuỗi 'samecity'.
update customer c1 join customer c2 on c1.last_name =c2.last_name and c1.customer_id <> c2.customer_id
				   join address ad1 on c1.address_id = ad1.address_id
                   join address ad2 on c2.address_id = ad2.address_id
set ad1.address = concat(ad1.address, 'samecity')
where ad1.city_id = ad2.city_id;

-- ?
UPDATE customer c
JOIN address ad  ON ad.address_id = c.address_id
JOIN city ci ON ci.city_id = ad.city_id
JOIN country co ON co.country_id = ci.country_id
SET ad.address = CONCAT(ad.address, 'samecity')
WHERE c.last_name = c.last_name and ci.city = ci.city;
 -- ?
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- 9.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong danh mục 'Comedy' được phát hành vào năm 2007 trở đi,
-- đặt giá mới thấp hơn 15% so với giá hiện tại
update film f join (
					select f.film_id
                    from film f join film_category fc on f.film_id = fc.film_id
								join category c on fc.category_id = c.category_id
					where c.name = 'Comedy' and f.release_year >= 2007
					) sub on f.film_id = sub.film_id
set rental_rate = rental_rate * 0.85;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 10.Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'G' và thời lượng dưới 1 giờ,
-- đặt giá mới là $1,50.
update film f join (
					select film_id from film where rating = 'G' and length < 60
					) sub on f.film_id = sub.film_id
set rental_rate = 1.50;

-- ?????????-------------------------------------------------------------------------------------------------------------------------------------------------------
-- 11.Viết truy vấn SQL để cập nhật mức lương của tất cả nhân viên trong cơ sở dữ liệu
-- dựa trên chức danh công việc và số năm kinh nghiệm của họ,
-- đặt mức lương mới bằng với mức lương hiện tại nhân với hệ số phụ thuộc vào chức danh công việc và số năm kinh nghiệm của họ


-- ??????????------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 12.Viết truy vấn SQL để cập nhật số lượng của tất cả các sản phẩm trong cơ sở dữ liệu dựa trên số lượng hiện tại của chúng
-- và số lượng đơn đặt hàng đã được đặt cho sản phẩm đó, đặt số lượng mới bằng số lượng hiện tại trừ đi số lượng đơn đặt hàng đã đặt được đặt cho sản phẩm đó.





