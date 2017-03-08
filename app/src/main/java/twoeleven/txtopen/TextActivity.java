package twoeleven.txtopen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        final String path = getIntent().getStringExtra("TEXT_PATH_ID");
        File f = new File(path);

        StringBuilder text = new StringBuilder();
        // From http://stackoverflow.com/questions/12421814/how-can-i-read-a-text-file-in-android
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

//Find the view by its id
        TextView tv = (TextView)findViewById(R.id.textView);

//Set the text
        tv.setText(text.toString());
    }
}
