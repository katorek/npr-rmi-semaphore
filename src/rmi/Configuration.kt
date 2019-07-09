package rmi

import kotlin.random.Random

class Configuration private constructor(
    val ip: String = "127.0.0.1",
    val registerName: String = "SemaphoreFactoryRegister",
    val port: Int
){
    val minSleepTime = 1000L
    val maxSleepTime = 4000L
    val random = Random

    fun workTime(): Long {
        return random.nextLong(minSleepTime*4, maxSleepTime*4)
    }

    fun sleepTime(): Long {
        return random.nextLong(minSleepTime*2, (maxSleepTime * 1.5).toLong())
    }

    companion object {
        var defaulltPort = 1099
        val r = Random
        val configuration: Configuration = Configuration(port = defaulltPort)
        operator fun invoke(): Configuration {
            return configuration
        }

        fun withNewPort() {
            defaulltPort = r.nextInt()
        }
    }
}