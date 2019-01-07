package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.UserRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "view";

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void init() {
        addComponent(new Label("This is a view scoped view"));
        DataProvider<UserAccount, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    long offset = query.getOffset();

                    // The number of items to load
                    int limit = query.getLimit();

                    List<UserAccount> persons = userRepository.findAllWithLimit(offset, limit);

                    return persons.stream();
                },
                // Second callback fetches the number of items for a query
                query -> (int) userRepository.count()
        );

        Grid<UserAccount> grid = new Grid<>();
        grid.setDataProvider(dataProvider);
        grid.addColumn(UserAccount::getId).setCaption("Id");
        grid.addColumn(UserAccount::getUsername)
                .setCaption("Username").setComparator(
                Comparator.comparing(UserAccount::getUsername)::compare);
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button addPersonButton = new Button("Add user account",
                clickEvent -> {
                    UserAccount tmpAcc = new UserAccount();
                    tmpAcc.setUsername("New user");
                    tmpAcc.setPassword("new password");
                    tmpAcc = userRepository.save(tmpAcc);

                    dataProvider.refreshAll();
                });

        Button modifyPersonButton = new Button("Modify user account",
                clickEvent -> {
                    final UserAccount personToChange = grid.getSelectionModel().getFirstSelectedItem().get();
                    UpdateUserDialog updateUserDialog = new UpdateUserDialog(personToChange);
                    updateUserDialog.setHeight("400px");
                    updateUserDialog.setWidth("400px");

                    // Set window position.
                    updateUserDialog.setPositionX(200);
                    updateUserDialog.setPositionY(50);
                    UI.getCurrent().addWindow(updateUserDialog);
                    System.out.println(personToChange.getId());
                    System.out.println(personToChange.getUsername());
                    System.out.println(personToChange.getPassword());
                    userRepository.save(personToChange);
                    grid.getSelectionModel().deselectAll();
                    dataProvider.refreshAll();
                });

        Button deletePersonButton = new Button("Delete user account",
                clickEvent -> {
                    UserAccount tmpAcc = grid.getSelectionModel().getFirstSelectedItem().get();
                    userRepository.delete(tmpAcc);

                    dataProvider.refreshAll();
                });

        buttonLayout.addComponents(addPersonButton, modifyPersonButton, deletePersonButton);
        addComponent(buttonLayout);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}