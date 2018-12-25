package com.tishkevich.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("user")
public class UserView extends VerticalLayout {

    public UserView(@Autowired MessageBean bean) {
        Button button = new Button("User, click me",
                e -> Notification.show(bean.getMessage()));
        add(button);
    }

}
