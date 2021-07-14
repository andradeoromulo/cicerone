-- All enabled categories by order
SELECT *
    FROM Category
    WHERE disabled = FALSE
    ORDER BY order_position;

-- All enabled subcategories by order
SELECT *
    FROM Subcategory
    WHERE disabled = FALSE
    ORDER BY order_position;

-- All enabled courses
SELECT *
    FROM Course
    WHERE disabled = FALSE ;

-- Names of all subcategories without a description
SELECT title
    FROM Subcategory
    WHERE description = '' OR description IS null;

-- Names of all enabled subcategories with at least 1 course
SELECT DISTINCT s.title, s.order_position
    FROM Subcategory s
         JOIN Course c OR s.id = c.subcategory_id
    WHERE s.disabled = false
    ORDER BY s.order_position;

-- Name and amount of courses of the instructor who has the biggest amount of courses
SELECT c.instructor, COUNT(*) AS coursesAmount
    FROM Course c
    GROUP BY c.instructor
    ORDER BY coursesAmount DESC
    LIMIT 1;

-- Names of all enabled categories with its amount of enabled courses and total hours to finish
SELECT cat.title, COUNT(crs.id) AS courseAamount, COALESCE(SUM(crs.time_to_finish_in_hours), 0) AS totalHoursToFinish
    FROM Category cat
         LEFT JOIN Subcategory sub on cat.id = sub.category_id
         LEFT JOIN Course crs on sub.id = crs.subcategory_id
    WHERE cat.disabled = FALSE
    GROUP BY cat.title;
