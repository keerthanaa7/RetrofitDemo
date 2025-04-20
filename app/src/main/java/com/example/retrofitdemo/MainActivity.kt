package com.example.retrofitdemo

import Album
import AlbumItem
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitdemo.ui.theme.RetrofitDemoTheme
import retrofit2.Response

class MainActivity : ComponentActivity() {
    companion object{
        private val TAG = "MainActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val retrofit = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        val albumitem = AlbumItem(1, "keer Album", 101)
        val postResponse:LiveData<Response<AlbumItem>> = liveData {
            val response:Response<AlbumItem> = retrofit.uploadAlbumItem(albumitem)
            emit(response)
        }

        postResponse.observe(this, Observer{
            val albumitem = it.body()
            if (albumitem != null) {
                Log.d(TAG, "album id : " + albumitem.id + "album title : " + albumitem.title
                        + "album user id : " + albumitem.userId)
            }
        })

    /*    val responseLiveData:LiveData<Response<Album>> = liveData {
            val response:Response<Album> = retrofit.getAlbums()
            emit(response)
        }*/

        /*val albumitemResponse:LiveData<Response<AlbumItem>> = liveData {
            val response:Response<AlbumItem> = retrofit.getAlbum(1)
            emit(response)
        }

        albumitemResponse.observe(this, Observer{
            val albumitem = it.body()
            if (albumitem != null) {
                Log.d(TAG, "album id : " + albumitem.id + "album title : " + albumitem.title
                + "album user id : " + albumitem.userId)
            }
        })*/

       /* val responseSortedLiveData:LiveData<Response<Album>> = liveData {
            val response:Response<Album> = retrofit.getAlbumsSorted(3)
            emit(response)
        }

        responseSortedLiveData.observe(this, Observer{
            val albumssorted = it.body()?.listIterator()
            if(albumssorted != null){
                while(albumssorted.hasNext()){
                    val albumSorted = albumssorted.next()
                    Log.d(TAG, "ALBUM NAME sorted" + albumSorted.title)
                }

            }
        })*/

    /*    responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if(albumList != null){
                while(albumList.hasNext()){
                    val album = albumList.next()
                    Log.d(TAG, "album name " + album.title)

                }
            }
        })*/

        /*enableEdgeToEdge()
        setContent {
            RetrofitDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitDemoTheme {
        Greeting("Android")
    }
}