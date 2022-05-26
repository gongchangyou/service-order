import com.mouse.order.DubboDemoApplication;
import com.mouse.order.impl.OrderServiceImpl;
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
    private OrderServiceImpl orderServiceImpl;

    @Test
    void create() {
        val orderId = orderServiceImpl.create();
        log.info("orderId={}", orderId);
    }

    @Test
    void buy() {
        val result = orderServiceImpl.buy(1);
        log.info("result={}", result);
    }



}