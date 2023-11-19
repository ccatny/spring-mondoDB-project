PID=$(ps -ef |grep springboot-mongo-demo.jar |grep -v grep |awk '{ print $2 }')
echo PID $PID
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
    echo stop springboot-mongo-demo
fi

nohup java -jar springboot-mongo-demo.jar   > nohup.out 2>&1 &

echo start springboot-mongo-demo...
