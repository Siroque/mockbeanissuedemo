package mockbeanissuedemo

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpRequest

class HttpRequestBuilder {
    private final MutableHttpRequest request

    static HttpRequestBuilder create(String uri, HttpMethod method = HttpMethod.GET) {
        return new HttpRequestBuilder(method, uri)
    }

    private HttpRequestBuilder(HttpMethod method, String uri) {
        request = HttpRequest.create(method, uri)
    }

    HttpRequestBuilder body(Object body) {
        request.body(body)
        return this
    }

    HttpRequestBuilder authorized() {
        request.bearerAuth(OAuthMock.VALID_TOKEN)
        return this
    }

    HttpRequestBuilder headers(Map<String, String> headers) {
        request.headers(headers)
        return this
    }

    HttpRequestBuilder header(String name, String value) {
        request.header(name, value)
        return this
    }

    MutableHttpRequest build() {
        return request
    }
}
