package com.micasa.data;

import java.util.List;

public record Specification(List<DataSpecification> specifications, int count, FileType outputType, String outputPath) {
    public Specification {
        if (specifications == null || specifications.size() == 0) throw new IllegalArgumentException("The data specification is mandatory and must contain at list one specification");
        if (count <= 0) throw new IllegalArgumentException("The number of records to generate must be strictly positive");
    }
}
