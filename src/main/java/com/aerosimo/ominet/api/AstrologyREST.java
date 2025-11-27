/******************************************************************************
 * This piece of work is to enhance astrology project functionality.          *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      AstrologyREST.java                                              *
 * Created:   27/11/2025, 22:00                                               *
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

package com.aerosimo.ominet.api;

import com.aerosimo.ominet.core.models.Spectre;
import com.aerosimo.ominet.core.models.Vercel;
import com.aerosimo.ominet.dao.impl.APIResponseDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/horoscope")
@Produces(MediaType.APPLICATION_JSON)
public class AstrologyREST {

    private static final Logger log = LogManager.getLogger(AstrologyREST.class.getName());

    @POST
    public Response getHoroscope() {
        try{
            Vercel.updateZodiac();
            log.info("Successfully initiate the process of getting daily horoscope.");
            return Response.ok(new APIResponseDTO("success", "daily horoscope update initiated successfully")).build();
        } catch (Exception err) {
            log.error("❌ Error while updating horoscope: {}", err.getMessage(), err);
            try {
                Spectre.recordError("TE-10001", "❌ Error while updating horoscope " + err.getMessage(), Vercel.class.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Response.serverError()
                    .entity(new APIResponseDTO("unsuccessful", "internal server error"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}