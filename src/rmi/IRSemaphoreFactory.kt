package rmi

import java.rmi.Remote
import java.rmi.RemoteException

interface IRSemaphoreFactory: Remote {
    @Throws(RemoteException::class)
    fun getSemaphore(id: Long): IRSemaphore?
    @Throws(RemoteException::class)
    fun createSemapore(id: Long, permits: Int): Boolean
}