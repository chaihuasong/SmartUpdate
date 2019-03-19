package com.chs.smartupdate.data;

import android.os.Parcel;

import java.util.HashMap;

/**
 * Created by lwy on 2018/8/29.
 */

public class AppUpdateModel {
    /* {
         "minVersion": "100",
             "minAllowPatchVersion": "101",
             "newVersion": "101",
             "tip": "测试",
             "size": 12407173,
             "apkURL": "http://host:port/path/to/app/module_app-debug.apk",
             "hash": "07c5c118b64d3e8f16d5de08d1b92cfb",
             "patchInfo": {
         "v100": {
             "patchURL": "http://host:port/path/to/app/v100/100to101.patch",
                     "tip": "测试(本次更新包大小:3543643byte)",
                     "hash": "07c5c118b64d3e8f16d5de08d1b92cfb",
                     "size": 3543643
         }
     }
     }*/
    private int minVersion;
    private int minAllowPatchVersion;
    private int newVersion;
    private String tip;
    private long size;
    private String apkURL;
    private String hash;
    private HashMap<String, PatchInfoModel> patchInfoMap;

    public int getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(int minVersion) {
        this.minVersion = minVersion;
    }

    public int getMinAllowPatchVersion() {
        return minAllowPatchVersion;
    }

    public void setMinAllowPatchVersion(int minAllowPatchVersion) {
        this.minAllowPatchVersion = minAllowPatchVersion;
    }

    public int getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(int newVersion) {
        this.newVersion = newVersion;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getApkURL() {
        return apkURL;
    }

    public void setApkURL(String apkURL) {
        this.apkURL = apkURL;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public HashMap<String, PatchInfoModel> getPatchInfoMap() {
        return patchInfoMap;
    }

    public void setPatchInfoMap(HashMap<String, PatchInfoModel> patchInfoMap) {
        this.patchInfoMap = patchInfoMap;
    }

    public static class PatchInfoModel {
        private String patchURL;
        private String tip;
        private int size;
        private String hash;

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getPatchURL() {
            return patchURL;
        }

        public void setPatchURL(String patchURL) {
            this.patchURL = patchURL;
        }


        protected PatchInfoModel(Parcel in) {
            this.patchURL = in.readString();
            this.tip = in.readString();
            this.size = in.readInt();
            this.hash = in.readString();
        }

    }

}
