package com.example.myapplication.lost;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mate.AloneActivity;
import com.example.myapplication.mate.ContestActivity;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.ServiceApi;
import com.example.myapplication.network.UploadResult;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LostWriteActivity extends AppCompatActivity {
    private EditText titleText;
    private TextView nicknameText;
    private EditText contentText;
    private TextView typeText;
    private TextView campusText;
    private RadioGroup campusgroup;
    private RadioGroup typeGroup;
    private ProgressBar mProgressView;
    private ServiceApi service;
    private final String NICKNAME_EXTRA = "NICKNAME_EXTRA";

    /* -- 이미지 업로드 부분임당(1) -- */
    private Button getImgBtn;
    private Button uploadImg;
    String getImgURL="";
    String getImgName="";
    String upLoadServerUri = "http://13.125.107.224/index.php";

    final int REQ_CODE_SELECT_IMAGE=100;
    ProgressDialog asyncDialog;
    int serverResponseCode = 0;
    /* -- 요기까지 -- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_write);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        titleText = (EditText) findViewById(R.id.title);
        String nicknameText = getIntent().getStringExtra("NICKNAME_EXTRA");
        contentText = (EditText) findViewById(R.id.content);
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        typeGroup = (RadioGroup) findViewById(R.id.typeGroup);
        campusgroup = (RadioGroup) findViewById(R.id.campusgroup);
        campusgroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        typeGroup = (RadioGroup) findViewById(R.id.typeGroup);
        typeGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        mProgressView = (ProgressBar) findViewById(R.id.progressBar2);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        ImageButton BackButton = (ImageButton) findViewById(R.id.imageButton1);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button excuteButton = (Button) findViewById(R.id.excute);
        excuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                attemptWrite();
            }
        });

        /* -- 이미지 업로드 부분임당(2) -- */
        getImgBtn = (Button)findViewById(R.id.getImg);
        getImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사진 갤러리 호출
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        uploadImg = (Button)findViewById(R.id.uploadImg);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),getImgURL,Toast.LENGTH_SHORT).show();
                /**
                 * getImgBtn 버튼 클릭을 통해, 업로드할 사진의 절대경로를 가져옴
                 * 서버로 보내는 시간을 고려하여 진행바를 넣어줌
                 */

                asyncDialog = new ProgressDialog(LostWriteActivity.this);
                asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                asyncDialog.setMessage("로딩중입니다..");

                // show dialog
                asyncDialog.show();

                uploadFile2(getImgURL);

                //uploadFile(getImgURL , getImgName);
                //DoFileUpload(serverURL, getImgURL);


            }
        });
        /* -- 요기까지 -- */
    }

    /* -- 이미지 업로드 부분임당(3) -- */
    public int uploadFile2(String sourceFileUri) {
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {
            asyncDialog.dismiss();
            Log.e("uploadFile", "Source File not exist :"
                    +getImgURL);

            runOnUiThread(new Runnable() {
                public void run() {
                }
            });
            return 0;
        }
        else
        {
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LostWriteActivity.this, "File Upload Complete.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {
                asyncDialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LostWriteActivity.this, "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                asyncDialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LostWriteActivity.this, "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upld to server Excption", "Exception : "
                        + e.getMessage(), e);
            }
            asyncDialog.dismiss();
            return serverResponseCode;
        } // End else block

    }


    // 선택된 이미지 가져오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());

                    Log.i("myTag", name_Str);

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView) findViewById(R.id.imageView1);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);

                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        getImgURL = imgPath;
        getImgName = imgName;

        return "success";
    }

    /**
     * Upload Image Client Code
     */

    public void DoFileUpload(String apiUrl, String absolutePath) {
        HttpFileUpload(apiUrl, "", absolutePath);
    }

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    public void HttpFileUpload(String urlString, String params, String fileName) {
        try {

            FileInputStream mFileInputStream = new FileInputStream(fileName);
            URL connectUrl = new URL(urlString);
            Log.d("Test", "mFileInputStream  is " + mFileInputStream);

            // HttpURLConnection 통신
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // write data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

            Log.d("Test", "image byte is " + bytesRead);

            // read image
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            Log.e("Test", "File is written");
            mFileInputStream.close();
            dos.flush();
            // finish upload...

            // get response
            InputStream is = conn.getInputStream();

            StringBuffer b = new StringBuffer();
            for (int ch = 0; (ch = is.read()) != -1; ) {
                b.append((char) ch);
            }
            is.close();
            Log.e("Test", b.toString());


        } catch (Exception e) {
            Log.d("Test", "exception " + e.getMessage());
            // TODO: handle exception
        }
    } // end of HttpFileUpload()



    private void uploadFile(String ImgURL, String ImgName) {
        /**
         * 현재 연결된 서버의 URL을 받아옴
         */
        String url = "http://13.125.107.224/index.php";

        /**
         * 다시 연결 시도
         */
        // create upload service client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        ServiceApi service = retrofit.create(ServiceApi.class);

        /**
         * 서버로 보낼 파일의 전체 url을 이용해 작업
         */

        File photo = new File(ImgURL);
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), photo);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", photo.getName(), photoBody);

