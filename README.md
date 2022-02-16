<p align="center">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/> * * *  <img src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>  * * *  <img src="https://img.shields.io/badge/docker%20-%230db7ed.svg?&style=for-the-badge&logo=docker&logoColor=white"/>
</p>

# OpenClassrooms Application Developer Java Project 8

![Screenshot](logo.jpeg)

## TourGuide App

TourGuide is a Spring Boot application that has been a centerpiece in the TripMasters app portfolio. The application will be targeting people looking for package deals on hotel stays and admissions to various attractions.

It now supports the tracking of 100.000 users in a bit less than 3 minutes and the calculation of reward points for 100.000 users in +-3 minutes.

## Project Architecture
![Screenshot](architecture.png)


## Run the app
First, build all jar, in each microservice run :
```
$./gradlew bootJar
```
Then run docker :
```
$ docker-compose up
```
## Testing
To generate jacoco reports and tests, in each microservice run (all microservices must be started) :
```
$ ./gradlew jacocoTestReport
```

## Swagger documentation :
<ul>
<li><b>TourGuideMain : </b>http://localhost:8080/swagger-ui/index.html</li>
<li><b>Gps : </b>http://localhost:8081/swagger-ui/index.html</li>
<li><b>User : </b>http://localhost:8082/swagger-ui/index.html</li>
<li><b>Reward : </b>http://localhost:8083/swagger-ui/index.html</li>
<li><b>Pricer : </b>http://localhost:8084/swagger-ui/index.html</li>
</ul>

## Performances : 
![Screenshot](performances.png)

## Test reports :

### TourGuideMain : 
![Screenshot](jacoco_reports/Tourguidemain_report.png)

### Gps :
![Screenshot](jacoco_reports/gps_report.png)

### User :
![Screenshot](jacoco_reports/User_report.png)

### Rewards :
![Screenshot](jacoco_reports/Rewards_report.png)

### Pricer :
![Screenshot](jacoco_reports/Pricer_report.png)

### Disclaimer
<p>This is a school project, I made a mistake configuring my .git & .gitignore file which made it impossible to push to github.</p>
<p>A new .git file has been initialized with the right gitignore in order to be able to push on github.</p>

<p>Screenshots of the history of my project can be seen in</p>

```
/GitHistory_Screenshots
```