# distributed-systems-demo
Microservices demo taking into account technologies like: AWS, Spring ecosystem, Kubernetes, Kafka, and others

## DEV Notes
###### Package the project with a profile (maven):
Command: `mvn clean package -P <maven-profile>`

Example: `mvn clean package -P build-docker-image`

###### See docker container content
- `docker exec <docker-container-name> cat /app/resources/application-docker.yml`

## Kubernetes
- Check logs: `docker logs <container-name>`

## Kubernetes
##### Requited tools:
- https://kubernetes.io/docs/tasks/tools/

##### Requited commands:
- Start a container with kubectl: `kubectl run <some-name> --image=<docker-image> --port=<app-port>`
- Retrieve existing pods: `kubectl get pods`, `kubectl get nodes -v=10`
- Access the application (v1): `kubectl port-forward pod/<pod-name> <host-port>:<pod-port>`
- Delete pod: `kubectl delete pod <pod-name>`
- Start all ymls: `kubectl apply -f boostrap/postgress`. Need to be located in the folder.
- Check logs: `kubectl logs <pod-name>`, `kubectl logs <pod-name> -f`
- SSH a pod: `minikube ssh`
- In case we need to connect to a postgres pod: `kubectl exec -it <pod-name> -- psql -U <username>`. Then we can create more databases.
- For enabling pod url instead of the load balancer: `minikube tunnel`
- Retrieve services: `kubectl get svc`
- Update replicas: `kubectl scale --replicas=0 deployment customer`
- Access to LoadBalancer: `minikube tunnel`

Use the following commands to access both RabbitMQ and Zipkin
- minikube service --url rabbitmq
- minikube service --url zipkin

IE: If I would need to run customer service
- `kubectl apply -f minikube/services/customer`


##### Commands Used

export KUBECONFIG=~/amigoscode-kubeconfig.yml

kubectl apply -f path
kubectl delete -f path

kubectl get po
kubectl get svc
kubectl get po -w

kubectl logs postgres-0

kubectl exec -it postgres-0 -- psql -U amigoscode

kubectl describe pod podName

kubectl logs podName
kubectl logs -f podName

kubectl exec -it postgres-0 -- psql -U linpostgres -h lin-4621-419-pgsql-primary.servers.linodedb.net
kubectl exec -it postgres-0 -- psql --help
kubectl exec -it postgres-0 -- psql -U linpostgres -h lin-4621-419-pgsql-primary.servers.linodedb.net
kubectl exec -it postgres-0 -- bash

psql -U host -U username -d database
\d
\dt
\c databaseName
create database dbName;
select * from tableName;

##### Intellij IDEA notes
- In Program arguments: `--server.port=<port>` when we want to run a service in a different port


##### Other notes
- https://stackoverflow.com/questions/29670116/remove-duplicates-from-a-list-of-objects-based-on-property-in-java-8


