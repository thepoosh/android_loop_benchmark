package me.glide.benchmarks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TEST_ARRAY_SIZE = 100000;
    TextView mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = (TextView) findViewById(R.id.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        testIndexedFor();
        System.gc();
        testForEach();
        System.gc();
        testWithException();
    }

    private void testWithException() {
        mData.setText(mData.getText() + "\n\ntesting with exception");
        int[] data = new int[TEST_ARRAY_SIZE];
        final long now = System.nanoTime();
        try {
            for (int i = 0; ; i++) {
                data[i] = i;
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            mData.setText(mData.getText() + "\nthis took " + (System.nanoTime() - now) + " nano seconds");
        }
    }

    private void testForEach() {
        mData.setText(mData.getText() + "\n\ntesting regular for without index");
        int[] data = new int[TEST_ARRAY_SIZE];
        final long now = System.nanoTime();
        for(int i : data) {
            data[i] = i++;
        }
        mData.setText(mData.getText() + "\nthis took " + (System.nanoTime() - now) + " nano seconds");
    }

    private void testIndexedFor() {
        mData.setText("testing regular for with index");
        int[] data = new int[TEST_ARRAY_SIZE];
        final long now = System.nanoTime();
        for(int i = 0; i < TEST_ARRAY_SIZE; i++) {
          data[i] = i;
        }
        mData.setText(mData.getText() + "\nthis took " + (System.nanoTime() - now) + " nano seconds");
    }

}


