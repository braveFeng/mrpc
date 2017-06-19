#! /bin/bash

mvn clean install -N

cd mrpc-common && mvn clean install -Dmaven.test.skip=true -U
cd ..

cd mrpc-core && mvn clean install -Dmaven.test.skip=true -U
cd ..

cd mrpc-registry-zk && mvn clean install -Dmaven.test.skip=true -U
cd ..

cd mrpc-metric-influxdb && mvn clean install -Dmaven.test.skip=true -U
cd ..

cd mrpc-serialize && mvn clean install -P snapshots -N

cd mrpc-serialize-kryo && mvn clean install -Dmaven.test.skip=true -U
cd ..

cd mrpc-serialize-protostuff && mvn clean install -Dmaven.test.skip=true -U
cd ../../

cd mrpc-spring-boot-starter && mvn clean install -Dmaven.test.skip=true -U
cd ..