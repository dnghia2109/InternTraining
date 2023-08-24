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
@RequestMapping("api/v1/mysql/lv03")
public class QueryLevel03 {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/ex1")
    public ResponseEntity<?> ex1() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(a.first_name,' ',a.last_name) AS actor_name, c.name AS category_name,\n" +
                                "\t\tAVG(TIMESTAMPDIFF(hour, r.rental_date, r.return_date)) as avg_rental_duration\n" +
                                "FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "\t\t\tJOIN film_category fc ON fa.film_id = fc.film_id\n" +
                                "            JOIN category c ON fc.category_id = c.category_id\n" +
                                "            JOIN inventory i ON fc.film_id = i.film_id\n" +
                                "            JOIN rental r ON i.inventory_id = r.inventory_id\n" +
                                "GROUP BY actor_name, category_name\n" +
                                "HAVING COUNT(DISTINCT fc.film_id) > 0"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex2")
    public ResponseEntity<?> ex2() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(a.first_name, ' ', a.last_name) AS `full_name`\n" +
                                "FROM actor a\n" +
                                "INNER JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "INNER JOIN film f ON fa.film_id = f.film_id\n" +
                                "WHERE f.rating = 'R' AND f.length > 120 AND a.actor_id NOT IN (\n" +
                                "    SELECT a.actor_id\n" +
                                "    FROM actor a\n" +
                                "    INNER JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "    INNER JOIN film f ON fa.film_id = f.film_id\n" +
                                "    WHERE f.rating = 'G'\n" +
                                "    )\n" +
                                "GROUP BY a.actor_id;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex3")
    public ResponseEntity<?> ex3() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date), COUNT(*) AS number_films, SUM(p.amount) AS total_rental_fee\n" +
                                "FROM customer c\n" +
                                "INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "INNER JOIN payment p on r.rental_id = p.rental_id\n" +
                                "GROUP BY c.customer_id, DATE(r.rental_date)\n" +
                                "HAVING COUNT(*) > 5;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex4")
    public ResponseEntity<?> ex4() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT c.customer_id,\n" +
                                "       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,\n" +
                                "       COUNT(DISTINCT r.inventory_id) AS total_rentals,\n" +
                                "       SUM(p.amount) AS total_payment\n" +
                                "FROM customer c\n" +
                                "JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                                "JOIN film_category fc ON i.film_id = fc.film_id\n" +
                                "JOIN payment p ON r.rental_id = p.rental_id\n" +
                                "WHERE fc.category_id = (SELECT category_id FROM category WHERE name = 'Action')\n" +
                                "GROUP BY c.customer_id\n" +
                                "HAVING COUNT(DISTINCT i.film_id) = (\n" +
                                "    SELECT COUNT(fc.film_id)\n" +
                                "    FROM film_category\n" +
                                "    WHERE category_id = (\n" +
                                "        SELECT category_id\n" +
                                "        FROM category\n" +
                                "        WHERE name = 'Action'));"
                );
        return ResponseEntity.ok(rs);
    }


    @GetMapping("/ex5")
    public ResponseEntity<?> ex5() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date) AS date_of_renting, \n" +
                                "       f.title , COUNT(*) AS number_of_times_renting\n" +
                                "FROM customer c\n" +
                                "INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "INNER JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                                "INNER JOIN film f ON i.film_id = f.film_id\n" +
                                "GROUP BY c.customer_id, DATE(r.rental_date), f.film_id\n" +
                                "HAVING COUNT(*) > 1;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex6")
    public ResponseEntity<?> ex6() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date) AS date_of_renting, \n" +
                                "       f.title , COUNT(*) AS number_of_times_renting\n" +
                                "FROM customer c\n" +
                                "INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "INNER JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                                "INNER JOIN film f ON i.film_id = f.film_id\n" +
                                "GROUP BY c.customer_id, DATE(r.rental_date), f.film_id\n" +
                                "HAVING COUNT(*) > 1;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex7")
    public ResponseEntity<?> ex7() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT CONCAT(c.first_name, ' ', c.last_name) AS full_name, DATE(r.rental_date) AS date_of_renting, \n" +
                                "       f.title , COUNT(*) AS rent_times\n" +
                                "FROM customer c\n" +
                                "INNER JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "INNER JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                                "INNER JOIN film f ON i.film_id = f.film_id\n" +
                                "GROUP BY c.customer_id, DATE(r.rental_date), f.film_id\n" +
                                "HAVING COUNT(*) > 1;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex8")
    public ResponseEntity<?> ex8() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT f.title\n" +
                                "FROM film f\n" +
                                "INNER JOIN inventory i on f.film_id = i.film_id\n" +
                                "INNER JOIN rental r on i.inventory_id = r.inventory_id\n" +
                                "WHERE r.customer_id NOT IN (\n" +
                                "    SELECT DISTINCT r2.customer_id\n" +
                                "    FROM rental r2\n" +
                                "    INNER JOIN inventory i2 on r2.inventory_id = i2.inventory_id\n" +
                                "    INNER JOIN film f2 on i2.film_id = f2.film_id\n" +
                                "    WHERE f2.rating = 'G'\n" +
                                ")\n" +
                                "GROUP BY f.film_id\n" +
                                "HAVING count(r.rental_id) > 100;"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex9")
    public ResponseEntity<?> ex9() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT concat(c.first_name,' ',c.last_name) AS customer_name\n" +
                                "FROM customer c\n" +
                                "JOIN rental r ON c.customer_id = r.customer_id\n" +
                                "JOIN inventory i ON r.inventory_id = i.inventory_id\n" +
                                "JOIN film_category fc ON fc.film_id = i.film_id\n" +
                                "JOIN film f ON f.film_id = fc.film_id\n" +
                                "WHERE f.length <= 180 AND fc.category_id NOT IN (\n" +
                                "    SELECT fc1.category_id\n" +
                                "    FROM film_category fc1\n" +
                                "    INNER JOIN inventory i1 ON fc1.film_id = i1.film_id\n" +
                                "    INNER JOIN rental r1 ON i1.inventory_id = r1.inventory_id\n" +
                                "    INNER JOIN customer c1 ON r1.customer_id = c1.customer_id\n" +
                                "    WHERE c1.customer_id = c.customer_id\n" +
                                ");"
                );
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/ex10")
    public ResponseEntity<?> ex10() {
        List<Map<String, Object>> rs =
                jdbcTemplate.queryForList(
                        "SELECT a.actor_id, concat(a.first_name,' ',a.last_name) AS actor_name\n" +
                                "FROM actor a\n" +
                                "INNER JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "INNER JOIN film f ON fa.film_id = f.film_id\n" +
                                "WHERE f.rating = 'PG-13'\n" +
                                "    AND f.length > 120\n" +
                                "    AND a.actor_id IN (\n" +
                                "        SELECT a.actor_id\n" +
                                "        FROM actor a\n" +
                                "        INNER JOIN film_actor fa ON a.actor_id = fa.actor_id\n" +
                                "        INNER JOIN film f ON fa.film_id = f.film_id\n" +
                                "        WHERE f.rating = 'R' AND f.length < 90\n" +
                                "    )\n" +
                                "GROUP BY a.actor_id;"
                );
        return ResponseEntity.ok(rs);
    }

}
