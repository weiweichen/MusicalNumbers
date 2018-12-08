package musicalnumbers

import java.io._
import scala.collection.immutable._

object MusicalNumbers{

  var firstNote : Option[Int] = None

  def main(args: Array[String]): Unit = {
    val inputName = args(0)
    val tuneType = args(1)
    val key = args(2)
    val fis = utils.getFileInputStream(args)

    val tuneName = inputName.split('.').head.split('/').last.split('_').map(s => s(0).toUpper + s.tail).mkString(" ")

    val outputName = inputName.replace(".txt", ".abc")

    val header = abcHeader(tuneName, tuneType, key)
    val body = abcBody(tuneType, fis, key)
    utils.writeToFile(outputName, List(header,body).mkString("\n"))
  }

  def getNextNumber(fs: FileInputStream) : Int = {
    var ch : Char = 'a'
    while(!ch.isDigit){
      ch = fs.read().toChar
    }

    val number = ch.toInt - 0x30

    firstNote match {
      case Some(_) =>
      case None => firstNote = Some(number)
    }

    // (number + 10 - firstNote.get) % 10
    number
  }

  val tuneInfo = Map(
    "jig"->("6/8", "1/8", "jig", "Jig", 96, 6),
    "reel"->("C|", "1/8", "reel", "Reel", 128, 8),
    "march"->("2/2", "1/4", "march", "March", 64, 4)
  )


  val abcNotes = List('C', 'D', 'E', 'F', 'G', 'A', 'B')
  val digits = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val keyIndex = Map(
    "C" -> 0,
    "D" -> 1,
    "E" -> 2,
    "F" -> 3,
    "G" -> 4,
    "A" -> 5,
    "B" -> 6
  )

  def getNoteABCMapping(key: String) = {
    val rootIdx = keyIndex(key)
    digits.foldLeft(Map[Int, Char]())((map: Map[Int, Char], number: Int) => {
      val abcNoteIdx = (number + rootIdx) % abcNotes.size
      map + (number -> abcNotes(abcNoteIdx))
    })
  }

  val measureEnd = List(
    "|", "|", "|", "|\n",
    "|", "|", "|", ":||\n|:",
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
    val totalNumNotes = tuneInfo(tuneType)._5
    val noteABCMapping = getNoteABCMapping(key)
    println(s"Note to ABC Mapping\n${noteABCMapping.mkString("\n")}\n")

    val builder = new StringBuilder

    for(line <- 0 until 4) {
      for(measure <- 0 until 4) {
        for (note <- 0 until notesPerMeasure / 2) {
          val number = getNextNumber(fs)
          builder.append(s"${noteABCMapping(number)}")
        }
        builder.append(" ")
        for (note <- notesPerMeasure / 2 until notesPerMeasure) {
          val number = getNextNumber(fs)
          builder.append(s"${noteABCMapping(number)}")
        }
        builder.append(measureEnd(line * 4 + measure))
      }
    }
    builder.toString()
  }

}