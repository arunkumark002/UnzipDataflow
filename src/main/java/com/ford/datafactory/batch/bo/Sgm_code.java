package com.ford.datafactory.batch.bo;



import java.util.ArrayList;

public class Sgm_code{
    private ArrayList <Included> included = new ArrayList <Included>();
    private ArrayList <Excluded> excluded = new ArrayList <Excluded>();
    public static class Included{
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public ArrayList <Included> getIncluded() {
        return included;
    }

    public void setIncluded(ArrayList <Included> included) {
        this.included = included;
    }
    public ArrayList <Excluded> getExcluded() {
        return excluded;
    }

    public void setExcluded(ArrayList <Excluded> excluded) {
        this.excluded = excluded;
    }
    public static class Excluded{
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
