package mockbeanissuedemo

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("mockbeanissuedemo")
                .mainClass(Application.javaClass)
                .start()
    }
}