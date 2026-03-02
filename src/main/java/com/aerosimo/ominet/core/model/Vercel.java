/******************************************************************************
 * This piece of work is to enhance astrology project functionality.          *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      Vercel.java                                                     *
 * Created:   10/10/2025, 03:45                                               *
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

package com.aerosimo.ominet.core.model;
import com.aerosimo.ominet.dao.mapper.HoroscopeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Vercel {

    private static final Logger log = LogManager.getLogger(Vercel.class.getName());

    public static void updateZodiac() {
        String[] signs = new String[]{"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
        for (String sign : signs) {
            String apiUrl = "https://freehoroscopeapi.com/api/v1/get-horoscope/daily?sign=" + sign;
            log.info("API URL: " + apiUrl);
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                int responseCode = conn.getResponseCode();
                log.info("Response Code from Vercel is : " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // 200
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();
                    log.info(response.toString());
                    // Parse JSON response
                    JSONObject zodiac = new JSONObject(response.toString());
                    JSONObject zodiacData = zodiac.getJSONObject("data");
                    // Extract elements
                    String currentDay = zodiacData.getString("date");
                    String narrative = zodiacData.getString("horoscope");
                    // Call Horoscope class to insert into DB
                    HoroscopeDAO.saveHoroscope(sign, currentDay, narrative);
                    log.info("Successfully updated horoscope for {}. Today: {}", sign, currentDay);
                } else {
                    log.error("Failed to fetch data for {}. HTTP Code: {}", sign, responseCode);
                    try {
                        Spectre.recordError("TE-20001", "Failed to fetch data for " + sign + " HTTP Code: " + responseCode, Vercel.class.getName());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                conn.disconnect();
            } catch (Exception err) {
                log.error("Horoscope service failed with adaptor error {}", String.valueOf(err));
                try {
                    Spectre.recordError("TE-20001", "Horoscope service failed with adaptor error " + err, Vercel.class.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}