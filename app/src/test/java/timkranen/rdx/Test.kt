package timkranen.rdx

import org.junit.Test

import org.junit.Assert.*
import timkranen.rdx.test.MockApp

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Test {

    private val mockApp = MockApp()

    @Test
    fun test() {
        mockApp.startLoad()

        mockApp.getStateLog().forEach {
            System.out.println(it)
        }
    }
}
