package musicalnumbers

import java.io._
import scala.collection.immutable._

object MusicalNumbers{

  var firstNumber = 0

  def main(args: Array[String]): Unit = {
    Options.parser.parse(args, Config()) match {
      case Some(config) =>
        println(s"${config}")

        val inputName = config.inputFileName
        val tuneType = config.tuneType
        val key = config.key

        firstNumber = getFirstNumber(config.inputFileName)

        val fis = utils.getFileInputStream(config.inputFileName)

        val tuneName = inputName.split('.').head.split('/').last.split('_').map(s => s(0).toUpper + s.tail).mkString(" ")

        val outputName = inputName.replace(".txt", ".abc")

        val header = abcHeader(tuneName, tuneType, key)
        val body = abcBody(tuneType, fis, key)
        utils.writeToFile(outputName, List(header,body).mkString("\n"))

      case None =>
      // arguments are bad, error message will have been displayed
    }
  }

  def getFirstNumber(fileName: String) : Int = {
    val fs = utils.getFileInputStream(fileName)
    val firstNumber = getNextNumber(fs)
    fs.close()
    firstNumber
  }

  def getNextNumber(fs: FileInputStream) : Int = {
    var ch : Char = 'a'
    while(!ch.isDigit){
      ch = fs.read().toChar
    }

    ch.toInt - 0x30
  }

  val tuneInfo = Map(
    "jig"->("6/8", "1/8", "jig", "Jig", 96, 6),
    "reel"->("C|", "1/8", "reel", "Reel", 128, 8),
    "march"->("2/2", "1/4", "march", "March", 64, 4)
  )


  val abcNotes =
    List("C,", "D,", "E,", "F,", "G,", "A,", "B,",
         "C", "D", "E", "F", "G", "A", "B",
         "c", "d", "e", "f", "g", "a", "b")

  val digits = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val keyIndex = Map(
    "C" -> 7,
    "D" -> 8,
    "E" -> 9,
    "F" -> 10,
    "G" -> 11,
    "A" -> 12,
    "B" -> 13
  )

  def getNoteABCMapping(key: String) = {
    val rootIdx = keyIndex(key)
    digits.foldLeft(Map[Int, String]())((map: Map[Int, String], number: Int) => {
      // val abcNoteIdx = (number + rootIdx) % abcNotes.size
      val abcNoteIdx = number - firstNumber + rootIdx
      map + (number  -> abcNotes(abcNoteIdx))
    })
  }

  val measureEnd = List(
    "|", "|", "|", "|\n",
    "|", "|", "|", ":||\n",
    "|", "|", "|", "|\n",
    "|", "|", "|", ":|\n"
  )

  def abcHeader(tuneName: String, tuneType: String, key: String) : String = {
    val timeSignature = tuneInfo(tuneType)._1
    val noteLength = tuneInfo(tuneType)._2
    val tuneTypeName = tuneInfo(tuneType)._4

    s"""
       |X:1
       |T:${tuneName} ${tuneTypeName}
       |M:${timeSignature}
       |L:${noteLength}
       |R:${tuneType}
       |K:${key}""".stripMargin
  }

  def abcBody(tuneType: String, fs: FileInputStream, key: String) : String = {
    val notesPerMeasure = tuneInfo(tuneType)._6
    val noteABCMapping = getNoteABCMapping(key)
    println(s"Note to ABC Mapping\n${noteABCMapping.mkString("\n")}\n")

    val builder = new StringBuilder

    for(line <- 0 until 4) {
      val lyrics = new StringBuilder
      lyrics.append(s"w: ")
      if (line == 2)
        builder.append("|:")
      for(measure <- 0 until 4) {
        for (note <- 0 until notesPerMeasure / 2) {
          val number = getNextNumber(fs)
          builder.append(s"${noteABCMapping(number)}")
          lyrics.append(s"${number} ")
        }
        builder.append(" ")
        for (note <- notesPerMeasure / 2 until notesPerMeasure) {
          val number = getNextNumber(fs)
          builder.append(s"${noteABCMapping(number)}")
          lyrics.append(s"${number} ")
        }
        builder.append(measureEnd(line * 4 + measure))
        lyrics.append(" ")
      }
      lyrics.append("\n")
      builder.append(lyrics.toString())
    }
    builder.toString()
  }

}