package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.QuestionDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("user")
public class UserView extends VerticalLayout {


    public UserView(@Autowired MessageBean bean) {
        Tab[] tabList = new Tab[10];
        Div[] pageList = new Div[10];
        VerticalLayout[] layout = new VerticalLayout[10];

        Paragraph[] badge = new Paragraph[10];
        RadioButtonGroup<String>[] group = new RadioButtonGroup[10];
        Map<Tab, Component> tabsToPages = new HashMap<>();
        List<QuestionDto> currentList = bean.getRandomQuestions();
        if (currentList != null) {
            tabList[0] = new Tab("Question 1");
            pageList[0] = new Div();
            badge[0] = new Paragraph(currentList.get(0).getQuestionText());
            badge[0].getStyle().set("fontSize", "100%");

            group[0] = new RadioButtonGroup<>();
            group[0].setItems("1. " + currentList.get(0).getAnswerList()[0], "2. " + currentList.get(0).getAnswerList()[1], "3. " + currentList.get(0).getAnswerList()[2], "4. " + currentList.get(0).getAnswerList()[3]);
            layout[0] = new VerticalLayout(badge[0], group[0]);
            layout[0].getStyle().set("alignItems", "center");
            pageList[0].add(layout[0]);
            tabsToPages.put(tabList[0], pageList[0]);
            for (int i = 1; i <= 9; i++) {
                tabList[i] = new Tab("Question " + (i + 1));
                pageList[i] = new Div();
                badge[i] = new Paragraph(currentList.get(i).getQuestionText());
                badge[i].getStyle().set("fontSize", "100%");
                pageList[i].setVisible(false);
                group[i] = new RadioButtonGroup<>();
                group[i].setItems("1. " + currentList.get(i).getAnswerList()[0], "2. " + currentList.get(i).getAnswerList()[1], "3. " + currentList.get(i).getAnswerList()[2], "4. " + currentList.get(i).getAnswerList()[3]);
                layout[i] = new VerticalLayout(badge[i], group[i]);
                layout[i].getStyle().set("alignItems", "center");
                pageList[i].add(layout[i]);
                tabsToPages.put(tabList[i], pageList[i]);
            }


        }
        Tabs tabs = new Tabs(tabList[0], tabList[1], tabList[2], tabList[3], tabList[4], tabList[5], tabList[6], tabList[7], tabList[8], tabList[9]);

        Div pages = new Div(pageList[0], pageList[1], pageList[2], pageList[3], pageList[4], pageList[5], pageList[6], pageList[7], pageList[8], pageList[9]);
        Set<Component> pagesShown = Stream.of(pageList[0])
                .collect(Collectors.toSet());
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(tpage -> tpage.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        tabs.setFlexGrowForEnclosedTabs(1);
        add(tabs);
        add(pages);
        Button button = new Button("User, click me",
                e -> Notification.show(bean.getMessage()));
        //add(button);
    }

}
