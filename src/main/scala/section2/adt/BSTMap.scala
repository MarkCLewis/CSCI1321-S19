package section2.adt

import collection.mutable

class BSTMap[K, V] extends mutable.Map[K, V] {
  def -=(key: K): this.type = {
    ???
  }
  def +=(kv: (K, V)): this.type = {
    ???
  }
  
  def get(key: K): Option[V] = {
    ???
  }
  def iterator: Iterator[(K, V)] = {
    ???
  }
}

object BSTMap {
  private class Node[K, V](val key: K, var value: V, var left: Node[K, V], var right: Node[K, V])
}