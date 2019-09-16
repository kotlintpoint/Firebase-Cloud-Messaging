package com.example.android1to3.online;

import com.google.gson.annotations.SerializedName;
import java.util.List;




public class CountryRoot {

    @Override
    public String toString() {
        return "CountryRoot{" +
                "name='" + name + '\'' +
                ", topLevelDomain=" + topLevelDomain +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", callingCodes=" + callingCodes +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + altSpellings +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population=" + population +
                ", latlng=" + latlng +
                ", demonym='" + demonym + '\'' +
                ", area=" + area +
                ", gini=" + gini +
                ", timezones=" + timezones +
                ", borders=" + borders +
                ", nativeName='" + nativeName + '\'' +
                ", numericCode='" + numericCode + '\'' +
                ", currencies=" + currencies +
                ", languages=" + languages +
                ", translations=" + translations +
                ", flag='" + flag + '\'' +
                ", regionalBlocs=" + regionalBlocs +
                ", cioc='" + cioc + '\'' +
                '}';
    }

    @SerializedName("name")
    private final String name;

    @SerializedName("topLevelDomain")
    private final List<String> topLevelDomain;

    @SerializedName("alpha2Code")
    private final String alpha2Code;

    @SerializedName("alpha3Code")
    private final String alpha3Code;

    @SerializedName("callingCodes")
    private final List<String> callingCodes;

    @SerializedName("capital")
    private final String capital;

    @SerializedName("altSpellings")
    private final List<String> altSpellings;

    @SerializedName("region")
    private final String region;

    @SerializedName("subregion")
    private final String subregion;

    @SerializedName("population")
    private final int population;

    @SerializedName("latlng")
    private final List<Double> latlng;

    @SerializedName("demonym")
    private final String demonym;

    @SerializedName("area")
    private final double area;

    @SerializedName("gini")
    private final double gini;

    @SerializedName("timezones")
    private final List<String> timezones;

    @SerializedName("borders")
    private final List<String> borders;

    @SerializedName("nativeName")
    private final String nativeName;

    @SerializedName("numericCode")
    private final String numericCode;

    @SerializedName("currencies")
    private final List<Currencies> currencies;

    @SerializedName("languages")
    private final List<Languages> languages;

    @SerializedName("translations")
    private final Translations translations;

    @SerializedName("flag")
    private final String flag;

    @SerializedName("regionalBlocs")
    private final List<RegionalBlocs> regionalBlocs;

    @SerializedName("cioc")
    private final String cioc;

    public CountryRoot(String name, List<String> topLevelDomain, String alpha2Code,
                       String alpha3Code, List<String> callingCodes, String capital, List<String> altSpellings,
                       String region, String subregion, int population, List<Double> latlng, String demonym,
                       int area, double gini, List<String> timezones, List<String> borders, String nativeName,
                       String numericCode, List<Currencies> currencies, List<Languages> languages,
                       Translations translations, String flag, List<RegionalBlocs> regionalBlocs,
                       String cioc) {
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translations = translations;
        this.flag = flag;
        this.regionalBlocs = regionalBlocs;
        this.cioc = cioc;
    }

    public String getName() {
        return name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public int getPopulation() {
        return population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public double getArea() {
        return area;
    }

    public double getGini() {
        return gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public String getFlag() {
        return flag;
    }

    public List<RegionalBlocs> getRegionalBlocs() {
        return regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public static class Currencies {
        @SerializedName("code")
        private final String code;

        @SerializedName("name")
        private final String name;

        @SerializedName("symbol")
        private final String symbol;

        public Currencies(String code, String name, String symbol) {
            this.code = code;
            this.name = name;
            this.symbol = symbol;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public static class Languages {
        @SerializedName("iso639_1")
        private final String iso6391;

        @SerializedName("iso639_2")
        private final String iso6392;

        @SerializedName("name")
        private final String name;

        @SerializedName("nativeName")
        private final String nativeName;

        public Languages(String iso6391, String iso6392, String name, String nativeName) {
            this.iso6391 = iso6391;
            this.iso6392 = iso6392;
            this.name = name;
            this.nativeName = nativeName;
        }

        public String getIso6391() {
            return iso6391;
        }

        public String getIso6392() {
            return iso6392;
        }

        public String getName() {
            return name;
        }

        public String getNativeName() {
            return nativeName;
        }
    }

    public static class Translations {
        @SerializedName("de")
        private final String de;

        @SerializedName("es")
        private final String es;

        @SerializedName("fr")
        private final String fr;

        @SerializedName("ja")
        private final String ja;

        @SerializedName("it")
        private final String it;

        @SerializedName("br")
        private final String br;

        @SerializedName("pt")
        private final String pt;

        @SerializedName("nl")
        private final String nl;

        @SerializedName("hr")
        private final String hr;

        @SerializedName("fa")
        private final String fa;

        public Translations(String de, String es, String fr, String ja, String it, String br,
                            String pt, String nl, String hr, String fa) {
            this.de = de;
            this.es = es;
            this.fr = fr;
            this.ja = ja;
            this.it = it;
            this.br = br;
            this.pt = pt;
            this.nl = nl;
            this.hr = hr;
            this.fa = fa;
        }

        public String getDe() {
            return de;
        }

        public String getEs() {
            return es;
        }

        public String getFr() {
            return fr;
        }

        public String getJa() {
            return ja;
        }

        public String getIt() {
            return it;
        }

        public String getBr() {
            return br;
        }

        public String getPt() {
            return pt;
        }

        public String getNl() {
            return nl;
        }

        public String getHr() {
            return hr;
        }

        public String getFa() {
            return fa;
        }
    }

    public static class RegionalBlocs {
        @SerializedName("acronym")
        private final String acronym;

        @SerializedName("name")
        private final String name;

        @SerializedName("otherAcronyms")
        private final List<Object> otherAcronyms;

        @SerializedName("otherNames")
        private final List<Object> otherNames;

        public RegionalBlocs(String acronym, String name, List<Object> otherAcronyms,
                             List<Object> otherNames) {
            this.acronym = acronym;
            this.name = name;
            this.otherAcronyms = otherAcronyms;
            this.otherNames = otherNames;
        }

        public String getAcronym() {
            return acronym;
        }

        public String getName() {
            return name;
        }

        public List<Object> getOtherAcronyms() {
            return otherAcronyms;
        }

        public List<Object> getOtherNames() {
            return otherNames;
        }
    }
}
