package xyz.xlong99.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 胡学良
 * 上传图片
 */
public class UploadFile {
    public static String uploadFiles(MultipartFile file) throws Exception {
        CommonsMultipartFile cFile = (CommonsMultipartFile) file;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        InputStream uploadedStream = fileItem.getInputStream();
        HashMap<String,InputStream> files = new HashMap<String, InputStream>();
        files.put("smfile",uploadedStream);
        String line = uploadToFarService(files);
        uploadedStream.close();
        JSONObject jsonObject = JSONObject.parseObject(line);
        JSONObject data = JSONObject.parseObject(jsonObject.getString("data"));
        return data.getString("url");
    }

    public static String uploadToFarService(HashMap<String, InputStream> files) throws Exception{
        String line;
        // 定义数据分隔线
        String BOUNDARY = "---------7d4a6d158c9";
        String u = "https://sm.ms/api/upload";
        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + BOUNDARY);

        OutputStream out = new DataOutputStream(conn.getOutputStream());
        // 定义最后数据分隔线
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        Iterator iter = files.entrySet().iterator();
        int i=0;
        while (iter.hasNext()) {
            i++;
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            InputStream val = (InputStream) entry.getValue();
            String fname = key;
            File file = new File(fname);
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"smfile"
                    + "\";filename=\"" + fname + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] data = sb.toString().getBytes();
            out.write(data);
            DataInputStream in = new DataInputStream(val);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 多个文件时，二个文件之间加入这个
            out.write("\r\n".getBytes());
            in.close();
        }
        out.write(end_data);
        out.flush();
        out.close();

        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        line =  reader.readLine();
        return line;

    }
}
