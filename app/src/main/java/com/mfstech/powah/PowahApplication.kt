package com.mfstech.powah

import android.app.Application
import com.mfstech.powah.common.database.DeviceDao
import com.mfstech.powah.common.database.PowahDatabase
import com.mfstech.powah.common.database.getPowahDatabase
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.details.business.DetailsRepository
import com.mfstech.powah.details.business.DetailsRepositoryImpl
import com.mfstech.powah.details.presenter.DetailsContract
import com.mfstech.powah.details.presenter.DetailsViewModel
import com.mfstech.powah.home.business.HomeRepository
import com.mfstech.powah.home.business.HomeRepositoryImpl
import com.mfstech.powah.home.presenter.HomeContract
import com.mfstech.powah.home.presenter.HomeViewModel
import com.mfstech.powah.input.business.InputRepository
import com.mfstech.powah.input.business.InputRepositoryImpl
import com.mfstech.powah.input.presenter.InputContract
import com.mfstech.powah.input.presenter.InputViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import java.time.Duration
import kotlin.coroutines.CoroutineContext

class PowahApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PowahApplication)
            modules(module {
                single<PowahDatabase> { getPowahDatabase(get()) }
                single<CoroutineContext> { Dispatchers.IO }
                factory<DeviceDao> { get<PowahDatabase>().getDeviceDao() }

                factory<HomeRepository> { HomeRepositoryImpl(get()) }
                viewModel { (view: HomeContract.View) -> HomeViewModel(view, get(), get()) }

                factory<InputRepository> { InputRepositoryImpl(get()) }
                viewModel { (view: InputContract.View, device: Device?) ->
                    InputViewModel(view, device, get(), get())
                }

                factory<OkHttpClient> {
                    OkHttpClient.Builder()
                        .pingInterval(Duration.ofSeconds(30))
                        .build()
                }

                factory<Request> { (device: Device) ->
                    Request.Builder()
                        .url("http://${device.route}/events")
                        .header("Accept", "application/json; q=0.5")
                        .addHeader("Accept", "text/event-stream")
                        .build()
                }

                factory<DetailsRepository> { (device: Device) ->
                    DetailsRepositoryImpl(get { parametersOf(device) }, get(), get())
                }
                viewModel { (view: DetailsContract.View, device: Device) ->
                    DetailsViewModel(
                        view,
                        device,
                        get<DetailsRepository> { parametersOf(device) },
                        get()
                    )
                }
            })
        }
    }
}