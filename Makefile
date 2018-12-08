
compile:
	sbt assembly 

all: compile
	 java -jar target/scala-2.11/Musical-Numbers-assembly-1.0-SNAPSHOT.jar resources/SquareRoots/2.txt resources/SquareRoots/2.abc

run2:
	 java -jar target/scala-2.11/Musical-Numbers-assembly-1.0-SNAPSHOT.jar resources/SquareRoots/2.txt resources/SquareRoots/2.abc

