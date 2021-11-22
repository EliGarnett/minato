package priv.eli.minato.model.fanshe;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.Param;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        try {
            ParamSetting paramSetting = new ParamSetting();
            paramSetting.setId(1);
            paramSetting.setName("paramSetting name");
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            paramSetting.setStrList(list);

            ParamSettingDTO paramSettingDTO = new ParamSettingDTO();

            Field[] fields = paramSettingDTO.getClass().getDeclaredFields();

            for (Field field : fields) {

                PropertyDescriptor paramSettingDtoPd = new PropertyDescriptor(field.getName(), paramSettingDTO.getClass());
                // 获取set方法
                Method setMethod = paramSettingDtoPd.getWriteMethod();

                PropertyDescriptor paramSettingPd = new PropertyDescriptor(field.getName(), paramSetting.getClass());
                // 获取get方法
                Method getMethod = paramSettingPd.getReadMethod();

                if ("id".equals(field.getName())) {
                    Integer getValue = (Integer) getMethod.invoke(paramSetting, new Object[] {});
                    setMethod.invoke(paramSettingDTO, getValue);
                } else {
                    Object invokeValue = getMethod.invoke(paramSetting, new Object[] {});
                    String getValue = null == invokeValue ? "" : String.valueOf(invokeValue);
                    setMethod.invoke(paramSettingDTO, getValue);
                }

            }
            System.out.println(paramSettingDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
