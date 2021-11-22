package priv.eli.minato.model.fanshe;

import lombok.Data;

import java.util.List;

@Data
public class ParamSetting {

    private Integer id;

    private String name;

    private List<String> strList;
}
