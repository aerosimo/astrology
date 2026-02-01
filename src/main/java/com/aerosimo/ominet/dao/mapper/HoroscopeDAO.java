/******************************************************************************
 * This piece of work is to enhance astrology project functionality.          *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      HoroscopeDAO.java                                               *
 * Created:   07/11/2025, 23:55                                               *
 * Modified:  27/11/2025, 22:02                                               *
 *                                                                            *
 * Copyright (c)  2025.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.ominet.dao.mapper;

import com.aerosimo.ominet.core.config.Connect;
import com.aerosimo.ominet.core.model.Spectre;
import com.aerosimo.ominet.dao.impl.HoroscopeResponseDTO;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class HoroscopeDAO {

    private static final Logger log = LogManager.getLogger(HoroscopeDAO.class.getName());

    public static String saveHoroscope(String zodiac, String currentDay, String narrative) {
        log.info("Preparing to save new daily horoscope from Vercel to database");
        String response;
        String sql = "{call starcast_pkg.saveHoroscope(?,?,?,?,?)}";
        try (Connection con = Connect.dbase();
             CallableStatement stmt = con.prepareCall(sql)) {
            stmt.setString(1, zodiac);
            stmt.setString(2, currentDay);
            stmt.setString(3, narrative);
            stmt.setString(4, "Vercel");
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(5);
            if(response.equalsIgnoreCase("success")){
                log.info("Successfully write {} daily horoscope update from Vercel to database", zodiac);
                return response;
            } else {
                log.error("Fail to save {} daily horoscope update from Vercel to database", zodiac);
                return response;
            }
        } catch (SQLException err) {
            log.error("Error in starcast_pkg (SAVE HOROSCOPE)", err);
            try {
                Spectre.recordError("TE-20001", "Error in starcast_pkg (SAVE HOROSCOPE): " + err.getMessage(), HoroscopeDAO.class.getName());
                response = "internal server error";
                return response;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HoroscopeResponseDTO getHoroscope(String sign) {
        log.info("Preparing to retrieve horoscope details");
        HoroscopeResponseDTO response = null;
        String sql = "{call starcast_pkg.getHoroscope(?,?)}";
        String zodiac = StringUtils.capitalize(sign.toLowerCase());
        try (Connection con = Connect.dbase();
             CallableStatement stmt = con.prepareCall(sql)) {
            stmt.setString(1, zodiac);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                if (rs != null && rs.next()) {
                    response = new HoroscopeResponseDTO();
                    response.setZodiacSign(rs.getString("zodiacSign"));
                    response.setCurrentDay(rs.getString("currentDay"));
                    response.setNarrative(rs.getString("narrative"));
                    response.setModifiedBy(rs.getString("modifiedBy"));
                    response.setModifiedDate(rs.getString("modifiedDate"));
                }
            }
        } catch (SQLException err) {
            log.error("Error in starcast_pkg (GET HOROSCOPE)", err);
            try {
                Spectre.recordError("TE-20001", err.getMessage(), HoroscopeDAO.class.getName());
            } catch (Exception ignored) {}
        }
        return response;
    }
}