#!/bin/sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NODEPORT_PORT (NAMESPACE)" >&2
  exit 1
elif [ "$#" -lt 2 ]; then
  NP=$1
  NS=example
else
  NP=$1
  NS=$2
fi

WORK_DIR="$(cd "$(dirname "$0")"; pwd -P)"
echo "> namespace: $NS" >&2
kubectl create namespace $NS
kubectl -n $NS create configmap mysql-init --from-file="$WORK_DIR/mysql-init.sql"
helm install -n $NS example-mysql \
--set auth.rootPassword=hello,initdbScriptsConfigMap=mysql-init,primary.service.type=NodePort,primary.service.nodePorts.mysql=$NP \
bitnami/mysql
