package mockbeanissuedemo.actors

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable
import mockbeanissuedemo.HttpRequestBuilder

import static io.micronaut.http.HttpMethod.GET

class ApiActor {

    private final RxHttpClient apiClient

    ApiActor(RxHttpClient apiClient) {
        this.apiClient = apiClient
    }

    Flowable<HttpResponse<List<String>>> requestEntities() {
        HttpRequestBuilder requestBuilder = HttpRequestBuilder.create("/api/test", GET)
        return apiClient.exchange(requestBuilder.build(), Argument.of(String)) as Flowable<HttpResponse<String>>
    }
}
