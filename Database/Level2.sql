USE sakila;

-- 1.Write a SQL query to return the top 10 customers who have generated the most revenue
-- for the store, including their names and total revenue generated.
SELECT c.customer_id, CONCAT(c.first_name, ' ', c.last_name) AS customer_name, SUM(p.amount) AS total_revenue
FROM customer c
JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, customer_name
ORDER BY total_revenue DESC
LIMIT 10;



-- 2.Write a SQL query to return the names and contact information of all customers
-- who have rented films in all categories in the database.
-- b1: Lấy ra ds các category mà customer đã thuê
SELECT c.customer_id, GROUP_CONCAT(DISTINCT fc.category_id)
FROM inventory i
JOIN rental r ON i.inventory_id = r.inventory_id
JOIN customer c ON r.customer_id = c.customer_id
JOIN film_category fc ON i.film_id = fc.film_id
GROUP BY c.customer_id
HAVING (COUNT(DISTINCT fc.category_id) = (SELECT COUNT(1) FROM category c));



SELECT c.customer_id AS `id`, CONCAT(c.first_name, ' ', c.last_name) AS `Name`, c.email,
       CONCAT(a2.address, ', ', a2.district) AS `Address`
FROM customer c
LEFT JOIN address a2 ON a2.address_id = c.address_id
# LEFT JOIN city c2 ON a2.city_id = c2.city_id
# LEFT JOIN country c3 ON c2.country_id = c3.country_id
WHERE c.customer_id IN (
	SELECT c.customer_id
	FROM inventory i
	JOIN rental r ON i.inventory_id = r.inventory_id
	JOIN customer c ON r.customer_id = c.customer_id
	JOIN film_category fc ON i.film_id = fc.film_id
	GROUP BY c.customer_id
	HAVING (COUNT(DISTINCT fc.category_id) = (SELECT COUNT(1) FROM category c))
);


-- 3. Write a SQL query to return the titles of all films in the database that have been rented
-- at least once but never returned.
SELECT f.title
FROM film f
INNER JOIN inventory i ON f.film_id = i.film_id
INNER JOIN rental r ON i.inventory_id = r.inventory_id
WHERE r.return_date IS NULL;


-- 4. Write a SQL query to return the names of all actors who have appeared in at least one film
-- in each category in the database.
-- b1: lấy ra ds actor có full cate


# WITH `temp_cte` AS (
# 	SELECT fa.actor_id , GROUP_CONCAT(DISTINCT fc.category_id)
# FROM film_actor fa
# INNER JOIN film_category fc ON fa.film_id = fc.film_id
# GROUP BY fa.actor_id
# HAVING COUNT(DISTINCT fc.category_id) = (SELECT COUNT(1) FROM category c)
# )
# SELECT * FROM `temp_cte`;
SELECT CONCAT(a.first_name, ' ', a.last_name)
FROM actor a
WHERE a.actor_id IN (
	SELECT fa.actor_id
	FROM film_actor fa
	INNER JOIN film_category fc ON fa.film_id = fc.film_id
	GROUP BY fa.actor_id
	HAVING COUNT(DISTINCT fc.category_id) = (SELECT COUNT(1) FROM category c)
);

# SELECT *
# FROM category c;


-- 5.Write a SQL query to return the names of all customers who have rented the same film more than once
-- in a single transaction, along with the number of times they rented it.



-- 6.Write a SQL query to return the total revenue generated by each actor in the database,
-- based on the rental fees of the films they have appeared in.
SELECT a.actor_id, SUM(p.amount) AS revenue
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN inventory i ON fa.film_id = i.film_id
INNER JOIN rental r ON i.inventory_id = r.inventory_id
INNER JOIN payment p ON r.rental_id = p.rental_id
GROUP BY a.actor_id;


-- 7.Write a SQL query to return the names of all actors who have appeared in at least one film
-- with a rating of 'R', but have never appeared in a film with a rating of 'G'.
SELECT a.actor_id
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN film f ON fa.film_id = f.film_id
WHERE f.rating = 'R' AND f.rating != 'G'
GROUP BY a.actor_id;

# SELECT film_id, rating
# FROM film
# WHERE rating = 'G';



-- 8.Write a SQL query to return the titles of all films in the database that have been rented by
-- more than 50 customers, but have never been rented by the same customer more than once.
-- b1: Lấy ds phim đc thuê chỉ 1 lần bởi mỗi khách hàng
SELECT f.title,COUNT(DISTINCT r.customer_id)
FROM film f
INNER JOIN inventory i ON f.film_id = i.film_id
INNER JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY f.film_id
HAVING COUNT(DISTINCT r.customer_id) > 30;



