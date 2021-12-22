package com.freight;

import com.freight.entity.Dataset;
import com.freight.entity.JsonRootBean;
import com.freight.entity.Lindata6;
import com.freight.service.Lindata6ervice;
import com.freight.utils.InterfaceCall;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
@RestController
public class lindata {
    @Autowired
    InterfaceCall call=new InterfaceCall();
    @Resource
    private Lindata6ervice userservice;
    //private Worlddataervice userservice;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value="/addlin", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void main() throws IOException {
        String[] cc= {"4403,4406,4407,4408,4409","4410,4411,4412,4413,4701","4702,4703,4704,4705,4706","4414,4415,4416,4417,4418","4419,4420,4421,4401,4402","4404,4405,48,4707,940161","940169,940330,940340,940350,940360"};
        String[] start_year = {"2010,2011,2012,2013,2014","2015,2016,2017,2018"};//,'2013','2014','2015','2016','2017','2018';

        for(int y=0;y<start_year.length;y++) {
        for(int i=0;i<cc.length;i++) {
            boolean ift=true;
            while(ift) {
                try{
                    String url="http://comtrade.un.org/api/get?r=all&p=0&ps=" + start_year[y] + "&px=H3&rg=1,2&freq=A&type=C&cc=" + cc[i] + "&fmt=json";
                    save(url);
                    System.out.println(""+url);
                    ift=false;
                }
                catch (Exception ex)
                {
                    System.out.println(""+ex.toString());
                }
            }

        } }
        System.out.println("ok");
    }
    public void save(String url)
    {
        String body = call.getjson(url);

        JsonRootBean gs = gson.fromJson(body, JsonRootBean.class);
        for (int j = 0; j < gs.getDataset().size(); j++) {
            Lindata6 lin6=new Lindata6();
            Dataset d=gs.getDataset().get(j);
            lin6.setCmdCode(d.getCmdCode());
            lin6.setPtCode(String.valueOf(d.getPtCode()) );
            lin6.setPtTitle(d.getPtTitle());
            lin6.setRgDesc(d.getRgDesc());
            lin6.setRtCode(String.valueOf(d.getRtCode()));
            lin6.setRtTitle(d.getRtTitle());
            lin6.setTradeValue(Double.valueOf(d.getTradeValue()));
            userservice.save(lin6);
        }
        System.out.println(""+gs.getDataset().size());
    }
}
