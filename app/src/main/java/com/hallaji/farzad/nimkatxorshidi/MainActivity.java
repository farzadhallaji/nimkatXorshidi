package com.hallaji.farzad.nimkatxorshidi;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    //    SlideShowView slideShowView;
//    private String[] FilePathStrings;
//    private String[] FileNameStrings;
//    private File[] listFile;
//    File file;
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PRDownloader.initialize(getApplicationContext());


        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

//        TextView textView = findViewById(R.id.asadasad);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext() , MenuActivity.class);
//                startActivity(intent);
//            }
//        });
//        //
////        // Go fullscreen
////        requestWindowFeature(Window.FEATURE_NO_TITLE);
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
////
////
////        slideShowView = (SlideShowView) findViewById(R.id.slideshow);
////        ArrayList<String> paths= new ArrayList<>();
//////
//////        adapter = new GenericResourceBitmapAdapter(this ,  paths) {
//////            @Override
//////            protected int getItemImageResourceId(Object item, int position) {
//////                return 0;
//////            }
//////        };
////
////      //  paths = ReadImageFilesFromSDCard();
////
////
////
////        //////////////////////////////////////////////////////////////////
////
////
////
////
////        // Check for SD Card
////        if (!Environment.getExternalStorageState().equals(
////                Environment.MEDIA_MOUNTED)) {
////            Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
////                    .show();
////        } else {
////            // Locate the image folder in your SD Card
////            file = new File(Environment.getExternalStorageDirectory()
////                    + File.separator + "Data/Media/Images");
////            // Create a new folder if no folder named SDImageTutorial exist
////            file.mkdirs();
////        }
////
////        if (file.isDirectory()) {
////            Toast.makeText(this, "Data/Media/Images", Toast.LENGTH_LONG).show();
////
////            listFile = file.listFiles();
////            // Create a String array for FilePathStrings
////            FilePathStrings = new String[listFile.length];
////            // Create a String array for FileNameStrings
////            FileNameStrings = new String[listFile.length];
////
////            for (int i = 0; i < listFile.length; i++) {
////                // Get the path of the image file
////                FilePathStrings[i] = listFile[i].getAbsolutePath();
////                // Get the name image file
////                FileNameStrings[i] = listFile[i].getName();
////            }
////        }
////
////
////    }
////
//////    private ArrayList<String> ReadImageFilesFromSDCard() {
//////        ArrayList<String> paths = new ArrayList<>();
//////
//////
//////
//////
//////
//////        return paths;
//////
        ViewFlipper viewFlipper = findViewById(R.id.image_flipper);
        try{
            fun(viewFlipper);
        }catch (Exception e){}

        //Todo prior 2 : send id

//        HttpRequest request = HttpRequest.get("http://eninocontrol.ir/api/whatsupdoc", true, "id", "id52");
//        System.out.println("satre 129 : >>>>>>>>>>>>>>>   "); // GET http://google.com?id[]=22&id[]=23
//        System.out.println(request.()); // GET http://google.com?id[]=22&id[]=23


//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while(true) {
//                    try {
//                        Thread.sleep(1000*60*20);
//                        //your code here...
//
//
//                    } catch (InterruptedException ie) {
//                    }
//                }
//            }
//        };
//        t.start();
//        Timer timer = new Timer ();
//        TimerTask hourlyTask = new TimerTask () {
//            @Override
//            public void run () {
//
//            }
//        };
//
//        timer.schedule (hourlyTask, 0, 1000*60*10);


        //Log.i("address", Environment.getExternalStorageDirectory().getPath());

    }

    public void fun(final ViewFlipper viewFlipper) {

        viewFlipper.removeAllViews();

        File fileImages = new File(Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Images/");
        File fileVideos = new File(Environment.getExternalStorageDirectory().toString() + "/Nimkat/Media/Videos/");
        fileImages.mkdirs();
        fileVideos.mkdirs();

        File[] filesVideo = fileVideos.listFiles();
        File[] filesImage = fileImages.listFiles();

        System.out.println("asasasewrferftgtyhtyh   :   "+filesImage.length);


//        if(filesImage.length>0){
        for (File f : filesImage) {
            ImageView image = new ImageView(getApplicationContext());
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bmOptions);
            image.setImageBitmap(bitmap);
            viewFlipper.addView(image);
        }
//        }

        viewFlipper.setFlipInterval(5000);


        for (final File f : filesVideo) {
            final VideoView video = new VideoView(getApplicationContext());
            video.setVideoPath(f.getPath());
            viewFlipper.addView(video);
            final ViewFlipper finalViewFlipper = viewFlipper;
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    finalViewFlipper.stopFlipping();
                    video.start();
                }
            });

            final ViewFlipper finalViewFlipper1 = viewFlipper;
            final File[] finalFilesVideo = filesVideo;
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(f.equals(finalFilesVideo[finalFilesVideo.length-1])){
                        UpdateFiles(viewFlipper);
                        fun(viewFlipper);
                    }
                    else{
                        video.stopPlayback();
                        finalViewFlipper1.startFlipping();
                    }
                }
            });
        }

        viewFlipper.startFlipping();


        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"assdkmvdlkfjvnkdjfvnkdfjvndfvkdfvj" , Toast.LENGTH_LONG).show();
            }
        });


    }

