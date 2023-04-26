# example-project
This project was developed as a service application wrapped around the [Cat Facts API](https://catfact.ninja/) api running on Kubernetes.

## 1 Prerequisites
- A Kubernetes cluster (`kubectl` should be installed)
- Helm (You should be able to use `helm`)

## 2 How to
1. Deploy mysql in k8s
   > Usage: ./kubernetes/mysql-init.sh {**NodePort** to expose} ({**namespace**} = default:example)
   ```
   ./kubernetes/mysql-init.sh 31001 example
   ```
1. Install the helm charts
   > Usage: helm -n {**namespace**} install {chart **name**} {chart **path**}
   ```
   helm -n example install example-db ./helm-chart/example-db/
   helm -n example install example-api ./helm-chart/example-api/
   ```
1. At last!
   ```
   plantrue@merrygoround ~ % curl -H 'Host: api.example.plantrue.local' api.example.plantrue.local/v1/catfact | jq
     % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                    Dload  Upload   Total   Spent    Left  Speed
   100   183  100   183    0     0    110      0  0:00:01  0:00:01 --:--:--   111
   {
     "ver": "0.0.1-SNAPSHOT",
     "time": "2023-04-27T02:16:26.998413251",
     "host": "example-api-5ddf894754-v4vk8 (10.244.3.22)",
     "msg": "A cat can jump up to five times its own height in a single bound."
   }
   plantrue@merrygoround ~ %
   ```