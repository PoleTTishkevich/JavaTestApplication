package com.tishkevich.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

@Route("main")
public class MainView extends VerticalLayout {

    public MainView() {

        Button button = new Button("Click me",
                e -> Notification.show("Clicked"));
        add(button);
    }

}
