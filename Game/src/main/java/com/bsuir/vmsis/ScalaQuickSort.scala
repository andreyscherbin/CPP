package com.bsuir.vmsis
import scala.collection.JavaConversions._
import java.util.ArrayList
import com.bsuir.vmsis.FileSystem

/*trait Ord {
  def <(that: Any): Boolean
  def <=(that: Any): Boolean = (this < that) || (this == that)
  def >(that: Any): Boolean = !(this <= that)
  def >=(that: Any): Boolean = !(this < that)
}

class fileM() extends Ord {

  // return 0 if the same; negative if this < that; positive if this > that
  def <(that: Any): Boolean = {
    true
  }
  def >(that: Any): Boolean = {
    true
  }

}*/

 /* def quickSort[fileM](xs: Array[fileM]): Array[fileM] = {
    if (xs.length <= 1) xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        quickSort(xs filter (pivot >)),
        xs filter (pivot ==),
        quickSort(xs filter (pivot <)))
    }
  }*/

object ScalaQuickSort {

  private def quickSort[T](list: List[T])(implicit o: Ordering[T]): List[T] =
    list match {
      case Nil => Nil
      case (head :: tail) =>
        val pivot = head
        val (lessThanPivot, greaterThanPivot) = tail.partition(e => o.lteq(pivot, e))
        quickSort(lessThanPivot) ::: pivot :: quickSort(greaterThanPivot)
    }
  
  def scalaQuickSort(list: ArrayList[FileSystem]): Unit = {

    val sortedList = list.toList
    this.quickSort(sortedList)
  }
}