//    private void UpdateFiles() {
//
//
//        File fileImages = new File(Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Images/");
//        File fileVideos = new File(Environment.getExternalStorageDirectory().toString() + "/Nimkat/Media/Videos/");
//        fileImages.mkdirs();
//        fileVideos.mkdirs();
//
//
//
//        final RequestParams params = new RequestParams();
//        params.put("id","id544");
//        client.post("http://eninocontrol.ir/api/whatsupmedia", params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
//                System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());
//                for(int i= 0 ; i < responseBody.length() ; i++){
//                    try {
//                        JSONObject jsonObject = responseBody.getJSONObject(i);
//                        if(jsonObject.getString("file_type").contains("image"))
//                            PRDownloader.download("http://eninocontrol.ir"+jsonObject.getString("route"),
//                                    Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Images/"
//                                    ,jsonObject.getString("name")).build().start(new OnDownloadListener() {
//                                @Override
//                                public void onDownloadComplete() {
//
//                                }
//
//                                @Override
//                                public void onError(Error error) {
//
//                                }
//                            });
//                        if(jsonObject.getString("file_type").contains("video"))
//                            PRDownloader.download("http://eninocontrol.ir"+jsonObject.getString("route"),
//                                    Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Videos/"
//                                    ,jsonObject.getString("name")).build().start(new OnDownloadListener() {
//                                @Override
//                                public void onDownloadComplete() {
//
//                                }
//
//                                @Override
//                                public void onError(Error error) {
//
//                                }
//                            });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        });
//    }


    private void UpdateFiles(final ViewFlipper viewFlipper) {


        File fileImages = new File(Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Images/");
        File fileVideos = new File(Environment.getExternalStorageDirectory().toString() + "/Nimkat/Media/Videos/");
        fileImages.mkdirs();
        fileVideos.mkdirs();
        final File[] filesVideo = fileVideos.listFiles();
        final File[] filesImage = fileImages.listFiles();

        final ArrayList<String> serverFiles=new ArrayList<>();

        final RequestParams params = new RequestParams();
        params.put("id","id544");
        client.post("http://eninocontrol.ir/api/whatsupmedia", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
                System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());

                //Delete not in server files
                for(int i= 0 ; i < responseBody.length() ; i++) {
                    try {
                        serverFiles.add(responseBody.getJSONObject(i).getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                for (int i=0 ; i < filesVideo.length ; i++){
                    if(!serverFiles.contains(filesVideo[i].getName())){
                        final VideoView video = new VideoView(getApplicationContext());
                        video.setVideoPath(filesVideo[i].getPath());
                        viewFlipper.removeView(video);
                        filesVideo[i].delete();
                    }

                }
                for (int i=0 ; i < filesImage.length ; i++){
                    if(!serverFiles.contains(filesImage[i].getName())){
                        ImageView image = new ImageView(getApplicationContext());
                        image.setScaleType(ImageView.ScaleType.FIT_XY);
                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                        Bitmap bitmap = BitmapFactory.decodeFile(filesImage[i].getAbsolutePath(), bmOptions);
                        image.setImageBitmap(bitmap);
                        viewFlipper.removeView(image);
                        filesImage[i].delete();

                    }
                }

                //Download new files
                for(int i= 0 ; i < responseBody.length() ; i++){
                    try {
                        final JSONObject jsonObject = responseBody.getJSONObject(i);
                        if(jsonObject.getString("file_type").contains("image")) {
                            if(! new File(Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Images/"+jsonObject.getString("name")).exists())
                                PRDownloader.download("http://eninocontrol.ir" + jsonObject.getString("route"),
                                        Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Images/"
                                        , jsonObject.getString("name")).build().start(new OnDownloadListener() {
                                    @Override
                                    public void onDownloadComplete() {
                                        try {
                                            System.out.println("asasasewrferftgtyhtyh :  Download  "+jsonObject.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(Error error) {

                                    }
                                });
                        }
                        if(jsonObject.getString("file_type").contains("video")) {
                            if(! new File(Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Videos/"+jsonObject.getString("name")).exists())
                                PRDownloader.download("http://eninocontrol.ir" + jsonObject.getString("route"),
                                        Environment.getExternalStorageDirectory().getPath() + "/Nimkat/Media/Videos/"
                                        , jsonObject.getString("name")).build().start(new OnDownloadListener() {
                                    @Override
                                    public void onDownloadComplete() {
                                        try {
                                            System.out.println("asasasewrferftgtyhtyh :  Download  "+jsonObject.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(Error error) {

                                    }
                                });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

}
























































//
//
//
//        //File Nimkat = new File(Environment.getExternalStorageDirectory().getPath()+"/Nimkat");
//        try {
//            File fileImages = new File(Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Images/");
//            File fileVideos = new File(Environment.getExternalStorageDirectory().toString() +"/Nimkat/Media/Videos/");
//            //File fileVideos = new File("/storage/emulated/0/Nimkat/Media/Videos/");
//            fileImages.mkdirs();
//            fileVideos.mkdirs();
//            //Log.i("length1  : " ,fileVideos.exists() +"");
//            //ArrayList<File> filesVideo= null;
//
//
//            File[] filesVideo = null;
//            try{
//                filesVideo = fileVideos.listFiles();
//                Log.i("length2  : " ,"a"+ filesVideo[0].getName());
//
//            }catch (Exception e){
//                Log.i("length2  : " ,"ascsdsdfvdfvdfv");
//
//            }
//            // TODO 2 it may include other type of file
//            File[] filesImage = fileImages.listFiles();
//
////        Toast.makeText(getApplicationContext() , "length" + filesImage.length,Toast.LENGTH_LONG).show();
//
//            if(filesImage.length>0){
//                for (File f : filesImage) {//or something like this
//                    ImageView image = new ImageView ( getApplicationContext() );
//                    //image.setImageBitmap( photo.getBitmap() );
//                    //Toast.makeText(this,f.getPath()+  "           tunes" , Toast.LENGTH_LONG).show();
//                    image.setScaleType(ImageView.ScaleType.FIT_XY);
//                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//                    Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),bmOptions);
//                    //bitmap = Bitmap.createScaledBitmap(bitmap,image.getRootView().getWidth(),image.getRootView().getHeight(),true);
//                    image.setImageBitmap(bitmap);
//                    viewFlipper.addView( image );
//                }
//            }
//            Log.i("asadafaddsdsdvs",Environment.getExternalStorageDirectory().getPath());
//
//            viewFlipper.setFlipInterval( 5000 ); //5s intervals
//
//
//            //TODO 1 empty File
//            System.out.println("asasasewrferghtyhtytyjyujyukuiks : "+fileVideos.list());
//
//            if(filesVideo.length>0) {
//                for (final File f : filesVideo) {
//                    final VideoView video = new VideoView ( getApplicationContext() );
//                    //image.setImageBitmap( photo.getBitmap() );
//                    //Toast.makeText(this,f.getPath()+  "           tunes" , Toast.LENGTH_LONG).show();
//
//                    video.setVideoPath(f.getPath());
//                    Log.i("asadafaddsdsdvs",f.getPath());
//
//
//                    viewFlipper.addView( video );
//                    final ViewFlipper finalViewFlipper = viewFlipper;
//                    video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            finalViewFlipper.stopFlipping();
//                            video.start();
//                        }
//                    });
//                    final ViewFlipper finalViewFlipper1 = viewFlipper;
//                    final File[] finalFilesVideo = filesVideo;
//                    video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mediaPlayer) {
//                            if(f.equals(finalFilesVideo[finalFilesVideo.length-1])){
//                                final ArrayList<String> filenames = null;
//                                final ArrayList<String> filedates = null;
//                                ArrayList<String> filenamesold = null;
//                                ArrayList<String> filedatesold = null;
//                                //update views
//                                final RequestParams params = new RequestParams();
//                                params.put("id","id544");
//                                client.post("http://eninocontrol.ir/api/whatsupdoc", params, new JsonHttpResponseHandler() {
//                                    @Override
//                                    public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
//                                        System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());
//
////                                        SharedPreferences preferences = getSharedPreferences("DATABASE FILES", MODE_PRIVATE);
////
////                                        String strnames = preferences.getString("arrayesnames","");
////                                        String strdates = preferences.getString("arrayesdates","");
////                                                (strnames.substring(1, strnames.length() - 1).replaceAll("\"", "").split(",")));
////                                        ArrayList<String> myList = new ArrayList<String>(Arrays.asList
////                                                (str.substring(1, str.length() - 1).replaceAll("\"", "").split(",")));
//                                        for(int i= 0 ; i < responseBody.length() ; i++){
//                                            try {
//
//                                                JSONObject jsonObject = responseBody.getJSONObject(i);
//                                                final String pp =Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Images/"+jsonObject.getString("name");
//                                                System.out.println("wertyukjhgfcasdnngnh :      "+"http://eninocontrol.ir"+jsonObject.getString("route"));
//                                                PRDownloader.download("http://eninocontrol.ir"+jsonObject.getString("route"),
//                                                        Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Images/"
//                                                        ,jsonObject.getString("name")).build().start(new OnDownloadListener() {
//                                                    @Override
//                                                    public void onDownloadComplete() {
//
//                                                        ImageView image = new ImageView ( getApplicationContext() );
//                                                        //image.setImageBitmap( photo.getBitmap() );
//                                                        //Toast.makeText(this,f.getPath()+  "           tunes" , Toast.LENGTH_LONG).show();
//                                                        image.setScaleType(ImageView.ScaleType.FIT_XY);
//                                                        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//                                                        Bitmap bitmap = BitmapFactory.decodeFile(pp,bmOptions);
//                                                        //bitmap = Bitmap.createScaledBitmap(bitmap,image.getRootView().getWidth(),image.getRootView().getHeight(),true);
//                                                        image.setImageBitmap(bitmap);
//                                                        viewFlipper.addView( image );
//
//                                                    }
//
//                                                    @Override
//                                                    public void onError(Error error) {
//
//                                                    }
//                                                });
////                                                filenames.add(jsonObject.getString("name"));
////                                                filedates.add(jsonObject.getString("created_at"));
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//
////                                        SharedPreferences.Editor editor = getSharedPreferences("DATABASE FILES", MODE_PRIVATE).edit();
////
////                                        editor.putString("arrayesnames", filenames.toString());
////                                        editor.putString("arrayesdates", filedates.toString());
////                                        editor.apply();
//                                    }
//
//                                });
//
//                                client.post("http://eninocontrol.ir/api/whatsupmedia", params, new JsonHttpResponseHandler() {
//                                    @Override
//                                    public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
//                                        System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());
//                                        for(int i= 0 ; i < responseBody.length() ; i++){
//                                            try {
//                                                JSONObject jsonObject = responseBody.getJSONObject(i);
////                                                PRDownloader.download(jsonObject.getString("route"),
////                                                        Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Videos/"
////                                                        ,jsonObject.getString("name"));
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }
//
//                                });
//
//                                fun(viewFlipper);
//                            }
//                            else{
//                                video.stopPlayback();
//                                finalViewFlipper1.startFlipping();
//                            }
//                        }
//                    });
//                }
//            }else {
//                final RequestParams params = new RequestParams();
//                params.put("id","id544");
//                client.post("http://eninocontrol.ir/api/whatsupdoc", params, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
//                        System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());
//                        for(int i= 0 ; i < responseBody.length() ; i++){
//                            try {
//                                JSONObject jsonObject = responseBody.getJSONObject(i);
//                                PRDownloader.download("http://eninocontrol.ir"+jsonObject.getString("route"),
//                                        Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Images/"
//                                        ,jsonObject.getString("name"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                });
//
//                client.post("http://eninocontrol.ir/api/whatsupmedia", params, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers,JSONArray responseBody) {
//                        System.out.println("asasasewrferftgtyhtyh"+responseBody.toString());
//                        for(int i= 0 ; i < responseBody.length() ; i++){
//                            try {
//                                JSONObject jsonObject = responseBody.getJSONObject(i);
////                                                PRDownloader.download(jsonObject.getString("route"),
////                                                        Environment.getExternalStorageDirectory().getPath() +"/Nimkat/Media/Videos/"
////                                                        ,jsonObject.getString("name"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                });
//            }
//
//
//
//            viewFlipper.startFlipping();
//
//
//
//            viewFlipper.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getApplicationContext() , MenuActivity.class);
//                    startActivity(intent);
//                    //Toast.makeText(getApplicationContext(),"assdkmvdlkfjvnkdjfvnkdfjvndfvkdfvj" , Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//        }catch (Exception e){
//
//            Toast.makeText(this,Environment.getExternalStorageDirectory().getPath() , Toast.LENGTH_LONG).show();
//
//
//            viewFlipper.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getApplicationContext() , MenuActivity.class);
//                    startActivity(intent);
//                    //Toast.makeText(getApplicationContext(),"assdkmvdlkfjvnkdjfvnkdfjvndfvkdfvj" , Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }
//
//    }
//}
