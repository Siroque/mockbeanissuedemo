package mockbeanissuedemo

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Controller("/api/test")
class TestController @Inject constructor(private val service: TestService) {

    @Get
    fun getString(): Single<MutableHttpResponse<String>> {
        return service.getString()
                .subscribeOn(Schedulers.io())
                .map { HttpResponse.ok(it) }
                .onErrorReturn { t: Throwable -> handleError(t) }
    }

    fun handleError(throwable: Throwable): MutableHttpResponse<String> {
        return when (throwable) {
            is CompositeException -> handleCompositeException(throwable)
            else -> HttpResponse.serverError()
        }
    }

    private fun handleCompositeException(throwable: CompositeException): MutableHttpResponse<String> {
        return HttpResponse.serverError()
    }
}
