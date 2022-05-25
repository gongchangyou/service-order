import com.mouse.dubbodemo.DubboDemoApplication;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/3/28 2:23 下午
 */
@Slf4j
@SpringBootTest(classes= DubboDemoApplication.class)
public class MinIOTest {

    private MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://localhost:9000")
                    .credentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY")
                    .build();

    @Test
    void upload(){
        try {
            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            } else {
                log.info("Bucket 'asiatrip' already exists.");
            }

            val response = minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("asiatrip")
                            .object("baiduyjroot.rar")
                            .filename("/Users/gongchangyou/Downloads/baiduyjroot.rar") //在本地找个文件
                            .build());
            log.info("upload response = {}",response);

        }catch (Exception e) {
            log.error("upload error ", e);
        }
    }

    @Test
    void get(){
        try {
            val url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                            .bucket("asiatrip")
                            .object("baiduyjroot.rar")
                            .expiry(10, TimeUnit.SECONDS)
                            .method(Method.GET)
                    .build());
            log.info("url = {}", url);
        } catch (Exception e) {
            log.error(" get ",e);
        }
    }
}
