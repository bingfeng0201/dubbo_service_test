package service.huiyuan;

import com.alibaba.fastjson.JSONArray;
import com.imxiaomai.member.service.OpenBalanceService;
import com.imxiaomai.member.dto.CommonDto;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class SelectPaymentCodeServiceT extends AbstractJavaSamplerClient{
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-config.xml");

    public Integer memId;
    public Boolean refreshTest;

    @Resource
    public OpenBalanceService openBalanceService;
    private String resultData;

    @Override
    public Arguments getDefaultParameters() {
        Arguments ar = new Arguments();
        ar.addArgument("memId","2018146");
        ar.addArgument("refreshTest","false");
        return ar;
    }

    public void setupTest(JavaSamplerContext arg0){
        openBalanceService=(OpenBalanceService)context.getBean("openBalanceService");
    }

    public SampleResult runTest(JavaSamplerContext context) {

        //控制事物的开始和结束时间，将结果写到结果树中
        SampleResult sr = new SampleResult();
        memId = Integer.parseInt(context.getParameter("memId"));
        refreshTest = Boolean.getBoolean(context.getParameter("refreshTest"));

        sr.sampleStart();

        try {

            CommonDto<String> responseData = openBalanceService.selectPaymentCode(memId,refreshTest);
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
