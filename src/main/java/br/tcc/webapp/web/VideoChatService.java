package br.tcc.webapp.web;

import br.tcc.webapp.model.Project;
import com.opentok.api.OpenTokSDK;
import com.opentok.api.constants.RoleConstants;
import com.opentok.api.constants.SessionProperties;
import com.opentok.exception.OpenTokException;
import org.appfuse.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 13/10/13
 * Time: 01:49
 * To change this template use File | Settings | File Templates.
 */

@Named
@Singleton
@Lazy(value = false)
public class VideoChatService {

    protected final transient Logger log = LoggerFactory.getLogger(getClass());
    /**
     * A list of video sessions per CustomRoot
     */
    private static final ConcurrentMap<Long, Map<String, String>> openTokSessions = new ConcurrentHashMap<Long, Map<String, String>>();
    public static final String TOKBOX_API_SECRET = "7b6fc67e6c3fd69a29e7acdc894bc38f6e0002e5";
    public static final int TOKBOX_API_KEY = 21914812;

    public String getOrCreateVideoSession(String componentId, Project project) {
        String sessionId;
        Long key = project.getId();
        if (openTokSessions.containsKey(key)) {
            Map<String, String> sessions = openTokSessions.get(key);
            if (sessions.containsKey(componentId)) {
                sessionId = sessions.get(componentId);
                log.info(String.format("Found existing session id %s for componentId %s", sessionId, componentId));
                return sessionId;
            }
        }

        synchronized (openTokSessions) {
            log.debug("Started openTokSessions synchronized for customRoot " + project.getName());

            Map<String, String> sessions;
            if (!openTokSessions.containsKey(key)) {
                sessions = new HashMap<String, String>();
                openTokSessions.put(key, sessions);
            } else
                sessions = openTokSessions.get(key);

            OpenTokSDK sdk = this.getOpenTokSDKInstance();

            //Generate Session Properties for a session
            SessionProperties sp = new SessionProperties();
            sp.p2p_preference = "enabled";

            //Generate a session with a location hint and session properties
            try {
                sessionId = sdk.create_session(componentId, sp).getSessionId();
            } catch (OpenTokException e) {
                throw new RuntimeException(e);
            }
            log.info(String.format("Created new session id %s for componentId %s", sessionId, componentId));

            sessions.put(componentId, sessionId);
            log.debug("Ended openTokSessions synchronized for customRoot " + project.getName());
        }
        return sessionId;
    }

    public String createUserTokenForSession(String sessionKey, User user) {
        // Set the following constants with the API key and API secret
        // that you receive when you sign up to use the OpenTok API:
        OpenTokSDK sdk = getOpenTokSDKInstance();

        // Replace with meaningful metadata for the connection.
        String connectionMetadata = user.getFullName();

        // Generate a token. Use the RoleConstants value appropriate for the user.
        String token = null;
        try {
            token = sdk.generate_token(sessionKey, RoleConstants.PUBLISHER, null, connectionMetadata);
        } catch (OpenTokException e) {
            throw new RuntimeException(e);
        }
        log.info(String.format("Created new token for user... %s", token));
        return token;
    }

    private OpenTokSDK getOpenTokSDKInstance() {
        return new OpenTokSDK(TOKBOX_API_KEY, TOKBOX_API_SECRET);
    }

}
