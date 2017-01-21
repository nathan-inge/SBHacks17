/**
 * Created by MLH-Admin on 1/21/2017.
 */
public class countyList {
    county [] countyList;
    private int allocated;
    private int usedSpace;
    private int currentCounty;
    public boolean isNewList;

public countyList(){
    countyList = new county [10];
    allocated = 10;
    usedSpace = 0;
    currentCounty = 0;
    isNewList = false;
}




    public countyList(boolean placeHolder){
        isNewList = true;

    }





    public countyList(countyList copyFrom){
        for (int count = 0; count < usedSpace; count++)
        copyFrom.addCounty(countyList[count]);


    }









public void refreshFlow(){
    currentCounty = 0;

}





public void removePrevious(){
    int indexToRemove = currentCounty - 1;
        countyList newList = new countyList();
    for (int count = 0; count < usedSpace; count++) {
        if(count != indexToRemove)
        newList.addCounty(countyList[count]);
    }
}










public county next(boolean moveOn){

    if(currentCounty < usedSpace){
        if(moveOn)
            currentCounty++;
        return countyList[currentCounty - 1];
    } else
        return new county();

}



public boolean ended(){
    return currentCounty < usedSpace;
}
















    public void addCounty (county newCounty){
        countyList[usedSpace] = new county(newCounty);
        usedSpace++;

        if(usedSpace == allocated)
            reallocate();

    }



    public int size(){
        return usedSpace;
    }



    private void reallocate() {
        int newSize = allocated * 2;
        county [] newList = new county [newSize];

        for (int count = 0; count < usedSpace; count++) {
            newList[count] = countyList[count];
        }

        allocated = newSize;
        countyList = new county[allocated];

        for (int count = 0; count < usedSpace; count++) {
            countyList[count] = newList[count];
        }
    }


    public void print(){

        for (int count = 0; count < usedSpace; count++){
            System.out.println(countyList[count].name + " " + countyList[count].population_density + " " + countyList[count].householdIncome);


        }



    }
}
