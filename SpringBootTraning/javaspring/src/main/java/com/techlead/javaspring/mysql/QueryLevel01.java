package com.techlead.javaspring.mysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/mysql/lv01")
public class QueryLevel01 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 1. Write a SQL query to return the first and last names of all actors in the database.
    @GetMapping("/actors/name")
    public ResponseEntity<?> getAllActorName() {
        List<Map<String, Object>> rs = jdbcTemplate.queryForList("SELECT a.first_name , a.last_name FROM actor a");
        return ResponseEntity.ok(rs);
    }


    //2. Write a SQL query to return the titles of all films in the database,
    //along with their rental rates and replacement costs.
    @GetMapping("/films")
    public ResponseEntity<?> getFilmTitle_RentalRate_ReplacementCost() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList("SELECT f.title, f.rental_rate, f.replacement_cost FROM film f;");
        return ResponseEntity.ok(rs);
    }

    //3. Write a SQL query to return the top 5 most rented films in the database,
    //along with the number of times they have been rented.
    @GetMapping("/ex3")
    public ResponseEntity<?> ex3() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT f.film_id, f.title, count(r.rental_id) AS `Count rental`, group_concat(DISTINCT r.rental_id)\n" +
                        "    FROM film f\n" +
                        "    LEFT JOIN inventory i ON f.film_id = i.film_id\n" +
                        "    LEFT JOIN rental r ON i.inventory_id = r.inventory_id\n" +
                        "    GROUP BY f.film_id\n" +
                        "    ORDER BY `Count rental` DESC\n" +
                        "    LIMIT 5;"
                );
        return ResponseEntity.ok(rs);
    }

    //4. Write a SQL query to return the average rental duration for each category of film in the database.
    @GetMapping("/ex4")
    public ResponseEntity<?> ex4() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT c.category_id, AVG(f.rental_duration)\n" +
                                "    FROM category c\n" +
                                "    LEFT JOIN film_category fc ON c.category_id = fc.category_id\n" +
                                "    LEFT JOIN film f ON f.film_id = fc.film_id\n" +
                                "    GROUP BY c.category_id;"
                );
        return ResponseEntity.ok(rs);
    }

    // 5. Write a SQL query to return the names and addresses of all customers who have rented a film in the month of January 2022.
    @GetMapping("/ex5")
    public ResponseEntity<?> ex5() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT  DISTINCT c.customer_id,  CONCAT(c.first_name,' ', c.last_name) AS `Name`, r.rental_id,  a.address, address2\n" +
                                "    FROM customer c\n" +
                                "    INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "    INNER JOIN address a ON c.address_id = a.address_id\n" +
                                "    WHERE YEAR(rental_date) = 2005 AND MONTH(rental_date) = 5;"
                );
        return ResponseEntity.ok(rs);
    }

    //6. Write a SQL query to return the revenue generated by each store in the database for the year 2021.
    @GetMapping("/ex6")
    public ResponseEntity<?> ex6() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT st.store_id,\n" +
                                "    CONCAT(a.address,', ',a.district,', ',c.city) AS store_address,\n" +
                                "    SUM(p.amount) as amount_2005\n" +
                                "    FROM payment p JOIN staff sf ON p.staff_id = sf.staff_id\n" +
                                "    JOIN store st ON sf.store_id = st.store_id\n" +
                                "    JOIN address a ON st.address_id = a.address_id\n" +
                                "    JOIN city c ON a.city_id = c.city_id\n" +
                                "    WHERE YEAR(payment_date) = 2005\n" +
                                "    GROUP BY st.store_id;"
                );
        return ResponseEntity.ok(rs);
    }

    // 7.Write a SQL query to return the names of all actors who have
    // appeared in more than 20 films in the database.
    @GetMapping("/ex7")
    public ResponseEntity<?> ex7() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT * FROM film_actor;\n" +
                                "    SELECT a.actor_id,\n" +
                                "    CONCAT(a.first_name,' ',a.last_name) AS actor_name,\n" +
                                "    COUNT(film_id) AS films\n" +
                                "    FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "    GROUP BY a.actor_id\n" +
                                "    HAVING films > 20;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex8")
    public ResponseEntity<?> ex8() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT title, `length`, rating\n" +
                                "FROM film f\n" +
                                "WHERE f.rating = 'PG-13' AND LENGTH > 120;"
                );
        return ResponseEntity.ok(rs);
    }
}
