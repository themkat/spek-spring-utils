package net.themkat.spek.spring.utils.context

import org.spekframework.spek2.dsl.Root
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.transaction.annotation.Transactional

class SpringContext {
    var applicationContext: ConfigurableApplicationContext

    constructor(basePackage: String) {
        applicationContext = AnnotationConfigApplicationContext().apply {
            scan(basePackage)
            refresh()
        }
    }

    internal fun closeContext() {
        applicationContext.close()
    }

    inline fun <reified T> inject() = applicationContext.getBean(T::class.java)

    inline fun <reified T> autowire() = inject<T>()

    @Transactional
    fun transactional(operation: () -> Unit) = operation()
}

fun Root.springContext(basePackage: String, springContextBody: SpringContext.() -> Unit) {
    val springContext: SpringContext = SpringContext(basePackage)
    springContext.springContextBody()

    afterGroup {
        springContext.closeContext()
    }
}