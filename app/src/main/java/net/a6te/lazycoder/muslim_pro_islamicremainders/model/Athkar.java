package net.a6te.lazycoder.muslim_pro_islamicremainders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Athkar {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("updateCode")
    @Expose
    private String updateCode;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Amharic {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Arabic {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Azerbaijani {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Azerus {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class BehasaMelayu {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Bengali {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class ChineseSimplified {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }
    public class ChineseTraditional {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Data {

        @SerializedName("amharic")
        @Expose
        private List<Amharic> amharic = null;
        @SerializedName("arabic")
        @Expose
        private List<Arabic> arabic = null;
        @SerializedName("azerbaijani")
        @Expose
        private List<Azerbaijani> azerbaijani = null;
        @SerializedName("azeri")
        @Expose
        private List<Azerus> azeri = null;
        @SerializedName("behasa_melayu")
        @Expose
        private List<BehasaMelayu> behasaMelayu = null;
        @SerializedName("bengali")
        @Expose
        private List<Bengali> bengali = null;
        @SerializedName("chinese_simplified")
        @Expose
        private List<ChineseSimplified> chineseSimplified = null;
        @SerializedName("chinese_traditional")
        @Expose
        private List<ChineseTraditional> chineseTraditional = null;
        @SerializedName("english")
        @Expose
        private List<English> english = null;
        @SerializedName("french")
        @Expose
        private List<French> french = null;
        @SerializedName("german")
        @Expose
        private List<German> german = null;
        @SerializedName("hindi")
        @Expose
        private List<Hindi> hindi = null;
        @SerializedName("indonesian")
        @Expose
        private List<Indonesian> indonesian = null;
        @SerializedName("malay")
        @Expose
        private List<Malay> malay = null;
        @SerializedName("pashto")
        @Expose
        private List<Pashto> pashto = null;
        @SerializedName("persian")
        @Expose
        private List<Persian> persian = null;
        @SerializedName("russian")
        @Expose
        private List<Russian> russian = null;
        @SerializedName("spanish")
        @Expose
        private List<Spanish> spanish = null;
        @SerializedName("turkish")
        @Expose
        private List<Turkish> turkish = null;
        @SerializedName("urdu")
        @Expose
        private List<Urdu> urdu = null;

        public List<Amharic> getAmharic() {
            return amharic;
        }

        public void setAmharic(List<Amharic> amharic) {
            this.amharic = amharic;
        }

        public List<Arabic> getArabic() {
            return arabic;
        }

        public void setArabic(List<Arabic> arabic) {
            this.arabic = arabic;
        }

        public List<Azerbaijani> getAzerbaijani() {
            return azerbaijani;
        }

        public void setAzerbaijani(List<Azerbaijani> azerbaijani) {
            this.azerbaijani = azerbaijani;
        }

        public List<Azerus> getAzeri() {
            return azeri;
        }

        public void setAzeri(List<Azerus> azeri) {
            this.azeri = azeri;
        }

        public List<BehasaMelayu> getBehasaMelayu() {
            return behasaMelayu;
        }

        public void setBehasaMelayu(List<BehasaMelayu> behasaMelayu) {
            this.behasaMelayu = behasaMelayu;
        }

        public List<Bengali> getBengali() {
            return bengali;
        }

        public void setBengali(List<Bengali> bengali) {
            this.bengali = bengali;
        }

        public List<ChineseSimplified> getChineseSimplified() {
            return chineseSimplified;
        }

        public void setChineseSimplified(List<ChineseSimplified> chineseSimplified) {
            this.chineseSimplified = chineseSimplified;
        }

        public List<ChineseTraditional> getChineseTraditional() {
            return chineseTraditional;
        }

        public void setChineseTraditional(List<ChineseTraditional> chineseTraditional) {
            this.chineseTraditional = chineseTraditional;
        }

        public List<English> getEnglish() {
            return english;
        }

        public void setEnglish(List<English> english) {
            this.english = english;
        }

        public List<French> getFrench() {
            return french;
        }

        public void setFrench(List<French> french) {
            this.french = french;
        }

        public List<German> getGerman() {
            return german;
        }

        public void setGerman(List<German> german) {
            this.german = german;
        }

        public List<Hindi> getHindi() {
            return hindi;
        }

        public void setHindi(List<Hindi> hindi) {
            this.hindi = hindi;
        }

        public List<Indonesian> getIndonesian() {
            return indonesian;
        }

        public void setIndonesian(List<Indonesian> indonesian) {
            this.indonesian = indonesian;
        }

        public List<Malay> getMalay() {
            return malay;
        }

        public void setMalay(List<Malay> malay) {
            this.malay = malay;
        }

        public List<Pashto> getPashto() {
            return pashto;
        }

        public void setPashto(List<Pashto> pashto) {
            this.pashto = pashto;
        }

        public List<Persian> getPersian() {
            return persian;
        }

        public void setPersian(List<Persian> persian) {
            this.persian = persian;
        }

        public List<Russian> getRussian() {
            return russian;
        }

        public void setRussian(List<Russian> russian) {
            this.russian = russian;
        }

        public List<Spanish> getSpanish() {
            return spanish;
        }

        public void setSpanish(List<Spanish> spanish) {
            this.spanish = spanish;
        }

        public List<Turkish> getTurkish() {
            return turkish;
        }

        public void setTurkish(List<Turkish> turkish) {
            this.turkish = turkish;
        }

        public List<Urdu> getUrdu() {
            return urdu;
        }

        public void setUrdu(List<Urdu> urdu) {
            this.urdu = urdu;
        }

    }

    public class English {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class French {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class German {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Hindi {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Indonesian {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Malay {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Pashto {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Persian {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }
    public class Russian {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }
    public class Spanish {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Turkish {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    public class Urdu {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("atkhar")
        @Expose
        private String atkhar;
        @SerializedName("tag")
        @Expose
        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtkhar() {
            return atkhar;
        }

        public void setAtkhar(String atkhar) {
            this.atkhar = atkhar;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
