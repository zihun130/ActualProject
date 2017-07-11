package atguigu.com.actualproject.magazine.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sun on 2017/7/10.
 */

public class MagazineBean {


    /**
     * meta : {"status":0,"server_time":"2017-07-10 15:54:15","account_id":0,"cost":0.18508577346802,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":false,"num_items":1,"items":{"keys":["2017.JUL.08","2017.JUL.07","2017.JUL.05","2017.JUL.02","2017.JUN.29","2017.JUN.27","2017.JUN.26","2017.JUN.20","2017.JUN.19","2017.JUN.15","2017.JUN.10","2017.JUN.06","2017.JUN.05","2017.MAY.23","2017.MAY.20","2017.MAY.18","2017.MAY.15","2017.MAY.12","2017.MAY.09","2017.MAY.06","2017.MAY.04","2017.MAY.03","2017.MAY.01","2017.APR.28","2017.APR.24","2017.APR.21","2017.APR.19","2017.APR.17","2017.APR.15","2017.APR.14","2017.APR.13","2017.APR.12","2017.APR.10","2017.APR.07","2017.APR.06","2017.APR.04","2017.APR.02","2017.MAR.30","2017.MAR.27","2017.MAR.25","2017.MAR.24","2017.MAR.23","2017.MAR.21","2017.MAR.20","2017.MAR.19","2017.MAR.18","2017.MAR.17","2017.MAR.16","2017.MAR.15","2017.MAR.14","2017.MAR.13","2017.MAR.12","2017.MAR.09","2017.MAR.08","2017.MAR.06","2017.MAR.04","2017.MAR.03","2017.MAR.02","2017.FEB.28","2017.FEB.27","2017.FEB.24","2017.FEB.22","2017.FEB.18","2017.FEB.17","2017.FEB.16","2017.FEB.15","2017.FEB.14","2017.FEB.10","2017.FEB.09","2017.FEB.08","2017.FEB.07","2017.FEB.05","2017.FEB.04","2017.FEB.03","2017.JAN.24","2017.JAN.23","2017.JAN.22","2017.JAN.21","2017.JAN.18","2017.JAN.17","2017.JAN.16","2017.JAN.15","2017.JAN.13","2017.JAN.11","2017.JAN.09","2017.JAN.08"],"infos":{"2017.JUL.08":[],"2017.JUL.07":[],"2017.JUL.05":[],"2017.JUL.02":[],"2017.JUN.29":[],"2017.JUN.27":[],"2017.JUN.26":[],"2017.JUN.20":[],"2017.JUN.19":[],"2017.JUN.15":[],"2017.JUN.10":[],"2017.JUN.06":[],"2017.JUN.05":[],"2017.MAY.23":[],"2017.MAY.20":[],"2017.MAY.18":[],"2017.MAY.15":[],"2017.MAY.12":[],"2017.MAY.09":[],"2017.MAY.06":[],"2017.MAY.04":[],"2017.MAY.03":[],"2017.MAY.01":[],"2017.APR.28":[],"2017.APR.24":[],"2017.APR.21":[],"2017.APR.19":[],"2017.APR.17":[],"2017.APR.15":[],"2017.APR.14":[],"2017.APR.13":[],"2017.APR.12":[],"2017.APR.10":[],"2017.APR.07":[],"2017.APR.06":[],"2017.APR.04":[],"2017.APR.02":[],"2017.MAR.30":[],"2017.MAR.27":[],"2017.MAR.25":[],"2017.MAR.24":[],"2017.MAR.23":[],"2017.MAR.21":[],"2017.MAR.20":[],"2017.MAR.19":[],"2017.MAR.18":[],"2017.MAR.17":[],"2017.MAR.16":[],"2017.MAR.15":[],"2017.MAR.14":[],"2017.MAR.13":[],"2017.MAR.12":[],"2017.MAR.09":[],"2017.MAR.08":[],"2017.MAR.06":[],"2017.MAR.04":[],"2017.MAR.03":[],"2017.MAR.02":[],"2017.FEB.28":[],"2017.FEB.27":[],"2017.FEB.24":[],"2017.FEB.22":[],"2017.FEB.18":[],"2017.FEB.17":[],"2017.FEB.16":[],"2017.FEB.15":[],"2017.FEB.14":[],"2017.FEB.10":[],"2017.FEB.09":[],"2017.FEB.08":[],"2017.FEB.07":[],"2017.FEB.05":[],"2017.FEB.04":[],"2017.FEB.03":[],"2017.JAN.24":[],"2017.JAN.23":[],"2017.JAN.22":[],"2017.JAN.21":[],"2017.JAN.18":[],"2017.JAN.17":[],"2017.JAN.16":[],"2017.JAN.15":[],"2017.JAN.13":[],"2017.JAN.11":[],"2017.JAN.09":[],"2017.JAN.08":[]}}}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-10 15:54:15
         * account_id : 0
         * cost : 0.18508577346802
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : false
         * num_items : 1
         * items : {"keys":["2017.JUL.08","2017.JUL.07","2017.JUL.05","2017.JUL.02","2017.JUN.29","2017.JUN.27","2017.JUN.26","2017.JUN.20","2017.JUN.19","2017.JUN.15","2017.JUN.10","2017.JUN.06","2017.JUN.05","2017.MAY.23","2017.MAY.20","2017.MAY.18","2017.MAY.15","2017.MAY.12","2017.MAY.09","2017.MAY.06","2017.MAY.04","2017.MAY.03","2017.MAY.01","2017.APR.28","2017.APR.24","2017.APR.21","2017.APR.19","2017.APR.17","2017.APR.15","2017.APR.14","2017.APR.13","2017.APR.12","2017.APR.10","2017.APR.07","2017.APR.06","2017.APR.04","2017.APR.02","2017.MAR.30","2017.MAR.27","2017.MAR.25","2017.MAR.24","2017.MAR.23","2017.MAR.21","2017.MAR.20","2017.MAR.19","2017.MAR.18","2017.MAR.17","2017.MAR.16","2017.MAR.15","2017.MAR.14","2017.MAR.13","2017.MAR.12","2017.MAR.09","2017.MAR.08","2017.MAR.06","2017.MAR.04","2017.MAR.03","2017.MAR.02","2017.FEB.28","2017.FEB.27","2017.FEB.24","2017.FEB.22","2017.FEB.18","2017.FEB.17","2017.FEB.16","2017.FEB.15","2017.FEB.14","2017.FEB.10","2017.FEB.09","2017.FEB.08","2017.FEB.07","2017.FEB.05","2017.FEB.04","2017.FEB.03","2017.JAN.24","2017.JAN.23","2017.JAN.22","2017.JAN.21","2017.JAN.18","2017.JAN.17","2017.JAN.16","2017.JAN.15","2017.JAN.13","2017.JAN.11","2017.JAN.09","2017.JAN.08"],"infos":{"2017.JUL.08":[],"2017.JUL.07":[],"2017.JUL.05":[],"2017.JUL.02":[],"2017.JUN.29":[],"2017.JUN.27":[],"2017.JUN.26":[],"2017.JUN.20":[],"2017.JUN.19":[],"2017.JUN.15":[],"2017.JUN.10":[],"2017.JUN.06":[],"2017.JUN.05":[],"2017.MAY.23":[],"2017.MAY.20":[],"2017.MAY.18":[],"2017.MAY.15":[],"2017.MAY.12":[],"2017.MAY.09":[],"2017.MAY.06":[],"2017.MAY.04":[],"2017.MAY.03":[],"2017.MAY.01":[],"2017.APR.28":[],"2017.APR.24":[],"2017.APR.21":[],"2017.APR.19":[],"2017.APR.17":[],"2017.APR.15":[],"2017.APR.14":[],"2017.APR.13":[],"2017.APR.12":[],"2017.APR.10":[],"2017.APR.07":[],"2017.APR.06":[],"2017.APR.04":[],"2017.APR.02":[],"2017.MAR.30":[],"2017.MAR.27":[],"2017.MAR.25":[],"2017.MAR.24":[],"2017.MAR.23":[],"2017.MAR.21":[],"2017.MAR.20":[],"2017.MAR.19":[],"2017.MAR.18":[],"2017.MAR.17":[],"2017.MAR.16":[],"2017.MAR.15":[],"2017.MAR.14":[],"2017.MAR.13":[],"2017.MAR.12":[],"2017.MAR.09":[],"2017.MAR.08":[],"2017.MAR.06":[],"2017.MAR.04":[],"2017.MAR.03":[],"2017.MAR.02":[],"2017.FEB.28":[],"2017.FEB.27":[],"2017.FEB.24":[],"2017.FEB.22":[],"2017.FEB.18":[],"2017.FEB.17":[],"2017.FEB.16":[],"2017.FEB.15":[],"2017.FEB.14":[],"2017.FEB.10":[],"2017.FEB.09":[],"2017.FEB.08":[],"2017.FEB.07":[],"2017.FEB.05":[],"2017.FEB.04":[],"2017.FEB.03":[],"2017.JAN.24":[],"2017.JAN.23":[],"2017.JAN.22":[],"2017.JAN.21":[],"2017.JAN.18":[],"2017.JAN.17":[],"2017.JAN.16":[],"2017.JAN.15":[],"2017.JAN.13":[],"2017.JAN.11":[],"2017.JAN.09":[],"2017.JAN.08":[]}}
         */

