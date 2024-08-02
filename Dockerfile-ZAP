FROM softwaresecurityproject/zap-bare

ENV ZAP_AUTO_UPDATE=false

EXPOSE 9090

CMD ["sh", "-c", "nohup zap.sh -daemon -port 9090 -config api.disablekey=true -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true -config view.mode=attack -host 0.0.0.0 -config network.localServers.mainProxy.behindNat=true -config network.connection.timeOutInSecs=120 -config network.httpState.timeoutInSecs=120 -config log.level=DEBUG & sleep 60 && curl 'http://localhost:9090/JSON/context/action/newContext/?contextName=ZAP_CONTEXT' && tail -f /dev/null"]