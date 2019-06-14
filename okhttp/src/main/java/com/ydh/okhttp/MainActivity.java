package com.ydh.okhttp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ydh.network.HttpHelper;
import com.ydh.network.call.HttpCallback;
import com.ydh.okhttp.bean.BeanPhoto;
import com.ydh.okhttp.bean.DeviceLoginModel;
import com.ydh.okhttp.databinding.ActivityMainBinding;
import com.ydh.okhttp.network.OkHttpNet;
import com.ydh.okhttp.util.TimeUtil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault12;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ydh.okhttp.util.SendSoap.translate;

/**
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {

    private static String TAG = "zxy";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(view ->
                HttpHelper.obtain().post("http://c.3g.163.com/photo/api/set/0001%2F2250173.json", null, new HttpCallback<BeanPhoto>() {
                    @Override
                    public void onFailure(String e) {
                        Log.e("zxy", "onFailure: " + e);
                    }

                    @Override
                    public void onSuccess(BeanPhoto beanPhoto) {
                        Log.e("zxy", "onSuccess: " + beanPhoto.toString());
                    }
                }));

        binding.button2.setOnClickListener(view -> {
            if (TimeUtil.isFastDoubleClick()) {
                Log.e("zxy", "onClick: 成功");
            }
        });

        // 使用retrofit 请求
        binding.button3.setOnClickListener(view -> {
            HashMap map = new HashMap();
            map.put("deviceMac", "BO:F1:EC:32:95:9A");

            HttpHelper.obtain().post("user/login", map, new HttpCallback<DeviceLoginModel>() {
                @Override
                public void onFailure(String e) {
                    Log.e("zxy", "onFailure: " + e);
                }

                @Override
                public void onSuccess(DeviceLoginModel beanPhoto) {
                    Log.e("zxy", "onSuccess: " + beanPhoto.toString());
                    Log.e("zxy", "线程名称: " + Thread.currentThread().getName());
                }
            });
        });

        binding.okGetSey.setOnClickListener(view -> {
            OkHttpNet.getIn().synRequest();
        });

        binding.okGetAsy.setOnClickListener(view -> {
            OkHttpNet.getIn().asyRequest();
        });

        binding.okPostStr.setOnClickListener(view -> {
            OkHttpNet.getIn().postStringRequest();
        });

        binding.okPostFile.setOnClickListener(view -> {
            OkHttpNet.getIn().postFileRequest();
        });

        binding.okPostForm.setOnClickListener(view -> {
            OkHttpNet.getIn().postFormRequest();
        });

        binding.okPostBody.setOnClickListener(view -> {
            OkHttpNet.getIn().postBodyRequest();
        });

        binding.soap.setOnClickListener(view -> {
            new Thread(() -> {
//                    queryPhoneLocation("13399040879");
//                    searchWea("", "", "");
//                    calculate1();
                try {
                    callXml();
                } catch (Exception e) {
                    Log.e(TAG, "run: 失败");
                    e.printStackTrace();
                }
//                    try {
//                        translate();
//                    } catch (Exception e) {
//                        Log.e(TAG, "run: 失败" );
//                        e.printStackTrace();
//                    }
            }).start();

        });

    }

    private String queryPhoneLocation(String phone) {
        String nameSpace = "http://WebXml.com.cn/";
        String methodName = "getMobileCodeInfo";
        SoapObject soapObject = new SoapObject(nameSpace, methodName);

        soapObject.addProperty("mobileCode", phone);
        soapObject.addProperty("userID", "");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true; //very important for compatibility
        envelope.bodyOut = soapObject;

        String url = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        HttpTransportSE htSE = new HttpTransportSE(url);

        Object response = null;

        try {
            String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";
            htSE.call(soapAction, envelope);

            response = envelope.getResponse();

        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        if (response != null) {
            Log.d("HelloAndroidSoap", "The invoke result is: " + response.toString());

            return response.toString();
        } else {
            Log.d("HelloAndroidSoap", "The invoke result is: 失败");
            return null;
        }
    }


    /**
     * @param wsdlurl  http://60.190.224.119:5039/XRHotelSelf-Gzl-1.4/
     * @param method
     * @param cityname
     * @return
     */
    public static SoapObject searchWea(String wsdlurl, String method, String cityname) {
        //指定webservice的命名空间和调用的方法名
        String namespace = "http://tempuri.org";
        SoapObject soap = new SoapObject(namespace, method);
        //添加属性，只要设置参数的顺序一致，调用方法的参数名不一定与服务端的WebService类中的方法参数名一致
        soap.addProperty("theCityName", cityname);
        //通过SoapSerializationEnvelope类的构造方法设置SOAP协议的版本号。
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //设置需要传出的Soap
        soapEnvelope.bodyOut = soap;
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(soap);
        //创建http传输对象
        HttpTransportSE transportSE = new HttpTransportSE(wsdlurl);
        //soap操作url
        String SOAP_ACTION = namespace + method;
        try {
            //请求调用WebService方法
            transportSE.call(SOAP_ACTION, soapEnvelope);
            //使用getResponse获得WebService方法解析xml的返回结果
            SoapObject result = (SoapObject) soapEnvelope.getResponse();
            if (result != null)
                return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 摄氏度 转 华氏温度
    public void calculate() {
//        设置命名空间、访问地址、方法名
        String SOAP_ACTION = "http://www.w3schools.com/xml/CelsiusToFahrenheit";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String NAMESPACE = "http://www.w3schools.com/xml/";
        String URL = "http://www.w3schools.com/xml/tempconvert.asmx";
        try {
//            创建soapObject,即拼装soap bodyin
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
//            添加传入参数，根据具体格式测试
            Request.addProperty("Celsius", "32.0");
//            创建soap 数据
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);
//          soap 协议发送
            transport.call(SOAP_ACTION, soapEnvelope);
//            soap 请求完成后返回数据并转换成字符串
            SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.e("zxy", "Result Celsius: " + resultString);
        } catch (Exception ex) {
            Log.e("zxy", "Error: " + ex.getMessage());
        }
    }

    // 摄氏度 转 华氏温度
    public void calculate1() {
//        设置命名空间、访问地址、方法名
        String SOAP_ACTION = "http://tempuri.org/GetAvailableRoomFeatures";
        String METHOD_NAME = "GetAvailableRoomFeatures";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://60.190.224.119:5039/XRHotelSelf-Gzl-1.4/";
        try {
//            创建soapObject,即拼装soap bodyin
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
//            添加传入参数，根据具体格式测试
            Request.addProperty("ArrivalDate", "2019-05-26");
            Request.addProperty("DepartureDate", "2019-05-27");
            Request.addProperty("RoomType", "");
            Request.addProperty("RoomNumber", "");
            Request.addProperty("RoomStates", "R");


//            创建soap 数据
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            Log.e("zxy", "Result Celsius: " + Request.toString());
//            InputStream in=this.getClass().getClassLoader().getResourceAsStream("xml.xml");
//            String body=readSoapFile(in);
//            soapEnvelope.setOutputSoapObject(body);

            HttpTransportSE transport = new HttpTransportSE(URL);
//          soap 协议发送
            transport.call(SOAP_ACTION, soapEnvelope);
//            soap 请求完成后返回数据并转换成字符串
            SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.e("zxy", "Result Celsius: " + resultString);
        } catch (Exception ex) {
            Log.e("zxy", "Error: " + ex.getMessage());
        }
    }

    public String readSoapFile(InputStream input) throws Exception {
        byte[] b = new byte[1024];
        int len = 0;
        int temp = 0;
        while ((temp = input.read()) != -1) {
            b[len] = (byte) temp;
            len++;
        }
        String soapxml = new String(b);
        //替换xml文件的占位符
        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", "2019-05-26");
        map.put("endTime", "2019-05-27");
        return replace(soapxml, map);
    }

    public String replace(String param, Map<String, String> params) throws Exception {
        //拼凑占位符使用正则表达式替换之
        String result = param;
        if (params != null && !params.isEmpty()) {
            //拼凑占位符
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String name = "\\$" + entry.getKey();
                Pattern p = Pattern.compile(name);
                Matcher m = p.matcher(result);
                if (m.find()) {
                    result = m.replaceAll(entry.getValue());
                }
            }
        }
        return result;
    }


    public static void callXml() throws Exception {
        //地址
        java.net.URL url = new URL("http://60.190.224.119:5039/XRHotelSelf-Gzl-1.4/.wasm");
        //调用的方法
        String soapActionString = "http://60.190.224.119:5039/XRHotelSelf-Gzl-1.4/RoomResource_Query";

        //打开链接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //拼接好xml
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n");
        sb.append("<soap12:Body>\n");
        sb.append("<GetAvailableRoomFeatures xmlns=\"http://tempuri.org/\">\n");

        sb.append("<interface>");
        sb.append("<item>");
        sb.append("                <ArrivalDate>2019-05-26</ArrivalDate>\n" +
                "                <DepartureDate>2019-05-27</DepartureDate>\n" +
                "                <RoomType></RoomType>\n" +
                "                <RoomNumber></RoomNumber>\n" +
                "                <RoomStates>R</RoomStates>\n");
        sb.append("</item>\n");
        sb.append("</interface>\n");

        sb.append("</GetAvailableRoomFeatures>\n");
        sb.append("</soap12:Body>\n");
        sb.append("</soap12:Envelope>\n");
        String xmlStr = sb.toString();
        Log.i(TAG, "callXml: response555:" + xmlStr);

        System.out.println(xmlStr);
        //设置好header信息
        con.setRequestMethod("POST");
        con.setRequestProperty("content-type", "application/soap+xml; charset=utf-8");
        con.setRequestProperty("Content-Length", String.valueOf(xmlStr.getBytes().length));
        con.setRequestProperty("soapActionString", soapActionString);

        //post请求需要设置
        con.setDoOutput(true);
        con.setDoInput(true);
//        con.connect();

        //对请求body 往里写xml 设置请求参数
        OutputStream ops = con.getOutputStream();
        ops.write(xmlStr.getBytes());
        ops.flush();
        ops.close();

        //设置响应回来的信息
        InputStream ips = con.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = ips.read(buf)) != -1) {
            baos.write(buf, 0, length);
            baos.flush();
        }
        byte[] responsData = baos.toByteArray();
        baos.close();

        //处理写响应信息
        String responsMess = new String(responsData, "utf-8");
        System.out.println("========================");
        System.out.println(responsMess);
        System.out.println(con.getResponseCode());
        Log.i(TAG, "callXml: response2:" + responsMess);
        Log.i(TAG, "callXml: response3:" + con.getResponseCode());
    }

}
