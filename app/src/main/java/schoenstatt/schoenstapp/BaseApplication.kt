package schoenstatt.schoenstapp

import android.app.Application
import com.facebook.stetho.Stetho
import dev.blacktobacco.com.data.module.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import schoenstatt.schoenstapp.module.appModule

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(appModule, dataModule))
        }
    }


}