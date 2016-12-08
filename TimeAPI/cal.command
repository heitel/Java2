#! /bin/bash
clear

read  -p "Bitte geben Sie eine Jahreszahl ein: " year
cd ~/Documents/w2017/TimeAPI/bin
for i in {1..12}; do
	java KalenderD $i $year
done;
read -n 1 -p "hit any key" ende
osascript -e \
	'tell application "Terminal" to close first window' \
	& exit