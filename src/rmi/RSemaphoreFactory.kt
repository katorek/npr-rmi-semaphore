package rmi

import java.io.Serializable
import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject

class RSemaphoreFactory(
    val semaphores: HashMap<Long, IRSemaphore> = HashMap()
) : UnicastRemoteObject(), Serializable, IRSemaphoreFactory {

    @Synchronized
    @Throws(RemoteException::class)
    override fun createSemapore(id: Long, permits: Int): Boolean {
        when (semaphores.containsKey(id)) {
            true -> return false
            false -> {
                try {
                    val rs = RSemaphore(permits)
                    semaphores.put(id, rs)
                    return true
                } catch (e: RemoteException) {
                    e.printStackTrace()
                    return false
                }
            }
        }
    }

    @Throws(RemoteException::class)
    override fun getSemaphore(id: Long): IRSemaphore? {
        return semaphores.get(id)
    }


}