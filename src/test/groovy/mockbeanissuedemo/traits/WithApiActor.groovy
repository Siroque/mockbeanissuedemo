package mockbeanissuedemo.traits

import mockbeanissuedemo.actors.ApiActor
import org.junit.Before

trait WithApiActor extends WithHttpClient {

    ApiActor apiActor

    @Before
    def setupActor() {
        apiActor = new ApiActor(httpClient)
    }
}