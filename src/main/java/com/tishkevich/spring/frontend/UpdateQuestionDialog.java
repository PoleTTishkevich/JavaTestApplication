package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.repositories.UserRepository;
import com.tishkevich.spring.service.CategoryService;
import com.tishkevich.spring.service.QuestionDboService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;

public class UpdateQuestionDialog extends Window {

    public UpdateQuestionDialog(String title, final QuestionDbo currentQuestion, QuestionDboService questionDboService, CategoryService categoryService) {
        super(title); // Set window caption
        center();
        setModal(true);
        Binder<QuestionDbo> binder = new Binder<>();

        VerticalLayout layout = new VerticalLayout();
        TextArea questionText = new TextArea("Question text: ");
        questionText.setSizeFull();
        TextArea firstAnswer = new TextArea("First answer: ");
        firstAnswer.setSizeFull();
        TextArea secondAnswer = new TextArea("Second answer: ");
        secondAnswer.setSizeFull();
        TextArea thirdAnswer = new TextArea("Third answer: ");
        thirdAnswer.setSizeFull();
        TextArea forthAnswer = new TextArea("Forth answer: ");
        forthAnswer.setSizeFull();
        TextField correctAnswerNumber = new TextField("Correct answer number: ");
        TextField categoryName = new TextField("Category name: ");
        // Shorthand for cases without extra configuration

        binder.bind(questionText, QuestionDbo::getQuestionText, QuestionDbo::setQuestionText);
        binder.bind(firstAnswer, QuestionDbo::getFirstAnswer, QuestionDbo::setFirstAnswer);
        binder.bind(secondAnswer, QuestionDbo::getSecondAnswer, QuestionDbo::setSecondAnswer);
        binder.bind(thirdAnswer, QuestionDbo::getThirdAnswer, QuestionDbo::setThirdAnswer);
        binder.bind(forthAnswer, QuestionDbo::getForthAnswer, QuestionDbo::setForthAnswer);
        binder.bind(correctAnswerNumber, (question -> question.getCorrectAnswerNumber() + ""), ((question, text) -> question.setCorrectAnswerNumber(Integer.valueOf(text))));
        binder.bind(categoryName, (question -> question.getCategory().getName()), ((question, text) -> question.setCategory(categoryService.findByName(text))));
        binder.readBean(currentQuestion);
        layout.addComponents(questionText, firstAnswer, secondAnswer, thirdAnswer, forthAnswer, correctAnswerNumber, categoryName, new Button("Submit", event -> {
            try {
                binder.writeBean(currentQuestion);
                questionDboService.save(currentQuestion);
            } catch (ValidationException e) {
                Notification.show("Question could not be saved, " +
                        "please check error messages for each field.");
            }
            close();
        }), new Button("Cancel", event -> {
            binder.readBean(currentQuestion);
            close();
        }));
        setContent(layout);
    }
}
