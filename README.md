# SparkApp

How to run the example.

1. Download spark-1.4.0-bin-hadoop2.6.tgz
2. Unzip it
3. Start ./sbin/start-master.sh
4. Start ./sbin/start-slave.sh spark://superthree:7077
5. Check page http://localhost:8080/
6. Under eclipse project, it needs the library spark-assembly-1.4.0-hadoop2.6.0.jar
7. jar cvf simpleapp.jar -C bin/ .
8. Run spark-submit --class SimpleApp --master  spark://superthree:7077  simpleapp.jar /media/gf/Storage/workspace/SparkApp/test > results
9. Check result file retults
