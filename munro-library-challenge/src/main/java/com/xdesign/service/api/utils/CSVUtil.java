package com.xdesign.service.api.utils;

import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.constants.FileConstants;
import com.xdesign.service.api.model.HillData;
import com.xdesign.service.api.model.MunroModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtil {

    /**
     * A Method to load the munro data file then create and return the loaded MunroModel
     *
     * @return {@linkplain MunroModel}
     */
    public static MunroModel getMunroData() {
        MunroModel munroModel = new MunroModel();
        List<HillData> hillDataList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(FileConstants.FILE_SRC_FULL_MUNRO_DATA))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                hillDataList.add(getHillData(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        munroModel.setItems(hillDataList);

        return munroModel;
    }

    /**
     * Method to parse a given record/CSV line to retrieve the specified required data
     * (Hill Category, Name, Grid Reference, and Height) and load it into a HillData object
     *
     * @return {@linkplain HillData}
     */
    private static HillData getHillData(String line) {
        List<String> record = new ArrayList<>();

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(Constants.COMMA);

            while (rowScanner.hasNext()) {
                record.add(rowScanner.next());
            }

            HillData recordHillData = new HillData();
            recordHillData.setHillCategory(record.get(27));
            recordHillData.setName(record.get(6));
            recordHillData.setGridReference(record.get(13));
            recordHillData.setHeight(Double.parseDouble(record.get(9)));

            return recordHillData;
        }
    }
}
