package com.testco.football.data;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Created by sergey on 1/26/17.
 */

public class DataUtil {

    public static void parsePreFill(League league, Reader reader) throws IOException {
        LineNumberReader rd = new LineNumberReader(reader);
        String str;
        while( (str = rd.readLine()) != null ) {
            String[] parts = str.split(",");
            if( parts.length != 4 ) throw new IOException("Incorrect File Format");
            try {
                league.addGame(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
            } catch (Exception e) {
                throw new IOException("Incorrect File Format");
            }
        }
    }
}
