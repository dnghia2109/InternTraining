USE sakila;

-- 1.Write a SQL query to update the rental rate of all films in the database that have been rented more than 100 times,
-- setting the new rental rate to be 10% higher than the current rate.
WITH cte_film_rent_more_100 AS (
    SELECT f.film_id
    FROM film f
    INNER JOIN inventory i on f.film_id = i.film_id
    INNER JOIN rental r on i.inventory_id = r.inventory_id
    GROUP BY f.film_id
    HAVING count(r.rental_id) > 33
)
UPDATE film f2
SET rental_rate = rental_rate * 1.1
WHERE film_id IN (SELECT * FROM cte_film_rent_more_100);



-- 2.Write a SQL query to update the rental duration of all films in the database that have been rented more than 5
-- times, setting the new duration to be 5% longer than the current duration.
WITH cte_film_rent_more_5 AS (
    SELECT f.film_id
    FROM film f
    INNER JOIN inventory i on f.film_id = i.film_id
    INNER JOIN rental r on i.inventory_id = r.inventory_id
    GROUP BY f.film_id
    HAVING count(r.rental_id) > 5
)
UPDATE film f2
SET length = length * 1.05
WHERE film_id IN (SELECT * FROM cte_film_rent_more_5);


-- 3.Write a SQL query to update the rental rate of all films in the 'Action' category that were released before the
-- year 2005, setting the new rate to be 20% higher than the current rate.
WITH cte_film_category_is_action AS (
    SELECT f.film_id
    FROM film f
    INNER JOIN film_category fc on f.film_id = fc.film_id
    INNER JOIN category c on fc.category_id = c.category_id
    WHERE c.name = 'Action' AND f.release_year < 2005
)
UPDATE film f2
SET rental_rate = rental_rate * 1.2
WHERE film_id IN (SELECT * FROM cte_film_category_is_action);


-- 4.Write a SQL query to update the email address of all customers who have rented a film from the 'Horror' category
-- in the month of October 2022, setting the new email address to be a combination of their current email address
-- and the string 'horrorlover'.
UPDATE customer c
INNER JOIN (
    SELECT c.customer_id
    FROM customer c
    JOIN rental r on c.customer_id = r.customer_id
    JOIN inventory i on r.inventory_id = i.inventory_id
    JOIN film_category fc on i.film_id = fc.film_id
    JOIN category ca on fc.category_id = ca.category_id
    WHERE ca.name = 'Horror' AND month(r.rental_date) = 10 AND year(r.rental_date) = 2022
) AS sub ON c.customer_id = sub.customer_id
SET c.email = concat(c.email, ' ', 'horrorlover');



-- 5.Write a SQL query to update the rental rate of all films in the database that have been rented by more than
-- 10 customers, setting the new rate to be 5% higher than the current rate, but not higher than $4.00.
UPDATE film f
INNER JOIN (
    SELECT f2.film_id
    FROM film f2
    INNER JOIN inventory i on f2.film_id = i.film_id
    INNER JOIN rental r on i.inventory_id = r.inventory_id
    GROUP BY f2.film_id
    HAVING count(r.customer_id) > 10
) AS sub ON sub.film_id = f.film_id
SET f.rental_rate = (
                    CASE
						WHEN f.rental_rate * 1.05 <= 4.00 THEN f.rental_rate * 1.05
                        ELSE 4.00
					END);




-- 6.Write a SQL query to update the rental rate of all films in the database that have a rating of 'PG-13' and a
-- length of more than 2 hours, setting the new rate to be $3.50.
UPDATE film f
SET f.rental_rate = 3.50
WHERE f.rating = 'PG-13' AND f.length > 120;


-- 7.Write a SQL query to update the rental duration of all films in the 'Sci-Fi' category that were released in the
-- year 2010, setting the new duration to be equal to the length of the film in minutes.
UPDATE film f
INNER JOIN (
    SELECT f.film_id
    FROM film f
    INNER JOIN film_category fc ON f.film_id = fc.film_id
    INNER JOIN category c on fc.category_id = c.category_id
    WHERE c.name = 'Sci-Fi' AND f.release_year = 2010
) AS sub ON f.film_id = sub.film_id
SET f.rental_duration = f.length;



-- 8.Write a SQL query to update the address of all customers who live in the same city as another customer with the
-- same last name, setting the new address to be the concatenation of their current address and the string 'samecity'.
UPDATE customer c1
    JOIN customer c2 ON c1.last_name = c2.last_name AND c1.customer_id <> c2.customer_id
	JOIN address ad1 ON c1.address_id = ad1.address_id
    JOIN address ad2 ON c2.address_id = ad2.address_id
SET ad1.address = concat(ad1.address, 'samecity')
WHERE ad1.city_id = ad2.city_id;


-- 9.Write a SQL query to update the rental rate of all films in the 'Comedy' category that were released in the
-- year 2007 or later, setting the new rate to be 15% lower than the current rate.
# UPDATE film
# SET rental_rate = rental_rate * 0.85
# WHERE film_id IN (
#     SELECT f2.film_id
#     FROM film f2
#     INNER JOIN film_category fc ON f2.film_id = fc.film_id
#     INNER JOIN category c ON fc.category_id = c.category_id
#     WHERE c.name = 'Comedy' AND YEAR(f2.release_year) >= 2007
# );

UPDATE film f
INNER JOIN (
    SELECT f.film_id
    FROM film f
    JOIN film_category fc ON f.film_id = fc.film_id
    JOIN category c ON fc.category_id = c.category_id
    WHERE c.name = 'Comedy' AND f.release_year >= 2007
) sub ON f.film_id = sub.film_id
SET rental_rate = rental_rate * 0.85;



-- 10.Write a SQL query to update the rental rate of all films in the database that have a rating of 'G'
-- and a length of less than 1 hour, setting the new rate to be $1.50.
UPDATE film f
INNER JOIN (
    SELECT film_id
    FROM film
    WHERE rating = 'G' AND length < 60
) sub ON f.film_id = sub.film_id
SET rental_rate = 1.50;
