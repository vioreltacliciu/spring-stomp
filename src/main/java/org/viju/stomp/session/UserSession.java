package org.viju.stomp.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.viju.stomp.domain.User;

import java.util.Date;

@Component
@Scope(value = "session" ,proxyMode = ScopedProxyMode.TARGET_CLASS)

public class UserSession {

    private User user;

    private Date date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
