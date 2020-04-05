package main.java;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Motivator implements ProjectComponent {
    private final Project myProject;

    public Motivator(final Project project) {
        myProject = project;
    }

    @Override
    public void projectOpened() {
        DateGenerator dateGen = new DateGenerator();
        Integer streak = 1;
        try {
            streak = dateGen.getDates();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> quotes = new ArrayList<>();
        try {
            Parser p = new Parser();
            quotes = p.getQuotes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Notification notification = new Notification("Motivator", "Hello There!",
                "", NotificationType.INFORMATION);
        notification.setSubtitle("Your current streak is " + streak + "\uD83D\uDD25" + "days!");
        notification.setContent(quotes.get(new Random().nextInt(quotes.size())).split(" ", 2)[1]);
        notification.notify(myProject);
    }

}
