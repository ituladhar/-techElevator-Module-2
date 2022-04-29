-- 18. The average length of movies in the "Science Fiction" genre. Name the column 'average_length'.
-- (1 row, expected result around 110-120)
SELECT AVG(m.length_minutes) AS average_length
FROM movie AS m
	JOIN movie_genre AS mg USING (movie_id)
	JOIN genre AS g  USING (genre_id)
WHERE g.genre_name = 'Science Fiction';
