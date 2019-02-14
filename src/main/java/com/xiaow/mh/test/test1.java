package com.xiaow.mh.test;

import com.xiaow.mh.business.domain.Suit;
import org.springframework.util.MultiValueMap;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by zhangnengwen on 18/5/7.
 */
public class test1 {
    public static void main(String[] args){
//        Double amount = 5.00;
//        double b = 6;
//        double c = 6.564;
//        double d = 6.4;
//        System.out.println(String.format("%.2f", amount));
//        System.out.println(String.format("%.2f", b));
//        System.out.println(String.format("%.2f", c));
//        System.out.println(String.format("%.2f", d));
//        Pattern pattern = Pattern.compile("_([0-9]{5})_([0-9]{8})(\\.csv)$");
//        Matcher matcher = pattern.matcher("1bbf3628_00500_20181120.csv");
//        if(matcher.find()){
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//        }
        String a = "http://anitoys.masadora.io/shop/1/product/search.html?shopCategoryId=41&page=2&a=43";
        Pattern pattern = Pattern.compile("&page=([0-9]+)");
        String b = a.replaceAll("&page=([0-9]+)","");
        //System.out.println(b);

        String cc ="<p class=\\\"MsoNormal\\\" align=\\\"left\\\"><span style=\\\"color: rgb(255, 102, 0); font-family: 微软雅黑, sans-serif; font-size: 20px; line-height: normal;\\\">商品详细</span><br></p>\n" +
                "\n" +
                "<p class=\\\"MsoNormal\\\" align=\\\"left\\\"><span style=\\\"color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans-serif; font-size: 13.3333px; line-height: normal;\\\">由在『苍蓝钢铁的琶音 －ARS NOVA-』本篇中活跃着的雾岛(与太郎)带来的，对心智模型全员进行了布偶熊「与太郎」化的衍生作品『雾熊s』中的「伊欧娜」以软塑料模型的形式登场了！在一定范围内可动的头、手、足部和领带是她的萌点所在。其头部安装的按下后会发出「噗皮～」声响的装置能够还原出她在行走时所发出的梦幻之音。请结合同时开订的「雾熊s 雾岛」一起来感受下由软塑料特有的温柔色泽和存在感所带来的治愈吧。</span><br></p>\n" +
                "\n" +
                "<div class=\\\"MsoNormal\\\" align=\\\"center\\\" style=\\\"text-align:center;mso-pagination:widow-orphan\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:12.0pt;font-family:宋体;mso-bidi-font-family:Arial;\n" +
                "color:black;mso-font-kerning:0pt\\\">\n" +
                "\n" +
                "<hr size=\\\"1\\\" width=\\\"100%\\\" noshade=\\\"\\\" style=\\\"color:#574F4B\\\" align=\\\"center\\\">\n" +
                "\n" +
                "</span></div>\n" +
                "\n" +
                "<p class=\\\"MsoNormal\\\" align=\\\"left\\\"></p><div class=\\\"MsoNormal\\\" align=\\\"center\\\" style=\\\"text-align:center;mso-pagination:widow-orphan\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:12.0pt;font-family:宋体;mso-bidi-font-family:Arial;\n" +
                "color:black;mso-font-kerning:0pt\\\">\n" +
                "\n" +
                "</span></div>\n" +
                "\n" +
                "<p mce_style=\\'margin-top: 16px; margin-bottom: 16px; font-size: 13.3333339691162px;\\' style=\\\"padding: 0px; margin-top: 16px; margin-bottom: 16px; color: rgb(0, 0, 0); font-family: Arial, Helvetica, sans-serif; font-size: 13.3333px; line-height: normal;\\\"><br><img src=\\\"http://upload2016.anitoys.com/FuCpHV7gchyw3OXfR3ZYYUfCKuMs\\\" style=\\\"width: 933px;\\\"></p><p class=\\\"MsoNormal\\\" align=\\\"left\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:9.0pt;font-family:\\\" arial\\\",sans-serif;mso-fareast-font-family:=\\\"\\\" 宋体;color:black;mso-font-kerning:0pt\\\"=\\\"\\\"><o:p><span style=\\\"font-family: 微软雅黑; font-size: 13.3333px; line-height: 20px; text-indent: 2em;\\\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></o:p></span><img src=\\\"http://upload2016.anitoys.com/FjhPX9H0qszcdSpjZk2qeRbOFQVO\\\" style=\\\"width: 524px;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:9.0pt;font-family:\\\" arial\\\",sans-serif;mso-fareast-font-family:=\\\"\\\" 宋体;color:black;mso-font-kerning:0pt\\\"=\\\"\\\"><o:p><span style=\\\"font-family: 微软雅黑; font-size: 13.3333px; line-height: 20px; text-indent: 2em;\\\"></span></o:p></span></p><p class=\\\"MsoNormal\\\" align=\\\"left\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:9.0pt;font-family:\\\" arial\\\",sans-serif;mso-fareast-font-family:=\\\"\\\" 宋体;color:black;mso-font-kerning:0pt\\\"=\\\"\\\"><o:p>&nbsp;</o:p></span></p>";
        cc = cc.replace("\\\"","\"").replace("\\'","'");
        System.out.print(cc);
    }
}
