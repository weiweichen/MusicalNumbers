package musicalnumbers

case class Config(inputFileName: String = "resources/numbers/pi.txt",
                  tuneType: String = "",
                  key: String = "",
                  rhythmPattern: String = "")


object Options {

  val validKeys = Set[String]("C", "D", "E", "F", "G", "A", "B")
  val validTuneTypes = Set[String]("jig", "match", "reel")

  val parser = new scopt.OptionParser[Config]("MuscialNumbers-X.Y") {
    head("Musical Numbers", "0.0")

    opt[String]('i', "input").action((x, c) =>
      c.copy(inputFileName = x)).text("Input File Name")

    opt[String]('t', "tune type").required().action((x, c) =>
      c.copy(tuneType = x)).validate(x =>
        if (validTuneTypes.contains(x)) success
        else {
          failure(s"Tune type needs to be one of these: [${validTuneTypes.mkString(", ")}].")
        }
      ).text("Tune Type")

    opt[String]('k', "key").required().action((x, c) =>
      c.copy(key = x)).validate(x =>
      if (validTuneTypes.contains(x)) success
      else {
        failure(s"Tune key signature needs to be one of these: [${validKeys.mkString(", ")}].")
    }).text("Tune Key Signature")

    opt[String]('r', "rhythm pattern").action((x, c) =>
      c.copy(rhythmPattern = x)).text("Rhythm Pattern (Support TBD).")
  }

}