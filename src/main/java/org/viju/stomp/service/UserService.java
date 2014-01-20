package org.viju.stomp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.viju.stomp.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService  implements ApplicationListener<BrokerAvailabilityEvent> {

    private final SimpMessageSendingOperations messagingTemplate;

    private AtomicBoolean brokerAvailable = new AtomicBoolean();

    UserGenerator userGenerator=new UserGenerator();

    @Autowired
    public UserService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedDelay = 4000)
    public void userList(){
        for(User user : userGenerator.generateUsers()){
            if (this.brokerAvailable.get()) {
                this.messagingTemplate.convertAndSendToUser("paulson","/queue/users-list", user);
            }
        }

    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        this.brokerAvailable.set(event.isBrokerAvailable());
    }

    private class UserGenerator {

        User user=new User();

        public List<User> generateUsers() {
            List<User> ret=new ArrayList<>();
            for (int p=0;p<10 ;p++) {
                User usr=new User();
                Random rand=new Random();
                int b=rand.nextInt(100*(p+1));
                usr.setEmail("tst_"+b+"@viju.ro");
                usr.setPassword("pass"+b);
                usr.setRole("role_"+b);
                usr.setUserName("user_"+b);
                ret.add(usr);
            }

            return ret;
        }
    }
}
