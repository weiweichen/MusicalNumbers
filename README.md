# MusicalNumbers
This is a program to map number series, such as pi, square roots of numbers, ect.
into traditional folk music tunes (i.e. march, jig, reel, etc).

The program is written to full fill the quest of finding out
 which number is the most musical number? 

Email me (weiwei.chen.uci@gmail.com) if you have an answer! 
 

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
The compiled jar will be ```target/scala-2.11/MusicalNumbers-assembly-0.0.jar```

## How to run

```
$ cd MusicalNumbers/
$ java -jar target/scala-2.11/MusicalNumbers-assembly-0.0.jar -i resources/numbers/the_number_of_pi.txt -t jig -k D -m wrapped
```

Program usage:
```
Musical Numbers 0.0
Usage: MuscialNumbers-X.Y [options]

  -i, --input <value>      Input File Name
  -t, --tune type <value>  Tune Type
  -k, --key <value>        Tune Key Signature
  -m, --Note Mapping <value>
                           Note Mapping Strategy
  -r, --rhythm pattern <value>
                           Rhythm Pattern (Support TBD).
  -v, --verbose            verbose is a flag
```

Program output:
```
Output File: resources/numbers/the_number_of_pi.abc

X:1
T:The Number Of Pi Jig
M:6/8
L:1/8
R:jig
K:D
DBE BFC|CGF DFB|CAC DCD|BEG CGE|
w: 3 1 4 1 5 9  2 6 5 3 5 8  9 7 9 3 2 3  8 4 6 2 6 4  
DDB DCA|CFA CBB|EBC ABG|CDC CDA:||
w: 3 3 8 3 2 7  9 5 0 2 8 8  4 1 9 7 1 6  9 3 9 9 3 7  
|:FBA FBC|ACA ECE|EFC CDA|ABB GEA|
w: 5 1 0 5 8 2  0 9 7 4 9 4  4 5 9 2 3 0  7 8 1 6 4 0  
GCB GCA|BCC BGC|BAD EBC|FDE CBB:|
w: 6 2 8 6 2 0  8 9 9 8 6 2  8 0 3 4 8 2  5 3 4 2 1 1 
```

## How to check the result
The program outputs the tune in ABC notation: https://en.wikipedia.org/wiki/ABC_notation

There are many free software for converting ABC notation into sheet music, 
and some can also generate the MIDI for listening, such as:
MuseScore: https://musescore.org/en/download (with *abc import* https://musescore.org/en/project/abc-import) 

## Resources:
The program takes a txt file containing the number series as input.

Some of the sample numbers are located in ```resources/numbers/*.txt```. 
The source of the numbers are from https://apod.nasa.gov/htmltest/rjn_dig.html 

# TODO
1. It's quite obvious that rhythm is an integral part of a ear-pleasing tune. 
Hence, the program shall take some rhythm patterns to add some cadence to the tunes.

1. Support for more tune types such as polka, waltz, ect.

1. Support for chromatic scales instead of diatonic scales only.

1. Auto generator for number sequences, such as generating first k digits of square root N, 
square root of an imaginary number?

1. ...

# About
This project is authored by Weiwei Chen

Email: weiwei.chen.uci@gmail.com   

Link: http://www.cecs.uci.edu/~weiweic/


