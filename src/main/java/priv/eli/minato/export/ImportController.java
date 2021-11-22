package priv.eli.minato.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Eli
 * @date 2020/7/14
 */

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://10.199.150.154/cloudpoi/api/excel/import";

    @PostMapping("/excel")
    public String importFile(@RequestParam(value = "file") MultipartFile file) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        //        String tempFileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String tempFilePath = "/Users/Eli" + File.separator + "/temp";
        File tempFile = new File(tempFilePath);
        file.transferTo(tempFile);

        FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
        //设置请求体，注意是LinkedMultiValueMap
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("excel", fileSystemResource);
        formData.add("appName", "taxmultiweb");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(formData, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }
}


