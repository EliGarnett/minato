package priv.eli.minato.demo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eli
 * @date 2021/8/24
 */
public class MainApp {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        list.stream().filter(l -> l > 2);

        System.out.println(JSON.toJSONString(list));
    }

}
