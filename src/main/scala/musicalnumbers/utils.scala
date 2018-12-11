package musicalnumbers

import java.io._

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
    val fis = getFileInputStream(args(0))
    while(fis.available() > 0) {
      val current = fis.read().toChar
      println(current)
    }
  }

  def getFileInputStream(fileName: String) : FileInputStream = {
    val file = new File(fileName)
    if (!file.exists()) {
      println(fileName + " does not exist.")
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

