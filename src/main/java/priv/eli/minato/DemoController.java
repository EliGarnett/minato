package priv.eli.minato;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Eli
 * @date 2020/7/16
 */
@RestController
@RequestMapping(value = "/demo/controller")
public class DemoController {

    @GetMapping("/get")
    public void getDemo(@RequestParam(value = "flag", required = false, defaultValue = "true") boolean flag) {
        System.out.println(flag);

    }

}
