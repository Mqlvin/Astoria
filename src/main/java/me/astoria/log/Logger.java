/*
---------------------------------------
* Created: b0.1 - 14/01/2022
* Author(s): [Henry]
* License:
---------------------------------------
*/

package me.astoria.log;

import me.astoria.Astoria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static final Date DATE = new Date();

    public static void log(String message) {
        System.out.println("[" + FORMAT.format(DATE) + "] [" + Astoria.NAME + "] " + message);
    }

    public static void report(String message, Severity severity) {
        System.out.println("[" + FORMAT.format(DATE) + "] [" + Astoria.NAME + "/" + severity.name() + "] " + message);
    }
}