        private boolean has_more;
        private int num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getNum_items() {
            return num_items;
        }

        public void setNum_items(int num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * keys : ["2017.JUL.08","2017.JUL.07","2017.JUL.05","2017.JUL.02","2017.JUN.29","2017.JUN.27","2017.JUN.26","2017.JUN.20","2017.JUN.19","2017.JUN.15","2017.JUN.10","2017.JUN.06","2017.JUN.05","2017.MAY.23","2017.MAY.20","2017.MAY.18","2017.MAY.15","2017.MAY.12","2017.MAY.09","2017.MAY.06","2017.MAY.04","2017.MAY.03","2017.MAY.01","2017.APR.28","2017.APR.24","2017.APR.21","2017.APR.19","2017.APR.17","2017.APR.15","2017.APR.14","2017.APR.13","2017.APR.12","2017.APR.10","2017.APR.07","2017.APR.06","2017.APR.04","2017.APR.02","2017.MAR.30","2017.MAR.27","2017.MAR.25","2017.MAR.24","2017.MAR.23","2017.MAR.21","2017.MAR.20","2017.MAR.19","2017.MAR.18","2017.MAR.17","2017.MAR.16","2017.MAR.15","2017.MAR.14","2017.MAR.13","2017.MAR.12","2017.MAR.09","2017.MAR.08","2017.MAR.06","2017.MAR.04","2017.MAR.03","2017.MAR.02","2017.FEB.28","2017.FEB.27","2017.FEB.24","2017.FEB.22","2017.FEB.18","2017.FEB.17","2017.FEB.16","2017.FEB.15","2017.FEB.14","2017.FEB.10","2017.FEB.09","2017.FEB.08","2017.FEB.07","2017.FEB.05","2017.FEB.04","2017.FEB.03","2017.JAN.24","2017.JAN.23","2017.JAN.22","2017.JAN.21","2017.JAN.18","2017.JAN.17","2017.JAN.16","2017.JAN.15","2017.JAN.13","2017.JAN.11","2017.JAN.09","2017.JAN.08"]
             * infos : {"2017.JUL.08":[],"2017.JUL.07":[],"2017.JUL.05":[],"2017.JUL.02":[],"2017.JUN.29":[],"2017.JUN.27":[],"2017.JUN.26":[],"2017.JUN.20":[],"2017.JUN.19":[],"2017.JUN.15":[],"2017.JUN.10":[],"2017.JUN.06":[],"2017.JUN.05":[],"2017.MAY.23":[],"2017.MAY.20":[],"2017.MAY.18":[],"2017.MAY.15":[],"2017.MAY.12":[],"2017.MAY.09":[],"2017.MAY.06":[],"2017.MAY.04":[],"2017.MAY.03":[],"2017.MAY.01":[],"2017.APR.28":[],"2017.APR.24":[],"2017.APR.21":[],"2017.APR.19":[],"2017.APR.17":[],"2017.APR.15":[],"2017.APR.14":[],"2017.APR.13":[],"2017.APR.12":[],"2017.APR.10":[],"2017.APR.07":[],"2017.APR.06":[],"2017.APR.04":[],"2017.APR.02":[],"2017.MAR.30":[],"2017.MAR.27":[],"2017.MAR.25":[],"2017.MAR.24":[],"2017.MAR.23":[],"2017.MAR.21":[],"2017.MAR.20":[],"2017.MAR.19":[],"2017.MAR.18":[],"2017.MAR.17":[],"2017.MAR.16":[],"2017.MAR.15":[],"2017.MAR.14":[],"2017.MAR.13":[],"2017.MAR.12":[],"2017.MAR.09":[],"2017.MAR.08":[],"2017.MAR.06":[],"2017.MAR.04":[],"2017.MAR.03":[],"2017.MAR.02":[],"2017.FEB.28":[],"2017.FEB.27":[],"2017.FEB.24":[],"2017.FEB.22":[],"2017.FEB.18":[],"2017.FEB.17":[],"2017.FEB.16":[],"2017.FEB.15":[],"2017.FEB.14":[],"2017.FEB.10":[],"2017.FEB.09":[],"2017.FEB.08":[],"2017.FEB.07":[],"2017.FEB.05":[],"2017.FEB.04":[],"2017.FEB.03":[],"2017.JAN.24":[],"2017.JAN.23":[],"2017.JAN.22":[],"2017.JAN.21":[],"2017.JAN.18":[],"2017.JAN.17":[],"2017.JAN.16":[],"2017.JAN.15":[],"2017.JAN.13":[],"2017.JAN.11":[],"2017.JAN.09":[],"2017.JAN.08":[]}
             */

            private InfosBean infos;
            private List<String> keys;

            public InfosBean getInfos() {
                return infos;
            }

            public void setInfos(InfosBean infos) {
                this.infos = infos;
            }

            public List<String> getKeys() {
                return keys;
            }

            public void setKeys(List<String> keys) {
                this.keys = keys;
            }

            public static class InfosBean {
                @SerializedName("2017.JUL.08")
                private List<?> _$_2017JUL0838; // FIXME check this code
                @SerializedName("2017.JUL.07")
                private List<?> _$_2017JUL07321; // FIXME check this code
                @SerializedName("2017.JUL.05")
                private List<?> _$_2017JUL05194; // FIXME check this code
                @SerializedName("2017.JUL.02")
                private List<?> _$_2017JUL0236; // FIXME check this code
                @SerializedName("2017.JUN.29")
                private List<?> _$_2017JUN29129; // FIXME check this code
                @SerializedName("2017.JUN.27")
                private List<?> _$_2017JUN2777; // FIXME check this code
                @SerializedName("2017.JUN.26")
                private List<?> _$_2017JUN26104; // FIXME check this code
                @SerializedName("2017.JUN.20")
                private List<?> _$_2017JUN2036; // FIXME check this code
                @SerializedName("2017.JUN.19")
                private List<?> _$_2017JUN19118; // FIXME check this code
                @SerializedName("2017.JUN.15")
                private List<?> _$_2017JUN1576; // FIXME check this code
                @SerializedName("2017.JUN.10")
                private List<?> _$_2017JUN10332; // FIXME check this code
                @SerializedName("2017.JUN.06")
                private List<?> _$_2017JUN0649; // FIXME check this code
                @SerializedName("2017.JUN.05")
                private List<?> _$_2017JUN05230; // FIXME check this code
                @SerializedName("2017.MAY.23")
                private List<?> _$_2017MAY23283; // FIXME check this code
                @SerializedName("2017.MAY.20")
                private List<?> _$_2017MAY20224; // FIXME check this code
                @SerializedName("2017.MAY.18")
                private List<?> _$_2017MAY18270; // FIXME check this code
                @SerializedName("2017.MAY.15")
                private List<?> _$_2017MAY15304; // FIXME check this code
                @SerializedName("2017.MAY.12")
                private List<?> _$_2017MAY12131; // FIXME check this code
                @SerializedName("2017.MAY.09")
                private List<?> _$_2017MAY09105; // FIXME check this code
                @SerializedName("2017.MAY.06")
                private List<?> _$_2017MAY06153; // FIXME check this code
                @SerializedName("2017.MAY.04")
                private List<?> _$_2017MAY0436; // FIXME check this code
                @SerializedName("2017.MAY.03")
                private List<?> _$_2017MAY0344; // FIXME check this code
                @SerializedName("2017.MAY.01")
                private List<?> _$_2017MAY0156; // FIXME check this code
                @SerializedName("2017.APR.28")
                private List<?> _$_2017APR28291; // FIXME check this code
                @SerializedName("2017.APR.24")
                private List<?> _$_2017APR2463; // FIXME check this code
                @SerializedName("2017.APR.21")
                private List<?> _$_2017APR2170; // FIXME check this code
                @SerializedName("2017.APR.19")
                private List<?> _$_2017APR19130; // FIXME check this code
                @SerializedName("2017.APR.17")
                private List<?> _$_2017APR17148; // FIXME check this code
                @SerializedName("2017.APR.15")
                private List<?> _$_2017APR1570; // FIXME check this code
                @SerializedName("2017.APR.14")
                private List<?> _$_2017APR14111; // FIXME check this code
                @SerializedName("2017.APR.13")
                private List<?> _$_2017APR13231; // FIXME check this code
                @SerializedName("2017.APR.12")
                private List<?> _$_2017APR12127; // FIXME check this code
                @SerializedName("2017.APR.10")
                private List<?> _$_2017APR10241; // FIXME check this code
                @SerializedName("2017.APR.07")
                private List<?> _$_2017APR07175; // FIXME check this code
                @SerializedName("2017.APR.06")
                private List<?> _$_2017APR06293; // FIXME check this code
                @SerializedName("2017.APR.04")
                private List<?> _$_2017APR04184; // FIXME check this code
                @SerializedName("2017.APR.02")
                private List<?> _$_2017APR02134; // FIXME check this code
                @SerializedName("2017.MAR.30")
                private List<?> _$_2017MAR30198; // FIXME check this code
                @SerializedName("2017.MAR.27")
                private List<?> _$_2017MAR27199; // FIXME check this code
                @SerializedName("2017.MAR.25")
                private List<?> _$_2017MAR25173; // FIXME check this code
                @SerializedName("2017.MAR.24")
                private List<?> _$_2017MAR2476; // FIXME check this code
                @SerializedName("2017.MAR.23")
                private List<?> _$_2017MAR23255; // FIXME check this code
                @SerializedName("2017.MAR.21")
                private List<?> _$_2017MAR21261; // FIXME check this code
                @SerializedName("2017.MAR.20")
                private List<?> _$_2017MAR20135; // FIXME check this code
                @SerializedName("2017.MAR.19")
                private List<?> _$_2017MAR19296; // FIXME check this code
                @SerializedName("2017.MAR.18")
                private List<?> _$_2017MAR18255; // FIXME check this code
                @SerializedName("2017.MAR.17")
                private List<?> _$_2017MAR17302; // FIXME check this code
                @SerializedName("2017.MAR.16")
                private List<?> _$_2017MAR16313; // FIXME check this code
                @SerializedName("2017.MAR.15")
                private List<?> _$_2017MAR15203; // FIXME check this code
                @SerializedName("2017.MAR.14")
                private List<?> _$_2017MAR14153; // FIXME check this code
                @SerializedName("2017.MAR.13")
                private List<?> _$_2017MAR13142; // FIXME check this code
                @SerializedName("2017.MAR.12")
                private List<?> _$_2017MAR1281; // FIXME check this code
                @SerializedName("2017.MAR.09")
                private List<?> _$_2017MAR09132; // FIXME check this code
                @SerializedName("2017.MAR.08")
                private List<?> _$_2017MAR08158; // FIXME check this code
                @SerializedName("2017.MAR.06")
                private List<?> _$_2017MAR06268; // FIXME check this code
                @SerializedName("2017.MAR.04")
                private List<?> _$_2017MAR04134; // FIXME check this code
                @SerializedName("2017.MAR.03")
                private List<?> _$_2017MAR03104; // FIXME check this code
                @SerializedName("2017.MAR.02")
                private List<?> _$_2017MAR02232; // FIXME check this code
                @SerializedName("2017.FEB.28")
                private List<?> _$_2017FEB28201; // FIXME check this code
                @SerializedName("2017.FEB.27")
                private List<?> _$_2017FEB27232; // FIXME check this code
                @SerializedName("2017.FEB.24")
                private List<?> _$_2017FEB2484; // FIXME check this code
                @SerializedName("2017.FEB.22")
                private List<?> _$_2017FEB2285; // FIXME check this code
                @SerializedName("2017.FEB.18")
                private List<?> _$_2017FEB18117; // FIXME check this code
                @SerializedName("2017.FEB.17")
                private List<?> _$_2017FEB17107; // FIXME check this code
                @SerializedName("2017.FEB.16")
                private List<?> _$_2017FEB16124; // FIXME check this code
                @SerializedName("2017.FEB.15")
                private List<?> _$_2017FEB15277; // FIXME check this code
                @SerializedName("2017.FEB.14")
                private List<?> _$_2017FEB14139; // FIXME check this code
                @SerializedName("2017.FEB.10")
                private List<?> _$_2017FEB10145; // FIXME check this code
                @SerializedName("2017.FEB.09")
                private List<?> _$_2017FEB0948; // FIXME check this code
                @SerializedName("2017.FEB.08")
                private List<?> _$_2017FEB08166; // FIXME check this code
                @SerializedName("2017.FEB.07")
                private List<?> _$_2017FEB07307; // FIXME check this code
                @SerializedName("2017.FEB.05")
                private List<?> _$_2017FEB05226; // FIXME check this code
                @SerializedName("2017.FEB.04")
                private List<?> _$_2017FEB04161; // FIXME check this code
                @SerializedName("2017.FEB.03")
                private List<?> _$_2017FEB03230; // FIXME check this code
                @SerializedName("2017.JAN.24")
                private List<?> _$_2017JAN24232; // FIXME check this code
                @SerializedName("2017.JAN.23")
                private List<?> _$_2017JAN23133; // FIXME check this code
                @SerializedName("2017.JAN.22")
                private List<?> _$_2017JAN22207; // FIXME check this code
                @SerializedName("2017.JAN.21")
                private List<?> _$_2017JAN21269; // FIXME check this code
                @SerializedName("2017.JAN.18")
                private List<?> _$_2017JAN18278; // FIXME check this code
                @SerializedName("2017.JAN.17")
                private List<?> _$_2017JAN1735; // FIXME check this code
                @SerializedName("2017.JAN.16")
                private List<?> _$_2017JAN16101; // FIXME check this code
                @SerializedName("2017.JAN.15")
                private List<?> _$_2017JAN1534; // FIXME check this code
                @SerializedName("2017.JAN.13")
                private List<?> _$_2017JAN1399; // FIXME check this code
                @SerializedName("2017.JAN.11")
                private List<?> _$_2017JAN1139; // FIXME check this code
                @SerializedName("2017.JAN.09")
                private List<?> _$_2017JAN09107; // FIXME check this code
                @SerializedName("2017.JAN.08")
                private List<?> _$_2017JAN08252; // FIXME check this code

                public List<?> get_$_2017JUL0838() {
                    return _$_2017JUL0838;
                }

                public void set_$_2017JUL0838(List<?> _$_2017JUL0838) {
                    this._$_2017JUL0838 = _$_2017JUL0838;
                }

                public List<?> get_$_2017JUL07321() {
                    return _$_2017JUL07321;
                }

                public void set_$_2017JUL07321(List<?> _$_2017JUL07321) {
                    this._$_2017JUL07321 = _$_2017JUL07321;
                }

                public List<?> get_$_2017JUL05194() {
                    return _$_2017JUL05194;
                }

                public void set_$_2017JUL05194(List<?> _$_2017JUL05194) {
                    this._$_2017JUL05194 = _$_2017JUL05194;
                }

                public List<?> get_$_2017JUL0236() {
                    return _$_2017JUL0236;
                }

                public void set_$_2017JUL0236(List<?> _$_2017JUL0236) {
                    this._$_2017JUL0236 = _$_2017JUL0236;
                }

                public List<?> get_$_2017JUN29129() {
                    return _$_2017JUN29129;
                }

                public void set_$_2017JUN29129(List<?> _$_2017JUN29129) {
                    this._$_2017JUN29129 = _$_2017JUN29129;
                }

                public List<?> get_$_2017JUN2777() {
                    return _$_2017JUN2777;
                }

                public void set_$_2017JUN2777(List<?> _$_2017JUN2777) {
                    this._$_2017JUN2777 = _$_2017JUN2777;
                }

                public List<?> get_$_2017JUN26104() {
                    return _$_2017JUN26104;
                }

                public void set_$_2017JUN26104(List<?> _$_2017JUN26104) {
                    this._$_2017JUN26104 = _$_2017JUN26104;
                }

                public List<?> get_$_2017JUN2036() {
                    return _$_2017JUN2036;
                }

                public void set_$_2017JUN2036(List<?> _$_2017JUN2036) {
                    this._$_2017JUN2036 = _$_2017JUN2036;
                }

                public List<?> get_$_2017JUN19118() {
                    return _$_2017JUN19118;
                }

                public void set_$_2017JUN19118(List<?> _$_2017JUN19118) {
                    this._$_2017JUN19118 = _$_2017JUN19118;
                }

                public List<?> get_$_2017JUN1576() {
                    return _$_2017JUN1576;
                }

                public void set_$_2017JUN1576(List<?> _$_2017JUN1576) {
                    this._$_2017JUN1576 = _$_2017JUN1576;
                }

                public List<?> get_$_2017JUN10332() {
                    return _$_2017JUN10332;
                }

                public void set_$_2017JUN10332(List<?> _$_2017JUN10332) {
                    this._$_2017JUN10332 = _$_2017JUN10332;
                }

                public List<?> get_$_2017JUN0649() {
                    return _$_2017JUN0649;
                }

                public void set_$_2017JUN0649(List<?> _$_2017JUN0649) {
                    this._$_2017JUN0649 = _$_2017JUN0649;
                }

                public List<?> get_$_2017JUN05230() {
                    return _$_2017JUN05230;
                }

                public void set_$_2017JUN05230(List<?> _$_2017JUN05230) {
                    this._$_2017JUN05230 = _$_2017JUN05230;
                }

                public List<?> get_$_2017MAY23283() {
                    return _$_2017MAY23283;
                }

                public void set_$_2017MAY23283(List<?> _$_2017MAY23283) {
                    this._$_2017MAY23283 = _$_2017MAY23283;
                }

                public List<?> get_$_2017MAY20224() {
                    return _$_2017MAY20224;
                }

                public void set_$_2017MAY20224(List<?> _$_2017MAY20224) {
                    this._$_2017MAY20224 = _$_2017MAY20224;
                }

                public List<?> get_$_2017MAY18270() {
                    return _$_2017MAY18270;
                }

                public void set_$_2017MAY18270(List<?> _$_2017MAY18270) {
                    this._$_2017MAY18270 = _$_2017MAY18270;
                }

                public List<?> get_$_2017MAY15304() {
                    return _$_2017MAY15304;
                }

                public void set_$_2017MAY15304(List<?> _$_2017MAY15304) {
                    this._$_2017MAY15304 = _$_2017MAY15304;
                }

                public List<?> get_$_2017MAY12131() {
                    return _$_2017MAY12131;
                }

                public void set_$_2017MAY12131(List<?> _$_2017MAY12131) {
                    this._$_2017MAY12131 = _$_2017MAY12131;
                }

                public List<?> get_$_2017MAY09105() {
                    return _$_2017MAY09105;
                }

                public void set_$_2017MAY09105(List<?> _$_2017MAY09105) {
                    this._$_2017MAY09105 = _$_2017MAY09105;
                }

                public List<?> get_$_2017MAY06153() {
                    return _$_2017MAY06153;
                }

                public void set_$_2017MAY06153(List<?> _$_2017MAY06153) {
                    this._$_2017MAY06153 = _$_2017MAY06153;
                }

                public List<?> get_$_2017MAY0436() {
                    return _$_2017MAY0436;
                }

                public void set_$_2017MAY0436(List<?> _$_2017MAY0436) {
                    this._$_2017MAY0436 = _$_2017MAY0436;
                }

                public List<?> get_$_2017MAY0344() {
                    return _$_2017MAY0344;
                }

                public void set_$_2017MAY0344(List<?> _$_2017MAY0344) {
                    this._$_2017MAY0344 = _$_2017MAY0344;
                }

                public List<?> get_$_2017MAY0156() {
                    return _$_2017MAY0156;
                }

                public void set_$_2017MAY0156(List<?> _$_2017MAY0156) {
                    this._$_2017MAY0156 = _$_2017MAY0156;
                }

                public List<?> get_$_2017APR28291() {
                    return _$_2017APR28291;
                }

                public void set_$_2017APR28291(List<?> _$_2017APR28291) {
                    this._$_2017APR28291 = _$_2017APR28291;
                }

                public List<?> get_$_2017APR2463() {
                    return _$_2017APR2463;
                }

                public void set_$_2017APR2463(List<?> _$_2017APR2463) {
                    this._$_2017APR2463 = _$_2017APR2463;
                }

                public List<?> get_$_2017APR2170() {
                    return _$_2017APR2170;
                }

                public void set_$_2017APR2170(List<?> _$_2017APR2170) {
                    this._$_2017APR2170 = _$_2017APR2170;
                }

                public List<?> get_$_2017APR19130() {
                    return _$_2017APR19130;
                }

                public void set_$_2017APR19130(List<?> _$_2017APR19130) {
                    this._$_2017APR19130 = _$_2017APR19130;
                }

                public List<?> get_$_2017APR17148() {
                    return _$_2017APR17148;
                }

                public void set_$_2017APR17148(List<?> _$_2017APR17148) {
                    this._$_2017APR17148 = _$_2017APR17148;
                }

                public List<?> get_$_2017APR1570() {
                    return _$_2017APR1570;
                }

                public void set_$_2017APR1570(List<?> _$_2017APR1570) {
                    this._$_2017APR1570 = _$_2017APR1570;
                }

                public List<?> get_$_2017APR14111() {
                    return _$_2017APR14111;
                }

                public void set_$_2017APR14111(List<?> _$_2017APR14111) {
                    this._$_2017APR14111 = _$_2017APR14111;
                }

                public List<?> get_$_2017APR13231() {
                    return _$_2017APR13231;
                }

                public void set_$_2017APR13231(List<?> _$_2017APR13231) {
                    this._$_2017APR13231 = _$_2017APR13231;
                }

                public List<?> get_$_2017APR12127() {
                    return _$_2017APR12127;
                }

                public void set_$_2017APR12127(List<?> _$_2017APR12127) {
                    this._$_2017APR12127 = _$_2017APR12127;
                }

                public List<?> get_$_2017APR10241() {
                    return _$_2017APR10241;
                }

                public void set_$_2017APR10241(List<?> _$_2017APR10241) {
                    this._$_2017APR10241 = _$_2017APR10241;
                }

                public List<?> get_$_2017APR07175() {
                    return _$_2017APR07175;
                }

                public void set_$_2017APR07175(List<?> _$_2017APR07175) {
                    this._$_2017APR07175 = _$_2017APR07175;
                }

                public List<?> get_$_2017APR06293() {
                    return _$_2017APR06293;
                }

                public void set_$_2017APR06293(List<?> _$_2017APR06293) {
                    this._$_2017APR06293 = _$_2017APR06293;
                }

                public List<?> get_$_2017APR04184() {
                    return _$_2017APR04184;
                }

                public void set_$_2017APR04184(List<?> _$_2017APR04184) {
                    this._$_2017APR04184 = _$_2017APR04184;
                }

                public List<?> get_$_2017APR02134() {
                    return _$_2017APR02134;
                }

                public void set_$_2017APR02134(List<?> _$_2017APR02134) {
                    this._$_2017APR02134 = _$_2017APR02134;
                }

                public List<?> get_$_2017MAR30198() {
                    return _$_2017MAR30198;
                }

                public void set_$_2017MAR30198(List<?> _$_2017MAR30198) {
                    this._$_2017MAR30198 = _$_2017MAR30198;
                }

                public List<?> get_$_2017MAR27199() {
                    return _$_2017MAR27199;
                }

                public void set_$_2017MAR27199(List<?> _$_2017MAR27199) {
                    this._$_2017MAR27199 = _$_2017MAR27199;
                }

                public List<?> get_$_2017MAR25173() {
                    return _$_2017MAR25173;
                }

                public void set_$_2017MAR25173(List<?> _$_2017MAR25173) {
                    this._$_2017MAR25173 = _$_2017MAR25173;
                }

                public List<?> get_$_2017MAR2476() {
                    return _$_2017MAR2476;
                }

                public void set_$_2017MAR2476(List<?> _$_2017MAR2476) {
                    this._$_2017MAR2476 = _$_2017MAR2476;
                }

                public List<?> get_$_2017MAR23255() {
                    return _$_2017MAR23255;
                }

                public void set_$_2017MAR23255(List<?> _$_2017MAR23255) {
                    this._$_2017MAR23255 = _$_2017MAR23255;
                }

                public List<?> get_$_2017MAR21261() {
                    return _$_2017MAR21261;
                }

                public void set_$_2017MAR21261(List<?> _$_2017MAR21261) {
                    this._$_2017MAR21261 = _$_2017MAR21261;
                }

                public List<?> get_$_2017MAR20135() {
                    return _$_2017MAR20135;
                }

                public void set_$_2017MAR20135(List<?> _$_2017MAR20135) {
                    this._$_2017MAR20135 = _$_2017MAR20135;
                }

                public List<?> get_$_2017MAR19296() {
                    return _$_2017MAR19296;
                }

                public void set_$_2017MAR19296(List<?> _$_2017MAR19296) {
                    this._$_2017MAR19296 = _$_2017MAR19296;
                }

                public List<?> get_$_2017MAR18255() {
                    return _$_2017MAR18255;
                }

                public void set_$_2017MAR18255(List<?> _$_2017MAR18255) {
                    this._$_2017MAR18255 = _$_2017MAR18255;
                }

                public List<?> get_$_2017MAR17302() {
                    return _$_2017MAR17302;
                }

                public void set_$_2017MAR17302(List<?> _$_2017MAR17302) {
                    this._$_2017MAR17302 = _$_2017MAR17302;
                }

                public List<?> get_$_2017MAR16313() {
                    return _$_2017MAR16313;
                }

                public void set_$_2017MAR16313(List<?> _$_2017MAR16313) {
                    this._$_2017MAR16313 = _$_2017MAR16313;
                }

                public List<?> get_$_2017MAR15203() {
                    return _$_2017MAR15203;
                }

                public void set_$_2017MAR15203(List<?> _$_2017MAR15203) {
                    this._$_2017MAR15203 = _$_2017MAR15203;
                }

                public List<?> get_$_2017MAR14153() {
                    return _$_2017MAR14153;
                }

                public void set_$_2017MAR14153(List<?> _$_2017MAR14153) {
                    this._$_2017MAR14153 = _$_2017MAR14153;
                }

                public List<?> get_$_2017MAR13142() {
                    return _$_2017MAR13142;
                }

                public void set_$_2017MAR13142(List<?> _$_2017MAR13142) {
                    this._$_2017MAR13142 = _$_2017MAR13142;
                }

                public List<?> get_$_2017MAR1281() {
                    return _$_2017MAR1281;
                }

                public void set_$_2017MAR1281(List<?> _$_2017MAR1281) {
                    this._$_2017MAR1281 = _$_2017MAR1281;
                }

                public List<?> get_$_2017MAR09132() {
                    return _$_2017MAR09132;
                }

                public void set_$_2017MAR09132(List<?> _$_2017MAR09132) {
                    this._$_2017MAR09132 = _$_2017MAR09132;
                }

                public List<?> get_$_2017MAR08158() {
                    return _$_2017MAR08158;
                }

                public void set_$_2017MAR08158(List<?> _$_2017MAR08158) {
                    this._$_2017MAR08158 = _$_2017MAR08158;
                }

                public List<?> get_$_2017MAR06268() {
                    return _$_2017MAR06268;
                }

                public void set_$_2017MAR06268(List<?> _$_2017MAR06268) {
                    this._$_2017MAR06268 = _$_2017MAR06268;
                }

                public List<?> get_$_2017MAR04134() {
                    return _$_2017MAR04134;
                }

                public void set_$_2017MAR04134(List<?> _$_2017MAR04134) {
                    this._$_2017MAR04134 = _$_2017MAR04134;
                }

                public List<?> get_$_2017MAR03104() {
                    return _$_2017MAR03104;
                }

                public void set_$_2017MAR03104(List<?> _$_2017MAR03104) {
                    this._$_2017MAR03104 = _$_2017MAR03104;
                }

                public List<?> get_$_2017MAR02232() {
                    return _$_2017MAR02232;
                }

                public void set_$_2017MAR02232(List<?> _$_2017MAR02232) {
                    this._$_2017MAR02232 = _$_2017MAR02232;
                }

                public List<?> get_$_2017FEB28201() {
                    return _$_2017FEB28201;
                }

                public void set_$_2017FEB28201(List<?> _$_2017FEB28201) {
                    this._$_2017FEB28201 = _$_2017FEB28201;
                }

                public List<?> get_$_2017FEB27232() {
                    return _$_2017FEB27232;
                }

                public void set_$_2017FEB27232(List<?> _$_2017FEB27232) {
                    this._$_2017FEB27232 = _$_2017FEB27232;
                }

                public List<?> get_$_2017FEB2484() {
                    return _$_2017FEB2484;
                }

                public void set_$_2017FEB2484(List<?> _$_2017FEB2484) {
                    this._$_2017FEB2484 = _$_2017FEB2484;
                }

                public List<?> get_$_2017FEB2285() {
                    return _$_2017FEB2285;
                }

                public void set_$_2017FEB2285(List<?> _$_2017FEB2285) {
                    this._$_2017FEB2285 = _$_2017FEB2285;
                }

                public List<?> get_$_2017FEB18117() {
                    return _$_2017FEB18117;
                }

                public void set_$_2017FEB18117(List<?> _$_2017FEB18117) {
                    this._$_2017FEB18117 = _$_2017FEB18117;
                }

                public List<?> get_$_2017FEB17107() {
                    return _$_2017FEB17107;
                }

                public void set_$_2017FEB17107(List<?> _$_2017FEB17107) {
                    this._$_2017FEB17107 = _$_2017FEB17107;
                }

                public List<?> get_$_2017FEB16124() {
                    return _$_2017FEB16124;
                }

                public void set_$_2017FEB16124(List<?> _$_2017FEB16124) {
                    this._$_2017FEB16124 = _$_2017FEB16124;
                }

                public List<?> get_$_2017FEB15277() {
                    return _$_2017FEB15277;
                }

                public void set_$_2017FEB15277(List<?> _$_2017FEB15277) {
                    this._$_2017FEB15277 = _$_2017FEB15277;
                }

                public List<?> get_$_2017FEB14139() {
                    return _$_2017FEB14139;
                }

                public void set_$_2017FEB14139(List<?> _$_2017FEB14139) {
                    this._$_2017FEB14139 = _$_2017FEB14139;
                }

                public List<?> get_$_2017FEB10145() {
                    return _$_2017FEB10145;
                }

                public void set_$_2017FEB10145(List<?> _$_2017FEB10145) {
                    this._$_2017FEB10145 = _$_2017FEB10145;
                }

                public List<?> get_$_2017FEB0948() {
                    return _$_2017FEB0948;
                }

                public void set_$_2017FEB0948(List<?> _$_2017FEB0948) {
                    this._$_2017FEB0948 = _$_2017FEB0948;
                }

                public List<?> get_$_2017FEB08166() {
                    return _$_2017FEB08166;
                }

                public void set_$_2017FEB08166(List<?> _$_2017FEB08166) {
                    this._$_2017FEB08166 = _$_2017FEB08166;
                }

                public List<?> get_$_2017FEB07307() {
                    return _$_2017FEB07307;
                }

                public void set_$_2017FEB07307(List<?> _$_2017FEB07307) {
                    this._$_2017FEB07307 = _$_2017FEB07307;
                }

                public List<?> get_$_2017FEB05226() {
                    return _$_2017FEB05226;
                }

                public void set_$_2017FEB05226(List<?> _$_2017FEB05226) {
                    this._$_2017FEB05226 = _$_2017FEB05226;
                }

                public List<?> get_$_2017FEB04161() {
                    return _$_2017FEB04161;
                }

                public void set_$_2017FEB04161(List<?> _$_2017FEB04161) {
                    this._$_2017FEB04161 = _$_2017FEB04161;
                }

                public List<?> get_$_2017FEB03230() {
                    return _$_2017FEB03230;
                }

                public void set_$_2017FEB03230(List<?> _$_2017FEB03230) {
                    this._$_2017FEB03230 = _$_2017FEB03230;
                }

                public List<?> get_$_2017JAN24232() {
                    return _$_2017JAN24232;
                }

                public void set_$_2017JAN24232(List<?> _$_2017JAN24232) {
                    this._$_2017JAN24232 = _$_2017JAN24232;
                }

                public List<?> get_$_2017JAN23133() {
                    return _$_2017JAN23133;
                }

                public void set_$_2017JAN23133(List<?> _$_2017JAN23133) {
                    this._$_2017JAN23133 = _$_2017JAN23133;
                }

                public List<?> get_$_2017JAN22207() {
                    return _$_2017JAN22207;
                }

                public void set_$_2017JAN22207(List<?> _$_2017JAN22207) {
                    this._$_2017JAN22207 = _$_2017JAN22207;
                }

                public List<?> get_$_2017JAN21269() {
                    return _$_2017JAN21269;
                }

                public void set_$_2017JAN21269(List<?> _$_2017JAN21269) {
                    this._$_2017JAN21269 = _$_2017JAN21269;
                }

                public List<?> get_$_2017JAN18278() {
                    return _$_2017JAN18278;
                }

                public void set_$_2017JAN18278(List<?> _$_2017JAN18278) {
                    this._$_2017JAN18278 = _$_2017JAN18278;
                }

                public List<?> get_$_2017JAN1735() {
                    return _$_2017JAN1735;
                }

                public void set_$_2017JAN1735(List<?> _$_2017JAN1735) {
                    this._$_2017JAN1735 = _$_2017JAN1735;
                }

                public List<?> get_$_2017JAN16101() {
                    return _$_2017JAN16101;
                }

                public void set_$_2017JAN16101(List<?> _$_2017JAN16101) {
                    this._$_2017JAN16101 = _$_2017JAN16101;
                }

                public List<?> get_$_2017JAN1534() {
                    return _$_2017JAN1534;
                }

                public void set_$_2017JAN1534(List<?> _$_2017JAN1534) {
                    this._$_2017JAN1534 = _$_2017JAN1534;
                }

                public List<?> get_$_2017JAN1399() {
                    return _$_2017JAN1399;
                }

                public void set_$_2017JAN1399(List<?> _$_2017JAN1399) {
                    this._$_2017JAN1399 = _$_2017JAN1399;
                }

                public List<?> get_$_2017JAN1139() {
                    return _$_2017JAN1139;
                }

                public void set_$_2017JAN1139(List<?> _$_2017JAN1139) {
                    this._$_2017JAN1139 = _$_2017JAN1139;
                }

                public List<?> get_$_2017JAN09107() {
                    return _$_2017JAN09107;
                }

                public void set_$_2017JAN09107(List<?> _$_2017JAN09107) {
                    this._$_2017JAN09107 = _$_2017JAN09107;
                }

                public List<?> get_$_2017JAN08252() {
                    return _$_2017JAN08252;
                }

                public void set_$_2017JAN08252(List<?> _$_2017JAN08252) {
                    this._$_2017JAN08252 = _$_2017JAN08252;
                }
            }


        }
    }
}
