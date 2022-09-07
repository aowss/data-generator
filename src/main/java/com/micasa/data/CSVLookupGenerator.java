package com.micasa.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVLookupGenerator extends LookupGenerator<String> {

    private Iterable<CSVRecord> records;

    //  TODO: handle different options like passing the header, the delimiter, the type ( e.g. Excel ), ...
    public CSVLookupGenerator(String filePath, String selector) {
        super(filePath, FileType.CSV, selector);
    }

    private synchronized void loadData() throws IOException {
        if (!loaded) {
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new FileReader(filePath));
        }
        loaded = true;
    }

    @Override
    public String[] generate(int count) throws RuntimeException {
        try {
            loadData();
            String[] result = new String[count];

            var iterator = records.iterator();
            int index = 0;
            while (iterator.hasNext() && index < count) {
                var value = iterator.next().get(selector);
                result[index++] = value;
            }

            return result;
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Unable to load the file at " + filePath);
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to parse the file at " + filePath, ioe);
        }
    }

}
