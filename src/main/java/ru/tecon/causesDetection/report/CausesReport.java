package ru.tecon.causesDetection.report;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import ru.tecon.causesDetection.ejb.CausesDetectionSB;
import ru.tecon.causesDetection.model.TableCausesHigh;
import ru.tecon.causesDetection.model.TableCausesLow;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CausesReport {

    public static SXSSFWorkbook createReport(int id, LocalDateTime timestampLDT, String interval, String user, String alg, int repType, CausesDetectionSB bean) {
        SXSSFWorkbook wb;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timestamp = timestampLDT.format(formatter);
        if (alg.equals("high")) {
            wb = createHighReport(id, timestamp, interval, user, repType, bean);
        } else {
            wb = createLowReport(id, timestamp, interval, user, repType, bean);
        }
        return wb;
    }

    public static SXSSFWorkbook createHighReport(int id, String timestamp, String interval, String user, int repType, CausesDetectionSB bean) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sh = wb.createSheet("Отчет");

        CellStyle headerStyle = setHeaderStyle(wb);
        CellStyle headerStyleNoBold = setHeaderStyleNoBold(wb);
        CellStyle nowStyle = setCellNow (wb);
        CellStyle tableHeaderStyle = setTableHeaderStyle(wb);
        CellStyle cellTableStyle = setCellTable(wb);
        CellStyle cellGStyleItog = setCellGStyleItog (wb);

        SXSSFRow row_1 = sh.createRow(0);
        row_1.setHeight((short) 435);
        SXSSFCell cell_1_1 = row_1.createCell(0);
        cell_1_1.setCellValue("ПАО \"МОЭК\": АС \"ТЕКОН - Диспетчеризация\"");
        CellRangeAddress title = new CellRangeAddress(0, 0, 0, 13);
        sh.addMergedRegion(title);
        cell_1_1.setCellStyle(headerStyle);

        SXSSFRow row_2 = sh.createRow(1);
        row_2.setHeight((short) 435);
        SXSSFCell cell_2_1 = row_2.createCell(0);
        cell_2_1.setCellValue("Выявление и устранение причин завышения температуры ГВС Т7 и/или циркуляционного расхода горячей воды G13");
        CellRangeAddress formName = new CellRangeAddress(1, 1, 0, 13);
        sh.addMergedRegion(formName);
        cell_2_1.setCellStyle(headerStyle);

        if (interval.equals("y")) {
            SXSSFRow row_3 = sh.createRow(2);
            row_3.setHeight((short) 435);
            SXSSFCell cell_3_1 = row_3.createCell(0);
            cell_3_1.setCellValue("Период: " + timestamp.substring(0, 4) + " год");
            CellRangeAddress objName = new CellRangeAddress(2, 2, 0, 13);
            sh.addMergedRegion(objName);
            cell_3_1.setCellStyle(headerStyleNoBold);
        } else {
            SXSSFRow row_3 = sh.createRow(2);
            row_3.setHeight((short) 435);
            SXSSFCell cell_3_1 = row_3.createCell(0);
            cell_3_1.setCellValue("Период: " + timestamp.substring(5, 7) + "/" + timestamp.substring(0, 4));
            CellRangeAddress objName = new CellRangeAddress(2, 2, 0, 13);
            sh.addMergedRegion(objName);
            cell_3_1.setCellStyle(headerStyleNoBold);
        }

        SXSSFRow row_4 = sh.createRow(3);
        row_4.setHeight((short) 435);
        SXSSFCell cell_4_1 = row_4.createCell(0);
        sh.setColumnWidth(0, 15 * 256);
        sh.setColumnWidth(1, 16 * 256);
        sh.setColumnWidth(2, 16 * 256);
        sh.setColumnWidth(3, 17 * 256);
        sh.setColumnWidth(4, 13 * 256);
        sh.setColumnWidth(5, 13 * 256);
        sh.setColumnWidth(6, 13 * 256);
        sh.setColumnWidth(7, 13 * 256);
        sh.setColumnWidth(8, 13 * 256);
        sh.setColumnWidth(9, 13 * 256);
        sh.setColumnWidth(10, 13 * 256);
        sh.setColumnWidth(11, 12 * 256);
        sh.setColumnWidth(12, 14 * 256);
        sh.setColumnWidth(13, 13 * 256);
        sh.setColumnWidth(14, 13 * 256);
        sh.setColumnWidth(15, 15 * 256);
        sh.setColumnWidth(16, 14 * 256);
        sh.setColumnWidth(17, 13 * 256);
        sh.setColumnWidth(18, 22 * 256);
        sh.setColumnWidth(19, 22 * 256);


        if (repType == 0) {
            cell_4_1.setCellValue("по " + bean.getStructRep(id));
        } else {
            cell_4_1.setCellValue(bean.getStructRep(id));
        }
        CellRangeAddress repName = new CellRangeAddress(3, 3, 0, 13);
        sh.addMergedRegion(repName);
        cell_4_1.setCellStyle(headerStyleNoBold);

        createNow(sh, nowStyle);

        // готовим шапку таблицы
        SXSSFRow row_7 = sh.createRow(6);
        SXSSFCell cell_7_1 = row_7.createCell(0);
        cell_7_1.setCellStyle(tableHeaderStyle);
        cell_7_1.setCellValue("Филиал");

        SXSSFCell cell_7_2 = row_7.createCell(1);
        cell_7_2.setCellStyle(tableHeaderStyle);
        cell_7_2.setCellValue("Предприятие");

        SXSSFCell cell_7_3 = row_7.createCell(2);
        cell_7_3.setCellStyle(tableHeaderStyle);
        cell_7_3.setCellValue("ЦТП");

        SXSSFCell cell_7_4 = row_7.createCell(3);
        cell_7_4.setCellStyle(tableHeaderStyle);
        cell_7_4.setCellValue("Дата");

        SXSSFCell cell_7_5 = row_7.createCell(4);
        cell_7_5.setCellStyle(tableHeaderStyle);
        cell_7_5.setCellValue("Т7баз, ℃");

        SXSSFCell cell_7_6 = row_7.createCell(5);
        cell_7_6.setCellStyle(tableHeaderStyle);
        cell_7_6.setCellValue("Т13баз, ℃");

        SXSSFCell cell_7_7 = row_7.createCell(6);
        cell_7_7.setCellStyle(tableHeaderStyle);
        cell_7_7.setCellValue("Т7тек, ℃");

        SXSSFCell cell_7_8 = row_7.createCell(7);
        cell_7_8.setCellStyle(tableHeaderStyle);
        cell_7_8.setCellValue("Т13тек, ℃");

        SXSSFCell cell_7_9 = row_7.createCell(8);
        cell_7_9.setCellStyle(tableHeaderStyle);
        cell_7_9.setCellValue("G7баз, т⁄мес");

        SXSSFCell cell_7_10 = row_7.createCell(9);
        cell_7_10.setCellStyle(tableHeaderStyle);
        cell_7_10.setCellValue("G13баз, т⁄мес");

        SXSSFCell cell_7_11 = row_7.createCell(10);
        cell_7_11.setCellStyle(tableHeaderStyle);
        cell_7_11.setCellValue("G13тек, т⁄мес");

        SXSSFCell cell_7_12 = row_7.createCell(11);
        cell_7_12.setCellStyle(tableHeaderStyle);
        cell_7_12.setCellValue("Gвод, т⁄мес");

        SXSSFCell cell_7_13 = row_7.createCell(12);
        cell_7_13.setCellStyle(tableHeaderStyle);
        cell_7_13.setCellValue("Qф.баз.цирк, Гкал");

        SXSSFCell cell_7_14 = row_7.createCell(13);
        cell_7_14.setCellStyle(tableHeaderStyle);
        cell_7_14.setCellValue("Qф.баз.вод, Гкал");

        SXSSFCell cell_7_15 = row_7.createCell(14);
        cell_7_15.setCellStyle(tableHeaderStyle);
        cell_7_15.setCellValue("Qф.баз., Гкал");

        SXSSFCell cell_7_16 = row_7.createCell(15);
        cell_7_16.setCellStyle(tableHeaderStyle);
        cell_7_16.setCellValue("Qф.тек.цирк, Гкал");

        SXSSFCell cell_7_17 = row_7.createCell(16);
        cell_7_17.setCellStyle(tableHeaderStyle);
        cell_7_17.setCellValue("Qф.тек.вод, Гкал");

        SXSSFCell cell_7_18 = row_7.createCell(17);
        cell_7_18.setCellStyle(tableHeaderStyle);
        cell_7_18.setCellValue("Qф.тек., Гкал");

        SXSSFCell cell_7_19 = row_7.createCell(18);
        cell_7_19.setCellStyle(tableHeaderStyle);
        cell_7_19.setCellValue("Эффект по теплу, Гкал");

        SXSSFCell cell_7_20 = row_7.createCell(19);
        cell_7_20.setCellStyle(tableHeaderStyle);
        cell_7_20.setCellValue("Эффект по теплу, тыс.руб.");

        //с шапкой таблицы покончено, начинаем заполнять тело отчета
        List<TableCausesHigh> objects = bean.getCausesHighTable(id, timestamp, interval, user);

        int begRow = 7;
        TableCausesHigh itog = objects.get(objects.size()-1);
        objects.remove(objects.size()-1);

        if (!objects.isEmpty()) {
            for (TableCausesHigh object : objects) {
                SXSSFRow row = sh.createRow(begRow);

                SXSSFCell cell_1 = row.createCell(0);
                cell_1.setCellValue(object.getFilial());
                cell_1.setCellStyle(cellTableStyle);

                SXSSFCell cell_2 = row.createCell(1);
                cell_2.setCellValue(object.getPred());
                cell_2.setCellStyle(cellTableStyle);

                SXSSFCell cell_3 = row.createCell(2);
                cell_3.setCellValue(object.getChp());
                cell_3.setCellStyle(cellTableStyle);

                SXSSFCell cell_4 = row.createCell(3);
                cell_4.setCellValue(object.getDate());
                cell_4.setCellStyle(cellTableStyle);

                SXSSFCell cell_5 = row.createCell(4);
                cell_5.setCellValue(object.getT7_base());
                cell_5.setCellStyle(cellTableStyle);

                SXSSFCell cell_6 = row.createCell(5);
                cell_6.setCellValue(object.getT13_base());
                cell_6.setCellStyle(cellTableStyle);

                SXSSFCell cell_7 = row.createCell(6);
                cell_7.setCellValue(object.getT7_now());
                cell_7.setCellStyle(cellTableStyle);

                SXSSFCell cell_8 = row.createCell(7);
                cell_8.setCellValue(object.getT13_now());
                cell_8.setCellStyle(cellTableStyle);

                SXSSFCell cell_9 = row.createCell(8);
                cell_9.setCellValue(object.getG7_base());
                cell_9.setCellStyle(cellTableStyle);

                SXSSFCell cell_10 = row.createCell(9);
                cell_10.setCellValue(object.getG13_base());
                cell_10.setCellStyle(cellTableStyle);

                SXSSFCell cell_11 = row.createCell(10);
                cell_11.setCellValue(object.getG13_now());
                cell_11.setCellStyle(cellTableStyle);

                SXSSFCell cell_12 = row.createCell(11);
                cell_12.setCellValue(object.getG_water());
                cell_12.setCellStyle(cellTableStyle);

                SXSSFCell cell_13 = row.createCell(12);
                cell_13.setCellValue(object.getQf_base_circ());
                cell_13.setCellStyle(cellTableStyle);

                SXSSFCell cell_14 = row.createCell(13);
                cell_14.setCellValue(object.getQf_base_vvod());
                cell_14.setCellStyle(cellTableStyle);

                SXSSFCell cell_15 = row.createCell(14);
                cell_15.setCellValue(object.getQf_base());
                cell_15.setCellStyle(cellTableStyle);

                SXSSFCell cell_16 = row.createCell(15);
                cell_16.setCellValue(object.getQf_now_circ());
                cell_16.setCellStyle(cellTableStyle);

                SXSSFCell cell_17 = row.createCell(16);
                cell_17.setCellValue(object.getQf_now_vvod());
                cell_17.setCellStyle(cellTableStyle);

                SXSSFCell cell_18 = row.createCell(17);
                cell_18.setCellValue(object.getQf_now());
                cell_18.setCellStyle(cellTableStyle);

                SXSSFCell cell_19 = row.createCell(18);
                cell_19.setCellValue(object.getEff_heat_fiz());
                cell_19.setCellStyle(cellTableStyle);

                SXSSFCell cell_20 = row.createCell(19);
                cell_20.setCellValue(object.getEff_heat_money());
                cell_20.setCellStyle(cellTableStyle);

                begRow ++;
            }
            SXSSFRow row = sh.createRow(begRow);
            SXSSFCell cell_1 = row.createCell(0);
            cell_1.setCellValue(itog.getFilial());
            cell_1.setCellStyle(cellGStyleItog);


            SXSSFCell cell_17 = row.createCell(18);
            cell_17.setCellValue(itog.getEff_heat_fiz());
            cell_17.setCellStyle(cellGStyleItog);

            SXSSFCell cell_18 = row.createCell(19);
            cell_18.setCellValue(itog.getEff_heat_money());
            cell_18.setCellStyle(cellGStyleItog);

            CellRangeAddress itogMerge = new CellRangeAddress(begRow, begRow, 0, 17);
            sh.addMergedRegion(itogMerge);
        } else {
            SXSSFRow row = sh.createRow(begRow);
            SXSSFCell cell_1 = row.createCell(0);
            cell_1.setCellValue("Данные отсутствуют");
            cell_1.setCellStyle(nowStyle);
            CellRangeAddress itogMerge = new CellRangeAddress(begRow, begRow, 0, 3);
            sh.addMergedRegion(itogMerge);
        }

        return wb;
    }

    public static SXSSFWorkbook createLowReport(int id, String timestamp, String interval, String user, int repType, CausesDetectionSB bean) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sh = wb.createSheet("Отчет");

        CellStyle headerStyle = setHeaderStyle(wb);
        CellStyle headerStyleNoBold = setHeaderStyleNoBold(wb);
        CellStyle nowStyle = setCellNow (wb);
        CellStyle tableHeaderStyle = setTableHeaderStyle(wb);
        CellStyle cellTableStyle = setCellTable(wb);

        CellStyle cellGStyleItog = setCellGStyleItog (wb);


        SXSSFRow row_1 = sh.createRow(0);
        row_1.setHeight((short) 435);
        SXSSFCell cell_1_1 = row_1.createCell(0);
        cell_1_1.setCellValue("ПАО \"МОЭК\": АС \"ТЕКОН - Диспетчеризация\"");
        CellRangeAddress title = new CellRangeAddress(0, 0, 0, 13);
        sh.addMergedRegion(title);
        cell_1_1.setCellStyle(headerStyle);

        SXSSFRow row_2 = sh.createRow(1);
        row_2.setHeight((short) 435);
        SXSSFCell cell_2_1 = row_2.createCell(0);
        cell_2_1.setCellValue("Выявление и устранение причин занижения температуры Т3 и/или изменения циркуляционных расходов воды в системах отопления");
        CellRangeAddress formName = new CellRangeAddress(1, 1, 0, 13);
        sh.addMergedRegion(formName);
        cell_2_1.setCellStyle(headerStyle);

        if (interval.equals("y")) {
            SXSSFRow row_3 = sh.createRow(2);
            row_3.setHeight((short) 435);
            SXSSFCell cell_3_1 = row_3.createCell(0);
            cell_3_1.setCellValue("За " + timestamp.substring(0, 4) + " год");
            CellRangeAddress objName = new CellRangeAddress(2, 2, 0, 13);
            sh.addMergedRegion(objName);
            cell_3_1.setCellStyle(headerStyleNoBold);
        } else {
            SXSSFRow row_3 = sh.createRow(2);
            row_3.setHeight((short) 435);
            SXSSFCell cell_3_1 = row_3.createCell(0);
            cell_3_1.setCellValue("На период: " + timestamp.substring(5, 7) + "/" + timestamp.substring(0, 4));
            CellRangeAddress objName = new CellRangeAddress(2, 2, 0, 13);
            sh.addMergedRegion(objName);
            cell_3_1.setCellStyle(headerStyleNoBold);
        }

        SXSSFRow row_4 = sh.createRow(3);
        row_4.setHeight((short) 435);
        SXSSFCell cell_4_1 = row_4.createCell(0);
        sh.setColumnWidth(0, 15 * 256);
        sh.setColumnWidth(1, 16 * 256);
        sh.setColumnWidth(2, 16 * 256);
        sh.setColumnWidth(3, 17 * 256);
        sh.setColumnWidth(4, 15 * 256);
        sh.setColumnWidth(5, 10 * 256);
        sh.setColumnWidth(6, 12 * 256);
        sh.setColumnWidth(7, 12 * 256);
        sh.setColumnWidth(8, 13 * 256);
        sh.setColumnWidth(9, 13 * 256);
        sh.setColumnWidth(10, 13 * 256);
        sh.setColumnWidth(11, 13 * 256);
        sh.setColumnWidth(12, 13 * 256);
        sh.setColumnWidth(13, 13 * 256);
        sh.setColumnWidth(14, 13 * 256);
        sh.setColumnWidth(15, 13 * 256);
        sh.setColumnWidth(16, 22 * 256);
        sh.setColumnWidth(17, 22 * 256);

        if (repType == 0) {
            cell_4_1.setCellValue("по " + bean.getStructRep(id));
        } else {
            cell_4_1.setCellValue(bean.getStructRep(id));
        }        CellRangeAddress repName = new CellRangeAddress(3, 3, 0, 13);
        sh.addMergedRegion(repName);
        cell_4_1.setCellStyle(headerStyleNoBold);

        createNow(sh, nowStyle);

        // готовим шапку таблицы
        SXSSFRow row_7 = sh.createRow(6);
        SXSSFCell cell_7_1 = row_7.createCell(0);
        cell_7_1.setCellStyle(tableHeaderStyle);
        cell_7_1.setCellValue("Филиал");

        SXSSFCell cell_7_2 = row_7.createCell(1);
        cell_7_2.setCellStyle(tableHeaderStyle);
        cell_7_2.setCellValue("Предприятие");

        SXSSFCell cell_7_3 = row_7.createCell(2);
        cell_7_3.setCellStyle(tableHeaderStyle);
        cell_7_3.setCellValue("ЦТП");

        SXSSFCell cell_7_4 = row_7.createCell(3);
        cell_7_4.setCellStyle(tableHeaderStyle);
        cell_7_4.setCellValue("Дата");

        SXSSFCell cell_7_5 = row_7.createCell(4);
        cell_7_5.setCellStyle(tableHeaderStyle);
        cell_7_5.setCellValue("Период отчетный");

        SXSSFCell cell_7_6 = row_7.createCell(5);
        cell_7_6.setCellStyle(tableHeaderStyle);
        cell_7_6.setCellValue("Qр, Гкал/час");

        SXSSFCell cell_7_7 = row_7.createCell(6);
        cell_7_7.setCellStyle(tableHeaderStyle);
        cell_7_7.setCellValue("Тнв.ф.баз, ℃");

        SXSSFCell cell_7_8 = row_7.createCell(7);
        cell_7_8.setCellStyle(tableHeaderStyle);
        cell_7_8.setCellValue("Тнв.ф.отч, ℃");

        SXSSFCell cell_7_9 = row_7.createCell(8);
        cell_7_9.setCellStyle(tableHeaderStyle);
        cell_7_9.setCellValue("Qф.баз, Гкал⁄ч");

        SXSSFCell cell_7_10 = row_7.createCell(9);
        cell_7_10.setCellStyle(tableHeaderStyle);
        cell_7_10.setCellValue("Qф.отч, Гкал⁄ч");

        SXSSFCell cell_7_11 = row_7.createCell(10);
        cell_7_11.setCellStyle(tableHeaderStyle);
        cell_7_11.setCellValue("Qр.баз, Гкал⁄ч");

        SXSSFCell cell_7_12 = row_7.createCell(11);
        cell_7_12.setCellStyle(tableHeaderStyle);
        cell_7_12.setCellValue("Qр.отч, Гкал⁄ч");

        SXSSFCell cell_7_13 = row_7.createCell(12);
        cell_7_13.setCellStyle(tableHeaderStyle);
        cell_7_13.setCellValue("ΔQбаз, Гкал⁄ч");

        SXSSFCell cell_7_14 = row_7.createCell(13);
        cell_7_14.setCellStyle(tableHeaderStyle);
        cell_7_14.setCellValue("ΔQотч, Гкал⁄ч");

        SXSSFCell cell_7_15 = row_7.createCell(14);
        cell_7_15.setCellStyle(tableHeaderStyle);
        cell_7_15.setCellValue("ΔQ, Гкал⁄ч");

        SXSSFCell cell_7_16 = row_7.createCell(15);
        cell_7_16.setCellStyle(tableHeaderStyle);
        cell_7_16.setCellValue("ΔQприв, Гкалч");

        SXSSFCell cell_7_17 = row_7.createCell(16);
        cell_7_17.setCellStyle(tableHeaderStyle);
        cell_7_17.setCellValue("Эффект по теплу, Гкал");

        SXSSFCell cell_7_18 = row_7.createCell(17);
        cell_7_18.setCellStyle(tableHeaderStyle);
        cell_7_18.setCellValue("Эффект по теплу, тыс.руб.");

        //с шапкой таблицы покончено, начинаем заполнять тело отчета
        List<TableCausesLow> objects = bean.getCausesLowTable(id, timestamp, interval, user);
        TableCausesLow itog = objects.get(objects.size()-1);
        objects.remove(objects.size()-1);

        int begRow = 7;

        if (!objects.isEmpty()) {
            for (TableCausesLow object : objects) {
                SXSSFRow row = sh.createRow(begRow);

                SXSSFCell cell_1 = row.createCell(0);
                cell_1.setCellValue(object.getFilial());
                cell_1.setCellStyle(cellTableStyle);

                SXSSFCell cell_2 = row.createCell(1);
                cell_2.setCellValue(object.getPred());
                cell_2.setCellStyle(cellTableStyle);

                SXSSFCell cell_3 = row.createCell(2);
                cell_3.setCellValue(object.getChp());
                cell_3.setCellStyle(cellTableStyle);

                SXSSFCell cell_4 = row.createCell(3);
                cell_4.setCellValue(object.getDate());
                cell_4.setCellStyle(cellTableStyle);

                SXSSFCell cell_5 = row.createCell(4);
                cell_5.setCellValue(object.getRep_period());
                cell_5.setCellStyle(cellTableStyle);

                SXSSFCell cell_6 = row.createCell(5);
                cell_6.setCellValue(object.getQr());
                cell_6.setCellStyle(cellTableStyle);

                SXSSFCell cell_7 = row.createCell(6);
                cell_7.setCellValue(object.getTnv_f_base());
                cell_7.setCellStyle(cellTableStyle);

                SXSSFCell cell_8 = row.createCell(7);
                cell_8.setCellValue(object.getTnv_f_rep());
                cell_8.setCellStyle(cellTableStyle);

                SXSSFCell cell_9 = row.createCell(8);
                cell_9.setCellValue(object.getQf_base());
                cell_9.setCellStyle(cellTableStyle);

                SXSSFCell cell_10 = row.createCell(9);
                cell_10.setCellValue(object.getQf_rep());
                cell_10.setCellStyle(cellTableStyle);

                SXSSFCell cell_11 = row.createCell(10);
                cell_11.setCellValue(object.getQr_base());
                cell_11.setCellStyle(cellTableStyle);

                SXSSFCell cell_12 = row.createCell(11);
                cell_12.setCellValue(object.getQr_rep());
                cell_12.setCellStyle(cellTableStyle);

                SXSSFCell cell_13 = row.createCell(12);
                cell_13.setCellValue(object.getDq_base());
                cell_13.setCellStyle(cellTableStyle);

                SXSSFCell cell_14 = row.createCell(13);
                cell_14.setCellValue(object.getDq_rep());
                cell_14.setCellStyle(cellTableStyle);

                SXSSFCell cell_15 = row.createCell(14);
                cell_15.setCellValue(object.getDq());
                cell_15.setCellStyle(cellTableStyle);

                SXSSFCell cell_16 = row.createCell(15);
                cell_16.setCellValue(object.getDq_priv());
                cell_16.setCellStyle(cellTableStyle);

                SXSSFCell cell_17 = row.createCell(16);
                cell_17.setCellValue(object.getEff_heat_fiz());
                cell_17.setCellStyle(cellTableStyle);

                SXSSFCell cell_18 = row.createCell(17);
                cell_18.setCellValue(object.getEff_heat_money());
                cell_18.setCellStyle(cellTableStyle);

                begRow ++;
            }

            SXSSFRow row = sh.createRow(begRow);
            SXSSFCell cell_1 = row.createCell(0);
            cell_1.setCellValue(itog.getFilial());
            cell_1.setCellStyle(cellGStyleItog);

            SXSSFCell cell_17 = row.createCell(16);
            cell_17.setCellValue(itog.getEff_heat_fiz());
            cell_17.setCellStyle(cellGStyleItog);

            SXSSFCell cell_18 = row.createCell(17);
            cell_18.setCellValue(itog.getEff_heat_money());
            cell_18.setCellStyle(cellGStyleItog);

            CellRangeAddress itogMerge = new CellRangeAddress(begRow, begRow, 0, 15);
            sh.addMergedRegion(itogMerge);
        } else {
            SXSSFRow row = sh.createRow(begRow);
            SXSSFCell cell_1 = row.createCell(0);
            cell_1.setCellValue("Данные отсутствуют");
            cell_1.setCellStyle(nowStyle);
            CellRangeAddress itogMerge = new CellRangeAddress(begRow, begRow, 0, 3);
            sh.addMergedRegion(itogMerge);
        }
        return wb;
    }

    private static void createNow(SXSSFSheet sh, CellStyle nowStyle) {
        String now = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        SXSSFRow row_5 = sh.createRow(4);
        row_5.setHeight((short) 435);
        SXSSFCell cell_5_1 = row_5.createCell(0);
        cell_5_1.setCellStyle(nowStyle);
        cell_5_1.setCellValue("Отчет сформирован  " + now);
        CellRangeAddress nowDone = new CellRangeAddress(4, 4, 0, 13);
        sh.addMergedRegion(nowDone);
    }

    ///////////////////////////////////////////  Определение стилей тут

    //  Стиль заголовка жирный
    private static CellStyle setHeaderStyle(SXSSFWorkbook p_wb) {

        CellStyle style = p_wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(false);

        Font headerFont = p_wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("Times New Roman");
        headerFont.setFontHeightInPoints((short) 16);

        style.setFont(headerFont);

        return style;
    }

    //  Стиль заголовка не жирный
    private static CellStyle setHeaderStyleNoBold(SXSSFWorkbook p_wb) {

        CellStyle style = p_wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(false);

        Font headerFont = p_wb.createFont();
        headerFont.setBold(false);
        headerFont.setFontName("Times New Roman");
        headerFont.setFontHeightInPoints((short) 16);

        style.setFont(headerFont);

        return style;
    }

    //стиль для даты создания отчета
    private static CellStyle setCellNow(SXSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);


        Font nowFont = wb.createFont();
        nowFont.setBold(false);
        nowFont.setFontName("Times New Roman");
        nowFont.setFontHeightInPoints((short) 12);

        style.setFont(nowFont);

        return style;
    }

    //  Стиль шапки таблицы
    private static CellStyle setTableHeaderStyle(SXSSFWorkbook p_wb) {
        CellStyle style = p_wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);

        style.setBorderTop(BorderStyle.THICK);
        style.setBorderLeft(BorderStyle.THICK);
        style.setBorderRight(BorderStyle.THICK);
        style.setBorderBottom(BorderStyle.THICK);

        Font tableHeaderFont = p_wb.createFont();

        tableHeaderFont.setBold(true);
        tableHeaderFont.setFontName("Times New Roman");
        tableHeaderFont.setFontHeightInPoints((short) 12);

        style.setFont(tableHeaderFont);

        return style;
    }

    private static CellStyle setCellTable(SXSSFWorkbook p_wb) {
        CellStyle style = p_wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);

        Font cellNoBoldFont = p_wb.createFont();

        cellNoBoldFont.setBold(false);
        cellNoBoldFont.setFontName("Times New Roman");
        cellNoBoldFont.setFontHeightInPoints((short) 11);

        style.setFont(cellNoBoldFont);

        return style;
    }

    private static CellStyle setCellGStyleItog(SXSSFWorkbook p_wb) {
        CellStyle style = p_wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);

        Font cellNoBoldFont = p_wb.createFont();

        cellNoBoldFont.setBold(false);
        cellNoBoldFont.setFontName("Times New Roman");
        cellNoBoldFont.setFontHeightInPoints((short) 11);

        style.setFont(cellNoBoldFont);
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        return style;
    }
}
