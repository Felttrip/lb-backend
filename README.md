#Build
`docker build -t felttrip/lb-backend .`

#Start MySql
`docker run --name felttrip_mysql -e MYSQL_ROOT_PASSWORD=pass -d mysql:5.7`

#Run
`docker run --name felttrip_lb_backend --link felttrip_mysql:mysql -d -p 80:4567 felttrip/lb-backend`
