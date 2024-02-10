package mfstech.apps.powah

import android.app.Application
import kotlinx.coroutines.Dispatchers
import mfstech.apps.powah.common.database.DeviceDao
import mfstech.apps.powah.common.database.PowahDatabase
import mfstech.apps.powah.common.database.getPowahDatabase
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.details.business.DetailsRepository
import mfstech.apps.powah.details.business.DetailsRepositoryImpl
import mfstech.apps.powah.details.presenter.DetailsContract
import mfstech.apps.powah.details.presenter.DetailsViewModel
import mfstech.apps.powah.home.business.HomeRepository
import mfstech.apps.powah.home.business.HomeRepositoryImpl
import mfstech.apps.powah.home.presenter.HomeContract
import mfstech.apps.powah.home.presenter.HomeViewModel
import mfstech.apps.powah.input.business.InputRepository
import mfstech.apps.powah.input.business.InputRepositoryImpl
import mfstech.apps.powah.input.presenter.InputContract
import mfstech.apps.powah.input.presenter.InputViewModel
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