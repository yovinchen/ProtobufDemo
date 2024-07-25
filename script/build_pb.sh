#!/bin/bash
SRC_DIR="."
JAVA_DST_DIR="../src/main/java"
PYTHON_DST_DIR="../src/main/python"

./protoc-27.2-osx-aarch_64/bin/protoc -I=$SRC_DIR --java_out=$JAVA_DST_DIR $SRC_DIR/AllTypes.proto
./protoc-27.2-osx-aarch_64/bin/protoc -I=$SRC_DIR --python_out=$PYTHON_DST_DIR $SRC_DIR/AllTypes.proto