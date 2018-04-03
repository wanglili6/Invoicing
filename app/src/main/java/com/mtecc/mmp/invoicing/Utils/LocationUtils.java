package com.mtecc.mmp.invoicing.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import java.util.List;

/**
 * Created by phillip on 2018/4/3.
 * 获取定位坐标
 */

public class LocationUtils {
   private String locationStr = "";
   private Context mContext;

    public LocationUtils(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    /**
     * 获取坐标  逗号分隔(,)
     * @return
     */
    public String getLocationStr() {
        return locationStr;
    }

    /**
     * 设置坐标  逗号分隔(,)
     * @param locationStr
     */
    public void setLocationStr(String locationStr) {
        this.locationStr = locationStr;
    }

    /**
     * 使用定位管理者获取定位坐标
     */

    private  void getLocation() {
        LocationManager systemService = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = systemService.getProviders(true);
        String provider = "";
        for (int i = 0; i < providers.size(); i++) {
            if (providers.contains(LocationManager.GPS_PROVIDER)) {
                provider = LocationManager.GPS_PROVIDER;
            } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                provider = LocationManager.NETWORK_PROVIDER;
            } else {
                Toast.makeText(mContext, "定位失败!", Toast.LENGTH_SHORT).show();
            }
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (TextUtils.isEmpty(provider)) {
            Toast.makeText(mContext, "定位失败!", Toast.LENGTH_SHORT).show();
        } else {
            Location location = systemService.getLastKnownLocation(provider);
            if (location != null) {
                LogUtils.d(location.getLatitude() + "---" + location.getLongitude());
                locationStr =location.getLatitude() + "," + location.getLongitude();
            }
            systemService.requestLocationUpdates(provider, 2000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        LogUtils.d(location.getLatitude() + "---" + location.getLongitude());
                        locationStr =location.getLatitude() + "," + location.getLongitude();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    LogUtils.d(provider + "---" + status + "===" + status + extras.toString());

                }

                @Override
                public void onProviderEnabled(String provider) {
                    LogUtils.d(provider + "---");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    LogUtils.d(provider + "--777-");
                }
            });
        }
    }
}
