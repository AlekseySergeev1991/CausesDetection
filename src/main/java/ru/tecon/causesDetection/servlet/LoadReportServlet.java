package ru.tecon.causesDetection.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import ru.tecon.causesDetection.ejb.CausesDetectionSB;
import ru.tecon.causesDetection.report.CausesReport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/loadReport")
public class LoadReportServlet extends HttpServlet {

    @EJB
    private CausesDetectionSB bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String timestamp = req.getParameter("timestamp");
        String interval = req.getParameter("interval");
        String user = req.getParameter("user");
        String alg = req.getParameter("alg");
        int repType = Integer.parseInt(req.getParameter("repType"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime timestampLDT = LocalDateTime.parse(timestamp, formatter);

        resp.setContentType("application/vnd.ms-excel; charset=UTF-8");
        if (repType == 0) {
            if (alg.equals("high")) {
                String struct = bean.getStructRep(id);
                struct = structCorrection(resp, struct);
                resp.setHeader("Content-Disposition", "attachment; filename=\"" +
                        URLEncoder.encode("Выявление", "UTF-8") + " " +
                        URLEncoder.encode("и", "UTF-8") + " " +
                        URLEncoder.encode("устранение", "UTF-8") + " " +
                        URLEncoder.encode("причин", "UTF-8") + " " +
                        URLEncoder.encode("завышения", "UTF-8") + " " +
                        URLEncoder.encode("температуры", "UTF-8") + " " +
                        URLEncoder.encode("ГВС", "UTF-8") + " " +
                        URLEncoder.encode("Т7", "UTF-8") + " " +
                        URLEncoder.encode("и/или", "UTF-8") + " " +
                        URLEncoder.encode("циркуляционного", "UTF-8") + " " +
                        URLEncoder.encode("расхода", "UTF-8") + " " +
                        URLEncoder.encode("горячей", "UTF-8") + " " +
                        URLEncoder.encode("воды", "UTF-8") + " " +
                        URLEncoder.encode("G13", "UTF-8") + " " +
                        URLEncoder.encode(struct, "UTF-8") +
                        URLEncoder.encode(".xlsx", "UTF-8") + "\"");
                resp.setCharacterEncoding("UTF-8");
            } else {
                String struct = bean.getStructRep(id);
                struct = structCorrection(resp, struct);
                resp.setHeader("Content-Disposition", "attachment; filename=\"" +
                        URLEncoder.encode("Выявление", "UTF-8") + " " +
                        URLEncoder.encode("и", "UTF-8") + " " +
                        URLEncoder.encode("устранение", "UTF-8") + " " +
                        URLEncoder.encode("причин", "UTF-8") + " " +
                        URLEncoder.encode("занижения", "UTF-8") + " " +
                        URLEncoder.encode("температуры", "UTF-8") + " " +
                        URLEncoder.encode("Т3", "UTF-8") + " " +
                        URLEncoder.encode("и/или", "UTF-8") + " " +
                        URLEncoder.encode("изменения", "UTF-8") + " " +
                        URLEncoder.encode("циркуляционных", "UTF-8") + " " +
                        URLEncoder.encode("расходов", "UTF-8") + " " +
                        URLEncoder.encode("воды", "UTF-8") + " " +
                        URLEncoder.encode("в", "UTF-8") + " " +
                        URLEncoder.encode("системах", "UTF-8") + " " +
                        URLEncoder.encode("отопления", "UTF-8") + " " +
                        URLEncoder.encode(struct, "UTF-8") +
                        URLEncoder.encode(".xlsx", "UTF-8") + "\"");
                resp.setCharacterEncoding("UTF-8");
            }
        } else {
            if (alg.equals("high")) {
                String struct = bean.getObjName(id);
                struct = struct.replace('/', '_');
                struct = struct.replaceAll(" ", "");
                struct = struct.replaceFirst("\"", "_");
                struct = struct.replaceAll("\"", "");

                resp.setHeader("Content-Disposition", "attachment; filename=\"" +
                        URLEncoder.encode("Выявление", "UTF-8") + " " +
                        URLEncoder.encode("и", "UTF-8") + " " +
                        URLEncoder.encode("устранение", "UTF-8") + " " +
                        URLEncoder.encode("причин", "UTF-8") + " " +
                        URLEncoder.encode("завышения", "UTF-8") + " " +
                        URLEncoder.encode("температуры", "UTF-8") + " " +
                        URLEncoder.encode("ГВС", "UTF-8") + " " +
                        URLEncoder.encode("Т7", "UTF-8") + " " +
                        URLEncoder.encode("и/или", "UTF-8") + " " +
                        URLEncoder.encode("циркуляционного", "UTF-8") + " " +
                        URLEncoder.encode("расхода", "UTF-8") + " " +
                        URLEncoder.encode("горячей", "UTF-8") + " " +
                        URLEncoder.encode("воды", "UTF-8") + " " +
                        URLEncoder.encode("G13", "UTF-8") + " " +
                        URLEncoder.encode(struct, "UTF-8") +
                        URLEncoder.encode(".xlsx", "UTF-8") + "\"");
                resp.setCharacterEncoding("UTF-8");
            } else {
                String struct = bean.getObjName(id);
                struct = struct.replace('/', '_');
                struct = struct.replaceAll(" ", "");
                struct = struct.replaceFirst("\"", "_");
                struct = struct.replaceAll("\"", "");
                resp.setHeader("Content-Disposition", "attachment; filename=\"" +
                        URLEncoder.encode("Выявление", "UTF-8") + " " +
                        URLEncoder.encode("и", "UTF-8") + " " +
                        URLEncoder.encode("устранение", "UTF-8") + " " +
                        URLEncoder.encode("причин", "UTF-8") + " " +
                        URLEncoder.encode("занижения", "UTF-8") + " " +
                        URLEncoder.encode("температуры", "UTF-8") + " " +
                        URLEncoder.encode("Т3", "UTF-8") + " " +
                        URLEncoder.encode("и/или", "UTF-8") + " " +
                        URLEncoder.encode("изменения", "UTF-8") + " " +
                        URLEncoder.encode("циркуляционных", "UTF-8") + " " +
                        URLEncoder.encode("расходов", "UTF-8") + " " +
                        URLEncoder.encode("воды", "UTF-8") + " " +
                        URLEncoder.encode("в", "UTF-8") + " " +
                        URLEncoder.encode("системах", "UTF-8") + " " +
                        URLEncoder.encode("отопления", "UTF-8") + " " +
                        URLEncoder.encode(struct, "UTF-8") +
                        URLEncoder.encode(".xlsx", "UTF-8") + "\"");
                resp.setCharacterEncoding("UTF-8");
            }
        }

        try (OutputStream output = resp.getOutputStream()) {
            CausesReport.createReport(id, timestampLDT, interval, user, alg, repType, bean).write(output);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    private String structCorrection(HttpServletResponse resp, String struct) throws UnsupportedEncodingException {
        struct = struct.replace('/', '_');
        struct = struct.replaceAll(" ", "");
        struct = struct.replaceFirst("\"", "_");
        struct = struct.replaceAll("\"", "");
        return struct;

    }
}
