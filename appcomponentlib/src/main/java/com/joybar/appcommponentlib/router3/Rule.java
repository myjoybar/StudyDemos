package com.joybar.appcommponentlib.router3;

/**
 * Created by joybar on 04/11/2017.
 */

public class Rule {

    private String module = "";
    private String pattern = "";
    private String scheme = "";
    private Class classz = null;


    public Rule(String module, String scheme, Class classz) {
        this.module = module;
        this.scheme = scheme;
        this.classz = classz;
    }

    public Rule(String module, String pattern, String scheme) {
        this.module = module;
        this.pattern = pattern;
        this.scheme = scheme;
    }


    public Class getClassz() {
        return classz;
    }

    public void setClassz(Class classz) {
        this.classz = classz;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


    @Override
    public int hashCode() {
        return pattern.hashCode() + scheme.hashCode() + module.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj instanceof Rule) {
            Rule rule = (Rule) obj;
            return (scheme.equals(rule.scheme) && pattern.equals(rule.pattern) && module.equals(rule.module));
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Rule{" + "module='" + module + '\'' + ", pattern='" + pattern + '\'' + ", " +
                "scheme='" + scheme + '\'' + ", classz=" + classz + '}';
    }

    public static class RuleKey {

        private String module;
        private String pattern;
        private String scheme;


        public RuleKey(String module, String pattern, String scheme) {
            this.module = module;
            this.pattern = pattern;
            this.scheme = scheme;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        @Override
        public int hashCode() {
            return pattern.hashCode() + scheme.hashCode() + module.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof RuleKey) {
                RuleKey rule = (RuleKey) obj;
                return (scheme.equals(rule.scheme) && pattern.equals(rule.pattern) && module
                        .equals(rule.module));
            }
            return super.equals(obj);
        }
    }

}
