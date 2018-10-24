package net.themkat.spek.spring.utils.mockmvc

import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers.hasSize
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class MockMvc(val controller: Any) {

    fun standaloneSetup() = MockMvcBuilders.standaloneSetup(controller).build()

    fun get(url: String) = MockMvcRequestBuilders.get(url)

    fun post(url: String) = MockMvcRequestBuilders.post(url)

    fun put(url: String) = MockMvcRequestBuilders.put(url)

    fun status() = MockMvcResultMatchers.status()

    fun jsonPath(expression: String, vararg args: Any) = MockMvcResultMatchers.jsonPath(expression, args)

    fun <T> jsonPath(expression: String, matcher: Matcher<T>) = MockMvcResultMatchers.jsonPath(expression, matcher)

    fun jsonPathEquals(expression: String, equals: Any) = jsonPath(expression).value(CoreMatchers.equalTo(equals))

    fun jsonPathSize(expression: String, size: Int) = jsonPath(expression, hasSize<Number>(size))
}

fun mockMvc(controller: Any, mockMvcBody: MockMvc.() -> Unit) {
    val mockMvcObj = MockMvc(controller)
    mockMvcObj.mockMvcBody()
}