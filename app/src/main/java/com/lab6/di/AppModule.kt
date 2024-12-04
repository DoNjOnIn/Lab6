import android.app.Application
import androidx.room.Room
import com.lab6.data.ServerApi
import com.lab6.ui.screens.current.BaseScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.jsonkeeper.com/"

val appModule = module {

    single<ServerApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
            .create(ServerApi::class.java)
    }

    viewModel { BaseScreenViewModel(get()) }
}



