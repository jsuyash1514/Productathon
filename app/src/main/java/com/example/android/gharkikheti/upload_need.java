package com.example.android.gharkikheti;

public class upload_need {
    public static class Upload {
        private String mName;
        private String mstartdate;
        private String menddate;
        private String mImageUrl;
        public Upload(String mName, String mstartdate, String menddate,String mImageUrl) {
            this.mName = mName;
            this.mstartdate = mstartdate;
            this.menddate = menddate;
            this.mImageUrl=mImageUrl;
        }

        public String getmImageUrl() {
            return mImageUrl;
        }

        public void setmImageUrl(String mImageUrl) {
            this.mImageUrl = mImageUrl;
        }

        public Upload() {
            //empty constructor needed
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getMstartdate() {
            return mstartdate;
        }

        public void setMstartdate(String mstartdate) {
            this.mstartdate = mstartdate;
        }

        public String getMenddate() {
            return menddate;
        }

        public void setMenddate(String menddate) {
            this.menddate = menddate;
        }
    }
}