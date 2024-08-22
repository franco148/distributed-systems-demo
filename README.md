# distributed-systems-demo
Microservices demo taking into account technologies like: AWS, Spring ecosystem, Kubernetes, Kafka, and others

## DEV Notes
###### Package the project with a profile (maven):
Command: `mvn clean package -P <maven-profile>`

Example: `mvn clean package -P build-docker-image`


## Kubernetes
##### Requited tools:
- https://kubernetes.io/docs/tasks/tools/

##### Requited commands:
- Start a container with kubectl: `kubectl run <some-name> --image=<docker-image> --port=<app-port>`
- Retrieve existing pods: `kubectl get pods`
- Access the application (v1): `kubectl port-forward pod/<pod-name> <host-port>:<pod-port>`
- Delete pod: `kubectl delete pod <pod-name>`










