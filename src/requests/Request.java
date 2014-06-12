package requests;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.GsonBuilder;

public class Request extends AsyncTask<Map, Void, Boolean> {

	private static String LOCAL_DNS_IP = "10.0.2.2:8080/ManageTime";
	private static String PRODUCTION_DNS_IP = "http://warm-spire-3935.herokuapp.com";
	
	private static String URL_POST = "http://" + PRODUCTION_DNS_IP + "/post/atividade";
	
	@Override
	public Boolean doInBackground(Map... params) {
		executeRequest(params);
		return true;
	}

	public static void executeRequest(Map[] params) {
	    for (Map atividadeMap : params) {
	    	String json = new GsonBuilder().create().toJson(atividadeMap, Map.class);
	    	makeRequest(URL_POST, json);
		}
		
		
	}

	public static HttpResponse makeRequest(String uri, String json) {
	    try {
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.setEntity(new StringEntity(json));
	        httpPost.setHeader("Accept", "application/json");
	        httpPost.setHeader("Content-type", "application/json");
	        return new DefaultHttpClient().execute(httpPost);
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
