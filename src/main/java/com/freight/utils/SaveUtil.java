//package com.freight.utils;
//
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.model.PutObjectResult;
//import com.freight.entity.GSS.pushR.shipmentsbody;
//import com.jcraft.jsch.ChannelSftp;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.XMLWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//
//@Component
//public class SaveUtil {
//
//    @Autowired
//    SftpConnect sfrpconn;
//    @Autowired
//    SftpCommon sftpcommon;
//    @Autowired
//    S3Util s3;
//    Regions clientRegion = Regions.AP_SOUTHEAST_2;
//    @Value("${AWS.bucketName}")
//    String bucketName;
//    //保存文件到服务器
//    public boolean Savexml(String xml,String Name)
//    {
//        try {
//            Name = Name+".xml";
//            // 1、设置生成xml的格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            // 设置编码格式
//            format.setEncoding("UTF-8");
//            // 6、生成xml文件
//            File file = new File(Name);
//
//            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
//            // 设置是否转义，默认使用转义字符
//            writer.setEscapeText(false);
//            writer.write(xml);
//            writer.close();
//            System.out.println("生成rss.xml成功");
//            InputStream inputStream = null;
//            inputStream = new FileInputStream(file);
//            file.deleteOnExit();
//            ChannelSftp sfrp = sfrpconn.connect();
//            //保存到sftp服务器
//            boolean ok = sftpcommon.uploadFile(sfrp,inputStream,Name);
//            sfrpconn.disconnect(sfrp);
//            //保存到亚马逊云
//            s3.creatConnect();
//            //List<Bucket> bucke=s3.getBuckets();
//            //创建桶
//            //Bucket buck = s3.creatBucket(bucketName);
//            s3.getBacketObjects(bucketName);
//            //上传文件
//            PutObjectResult pr= s3.creatObject(bucketName,"mainfreight/xml/", Name,file);
//            //mainfreight/xml/mainfreight.xml
//            //ObjectListing ob = s3.getBacketObjects(bucketName);
//            //测试是否上传成功
//            //String url =s3.getDownloadUrl(bucketName,"mainfreight/xml/mainfreight.xml");
//            return ok;
//        }
//        catch (Exception e) {
////            e.printStackTrace();
//            System.out.println("保存xml文件失败");
//            return false;
//        }
//    }
//    /**
//     * 将base64编码转换成PDF
//     * @param base64sString 1.使用BASE64Decoder对编码的字符串解码成字节数组
//     *            2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
//     *            3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
//     *            4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
//     * @param fileName 文件名称
//     */
//    public String generateBase64StringToFile(String url,String Name) {
//        BufferedInputStream bin = null;
//        FileOutputStream fout = null;
//        BufferedOutputStream bout = null;
//        shipmentsbody body = new shipmentsbody();
//        try {
//            s3.creatConnect();
//            s3.getBacketObjects(bucketName);
//
//            FileUtil fileutil = new FileUtil();
//            File file = fileutil.getfile(url, Name);
//            //上传文件
//            PutObjectResult pr = s3.creatObject(bucketName, "fliway/jpg/", Name, file);
//            String urljpg = s3.getDownloadUrl(bucketName, "fliway/jpg/" + Name);
//
//            return urljpg;
//        } catch (Exception e) {
//            return "";
//        } finally {
//
//        }
//    }
//    //保存文件到服 务器
//    public String SaveS3xml(String xml,String Name)
//    {
//        try {
//            Name = Name+".xml";
//            // 1、设置生成xml的格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            // 设置编码格式
//            format.setEncoding("UTF-8");
//            // 6、生成xml文件
//            File file = new File(Name);
//
//            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
//            // 设置是否转义，默认使用转义字符
//            writer.setEscapeText(false);
//            writer.write(xml);
//            writer.close();
//            System.out.println("生成rss.xml成功");
//            InputStream inputStream = null;
//            inputStream = new FileInputStream(file);
//            file.deleteOnExit();
//            //保存到亚马逊云
//            s3.creatConnect();
//            //List<Bucket> bucke=s3.getBuckets();
//            //创建桶
//            //Bucket buck = s3.creatBucket(bucketName);
//            s3.getBacketObjects(bucketName);
//            //上传文件
//            PutObjectResult pr= s3.creatObject(bucketName,"fliway/xml/", Name,file);
//            //mainfreight/xml/mainfreight.xml
//            //ObjectListing ob = s3.getBacketObjects(bucketName);
//            //测试是否上传成功
//            String url =s3.getDownloadUrl(bucketName,"fliway/xml/"+Name);
//            return url;
//        }
//        catch (Exception e) {
////            e.printStackTrace();
//            System.out.println("保存xml文件失败");
//            return "保存xml文件失败"+e.toString();
//        }
//    }
//}
