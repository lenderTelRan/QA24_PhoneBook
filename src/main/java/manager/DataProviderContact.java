package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{Contact.builder()
                .name("Pit444")
                .lastName("Parker")
                .phone("0556355353")
                .email("parker442@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Pit54")
                .lastName("Parker")
                .phone("0556352223")
                .email("parker54@gmail.com")
                .address("Haifa, Israel")
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Pit444")
                .lastName("Parker")
                .phone("05563")
                .email("parker442@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Pit444")
                .lastName("Parker")
                .phone("05563565654488955")
                .email("parker442@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Pit444")
                .lastName("Parker")
                .phone("fffdddrrrwww")
                .email("parker442@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Pit444")
                .lastName("Parker")
                .phone("")
                .email("parker442@gmail.com")
                .address("Haifa, Israel")
                .description("coworker")
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contactData.csv"));
        String line = reader.readLine();

        while(line != null) {
            String[] dataList = line.split(",");

            list.add(new Object[]{Contact.builder()
                    .name(dataList[0])
                    .lastName(dataList[1])
                    .email(dataList[2])
                    .phone(dataList[3])
                    .address(dataList[4])
                    .description(dataList[5])
                    .build()
            });
            line = reader.readLine();
        }

        return list.iterator();
    }
}
