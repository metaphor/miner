#!/bin/sh

gitHash=`git rev-parse --short HEAD`
echo "GIT_HASH=${gitHash}" > gitHash.properties
