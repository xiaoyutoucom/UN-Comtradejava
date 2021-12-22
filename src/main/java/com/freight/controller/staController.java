package com.freight.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.freight.annotation.JwtIgnore;
import com.freight.common.R;
import com.freight.entity.Lindata6;
import com.freight.entity.Model;
import com.freight.entity.Worlddata;
import com.freight.service.Lindata6ervice;
import com.freight.service.Worlddataervice;
import com.freight.utils.ExcelUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuxin
 * @since 2021-10-05
 */
@RestController
@RequestMapping("api/v1/freight")
@Api(value = "AutoOrder", tags = "AutoOrder")
public class staController {

    @Resource
    private Lindata6ervice lin;
    @Resource
    private Worlddataervice word;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    String[] code={"156","643","251","276","826","348","40","203","703","528","724","381","112","616","56","246","398","417","792","116","458","764","704","418","702","586","104","699","50"};
    String[] cname={"中国","俄罗斯联邦","法国","德国","英国","匈牙利","奥地利","捷克共和国","斯洛伐克共和国","荷兰","西班牙","意大利","白俄罗斯","波兰","比利时","芬兰","哈萨克斯坦","吉尔吉斯斯坦","土耳其","柬埔寨","马来西亚","泰国","越南","老挝","新加坡","巴基斯坦","缅甸","印度","孟加拉国"};
    //@SaCheckLogin //同意登陆验证登陆验证暂时不起用
    @RequestMapping(value="/sta", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "tongji")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @JwtIgnore
    public R orders() {
        try {
            for (int i = 2010; i < 2019; i++) {
                double[] dqE ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                double[] dqI ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                double[] lydqE={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                double[] lydqI={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                double worldE = 0;//世界出口
                double worldI = 0;//世界进口
                QueryWrapper<Worlddata> userQueryWrapper = Wrappers.query();
                Map<String, Object> map = new HashMap<>();
                map.put("yr", i);
                userQueryWrapper.allEq(map);
                List<Worlddata> wlist = word.list(userQueryWrapper);
                for (int j = 0; j < wlist.size(); j++) {
                    Worlddata row = wlist.get(j);
                    if (row.getRgDesc().equals("Import")) {
                        worldI += row.getTradeValue();
                    } else {
                        worldE += row.getTradeValue();
                    }
                }
                System.out.println(i + "进口总:" + worldI);
                System.out.println(i + "出口总:" + worldE);
                double worldLYE = 0;//世界出口
                double worldLYI = 0;//世界进口
              QueryWrapper<Lindata6> QueryWrapper = Wrappers.query();
                Map<String, Object> mapl = new HashMap<>();
                map.put("yr", i);
                userQueryWrapper.allEq(map);
                List<Lindata6> llist = lin.list(QueryWrapper);
                for (int j = 0; j < llist.size(); j++) {
                    Lindata6 row = llist.get(j);
                    if (row.getRgDesc().equals("Import")) {
                        worldLYE += row.getTradeValue();
                    } else {
                        worldLYI += row.getTradeValue();
                    }
                }
                System.out.println(i + "林进口总:" + worldLYE);
                System.out.println(i + "林出口总:" + worldLYI);
                System.out.println(i+"年竞争性指数:");
                List<Model> excellist = new ArrayList<>();
                //按照国家获取全部贸易量
                for(int c=0;c<code.length;c++)
                {
                    for(int j = 0; j < wlist.size(); j++)
                    {
                        Worlddata row = wlist.get(j);
                        if(row.getRtCode().equals(code[c]))
                        {
                            if (row.getRgDesc().equals("Import")) {
                                dqI[c] += row.getTradeValue();
                            } else {
                                dqE[c] += row.getTradeValue();
                            }
                        }
                    }
                }
                for(int c=0;c<code.length;c++)
                {
                    for(int j = 0; j < llist.size(); j++)
                    {
                        Lindata6 row = llist.get(j);
                        if(row.getRtCode().equals(code[c]))
                        {
                            if (row.getRgDesc().equals("Import")) {
                                lydqI[c] += row.getTradeValue();
                            } else {
                                lydqE[c] += row.getTradeValue();
                            }
                        }
                    }
                }
                System.out.println(i + "林进口总:" + dqI);
                List<String[]> JZZist = new  ArrayList<>();

                for(int k=0;k<code.length;k++)
                {
                    double CI=0,RCAX=0,RCAM=0;
                    String[] jzz = {cname[k],"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    for(int j = 0; j < code.length; j++) {
//                        if (j<k) {
//                            RCAX = (lydqI[k] / dqI[k]) / (worldLYI / worldI);
//                            RCAM = (lydqE[j] / dqE[j]) / (worldLYE / worldE);
//                            CI = RCAX * RCAM;
//                            jzz[j+1]= String.valueOf(CI);
//                        }
                         if(j==k)
                        {
                            jzz[j+1]="0";
                        }
                        else
                        {
                            Double aaa= lydqE[k];
                            Double nnnn= dqE[k];
                            Double ccc= aaa/nnnn;
                            RCAX = (lydqE[k] / dqE[k]) / (worldLYE / worldE);
                            RCAM = (lydqI[j] / dqI[j]) / (worldLYI / worldI);
                            CI = RCAX * RCAM;

                            jzz[j+1]= String.valueOf(CI);
                            if(String.valueOf(CI).equals("Infinity"))
                            {
                                jzz[j+1]="0";
                            }
                        }
                    }
                    JZZist.add(jzz);

                }
                String[] title={String.valueOf(i),"中国","俄罗斯联邦","法国","德国","英国","匈牙利","奥地利","捷克共和国","斯洛伐克共和国","荷兰","西班牙","意大利","白俄罗斯","波兰","比利时","芬兰","哈萨克斯坦","吉尔吉斯斯坦","土耳其","柬埔寨","马来西亚","泰国","越南","老挝","新加坡","巴基斯坦","缅甸","印度","孟加拉国"};
                ExcelUtil.export("CI",title,JZZist,i+" CI互补性指数");
                List<String[]> CSL = new  ArrayList<>();

                double CS=0,X=0,Y=0,zgbz=0,qtbz=0;
                for(int k=0;k<code.length;k++) {
                    String[] cs = {cname[k],"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
                    for (int j = 0; j < code.length; j++) {
                        zgbz = lydqE[k] / dqE[k];
                        qtbz = lydqE[j] / dqE[j];
                        X = (zgbz + qtbz) / 2;
                        Y = 1 - abs((zgbz - qtbz) / (zgbz + qtbz));
                        CS = X * Y * 100;
                        if(j==k)
                        {
                            cs[j+1]="0";
                        }
                        else
                        {
                            cs[j+1]=String.valueOf(CS);
                        }
                        if(String.valueOf(CS).equals("NaN"))
                        {
                            cs[j+1]="0";
                        }
                        System.out.println(cname[k] +"-"+ cname[j]+code[j] + " CS:" + (CS));
                    }
                    CSL.add(cs);
                }
                ExcelUtil.export("CS",title,CSL,i+" CS相似度指数");
            }
            return R.ok();
        }catch(Exception ex){
            return R.ok().message(ex.toString());
        }
    }
    public static double abs(double a) {
        return (a < 0) ? -a : a;
    }

}

