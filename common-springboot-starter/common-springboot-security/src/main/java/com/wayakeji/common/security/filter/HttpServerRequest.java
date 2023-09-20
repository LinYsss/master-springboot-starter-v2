package com.wayakeji.common.security.filter;

import com.wayakeji.common.core.util.Charset;
import com.wayakeji.common.core.util.WebUtils;
import com.wayakeji.common.core.util.http.HttpHelper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *  Filter请求拦截中添加header属性
 *
 */
public class HttpServerRequest extends HttpServletRequestWrapper implements HttpRequest {

    private final byte[] bytes;
    private final String body;
    private final HttpServletRequest request;
    private Map<String, String> headerMap = new HashMap<String, String>();
    private Map<String, String[]> parameters = new HashMap<>();
    private Method mapping;
    private Class<?> control;
    /**
     * Constructs a request object wrapping the given request.
     *构造封装给定请求的请求对象。
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public HttpServerRequest(HttpServletRequest request) throws IOException {
        super(request);
        this.request = request;
        this.bytes = HttpHelper.getBodyString(request).getBytes(Charset.UTF_8);
        this.body = this.body();
        // 将现有parameter传递给params
        parameters.putAll(request.getParameterMap());
    }

    public void addHeader(String name, String value) {
        this.headerMap.put(name, value);
    }

    public void setMapping(Method mapping) {
        this.mapping = mapping;
    }
    public void setControl(Class<?> control) {
        this.control = control;
    }

    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

    @Override
    public String id() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String url() {
        StringBuilder strb = new StringBuilder();
        String uri = this.request.getServletPath();
        for(int i = 0; i < uri.length(); i++) {
            char c = uri.charAt(i);
            if(c == '?' || c == '#') {
                break;
            }
            strb.append(c);
        }
        return strb.toString();
    }

    public String getBody() {
        return body;
    }
    @Override
    public String body() {
        // 获取body数据
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = this.getInputStream();
            if(inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }else {
                stringBuilder.append("");
            }
        }catch (IOException ex) {

        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String socketAddr() {
        return request.getRemoteAddr();
    }

    @Override
    public String ip() {
        return ip(true);
    }

    @Override
    public String ip(boolean agency) {
        if(agency) {
            return WebUtils.getRequestIp(this.request);
        }else {
            return this.request.getRemoteAddr();
        }
    }

    @Override
    public Class<?> control() {
        return control;
    }

    @Override
    public Method mapping() {
        return mapping;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public int available() throws IOException {
                return bytes.length;
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 获取所有参数名
     *
     * @return all parameter names
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(parameters.keySet());
        return vector.elements();
    }


    @Override
    public Map<String, String[]> getParameterMap() {
        return parameters;
    }


    /**
     * 添加参数
     *
     * @param extraParams 自定义参数Map
     */
    public void addParameters(Map<String, Object> extraParams) {
        if(extraParams == null) {
            return;
        }
        for(Map.Entry<String, Object> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 添加参数
     *
     * @param name  key
     * @param value value
     */
    private void addParameter(String name, Object value) {
        if(value != null) {
            if(value instanceof String[]) {
                parameters.put(name, (String[]) value);
            }else if(value instanceof String) {
                parameters.put(name, new String[]{(String) value});
            }else {
                parameters.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    /**
     * 重写getParameter，参数从当前类中的map获取
     *
     * @param name key
     * @return value
     */
    @Override
    public String getParameter(String name) {
        String[] values = parameters.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameter，参数从当前类中的map获取
     *
     * @param name key
     * @return value
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameters.get(name);
    }
}
