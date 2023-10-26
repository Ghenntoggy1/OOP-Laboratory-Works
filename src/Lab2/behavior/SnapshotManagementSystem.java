package Lab2.behavior;

import Lab2.files.GeneralFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

public class SnapshotManagementSystem {
    private String directoryPath = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab2\\filesExample\\";
    private String snapshotFilePath = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab2\\snapshot\\" + "snapshot.txt";
    private Long lastSnapshotDate;
    private HashMap<String, GeneralFile> currSnapshot;
    private HashMap<String, GeneralFile> knownSnapshot;
    private HashMap<String, GeneralFile> prevSnapshot;

    public SnapshotManagementSystem() {
        this.currSnapshot = new HashMap<>();
        this.prevSnapshot = new HashMap<>(this.currSnapshot);
        this.knownSnapshot = new HashMap<>(this.currSnapshot);
    }

    public void saveNewSnapshotDate() {
//        try (FileWriter fileWriter = new FileWriter(snapshotFilePath); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
//            bufferedWriter.write(lastSnapshotDate.toString());
//            bufferedWriter.newLine();
//            for (String fileName : prevSnapshot.keySet()) {
//                File newFileFromPrevFile = new File(directoryPath + fileName);
//                bufferedWriter.write(newFileFromPrevFile.getName() + " - " + newFileFromPrevFile.lastModified());
//                bufferedWriter.newLine();
//            }
//            System.out.println("SNAPSHOT SAVE SUCCEED");
//        } catch (IOException e) {
//            System.out.println("SNAPSHOT SAVE FAILED!");
//        }
        try (PrintWriter printWriter = new PrintWriter(snapshotFilePath)) {
            printWriter.println(lastSnapshotDate);
            for (String fileName : prevSnapshot.keySet()) {
                File file = new File(directoryPath + fileName);
                printWriter.println(fileName + " - " + file.lastModified());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadStateFromPrevSnapshot() {
        try {
            List<String> linesFromFile = Files.readAllLines(Paths.get(this.snapshotFilePath));
            setLastSnapshotDate(Long.parseLong(linesFromFile.get(0)));
            this.lastSnapshotDate = Long.parseLong(linesFromFile.get(0));
            System.out.println("Last snapshot: " + this.lastSnapshotDate);
            this.prevSnapshot.clear();
            for (String line : linesFromFile) {
                if (linesFromFile.get(0).equals(line)) {
                    continue;
                }
                String[] file_lastModificationDate = line.split(" - ");
                String fileName = file_lastModificationDate[0];
                File file = new File(this.directoryPath + fileName);
                Long lastModificationDate;
                if (file.exists()) {
                    lastModificationDate = Long.parseLong(file_lastModificationDate[1]);
                }
                else {
                    lastModificationDate = Long.valueOf(0);
                }
                GeneralFile newFile = GeneralFile.generateNewFile(this.directoryPath, fileName, lastModificationDate);
                prevSnapshot.put(fileName, newFile);
            }
            System.out.println("LOADING STATE FROM PREVIOUS SNAPSHOT SUCCEED!");
        } catch (IOException e) {
            System.out.println("LOADING STATE FROM PREVIOUS SNAPSHOT FAILED!");
        }
    }

    public void loadStateFromCurrSnapshot() {
        this.currSnapshot.clear();
        File filesPackage = new File(this.directoryPath);
        if (filesPackage.exists() && filesPackage.isDirectory()) {
            File[] files = filesPackage.listFiles();
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    Long lastModificationDate = file.lastModified();
                    GeneralFile newFile = GeneralFile.generateNewFile(this.directoryPath, fileName, lastModificationDate);
                    this.currSnapshot.put(fileName, newFile);
                }
                System.out.println("LOADING STATE FROM CURRENT SNAPSHOT SUCCEED!");
            }
            else {
                System.out.println("NO FILES FOUND IN DIRECTORY: " + filesPackage.getAbsolutePath());
            }
        }
        else {
            System.out.println("NON EXISTING DIRECTORY!");
        }
    }

    public void commit() {
        setPrevSnapshot(new HashMap<>(getCurrSnapshot()));
        setLastSnapshotDate(System.currentTimeMillis());
        saveNewSnapshotDate();
        System.out.println("SNAPSHOT GOT UPDATED AT: " + new Timestamp(getLastSnapshotDate()) + " : ID: " + getLastSnapshotDate());
    }

    public void status() {
        loadStateFromPrevSnapshot();
        loadStateFromCurrSnapshot();
        System.out.println("LAST SNAPSHOT WAS AT: " + new Timestamp(getLastSnapshotDate()) + " : ID: " + getLastSnapshotDate());
        HashMap<String, GeneralFile> currSnapshotFiles = getCurrSnapshot();
        HashMap<String, GeneralFile> prevSnapshotFiles = getPrevSnapshot();
        System.out.println("COMPARING STATES FROM SNAPSHOTS...");
        for (String fileName : currSnapshotFiles.keySet()) {
            GeneralFile currFileInstance = currSnapshotFiles.get(fileName);
            GeneralFile prevFileInstance = prevSnapshotFiles.get(fileName);
            if (!currFileInstance.getLastModificationDate().equals(prevFileInstance.getLastModificationDate())) {
                System.out.println(fileName + " - Changed");
            }
            else {
                System.out.println(fileName + " - No Changes");
            }
        }
    }

    public Long getLastSnapshotDate() {
        return this.lastSnapshotDate;
    }
    public void setLastSnapshotDate(Long lastSnapshotDate) {
        this.lastSnapshotDate = lastSnapshotDate;
    }
    public HashMap<String, GeneralFile> getPrevSnapshot() {
        return this.prevSnapshot;
    }
    public void setPrevSnapshot(HashMap<String, GeneralFile> prevSnapshot) {
        this.prevSnapshot.clear();
        this.prevSnapshot.putAll(prevSnapshot);
    }
    public HashMap<String, GeneralFile> getCurrSnapshot() {
        return this.currSnapshot;
    }
    public void setCurrSnapshot(HashMap<String, GeneralFile> currSnapshot) {
        this.currSnapshot.clear();
        this.currSnapshot.putAll(currSnapshot);
    }
    public HashMap<String, GeneralFile> getKnownSnapshot() {
        return this.knownSnapshot;
    }
    public void setKnownSnapshot(HashMap<String, GeneralFile> knownSnapshot) {
        this.knownSnapshot.clear();
        this.knownSnapshot.putAll(knownSnapshot);
    }
}
