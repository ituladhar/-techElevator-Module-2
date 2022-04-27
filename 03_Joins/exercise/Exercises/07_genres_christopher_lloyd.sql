-- 7. The genres of movies that Christopher Lloyd has appeared in (8 rows)
-- Hint: DISTINCT will prevent duplicate values in your query results.
SELECT DISTINCT g.genre_name 
FROM person AS p
	JOIN movie_actor AS ma ON ma.actor_id = p.person_id
	JOIN movie AS m ON m.movie_id = ma.movie_id
	JOIN movie_genre AS mg ON mg.movie_id = m.movie_id
	JOIN genre AS g ON g.genre_id = mg.genre_id
WHERE p.person_name = 'Christopher Lloyd'