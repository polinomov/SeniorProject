package com.projects.oleg.seniorproject.Camera;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.util.Size;
import android.util.SizeF;

import com.projects.oleg.seniorproject.Camera.CameraTexture;

/**
 * Created by Oleg Tolstov on 8:35 PM, 10/9/15. SeniorProject
 */
public class FrontCamera extends CameraDevice.StateCallback{
    public static SizeF sensorSizeMM; //size of the physical sensor in mm
    public static Rect sensorActivePixels; //the pixels on the sensor that are used when taking pictures, Face recog is in this space
    public static Size sensorAllPixels; //all the pixels in the sensor

    public static float pixelToMM; //totalPixelsInSensor / sizeOfSensor

    private Context mContext;
    private CameraManager cameraManager;
    private String mId;
    private CameraDevice camera;
    private boolean opened = false;

    public FrontCamera(Context context) throws CameraAccessException {
        mContext = context;
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        mId = getFontCamera();
        cameraManager.openCamera(mId,this,new Handler(mContext.getMainLooper()));
        sensorSizeMM = cameraManager.getCameraCharacteristics(mId).get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
        sensorAllPixels = cameraManager.getCameraCharacteristics(mId).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        sensorActivePixels = cameraManager.getCameraCharacteristics(mId).get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);

        pixelToMM = (float)sensorAllPixels.getWidth() / (float)sensorSizeMM.getWidth();

    }

    public synchronized boolean start(CameraTexture out) throws CameraAccessException {
        if(!opened){
            return false;
        }
        out.setCaptureRequest(camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).build());
        camera.createCaptureSession(out.getSurfaceList(),out,new Handler(mContext.getMainLooper()));
        return true;
    }

    private String getFontCamera(){
        try {
            String cameraID[] = cameraManager.getCameraIdList();
            for(int i = 0; i < cameraID.length; i++){
                CameraCharacteristics cam = cameraManager.getCameraCharacteristics(cameraID[i]);
                int camPosition = cam.get(CameraCharacteristics.LENS_FACING);
                if(camPosition == CameraCharacteristics.LENS_FACING_FRONT){
                    return cameraID[i];
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public synchronized void onOpened(CameraDevice camera) {
        opened = true;
        this.camera = camera;
    }

    @Override
    public void onDisconnected(CameraDevice camera) {

    }

    @Override
    public void onError(CameraDevice camera, int error) {

    }
}