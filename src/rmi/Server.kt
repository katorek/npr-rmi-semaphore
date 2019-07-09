package rmi

import java.rmi.Naming
import java.rmi.RMISecurityManager
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry
import java.rmi.server.ExportException


fun main() {
    val config = Configuration()
    System.setProperty("java.security.policy","file:/home/wjar/studia/npr/rmi/resources/security.policy");

    when {
//        System.getSecurityManager() == null -> System.setSecurityManager(SecurityManager())
        System.getSecurityManager() == null -> System.setSecurityManager(RMISecurityManager())
    }

    try {

        val factory = RSemaphoreFactory()
        var registry: Registry

        try {
            registry = LocateRegistry.createRegistry(config.port)
        } catch (e: ExportException) {
            registry = LocateRegistry.getRegistry(config.port)
        }


        registry.bind(config.registerName, factory)
        println("Started")
    } catch (e: Exception) {
        error("Exception: \n${e.printStackTrace()}")
    }
}