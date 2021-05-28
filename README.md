# Sountry

### Backend
https://github.com/dikamjit-borah/Sountry-Backend

## Brief
A one-in-all integrated social media platform for musicians to connect with one another. Musicians can swipe through their feed to discover users who have uploaded their music covers in similar genres. Upon connecting they can chat with one another. Users can upload requirements and queries related to music through a common forum page.

## Technical Specifications
* Android
  * XML
  * Kotlin
  * Java
  * Retrofit

* Backend
  * Node.js
  * Express.js
  * JWT
  * Firebase  
  * PostGreSQL

* Cloud
  * Heroku

## System Design

![db_design](https://github.com/hunkyxstudman/Musico/blob/main/db_design.png)
<br>
Our database will consist of the following tables
* USERS
  * This is the table that will contain the personal details and preferences of our users
* VIDEOS
  * This table will contain details of the videos that are uploaded by the users. The actual video will be stored in another cloud service like S3 or Firebase and the link will be present in the vid_link of this table
* CONNECTIONS
  * This is the most interesting part of our database. Our application has the feature to notify users when another user will send a connection request to them. Upon receiving said request the user has the option to either accept or reject the request. 
  * To implement this, we will have a initial empty CONNECTIONS table.
  *  Whenever a user(user_id_2) sends a request to another user(user_id_1) a row will be created in the CONNECTIONS table with the default value of is_connected as False.
  *   So we can retreive all entries with is_connected as False on the notifications tab. 
  *   Upon accepting, the is_connected will be changed to True, and all entries with is_connected as True will be fetched in the Connections tab of the application. 
  *   If request is rejected, we will simply remove that entire entry from the CONNECTIONS table.
 
