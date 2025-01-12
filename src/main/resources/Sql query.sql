-- Query to get the list of users who took the training lesson more than once at the same day,
-- grouped by user and training lesson, each ordered from the most recent lesson date to oldest
-- date
SELECT
    td.user_id,
    u.username,
    td.training_id,
    td.training_date,
    COUNT(*) AS count
FROM
    Training_details td
JOIN
    User u ON td.user_id = u.user_id
GROUP BY
    td.user_id, td.training_id, td.training_date
HAVING
    COUNT(*) > 1
ORDER BY
    td.user_id ASC,
    td.training_id ASC,
    td.training_date DESC;
