#! /bin/bash

#--------------------------------------------------------------------
# Script test de lancement d'un agent 
#--------------------------------------------------------------------


export JMX_PORT=50200
export JMX_REGISTRY_PORT=50100
export JMX_ROLE=user
export JMX_PWD=mdp

HOME_PROJET=$PWD/..

export BATCH_PROJET=$HOME_PROJET/bin

export JAVA_OPTS="-Xms64m -Xmx128m -XX:MaxPermSize=64m \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=$JMX_PORT \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=true \
-Dcom.sun.management.jmxremote.access.file=$HOME_PROJET/tests/jmxaccessfile \
-Dcom.sun.management.jmxremote.password.file=$HOME_PROJET/tests/jmxpasswordfile \
-Djava.io.tmpdir=/tmp \
-Djava.awt.headless=true "

echo "BATCH_PROJET = "$BATCH_PROJET
$BATCH_PROJET/launcher_batch.sh start $JMX_REGISTRY_PORT
