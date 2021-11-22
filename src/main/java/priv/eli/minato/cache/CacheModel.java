package priv.eli.minato.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheModel {

    private Integer id;

    private String name;

    private CacheModel next;

}
