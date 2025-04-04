/******************************************************************************
 * This piece of work is to enhance astrology project functionality.          *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      Horoscope.java                                                  *
 * Created:   30/03/2025, 16:22                                               *
 * Modified:  30/03/2025, 16:24                                               *
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

package com.aerosimo.ominet.astrology.dao.impl;

import com.aerosimo.ominet.astrology.core.config.Connect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class Horoscope {
    private static final Logger log = LogManager.getLogger(Horoscope.class.getName());

    static String response;
    static String sql;
    static Connection con;
    static {
        con = Connect.dbase();
    }
    static CallableStatement stmt;

    public static String saveHoroscope(String zodiac, String currentDay, String narrative) {
        log.info("Preparing to save new daily horoscope...");
        sql = "{call profile_pkg.SaveHoroscope(?,?,?,?,?)}";
            try {
                stmt = con.prepareCall(sql);
                stmt.setString(1, zodiac);
                stmt.setString(2, currentDay);
                stmt.setString(3, narrative);
                stmt.setString(4, "Vercel");
                stmt.registerOutParameter(5, Types.VARCHAR);
                stmt.execute();
                response = stmt.getString(5);
                log.info("Successfully write {} daily horoscope update from Vercel to database", zodiac);
            }catch(Exception err){
                response = "Fail";
                log.error("Horoscope service failed with adaptor error {}", String.valueOf(err));
            }
        return response;
    }
}