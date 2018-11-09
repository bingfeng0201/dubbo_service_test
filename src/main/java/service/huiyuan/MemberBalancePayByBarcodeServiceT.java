package service.huiyuan;

import com.alibaba.fastjson.JSONArray;
import com.imxiaomai.member.dto.CommonDto;
import com.imxiaomai.member.service.OpenBalanceService;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.math.BigDecimal;

/*
1.dubbo-扫码支付--ok
入参：
payPrice BigDecimal 是支付金额
shopCode String 否店铺编码
orderCode String 是订单号
barcode String 是授权码
返回值：code，message，data
 */

public class MemberBalancePayByBarcodeServiceT extends AbstractJavaSamplerClient{
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-config.xml");

    public BigDecimal payPrice;
    public String shopCode;
    public String orderCode ;
    public String barcode ;

    @Resource
    public OpenBalanceService openBalanceService;
    private String resultData;

    @Override
    public Arguments getDefaultParameters() {
        Arguments ar = new Arguments();
        ar.addArgument("payPrice","25.00");
        ar.addArgument("shopCode","TS0013");
        ar.addArgument("orderCode","false");
        ar.addArgument("barcode","false");
        return ar;
    }

    public void setupTest(JavaSamplerContext arg0){
        openBalanceService=(OpenBalanceService)context.getBean("openBalanceService");
    }

    public SampleResult runTest(JavaSamplerContext context) {

        //控制事物的开始和结束时间，将结果写到结果树中
        SampleResult sr = new SampleResult();
        shopCode = (context.getParameter("shopCode"));
        orderCode = context.getParameter("orderCode");

        sr.sampleStart();

        try {
            //DateVo dateVo =

           // CommonDto<String> responseData = openBalanceService.memberBalancePayByBarcode();
            CommonDto<String> responseData = null;
            if(responseData != null &&  !"".equals(responseData)){
                sr.setSuccessful(true);
                resultData =JSONArray.toJSON(responseData.getData()).toString();
                sr.setResponseData("code:"+responseData.getCode()+"message:"+responseData.getMessage() +"data:"+responseData.getData(),"utf-8");
            }else{
                sr.setSuccessful(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        sr.sampleEnd();

        return sr;
    }
    public void teardownTest(JavaSamplerContext arg0){}
}
