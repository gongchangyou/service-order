import com.mouse.dubbodemo.DubboDemoApplication;
import com.mouse.dubbodemo.repository.MacaqueRepository;
import com.mouse.dubbodemo.repository.db.model.Macaque;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/3/15 9:41 上午
 */

@Slf4j
@SpringBootTest(classes= DubboDemoApplication.class)
public class DBTest {

    //mongodb 客户端
    @Autowired
    private MacaqueRepository repository;


    @Test
    public void insert() {
        val result = repository.insertMacaque(new Macaque().builder()
                        .name("CJ184")
                        .memo("184memo")
                .build());
        log.info("result={}", result);
    }

    @Test
    public void update() {
        val result = repository.updateMacaque(new Macaque().builder()
                        .id(2)
                .name("CJ185")
                .memo("185memo")
                .build());
        log.info("result={}", result);
    }

    @Test
    public void search() {
        val result = repository.getMacaqueById(2L);
        log.info("result={}", result);
    }

    @Test
    public void searchByName() {
        val result = repository.getMacaqueByName("CJ185");
        log.info("result={}", result);
    }
}