package smimi.issueservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmimiApplication

fun main(args: Array<String>) {
	runApplication<SmimiApplication>(*args)
}
