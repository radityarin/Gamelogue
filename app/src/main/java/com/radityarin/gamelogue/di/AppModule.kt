package com.radityarin.gamelogue.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.GsonBuilder
import com.radityarin.gamelogue.data.source.AppRepositoryImpl
import com.radityarin.gamelogue.data.source.local.room.AppDatabase
import com.radityarin.gamelogue.data.source.remote.LocalDataSource
import com.radityarin.gamelogue.data.source.remote.RemoteDataSource
import com.radityarin.gamelogue.data.source.remote.network.ApiServiceApp
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants
import com.radityarin.gamelogue.domain.repositories.AppRepository
import com.radityarin.gamelogue.domain.usecases.games.GamesInteractor
import com.radityarin.gamelogue.domain.usecases.games.GamesUseCase
import com.radityarin.gamelogue.presentation.menu.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.sqlcipher.database.SQLiteDatabase.getBytes
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { get<AppDatabase>().appDao() }
    single {
        val passphrase: ByteArray = getBytes("gamelogue".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "gamelogue.db"
        ).setQueryCallback(object : RoomDatabase.QueryCallback {
            override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                println("SQL Query: $sqlQuery SQL Args: $bindArgs")
            }
        }, Executors.newSingleThreadExecutor())
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .build()
    }
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(EndpointConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
        retrofit.create(ApiServiceApp::class.java)
    }
}

val repositoryModule = module {
    single { CoroutineScope(Dispatchers.IO) }
    single {
        RemoteDataSource(
            get()
        )
    }
    single {
        LocalDataSource(
            get()
        )
    }
    single<AppRepository> {
        AppRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { GameViewModel(get()) }
}

val useCaseModule = module {
    single<GamesUseCase> { GamesInteractor(get()) }
}