-- 9.Write a SQL query to return the names of all customers who have rented a film from a category they have
-- never rented from before.
SELECT CONCAT(c.first_name,' ', c.last_name) AS full_name
FROM customer c  JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON i.inventory_id = r.inventory_id
JOIN film f ON i.film_id = f.film_id
WHERE f.film_id NOT IN (
	SELECT i2.film_id
    FROM customer c2
    JOIN rental r2 ON c2.customer_id = r2.customer_id
    JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
    -- JOIN film f2 ON i2.film_id = f2.film_id
    WHERE c2.customer_id = c.customer_id
);


-- Sửa lại
select c.customer_id, concat(c.first_name, ' ', c.last_name) AS full_name
from customer c
inner join rental r on c.customer_id = r.customer_id
inner join inventory i on r.inventory_id = i.inventory_id
inner join film f on i.film_id = f.film_id
inner join film_category fc on f.film_id = fc.film_id
GROUP BY c.customer_id, fc.category_id
HAVING COUNT(fc.category_id) = 1;




-- 10. Write a SQL query to return the titles of all films in the database that have been rented by
-- every customer who has ever rented a film from the 'Action' category.
-- b1: DS khách đã thuê các phim có cate = 'Action'
WITH cte_film_id AS (
	SELECT c.customer_id, GROUP_CONCAT(f.film_id) AS 'list_film', GROUP_CONCAT(f.title)
	FROM customer c
	INNER JOIN rental r ON c.customer_id = r.customer_id
	INNER JOIN inventory i ON r.inventory_id = i.inventory_id
	INNER JOIN film_category fc ON i.film_id = fc.film_id
	INNER JOIN film f ON fc.film_id = f.film_id
	WHERE fc.category_id = (SELECT category.category_id FROM category WHERE NAME = 'Action')
	GROUP BY c.customer_id
)
SELECT f.film_id, f.title
FROM film f
INNER JOIN inventory i2 ON f.film_id = i2.film_id
INNER JOIN rental r2 ON i2.inventory_id = r2.inventory_id
WHERE r2.customer_id IN (SELECT cte_film_id.customer_id FROM cte_film_id)
GROUP BY f.film_id, f.title;


SELECT category.category_id, group_concat(fc.film_id)
FROM category
INNER JOIN film_category fc ON category.category_id = fc.category_id
GROUP BY category.category_id;




WITH customers_rented_action_movies AS (
SELECT
	c.customer_id ,
	concat(c.first_name, ' ', c.last_name) AS full_name
FROM
	customer c
INNER JOIN rental r ON
	c.customer_id = r.customer_id
INNER JOIN inventory i ON
	r.inventory_id = i.inventory_id
INNER JOIN film f ON
	i.film_id = f.film_id
INNER JOIN film_category fc ON
	f.film_id = fc.film_id
INNER JOIN category c2 ON
	fc.category_id = c2.category_id
WHERE
	c2.name = 'Action'
GROUP BY
	c.customer_id)
,
customer_of_each_film AS (
SELECT
	f.film_id AS film_id ,
	f.title ,
	c.customer_id
FROM
	film f
INNER JOIN inventory i ON
	f.film_id = i.film_id
INNER JOIN rental r ON
	i.inventory_id = r.inventory_id
INNER JOIN customer c ON
	r.customer_id = c.customer_id
GROUP BY
	f.film_id,
	f.title ,
	c.customer_id )
SELECT
	f.title
FROM
	film f
WHERE
	NOT EXISTS ((
	SELECT
		customers_rented_action_movies.customer_id
	FROM
		customers_rented_action_movies)
EXCEPT (
SELECT
	customer_of_each_film.customer_id
FROM
	customer_of_each_film
WHERE
	f.film_id = customer_of_each_film.film_id));
# SELECT f.title
# FROM film f
# WHERE NOT EXISTS (
#     SELECT c.customer_id
#     FROM customer c
#     JOIN rental r ON c.customer_id = r.customer_id
#     JOIN inventory i ON r.inventory_id = i.inventory_id
#     JOIN film_category fc ON i.film_id = fc.film_id
#     JOIN category cat ON fc.category_id = cat.category_id
#     WHERE cat.name = 'Action' AND NOT EXISTS (
#         SELECT 1
#         FROM rental r2
#         JOIN inventory i2 ON r2.inventory_id = i2.inventory_id
#         WHERE c.customer_id = r2.customer_id AND i.film_id = i2.film_id
#     )
# );



SELECT a.actor_id, f.film_id, f.length, f.rating
FROM film f
INNER JOIN film_actor fa
    ON f.film_id = fa.film_id
INNER JOIN actor a
    ON fa.actor_id = a.actor_id
WHERE f.rating = 'R' AND f.length > 2 AND f.film_id NOT IN (
    SELECT f.film_id
    FROM film f
    WHERE f.rating = 'G'
)
GROUP BY a.actor_id, f.film_id;