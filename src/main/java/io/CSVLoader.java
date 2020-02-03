package io;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class loads CSV files using the OpenCSV library.
 */
public final class CSVLoader {

    /**
     * Root path to all data files
     */
    private final String dataPath;

    public CSVLoader(String dataPath){
        this.dataPath = dataPath;
    }

    /**
     * Load any csv file using OpenCSV library.
     * @param csvFileName
     * @param dataClass
     * @param <T>
     * @return
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> loadRentalSVData(String csvFileName, Class<? extends T> dataClass) throws FileNotFoundException {
        Path filePath = Paths.get(dataPath, csvFileName);
        return new CsvToBeanBuilder(new FileReader(filePath.toFile()))
                .withType(dataClass).build().parse();
    }
}
