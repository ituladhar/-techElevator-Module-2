-- 21. For every person in the person table with the first name of "George", list the number of movies they've been in--include all Georges, even those that have not appeared in any movies. Display the names in alphabetical order. (59 rows)
-- Name the count column 'num_of_movies'
SELECT p.person_name, COUNT(m.title) AS num_of_movies
FROM movie AS m
	JOIN movie_actor AS ma USING (movie_id)
	FULL JOIN person AS p ON ma.actor_id = p.person_id 
WHERE p.person_name LIKE 'George %'
GROUP by p.person_id
Order by p.person_name