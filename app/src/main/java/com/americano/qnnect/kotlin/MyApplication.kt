package what.the.mvvm

import android.app.Application
import com.americano.qnnect.kotlin.di.myDiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}