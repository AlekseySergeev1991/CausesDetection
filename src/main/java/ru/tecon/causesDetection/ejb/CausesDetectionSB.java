package ru.tecon.causesDetection.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import ru.tecon.causesDetection.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stateless bean для получения данных из база для формы выявление отклонений
 *
 * @author Aleksey Sergeev
 */
@Stateless
@LocalBean
public class CausesDetectionSB {

    private static final String SELECT_STRUCT_TREE = "select * from eff_calc.filter_view(?,?,?)";
    private static final String SELECT_OBJ_TYPE_PROP = "select * from dsp_0032t.get_obj_type_props(1)";
    private static final String GET_STRUCT = "select * from eff_calc.text_struct(?, ?)";
    private static final String GET_CONST = "select * from eff_calc_003.const_view_variation(?)";
    private static final String GET_TABLE_CAUSES_HIGH = "select * from eff_calc_003.table_causes_high_t_gvs_t7(?, ?, ?, ?)";
    private static final String GET_TABLE_CAUSES_LOW = "select * from eff_calc_003.table_causes_low_t_t3(?, ?, ?, ?)";
    private static final String GET_ARCHIVE_TABLE = "select * from eff_calc.report_view('causes')";
    private static final String GET_ARCHIVE_REPORT = "select * from eff_calc.decode_xlsx(?)";
    private static final String GET_ARCHIVE_NAME = "select * from eff_calc.get_inf_from_excel(?)";
    private static final String GET_STRUCT_REP = "select * from eff_calc.text_struct_no_user(?)";
    private static final String GET_OBJ_NAME = "select * from eff_calc.get_name_from_id(?)";



    private static final Logger logger = Logger.getLogger(CausesDetectionSB.class.getName());

    @Resource(name = "jdbc/DataSource")
    private DataSource dsRw;

    @Resource(name = "jdbc/DataSourceR")
    private DataSource dsR;

