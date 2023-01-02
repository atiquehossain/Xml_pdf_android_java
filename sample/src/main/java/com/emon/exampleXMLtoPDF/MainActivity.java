package com.emon.exampleXMLtoPDF;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

public class MainActivity extends AppCompatActivity {
    Button btnPrint;

    private PdfGenerator.XmlToPDFLifecycleObserver xmlToPDFLifecycleObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrint = findViewById(R.id.bt_print);



        xmlToPDFLifecycleObserver = new PdfGenerator.XmlToPDFLifecycleObserver(this);
        getLifecycle().addObserver(xmlToPDFLifecycleObserver);


        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PdfGenerator.getBuilder()
                        .setContext(MainActivity.this)
                        .fromViewIDSource()
                        .fromViewID(MainActivity.this, R.id.tv_print_area)
                        .setFileName("test")
                        .actionAfterPDFGeneration(PdfGenerator.ActionAfterPDFGeneration.SHARE)
                        .savePDFSharedStorage(xmlToPDFLifecycleObserver)
                        .build(new PdfGeneratorListener() {
                            @Override
                            public void onFailure(FailureResponse failureResponse) {
                                super.onFailure(failureResponse);
                            }

                            @Override
                            public void onStartPDFGeneration() {

                            }

                            @Override
                            public void onFinishPDFGeneration() {

                            }

                            @Override
                            public void showLog(String log) {
                                super.showLog(log);
                                Log.d("PDF-generation",log);
                            }

                            @Override
                            public void onSuccess(SuccessResponse response) {
                                super.onSuccess(response);
                            }
                        });
            }
        });



    }


}
