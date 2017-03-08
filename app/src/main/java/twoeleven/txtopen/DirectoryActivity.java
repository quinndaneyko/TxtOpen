package twoeleven.txtopen;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class DirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        final String path = getIntent().getStringExtra("PATH_ID");

        File f = new File(path);
        File files[] = f.listFiles();

        String stringFiles[] = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            stringFiles[i] = files[i].getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringFiles);
        final ListView lv = (ListView) findViewById(R.id.file_list);
        lv.setAdapter(adapter);

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                System.out.println(o);
                File f = new File(path + "/" + o);
                if (f.isDirectory()) {
                    Intent i = new Intent(getBaseContext(), DirectoryActivity.class);
                    i.putExtra("PATH_ID", path + "/" + o);
                    startActivity(i);
                }
                else if (f.getName().substring(f.getName().length() - 3).equals("txt")) {
                    Intent i = new Intent(getBaseContext(), TextActivity.class);
                    i.putExtra("TEXT_PATH_ID", path + "/" + o);
                    startActivity(i);
                }
            }
        });

    }
}
