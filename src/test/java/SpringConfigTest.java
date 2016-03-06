import com.hapi.invoice.front.service.InvoiceGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConfigTest {
    private String[] springContextFileLocation = {
            "classpath:spring/app-config.xml"
    };

    private ClassPathXmlApplicationContext springContext;

    @Before
    public void init() {
        springContext = new ClassPathXmlApplicationContext(springContextFileLocation);
    }

    @Test
    public void testLoading() {
        InvoiceGenerator bean = (InvoiceGenerator) this.springContext.getBean("invoiceGenerator");
        Assert.assertNotNull(bean);
    }
}
