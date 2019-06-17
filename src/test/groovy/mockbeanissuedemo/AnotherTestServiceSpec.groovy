package mockbeanissuedemo

import io.micronaut.http.HttpResponse
import io.micronaut.test.annotation.MicronautTest
import io.reactivex.functions.Predicate
import io.reactivex.subscribers.TestSubscriber
import mockbeanissuedemo.traits.WithApiActor
import spock.lang.Specification

import static io.micronaut.http.HttpStatus.OK

@MicronautTest
class AnotherTestServiceSpec extends Specification  implements WithApiActor {

    TestSubscriber<HttpResponse<List<String>>> testSubscriber

    def setup() {
        testSubscriber = new TestSubscriber<>()
    }

    def "test some stuff"() {
        when:
        sleep(3000)
        apiActor.requestEntities().subscribe(testSubscriber)

        then:
        testSubscriber.await()
                .assertNoErrors()
                .assertComplete()
                .assertValue({
                    it.status == OK
                    String body = it.body()
                    println(it.body())
                    body == "myString"
                } as Predicate)
    }
}
