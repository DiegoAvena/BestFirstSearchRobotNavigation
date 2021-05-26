#The source files to be compiled:
SOURCES := *.java

#The compiler command:
CC := javac

#The run command:
INCLUDES := -I ../include
RUNCOMMAND := java

#Main program file name:
PROGRAMNAME := Simulator

clean:
	find . -type f -name "*.class" -exec rm '{}' \;
	
#Compiles all of the java source files in the same directory:
all:
	$(CC) $(SOURCES)

#Runs the java file with the main method with command line arguments:
run:
	$(RUNCOMMAND) $(PROGRAMNAME) $(F)
