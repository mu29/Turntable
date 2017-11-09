package net.yeoubi.turntable.common.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type


class RxThreadCallAdapterFactory private constructor() : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        return RxCallAdapterWrapper(original.get(returnType, annotations, retrofit))
    }

    private class RxCallAdapterWrapper<R> internal constructor(private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Observable<*>> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Observable<*> {
            val observable = wrapped.adapt(call) as Observable<*>
            return observable.observeOn(AndroidSchedulers.mainThread())
        }
    }

    companion object {

        fun create(): CallAdapter.Factory {
            return RxThreadCallAdapterFactory()
        }
    }
}
