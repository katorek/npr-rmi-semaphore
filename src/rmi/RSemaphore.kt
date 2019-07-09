package rmi

import java.io.Serializable
import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject
import java.util.concurrent.Semaphore

class RSemaphore(
    val permits: Int,
    val semaphore: Semaphore = Semaphore(permits)
): IRSemaphore, UnicastRemoteObject(), Serializable {

    @Throws(RemoteException::class)
    override fun acquire(permits: Int): Int {
        semaphore.acquire(permits)
        return semaphore.availablePermits()
    }

    @Throws(RemoteException::class)
    override fun release(permits: Int): Int {
        semaphore.release(permits)
        return semaphore.availablePermits()
    }
}