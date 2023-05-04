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
helm install -n $NS example-kafka \
--set replicaCount=1,deleteTopicEnable=true \
--set externalAccess.enabled=true,externalAccess.service.type=NodePort,externalAccess.service.domain='localhost' \
--set externalAccess.service.nodePorts={$NP} \
bitnami/kafka