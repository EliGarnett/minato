package priv.eli.minato.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Eli
 * @Date 2022/6/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Integer code;

    private T body;


    public static ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setCode(200);
        return response;
    }

    public static <T> ApiResponse<T> success(T body) {
        ApiResponse response = new ApiResponse();
        response.setCode(200);
        response.setBody(body);
        return response;
    }
}
