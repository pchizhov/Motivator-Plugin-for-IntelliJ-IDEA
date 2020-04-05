package main.java;

import java.io.*;
import java.time.LocalDate;

public class DateGenerator {

    public Integer getDates() throws IOException {

        String filePath = "/Users/pavelchizhov/Documents/java/plugin/IntelliJ-IDEA-Plugin-master/src/main/resources/dates.txt";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String previousDate = reader.readLine();
        line = reader.readLine();
        int days = Integer.parseInt(line);

        LocalDate lastVisit = LocalDate.parse(previousDate);
        LocalDate thisVisit = LocalDate.now();
        if (thisVisit.minusDays(1).isEqual(lastVisit)) {
            ++days;
        } else if (thisVisit.minusDays(1).isAfter(lastVisit)) {
            days = 1;
        }

        File f = new File(filePath);
        f.delete();
        f.createNewFile();
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(thisVisit.toString() + "\n");
        writer.write(new Integer(days).toString());
        writer.close();

        return days;
    }
}
