package com.example.demotestcontainer

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.logging.Logger


@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class DemoTestcontainerApplicationTests {
    val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var customerRepository: CustomerRepository

    companion object {

        val logger = LoggerFactory.getLogger(this::class.java)

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            postgres.start()
        }

//        @AfterAll
//        @JvmStatic
//        fun afterAll() {
//            postgres.stop()
//        }

        @Container
        @JvmStatic
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:15-alpine")
            .withLogConsumer { Slf4jLogConsumer(logger) }

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }
    }


    @Test
    fun shouldGetAllCustomers() {
        val customers = listOf(
            Customer(null, "John"),
            Customer(null, "Dennis")
        )
        customerRepository.saveAll(customers.asIterable())
        val customerFetches = customerRepository.findAll().apply {
			println(this)
		}
		assert(customerFetches.toList().size == 2)
        println("Done!")
    }

}
