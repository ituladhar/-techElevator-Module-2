-- 17. The titles and taglines of movies that are in the "Family" genre and Samuel L. Jackson has acted in (4 rows)
SELECT m.title, m.tagline
FROM genre AS g
	JOIN movie_genre AS mg 
		USING (genre_id)
	JOIN movie AS m 
		USING (movie_id)
	JOIN movie_actor AS ma 
		USING (movie_id)
	JOIN person AS p 
		ON p.person_id = ma.actor_id
WHERE g.genre_name = 'Family'
	And p.person_name='Samuel L. Jackson';

