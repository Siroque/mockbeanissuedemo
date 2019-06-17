package mockbeanissuedemo.traits

import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import javax.inject.Inject

trait WithHttpClient {

    @Inject
    private EmbeddedServer embeddedServer

    private RxHttpClient httpClient

    RxHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = RxHttpClient.create(URI.create("http://localhost:${embeddedServer.port}").toURL())
        }
        return httpClient
    }
}