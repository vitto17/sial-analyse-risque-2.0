#! /bin/bash

#--------------------------------------------------------------------
# Script de gestion de l'agent
#--------------------------------------------------------------------
if test "${1}" == ""
then
    echo "Usage: $0 <action : start/stop/restart/status> <liste de parametres specifiques, quand action = start>"
    echo "e.g: launcher-batch.sh start"
    exit 1
fi

# Pour JAVA5 : /export/logiciels/java/jvm15
export JAVA_HOME=/export/logiciels/java/jvm16/jre/bin
cd $BATCH_PROJET

LIB=../lib

chmod u+r $LIB/*.jar


# Determination du caractere de separation du classpath
# (different sous Cygwin)
DEFAULT_CPS=:
CYGWIN_CPS=\;
case "`uname`" in
    CYGWIN*)  CPS=$CYGWIN_CPS;;
    *)        CPS=$DEFAULT_CPS;;
esac

JAVA_CLASSPATH=../conf$CPS

# Ajout des librairies
for i in $LIB/*; do
    JAVA_CLASSPATH=$JAVA_CLASSPATH$CPS$i
done

START_CMD="fr.gouv.agriculture.dal.sial.arq.agent.CalculerNoteRisqueAgentStart $JMX_REGISTRY_PORT"
STOP_CMD="fr.gouv.agriculture.dal.sial.arq.agent.CalculerNoteRisqueAgentStop $JMX_PORT $JMX_REGISTRY_PORT $JMX_ROLE $JMX_PWD"
STATUS_CMD="fr.gouv.agriculture.dal.sial.arq.agent.CalculerNoteRisqueAgentStatus $JMX_PORT $JMX_REGISTRY_PORT $JMX_ROLE $JMX_PWD"

MODE=$1; shift

case $MODE in
    "start")
        echo "Starting the Agent..."
        $JAVA_HOME/java $JAVA_OPTS -classpath $JAVA_CLASSPATH $START_CMD $*
        ;;
    "stop") 
        echo "Stopping the Agent..."
        $JAVA_HOME/java -classpath $JAVA_CLASSPATH $STOP_CMD
        ;;
    "status")
        $JAVA_HOME/java $JAVA_OPTS -classpath $JAVA_CLASSPATH $STATUS_CMD
        ;;
    "restart")
        echo "Restarting the Agent..."
        $JAVA_HOME/java -classpath $JAVA_CLASSPATH $STOP_CMD
        $JAVA_HOME/java $JAVA_OPTS -classpath $JAVA_CLASSPATH $START_CMD $*
        ;;
    *)
        echo "Unmanaged argument 'action': '$MODE'" >&2
        ;;
esac 
