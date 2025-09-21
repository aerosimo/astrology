/******************************************************************************
 * This piece of work is to enhance astrology project functionality.          *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      Zodiac.java                                                 *
 * Created:   30/03/2025, 20:33                                               *
 * Modified:  30/03/2025, 20:33                                               *
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

package com.aerosimo.ominet.astrology.services.util;

import com.aerosimo.ominet.astrology.dao.impl.Horoscope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Zodiac {
    private static final Logger log = LogManager.getLogger(Zodiac.class.getName());
    static String[] signs;
    static String apiUrl;
    static String currentDay;
    static String narrative;
    static URL url;
    static HttpURLConnection conn;
    static BufferedReader br;
    static JSONObject zodiac, data;
    static StringBuilder response;

    public static void updateZodiac() {
        signs = new String[]{"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
        for (String sign : signs) {
            apiUrl = "https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=" + sign + "&day=TODAY";
            try {
                url = new URL(apiUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 200
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();
                    // Parse JSON response
                    zodiac = new JSONObject(response.toString());
                    data = zodiac.getJSONObject("data");
                    // Extract elements
                    currentDay = data.getString("date");
                    narrative = data.getString("horoscope_data");
                    // Call Horoscope class to insert into DB
                    Horoscope.saveHoroscope(sign, currentDay, narrative);
                    log.info("Successfully updated horoscope for {}. Today: {}", sign, currentDay);
                } else {
                    log.error("Failed to fetch data for {}. HTTP Code: {}", sign, responseCode);
                }
                conn.disconnect();
            } catch (Exception err) {
                log.error("Horoscope service failed with adaptor error {}", String.valueOf(err));
            }
        }
    }
}