package fr.hoc.dap.server;

/** Application 0conf.
 * @author Michette & Thomas
 */
public class Configuration {
    /** Application name. */
    private static final String APPLICATION_NAME = "Hoc DaP";
    /** Path to Home file. */
    private static final String HOME_PATH = System.getProperty("user.home");
    /** For use authentication permission. */
    private static final String TOKENS_DIRECTORY_PATH = HOME_PATH + "/dap/tokens";
    /** Path to the json file. */
    private static final String CREDENTIALS_FILE_PATH = HOME_PATH + "/dap/credentials_web.json";
    /** Port call google. */
    private static final Integer PORT = 8888;
    /** Initialize applicationname. */
    private String applicationName;
    /** Initialize tokendirectorypath. */
    private String tokensDirectoryPath;

    /** Initialize credentialfilepath. */
    private String credentialFilePath;
    /** Initialize port. */
    private Integer port;

    /** Configuration builder with defaults value.
     */
    public Configuration() {
        applicationName = APPLICATION_NAME;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        credentialFilePath = CREDENTIALS_FILE_PATH;
        port = PORT;
    }

    /** Get path to oAuth2 callback url.
     * @return path to oAuth2 callback url
     */
    public String getoAuth2CallbackUrl() {
        return "/oAuth2Callback";
    }

    /** Get application name in this configuration.
     * @return application name in this configuration.
     */
    public String getApplicationName() {
        return applicationName;

    }

    /** Get path to tokens files in this configuration.
     * @return path to tokens files in this configuration.
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /** Get path to credential files in this configuration.
     * @return path to credential files in this configuration.
     */
    public String getCredentialsFilePath() {
        return credentialFilePath;
    }

    /** Get port in this configuration.
     * @return port in this configuration.
     */
    public Integer getPort() {
        return port;
    }

    /** Get home path.
     *  @return home path.
     */
    public static String getHomePath() {
        return HOME_PATH;
    }

    /** Set application name in this configuration .
     * @param applicationname application name in this configuration .
     */
    public void setApplicationName(final String applicationname) {
        this.applicationName = applicationname;
    }

    /** Set path to tokens files in this configuration .
     * @param tokensdirectorypath tokens path in this configuration .
     */
    public void setTokensdirectorypath0(final String tokensdirectorypath) {
        this.tokensDirectoryPath = tokensdirectorypath;
    }

    /** Set path to credential files in this configuration .
     * @param credentialfilepath credential path in this configuration .
     */
    public void setCredentialfilepath(final String credentialfilepath) {
        this.credentialFilePath = credentialfilepath;
    }

}
