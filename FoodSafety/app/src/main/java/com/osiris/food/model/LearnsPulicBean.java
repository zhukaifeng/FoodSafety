package com.osiris.food.model;

import java.util.List;

public class LearnsPulicBean {

    /**
     * status : success
     * code : 200
     * data : {"lesson_id":3,"lesson":{"name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01"},"list":[{"id":8,"name":"食品安全管理公共课程5","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"http://132.232.227.58/uploads/lessons/20190623/2de68c9bcb879dcacb11b2d92c60270d.jpg","category":"食品安全","time":"00:00:12","visited":"00:00:00"},{"id":7,"name":"食品安全公共课程4","start_time":"2019-06-01","end_time":"2021-09-20","thumb":"","category":"食品安全","time":"00:00:12","visited":"00:00:00"},{"id":6,"name":"食品安全公共课程3","start_time":"2019-06-23","end_time":"2019-07-28","thumb":"http://132.232.227.58/uploads/lessons/20190623/20fdbff0951cab791ddf4935da1cb132.jpg","category":"食品安全","time":"00:00:52","visited":"00:00:00"},{"id":5,"name":"公共课程2","start_time":"2019-06-23","end_time":"2023-06-23","thumb":"http://132.232.227.58/uploads/lessons/20190623/24d6edf7c4e6d849c58acd00bca83b97.jpg","category":"食品安全","time":"00:01:00","visited":"00:00:00"},{"id":4,"name":"食品安全公共课程","start_time":"2019-06-23","end_time":"2021-06-23","thumb":"http://132.232.227.58/uploads/lessons/20190623/bf3f6f182a25a8f27bfa229a4e1e0a50.jpg","category":"食品安全","time":"00:01:00","visited":"00:00:00"},{"id":2,"name":"2018～2019年度中级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"","category":"食品安全","time":"05:25:00","visited":"01:40:00"}]}
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
        /**
         * lesson_id : 3
         * lesson : {"name":"2018～2019年度高级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01"}
         * list : [{"id":8,"name":"食品安全管理公共课程5","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"http://132.232.227.58/uploads/lessons/20190623/2de68c9bcb879dcacb11b2d92c60270d.jpg","category":"食品安全","time":"00:00:12","visited":"00:00:00"},{"id":7,"name":"食品安全公共课程4","start_time":"2019-06-01","end_time":"2021-09-20","thumb":"","category":"食品安全","time":"00:00:12","visited":"00:00:00"},{"id":6,"name":"食品安全公共课程3","start_time":"2019-06-23","end_time":"2019-07-28","thumb":"http://132.232.227.58/uploads/lessons/20190623/20fdbff0951cab791ddf4935da1cb132.jpg","category":"食品安全","time":"00:00:52","visited":"00:00:00"},{"id":5,"name":"公共课程2","start_time":"2019-06-23","end_time":"2023-06-23","thumb":"http://132.232.227.58/uploads/lessons/20190623/24d6edf7c4e6d849c58acd00bca83b97.jpg","category":"食品安全","time":"00:01:00","visited":"00:00:00"},{"id":4,"name":"食品安全公共课程","start_time":"2019-06-23","end_time":"2021-06-23","thumb":"http://132.232.227.58/uploads/lessons/20190623/bf3f6f182a25a8f27bfa229a4e1e0a50.jpg","category":"食品安全","time":"00:01:00","visited":"00:00:00"},{"id":2,"name":"2018～2019年度中级食品安全培训","start_time":"2019-06-01","end_time":"2020-06-01","thumb":"","category":"食品安全","time":"05:25:00","visited":"01:40:00"}]
         */

        private int lesson_id;
        private LessonBean lesson;
        private List<ListBean> list;

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public LessonBean getLesson() {
            return lesson;
        }

        public void setLesson(LessonBean lesson) {
            this.lesson = lesson;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class LessonBean {
            /**
             * name : 2018～2019年度高级食品安全培训
             * start_time : 2019-06-01
             * end_time : 2020-06-01
             */

            private String name;
            private String start_time;
            private String end_time;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }

        public static class ListBean {
            /**
             * id : 8
             * name : 食品安全管理公共课程5
             * start_time : 2019-06-01
             * end_time : 2020-06-01
             * thumb : http://132.232.227.58/uploads/lessons/20190623/2de68c9bcb879dcacb11b2d92c60270d.jpg
             * category : 食品安全
             * time : 00:00:12
             * visited : 00:00:00
             */

            private int id;
            private String name;
            private String start_time;
            private String end_time;
            private String thumb;
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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
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
    }
}
