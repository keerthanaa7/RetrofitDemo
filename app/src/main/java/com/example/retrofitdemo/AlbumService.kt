package com.example.retrofitdemo

import Album
import AlbumItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    // get the album array mapped to the actual json data
    @GET("/albums")
    suspend fun getAlbums() :Response<Album>

    // get albums array based on user id
    @GET("/albums")
    suspend fun getAlbumsSorted(@Query("userId") userId:Int) : Response<Album>

    // get a single album item based on id
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id")albumid:Int):Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbumItem(@Body  albumItem: AlbumItem):Response<AlbumItem>
}
