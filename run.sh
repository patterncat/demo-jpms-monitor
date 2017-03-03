#!/bin/bash

echo ""
echo "--- LAUNCH ---"

echo " > run monitor"
echo ""

# Add these arguments to connect with remote debugger
#	-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 \

# the classpath is needed for Spark's dependencies
java9 \
	--module-path mods \
	--class-path "libs/*" \
	--add-modules java.sql,java.xml.bind \
	--module monitor
