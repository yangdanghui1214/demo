package com.ydh.network;

import java.util.List;

class ResponseDepartmentAttendResult {

    /**
     * departUserList : [{"allNums":0,"departmentNodeName":"技术部","absenceNums":0,"reallyNums":2,"departmentNodeId":1120,"departmentList":[{"departmentId":1100,"gmtUpdate":null,"schoolId":443,"roleType":null,"gmtCreate":"2019-03-15 17:24:31","parentId":0,"departName":"霆善科技089","order":null},{"departmentId":1120,"gmtUpdate":"2019-04-11 10:18:48","schoolId":443,"roleType":null,"gmtCreate":"2019-03-19 17:14:56","parentId":1100,"departName":"技术部","order":null}],"users":[{"realName":"韩威","face":"https://oss.tsignsv.cn/aipad/w/20181115/130908Y4yiY.png","userId":130965,"status":"common"},{"realName":"沈玉勇","face":"https://oss.tsignsv.cn/aigate/w/20190411/130908vHYHG.png","userId":130970,"status":"common"},{"realName":"胡安超","face":"https://tsign.oss-cn-hangzhou.aliyuncs.com/web/aigates/admin/face/1541469237873.png","userId":130968,"status":"notNeedToAttend"},{"realName":"萧就","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":133021,"status":"notNeedToAttend"}]},{"allNums":13,"departmentNodeName":"研发部","absenceNums":9,"reallyNums":4,"departmentNodeId":547,"departmentList":[{"departmentId":1100,"gmtUpdate":null,"schoolId":443,"roleType":null,"gmtCreate":"2019-03-15 17:24:31","parentId":0,"departName":"霆善科技089","order":null},{"departmentId":547,"gmtUpdate":"2019-03-15 17:24:32","schoolId":443,"roleType":null,"gmtCreate":"2018-11-03 14:09:11","parentId":1100,"departName":"研发部","order":null}],"users":[{"realName":"程灵芝","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":130912,"status":"absent"},{"realName":"徐红江","face":"https://oss.tsignsv.cn/aigate/w/20190402/130908dPnNJ.png","userId":130914,"status":"common"},{"realName":"杨党徽","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":130911,"status":"absent"},{"realName":"孙洋","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":131254,"status":"absent"},{"realName":"施爱鹏","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":130910,"status":"absent"},{"realName":"王凡","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":131478,"status":"leave"},{"realName":"徐常健","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":131768,"status":"absent"},{"realName":"徐琪","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":132085,"status":"absent"},{"realName":"杨阳","face":"https://oss.tsignsv.cn/aigate/w/20190328/130908cKnY2.png","userId":132143,"status":"common"},{"realName":"葛仁边","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":132285,"status":"absent"},{"realName":"蔡进凤","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":132347,"status":"absent"},{"realName":"姜欣","face":"https://oss.tsignsv.cn/aigate/w/20190411/130908HM2k9.png","userId":132977,"status":"absent"},{"realName":"陈卫华","face":"https://oss.tsignsv.cn/aigate/w/20190411/130908aYtqC.png","userId":132978,"status":"enter"},{"realName":"孙洋","face":"https://oss.tsignsv.cn/aigate/w/20190416/130908ZRhdH.png","userId":133055,"status":"common"}]},{"allNums":9,"departmentNodeName":"市场部","absenceNums":7,"reallyNums":2,"departmentNodeId":548,"departmentList":[{"departmentId":1100,"gmtUpdate":null,"schoolId":443,"roleType":null,"gmtCreate":"2019-03-15 17:24:31","parentId":0,"departName":"霆善科技089","order":null},{"departmentId":548,"gmtUpdate":"2019-03-15 17:24:32","schoolId":443,"roleType":null,"gmtCreate":"2018-11-03 15:01:26","parentId":1100,"departName":"市场部","order":null}],"users":[{"realName":"李雯文","face":"https://oss.tsignsv.cn/aigate/w/20181226/130908eaICR.png","userId":130923,"status":"common"},{"realName":"卞妍婷","face":"https://tsign.oss-cn-hangzhou.aliyuncs.com/web/aigates/admin/face/1541232343784.png","userId":130924,"status":"absent"},{"realName":"徐新瑜","face":"https://oss.tsignsv.cn/aipad/w/20181116/130908hurrf.png","userId":130966,"status":"absent"},{"realName":"丁玉洁","face":"https://oss.tsignsv.cn/aigate/w/20181226/130908NvVcI.png","userId":130971,"status":"absent"},{"realName":"卞志勇","face":"https://oss.tsignsv.cn/aigate/w/20190319/1309087s9wN.png","userId":130964,"status":"common"},{"realName":"陈艳","face":"https://oss.tsignsv.cn/aigate/w/20190318/130908exzHA.png","userId":132345,"status":"absent"},{"realName":"祁耀","face":"https://oss.tsignsv.cn/aigate/w/20190318/130908KzqND.jpg","userId":132346,"status":"absent"}]},{"allNums":0,"departmentNodeName":"霆善科技089","absenceNums":0,"reallyNums":0,"departmentNodeId":1100,"departmentList":[{"departmentId":1100,"gmtUpdate":null,"schoolId":443,"roleType":null,"gmtCreate":"2019-03-15 17:24:31","parentId":0,"departName":"霆善科技089","order":null}],"users":[]}]
     * updateTime : 2019-05-22 16:52:09
     */

