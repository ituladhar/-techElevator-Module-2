-- 21. For every person in the person table with the first name of "George", list the number of movies they've been in--include all Georges, even those that have not appeared in any movies. Display the names in alphabetical order. (59 rows)
-- Name the count column 'num_of_movies'
/*SELECT p.person_name, COUNT(m.title) AS num_of_movies
FROM movie AS m
	LEFT JOIN movie_actor AS ma ON ma.movie_id = m.movie_id
	full JOIN person AS p ON ma.actor_id = p.person_id 
WHERE p.person_name LIKE 'George%'
GROUP BY p.person_name;
*/
SELECT p.person_name, COUNT(m.title) AS num_of_movies
FROM person AS p
	 LEFT JOIN movie_actor AS ma ON ma.actor_id = p.person_id 
	 LEFT JOIN movie AS m ON m.movie_id = ma.movie_id
	WHERE p.person_name LIKE 'George%'
GROUP BY p.person_name;

