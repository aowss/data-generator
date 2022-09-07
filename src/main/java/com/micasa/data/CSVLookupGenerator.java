package com.micasa.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CSVLookupGenerator extends LookupGenerator<String> {

    private List<CSVRecord> records;

    //  TODO: Use CSVFormat to handle different options like passing the header, the delimiter, the type ( e.g. Excel ), ...
    public CSVLookupGenerator(String filePath, String selector) {
        super(filePath, FileType.CSV, selector);
    }

    private synchronized void loadData() {
        if (!loaded) {
            try (Reader input = new FileReader(filePath);
                 CSVParser parser = new CSVParser(input, CSVFormat.RFC4180.withHeader())) {
                records = parser.getRecords();
                if (records.isEmpty()) {
                    throw new RuntimeException("The file at " + filePath + " is empty");
                }
                loaded = true;
            } catch (FileNotFoundException fnfe) {
                throw new RuntimeException("Unable to load the file at " + filePath);
            } catch (IOException ioe) {
                throw new RuntimeException("Unable to parse the file at " + filePath, ioe);
            }
        }
    }

    @Override
    public String[] generate(int count) throws RuntimeException {
        loadData();
        try {
            String[] data = records
                    .stream()
                    .map(csvRecord -> csvRecord.get(selector))
                    .limit(count)
                    .toArray(String[]::new);

            if (data.length == count) return data;

            String[] result = new String[count];
            for (int i = 0; i < count; i++) {
                result[i] = data[i % records.size()];
            }
            return result;
        } catch (IllegalArgumentException iae) {
            throw new RuntimeException("The file at " + filePath + " doesn't contain the header '" + selector + "'", iae);
        }
    }

}
