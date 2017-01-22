/**
 * Created by MLH-Admin on 1/21/2017.
 */

package hacks.sb.sbhacks17;

public class county {
    public String name;
    public double population_density;
    public double householdIncome;
    public double educationalValue;
    public double mortgageRate;
    public double lat;
    public double lon;
//yo
    public int row;

    public county(String name, double density, double householdIncomeToAdd, double educationalRating, double mortgageMedian ,int row){
        this.name = name;
        population_density = density;
        this.householdIncome = householdIncomeToAdd;
        this.row = row;
        educationalValue = educationalRating;
        mortgageRate = mortgageMedian;

    }






    public county(county copyFrom){
        name = copyFrom.name;
        population_density = copyFrom.population_density;
        householdIncome = copyFrom.householdIncome;
        mortgageRate = copyFrom.mortgageRate;
        educationalValue = copyFrom.educationalValue;


        row = copyFrom.row;
    }



    public double getField(String type){

        switch (type){
            case countyFinder.TYPE_DENSITY:
                return population_density;

            case countyFinder.TYPE_HOUSEHOLD_INCOME:
                return householdIncome;

            case countyFinder.TYPE_EDUCATIONAL_VALUE:
                return educationalValue;

            case countyFinder.TYPE_MORTGAGE:
                return mortgageRate;

            default:
                return -1;


        }
    }






}
