# Create docker image for postgresql
```
docker run -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```
docker inspect <container Id>
nc -vz localhost 5432


Check table locks
SELECT
activity.pid,
activity.usename,
activity.query,
blocking.pid AS blocking_id,
blocking.query AS blocking_query
FROM pg_stat_activity AS activity
JOIN pg_stat_activity AS blocking ON blocking.pid = ANY(pg_blocking_pids(activity.pid));


curl -v "http://localhost:8080/api/customer"

curl -v "http://localhost:8080/api/customer/1"

Insert request
curl -i --header "Content-type: application/json" --request POST --data "{\"first_name\": \"TestF\", \"last_name\": \"TestL\"}" http://localhost:8080/api/customer 

curl -i --header "Content-type: application/json" --request POST --data '{"first_name": "TestF", "last_name": "TestL"}' http://localhost:8080/api/customer

Update request
curl -i --header "Content-type: application/json" --request POST --data '{"id": 1, first_name": "TestF", "last_name": "TestL"}' http://localhost:8080/api/customer

