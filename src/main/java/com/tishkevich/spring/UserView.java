package com.tishkevich.spring;

import com.tishkevich.spring.entities.ApiResponse;
import com.tishkevich.spring.entities.QuestionDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@Route("user")
public class UserView extends VerticalLayout {

    public UserView(@Autowired MessageBean bean) {
        Tabs tabs = new Tabs();
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/api/getRandomQuestions";
        QuestionDto[] result
                =restTemplate.getForEntity(fooResourceUrl, QuestionDto[].class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            currentResponse =(ApiResponse<QuestionDto>) response.getBody();
            System.out.println(currentResponse);
            currentList = currentResponse.getResult();
            System.out.println(currentList.toString());
        }
        if (currentList != null) {
            for (int i = 0; i <= 9; i++) {
                Tab tab = new Tab("Question " + (i + 1));
                Label label = new Label(currentList[i].getQuestionText());
                RadioButtonGroup<String> group = new RadioButtonGroup<>();
                group.setItems("1." + currentList[i].getAnswerList()[0], "2." + currentList[i].getAnswerList()[1], "3." + currentList[i].getAnswerList()[2], "4." + currentList[i].getAnswerList()[3]);
                tab.add(label);
                tab.add(group);
                tabs.add(tab);
            }
        }
         add(tabs);
        Button button = new Button("User, click me",
                e -> Notification.show(bean.getMessage()));
        //add(button);
    }

}
