package mockbeanissuedemo

import io.reactivex.Single

interface TestService {
    fun getString(): Single<String>
}