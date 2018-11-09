package huiyuan;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import service.huiyuan.SelectPaymentCodeServiceT;


public class SelectPaymentCodeTest {
    public static void main(String [] args){

        Arguments ar = new Arguments();
        ar.addArgument("memId","1");
        ar.addArgument("refreshTest","true");

        JavaSamplerContext context = new JavaSamplerContext(ar);

        SelectPaymentCodeServiceT qt = new SelectPaymentCodeServiceT();
        qt.setupTest(context);
        qt.runTest(context);

        System.out.println("-------over-------");

    }
}
