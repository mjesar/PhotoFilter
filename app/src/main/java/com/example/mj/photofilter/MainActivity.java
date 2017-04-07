package com.example.mj.photofilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    ImageView myImageView;
    Bitmap bitmap;
    Drawable drawable;
    int RED;
    int GREEN;
    int BLUE;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    Bitmap newPhoto = null;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SEEK BAR THAT CHANGE RGB COLOR OF PHOTO
        myImageView = (ImageView) findViewById(R.id.myImageView);


        myImageView.setImageBitmap(newPhoto);
        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        redSeekBar.setProgress(0);
        redSeekBar.setMax(255);
        greenSeekBar.setProgress(0);
        greenSeekBar.setMax(255);
        blueSeekBar.setProgress(0);
        blueSeekBar.setMax(255);
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);

        //RADIO BUTTONS THAT CHANGE IMAGE FILTERS


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Drawable[] layers;
        LayerDrawable layerDrawable;
        switch (checkedId) {

            case R.id.filterOne:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);

                break;

            case R.id.filterTwo:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filtertwo);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);

                break;
            case R.id.filterThree:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.antique01);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);

                break;
            case R.id.filterFour:

                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.antique02);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);
                break;
            case R.id.filterSix:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter3);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);
                break;
            case R.id.filterSeven:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter4);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);
                break;
            case R.id.filterEight:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter5);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);

                break;
            case R.id.filterNine:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter6);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);


                break;
            case R.id.filterTen:
                layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), newPhoto);
                layers[1] = getResources().getDrawable(R.drawable.filter7);
                layerDrawable = new LayerDrawable(layers);
                myImageView.setImageDrawable(layerDrawable);
                break;


        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        switch (seekBar.getId()) {
            case R.id.redSeekBar:
                RED = progress;
                break;
            case R.id.greenSeekBar:
                GREEN = progress;
                break;
            case R.id.blueSeekBar:
                BLUE = progress;
                break;
        }
        drawable =new BitmapDrawable(getResources(), newPhoto);
        bitmap = ((BitmapDrawable) drawable).getBitmap();
        newPhoto = invertImage(bitmap);
        myImageView.setImageBitmap(newPhoto);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_openimage:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", true);
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 1);
                return true;

            case R.id.action_saveimage:
                File myDir=new File("/sdcard/saved_images");
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Image-"+ n +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    newPhoto.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                newPhoto = extras.getParcelable("data");
                myImageView.setImageBitmap(newPhoto);

            }else {
                Bitmap newPhoto = extras.getParcelable("data");
                myImageView.setImageBitmap(newPhoto);
            }
        }


    }

    public Bitmap invertImage(Bitmap orignal) {

        Bitmap finalImage = Bitmap.createBitmap(orignal.getWidth(), orignal.getHeight(), orignal.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = orignal.getHeight();
        int width = orignal.getWidth();


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = orignal.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = RED + Color.red(pixelColor);
                G = GREEN + Color.green(pixelColor);
                B = BLUE + Color.blue(pixelColor);
                finalImage.setPixel(x, y, Color.argb(A, R, G, B));


            }
        }

        return finalImage;

    }


}
