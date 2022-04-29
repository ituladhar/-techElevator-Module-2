-- 13. The directors of the movies in the "Harry Potter Collection" (4 rows)
SELECT DISTINCT p.person_name
FROM collection AS c
	JOIN movie AS m USING (collection_id)
	JOIN person AS p ON p.person_id = m.director_id
WHERE c.collection_name = 'Harry Potter Collection';
