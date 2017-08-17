package org.spekframework.jvm

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.junit.jupiter.api.Test
import org.spekframework.jvm.support.AbstractSpekJvmRuntimeTest

/**
 * @author Ranie Jade Ramiso
 */
class AssertionTest: AbstractSpekJvmRuntimeTest() {
    @Test
    fun testPassingSpek() {
        class TestSpek: Spek({
            group("group") {
                test("test") {}
                test("another test") {}
            }
        })

        val recorder = executeTestsForClass(TestSpek::class)

        assertThat(recorder.testSuccessfulCount, equalTo(2))
    }

    @Test
    fun testFailingSpek() {
        class TestSpek: Spek({
            group("group") {
                test("test") { assertThat(true, equalTo(false)) }
                test("another test") {}
            }
        })

        val recorder = executeTestsForClass(TestSpek::class)

        assertThat(recorder.testSuccessfulCount, equalTo(1))
        assertThat(recorder.testFailureCount, equalTo(1))
    }
}
