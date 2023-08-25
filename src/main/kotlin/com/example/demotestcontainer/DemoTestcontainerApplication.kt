package com.example.demotestcontainer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.devtools.restart.RestartScope
import org.springframework.boot.runApplication
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.data.annotation.Id
import org.springframework.data.repository.CrudRepository
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootApplication
class DemoTestcontainerApplication


fun main(args: Array<String>) {
	runApplication<DemoTestcontainerApplication>(*args)
}

interface CustomerRepository : CrudRepository<Customer, Int>
data class Customer(@Id val id: Int?, val name: String)
