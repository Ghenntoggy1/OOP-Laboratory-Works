package Lab2.behavior;

import Lab2.files.GeneralFile;

import java.io.*;
import java.util.HashMap;

public class SnapshotManagementSystem {
    private final String directoryPath = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab2\\files";
    private final String snapshotFilePath = "C:\\IT Roma\\OOP Labs\\Labs\\src\\Lab2\\" + "snapshot.txt";
    private Long lastSnapshotDate;
    private HashMap<String, GeneralFile> currSnapshot;
    private HashMap<String, GeneralFile> knownSnapshot;
    private HashMap<String, GeneralFile> prevSnapshot;

    public SnapshotManagementSystem() {
        this.currSnapshot = new HashMap<>();
        this.prevSnapshot = new HashMap<>();
        this.knownSnapshot = new HashMap<>(this.currSnapshot);
    }

    public void saveNewSnapshotDate() {
        try (FileWriter fileWriter = new FileWriter(snapshotFilePath); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(lastSnapshotDate.toString());
            bufferedWriter.newLine();
            for (String fileName : prevSnapshot.keySet()) {
                File newFileFromPrevFile = new File(directoryPath + fileName);
                bufferedWriter.write(newFileFromPrevFile.getName() + newFileFromPrevFile.lastModified());
                bufferedWriter.newLine();
            }
            System.out.println("SNAPSHOT SAVE SUCCEED");
        } catch (IOException e) {
            System.out.println("SNAPSHOT SAVE FAILED!");
        }
    }

    public void commit() {
        lastSnapshotDate = System.currentTimeMillis();

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
