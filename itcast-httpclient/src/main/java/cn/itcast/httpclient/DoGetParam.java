package cn.itcast.httpclient;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DoGetParam {

	public static void main(String[] args) throws Exception {
		//创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//创建请求地址uri
		URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com/s");
		uriBuilder.setParameter("wd", "java");
		
		//输出地址
		System.out.println(uriBuilder.toString());
		
		//以一个uri作为构造参数创建httpGet对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3278.0 Safari/537.36");
		
		CloseableHttpResponse response = null;
		try {
			//利用httpClient执行httpGet请求
			response = httpClient.execute(httpGet);
			//处理结果
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content);
			}
		} finally {
			if(response != null){
				response.close();
			}
			httpClient.close();
		}

	}

}
