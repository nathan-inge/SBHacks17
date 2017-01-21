/**
 * Created by MLH-Admin on 1/21/2017.
 */
package hacks.sb.sbhacks17;

import jxl.Workbook;
import java.io.File;
import jxl.*;

import jxl.Workbook;
import java.io.File;
import jxl.*;


/**
 *  * Created by MLH-Admin on 1/21/2017.
 */

public class countyFinder {

    final private static int LOWER_RANGE_ROW = 2;
    final private static int UPPER_RANGE_ROW = 3199;

    final private static int COLUMN_COUNTY_NAME = 0;

    final public String TYPE_DENSITY = "density";
    final private static int COLUMN_POPULATION_DENSITY = 3;
    private boolean densitySearch;
    private int densityLowerBound;
    private int densityUpperBound;


    final public String TYPE_HOUSEHOLD_INCOME = "household.income";
    final private static int COLUMN_POPULATION_MEDIAN_INCOME = 4;
    private boolean householdIncomeSearch;
    private int householdIncomeLowerBound;
    private int householdIncomeUpperBound;




    Workbook workbook;
    Sheet sheet;

    public countyFinder(){
        try{
            workbook = Workbook.getWorkbook(new File("stats.xls"));
            sheet = workbook.getSheet(0);

            householdIncomeSearch = false;
            densitySearch = false;

        } catch (java.io.IOException z) {
            System.out.println ("file not read");
        } catch (jxl.read.biff.BiffException x) {

        }

    }





    public void addSearch(String search, int lowerBound, int upperBound){

        switch(search){
            case TYPE_DENSITY:
                densitySearch = true;
                densityLowerBound = lowerBound;
                densityUpperBound = upperBound;
                break;
            case TYPE_HOUSEHOLD_INCOME:
                householdIncomeSearch = true;
                householdIncomeLowerBound = lowerBound;
                householdIncomeUpperBound = upperBound;
                break;


        }





    }







    public countyList search() {
        countyList list = new countyList(true);


        if (densitySearch) {
            list = find(densityLowerBound, densityUpperBound, COLUMN_POPULATION_DENSITY,list,TYPE_DENSITY);
        }


        if (householdIncomeSearch) {
            list = find(0, 30000, 4,list, TYPE_HOUSEHOLD_INCOME);
        }

        return list;
    }















    private countyList find(int lowerBound, int upperBound, int column, countyList currentList, String type) {
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

            while (!(currentList.ended())) {
                System.out.println("hello im a bitch");
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
        double householdIncome = getDouble(4, row);

        return new county (name,population_density,householdIncome, row);
    }









    private Cell importCell(int column, int row){
        return sheet.getCell(column,row);


    }




    private double getDouble(int column, int row){
        assert (column !=0 && row != 1);
        Cell newCell = importCell(column,row);
        double populationDensity = Double.parseDouble(newCell.getContents().replace(",","."));
        return populationDensity;
    }


    private String getString(int column, int row){
        Cell newCell = importCell(column,row);
        String populationDensity = newCell.getContents();
        return populationDensity;
    }








}



