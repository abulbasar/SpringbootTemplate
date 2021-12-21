# Create docker image for postgresql
```
docker run -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```
docker inspect <container Id>
nc -vz localhost 5432


curl -v "http://localhost:8080/api/customer"

curl -v "http://localhost:8080/api/customer/1"

Insert request
curl -i --header "Content-type: application/json" --request POST --data "{\"first_name\": \"TestF\", \"last_name\": \"TestL\"}" http://localhost:8080/api/customer 

curl -i --header "Content-type: application/json" --request POST --data '{"first_name": "TestF", "last_name": "TestL"}' http://localhost:8080/api/customer

Update request
curl -i --header "Content-type: application/json" --request POST --data '{"id": 1, first_name": "TestF", "last_name": "TestL"}' http://localhost:8080/api/customer

Crawl
Actual rss endpoint https://economictimes.indiatimes.com/rssfeedsdefault.cms
Url encoded using https://meyerweb.com/eric/tools/dencoder/
curl http://localhost:8080/news/crawl?url=https%3A%2F%2Feconomictimes.indiatimes.com%2Frssfeedsdefault.cms | jq

curl http://localhost:8080/news | jq
curl http://localhost:8080/news/search?q=Cipla | jq