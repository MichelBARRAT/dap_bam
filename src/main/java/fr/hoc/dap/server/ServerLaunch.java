package fr.hoc.dap.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/** Boot Application server.
 * @author Michette & Thomas
 */
@SpringBootApplication
public class ServerLaunch {
    /** Logs.*/
    private static final Logger LOG = LogManager.getLogger("HelloWorld");

    /** Starting point.
     * @param args put parameters.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ServerLaunch.class, args);
    }

    /** Launch Configuration instance(you can set).
     * @return Configuration instance.
     */
    @Bean
    public Configuration config() {
        Configuration config = new Configuration();
        //        config.setApplicationName("BoB");
        return config;
    }

    /** Launch server client.
     * @param ctx put application context.
     * @return server client.
     */
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {

            LOG.info("Nom de l'appli : " + config().getApplicationName());
            LOG.info("Credentials : " + config().getCredentialsFilePath());
            LOG.info("Tokens : " + config().getTokensDirectoryPath());

        };
    }

}
