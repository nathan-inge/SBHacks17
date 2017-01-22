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
//yo

/**
 *  * Created by MLH-Admin on 1/21/2017.
 */

public class countyFinder {
// hi
    final private static int LOWER_RANGE_ROW = 2;
    final private static int UPPER_RANGE_ROW = 3198;

    final private static int COLUMN_COUNTY_NAME = 0;

    final static public String TYPE_DENSITY = "density";
    final private static int COLUMN_POPULATION_DENSITY = 3;
    private boolean densitySearch;
    private int densityLowerBound;
    private int densityUpperBound;


    final static public String TYPE_HOUSEHOLD_INCOME = "household.income";
    final private static int COLUMN_POPULATION_MEDIAN_INCOME = 4;
    private boolean householdIncomeSearch;
    private int householdIncomeLowerBound;
    private int householdIncomeUpperBound;


    final static public String TYPE_EDUCATIONAL_VALUE = "educational.value";
    final private static int COLUMN_POPULATION_COUNTY_FINDER= 5;
    private boolean educationalValueSearch;
    private int educationalValueLowerBound;
    private int educationalValueUpperBound;


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
                System.out.println(lowerBound + " " + upperBound);
                break;


        }





    }






    public countyList search() {
        countyList list = new countyList();


        if (densitySearch) {
            list = find(densityLowerBound, densityUpperBound, COLUMN_POPULATION_DENSITY,list,TYPE_DENSITY);
        }


        if (householdIncomeSearch) {
            list =  find(householdIncomeLowerBound, householdIncomeUpperBound, COLUMN_POPULATION_MEDIAN_INCOME,list, TYPE_HOUSEHOLD_INCOME);
        }

        return list;
    }















    private countyList find(int lowerBound, int upperBound, int column, countyList currentList, String type) {
        if (currentList.isNewList) {

            for (int count = 1; count < UPPER_RANGE_ROW; count++) {
                double field = getDouble(column, count);
                if (field >= lowerBound && field <= upperBound) {

                    county newCounty = extractCounty(count);
                    currentList.addCounty(newCounty);
                }
            }

            return currentList;
        } else {
            int size = currentList.size();
            for (int count = 0; count < size; count++) {
                double field = currentList.element(count).getField(type);
                System.out.println(field);

                if (field < lowerBound || field > upperBound) {
                    System.out.println("removing element " + field);
                    currentList.countyList.remove(count);
                    size = currentList.size();
                    count = 0;

                } System.out.println(currentList.size());


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



