-- 3. For all actors with the last name of "Jones", display the actor's name and movie titles they appeared in. Order the results by the actor names (A-Z). (48 rows)
SELECT p.person_name, m.title 
FROM person AS p
	JOIN movie_actor AS m_act ON p.person_id = m_act.actor_id
 	JOIN movie AS m ON m.movie_id = m_act.movie_id
WHERE person_name LIKE '% Jones'
ORDER BY person_name ASC;

