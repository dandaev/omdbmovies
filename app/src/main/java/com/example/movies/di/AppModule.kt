package com.example.movies.di

import com.example.movies.common.Constants
import com.example.movies.data.remote.OmdbMovieApi
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .addHeader("Authorization", Constants.API_KEY)
            .build()
    )

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("apikey", Constants.API_KEY).build())
            .build()
    )

    private val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideOmdbApi(): OmdbMovieApi{

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build().create(OmdbMovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: OmdbMovieApi): MovieRepository{
        return MovieRepositoryImpl(api)
    }
}