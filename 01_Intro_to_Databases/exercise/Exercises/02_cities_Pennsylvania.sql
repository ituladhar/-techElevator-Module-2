-- 2. The name and area of all cities in Pennsylvania (PA) (4 rows)
SELECT city_name, area
FROM city
where state_abbreviation = 'PA';
