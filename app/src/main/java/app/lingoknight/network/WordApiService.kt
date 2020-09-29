package app.lingoknight.network

import app.lingoknight.data.Word
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://users.metropolia.fi/~michaejc/LingoKnight/vocabulary/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WordApiService {

    @GET("word.json")
    suspend fun getProperties(): List<Word>
}

object WordApi {
    val retrofitService: WordApiService by lazy { retrofit.create(WordApiService::class.java) }
}