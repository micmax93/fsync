package pl.poznan.put.student.scala.fsync.cli

import pl.poznan.put.student.scala.fsync.actors.{Server, Client}
import pl.poznan.put.student.scala.fsync.communication.communicators.socket.{ServerCommunicator, ClientCommunicator}

object Fsync extends App {

  override def main(args: Array[String]): Unit = {
    val errorCode = analyzeArguments(args)
    if (errorCode == 1) {
      println(Console.RED + "Invalid argument" + Console.RESET)
    }
    System.exit(errorCode)
  }


  def analyzeArguments(args: Array[String]): Int = {
    args.length match {
      case 0 =>
        println(Console.GREEN + "File synchronization tool [v1.0] El Fartas & Biernacki" + Console.RESET)
        println(Console.YELLOW + "usage: fsync <mode> <command> <address>" + Console.RESET)
        println("<mode>          - server/client")
        println("<command>       - pull/push (only client mode)")
        println("<address>       - ip address or hostname (only client mode) \n")
        println("<directoryName> - name of repository directory (only client mode) \n")
        1
      case 1 =>
        if (args(0).toLowerCase != "server") {
          1
        } else {
          becomeServer(args)
        }
      case 4 =>
        if (args(0).toLowerCase != "client") {
          1
        } else {
          becomeClient(args)
        }
      case _ => 1

    }

  }

  def becomeClient(args: Array[String]): Int = {
    val actor = new Client()
    val arguments = Map("command" -> args(1).toLowerCase, "address" -> args(2).toLowerCase, "directoryName" -> args(3))
    val communicator = new ClientCommunicator(actor, arguments)
    communicator.initialize()
    0
  }

  def becomeServer(args: Array[String]): Int = {
    val arguments = Map[String, String]()
    val actor = new Server()
    val serverCommunicator = new ServerCommunicator(actor, arguments)
    serverCommunicator.initialize()
    0
  }


}
