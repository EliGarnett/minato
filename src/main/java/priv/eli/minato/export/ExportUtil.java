package priv.eli.minato.export;

import org.springframework.beans.factory.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExportUtil {

    public static void modifyZipOutput(List<String> urlList, ZipOutputStream zipOutputStream) {

        Integer count = 0;

        for (String url : urlList) {
            ZipEntry zipEntry = new ZipEntry(File.separator + count + ".jpg");
            InputStream inputStream = null;
            try {
                zipOutputStream.putNextEntry(zipEntry);
                inputStream = getInputStreamByGet(url);
                if (inputStream != null) {
                    byte[] buffer = new byte[1024];
                    int r = 0;
                    // 从输入流读取一定数量的字节，存到缓存区中
                    while ((r = inputStream.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, r);
                    }
                }
                count++;
            } catch (IOException e) {

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }

        }
    }

    private static InputStream getInputStreamByGet(String url) {
        try {
            //            HttpClient httpclient = new DefaultHttpClient();
            //
            //            HttpGet httpget = new HttpGet(url);
            //            HttpResponse resp = httpclient.execute(httpget);
            //            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
            //                HttpEntity entity = resp.getEntity();
            //
            //                return entity.getContent();
            //
            //            }


            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(100);
            conn.setConnectTimeout(100);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);

            //判定是否会进行302重定向
            while (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                System.out.println("11111111111111111111111111");
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
                System.out.println(location);
                //                System.out.println("跳转地址:" + location);
            }

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("22222222222222222222222222222222222");
                return conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
