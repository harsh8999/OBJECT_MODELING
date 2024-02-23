**Juke Box**

Welcome to the Juke Box application! With Juke Box, users can easily create playlists from a pool of available songs and customize them by adding or deleting songs as needed. Once a playlist is created, users can start playing songs by selecting the playlist, and the first song in the list will begin playing.

Juke Box also offers users the ability to switch between songs by using Next and Back commands or by selecting another song from the playlist. When reaching the end of a playlist, Next will switch to the first song in the list, and when reaching the start, Back will switch to the last song. Users can choose to play a song from another playlist if that playlist is currently selected. To play a
song from another playlist, users must first select the desired playlist and
then choose the song they wish to play.

In addition,
Juke Box supports album functionality, allowing users to browse and play songs from albums owned by an artist or artist group. Each song can feature multiple artists, but it will be owned by the artist or group whose album it belongs to. With Juke Box, users can enjoy a personalized music experience and easily manage their playlists and favorite songs.

**Functional Requirements**

* A user can create a playlist from a pool of available songs.
* A user can delete a playlist.

* A user can add / delete songs from the playlist.
* A user can start playing songs by choosing a playlist. On choosing a playlist, the first song in the playlist will start playing.

* A user can switch songs by using Next, Back command or by choosing another song from the playlist that has been chosen.

* On reaching the end, Next will switch to the first song in the current playlist.
* On reaching the start, Back will switch to the last song in the playlist.
* Only one song can be played at a time.
* A user can choose to play the song from another playlist if and only if that playlist is selected. Basically two operations have to be done to successfully play the song of their choice.
* Select the playlist (which will play the first song when selected).

* Choose the song of your choice.
* An album is a collection of songs owned by the original artist / artist group. Artist Group Example:- One Direction. One Direction Group is the album owner and is considered as an artist.

* Each song can feature multiple artists but it will be owned by the artist whose album this song belongs to.
* Each song can only be a part of one album.

**Entity Relationship Diagram**


**Design Patterns and Principles**

* Command Design Pattern to implement the commands
* Abstract Factory pattern to load data from the file
* SOLID principle

**Entities**

* Player
* Song
* User
* Playlist
* Album
* Artist
* Artist Group

**Repositories**

* CRUD Repository
* Album Repository
* Artist Repository
* Song Repository
* Playlist Repository
* User Repository

**Services**

* Album Service
* Artist Service
* Song Service
* Playlist Service
* User Service
* Player Service

**Language Used**:  

* Java
* junit for testing

Juke Box is a fun and interactive way to listen to your favorite music and create your own personalized playlists. Thank you for choosing Juke Box!
