package fr.hoc.dap.server;

/**
 * Application 0conf.
 *
 * @author Michette & Thomas
 */
public class Configuration {
    /** Default application name. */
    private static final String APPLICATION_NAME = "Hoc DaP";
    /** Default path to Home. */
    private static final String HOME_PATH = System.getProperty("user.home");
    /** Default path tokens. */
    private static final String TOKENS_DIRECTORY_PATH = HOME_PATH + "/dap/tokens";
    /** Default path to the json file. */
    private static final String CREDENTIALS_FILE_PATH = HOME_PATH + "/dap/credentials_web.json";
    /** Default google oauth2 callback. */
    private static final String OAUTH2_CALLBACK_URL = "/oAuth2Callback";
    /** Default google call port. */
    private static final Integer PORT = 8888;

    /** applicationname. */
    private String applicationName;
    /** tokendirectorypath. */
    private String tokensDirectoryPath;
    /** credentialfilepath. */
    private String credentialFilePath;
    /** auth2CallbackURL. */
    private String oauth2CallbackURL;
    /** port. */
    private Integer port;

    /**
     * Configuration builder with defaults value.
     */
    protected Configuration() {
        applicationName = APPLICATION_NAME;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        credentialFilePath = CREDENTIALS_FILE_PATH;
        oauth2CallbackURL = OAUTH2_CALLBACK_URL;
        port = PORT;
    }

    /**
     * Get path to oAuth2 callback url.
     *
     * @return path to oAuth2 callback url
     */
    public String getoAuth2CallbackUrl() {
        return oauth2CallbackURL;
    }

    /**
     * Get application name in this configuration.
     *
     * @return application name in this configuration.
     */
    public String getApplicationName() {
        return applicationName;

    }

    /**
     * Get path to tokens files in this configuration.
     *
     * @return path to tokens files in this configuration.
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /**
     * Get path to credential files in this configuration.
     *
     * @return path to credential files in this configuration.
     */
    public String getCredentialsFilePath() {
        return credentialFilePath;
    }

    /**
     * Get port in this configuration.
     *
     * @return port in this configuration.
     */
    protected Integer getPort() {
        return port;
    }

    /**
     * Get home path.
     *
     * @return home path.
     */
    protected static String getHomePath() {
        return HOME_PATH;
    }

    /**
     * Set application name in this configuration .
     *
     * @param applicationname application name in this configuration .
     */
    protected void setApplicationName(final String applicationname) {
        this.applicationName = applicationname;
    }

    /**
     * Set path to tokens files in this configuration .
     *
     * @param tokensdirectorypath tokens path in this configuration .
     */
    protected void setTokensdirectorypath0(final String tokensdirectorypath) {
        this.tokensDirectoryPath = tokensdirectorypath;
    }

    /**
     * Set path to credential files in this configuration .
     *
     * @param credentialfilepath credential path in this configuration .
     */
    protected void setCredentialfilepath(final String credentialfilepath) {
        this.credentialFilePath = credentialfilepath;
    }
}
