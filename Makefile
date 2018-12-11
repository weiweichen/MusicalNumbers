
compile:
	sbt assembly 

all: compile
	java -jar target/scala-2.11/MusicalNumbers-assembly-0.0.jar \
	 -i resources/numbers/the_number_of_pi.txt \
	 -t jig -k D -m wrapped -v

