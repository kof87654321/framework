package com.zl.common.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP工具类-支持HTTPS
 * 
 * @author zhangxianjun
 * @version $Id: HttpClient.java, v 0.1 2015年6月15日 下午7:48:07 zhangxianjun Exp $
 */
public class HttpClient {
    private final static Logger                       logger      = LoggerFactory.getLogger(HttpClient.class);

    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient                httpclient  = null;

    static {
        try {
            SSLContext sslContext = SSLContexts.custom().useTLS().build();
            sslContext.init(null, new TrustManager[] { new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } }, null);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext)).build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200)
                .setMaxLineLength(2000).build();
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8).setMessageConstraints(messageConstraints).build();
            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(200);
            connManager.setDefaultMaxPerRoute(20);
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        }
    }

    public static String postJsonBody(String url, int timeout, String content, String encoding) {
        return postJsonBody(url, timeout, null, content, encoding);
    }

    public static String postJsonBody(String url, int timeout, Map<String, String> headMap, String content,
                                      String encoding) {
        HttpPost post = new HttpPost(url);
        try {
            if (headMap != null && !headMap.isEmpty()) {
                Iterator<String> it = headMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    post.setHeader(key, headMap.get(key));
                }
            } else {
                post.setHeader("Content-type", "application/json");
            }

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);

            post.setEntity(new StringEntity(content, encoding));
            logger.info("[HttpUtils Post] begin invoke url:" + url + " , params:" + content);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        String str = EntityUtils.toString(entity, encoding);
                        logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :" + str);
                        return str;
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    public static String get(String url, String encode) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout)
            .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        logger.info("[HttpUtils Get] begin invoke:" + sb.toString());
        HttpGet get = new HttpGet(sb.toString());
        get.setConfig(requestConfig);

        try {
            CloseableHttpResponse response = httpclient.execute(get);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if (entity != null) {
                        responseString = EntityUtils.toString(entity, encode);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error(String.format("[HttpUtils Get]get response error, url:%s", sb.toString()), e);
                return responseString;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info(String.format("[HttpUtils Get]Debug url:%s , response string %s:", sb.toString(),
                responseString));
        } catch (SocketTimeoutException e) {
            logger.error(String.format("[HttpUtils Get]invoke get timout error, url:%s", sb.toString()), e);
            return responseString;
        } catch (Exception e) {
            logger.error(String.format("[HttpUtils Get]invoke get error, url:%s", sb.toString()), e);
        } finally {
            get.releaseConnection();
        }
        return responseString;
    }

    public final static int connectTimeout = 10000;

    /**
     * HTTPS请求，默认超时为5S
     * @param reqURL
     * @param params
     * @return
     */
    public static String connectPostHttps(String reqURL, Map<String, String> params) {

        String responseContent = null;

        HttpPost httpPost = new HttpPost(reqURL);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout)
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();

            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            // 绑定到请求 Entry
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, Consts.UTF_8));
            httpPost.setConfig(requestConfig);
            // 绑定到请求 Entry
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 执行POST请求
                HttpEntity entity = response.getEntity(); // 获取响应实体
                try {
                    if (null != entity) {
                        responseContent = EntityUtils.toString(entity, Consts.UTF_8);
                    }
                } finally {
                    if (entity != null) {
                        entity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info("requestURI : " + httpPost.getURI() + ", responseContent: " + responseContent);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            httpPost.releaseConnection();
        }
        return responseContent;

    }
}
