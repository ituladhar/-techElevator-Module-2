-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'. 
-- (19 rows, expected result for Action is around 180).
SELECT g.genre_name, count(m.title) AS num_of_movies
FROM movie AS m
	JOIN movie_genre AS mg USING (movie_id)
	JOIN genre AS g  USING (genre_id)
GROUP BY g.genre_name;
