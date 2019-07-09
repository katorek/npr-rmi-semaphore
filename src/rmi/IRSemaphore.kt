package rmi

import java.io.Serializable
import java.rmi.Remote
import java.rmi.RemoteException

interface IRSemaphore: Remote, Serializable {
    @Throws(RemoteException::class)
    fun acquire(permits: Int): Int

    @Throws(RemoteException::class)
    fun release(permits: Int): Int
}