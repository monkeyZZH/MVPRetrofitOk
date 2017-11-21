package com.wpz.mymvpframe.model.bean;

import java.util.List;

/**
 * Created by wpz on 2017/11/3 0003.
 * 类作用：
 */

public class ArticleDetailsBean {

    /**
     * code : 1
     * msg : 数据获取成功！
     * data : {"article_detail":{"id":1,"title":"用户称小蓝单车押金退还困难，退款专线已无法接通","content":"<p>多家共享单车品牌面临洗牌困境<\/p>\r\n\r\n<p>每日人物了解到，存在退还押金难的问题的不止小蓝单车，酷骑单车、小鸣单车、摩拜单车等多家共享单车品牌均有押金无法退还的问题。<\/p>\r\n\r\n<p>据据央视财经8月1日报道，今年6月至今，不少自行车生产厂商接到的共享单车订单比去年同期大幅下滑，甚至有些订单被突然暂停。<\/p>\r\n\r\n<p>今年6月以来，共享单车行业也迎来了一波倒闭潮，3家共享单车公司相继关门。<\/p>\r\n\r\n<p>6月13号，悟空单车宣布退出共享单车市场。<\/p>\r\n\r\n<p>6月21日，共享单车平台3Vbike对外宣布称，由于单车大量被盗，即日起停止运营。8月初，运营町町单车的铁拜公司人去楼空，有报道称创始人已被拘留。<img alt=\"\" src=\"/bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG\" style=\"height:640px; width:640px\" /><\/p>\r\n","keywords":"健身,减脂,方法","description":"近日，有报道称，小蓝单车在成都的办公地点已经很多天无人上班，3个维修点也撤走了2个，疑似\u201c人去楼空\u201d。","cover":"/bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG","view":111,"create_time":"2017-09-24 00:00:00","author":"by 余晓宇","rec_link":"https://www.baidu.com"},"advert":{"id":1,"name":"专门搞的耳机","content":["/bodybank/public/uploads/images/20171102/5db70af5446c20255459d6d3d5904175.png","/bodybank/public/uploads/images/20171102/76fe0ceb6c6c3b6f32f2e9438879fe0b.png","/bodybank/public/uploads/images/20171102/a58661702a5929524aa948840d734259.png"],"description":"无音乐不运动"},"cor_artilces":[{"id":21,"title":"第21天的文章"},{"id":13,"title":"第13天的文章"},{"id":14,"title":"第14天的文章"},{"id":15,"title":"第15天的文章"}],"comments":[]}
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
         * article_detail : {"id":1,"title":"用户称小蓝单车押金退还困难，退款专线已无法接通","content":"<p>多家共享单车品牌面临洗牌困境<\/p>\r\n\r\n<p>每日人物了解到，存在退还押金难的问题的不止小蓝单车，酷骑单车、小鸣单车、摩拜单车等多家共享单车品牌均有押金无法退还的问题。<\/p>\r\n\r\n<p>据据央视财经8月1日报道，今年6月至今，不少自行车生产厂商接到的共享单车订单比去年同期大幅下滑，甚至有些订单被突然暂停。<\/p>\r\n\r\n<p>今年6月以来，共享单车行业也迎来了一波倒闭潮，3家共享单车公司相继关门。<\/p>\r\n\r\n<p>6月13号，悟空单车宣布退出共享单车市场。<\/p>\r\n\r\n<p>6月21日，共享单车平台3Vbike对外宣布称，由于单车大量被盗，即日起停止运营。8月初，运营町町单车的铁拜公司人去楼空，有报道称创始人已被拘留。<img alt=\"\" src=\"/bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG\" style=\"height:640px; width:640px\" /><\/p>\r\n","keywords":"健身,减脂,方法","description":"近日，有报道称，小蓝单车在成都的办公地点已经很多天无人上班，3个维修点也撤走了2个，疑似\u201c人去楼空\u201d。","cover":"/bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG","view":111,"create_time":"2017-09-24 00:00:00","author":"by 余晓宇","rec_link":"https://www.baidu.com"}
         * advert : {"id":1,"name":"专门搞的耳机","content":["/bodybank/public/uploads/images/20171102/5db70af5446c20255459d6d3d5904175.png","/bodybank/public/uploads/images/20171102/76fe0ceb6c6c3b6f32f2e9438879fe0b.png","/bodybank/public/uploads/images/20171102/a58661702a5929524aa948840d734259.png"],"description":"无音乐不运动"}
         * cor_artilces : [{"id":21,"title":"第21天的文章"},{"id":13,"title":"第13天的文章"},{"id":14,"title":"第14天的文章"},{"id":15,"title":"第15天的文章"}]
         * comments : []
         */

