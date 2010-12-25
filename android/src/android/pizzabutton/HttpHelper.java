package android.pizzabutton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpHelper {
	/**
	 *
	 * @param url 	the url to execute the post to
	 * @param nameValuePairs	the parameters of the post
	 * @return		the text of the response from the call, or the empty
	 * 					string if failure
	 */
	public static String doHttpPost(String url, List<NameValuePair> nameValuePairs) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);

	    try {
	      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	      HttpResponse response = httpclient.execute(httppost);

	      return getHttpResponseText(response);
	    } catch (ClientProtocolException e) {
	      // TODO
	    } catch (IOException e) {
	      // TODO
	    }
	    return "";
	  }

	  public static String doHttpGet(String url, List<NameValuePair> nameValuePairs) {
	    HttpClient httpclient = new DefaultHttpClient();

	    StringBuilder uriBuilder = new StringBuilder();
	    uriBuilder.append(url);
	    if (null != nameValuePairs) {
		    uriBuilder.append("?");
		    for (NameValuePair pair : nameValuePairs) {
		      Log.v("test", pair.getValue() + "X");
		      uriBuilder.append(pair.getName() + "=" + pair.getValue() + "&");
		    }
	    }

	    Log.v("test", uriBuilder.toString());

	    HttpGet request = new HttpGet(uriBuilder.toString());
	    try {
	      HttpResponse response = httpclient.execute(request);
	      return getHttpResponseText(response);
	    } catch (ClientProtocolException e) {
	    	Log.v("test", "Error"+e);
	      // TODO if I fell like it
	    } catch (IOException e) {
	    	Log.v("test", "Error"+e);
	      // TODO if I feel like it
	    }

	    return "";
	  }

	  public static String getHttpResponseText(HttpResponse response) {
	    try {
	      InputStream in = response.getEntity().getContent();
	      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	      StringBuilder str = new StringBuilder();
	      String line = null;
	      while((line = reader.readLine()) != null) {
	        str.append(line + "\n");
	      }
	      in.close();
	      return str.toString();
	    } catch (IOException e){
	      // TODO if I feel like it
	    }
	    return "";
	  }
}
