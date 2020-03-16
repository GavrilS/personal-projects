# RidePal-Playlist Generator

Telerik Final Project

Link Trello - https://trello.com/b/DToa0rX6/ridepal-playlist-generator

Project description:

RidePal Playlist Generator is a web application, that enables users traveling
from point A to point B to generate a playlist of songs for the duration of the
trip. They choose the genres of the songs they want to listen to from a list of
predefined genres, and specify what portion of the duration of the trip each
genre should take. They can choose if they want to have more than one song from
a given artist in their playlist or not, and also they can choose to have only
top ranked songs in their playlist. Both guests and users can view available
playlists. Users can edit and delete playlists they have created. Admins can do
the same and they can also view a list of users and update/delete different
users. They can also synchronize different genres, enabling users to use them
when creating new playlists or disable already active genres, so that users can
no longer use them.

Technologies used in the project:

Backend: Java 8 – Spring Boot – Hibernate – Spring Data JPA – MariaDB

Frontend: Spring MVC (Thymeleaf) – Spring Security – HTML 5 – CSS 3 –
Swagger 2 (UI) – Bootstrap

Documentation:
Swagger-UI - http://localhost:8080/swagger-ui.html

Steps to build and run the project:

Prerequisites: In order to build and run the project you must first install all
the related IDE, Frameworks and tools described above in technologies used
section.

1.	Clone or download the repository.
2.	Open the project in your IDE.
3.	Run provided SQL scripts in db-scripts folder of the project.
4.	Build the project and run it.
5.	To load genres and tracks form Deezer (the source of our tracks) you have
two options:
-Use postman and run localhost:8080/api/initializer endpoint to get all deezer
genres and load tracks from three predefined genres (rap, rock and jazz).
-Instead of going through postman, log in with an admin account through the UI
and select the button Genres from the navigation bar at the top. This will load
all genres from Deezer and will show you a page with every genre. After that you
can synchronize only the genres which you want. The synchronization button will
automatically activate the specified genre and will download tracks for it from
Deezer.
6. You can now use the application freely. 
