package com.bsuir.vmsis
import scala.collection.JavaConversions._
import java.util.ArrayList
import com.bsuir.vmsis.FileSystem

object ScalaQuickSort {

  private def quickSort[T](list: List[T])(implicit o: Ordering[T]): List[T] =
    list match {
      case Nil => Nil      
      case (headElement :: tailElement) =>
        val baseElement = headElement
        val (lessThanBase, greaterThanBase) = tailElement.partition(element => o.lteq(baseElement, element))    // lteg return true if  baseElement <= element  
        quickSort(lessThanBase) ::: baseElement :: quickSort(greaterThanBase)    
    }
  
  def scalaQuickSort(list: ArrayList[FileSystem]): List [FileSystem] = {

    val sortedList = list.toList
    quickSort(sortedList) 
  }
}