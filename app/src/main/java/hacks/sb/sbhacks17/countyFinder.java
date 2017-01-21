package hacks.sb.sbhacks17;
import jxl.Workbook;
import java.io.File;
import jxl.*;



public class countyFinder {

    final private static int LOWER_RANGE_ROW = 2;
    final private static int UPPER_RANGE_ROW = 3199;

    final private static int COLUMN_COUNTY_NAME = 0;

    final private String TYPE_DENSITY = "density";
    final private static int COLUMN_POPULATION_DENSITY = 3;
    private boolean densitySearch;
    private double densityLowerBound;
    private double densityUpperBound;


    final private String TYPE_HOUSEHOLD_INCOME = "householdIncome";
    final private static int COLUMN_POPULATION_MEDIAN_INCOME = 4;
    private boolean householdIncomeSearch;
    private double householdIncomeLowerBound;
    private double householdIncomeUpperBound;




    Workbook workbook;
    Sheet sheet;

    public countyFinder(){
        try{
        workbook = Workbook.getWorkbook(new File("stats.xls"));
        sheet = workbook.getSheet(0);

        householdIncomeSearch = false;
        densitySearch = false;

        } catch (java.io.IOException z) {

        } catch (jxl.read.biff.BiffException x) {

        }

    }





    public void addSearch(String search, double lowerBound, double upperBound){

        switch(search){
            case "density":
                densitySearch = true;
                densityLowerBound = lowerBound;
                densityUpperBound = upperBound;
                break;
            case "household.income":
                householdIncomeSearch = true;
                householdIncomeLowerBound = lowerBound;
                householdIncomeUpperBound = lowerBound;
                break;


        }





    }







    public countyList search() {
        countyList list = new countyList(true);


        if (densitySearch) {
            list = find(densityLowerBound, densityUpperBound, COLUMN_POPULATION_DENSITY,list,TYPE_DENSITY);
        }


        if (householdIncomeSearch) {
            list = find(householdIncomeLowerBound, householdIncomeUpperBound, COLUMN_POPULATION_MEDIAN_INCOME,list, TYPE_HOUSEHOLD_INCOME);
        }

        return list;
    }















    private countyList find(double lowerBound, double upperBound, int column, countyList currentList, String type) {
        if(currentList.isNewList) {
            countyList list = new countyList();

            for (int count = 1; count < UPPER_RANGE_ROW; count++) {
                double field = getDouble(column, count);
                if (field >= lowerBound && field <= upperBound) {

                    county newCounty = extractCounty(count);
                    list.addCounty(newCounty);
                }
            }

            return list;
        } else {

            while (!currentList.ended()) {
                double field = currentList.next(true).getField(type);
                if (field < lowerBound || field > upperBound) {
                    currentList.removePrevious();
                }
            }
            return currentList;
        }

    }






    private county extractCounty(int row){
        String name = getString(COLUMN_COUNTY_NAME, row);
        double population_density = getDouble(COLUMN_POPULATION_DENSITY, row);
        double householdIncome = getDouble(COLUMN_POPULATION_MEDIAN_INCOME, row);

        return new county (name,population_density,householdIncome, row);
    }









    private Cell getCell(int column, int row){
        return sheet.getCell(column,row);


    }




    private double getDouble(int column, int row){
        assert (column !=0 && row != 1);
        Cell newCell = getCell(column,row);
        double populationDensity = Double.parseDouble(newCell.getContents().replace(",","."));
        return populationDensity;
    }



    private String getString(int column, int row){
        Cell newCell = getCell(column,row);
        String populationDensity = newCell.getContents();
        return populationDensity;
    }








}



