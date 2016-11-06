package berkantkz.checkrt;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Configuration;

import java.io.File;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static boolean findBinary(String binaryName) {
        boolean found = false;
        if (!found) {
            String[] places = {"/sbin/", "/system/bin/", "/system/xbin/",
                    "/data/local/xbin/", "/data/local/bin/",
                    "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
            for (String where : places) {
                if (new File(where + binaryName).exists()) {
                    found = true;

                    break;
                }
            }
        }
        return found;
    }

    private static boolean isRooted() {
        return findBinary("su");
    }

    private TextView logobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logobutton = (TextView) findViewById(R.id.howto);
        logobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this);
                builder.setTitle(R.string.howto);
                builder.setMessage(R.string.howtodescribe);
                builder.setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                return;
                            }
                        });
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/root"));
                                startActivity(browserIntent);
                            }
                        });
                builder.show();
            }
        });
    }

    public void checkbutton(View view) throws InterruptedException {
        if (isRooted() == true) {
            Intent intent = new Intent(this, rooted.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, notrooted.class);
            startActivity(intent);
        }
    }

    public void about(View view) throws InterruptedException {

        Intent intent = new Intent(this, about.class);
        startActivity(intent);
    }

    /*
    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    */

    public void exit(View view) throws InterruptedException {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        else { Toast.makeText(getBaseContext(), R.string.onceagainexit, Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

}
