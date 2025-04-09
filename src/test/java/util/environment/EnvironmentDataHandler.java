package util.environment;

import org.aeonbits.owner.ConfigFactory;

public class EnvironmentDataHandler {

    private static EnvironmentConf instance = null;

    private EnvironmentDataHandler(){}

    public static EnvironmentConf get(){
        if(instance == null){
            instance = ConfigFactory.create(EnvironmentConf.class);
        }
        return instance;
    }
}
