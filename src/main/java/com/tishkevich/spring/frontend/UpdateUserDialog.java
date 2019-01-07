package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.UserRepository;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class UpdateUserDialog extends Window {

    public UpdateUserDialog(final UserAccount userAccount, UserRepository userRepository) {
        super("Update user account"); // Set window caption
        center();
        setModal(true);
        VerticalLayout layout = new VerticalLayout();
        TextField username = new TextField("Username: ", userAccount.getUsername());
        TextField password = new TextField("Password: ");
        layout.addComponents(username, password, new Button("Submit", event -> {
            userAccount.setUsername(username.getValue().trim());
            if (password.getValue().trim().length() != 0) {
                System.out.println("password: " + password.getValue().trim());
                userAccount.setPassword(password.getValue());
            }
            userRepository.save(userAccount);
            close();
        }), new Button("Cancel", event -> close()));
        setContent(layout);
    }
}
