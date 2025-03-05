package ru.tecon.causesDetection.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class TableCausesLow implements Serializable {
    private String filial;
    private String pred;
    private String chp;
    private String date;
    private String rep_period;
    private String qr;
    private String tnv_f_base;
    private String tnv_f_rep;
    private String qf_base;
    private String qf_rep;
    private String qr_base;
    private String qr_rep;
    private String dq_base;
    private String dq_rep;
    private String dq;
    private String dq_priv;
    private String eff_heat_fiz;
    private String eff_heat_money;

    public TableCausesLow(String filial, String pred, String chp, String date, String rep_period, String qr, String tnv_f_base, String tnv_f_rep, String qf_base, String qf_rep, String qr_base, String qr_rep, String dq_base, String dq_rep, String dq, String dq_priv, String eff_heat_fiz, String eff_heat_money) {
        this.filial = filial;
        this.pred = pred;
        this.chp = chp;
        this.date = date;
        this.rep_period = rep_period;
        this.qr = qr;
        this.tnv_f_base = tnv_f_base;
        this.tnv_f_rep = tnv_f_rep;
        this.qf_base = qf_base;
        this.qf_rep = qf_rep;
        this.qr_base = qr_base;
        this.qr_rep = qr_rep;
        this.dq_base = dq_base;
        this.dq_rep = dq_rep;
        this.dq = dq;
        this.dq_priv = dq_priv;
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

    public String getRep_period() {
        return rep_period;
    }

    public void setRep_period(String rep_period) {
        this.rep_period = rep_period;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getTnv_f_base() {
        return tnv_f_base;
    }

    public void setTnv_f_base(String tnv_f_base) {
        this.tnv_f_base = tnv_f_base;
    }

    public String getTnv_f_rep() {
        return tnv_f_rep;
    }

    public void setTnv_f_rep(String tnv_f_rep) {
        this.tnv_f_rep = tnv_f_rep;
    }

    public String getQf_base() {
        return qf_base;
    }

    public void setQf_base(String qf_base) {
        this.qf_base = qf_base;
    }

    public String getQf_rep() {
        return qf_rep;
    }

    public void setQf_rep(String qf_rep) {
        this.qf_rep = qf_rep;
    }

    public String getQr_base() {
        return qr_base;
    }

    public void setQr_base(String qr_base) {
        this.qr_base = qr_base;
    }

    public String getQr_rep() {
        return qr_rep;
    }

    public void setQr_rep(String qr_rep) {
        this.qr_rep = qr_rep;
    }

    public String getDq_base() {
        return dq_base;
    }

    public void setDq_base(String dq_base) {
        this.dq_base = dq_base;
    }

    public String getDq_rep() {
        return dq_rep;
    }

    public void setDq_rep(String dq_rep) {
        this.dq_rep = dq_rep;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getDq_priv() {
        return dq_priv;
    }

    public void setDq_priv(String dq_priv) {
        this.dq_priv = dq_priv;
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
        return new StringJoiner(", ", TableCausesLow.class.getSimpleName() + "[", "]")
                .add("filial='" + filial + "'")
                .add("pred='" + pred + "'")
                .add("chp='" + chp + "'")
                .add("date='" + date + "'")
                .add("rep_period='" + rep_period + "'")
                .add("qr='" + qr + "'")
                .add("tnv_f_base='" + tnv_f_base + "'")
                .add("tnv_f_rep='" + tnv_f_rep + "'")
                .add("qf_base='" + qf_base + "'")
                .add("qf_rep='" + qf_rep + "'")
                .add("qr_base='" + qr_base + "'")
                .add("qr_rep='" + qr_rep + "'")
                .add("dq_base='" + dq_base + "'")
                .add("dq_rep='" + dq_rep + "'")
                .add("dq='" + dq + "'")
                .add("dq_priv='" + dq_priv + "'")
                .add("eff_heat_fiz='" + eff_heat_fiz + "'")
                .add("eff_heat_money='" + eff_heat_money + "'")
                .toString();
    }
}
