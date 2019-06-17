package mockbeanissuedemo

import io.micronaut.http.HttpResponse
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.reactivex.functions.Predicate
import io.reactivex.subscribers.TestSubscriber
import mockbeanissuedemo.traits.WithApiActor
import spock.lang.Specification

import static io.micronaut.http.HttpStatus.OK

@MicronautTest
class TestServiceSpec extends Specification implements WithApiActor {

    TestSubscriber<HttpResponse<List<String>>> testSubscriber

    @MockBean(TestServiceImpl)
    TestService mockTestService() {
        Mock(TestService) {
            getString() >> { throw new RuntimeException("db error") }
        }
    }

    def setup() {
        testSubscriber = new TestSubscriber<>()
    }

    def "test some stuff"() {
        when:
        sleep(3000)
        apiActor.requestEntities().subscribe(testSubscriber)

        then:
        testSubscriber.await().assertError(RuntimeException.class)
    }
}
