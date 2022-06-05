# Movie Rating app

A rest-api back end to simulate a movie rating and viewing Application.
## Tools Used
- JAVA 8
- Spring Boot
- H2 In-memory Database

## Basic Features

- API to submit ratings (between 1-10) by user for a selected movie.
- API to get the rating (between 1-10) of a selected movie.
- API to search for the list of in-theatre movies for a given pincode.


## Database Schemas

The Following tables are created on the application startup in h2-database
### CINEMA
| `id int PRIMARY KEY` | `address varchar(255) ` | `name varchar(255)` | `post_office varchar(255)` | `total_halls int`|
|:-------------------:|-----------------|--------------|---------------------|-----------------|
### CINEMA_HALL
| `id int PRIMARY KEY` | `cinema_id varchar(255) ` | `hall_serial_number int` | `total_seats int`| 
|:-------------------:|-----------------|--------------|---------------------|
### MOVIE
| `id int PRIMARY KEY` | `description varchar(255) ` | `name varchar(255)` | `overall_rating double` | `release_year int`|
|:-------------------:|-----------------|--------------|---------------------|-----------------|
### RATINGS
| `id int PRIMARY KEY` | `movie_id varchar(255) ` | `review varchar(255)` | `score int` | `user_id varchar(255)`|
|:-------------------:|-----------------|--------------|---------------------|-----------------|
### SCREENING
| `id int PRIMARY KEY` | `cinema_id varchar(255) ` | `date timestamp` | `movie_id varchar(255)`| 
|:-------------------:|-----------------|--------------|---------------------|
### USER
| `id int PRIMARY KEY` | `name varchar(255) ` | `email varchar(255)` |
|:-------------------:|-----------------|--------------|

## Populating the database and dataset

SQL Scripts to create a dataset can be found in the following file:
```
src\main\resources\import.sql
```
It contains intial scripts to populate the following tables:
- Movie,
- Cinema
- Cinema_Hall
- Screening
- User

> Note: For `Movie, User` tables data can be added through API calls as declared in below section or by adding scripts in the import.sql file. 
`Cinema,Cinema_Hall,Screening` tables cane be populated by adding scripts in the `import.sql` file
## Api Endpoints

- ***POST /rating***

End-point to submit a rating for a selected movie by the user

`$ curl localhost:8080/rating`
```javascript
     {
        "movieId": "1",
        "review": "Great Movie! Loved it",
        "score": 8,
        "userId":"1"
     }
```     
    

 Success Response:
  * **Code:** 200
   * **Content:** `Rating Saved successfully`
 
  Error Response:
  * **Code:** 400 BAD REQUEST
  *  **Content:** `{
    "code": "NOT_FOUND",
    "message": "No record found with this user-id"}`

    OR
    
  * **Code:** 400 BAD REQUEST
  *  **Content:** `{
    "code": VALIDATION_FAILED", "message": "Max value allowed for rating is 10"}` 

<br />

- ***GET /ratings/user/:id***

End-point to get all ratings for a particular user

`$ curl localhost:8080/ratings/user/1`


 Success Response:
  * **Code:** 200
  * **Content:** `[
    {
        "id": 1,
        "movieId": "1",
        "review": "Can be better",
        "userId": "1",
        "score": 8
    }
]`

<br />

- ***GET /ratings/movie/:id***

End-point to get all ratings for a particular movie

`$ curl localhost:8080/ratings/movie/1`


 Success Response:
  * **Code:** 200
  *  **Content:** `
   {
    "id": 1,
    "name": "MISSION_IMPOSSIBLE : GHOST RECKON",
    "overallRating": 8.0,
    "ratings": [
        {
            "id": 1,
            "movieId": "1",
            "review": "Can be better",
            "userId": "1",
            "score": 8
        }
    ]
}`

  Error Response:
  * **Code:** 400 BAD REQUEST
  * **Content:** `{
    "code": "NOT_FOUND",
    "message": "No record found with this movie-id"}`

<br />

- ***GET /movies/pin/:pin***

End-point to get all in-theatre movies for a particular pin

- `$ curl localhost:8080/movies/pin/700047`

    Success Response:
  * **Code:** 200
  * **Content:** `
   [
    {
        "id": 2,
        "name": "INTERSTELLAR",
        "description": "New age science fiction",
        "releaseYear": 2011,
        "overallRating": 0.0
    },
    {
        "id": 1,
        "name": "MISSION_IMPOSSIBLE : GHOST RECKON",
        "description": "The latest installment in mission impossible franchise",
        "releaseYear": 2012,
        "overallRating": 0.0
    }
]`

  Error Response:
  * **Code:** 400 BAD REQUEST
  * **Content:** `{
    "code": "NOT_FOUND",
    "message": No address details found for this pincode"}`

<br />

- ***POST /movie***

End-point to add a new movie to the database

`$ curl localhost:8080/movie`
```javascript
      {
        "name": "JAMES AND JILL",
        "description": "Its a drama-comedy series",
        "releaseYear": 2022,
        "overallRating": 0.0
      }
```

   
 Success Response:
  * **Code:** 200
  *  **Content:** `Movie added with id: 2`
 
  Error Response:
  * **Code:** 400 BAD REQUEST
  *  **Content:** `{
    "code": "VALIDATION_FAILED",
    "message": "Name cannot be null"
}`    

<br />

- ***GET /movies***

End-point to get all movies in the system

`$ curl localhost:8080/movies`

   
Success Response:
  * **Code:** 200
  * **Content:** `
   [
    {
        "id": 2,
        "name": "INTERSTELLAR",
        "description": "New age science fiction",
        "releaseYear": 2011,
        "overallRating": 0.0
    },
    {
        "id": 1,
        "name": "MISSION_IMPOSSIBLE : GHOST RECKON",
        "description": "The latest installment in mission impossible franchise",
        "releaseYear": 2012,
        "overallRating": 0.0
    }
]`
<br />


- ***POST /user***

End-point to add a new user to the database

`$ curl localhost:8080/user`

```javascript
      {
       "name": "James Johnson",
       "email": "roychoudhurysrij@gmail.com",
      }
```

Success Response:
  * **Code:** 200
  * **Content:** `User added with id: 2`
 
  Error Response:
  * **Code:** 400 BAD REQUEST
  * **Content:** `{
    "code": "VALIDATION_FAILED",
    "message": "Email is invalid"
}`    

<br />

- ***GET /users***

End-point to get all users in the system

`$ curl localhost:8080/users`


Success Response:
  * **Code:** 200
  * **Content:** `
[
      {
          "id": 1,
          "name": "roychoudhurysrijan442@gmail.com",
          "email": "Srijan"
      },
      {
          "id": 2,
          "name": "abc@gmail.com",
          "email": "David"
      },
      {
          "id": 3,
          "name": "User 3",
          "email": "abcd@gmail.com"
      }
]`


## Testing procedure
- Download the source code for the project.
- Make sure to have **maven** and **JAVA 8** installed.
- Run the `SpringBootH2DatabaseExampleApplication.java` class. It should start the spring application on port `8080`.
- Use the command and path declared in  `Api Endpoints` section to hit the respective APIs.

