package com.example.demotestcontainer

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestDemoTestcontainerApplication

fun main(args: Array<String>) {
	fromApplication<DemoTestcontainerApplication>().with(TestDemoTestcontainerApplication::class).run(*args)
}
