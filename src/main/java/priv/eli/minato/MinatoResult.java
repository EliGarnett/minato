package priv.eli.minato;

import lombok.Data;

@Data
public class MinatoResult<T> {

    public static final Integer SUCC_CODE = 0;

    public static final Integer FAIL_CODE = -1;

    public static final String SUCC_MSG = "success";

    public static final String FAIL_MSG = "fail";

    private T data;

    private Integer code;

    private String msg;

    public static MinatoResult success(Object data) {
        MinatoResult minatoResult = new MinatoResult();
        minatoResult.setCode(SUCC_CODE);
        minatoResult.setMsg(SUCC_MSG);
        minatoResult.setData(data);
        return minatoResult;
    }

    public static MinatoResult success() {
        MinatoResult minatoResult = new MinatoResult();
        minatoResult.setCode(SUCC_CODE);
        minatoResult.setMsg(SUCC_MSG);
        return minatoResult;
    }

    public static MinatoResult fail(Object data) {
        MinatoResult minatoResult = new MinatoResult();
        minatoResult.setCode(FAIL_CODE);
        minatoResult.setMsg(FAIL_MSG);
        return minatoResult;
    }

}
