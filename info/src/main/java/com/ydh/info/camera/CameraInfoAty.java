package com.ydh.info.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import com.ydh.info.R;
import com.ydh.info.databinding.AtyCameraInfoBinding;

import java.util.List;

/**
 * 相机信息
 *
 * @author 13001
 */
public class CameraInfoAty extends AppCompatActivity {

    AtyCameraInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_camera_info);

        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            cameraInfo();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraInfo();
            } else {
                binding.tvInfo.setText("没有权限");
                Toast.makeText(this, "没有访问相机的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cameraInfo() {
        String str = "";
        try {
            Camera mCamera = Camera.open(0);
            Camera.Parameters params = mCamera.getParameters();

            str = "相机支持的分辨率\n";
            List<Camera.Size> pictureSizes = params.getSupportedPictureSizes();
            int length = pictureSizes.size();
            for (int i = 0; i < length; i++) {
                str += "SupportedPictureSizes : " + pictureSizes.get(i).width + "x" + pictureSizes.get(i).height + "\n";
                Log.e("SupportedPictureSizes", "SupportedPictureSizes : " + pictureSizes.get(i).width + "x" + pictureSizes.get(i).height);
            }

            str += "相机预览的分辨率\n";
            List<Camera.Size> previewSizes = params.getSupportedPreviewSizes();
            length = previewSizes.size();
            for (int i = 0; i < length; i++) {
                str += "SupportedPictureSizes : " + pictureSizes.get(i).width + "x" + pictureSizes.get(i).height + "\n";
                Log.e("SupportedPreviewSizes", "SupportedPreviewSizes : " + previewSizes.get(i).width + "x" + previewSizes.get(i).height);
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "相机启动失败";
        }

        binding.tvInfo.setText(str);

    }
}
