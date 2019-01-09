package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;

public class UpdateUserDialog extends Window {

    public UpdateUserDialog(String title, final UserAccount userAccount, UserRepository userRepository) {
        super(title); // Set window caption
        center();
        setModal(true);
        Binder<UserAccount> binder = new Binder<>();

        VerticalLayout layout = new VerticalLayout();
        TextField username = new TextField("Username: ");
        TextField password = new TextField("Password: ");
        // Shorthand for cases without extra configuration

        binder.bind(username, UserAccount::getUsername, UserAccount::setUsername);
        binder.bind(password, UserAccount::getPassword, UserAccount::setPassword);
        binder.readBean(userAccount);
        layout.addComponents(username, password, new Button("Submit", event -> {
            try {
                binder.writeBean(userAccount);
                userRepository.save(userAccount);
            } catch (ValidationException e) {
                Notification.show("Person could not be saved, " +
                        "please check error messages for each field.");
            }
            close();
        }), new Button("Cancel", event -> {
            binder.readBean(userAccount);
            close();
        }));
        setContent(layout);
    }
}
