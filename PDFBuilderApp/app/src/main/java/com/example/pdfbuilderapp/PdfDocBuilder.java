package com.example.pdfbuilderapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfDocBuilder {
    /**
     * Global Variables of PdfBuilder Class
     *
     * @param bitmapArrayList have the list of bitmap images
     * to be added in the PDF-Document.
     * @param pageArrayList have the list of page contents
     * to be added in the PDF-Document.
     */
    private ArrayList<Bitmap> bitmapArrayList;
    private ArrayList<PdfDocument.Page> pageArrayList;

    /**
     * PdfDocBuilder default Constructor
     * where we initially set the Global Variables
     * to null, i.e: pageArrayList = bitmapArrayList = null;
     */
    public PdfDocBuilder() {
        this.bitmapArrayList = null;
        this.pageArrayList = null;
    }

    /**
     * Parameterized Constructor with
     * @param objBitmap to initialize bitmapArrayList variable.
     * @param objPage   to initialize pageArrayList variable.
     */
    public PdfDocBuilder(ArrayList<Bitmap> objBitmap, ArrayList<PdfDocument.Page> objPage) {
        this.bitmapArrayList = objBitmap;
        this.pageArrayList = objPage;
    }

    public void addBitmap(Bitmap bitmap) {
        if (bitmap == null)
            return;
        if (bitmapArrayList == null) {
            bitmapArrayList = new ArrayList<>();
            bitmapArrayList.add(bitmap);
        } else {
            bitmapArrayList.add(bitmap);
        }
    }

    public void addPage(PdfDocument.Page page) {
        if (page == null) {
            return;
        }
        if (pageArrayList == null) {
            pageArrayList = new ArrayList<>();
            pageArrayList.add(page);
        } else {
            pageArrayList.add(page);
        }
    }

    public int getBitmapNumber() {
        return bitmapArrayList.size();
    }

    public int getPageNumber() {
        return this.pageArrayList.size();
    }

    public static void createPDF(Context context, Bitmap bitmap) {
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        DisplayMetrics displayMetrics = new DisplayMetrics();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void createPDF(Context context, String someText) {
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(50, 50, 30, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(someText, 80, 50, paint);
        //canvas.drawt
        // finish the page
        document.finishPage(page);
// draw text on the graphics object of the page
        // Create Page 2
        pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 100, paint);
        document.finishPage(page);
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/myPdf/";
//        String directory_path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(directory_path);
        if (!file.exists()) {
            final boolean mkdirs = file.mkdirs();
            if (mkdirs)
                Toast.makeText(context.getApplicationContext(), "Folder Created", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context.getApplicationContext(), "Folder Not Created", Toast.LENGTH_SHORT).show();
        }
        String targetPdf = directory_path + "test.pdf";
//        String targetPdf = directory_path + "/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(context.getApplicationContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdfSameMethod(Context context, Bitmap bitmap) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

//        float hight = displaymetrics.heightPixels ;
//        float width = displaymetrics.widthPixels ;

//        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);


        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);


        // write the document content
        String targetPdf = Environment.getExternalStorageDirectory().getPath() + "/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
//            btn_convert.setText("Check PDF");
//            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void createPDFBitmap(Context context, Bitmap bitmap) {
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
// draw text on the graphics object of the page
        Canvas canvas = page.getCanvas();
        Bitmap resizedBitmap = ResizeImageView.resizeBitmap(bitmap,300,600,true);
        canvas.drawBitmap(resizedBitmap,0,0,null);
        //canvas.drawt
        // finish the page
        document.finishPage(page);

        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/myPdf/";
//        String directory_path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(directory_path);
        if (!file.exists()) {
            final boolean mkdirs = file.mkdirs();
            if (mkdirs)
                Toast.makeText(context.getApplicationContext(), "Folder Created", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context.getApplicationContext(), "Folder Not Created", Toast.LENGTH_SHORT).show();
        }
        String targetPdf = directory_path + "test.pdf";
//        String targetPdf = directory_path + "/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(context.getApplicationContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void createPDFBitmapArray(Context context, ArrayList<Bitmap> objBitmapList, String description) {
        if (objBitmapList == null || objBitmapList.isEmpty()){
            return;
        }

        // create a new document
        final PdfDocument document = new PdfDocument();
        for(int i=0; i<objBitmapList.size();++i){
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, i+1).create();
            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);
// draw text on the graphics object of the page
            Canvas canvas = page.getCanvas();
            Bitmap resizedBitmap = ResizeImageView.resizeBitmap(objBitmapList.get(i), 300, 600, true);
            canvas.drawBitmap(resizedBitmap, 0, 0, null);
            //canvas.drawt
            // finish the page
            document.finishPage(page);
        }

        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/myPdf/";
//        String directory_path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(directory_path);
        if (!file.exists()) {
            final boolean mkdirs = file.mkdirs();
            if (mkdirs)
                Toast.makeText(context.getApplicationContext(), "Folder Created", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context.getApplicationContext(), "Folder Not Created", Toast.LENGTH_SHORT).show();
        }
        String targetPdf = directory_path + "test.pdf";
//        String targetPdf = directory_path + "/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(context.getApplicationContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }

}
