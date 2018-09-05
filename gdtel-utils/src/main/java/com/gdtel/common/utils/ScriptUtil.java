package com.gdtel.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * Created by gdxieyue@gmail.com
 * 2017-06-06 10:02
 */
public class ScriptUtil {

    /**
     * 变量开始字符
     */
    public static final String VariableStart = "${";
    /**
     * 变量结束字符
     */
    public static final String VariableEnd = "}";


    public static String GetDateFormat(String param) throws Exception {
        String arrParam[] = param.split(",");
        LocalDateTime now = LocalDateTime.now();
        if (arrParam.length == 3) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(arrParam[2]);
            switch (arrParam[1]) {
                case "month":
                    return now.plusMonths(Integer.valueOf(arrParam[0])).format(dtf);
                case "day":
                    return now.plusDays(Integer.valueOf(arrParam[0])).format(dtf);
            }
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(param);
            return now.format(dtf);
        }
        throw new Exception("GetDateFormat param is error, params:" + param);
    }

    /**
     * 根据格式返回对应
     *
     * @param name
     * @return
     */
    public static String GetValue(String name) throws Exception {
        return GetDateFormat(name);
    }

    /**
     * 进行变量填充式计算
     *
     * @param _value 计算前的原始值
     * @return　计算后的取值
     */
    public static String FillValue(String _value) throws Exception {
        int __startIndex = _value.indexOf(VariableStart);
        if (__startIndex < 0) {
            //没有找到变量
            return _value;
        }

        //在变量之前的字符串
        String __tmpValue = _value.substring(0, __startIndex);
        int __endIndex = _value.indexOf(VariableEnd, __startIndex + VariableStart.length());
        if (__endIndex < 0) {
            //没有发现变量结束符
            return __tmpValue;
        }
        //变量名
        String __varName = _value.substring(
                __startIndex + VariableStart.length(),
                __endIndex - VariableStart.length() + VariableStart.length()
        );

        //在变量之后的字符串
        String __afterValue = _value.substring(__endIndex + VariableEnd.length());

        //取变量的值
        String __varValue;
        __varValue = GetValue(__varName);
        if (__varValue.length() >= 0) {
            __tmpValue += __varValue;
            __tmpValue += __afterValue;
            return FillValue(__tmpValue);
        } else {
            __tmpValue += __afterValue;
        }
        return __tmpValue;
    }


    /**
     * 根据格式返回对应
     *
     * @param name
     * @return
     */
    public static String GetValue(String name, JSONObject j) throws Exception {
        String result = j.getString(name);
        if (StringUtils.isNotBlank(result)) {
            return result;
        } else {
            return GetDateFormat(name);
        }
    }

    /**
     * 进行变量填充式计算
     *
     * @param _value 计算前的原始值
     * @return　计算后的取值
     */
    public static String FillValue(String _value, JSONObject j) throws Exception {
        int __startIndex = _value.indexOf(VariableStart);
        if (__startIndex < 0) {
            //没有找到变量
            return _value;
        }

        //在变量之前的字符串
        String __tmpValue = _value.substring(0, __startIndex);
        int __endIndex = _value.indexOf(VariableEnd, __startIndex + VariableStart.length());
        if (__endIndex < 0) {
            //没有发现变量结束符
            return __tmpValue;
        }
        //变量名
        String __varName = _value.substring(
                __startIndex + VariableStart.length(),
                __endIndex - VariableStart.length() + VariableStart.length()
        );

        //在变量之后的字符串
        String __afterValue = _value.substring(__endIndex + VariableEnd.length());

        //取变量的值
        String __varValue;
        __varValue = GetValue(__varName, j);
        if (__varValue.length() >= 0) {
            __tmpValue += __varValue;
            __tmpValue += __afterValue;
            return FillValue(__tmpValue, j);
        } else {
            __tmpValue += __afterValue;
        }
        return __tmpValue;
    }

    public static void CopyFile(String originFilePath, String targetFilePath) throws IOException, FileNotFoundException {
        String str = ReadFile(originFilePath);
        WriteStringToFile(targetFilePath, str);
    }

    public static void WriteStringToFile(String filePath, String txt) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(txt);
    }

    public static String ReadFile(String filePath) throws IOException {
        File f = new File(filePath);
        InputStream in = new FileInputStream(f);
        byte b[] = new byte[202400];
        int len = 0;
        int temp = 0;
        while ((temp = in.read()) != -1) {
            b[len] = (byte) temp;
            len++;
        }
        in.close();
        return new String(b, 0, len);
    }


    public static void main(String args[]) throws Exception {

        String cron = "0 13 16 4 * ?";
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(cron);
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}
