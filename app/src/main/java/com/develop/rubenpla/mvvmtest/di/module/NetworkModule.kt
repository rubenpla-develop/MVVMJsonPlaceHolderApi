package com.develop.rubenpla.mvvmtest.di.module

import com.develop.rubenpla.mvvmtest.network.PostApi
import com.develop.rubenpla.mvvmtest.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */

@Module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */

    @Provides
    @Reusable
    @JvmStatic
    internal fun providesPostApi(retrofit : Retrofit) : PostApi {

        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface() : Retrofit {

        /**
         * ERROR : SSl Exception, probably localized issue on Android Api 19
         *
         * 'https://www.google.es/search?q=HTTP+FAILED%3A+javax.net.ssl.SSLHandshakeException%3A+
         * avax.net.ssl.SSLProtocolException+jsonplaceholder&rlz=1C1PRFI_esES802ES802&oq=HTTP+
         * FAILED%3A+javax.net.ssl.SSLHandshakeException%3A+javax.net.ssl.SSLProtocolException
         *
         * useful resource ? : http://hostciti.net/faq/java/isklyuchenie-javax-net-ssl-
         * sslhandshakeexception-handshake-failed-pri-zaprose-na-server.html
         */

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .client(createOkHttpClient())
                .build()
    }

    private fun createOkHttpClient() : OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(logging)

        return okHttpClient.build()
    }
}