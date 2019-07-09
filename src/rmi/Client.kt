package rmi

import java.lang.Exception
import java.rmi.registry.LocateRegistry
import java.util.concurrent.ThreadLocalRandom

fun main() {
    val configuration = Configuration()

    try {
        val registry = LocateRegistry.getRegistry(configuration.ip, configuration.port)
        val rSemaphoreFactory = registry.lookup(configuration.registerName) as IRSemaphoreFactory

        val permitsList = arrayListOf(4)
        permitsList.forEachIndexed { index, element -> rSemaphoreFactory.createSemapore(index.toLong(), element) }

        val r = ThreadLocalRandom.current()

        while (true) {
            val id = r.nextInt(permitsList.size)
            val permits = r.nextInt(permitsList.get(id)) + 1

            val sem = rSemaphoreFactory.getSemaphore(id.toLong()) as IRSemaphore

            println("Trying to aquire $permits from $id")
            val rem = sem.acquire(permits)
            println("Acquired $permits from $id, remaining: $rem")

            Thread.sleep(configuration.workTime())

            val rem2 = sem.release(permits)
            println("Released $permits from $id, remaining: $rem2")

            Thread.sleep(configuration.sleepTime())
        }

    } catch (e: Exception) {
        error("Err: \n;$e")
    }
}