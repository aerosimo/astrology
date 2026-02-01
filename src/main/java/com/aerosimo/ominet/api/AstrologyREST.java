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

import com.aerosimo.ominet.core.model.Spectre;
import com.aerosimo.ominet.core.model.Vercel;
import com.aerosimo.ominet.dao.impl.APIResponseDTO;
import com.aerosimo.ominet.dao.impl.HoroscopeResponseDTO;
import com.aerosimo.ominet.dao.mapper.HoroscopeDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/horoscope")
public class AstrologyREST {

    private static final Logger log = LogManager.getLogger(AstrologyREST.class.getName());

    @POST
    @Path("/overhaul")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveHoroscope() {
        try{
            Vercel.updateZodiac();
            log.info("Successfully initiate the process of getting daily horoscope.");
            return Response.ok(new APIResponseDTO("success", "daily horoscope update initiated successfully")).build();
        } catch (Exception err) {
            log.error("❌ Error while updating horoscope: {}", err.getMessage(), err);
            try {
                Spectre.recordError("TE-20001", "❌ Error while updating horoscope " + err.getMessage(), Vercel.class.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Response.serverError()
                    .entity(new APIResponseDTO("unsuccessful", "internal server error"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/sign")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHoroscope(@QueryParam("value") String sign) {
        if (sign == null || sign.isBlank()) {
            log.error("Missing required 'value' query parameter");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new APIResponseDTO("unsuccessful", "missing required fields"))
                    .build();
        }

        HoroscopeResponseDTO resp = HoroscopeDAO.getHoroscope(sign);
        if (resp == null || resp.getZodiacSign() == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new APIResponseDTO("unsuccessful", "no horoscope found"))
                    .build();
        }

        return Response.ok(resp).build();
    }
}