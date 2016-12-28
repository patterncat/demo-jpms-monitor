#!/bin/bash

echo "--- COMPILATION & PACKAGING ---"

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r mods
mkdir mods

echo " > creating monitor.observer"
javac9 \
	-d classes/monitor.observer \
	$(find monitor.observer -name '*.java')
jar9 --create \
	--file mods/monitor.observer.jar \
	-C classes/monitor.observer .

echo " > creating monitor.observer.alpha"
javac9 \
	--module-path mods \
	-d classes/monitor.observer.alpha \
	$(find monitor.observer.alpha -name '*.java')
jar9 --create \
	--file mods/monitor.observer.alpha.jar \
	-C classes/monitor.observer.alpha .


echo " > creating monitor.observer.beta"
javac9 \
	--module-path mods \
	-d classes/monitor.observer.beta \
	$(find monitor.observer.beta -name '*.java')
jar9 --create \
	--file mods/monitor.observer.beta-1.0.jar \
	--module-version 1.0 \
	-C classes/monitor.observer.beta .
jar9 --create \
	--file mods/monitor.observer.beta-2.0.jar \
	--module-version 2.0 \
	-C classes/monitor.observer.beta .


echo " > creating monitor.statistics"
javac9 \
	--module-path mods \
	-d classes/monitor.statistics \
	$(find monitor.statistics -name '*.java')
jar9 --create \
	--file mods/monitor.statistics.jar \
	-C classes/monitor.statistics .

echo " > creating monitor.persistence"
javac9 \
	--module-path mods \
	-d classes/monitor.persistence \
	$(find monitor.persistence -name '*.java')
jar9 --create \
	--file mods/monitor.persistence.jar \
	-C classes/monitor.persistence .

echo " > creating monitor"
javac9 \
	--module-path mods \
	-d classes/monitor \
	$(find monitor -name '*.java')
jar9 --create \
	--file mods/monitor.jar \
	--main-class monitor.Monitor \
	-C classes/monitor .