    /**
     * @return дерево для формы
     */
    public List<StructTree> getTreeParam(String user, int filterId, String filter) {
        List<StructTree> result = new ArrayList<>();
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(SELECT_STRUCT_TREE)) {
            stm.setString(1, user);
            stm.setInt(2, filterId);
            stm.setString(3, filter);
            ResultSet res = stm.executeQuery();

            while (res.next()) {

                StructTree structTree = new StructTree(res.getString("id"),
                        res.getString("name"),
                        res.getString("parent"),
                        res.getInt("my_id"),
                        res.getString("my_type"),
                        res.getString("my_icon"));

                if (structTree.getMyIcon().equals("L")) {
                    structTree.setMyIcon("fa fa-link fa-rotate-90 linkIcon");
                } else {
                    structTree.setMyIcon("fa fa-cubes cubesIcon");
                }

                result.add(structTree);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return список свойств объета
     */
    public List<ObjProp> getProp() {
        List<ObjProp> result = new ArrayList<>();
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(SELECT_OBJ_TYPE_PROP)) {
            ResultSet res = stm.executeQuery();
            while (res.next()) {

                ObjProp objProp = new ObjProp(res.getInt("obj_prop_id"),
                        res.getString("obj_prop_name"));

                result.add(objProp);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return адрес объекта
     */
    public String getStruct(String user, String id) {
        String result = "";
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_STRUCT)) {
            stm.setString(1, user);
            stm.setString(2, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                result = res.getString("text_struct");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return список констант
     */
    public List<Const> getConst(int id) {
        List<Const> result = new ArrayList<>();
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_CONST)) {
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {

                Const curConst = new Const(res.getInt("const_id"), res.getString("name_const"),
                        res.getString("value"), res.getString("private"));


                result.add(curConst);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return список значений для таблицы по причинам завышения температуры
     */
    public List<TableCausesHigh> getCausesHighTable(int id, String strDate, String interval, String user) {
        List<TableCausesHigh> result = new ArrayList<>();
        if (strDate.length() < 16) {
            if (interval.equals("m")) {
                strDate = strDate + "-01 00:00";
            } else {
                strDate = strDate + "-01-01 00:00";
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(strDate, formatter);
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_TABLE_CAUSES_HIGH)) {
            stm.setInt(1, id);
            stm.setTimestamp(2, Timestamp.valueOf(date));
            stm.setString(3, interval);
            stm.setString(4, user);
            ResultSet res = stm.executeQuery();
            while (res.next()) {

                TableCausesHigh causesHighTable = new TableCausesHigh(res.getString("name_filial"),
                        res.getString("name_pred"), res.getString("name_chp"),
                        res.getString("date_"), res.getString("t7_base"),
                        res.getString("t13_base"),res.getString("t7_now"),
                        res.getString("t13_now"), res.getString("g7_base"),
                        res.getString("g13_base"), res.getString("g13_now"),
                        res.getString("g_water"), res.getString("qf_base_circ"),
                        res.getString("qf_base_vvod"), res.getString("qf_base"),
                        res.getString("qf_now_circ"), res.getString("qf_now_vvod"),
                        res.getString("qf_now"), res.getString("eff_heat_fiz"),
                        res.getString("eff_heat_money"));

                result.add(causesHighTable);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return список значений для таблицы по причинам занижения температуры
     */
    public List<TableCausesLow> getCausesLowTable(int id, String strDate, String interval, String user) {
        List<TableCausesLow> result = new ArrayList<>();
        if (strDate.length() < 16) {
            if (interval.equals("m")) {
                strDate = strDate + "-01 00:00";
            } else {
                strDate = strDate + "-01-01 00:00";
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(strDate, formatter);
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_TABLE_CAUSES_LOW)) {
            stm.setInt(1, id);
            stm.setTimestamp(2, Timestamp.valueOf(date));
            stm.setString(3, interval);
            stm.setString(4, user);
            ResultSet res = stm.executeQuery();
            while (res.next()) {

                TableCausesLow causesLowTable = new TableCausesLow(res.getString("name_filial"),
                        res.getString("name_pred"), res.getString("name_chp"),
                        res.getString("date_"), res.getString("period_"),
                        res.getString("qp"),res.getString("t_nv_f_base"),
                        res.getString("t_nv_f_period"), res.getString("q_f_base"),
                        res.getString("q_f_period"), res.getString("q_r_base"),
                        res.getString("q_r_period"), res.getString("dq_base"),
                        res.getString("dq_period"), res.getString("dq"),
                        res.getString("dq_pr"), res.getString("eff_heat"),
                        res.getString("eff_money"));

                result.add(causesLowTable);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return таблица с перечнем всех архивных отчетов
     */
    public List<ArchiveReport> getArchiveTable() {
        List<ArchiveReport> result = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.yyyy");
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_ARCHIVE_TABLE)) {
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ArchiveReport archiveReport = new ArchiveReport(res.getInt("id"), res.getString("rep_type"),
                        res.getDate("report_time").toLocalDate().format(dtf));
                archiveReport.setRedirect("loadArchiveReport?id=" + archiveReport.getId() + "&amp;");
                result.add(archiveReport);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return архивный отчет
     */
    public byte[] getByteReport(int id) {
        byte[] result = new byte[0];
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_ARCHIVE_REPORT)) {
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                result = res.getBytes("decode_xlsx");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return имя архива
     */
    public String getArchiveName(int id) {
        String result = "";
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_ARCHIVE_NAME)) {
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                result = res.getString("get_inf_from_excel");
                result = result.replaceAll("\"", "");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return структуру объекта
     */
    public String getStructRep(int id) {
        String result = "";
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_STRUCT_REP)) {
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                result = res.getString("text_struct_no_user");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }

    /**
     * @return имя объекта
     */
    public String getObjName(int id) {
        String result = "";
        try (Connection connect = dsR.getConnection();
             PreparedStatement stm = connect.prepareStatement(GET_OBJ_NAME)) {
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                result = res.getString("name");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException", e);
        }
        return result;
    }
}
