package com.micasa.data;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class DataGenerator {

    public Map<String, Data<?>> generate(Specification specification) {
        if (specification == null) throw new IllegalArgumentException("The generation specification is mandatory");

        Map<String, Data<?>> result = specification
                .specifications()
                .stream()
                .map(spec -> new Data(spec.name(), spec.valueGenerator().generate(specification.count())))
                .collect(Collectors.toMap(data -> data.name(), data -> data));

        if (specification.outputPath() != null && specification.outputPath().trim().length() > 0) serialize(result.values(), specification);

        return result;

    }

    //  TODO: implement serialization
    public void serialize(Collection<Data<?>> data, Specification specification) {

    }

}
