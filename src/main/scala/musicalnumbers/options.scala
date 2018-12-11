/**
  * Copyright Weiwei Chen 2018
  * Author: Weiwei Chen (weiwei.chen.uci@gmail.com)
  */

package musicalnumbers

case class Config(inputFileName: String = "resources/numbers/pi.txt",
                  tuneType: String = "",
                  key: String = "",
                  rhythmPattern: String = "",
                  noteMapping: String = "direct",
                  verbose: Boolean = false)

object Options {

  val validKeys = Set[String]("C", "D", "E", "F", "G", "A", "B")
  val validTuneTypes = Set[String]("jig", "march", "reel")
  val noteMappings = Set[String]("direct", "wrapped")

  val parser = new scopt.OptionParser[Config]("MuscialNumbers-X.Y") {
    head("Musical Numbers", "0.0")

    opt[String]('i', "input").action((x, c) =>
      c.copy(inputFileName = x)).text("Input File Name")

    opt[String]('t', "tunetype").required().action((x, c) =>
      c.copy(tuneType = x)).validate(x =>
        if (validTuneTypes.contains(x)) success
        else {
          failure(s"Tune type needs to be one of these: [${validTuneTypes.mkString(", ")}].")
        }
      ).text("Tune Type, i.e. jig, march, reel")

    opt[String]('k', "key").required().action((x, c) =>
      c.copy(key = x)).validate(x =>
      if (validKeys.contains(x)) success
      else {
        failure(s"Tune key signature needs to be one of these: [${validKeys.mkString(", ")}].")
    }).text("Tune Key Signature, i.e. C, D, E, F, G, A, B")

    opt[String]('m', "notemapping").action((x, c) =>
      c.copy(noteMapping = x)).validate(x =>
      if (noteMappings.contains(x)) success
      else {
        failure(s"Note Mapping needs to be one of these: [${noteMappings.mkString(", ")}].")
    }).text("Note Mapping Strategy")

    opt[String]('r', "rhythmpattern").action((x, c) =>
      c.copy(rhythmPattern = x)).text("Rhythm Pattern (Support TBD).")

    help("help").text("prints this usage text")

    opt[Unit]('v', "verbose").action( (_, c) =>
      c.copy(verbose = true) ).text("verbose is a flag")
  }

}