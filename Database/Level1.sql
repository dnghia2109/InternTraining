use sakila;
-- 1. Write a SQL query to return the first and last names of all actors in the database.
SELECT a.first_name , a.last_name
FROM actor a ;

-- 2. Write a SQL query to return the titles of all films in the database,
-- along with their rental rates and replacement costs.
SELECT f.title, f.rental_rate, f.replacement_cost
FROM film f;

-- 3. Write a SQL query to return the top 5 most rented films in the database,
-- along with the number of times they have been rented.
SELECT f.film_id, f.title, count(r.rental_id) AS `Count rental`, group_concat(DISTINCT r.rental_id)
FROM film f
LEFT JOIN inventory i ON f.film_id = i.film_id
LEFT JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY f.film_id
ORDER BY `Count rental` DESC
LIMIT 5;

-- 4. Write a SQL query to return the average rental duration for each category of film in the database.
SELECT c.category_id, AVG(f.rental_duration)
FROM category c
LEFT JOIN film_category fc ON c.category_id = fc.category_id
LEFT JOIN film f ON f.film_id = fc.film_id
GROUP BY c.category_id;


-- 5. Write a SQL query to return the names and addresses of all customers who have rented a film in the month of January 2022.
SELECT  DISTINCT c.customer_id,  CONCAT(c.first_name,' ', c.last_name) AS `Name`, r.rental_id,  a.address, address2
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN address a ON c.address_id = a.address_id
WHERE YEAR(rental_date) = 2005 AND MONTH(rental_date) = 5;

-- 6. Write a SQL query to return the revenue generated by each store
-- in the database for the year 2021.

SELECT st.store_id,
	   CONCAT(a.address,', ',a.district,', ',c.city) AS store_address,
       SUM(p.amount) as amount_2005
FROM payment p JOIN staff sf ON p.staff_id = sf.staff_id
			   JOIN store st ON sf.store_id = st.store_id
               JOIN address a ON st.address_id = a.address_id
               JOIN city c ON a.city_id = c.city_id
WHERE YEAR(payment_date) = 2005
GROUP BY st.store_id;

-- SELECT st.store_id, SUM(p.amount) as sum
-- FROM payment p
-- left JOIN rental r ON p.rental_id = r.rental_id
-- left JOIN staff s ON s.staff_id = r.staff_id
-- left JOIN store st ON s.store_id = st.store_id
-- GROUP BY st.store_id
-- ORDER BY SUM(p.amount) DESC


-- 7.Write a SQL query to return the names of all actors who have
-- appeared in more than 20 films in the database.
SELECT * FROM film_actor;
SELECT a.actor_id,
		CONCAT(a.first_name,' ',a.last_name) AS actor_name,
        COUNT(film_id) AS films
FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id
GROUP BY a.actor_id
HAVING films > 20;

-- 8.Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'PG-13' và thời lượng hơn 120 phút.
SELECT title, `length`, rating
FROM film f
WHERE f.rating = 'PG-13' AND LENGTH > 120;

# WITH TMP AS (
# 	SELECT c.customer_id , CONCAT(c.first_name , ' ', c.last_name) AS FULL_NAME, i.film_id
# 	FROM customer c
# 	INNER JOIN rental r ON r.customer_id = c.customer_id
# 	INNER JOIN inventory i ON i.inventory_id = r.inventory_id
# 	INNER JOIN payment p ON p.rental_id = r.rental_id
# 	GROUP BY c.customer_id , p.payment_id , i.film_id
# 	HAVING COUNT(i.inventory_id) > 1
# )
# SELECT TMP.*, COUNT(i.inventory_id) AS 'RentedCount'
# FROM TMP
# INNER JOIN rental r ON r.customer_id = TMP.customer_id
# INNER JOIN inventory I ON i.inventory_id = r.inventory_id AND i.film_id = TMP.film_id
# GROUP BY TMP.customer_id, TMP.full_name, TMP.film_id;


