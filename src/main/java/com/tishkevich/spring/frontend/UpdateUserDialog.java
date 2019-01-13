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

        binder.forField(username).withValidator(
                name -> name.length() >= 3,
                "Username must contain at least three characters")
                .bind(UserAccount::getUsername, UserAccount::setUsername);
        binder.forField(password).withValidator(
                name -> name.length() >= 3,
                "Password must contain at least three characters")
                .bind(UserAccount::getPassword, UserAccount::setPassword);
        binder.readBean(userAccount);
        Button saveButton = new Button("Submit", event -> {
            if (binder.writeBeanIfValid(userAccount)) {
                userRepository.save(userAccount);
            } else {

                Notification.show("Validation error count: "
                        + binder.validate().getValidationErrors().size());
            }
            close();
        });
        Button resetButton = new Button("Cancel", event -> {
            binder.readBean(userAccount);
            close();
        });
        layout.addComponents(username, password, saveButton, resetButton);
        binder.addStatusChangeListener(event -> {
            boolean isValid = event.getBinder().isValid();
            boolean hasChanges = event.getBinder().hasChanges();

            saveButton.setEnabled(hasChanges && isValid);
            resetButton.setEnabled(hasChanges);
        });
        setContent(layout);
    }
}
