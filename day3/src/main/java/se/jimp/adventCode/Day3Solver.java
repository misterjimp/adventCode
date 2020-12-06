package se.jimp.adventCode;

import java.util.List;

public class Day3Solver {

    String inputFile = "day3.puzzle";
    List<String> treeMap;
    int mapWidth;
    private int bottomIndex;

    public Day3Solver(String filename) {
        treeMap = PuzzleReader.readPuzzleFile(inputFile);
        mapWidth = treeMap.get(0).length();
        bottomIndex = treeMap.size();
    }

    private boolean isTree(int x, int y, int wrapInteger) throws Exception {
        boolean ret = false;
        //First reduce the x by wrapInteger

        x = x - (wrapInteger * mapWidth);

        // make sure we are in the right bounds

        if(x < mapWidth) {
            ret = treeMap.get(y).substring(x, x+1).equals("#");
        }
        else {
            throw new Exception("x is out of bounds, x: " + x + ", mapWidth: " + mapWidth + ", wrapInteger: " + wrapInteger);
        }

        return ret;
    }

    private int getNextX(int x, int slopex) {
        return x + slopex;
    }

    private int getNextY(int y, int slopey) {
        return y + slopey;
    }

    private int countTrees(int slopeX, int slopeY) throws Exception {

        int numberOfCrossedTrees = 0;
        int currentX = 0;
        int currentY = 0;
        int wrapInteger = 0;

        while(currentY < bottomIndex) {
            if(isTree(currentX, currentY, wrapInteger)) {
                numberOfCrossedTrees++;
            }
            currentX = getNextX(currentX, slopeX);
            currentY = getNextY(currentY, slopeY);

            //Check for wrap:

            if(currentX >= mapWidth * (wrapInteger + 1)) {
                wrapInteger++;
            }
        }

        return numberOfCrossedTrees;
    }

    public static void main(String[] args) throws Exception {
        Day3Solver solver = new Day3Solver("day3.puzzle");

        //Task1 count trees for a specific slope
        int task1Trees = solver.countTrees(3, 1);
        System.out.println("Trees with slope 3, 1: " + task1Trees);

        //Task2 count trees for a number of slopes and multiply them together

        long multiplier = 0;
        int currentTrees = 0;

        //slope1: 1,1
        currentTrees = solver.countTrees(1, 1);
        multiplier = currentTrees;
        System.out.println("Slope 1, 1: " + currentTrees);

        //slope2: 3, 1
        currentTrees = solver.countTrees(3, 1);
        multiplier = multiplier * currentTrees;
        System.out.println("Slope 3, 1: " + currentTrees);

        //slope3: 5, 1

        currentTrees = solver.countTrees(5, 1);
        multiplier = multiplier * currentTrees;
        System.out.println("Slope 5, 1: " + currentTrees);

        //slope3: 7, 1
        currentTrees = solver.countTrees(7, 1);
        multiplier = multiplier * currentTrees;
        System.out.println("Slope 7, 1: " + currentTrees);

        //slope3: 1, 2
        currentTrees = solver.countTrees(1, 2);
        multiplier = multiplier * currentTrees;
        System.out.println("Slope 1, 2: " + currentTrees);

        System.out.println("Multiplier: " + multiplier);

    }
}