    private String updateTime;
    private List<DepartUserListBean> departUserList;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DepartUserListBean> getDepartUserList() {
        return departUserList;
    }

    public void setDepartUserList(List<DepartUserListBean> departUserList) {
        this.departUserList = departUserList;
    }

    public static class DepartUserListBean {
        /**
         * allNums : 0
         * departmentNodeName : 技术部
         * absenceNums : 0
         * reallyNums : 2
         * departmentNodeId : 1120
         * departmentList : [{"departmentId":1100,"gmtUpdate":null,"schoolId":443,"roleType":null,"gmtCreate":"2019-03-15 17:24:31","parentId":0,"departName":"霆善科技089","order":null},{"departmentId":1120,"gmtUpdate":"2019-04-11 10:18:48","schoolId":443,"roleType":null,"gmtCreate":"2019-03-19 17:14:56","parentId":1100,"departName":"技术部","order":null}]
         * users : [{"realName":"韩威","face":"https://oss.tsignsv.cn/aipad/w/20181115/130908Y4yiY.png","userId":130965,"status":"common"},{"realName":"沈玉勇","face":"https://oss.tsignsv.cn/aigate/w/20190411/130908vHYHG.png","userId":130970,"status":"common"},{"realName":"胡安超","face":"https://tsign.oss-cn-hangzhou.aliyuncs.com/web/aigates/admin/face/1541469237873.png","userId":130968,"status":"notNeedToAttend"},{"realName":"萧就","face":"https://oss.tsignsv.cn/com/web/parentWoma.png","userId":133021,"status":"notNeedToAttend"}]
         */

        private int allNums;
        private String departmentNodeName;
        private int absenceNums;
        private int reallyNums;
        private int parentId;
        private int departmentNodeId;
        private List<DepartmentListBean> departmentList;
        private List<UsersBean> users;

        public int getAllNums() {
            return allNums;
        }

        public void setAllNums(int allNums) {
            this.allNums = allNums;
        }

        public String getDepartmentNodeName() {
            return departmentNodeName;
        }

        public void setDepartmentNodeName(String departmentNodeName) {
            this.departmentNodeName = departmentNodeName;
        }

        public int getAbsenceNums() {
            return absenceNums;
        }

        public void setAbsenceNums(int absenceNums) {
            this.absenceNums = absenceNums;
        }

        public int getReallyNums() {
            return reallyNums;
        }

        public void setReallyNums(int reallyNums) {
            this.reallyNums = reallyNums;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getDepartmentNodeId() {
            return departmentNodeId;
        }

        public void setDepartmentNodeId(int departmentNodeId) {
            this.departmentNodeId = departmentNodeId;
        }

        public List<DepartmentListBean> getDepartmentList() {
            return departmentList;
        }

        public void setDepartmentList(List<DepartmentListBean> departmentList) {
            this.departmentList = departmentList;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class DepartmentListBean {
            /**
             * departmentId : 1100
             * gmtUpdate : null
             * schoolId : 443
             * roleType : null
             * gmtCreate : 2019-03-15 17:24:31
             * parentId : 0
             * departName : 霆善科技089
             * order : null
             */

            private int departmentId;
            private Object gmtUpdate;
            private int schoolId;
            private Object roleType;
            private String gmtCreate;
            private int parentId;
            private String departName;
            private Object order;

            public int getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(int departmentId) {
                this.departmentId = departmentId;
            }

            public Object getGmtUpdate() {
                return gmtUpdate;
            }

            public void setGmtUpdate(Object gmtUpdate) {
                this.gmtUpdate = gmtUpdate;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public Object getRoleType() {
                return roleType;
            }

            public void setRoleType(Object roleType) {
                this.roleType = roleType;
            }

            public String getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(String gmtCreate) {
                this.gmtCreate = gmtCreate;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getDepartName() {
                return departName;
            }

            public void setDepartName(String departName) {
                this.departName = departName;
            }

            public Object getOrder() {
                return order;
            }

            public void setOrder(Object order) {
                this.order = order;
            }
        }

        public static class UsersBean {
            /**
             * realName : 韩威
             * face : https://oss.tsignsv.cn/aipad/w/20181115/130908Y4yiY.png
             * userId : 130965
             * status : common
             */

            private String realName;
            private String face;
            private int userId;
            private String status;

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
