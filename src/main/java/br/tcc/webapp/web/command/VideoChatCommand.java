package br.tcc.webapp.web.command;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 13/10/13
 * Time: 01:59
 * To change this template use File | Settings | File Templates.
 */
public class VideoChatCommand {
// ------------------------------ FIELDS ------------------------------

    private int apiKey;
    private String sessionKey;
    private String userToken;

// --------------------- GETTER / SETTER METHODS ---------------------


    public int getApiKey() {
        return apiKey;
    }

    public void setApiKey(int apiKey) {
        this.apiKey = apiKey;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
