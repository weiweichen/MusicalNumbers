# MusicalNumbers

## Minimum Prerequisite to build and run
1. jdk (for running java code)
   https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
   MacOS: https://www.chrisjmendez.com/2018/10/14/how-to-install-java-on-osx-using-homebrew/
  
1. scala-lang (well, since the program is written in Scala)
   https://www.scala-lang.org/download/
   MacOS: http://sourabhbajaj.com/mac-setup/Scala/README.html
1. sbt (for building scala program)
   https://www.scala-sbt.org/index.html
   MacOS: https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Mac.htm0l
   
Install everything on MacOs:
   https://medium.freecodecamp.org/installing-scala-and-apache-spark-on-mac-os-837ae57d283f
   
## How to build
```
$ cd MusicalNumbers/
$ sbt assembly
```
The target jar will be ```target/scala-2.11/MusicalNumbers-assembly-0.0.jar```

## How to run



```
$ cd MusicalNumbers/
$ 
```

Program usage:
```
Usage: MuscialNumbers-X.Y [options]

  -i, --input <value>      Input File Name
  -t, --tune type <value>  Tune Type
  -k, --key <value>        Tune Key Signature
  -r, --rhythm pattern <value>
                           Rhythm Pattern (Support TBD).
```