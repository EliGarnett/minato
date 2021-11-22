package priv.eli.minato.export;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import lombok.Cleanup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "/export")
public class ExportController {

    @GetMapping("/zip")
    public void exportZip(HttpServletResponse response) throws IOException {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://t.17win.com/iixAt74");
        //                        urlList.add("http://short-address.base-service-test.sit.91lyd.com/short-address/igi7TBl");
        //        urlList.add("http://short-address.base-service-test.sit.91lyd.com/short-address/IfiE0iU");

        String downloadFilename = "zipName.zip";
        downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
        // 指明response的返回对象是文件流
        response.setContentType("application/octet-stream");
        // 设置在下载框默认显示的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        try {
            ExportUtil.modifyZipOutput(urlList, zipOutputStream);
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            zipOutputStream.close();
        }
    }

    public static void main(String[] args) {
        String[][] arr = { { "1", "2", "3" },{ "4", "5", "6" } };
        System.out.println(JSON.toJSONString(arr));
    }

    private void down(List<String> urlList, HttpServletResponse response) {

        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            nameList.add("name" + i + ".jpg");
        }
        try {

            //zip包的名称
            String downloadFilename = "压缩包名.zip";
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            // 指明response的返回对象是文件流
            response.setContentType("application/octet-stream");
            // 设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
            @Cleanup
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            urlList.forEach(i -> {
                InputStream fis = null;
                try {
                    int index = urlList.indexOf(i);
                    // 跨平台建文件夹和文件名字
                    // File.separator  根据操作系统来判断用什么文件分隔符
                    // 这里只建立了一个文件夹，如果想分类的话，在后面加上想要的文件夹名称即可
                    ZipEntry zipEntry = new ZipEntry(index + File.separator + nameList.get(index));
                    // 放到压缩包里
                    zos.putNextEntry(zipEntry);
                    // 获得图片流
                    fis = getInputStreamByGet(i);
                    if (null != fis) {
                        byte[] buffer = new byte[1024];
                        int r = 0;
                        // 从输入流读取一定数量的字节，存到缓存区中
                        while ((r = fis.read(buffer)) != -1) {
                            zos.write(buffer, 0, r);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            zos.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过get请求得到读取器响应数据的数据流
     *
     * @param url
     * @return
     */
    private static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);

            //判定是否会进行302重定向
            if (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
                String location = conn.getHeaderField("Location");
                String cookies = conn.getHeaderField("Set-Cookie");

                URL serverUrl = new URL(location);
                conn = (HttpURLConnection) serverUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Cookie", cookies);
                //                conn.addRequestProperty("Accept-Charset", "UTF-8;");
                //                conn.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
                //                conn.addRequestProperty("Referer", "http://matols.com/");
                conn.connect();
                System.out.println("跳转地址:" + location);
            }

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
