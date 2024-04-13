package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"test_telran@gmail.com", "Test@12345"});
        list.add(new Object[]{"mara@gmail.com", "Mmar123456$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModels() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder()
                .email("test_telran@gmail.com")
                .password("Test@12345")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
    //*** read from file
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/testData.csv"));
        String line = reader.readLine();

        while (line != null) {
           String[] dataList = line.split(",");

           list.add(new Object[]{User.builder()
                   .email(dataList[0])
                   .password(dataList[1])
                   .build()
           });
           line = reader.readLine();
        }
        return list.iterator();
    }
}