        private ArticleDetailBean article_detail;
        private AdvertBean advert;
        private List<CorArtilcesBean> cor_artilces;
        private List<?> comments;

        public ArticleDetailBean getArticle_detail() {
            return article_detail;
        }

        public void setArticle_detail(ArticleDetailBean article_detail) {
            this.article_detail = article_detail;
        }

        public AdvertBean getAdvert() {
            return advert;
        }

        public void setAdvert(AdvertBean advert) {
            this.advert = advert;
        }

        public List<CorArtilcesBean> getCor_artilces() {
            return cor_artilces;
        }

        public void setCor_artilces(List<CorArtilcesBean> cor_artilces) {
            this.cor_artilces = cor_artilces;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public static class ArticleDetailBean {
            /**
             * id : 1
             * title : 用户称小蓝单车押金退还困难，退款专线已无法接通
             * content : <p>多家共享单车品牌面临洗牌困境</p>

             <p>每日人物了解到，存在退还押金难的问题的不止小蓝单车，酷骑单车、小鸣单车、摩拜单车等多家共享单车品牌均有押金无法退还的问题。</p>

             <p>据据央视财经8月1日报道，今年6月至今，不少自行车生产厂商接到的共享单车订单比去年同期大幅下滑，甚至有些订单被突然暂停。</p>

             <p>今年6月以来，共享单车行业也迎来了一波倒闭潮，3家共享单车公司相继关门。</p>

             <p>6月13号，悟空单车宣布退出共享单车市场。</p>

             <p>6月21日，共享单车平台3Vbike对外宣布称，由于单车大量被盗，即日起停止运营。8月初，运营町町单车的铁拜公司人去楼空，有报道称创始人已被拘留。<img alt="" src="/bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG" style="height:640px; width:640px" /></p>

             * keywords : 健身,减脂,方法
             * description : 近日，有报道称，小蓝单车在成都的办公地点已经很多天无人上班，3个维修点也撤走了2个，疑似“人去楼空”。
             * cover : /bodybank/public/uploads/images/20171102/45d6dab08a6d8759523c174704ae0aec.JPEG
             * view : 111
             * create_time : 2017-09-24 00:00:00
             * author : by 余晓宇
             * rec_link : https://www.baidu.com
             */

            private int id;
            private String title;
            private String content;
            private String keywords;
            private String description;
            private String cover;
            private int view;
            private String create_time;
            private String author;
            private String rec_link;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getRec_link() {
                return rec_link;
            }

            public void setRec_link(String rec_link) {
                this.rec_link = rec_link;
            }
        }

        public static class AdvertBean {
            /**
             * id : 1
             * name : 专门搞的耳机
             * content : ["/bodybank/public/uploads/images/20171102/5db70af5446c20255459d6d3d5904175.png","/bodybank/public/uploads/images/20171102/76fe0ceb6c6c3b6f32f2e9438879fe0b.png","/bodybank/public/uploads/images/20171102/a58661702a5929524aa948840d734259.png"]
             * description : 无音乐不运动
             */

            private int id;
            private String name;
            private String description;
            private List<String> content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<String> getContent() {
                return content;
            }

            public void setContent(List<String> content) {
                this.content = content;
            }
        }

        public static class CorArtilcesBean {
            /**
             * id : 21
             * title : 第21天的文章
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
