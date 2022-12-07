package com.ford.datafactory.batch.util;

import java.util.ArrayList;

/**
 * Utility Methods for Handling CSV Files.
 */
public class CsvUtil {


    /**
     * Split a .csv record into an array of column values.
     *
     * Not the most robust parser, but will work so long as all embedded quotes are escaped correctly
     * Ex: Column 1, "Column,"","" 2", Column 3
     *
     * @param csvRecord
     * @return ArrayList<String>
     */
    public static ArrayList<String> parseCsvRecord(String csvRecord)
    {
        ArrayList<String> columns = new ArrayList<String>();

        boolean insideQuote = false;

        int start=0;

        for(int i=0; i<csvRecord.length(); i++)
        {
            if(csvRecord.charAt(i)==',' && !insideQuote)
            {
                columns.add(trimCsvColumn(csvRecord.substring(start,i)));
                start = i+1;
            }
            else if(csvRecord.charAt(i)=='"')
                insideQuote=!insideQuote;
        }
        columns.add(trimCsvColumn(csvRecord.substring(start)));

        return columns;
    }


    /**
     * Remove Outer whitespace and Double Quotes from a .csv Column value
     *
     * @param column
     * @return String
     */
    public static String trimCsvColumn(String column)
    {

        column = column.trim();
        if (column.length() > 1 && column.substring(0,1).equals("\"") && column.substring(column.length()-1).equals("\"")) {
            column = column.substring(1,column.length()-1).trim();
        }

        if (column.length() == 0 ) {
            return null;
        }

        return column;
    }
}
