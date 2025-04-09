package util.environment;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface EnvironmentConf extends Config {

    String url();

    String username();

    String password();

    String wrong_password();

}
