package priv.eli.minato.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getBean("xxx");

//        ReentrantLock lock = new ReentrantLock()

    }

}
