package net.themkat.spek.spring.utils.context

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

    fun <T> inject(clazz: Class<T>) = applicationContext.getBean(clazz)

    fun <T> autowire(clazz: Class<T>) = inject(clazz)

    @Transactional
    fun transactional(operation: () -> Unit) = operation()
}

fun springContext(basePackage: String, springContextBody: SpringContext.() -> Unit) {
    val springContext: SpringContext = SpringContext(basePackage)
    springContext.springContextBody()
    //springContext.closeContext() // TODO: find a way to close this once spek is done
}