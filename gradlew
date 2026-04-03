#!/bin/bash

# Gradle Wrapper Script

if [ -n "$GRADLE_USER_HOME" ]; then
    export GRADLE_USER_HOME
fi

# Resolve the gradle wrapper executable
GRADLE_CMD="$(dirname "$0")/gradle" 

if [ -x "$GRADLE_CMD" ]; then
    exec "$GRADLE_CMD" "","$@"
else
    echo "Gradle wrapper not found!"
    exit 1
fi
