-- 6. The genres of "The Wizard of Oz" (3 rows)
SELECT  g.genre_name
FROM movie AS m
	JOIN movie_genre AS mg ON mg.movie_id = m.movie_id
 	JOIN genre AS g ON mg.genre_id = g.genre_id
WHERE m.title ='The Wizard of Oz';

