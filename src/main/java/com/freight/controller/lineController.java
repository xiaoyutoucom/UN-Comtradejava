//package com.freight.controller;
//
//
//import com.freight.annotation.JwtIgnore;
//import com.freight.common.R;
//import com.freight.entity.Dataset;
//import com.freight.entity.JsonRootBean;
//import com.freight.entity.Lindata6;
//import com.freight.service.FREIGHT_BOOKING_LOGervice;
//import com.freight.service.Lindata6ervice;
//import com.freight.utils.*;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author yuxin
// * @since 2021-10-05
// */
//@RestController
//@RequestMapping("api/v1/freight")
//@Api(value = "AutoOrder", tags = "AutoOrder")
//public class lineController {
//    @Resource
//    private FREIGHT_BOOKING_LOGervice logservice;
////    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    InterfaceCall call;
//    @Autowired
//    GetAPIResultUtil getu;
//    @Autowired
//    private FreightUtil fu;
//    @Autowired
//    CPPHUtil cpph;
//    @Autowired
//    fliwayUtil fy;
//    @Autowired
//    mainfreightUtil mf;
//    @Resource
//    private Lindata6ervice userservice;
//    //private Worlddataervice userservice;
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//    //@SaCheckLogin //同意登陆验证登陆验证暂时不起用
//    @RequestMapping(value="/line", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "下单接口")
//    @CrossOrigin(origins = "*",maxAge = 3600)
//    @JwtIgnore
//    public R orders() {
//        try {
//            String[] cc= {"4403,4406,4407,4408,4409","4410,4411,4412,4413,4701","4702,4703,4704,4705,4706","4414,4415,4416,4417,4418","4419,4420,4421,4401,4402","4404,4405,48,4707,940161","940169,940330,940340,940350,940360"};
//            String[] start_year = {"2010","2011","2012","2013","2014","2015","2016","2017","2018"};//,'2013','2014','2015','2016','2017','2018';
//            String[] USER_AGENTS = {
//            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
//                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
//                    "Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
//                    "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
//                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
//                    "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
//                    "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
//                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
//                    "Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
//                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
//                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
//                    "Mozilla/5.0 (X11; Linux i686; U;) Gecko/20070322 Kazehakase/0.4.5",
//                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.8) Gecko Fedora/1.9.0.8-1.fc10 Kazehakase/0.5.6",
//                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
//                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
//                    "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52"
//                    };
//            for(int y=0;y<start_year.length;y++) {
//                for(int i=0;i<cc.length;i++) {
//                    boolean ift=true;
//                    while(ift) {
//                        try{
//                            String url="https://comtrade.un.org/api/get?r=all&p=0&ps=" + start_year[y] + "&px=H3&rg=1,2&freq=A&type=C&cc=" + cc[i] + "&fmt=json";
//                            System.out.println(url);
//                            save(url);
//                            ift=false;
//                            try{
//                                Thread.currentThread().sleep(36000);
//                            }catch(InterruptedException ie){
//                                ie.printStackTrace();
//                            }
//                        }
//                        catch (Exception ex)
//                        {
//                            System.out.println(""+ex.toString());
//                        }
//                    }
//
//                } }
//            System.out.println("ok");
//            return R.ok();//返回下单成功后的数据
//        }
//        catch (Exception e) {
//
//            return R.error().message(e.toString());
//        }
//    }
//    public void save(String url)
//    { HttpHeaders headers = new HttpHeaders();
////        headers.set("Content-type", "application/json;charset=utf-8");
//        headers.set("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)");
//
//        String body = call.getjson(url,headers);
//
//        JsonRootBean gs = gson.fromJson(body, JsonRootBean.class);
//        for (int j = 0; j < gs.getDataset().size(); j++) {
//            Lindata6 lin6=new Lindata6();
//            Dataset d=gs.getDataset().get(j);
//            lin6.setCmdCode(d.getCmdCode());
//            lin6.setPtCode(String.valueOf(d.getPtCode()) );
//            lin6.setPtTitle(d.getPtTitle());
//            lin6.setRgDesc(d.getRgDesc());
//            lin6.setRtCode(String.valueOf(d.getRtCode()));
//            lin6.setRtTitle(d.getRtTitle());
//            lin6.setTradeValue(Double.valueOf(d.getTradeValue()));
//            lin6.setYr(Long.valueOf(d.getYr()));
//            userservice.save(lin6);
//        }
//
//    }
//}
//
