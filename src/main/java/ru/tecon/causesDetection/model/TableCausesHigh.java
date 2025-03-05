package ru.tecon.causesDetection.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class TableCausesHigh implements Serializable {
    private String filial;
    private String pred;
    private String chp;
    private String date;
    private String t7_base;
    private String t13_base;
    private String t7_now;
    private String t13_now;
    private String g7_base;
    private String g13_base;
    private String g13_now;
    private String g_water;
    private String qf_base_circ;
    private String qf_base_vvod;
    private String qf_base;
    private String qf_now_circ;
    private String qf_now_vvod;
    private String qf_now;
    private String eff_heat_fiz;
    private String eff_heat_money;

    public TableCausesHigh(String filial, String pred, String chp, String date, String t7_base, String t13_base, String t7_now, String t13_now, String g7_base, String g13_base, String g13_now, String g_water, String qf_base_circ, String qf_base_vvod, String qf_base, String qf_now_circ, String qf_now_vvod, String qf_now, String eff_heat_fiz, String eff_heat_money) {
        this.filial = filial;
        this.pred = pred;
        this.chp = chp;
        this.date = date;
        this.t7_base = t7_base;
        this.t13_base = t13_base;
        this.t7_now = t7_now;
        this.t13_now = t13_now;
        this.g7_base = g7_base;
        this.g13_base = g13_base;
        this.g13_now = g13_now;
        this.g_water = g_water;
        this.qf_base_circ = qf_base_circ;
        this.qf_base_vvod = qf_base_vvod;
        this.qf_base = qf_base;
        this.qf_now_circ = qf_now_circ;
        this.qf_now_vvod = qf_now_vvod;
        this.qf_now = qf_now;
        this.eff_heat_fiz = eff_heat_fiz;
        this.eff_heat_money = eff_heat_money;
    }

    public TableCausesHigh(String filial, String eff_heat_fiz, String eff_heat_money) {
        this.filial = filial;
        this.eff_heat_fiz = eff_heat_fiz;
        this.eff_heat_money = eff_heat_money;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getPred() {
        return pred;
    }

    public void setPred(String pred) {
        this.pred = pred;
    }

    public String getChp() {
        return chp;
    }

    public void setChp(String chp) {
        this.chp = chp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getT7_base() {
        return t7_base;
    }

    public void setT7_base(String t7_base) {
        this.t7_base = t7_base;
    }

    public String getT13_base() {
        return t13_base;
    }

    public void setT13_base(String t13_base) {
        this.t13_base = t13_base;
    }

    public String getT7_now() {
        return t7_now;
    }

    public void setT7_now(String t7_now) {
        this.t7_now = t7_now;
    }

    public String getT13_now() {
        return t13_now;
    }

    public void setT13_now(String t13_now) {
        this.t13_now = t13_now;
    }

    public String getG7_base() {
        return g7_base;
    }

    public void setG7_base(String g7_base) {
        this.g7_base = g7_base;
    }

    public String getG13_base() {
        return g13_base;
    }

    public void setG13_base(String g13_base) {
        this.g13_base = g13_base;
    }

    public String getG13_now() {
        return g13_now;
    }

    public void setG13_now(String g13_now) {
        this.g13_now = g13_now;
    }

    public String getG_water() {
        return g_water;
    }

    public void setG_water(String g_water) {
        this.g_water = g_water;
    }

    public String getQf_base_circ() {
        return qf_base_circ;
    }

    public void setQf_base_circ(String qf_base_circ) {
        this.qf_base_circ = qf_base_circ;
    }

    public String getQf_base_vvod() {
        return qf_base_vvod;
    }

    public void setQf_base_vvod(String qf_base_vvod) {
        this.qf_base_vvod = qf_base_vvod;
    }

    public String getQf_base() {
        return qf_base;
    }

    public void setQf_base(String qf_base) {
        this.qf_base = qf_base;
    }

    public String getQf_now_circ() {
        return qf_now_circ;
    }

    public void setQf_now_circ(String qf_now_circ) {
        this.qf_now_circ = qf_now_circ;
    }

    public String getQf_now_vvod() {
        return qf_now_vvod;
    }

    public void setQf_now_vvod(String qf_now_vvod) {
        this.qf_now_vvod = qf_now_vvod;
    }

    public String getQf_now() {
        return qf_now;
    }

    public void setQf_now(String qf_now) {
        this.qf_now = qf_now;
    }

    public String getEff_heat_fiz() {
        return eff_heat_fiz;
    }

    public void setEff_heat_fiz(String eff_heat_fiz) {
        this.eff_heat_fiz = eff_heat_fiz;
    }

    public String getEff_heat_money() {
        return eff_heat_money;
    }

    public void setEff_heat_money(String eff_heat_money) {
        this.eff_heat_money = eff_heat_money;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableCausesHigh.class.getSimpleName() + "[", "]")
                .add("filial='" + filial + "'")
                .add("pred='" + pred + "'")
                .add("chp='" + chp + "'")
                .add("date='" + date + "'")
                .add("t7_base='" + t7_base + "'")
                .add("t13_base='" + t13_base + "'")
                .add("t7_now='" + t7_now + "'")
                .add("t13_now='" + t13_now + "'")
                .add("g7_base='" + g7_base + "'")
                .add("g13_base='" + g13_base + "'")
                .add("g13_now='" + g13_now + "'")
                .add("g_water='" + g_water + "'")
                .add("qf_base_circ='" + qf_base_circ + "'")
                .add("qf_base_vvod='" + qf_base_vvod + "'")
                .add("qf_base='" + qf_base + "'")
                .add("qf_now_circ='" + qf_now_circ + "'")
                .add("qf_now_vvod='" + qf_now_vvod + "'")
                .add("qf_now='" + qf_now + "'")
                .add("eff_heat_fiz='" + eff_heat_fiz + "'")
                .add("eff_heat_money='" + eff_heat_money + "'")
                .toString();
    }
}

