#!/bin/sh  
# Define some constants  
NLP=nlp  
PROJECT_PATH=./ 
JAR_PATH=$PROJECT_PATH/lib  
BIN_PATH=$PROJECT_PATH/bin  
SRC_PATH=$PROJECT_PATH/src/$NLP  
  
# First remove the sources.list file if it exists and then create the sources file of the project  
rm -f $SRC_PATH/sources  
find $SRC_PATH/com -name *.java > $SRC_PATH/sources.list  
  
# First remove the ONSServer directory if it exists and then create the bin directory of ONSServer  
rm -rf $BIN_PATH/$NLP  
mkdir $BIN_PATH/$NLP  
  
# Compile the project  
javac -d $BIN_PATH/$NLP -classpath "$JAR_PATH/*.jar" @$SRC_PATH/sources.list