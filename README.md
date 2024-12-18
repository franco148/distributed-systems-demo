# distributed-systems-demo
Microservices demo taking into account technologies like: AWS, Spring ecosystem, Kubernetes, Kafka, and others

## DEV Notes
###### Package the project with a profile (maven):
Command: `mvn clean package -P <maven-profile>`

Example: `mvn clean package -P build-docker-image`

###### Docker commands
- [How to view the contents of docker images?](https://betterstack.com/community/questions/how-to-view-contents-of-docker-images/)
- See docker container content: `docker exec <docker-container-name> cat /app/resources/application-docker.yml`
- See docker images: `docker images`
- See docker container logs: `docker logs -f <container-id/docker-container-name>`
- Remove images: `docker rmi <image-id>`

## Kubernetes
- Check logs: `docker logs <container-name>`

## Kubernetes
##### Requited tools:
- https://kubernetes.io/docs/tasks/tools/

##### Requited commands:
- Start a container with kubectl: `kubectl run <some-name> --image=<docker-image> --port=<app-port>`
- Retrieve existing pods: `kubectl get pods`, `kubectl get pods -w`, `kubectl get nodes -v=10`
- Verify pod: `kubectl describe pod <podname>`
- Access the application (v1): `kubectl port-forward pod/<pod-name> <host-port>:<pod-port>`
- Delete/Stop pod: `kubectl delete pod <pod-name>`, `kubectl delete pod <pod-name> --force`
- Start all ymls: `kubectl apply -f boostrap/postgress`. Need to be located in the folder.
- Check logs: `kubectl logs <pod-name>`, `kubectl logs <pod-name> -f`
- SSH a pod: `minikube ssh`
- In case we need to connect to a postgres pod: `kubectl exec -it <pod-name> -- psql -U <username> -d <databasename>`. Then we can create more databases.
- For enabling pod url instead of the load balancer: `minikube tunnel`
- Retrieve services: `kubectl get svc`
- Update replicas: `kubectl scale --replicas=0 deployment customer`
- Access to LoadBalancer: `minikube tunnel`
- Stop minikube: `minikube stop`
- Delete minikube: `sudo minikube delete`
- Start minikube: `minikube start`

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


