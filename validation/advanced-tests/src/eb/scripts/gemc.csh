#!/bin/csh -f

if ( ! -e "$1.txt" ) then
    echo Missing input file:  $1.txt
    exit
endif
if ( -e "$1.evio" ) then
    echo Output file already exists:  $1.evio
    exit
endif

set run = 11
set nEvents = 100
set gcard = /group/clas12/gemc/5.0/config/clas12-default.gcard 

gemc \
    $gcard \
    -INPUT_GEN_FILE="LUND, $1.txt" \
    -OUTPUT="hipo, $1.hipo" \
    -RUNNO=$run \
    -USE_GUI=0 \
    -N=$nEvents

