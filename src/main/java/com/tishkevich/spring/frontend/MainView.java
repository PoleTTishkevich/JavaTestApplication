package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.QuestionDto;
import com.tishkevich.spring.service.CategoryService;
import com.tishkevich.spring.service.QuestionDboService;
import com.tishkevich.spring.service.UserDetailsServiceImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

//@Route("user")
@SpringUI
@Theme("valo")
@SpringViewDisplay
public class MainView extends UI implements ViewDisplay {

    private Panel springViewDisplay;

    @Autowired
    private QuestionDboService questionDboService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CategoryService categoryService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //fixme add method, that checks user's role
        UserDetails userDetails = userDetailsService.loadUserByUsername(vaadinRequest.getUserPrincipal().getName());
        boolean isAdmin = false;
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }

        System.out.println(isAdmin);
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);
        if (!isAdmin) {
            Label label = new Label("Hello, " + vaadinRequest.getUserPrincipal().getName() + ", choose the difficulty of questions");
            List<String> data = categoryService.findAll();

            RadioButtonGroup sample = new RadioButtonGroup<>("Select the difficulty", data);
            sample.setItemCaptionGenerator(item -> item.toString());
            sample.setSelectedItem(data.get(2));
            sample.setSizeFull();
            sample.setHeightUndefined();
            sample.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
            sample.addValueChangeListener(event -> Notification.show("Value changed:",
                    String.valueOf(event.getValue()),
                    Notification.Type.TRAY_NOTIFICATION));
            root.addComponent(label);
            root.addComponent(sample);
            root.setExpandRatio(label, 1.0f);
            root.setExpandRatio(sample, 1.0f);
            TabSheet tabsheet = new TabSheet();
            root.addComponent(tabsheet);
            root.setExpandRatio(tabsheet, 10.0f);
            List<QuestionDto> currentList = questionDboService.getRandomQuestions();
            for (int i = 0; i < 10; i++) {
                VerticalLayout tab1 = new VerticalLayout();
                String text = currentList.get(i).getQuestionText();
                int rowNums = text.split("\n").length;
                TextArea curentText = new TextArea("", text);
                curentText.setSizeFull();
                curentText.setRows(rowNums);
                curentText.setEnabled(false);
                tab1.addComponent(curentText);
                tabsheet.addTab(tab1, "question " + (i + 1));
            }
        } else {
            final CssLayout navigationBar = new CssLayout();
            navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
            navigationBar.addComponent(createNavigationButton("Questions",
                    QuestionsView.VIEW_NAME));
            navigationBar.addComponent(createNavigationButton("Users",
                    UsersView.VIEW_NAME));
            root.addComponent(navigationBar);

            springViewDisplay = new Panel();
            springViewDisplay.setSizeFull();
            root.addComponent(springViewDisplay);
            root.setExpandRatio(springViewDisplay, 1.0f);
        }

    }

    private Button createNavigationButton(String caption,
                                          final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(
                event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);

    }
}
