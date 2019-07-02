package com.osiris.food.model;

import java.util.List;

public class LearnsPulicBean {


    /**
     * status : success
     * code : 200
     * data : {"video":[{"id":1,"name":"2018～2019年度初级食品安全培训","thumb":"http://132.232.227.58/uploads/lessons/20190625/fec54c21685fbf483a21b6212277caa8.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":4,"category":"食品安全","time":"01:35:55","visited":"00:00:00"},{"id":2,"name":"2018～2019年度中级食品安全培训","thumb":"http://132.232.227.58/uploads/lessons/20190625/8e17abd61c9ebb759a42c5e5f7aa114a.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":4,"category":"食品安全","time":"04:35:00","visited":"00:00:00"},{"id":3,"name":"2018～2019年度高级食品安全培训","thumb":"http://132.232.227.58/uploads/lessons/20190625/701f9141efc960e371afb1d4407d19a4.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":4,"category":"食品安全","time":"00:00:12","visited":"00:00:00"},{"id":4,"name":"食品安全公共课程","thumb":"http://132.232.227.58/uploads/lessons/20190625/0ebfc95d45b4a21b7e3aa99b6b83e14e.jpg","start_time":"2019-06-23","end_time":"2021-06-23","category_id":4,"category":"食品安全","time":"00:23:40","visited":"00:00:00"},{"id":5,"name":"公共课程2","thumb":"http://132.232.227.58/uploads/lessons/20190625/52339fc8507d214e6f0dff12756aeb05.jpg","start_time":"2019-06-23","end_time":"2023-06-23","category_id":4,"category":"食品安全","time":"00:23:40","visited":"00:00:00"},{"id":6,"name":"食品安全公共课程3","thumb":"http://132.232.227.58/uploads/lessons/20190625/b623375d679515aa2d818bd8ee898e12.jpg","start_time":"2019-06-23","end_time":"2019-07-28","category_id":4,"category":"食品安全","time":"00:23:40","visited":"00:00:00"},{"id":7,"name":"食品安全公共课程4","thumb":"http://132.232.227.58/uploads/lessons/20190625/c3be144aa0530f0c97febc65ce1d6da6.jpg","start_time":"2019-06-01","end_time":"2021-09-20","category_id":4,"category":"食品安全","time":"00:23:40","visited":"00:00:00"},{"id":8,"name":"食品安全管理公共课程5","thumb":"http://132.232.227.58/uploads/lessons/20190625/e1f0d148ff200477950c91d1c4357beb.jpg","start_time":"2019-06-01","end_time":"2020-06-01","category_id":4,"category":"食品安全","time":"00:23:40","visited":"00:00:00"}],"content":[{"id":1,"title":"国务院关于加强食品等产品安全监督管理的特别规定(国务院令第503号)","thumb":"http://132.232.227.58/uploads/contents/20190624/6ef1931b5e4fcf38008bd95c94f2b8c6.jpg","created_at":"2019-06-17 02:16:30"}],"courses":[{"id":9,"title":"中华人民共和国食品安全法实施条例(国务院令第557号)","thumb":"http://132.232.227.58/uploads/contents/20190624/a61922d9207a6801a0cca07ba156bf09.jpg","created_at":"2019-06-24 22:12:11"}]}
     */

    private String status;
    private int code;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<VideoBean> video;
        private List<ContentBean> content;
        private List<CoursesBean> courses;

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<CoursesBean> getCourses() {
            return courses;
        }

        public void setCourses(List<CoursesBean> courses) {
            this.courses = courses;
        }

        public static class VideoBean {
            /**
             * id : 1
             * name : 2018～2019年度初级食品安全培训
             * thumb : http://132.232.227.58/uploads/lessons/20190625/fec54c21685fbf483a21b6212277caa8.jpg
             * start_time : 2019-06-01
             * end_time : 2020-06-01
             * category_id : 4
             * category : 食品安全
             * time : 01:35:55
             * visited : 00:00:00
             */

            private int id;
            private String name;
            private String thumb;
            private String start_time;
            private String end_time;
            private int category_id;
            private String category;
            private String time;
            private String visited;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getVisited() {
                return visited;
            }

            public void setVisited(String visited) {
                this.visited = visited;
            }
        }

        public static class ContentBean {
            /**
             * id : 1
             * title : 国务院关于加强食品等产品安全监督管理的特别规定(国务院令第503号)
             * thumb : http://132.232.227.58/uploads/contents/20190624/6ef1931b5e4fcf38008bd95c94f2b8c6.jpg
             * created_at : 2019-06-17 02:16:30
             */

            private int id;
            private String title;
            private String thumb;
            private String created_at;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }

        public static class CoursesBean {
            /**
             * id : 9
             * title : 中华人民共和国食品安全法实施条例(国务院令第557号)
             * thumb : http://132.232.227.58/uploads/contents/20190624/a61922d9207a6801a0cca07ba156bf09.jpg
             * created_at : 2019-06-24 22:12:11
             */

            private int id;
            private String title;
            private String thumb;
            private String created_at;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
