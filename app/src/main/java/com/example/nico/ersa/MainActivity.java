package com.example.nico.ersa;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;
        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONObject;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.RatingBar;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String Q1;
    String Q2;
    String Q4;
    String Q5;
    String Q6;
    String Q7;
    String Q8;
    String Q9;
    String Q10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RatingBar Q1_choice = (RatingBar) findViewById(R.id.ratingBar);
        final RadioGroup Q2_choice = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioGroup Q4_choice = (RadioGroup) findViewById(R.id.radioGroup2);
        final RadioGroup Q5_choice = (RadioGroup) findViewById(R.id.radioGroup3);
        final RadioGroup Q6_choice = (RadioGroup) findViewById(R.id.radioGroup4);
        final RadioGroup Q7_choice = (RadioGroup) findViewById(R.id.radioGroup5);
        final RadioGroup Q8_choice = (RadioGroup) findViewById(R.id.radioGroup6);
        final RadioGroup Q9_choice = (RadioGroup) findViewById(R.id.radioGroup7);

        final RadioButton Q2_checked = (RadioButton) findViewById(Q2_choice.getCheckedRadioButtonId());
        final RadioButton Q4_checked = (RadioButton) findViewById(Q4_choice.getCheckedRadioButtonId());
        final RadioButton Q5_checked = (RadioButton) findViewById(Q5_choice.getCheckedRadioButtonId());
        final RadioButton Q6_checked = (RadioButton) findViewById(Q6_choice.getCheckedRadioButtonId());
        final RadioButton Q7_checked = (RadioButton) findViewById(Q7_choice.getCheckedRadioButtonId());
        final RadioButton Q8_checked = (RadioButton) findViewById(Q8_choice.getCheckedRadioButtonId());
        final RadioButton Q9_checked = (RadioButton) findViewById(Q9_choice.getCheckedRadioButtonId());

        final EditText e_Q10=(EditText) findViewById(R.id.text);
        Button insert=(Button)findViewById(R.id.button3);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Q1 = Float.toString(Q1_choice.getRating());
                Q2 = Q2_checked.getText().toString();
                Q4 = Q4_checked.getText().toString();
                Q5 = Q5_checked.getText().toString();
                Q6 = Q6_checked.getText().toString();
                Q7 = Q7_checked.getText().toString();
                Q8 = Q8_checked.getText().toString();
                Q9 = Q9_checked.getText().toString();
                Q10 = e_Q10.getText().toString();

                insert();
            }
        });
    }

    public void insert()
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{
            @Override
            protected String doInBackground(String... params) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                //nameValuePairs.add(new BasicNameValuePair("id",id));
                nameValuePairs.add(new BasicNameValuePair("Q1",Q1));
                nameValuePairs.add(new BasicNameValuePair("Q2",Q2));
                nameValuePairs.add(new BasicNameValuePair("Q4",Q4));
                nameValuePairs.add(new BasicNameValuePair("Q5",Q5));
                nameValuePairs.add(new BasicNameValuePair("Q6",Q6));
                nameValuePairs.add(new BasicNameValuePair("Q7",Q7));
                nameValuePairs.add(new BasicNameValuePair("Q8",Q8));
                nameValuePairs.add(new BasicNameValuePair("Q9",Q9));
                nameValuePairs.add(new BasicNameValuePair("Q10",Q10));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("https://temerarious-fleet.000webhostapp.com/insert.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(MainActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(Q1, Q2, Q4, Q5, Q6, Q7, Q8, Q9, Q10);
    }
}
