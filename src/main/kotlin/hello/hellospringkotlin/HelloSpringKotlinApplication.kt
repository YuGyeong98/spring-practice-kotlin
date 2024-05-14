package hello.hellospringkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringKotlinApplication

fun main(args: Array<String>) {
	runApplication<HelloSpringKotlinApplication>(*args)
}
