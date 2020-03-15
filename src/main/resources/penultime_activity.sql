-- https://leetcode.com/problems/get-the-second-most-recent-activity/submissions/
-- can be simplified with "with"
select  username, activity, TO_CHAR(startdate, 'YYYY-MM-DD' ) as startdate, TO_CHAR(enddate, 'YYYY-MM-DD' ) as enddate from (
select username, activity, startdate, enddate from
    (select username, activity, startdate, enddate, RANK() OVER (PARTITION BY username order by enddate DESC) as arank from UserActivity)
where arank = 2
union all
select username, activity, startdate, enddate from
    (select username, activity, startdate, enddate, RANK() OVER (PARTITION BY username order by enddate DESC) as arank from UserActivity)
where arank = 1 and username not in (select username from
    (select username, RANK() OVER (PARTITION BY username order by enddate DESC) as arank from  UserActivity) where arank = 2)
    ) order by username desc