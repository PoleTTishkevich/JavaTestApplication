package com.tishkevich.spring.frontend;

import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.UserAccount;
import com.tishkevich.spring.service.CategoryService;
import com.tishkevich.spring.service.QuestionDboService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

// Pay attention to the order of annotations
@SpringView(name = QuestionsView.VIEW_NAME)
public class QuestionsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "questions";

    @Autowired
    private QuestionDboService questionDboService;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    void init() {
        DataProvider<QuestionDbo, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    long offset = query.getOffset();

                    // The number of items to load
                    int limit = query.getLimit();

                    List<QuestionDbo> questionDbos = questionDboService.findAllWithLimit(offset, limit);

                    return questionDbos.stream();
                },
                // Second callback fetches the number of items for a query
                query -> (int) questionDboService.count()
        );

        Grid<QuestionDbo> grid = new Grid<>();
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.addColumn(QuestionDbo::getId).setCaption("Id");
        grid.addColumn(QuestionDbo::getQuestionText)
                .setCaption("Text of question");
        grid.addColumn(QuestionDbo::getFirstAnswer)
                .setCaption("First answer");
        grid.addColumn(QuestionDbo::getSecondAnswer)
                .setCaption("Second answer");
        grid.addColumn(QuestionDbo::getThirdAnswer)
                .setCaption("Third answer");
        grid.addColumn(QuestionDbo::getForthAnswer)
                .setCaption("Forth answer");
        grid.addColumn(QuestionDbo::getCorrectAnswerNumber)
                .setCaption("Correct answer number");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button addQuestionButton = new Button("Add question",
                clickEvent -> {
                    QuestionDbo questionDbo = new QuestionDbo();
                    questionDbo.setQuestionText("New question");
                    questionDbo.setFirstAnswer("new answer");
                    questionDbo.setSecondAnswer("new answer");
                    questionDbo.setThirdAnswer("new answer");
                    questionDbo.setForthAnswer("new answer");
                    questionDbo.setCorrectAnswerNumber(0);
                    questionDbo.setCategory(categoryService.findByName("Elementary"));
                    //tmpAcc = userRepository.save(tmpAcc);
                    UpdateQuestionDialog updateUserDialog = new UpdateQuestionDialog("Create new question", questionDbo, questionDboService, categoryService);
                    updateUserDialog.setHeight("400px");
                    updateUserDialog.setWidth("400px");

                    // Set window position.
                    updateUserDialog.setPositionX(200);
                    updateUserDialog.setPositionY(50);
                    updateUserDialog.addCloseListener(closeEvent -> {
                        dataProvider.refreshAll();
                    });
                    UI.getCurrent().addWindow(updateUserDialog);
                    dataProvider.refreshAll();
                });

        Button modifyQuestionButton = new Button("Modify question",
                clickEvent -> {
                    final QuestionDbo questionDbo = grid.getSelectionModel().getFirstSelectedItem().get();
                    UpdateQuestionDialog updateUserDialog = new UpdateQuestionDialog("Modify question", questionDbo, questionDboService, categoryService);
                    updateUserDialog.setHeight("400px");
                    updateUserDialog.setWidth("400px");

                    // Set window position.
                    updateUserDialog.setPositionX(200);
                    updateUserDialog.setPositionY(50);
                    updateUserDialog.addCloseListener(closeEvent -> {
                        dataProvider.refreshAll();
                    });
                    UI.getCurrent().addWindow(updateUserDialog);
                    grid.getSelectionModel().deselectAll();
                    dataProvider.refreshAll();
                });

        Button deleteQuestionButton = new Button("Delete question",
                clickEvent -> {
                    QuestionDbo tmpQuestion = grid.getSelectionModel().getFirstSelectedItem().get();
                    questionDboService.delete(tmpQuestion.getId());
                    dataProvider.refreshAll();
                });

        buttonLayout.addComponents(addQuestionButton, modifyQuestionButton, deleteQuestionButton);
        addComponent(buttonLayout);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}