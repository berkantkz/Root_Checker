package berkantkz.checkrt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by berka on 24.08.2016.
 */
public class rooted extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooted);
    }

    public void back(View view) throws InterruptedException {
        finish();
    }

}
