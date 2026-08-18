package com.obem.data;

import java.nio.file.Path;

import org.testng.annotations.DataProvider;

import com.obem.utils.ExcelUtils;

public final class LoginDataProvider {

    private LoginDataProvider() {}

    @DataProvider(name = "loginData", parallel = true)
    public static Object[][] loginData() throws Exception {
        String path = Path.of(
                "src", "test", "resources", "testdata.xlsx")
                .toString();

        try (ExcelUtils excel = new ExcelUtils(path)) {
            int rows = excel.getRowCount("LoginData");
            Object[][] data = new Object[Math.max(0, rows - 1)][3];

            for (int row = 1; row < rows; row++) {
                data[row - 1][0] = excel.getCellData("LoginData", row, 0);
                data[row - 1][1] = excel.getCellData("LoginData", row, 1);
                data[row - 1][2] = excel.getCellData("LoginData", row, 2);
            }

            return data;
        }
    }
}
