package pl.poznan.put.student.scala

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import pl.poznan.put.student.scala.fsync.tree.DirectoryTree
import pl.poznan.put.student.scala.fsync.tree.builder.TreeBuilder
import pl.poznan.put.student.scala.fsync.utils.Container

object Tester extends App {

  def saveTree(tree: DirectoryTree): Unit = {
    val file = new FileOutputStream("tree.fsync")
    val output = new ObjectOutputStream(file)
    output.writeObject(tree)
    output.close()
  }

  def loadTree(): DirectoryTree = {
    val file = new FileInputStream("tree.fsync")
    val input = new ObjectInputStream(file)
    val tree = input.readObject().asInstanceOf[DirectoryTree]
    input.close()
    tree
  }

  val treeBuilder: TreeBuilder = Container.getTreeBuilder
  val differenceGenerator = Container.getDifferenceGenerator

  val tree = treeBuilder.generateTree("/home/phisikus/eagle")
  println(tree.root.toString)
  /*val tree2 = loadTree()

  val difference = differenceGenerator.generate(tree2, tree)
  println(difference.toString)

  saveTree(tree)*/

}
