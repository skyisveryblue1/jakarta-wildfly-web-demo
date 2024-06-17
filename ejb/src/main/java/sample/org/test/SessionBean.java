package sample.org.test;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
//import org.jboss.ejb3.annotation.SecurityDomain;
import jakarta.annotation.security.RolesAllowed;

@Stateless
//@RolesAllowed("user")
public class SessionBean {

    @Resource
    public SessionContext sessionContext;
    private HttpSession session;

    public void startTimer(HttpSession session, String key, String value) {
        this.session = session;
        System.out.println("Timer created. Info: " + key + "[" + value + "]");
        session.setAttribute(key, value);
        sessionContext.getTimerService().createTimer(60 * 1000, key);
    }

    // Timeout callback method
    @Timeout
    public void timeoutHandler(Timer timer) {
        // Handle timeout logic here
        Serializable info = (Serializable) timer.getInfo();
        if (session != null) {
            System.out.println("Timer expired. Info: " + info + "[" + session.getAttribute(info.toString()) + "]");
            session.removeAttribute(info.toString());
        }
        timer.cancel();
    }
}