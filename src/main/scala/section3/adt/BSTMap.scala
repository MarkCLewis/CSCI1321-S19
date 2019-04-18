package section3.adt
import collection.mutable

class BSTMap[K, V](lt: (K, K) => Boolean) extends mutable.Map[K, V] {
  import BSTMap._

  private var root: Node[K, V] = null

  def -=(key: K): this.type = {
    def removeNode(n: Node[K, V]): Node[K, V] = {
      if(n == null) n else {
        if (lt(key, n.key)) {
          n.left = removeNode(n.left)
          n
        }
        else if (lt(n.key, key)) {
          n.right = removeNode(n.right)
          n
        }
        else {
          if(n.left == null) n.right
          else if(n.right == null) n.left
          else {
            if (n.left.right == null) {
              n.left.right = n.right
              n.left
            } else {
              // helper goes here
              ???
            }
          }
        }
      }
    }
    root = removeNode(root)
    this
  }
  def +=(kv: (K, V)): this.type = {
    def addNode(n: Node[K, V]): Node[K, V] = {
      if (n == null) new Node(kv._1, kv._2, null, null)
      else {
        if (lt(kv._1, n.key)) n.left = addNode(n.left)
        else if (lt(n.key, kv._1)) n.right = addNode(n.right)
        else n.value = kv._2
        n
      }
    }
    root = addNode(root)

    this
  }

  // Members declared in scala.collection.MapLike
  def get(key: K): Option[V] = {
    var rover = root
    while (rover != null && !lt(rover.key, key) && !lt(key, rover.key)) {
      if (lt(key, rover.key)) rover = rover.left
      else rover = rover.right
    }
    if (rover == null) None else Some(rover.value)
  }
  def iterator = new Iterator[(K, V)] {
    private val stack = new ListStack[Node[K, V]]()
    private def pushAllLeft(n: Node[K, V]): Unit = {
      if(n != null) {
        stack.push(n)
        pushAllLeft(n.left)
      }
    }
    pushAllLeft(root)
    def hasNext(): Boolean = !stack.isEmpty
    def next(): (K, V) = {
      val ret = stack.pop
      pushAllLeft(ret.right)
      ret.key -> ret.value
    }
  }
  override def update(key: K, value: V): Unit = {
    this += ((key, value))
  }

  def preorder(n: Node[K, V], visit: (K, V) => Unit): Unit = {
    if (n != null) {
      visit(n.key, n.value)
      preorder(n.left, visit)
      preorder(n.right, visit)
    }
  }

  def postorder(n: Node[K, V], visit: (K, V) => Unit): Unit = {
    if (n != null) {
      postorder(n.left, visit)
      postorder(n.right, visit)
      visit(n.key, n.value)
    }
  }
  
  def inorder(n: Node[K, V], visit: (K, V) => Unit): Unit = {
    if (n != null) {
      inorder(n.left, visit)
      visit(n.key, n.value)
      inorder(n.right, visit)
    }
  }
}

object BSTMap {
  class Node[K, V](val key: K, var value: V, var left: Node[K, V], var right: Node[K, V])
}