//        Log.i("myTag","this file'name is "+ photo.getName());

        /**
         * 서버에 사진이외의 텍스트를 보낼 경우를 생각해서 일단 넣어둠
         */
        // add another part within the multipart request
        String descriptionString = "android";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);


        /**
         * 사진 업로드하는 부분 // POST방식 이용
         */
        Call<ResponseBody> call = service.upload(body, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    Gson gson = new Gson();
                    try {
                        String getResult = response.body().string();

                        JsonParser parser = new JsonParser();
                        JsonElement rootObejct = parser.parse(getResult);

//                        Log.i("mytag",rootObejct.toString());

                        UploadResult example = gson.fromJson(rootObejct, UploadResult.class);

                        Log.i("mytag",example.url);

                        String result = example.result;

                        if(result.equals("success")){
                            Toast.makeText(getApplicationContext(),"사진 업로드 성공!",Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("MyTag", "error : "+e.getMessage());
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"사진 업로드 실패!",Toast.LENGTH_SHORT).show();
                }


                // dismiss dialog
                asyncDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());

                // dismiss dialog
                asyncDialog.dismiss();
            }
        });
    }

    /* -- 요기까지 -- */

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.campusjj){
                campusText.setText("죽전캠");
            } else if(i == R.id.campusca){
                campusText.setText("천안캠");
            } else if(i == R.id.radio0){
                typeText.setText("찾아요");
            } else if(i == R.id.radio1){
                typeText.setText("주웠어요");
            }
        }
    };
    private void attemptWrite() {
        titleText.setError(null);
        contentText.setError(null);
        typeText.setError(null);
        campusText.setError(null);

        String title = titleText.getText().toString();
        String nickname = nicknameText.getText().toString();
        String content = contentText.getText().toString();
        String campus = campusText.getText().toString();
        String type = typeText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (title.isEmpty()) {
            titleText.setError("제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        } else if (!isTitleValid(title)) {
            titleText.setError("6자 이상의 제목을 입력해주세요.");
            focusView = titleText;
            cancel = true;
        }

        if (content.isEmpty()) {
            contentText.setError("내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        } else if (!isContentValid(content)) {
            contentText.setError("10자 이상의 내용을 입력해주세요.");
            focusView = contentText;
            cancel = true;
        }

        if (campus.isEmpty()) {
            campusText.setError("캠퍼스를 선택해주세요.");
            focusView = campusgroup;
            cancel = true;
        }

        if (type.isEmpty()) {
            typeText.setError("분실물 유형을 선택해주세요.");
            focusView = typeGroup;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLostWrite(new LostWriteData(title, nickname, content, type, campus));
            showProgress(true);
        }
    }

    private void startLostWrite(LostWriteData data) {
        service.lostwrite(data).enqueue(new Callback<LostWriteResponse>(){
            @Override
            public void onResponse(Call<LostWriteResponse> call, Response<LostWriteResponse> response) {
                LostWriteResponse result = response.body();
                Toast.makeText(LostWriteActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    if(typeText.getText().toString() == "찾아요"){
                        Intent intent = new Intent(LostWriteActivity.this, FindActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostWriteActivity.this.startActivity(intent);
                    } else if(typeText.getText().toString() == "주웠어요"){
                        Intent intent = new Intent(LostWriteActivity.this, GetActivity.class);
                        intent.putExtra(NICKNAME_EXTRA, getIntent().getStringExtra("NICKNAME_EXTRA"));
                        LostWriteActivity.this.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<LostWriteResponse> call, Throwable t) {
                Toast.makeText(LostWriteActivity.this, "글 작성 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("글 작성 에러 발생", t.getMessage());
                showProgress(false);
            }
        });

    }

    private boolean isTitleValid(String title) {
        return title.length()>=6;
    }

    private boolean isContentValid(String content) {
        return content.length() >= 10;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
