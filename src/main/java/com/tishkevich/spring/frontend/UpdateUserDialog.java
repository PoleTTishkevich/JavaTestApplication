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
        binder.readBean(userAccount);
        binder.bind(username, UserAccount::getUsername, UserAccount::setUsername);
        binder.bind(password, UserAccount::getPassword, UserAccount::setPassword);
        layout.addComponents(username, password, new Button("Submit", event -> {
            try {
                binder.writeBean(userAccount);
                // A real application would also save the updated person
                // using the application's backend
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
