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


SELECT CONCAT(a.first_name,' ',a.last_name) AS actor_name, c.name AS category_name,
		AVG(TIMESTAMPDIFF(hour, r.rental_date, r.return_date)) as avg_rental_duration
FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id
			JOIN film_category fc ON fa.film_id = fc.film_id
            JOIN category c ON fc.category_id = c.category_id
            JOIN inventory i ON fc.film_id = i.film_id
            JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY actor_name, category_name
HAVING COUNT(DISTINCT fc.film_id) > 0;


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
-- in a single transaction, along with the number of films they rented and the total rental fee. (đề lỗi)
SELECT c.customer_id, CONCAT(c.first_name,' ',c.last_name) AS full_name, COUNT(r.rental_id) AS total_film_rental, SUM(p.amount) AS total_fee
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN payment p ON r.rental_id = p.rental_id
GROUP BY c.customer_id,full_name
HAVING COUNT(r.rental_id) > 10;

SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date), COUNT(*) AS number_films, SUM(p.amount) AS total_rental_fee
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN payment p on r.rental_id = p.rental_id
GROUP BY c.customer_id, DATE(r.rental_date)
HAVING COUNT(*) > 5;


# -- 3. danh sách các customer thuê hơn 10 bộ phim 1 ngày
# SELECT c.customer_id, CONCAT(c.first_name,' ',c.last_name) AS full_name, COUNT(p.rental_id)
# FROM customer c
# INNER JOIN rental r ON c.customer_id = r.customer_id
# INNER JOIN payment p ON r.rental_id = p.rental_id
# INNER JOIN rental r2 ON r.rental_date = r2.rental_date
# WHERE r2.customer_id = c.customer_id
# GROUP BY c.customer_id, full_name;
#
#
# SELECT c.customer_id, c.first_name, c.last_name, DATE(r.rental_date) AS rental_day, COUNT(r.rental_id) AS rental_count
# FROM customer c
# JOIN rental r ON c.customer_id = r.customer_id
# GROUP BY c.customer_id, c.first_name, c.last_name, rental_day
# HAVING rental_count > 3;
#
# select customer_id, group_concat(rental_id)
# from rental
# where DATE(rental_date) = '2005-06-15'
# GROUP BY customer_id;
#
# SELECT c.customer_id, c.first_name, c.last_name, DATE(r1.rental_date) AS rental_day, COUNT(r1.rental_id) AS rental_count
# FROM customer c
# JOIN rental r1 ON c.customer_id = r1.customer_id
# JOIN rental r2 ON r1.rental_id <> r2.rental_id AND DATE(r1.rental_date) = DATE(r2.rental_date) AND c.customer_id = r2.customer_id
# GROUP BY c.customer_id, c.first_name, c.last_name, rental_day
# HAVING rental_count > 3;



-- 4.Write a SQL query to return the names of all customers who have rented every film in a category,
-- along with the total number of films rented and the total rental fee.


select c.customer_id, GROUP_CONCAT(DISTINCT fc.category_id)
from customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film_category fc ON i.film_id = fc.film_id
GROUP BY c.customer_id;


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
GROUP BY c.customer_id
HAVING COUNT(DISTINCT i.film_id) = (
    SELECT COUNT(fc.film_id)
    FROM film_category
    WHERE category_id = (
        SELECT category_id
        FROM category
        WHERE name = 'Action'));


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
GROUP BY c.customer_id, f.film_id, rental_date;



SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date) AS date_of_renting,
       f.title , COUNT(*) AS rent_times
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film f ON i.film_id = f.film_id
GROUP BY c.customer_id, DATE(r.rental_date), f.film_id
HAVING COUNT(*) > 1;


# SELECT STR_TO_DATE('21,5,2013','%d,%m,%Y');
# SELECT DATE(r.rental_date)
# FROM rental r
# WHERE r.rental_date =



-- 6.Write a SQL query to return the names of all actors who have appeared in at least one film with
-- every other actor in the database, along with the number of films they appeared in together.
SELECT f.film_id, group_concat(a.actor_id), count(a.actor_id)
FROM actor a
INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
INNER JOIN film f ON fa.film_id = f.film_id
INNER JOIN actor a2 ON a.actor_id = a2.actor_id
INNER JOIN film_actor fa2 ON a2.actor_id <> fa2.actor_id
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
HAVING count(r.rental_id) > 100;

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
        WHERE f.rating = 'R' AND f.length < 90
    )
GROUP BY a.actor_id;





