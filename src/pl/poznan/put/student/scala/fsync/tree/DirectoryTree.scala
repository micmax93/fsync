package pl.poznan.put.student.scala.fsync.tree

import java.nio.file.Path

class DirectoryTree(fullPath: Path, rootNode: TreeNode) {
  val path: Path = fullPath
  val root: TreeNode = rootNode

  def hash: String = {
    root.hash
  }

  override def toString: String = {
    "{\n path: " + path.toString + ",\n hash: " + hash + ",\n root: " + root.toString + "\n}"
  }

  override def equals(obj: scala.Any): Boolean = {
    val result = false
    val tree = obj.asInstanceOf[DirectoryTree]
    if (tree != null) {
      this.path.equals(tree.path) && this.root.equals(tree.root)
    }
    result
  }
}
