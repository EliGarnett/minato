package priv.eli.minato.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.eli.minato.MinatoResult;
import sun.misc.Cache;

@RestController
@RequestMapping("/cache/demo")
public class CacheDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "get")
    public MinatoResult getModel(@RequestParam(value = "key") String key) {
        CacheModel model = (CacheModel) redisTemplate.opsForValue().get(key);
        return MinatoResult.success(model);
    }

    @GetMapping(value = "set")
    public MinatoResult setModel(@RequestParam(value = "key") String key) {
        CacheModel model = CacheModel.builder().id(1).name("zhangsan").build();
        CacheModel model2 = CacheModel.builder().id(2).name("lisi").next(model).build();
        redisTemplate.opsForValue().set(key, model2);
        return MinatoResult.success();
    }
}
