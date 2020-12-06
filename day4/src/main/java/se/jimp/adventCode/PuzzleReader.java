package se.jimp.adventCode;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;;
import java.util.ArrayList;
import java.util.List;

public class PuzzleReader {

    public static List<String> readPuzzleFile(String filename) {
        List<String> retList = new ArrayList<>();

        try {
            retList = Files.readAllLines(FileSystems.getDefault().getPath(filename));
        } catch (IOException e) {
            System.out.println("FIle: " + filename + " not found");
        }

        return retList;
    }
}
