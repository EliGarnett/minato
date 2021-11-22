package priv.eli.minato.demo.sort;

import com.alibaba.fastjson.JSON;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Eli
 * @date 2020/8/26
 */
public class Demo {

    public static void main(String[] args) {

        System.out.println("1".equals(null));



        List<Entity> demoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Entity e = new Entity();
            if (i % 3 == 2) {
                e.setType(2);
            } else if (i % 3 == 1) {
                e.setType(1);
            }
            e.setCreateTime(new Date());
            try {
                Thread.sleep(300);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            demoList.add(e);
        }
        System.out.println(JSON.toJSONString(demoList));
        demoList.sort((entity1, entity2) -> sort(entity1, entity2));
        System.out.println(JSON.toJSONString(demoList));
    }

    static int sort(Entity entity1, Entity entity2) {
        int typeDiff = 0;
        if (entity1.getType() == null) {
            typeDiff = 1;
        }
        if (entity2.getType() == null) {
            typeDiff = -1;
        }
        if (entity1.getType() != null && entity2.getType() != null) {
            typeDiff = entity1.getType().compareTo(entity2.getType());
        }


        int timeDiff = 0;
        if (entity1.getCreateTime() == null) {
            timeDiff = 1;
        }
        if (entity2.getCreateTime() == null) {
            timeDiff = -1;
        }
        if (entity1.getCreateTime() != null && entity2.getCreateTime() != null) {
            timeDiff = entity1.getCreateTime().compareTo(entity2.getCreateTime());
        }
        timeDiff = timeDiff * -1;
        return typeDiff != 0 ? typeDiff : timeDiff;

    }

}
