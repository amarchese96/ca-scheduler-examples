### Deploy examples

- Execute the <em>export</em> script in order to parse the TOSCA service template into JSON format and upload to Dgraph DB:
```
./export ${DGRAPH_IP} ${DGRAPH_PORT} /path/to/service_template /path/to/output_json
```
Example:
```
./export 10.31.127.231 32002 /home/angelo/scheduler-plugins-project/ca-scheduler-examples/examples/bookinfo/bookinfo.yaml /home/angelo/scheduler-plugins-project/ca-scheduler-examples/examples/bookinfo/tosca.json
```

- Execute the following command in order to parse the TOSCA service template into K8S format:
```
puccini-tosca compile /path/to/service_template --exec=https://raw.githubusercontent.com/tliron/turandot/main/assets/tosca/profiles/kubernetes/1.0/js/resources/get.js --output=/path/to/output_k8s
```

Example:
```
puccini-tosca compile /home/angelo/scheduler-plugins-project/ca-scheduler-examples/examples/bookinfo/bookinfo.yaml --exec=https://raw.githubusercontent.com/tliron/turandot/main/assets/tosca/profiles/kubernetes/1.0/js/resources/get.js --output=/home/angelo/scheduler-plugins-project/ca-scheduler-examples/examples/bookinfo/bookinfo-k8s.yaml
```