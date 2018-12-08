package musicalnumbers

import java.io._
import scala.collection.immutable._

object utils {
    def writeToFile(fileName: String, content: String) = {
    val file = new File(fileName)
    println(s"Output File: ${fileName}")
    if (!file.exists()) {
      file.createNewFile()
    }
    val fos = new FileOutputStream(file, false)
    val outStream = new DataOutputStream(new BufferedOutputStream(fos))
    outStream.writeUTF(content)
    outStream.close()
    println(content)
  }

  def dummy(args: Array[String]) = {
    val fis = getFileInputStream(args)
    while(fis.available() > 0) {
      val current = fis.read().toChar
      println(current)
    }
  }

  def getFileInputStream(args: Array[String]) : FileInputStream = {
    val file = new File(args(0))
    if (!file.exists()) {
      println(args(0)+ " does not exist.")
      return null
    }

    if (!(file.isFile() && file.canRead())) {
      println(s"${file.getName} cannot be read from.")
      return null
    }

    try {
      val fis = new FileInputStream(file)
      return fis
    } catch  {
      case _: Throwable =>
        throw new Exception(s"${file.getName} cannot be read from.")
    }
  }

}

