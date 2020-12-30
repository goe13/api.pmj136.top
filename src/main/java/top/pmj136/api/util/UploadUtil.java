package top.pmj136.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-20
 */
@Component
public class UploadUtil {
    @Value("${api.upload.base-path}")
    private String basePath;

    public Map<String, Object> push(String pathName, MultipartFile file) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("flag", false);
        String fileType = file.getContentType();
        if (fileType == null) {
            resp.put("msg", "不支持的文件类型");
            return resp;
        }
        String suffix = fileType.split("/")[1];
        String prefix = fileType.split("/")[0];
        if (!"image".equals(prefix)) {
            resp.put("msg", "不支持的图片类型");
            return resp;
        }
        String fileName = System.currentTimeMillis() + "." + suffix;
        String targetPath = basePath + pathName + "/" + fileName;
        try {
            file.transferTo(new File(targetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.put("flag", true);
        resp.put("url", "https://img.pmj136.top/" + pathName + "/" + fileName);
        return resp;
    }

    public Boolean del(String fileName) {
        File f = new File(fileName);
        return f.delete();
    }
}
