package com.tishkevich.spring.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

// Pay attention to the order of annotations
@UIScope
@SpringView(name = QuestionsView.VIEW_NAME)
public class QuestionsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "questions";

    @PostConstruct
    void init() {
        addComponent(new Label("This is a UI scoped view. Greeter says:"));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

    @Override
    public void beforeLeave(ViewBeforeLeaveEvent event) {

    }
}