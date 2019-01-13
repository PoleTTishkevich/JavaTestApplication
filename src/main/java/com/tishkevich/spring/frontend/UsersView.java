package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.UserRepository;
import com.tishkevich.spring.service.UserDetailsServiceImpl;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "users";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    void init() {
        DataProvider<UserAccount, String> dataProvider = new CallbackDataProvider<>(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    long offset = query.getOffset();
                    Optional<String> filterFrase = query.getFilter();
                    // The number of items to load
                    int limit = query.getLimit();
                    List<UserAccount> persons = new ArrayList<>();
                    if (filterFrase.isPresent())
                        persons = userDetailsService.findAllWithLimit(offset, limit, filterFrase.get());
                    else {
                        persons = userRepository.findAllWithLimit(offset, limit);
                    }
                    return persons.stream();
                },
                // Second callback fetches the number of items for a query
                query -> {
                    if (query.getFilter().isPresent())
                        return (int) userDetailsService.countWithFilter(query.getFilter().get());
                    else return (int) userRepository.count();
                }
        );
        ConfigurableFilterDataProvider<UserAccount, Void, String> wrapper =
                dataProvider.withConfigurableFilter();

        TextField searchField = new TextField();
        searchField.setPlaceholder("filter by login");
        searchField.addValueChangeListener(event -> {
            String filter = event.getValue();
            if (filter.trim().isEmpty()) {
                // null disables filtering
                filter = null;
            }
            wrapper.setFilter(filter);
            wrapper.refreshAll();
        });
        Grid<UserAccount> grid = new Grid<>();
        grid.setDataProvider(wrapper);
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
                    //tmpAcc = userRepository.save(tmpAcc);
                    UpdateUserDialog updateUserDialog = new UpdateUserDialog("Create new user account", tmpAcc, userRepository);
                    updateUserDialog.setHeight("400px");
                    updateUserDialog.setWidth("300px");

                    // Set window position.
                    updateUserDialog.setPositionX(200);
                    updateUserDialog.setPositionY(50);
                    updateUserDialog.addCloseListener(closeEvent -> {
                        wrapper.refreshAll();
                    });
                    UI.getCurrent().addWindow(updateUserDialog);
                    wrapper.refreshAll();
                });

        Button modifyPersonButton = new Button("Modify user account",
                clickEvent -> {
                    final UserAccount personToChange = grid.getSelectionModel().getFirstSelectedItem().get();
                    UpdateUserDialog updateUserDialog = new UpdateUserDialog("Update user account", personToChange, userRepository);
                    updateUserDialog.setHeight("400px");
                    updateUserDialog.setWidth("300px");

                    // Set window position.
                    updateUserDialog.setPositionX(200);
                    updateUserDialog.setPositionY(50);
                    updateUserDialog.addCloseListener(closeEvent -> {
                        wrapper.refreshAll();
                    });
                    UI.getCurrent().addWindow(updateUserDialog);
                    grid.getSelectionModel().deselectAll();
                    wrapper.refreshAll();
                });

        Button deletePersonButton = new Button("Delete user account",
                clickEvent -> {
                    UserAccount tmpAcc = grid.getSelectionModel().getFirstSelectedItem().get();
                    userRepository.delete(tmpAcc);

                    wrapper.refreshAll();
                });

        buttonLayout.addComponents(addPersonButton, modifyPersonButton, deletePersonButton, searchField);
        addComponent(buttonLayout);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}