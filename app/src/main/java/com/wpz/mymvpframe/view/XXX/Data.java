package com.wpz.mymvpframe.view.XXX;

/**
 * name:周振辉
 * 时间：2017/11/13 13:33
 * 类描述：
 */

public class Data {

    /**
     * code : 1
     * msg : 数据获取成功！
     * data : {"RequestId":"94D9DC52-F416-4231-9682-97EBE286913C","VideoMeta":{"CoverURL":"http://muzhivote.cn/cover/2017-11-02-18-41-24_B2DDEEA8-200B-4729-95C1-76AC4DC4B77F.png","Status":"Normal","VideoId":"aacf07e1609243bfaeed24533ba478fc","Duration":7,"Title":"我这个就是测试标题"},"PlayAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwTFBjTXMvVjJaMVg0N1NaTzFMMHRFRU1PZjExckphU296ejJJSHBLZVhkdUFlQVhzL28wbW1oWjcvWVlsck1xR3NVYUh4U1lOSmN0dHNnSnFseitKcExGc3QySjZyOEpqc1VCeU9sa21GMnBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZOLzducFNVUmJ2M2I0eGxMZXVrQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWRQTDVwZ2lhTTFyOUVQYmRnZmhtaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDR4ckRoRzVnS3NNUWFnQUd2a1gvYk5DcDBpZEI2UzlneXI1cU4wem5zWkN4VVZtSGQxazhQaUFnZW5kamRLb3R0bGk3aCtDbGgxeVV3Lzh1UGR1b3F5VlZQSXFMaHpLN0pnVDVTQ0UzQy9oWVRGVFNKdmJYVjZvNmJUbTVTZE5sb1plZXd3bWtZZUY4enFrUG8xVHdNeDM3ZFV1ekovYzI1WFdUbnMyaEZoSHZUZGwwalFkRExraHAzQkE9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI5MU9NcDNQUGhTZ01QMElPcFB5VEFERjZ0bzUwbnZDN0IxK1JTVEJaZ3JVPVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxNy0xMS0xM1QwNTowNzozM1pcIixcIk1lZGlhSWRcIjpcImFhY2YwN2UxNjA5MjQzYmZhZWVkMjQ1MzNiYTQ3OGZjXCIsXCJTaWduYXR1cmVcIjpcImFPNFdpUUFFdkxmRjU0RFFJSW1zRnlPbVpncz1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6ImFhY2YwN2UxNjA5MjQzYmZhZWVkMjQ1MzNiYTQ3OGZjIiwiVGl0bGUiOiLmiJHov5nkuKrlsLHmmK/mtYvor5XmoIfpopgiLCJDb3ZlclVSTCI6Imh0dHA6Ly9tdXpoaXZvdGUuY24vY292ZXIvMjAxNy0xMS0wMi0xOC00MS0yNF9CMkRERUVBOC0yMDBCLTQ3MjktOTVDMS03NkFDNERDNEI3N0YucG5nIiwiRHVyYXRpb24iOjcuMH0sIkFjY2Vzc0tleUlkIjoiU1RTLkVwaXl1YTRCdlR2cjl0RVBBWTVyWUNTOUEiLCJQbGF5RG9tYWluIjoibXV6aGl2b3RlLmNuIiwiQWNjZXNzS2V5U2VjcmV0IjoiMzJjeVdxQ3VkV05aUEtFOEYyZFhlRlhRbWRkTXVqZXlYMVNZd0o4a2dFRTMiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjE5MjcyMDQxNDUwNDYzNzd9"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * RequestId : 94D9DC52-F416-4231-9682-97EBE286913C
         * VideoMeta : {"CoverURL":"http://muzhivote.cn/cover/2017-11-02-18-41-24_B2DDEEA8-200B-4729-95C1-76AC4DC4B77F.png","Status":"Normal","VideoId":"aacf07e1609243bfaeed24533ba478fc","Duration":7,"Title":"我这个就是测试标题"}
         * PlayAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwTFBjTXMvVjJaMVg0N1NaTzFMMHRFRU1PZjExckphU296ejJJSHBLZVhkdUFlQVhzL28wbW1oWjcvWVlsck1xR3NVYUh4U1lOSmN0dHNnSnFseitKcExGc3QySjZyOEpqc1VCeU9sa21GMnBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZOLzducFNVUmJ2M2I0eGxMZXVrQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWRQTDVwZ2lhTTFyOUVQYmRnZmhtaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDR4ckRoRzVnS3NNUWFnQUd2a1gvYk5DcDBpZEI2UzlneXI1cU4wem5zWkN4VVZtSGQxazhQaUFnZW5kamRLb3R0bGk3aCtDbGgxeVV3Lzh1UGR1b3F5VlZQSXFMaHpLN0pnVDVTQ0UzQy9oWVRGVFNKdmJYVjZvNmJUbTVTZE5sb1plZXd3bWtZZUY4enFrUG8xVHdNeDM3ZFV1ekovYzI1WFdUbnMyaEZoSHZUZGwwalFkRExraHAzQkE9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI5MU9NcDNQUGhTZ01QMElPcFB5VEFERjZ0bzUwbnZDN0IxK1JTVEJaZ3JVPVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxNy0xMS0xM1QwNTowNzozM1pcIixcIk1lZGlhSWRcIjpcImFhY2YwN2UxNjA5MjQzYmZhZWVkMjQ1MzNiYTQ3OGZjXCIsXCJTaWduYXR1cmVcIjpcImFPNFdpUUFFdkxmRjU0RFFJSW1zRnlPbVpncz1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6ImFhY2YwN2UxNjA5MjQzYmZhZWVkMjQ1MzNiYTQ3OGZjIiwiVGl0bGUiOiLmiJHov5nkuKrlsLHmmK/mtYvor5XmoIfpopgiLCJDb3ZlclVSTCI6Imh0dHA6Ly9tdXpoaXZvdGUuY24vY292ZXIvMjAxNy0xMS0wMi0xOC00MS0yNF9CMkRERUVBOC0yMDBCLTQ3MjktOTVDMS03NkFDNERDNEI3N0YucG5nIiwiRHVyYXRpb24iOjcuMH0sIkFjY2Vzc0tleUlkIjoiU1RTLkVwaXl1YTRCdlR2cjl0RVBBWTVyWUNTOUEiLCJQbGF5RG9tYWluIjoibXV6aGl2b3RlLmNuIiwiQWNjZXNzS2V5U2VjcmV0IjoiMzJjeVdxQ3VkV05aUEtFOEYyZFhlRlhRbWRkTXVqZXlYMVNZd0o4a2dFRTMiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjE5MjcyMDQxNDUwNDYzNzd9
         */

