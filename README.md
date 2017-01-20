#Build
`docker build -t felttrip/lb-backend .`

#Start MySql
`docker run -p 3306:3306 --name felttrip_mysql -v //c/Users/SpinDEV/Desktop/lb-backend/sql:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=lb_backend -d mysql:5.7`


#Run
`docker run -p 80:4567 --name felttrip_lb_backend --link felttrip_mysql:my_mysql -d felttrip/lb-backend`
