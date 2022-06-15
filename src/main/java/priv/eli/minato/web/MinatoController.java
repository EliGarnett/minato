package priv.eli.minato.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eli.minato.entity.ApiResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author Eli
 * @Date 2022/6/15
 */
@RestController
@RequestMapping("minato")
@Slf4j
public class MinatoController {

    private static final String URL = "https://pic1.zhimg.com/v2-8aa24df2133aecc5ab17d87210d9decc_r.jpg";


    @GetMapping("/download")
    public ApiResponse testDownload(HttpServletResponse response) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            String zipPackageName = "test.zip";
            zipPackageName = URLEncoder.encode(zipPackageName, StandardCharsets.UTF_8.name());
            // 指明response的返回对象是文件流
            response.setContentType("application/octet-stream");
            // 设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + zipPackageName);
            zipScreenShot(zipOutputStream);
            zipOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.success();

    }


    /**
     * 制作压缩包
     *
     * @param zipOutputStream 压缩包流
     */
    private void zipScreenShot(ZipOutputStream zipOutputStream) throws Exception {
        String picName = "test.jpg";
        ZipEntry zipEntry = new ZipEntry(picName);
        InputStream screenShotInputSteam = getFileInputSource(URL);
        if (Objects.nonNull(screenShotInputSteam)) {
            zipOutputStream.putNextEntry(zipEntry);
            // 从输入流读取一定数量的字节，存到缓存区中
            IOUtils.copy(screenShotInputSteam, zipOutputStream);
            screenShotInputSteam.close();
        }
    }

    private InputStream getFileInputSource(String originUrl) {
        log.info("通过公网获取文件流资源,url:{}", originUrl);
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(originUrl).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //  如果图片请求返回http code为302，尝试重定向5次
            for (int i = 0; i < 5 && conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP; i++) {
                conn.setRequestMethod("GET");
                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
                String location = conn.getHeaderField("Location");
                String cookies = conn.getHeaderField("Set-Cookie");
                URL serverUrl = new URL(location);
                conn = (HttpURLConnection) serverUrl.openConnection();
                conn.setRequestProperty("Cookie", cookies);
                conn.connect();
            }
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.error("公网多次重定向公网获取文件流资源失败，url : {}", originUrl);
            }
            return conn.getInputStream();
        } catch (IOException e) {
            log.error("公网多次重定向公网获取文件流资源失败，url : {}", originUrl, e);
            return null;
        }
    }

}
