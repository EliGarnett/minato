package priv.eli.minato.model.fanshe;

import lombok.Data;

import java.util.List;

@Data
public class ParamSettingDTO {

    private Integer id;

    private String name;

    private List<String> strList;

    public ParamSettingDTO() {
    }

    public ParamSettingDTO(Integer id, String name, List<String> strList) {
        this.id = id;
        this.name = name;
        this.strList = strList;
    }
}
