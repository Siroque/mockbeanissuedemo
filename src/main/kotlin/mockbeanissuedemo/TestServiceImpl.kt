package mockbeanissuedemo

import io.reactivex.Single
import javax.inject.Singleton

@Singleton
open class TestServiceImpl: TestService {
    override fun getString(): Single<String> {
        println("Original bean used")
        return Single.just("myString")
    }
}