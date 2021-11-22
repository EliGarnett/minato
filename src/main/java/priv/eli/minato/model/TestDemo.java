package priv.eli.minato.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestDemo {

    public static void main(String[] args) {

        List<Demo> demoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            demoList.add(Demo.builder().id(Long.valueOf(i)).name(i + "—" + i).build());

        }

        Map<String, Demo> demoMap = demoList.stream().collect(Collectors.toMap(demo -> String.valueOf(demo.getId()), Function.identity()));



        System.out.println(1);
    }

}