        private String RequestId;
        private VideoMetaBean VideoMeta;
        private String PlayAuth;

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public VideoMetaBean getVideoMeta() {
            return VideoMeta;
        }

        public void setVideoMeta(VideoMetaBean VideoMeta) {
            this.VideoMeta = VideoMeta;
        }

        public String getPlayAuth() {
            return PlayAuth;
        }

        public void setPlayAuth(String PlayAuth) {
            this.PlayAuth = PlayAuth;
        }

        public static class VideoMetaBean {
            /**
             * CoverURL : http://muzhivote.cn/cover/2017-11-02-18-41-24_B2DDEEA8-200B-4729-95C1-76AC4DC4B77F.png
             * Status : Normal
             * VideoId : aacf07e1609243bfaeed24533ba478fc
             * Duration : 7
             * Title : 我这个就是测试标题
             */

            private String CoverURL;
            private String Status;
            private String VideoId;
            private int Duration;
            private String Title;

            public String getCoverURL() {
                return CoverURL;
            }

            public void setCoverURL(String CoverURL) {
                this.CoverURL = CoverURL;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getVideoId() {
                return VideoId;
            }

            public void setVideoId(String VideoId) {
                this.VideoId = VideoId;
            }

            public int getDuration() {
                return Duration;
            }

            public void setDuration(int Duration) {
                this.Duration = Duration;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }
        }
    }
}
