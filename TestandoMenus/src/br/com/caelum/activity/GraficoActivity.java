package br.com.caelum.activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class GraficoActivity extends Activity {

	final static String urlGoogleChart = "http://chart.apis.google.com/chart";
	// final static String urlp3Api = "?cht=p3&chs=400x150&chl=A|B|C&chd=t:";
	final static String urlp3Api = "?cht=p3&chs=400x150&chl=A|B|C&chd=t:2,3,4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ImageView grafico = (ImageView) findViewById(R.id.grafico);

		InputStream is = null;

		try {
			HttpURLConnection httpConn = (HttpURLConnection) new URL(
					urlGoogleChart + urlp3Api).openConnection();
			httpConn.setRequestMethod("GET");

			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				is = httpConn.getInputStream();
				Bitmap imagem = BitmapFactory.decodeStream(is);
				is.close();

				grafico.setImageBitmap(imagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
