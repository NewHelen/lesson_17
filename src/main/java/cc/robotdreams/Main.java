package cc.robotdreams;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Directories and Files
        // ./   - Current dir
        // ../  - Parent dir
        // /    - Root dir
        // MacOS, Linux  - /
        // Windows       - \
        System.out.println("-------------------------------------------------------------------");
        System.out.println("System file separator: " + System.getProperty("file.separator"));
        System.out.println("System file separator: " + File.separator);
        System.out.println("System file separator: " + FileSystems.getDefault().getSeparator());
        System.out.println("-------------------------------------------------------------------");

        Path currentPath = Paths.get("./");
        System.out.println("Current path:           " + currentPath);
        System.out.println("Current absolute path:  " + currentPath.toAbsolutePath());
        System.out.println("Current absolute path:  " + currentPath.toAbsolutePath().normalize());
        System.out.println("Separator:              " + currentPath.getFileSystem().getSeparator());
        System.out.println("-------------------------------------------------------------------");

        Path parentPath = Paths.get("../");
        System.out.println("Parent path:           " + parentPath);
        System.out.println("Parent absolute path:  " + parentPath.toAbsolutePath());
        System.out.println("Parent absolute path:  " + parentPath.toAbsolutePath().normalize());
        System.out.println("-------------------------------------------------------------------");

        Path testFilePath = Paths.get("./files/testFile.txt");
        System.out.println("New file absolute path:  " + testFilePath.toAbsolutePath().normalize());
        Path newTestFilePath = Paths.get("./files/newTestFile.txt");

        //if (Files.notExists(newTestFilePath));
        //скопировать содержимое файла1 и создать новый файл2 с таким же содержимым
        Files.copy(testFilePath, newTestFilePath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        long newFileSize = Files.size(newTestFilePath);
        System.out.println("New file size:" + newFileSize + " bytes");

        // проверить что путь это дериктория  isDirectory
        if (Files.isDirectory(currentPath)) {
            System.out.printf("Path \"%s\" is directory\n", currentPath.toAbsolutePath().normalize());
        }

        // проверить что путь это файл  isRegularFile
        if (Files.isRegularFile(newTestFilePath)) {
            System.out.printf("Path \"%s\" is file\n", newTestFilePath.toAbsolutePath().normalize());
        }

        // создать новый файл (если он не существует)
        Path createFile = Paths.get("./files/createdFile.txt");
        if (Files.notExists(createFile))
            Files.createFile(createFile);

        // создать дерикторию nestedDir2 в дериктории nestedDir1/
        Path nestedDir = Paths.get("./files/nestedDir1/nestedDir2");
        Files.createDirectories(nestedDir);

        //удалить файл Files.delete
        Path fileToDelete = Paths.get(nestedDir.toString(),"./fileToDelete.txt");
        // ./files/nestedDir1/nestedDir2/./fileToDelete.txt
        System.out.println("File to delete: " + fileToDelete);
        //Files.delete(fileToDelete);
        // создать файл заново если его нет
        if (Files.notExists(fileToDelete));
         Files.createFile(fileToDelete);

         //удалить файл если он есть  deleteIfExists
        boolean isDeleted = Files.deleteIfExists(fileToDelete);
        System.out.println("File is deleted: " + isDeleted);

        //прочитать файл небольшой
        System.out.println("///////////////////////////////////////////");
        System.out.println("Content of \"" + testFilePath + "\"");
        System.out.println("---------------------------------------");
        System.out.println(Files.readAllLines(testFilePath));
        System.out.println("///////////////////////////////////////////");

        ///////////////////////////////////////////////////////////////////////////////////

        //Buffered readers and writers
        Reader reader = new FileReader("./files/buffered/fileToRead.txt");
        BufferedReader br = new BufferedReader(reader, 1024);
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null) {
            System.out.println( lineNumber ++ + ": " + line);
        }
        br.close();  // закривать файлы


        Writer writer = new FileWriter("./files/buffered/fileToWrite.txt");
        BufferedWriter bw = new BufferedWriter(writer, 1024);
        bw.write("Write new line with BufferedWriter\n");
        bw.flush(); // Force write to file

        bw.close();  // закривать файлы

        File fileExample = new File("./files/fileExample.txt");
        if (!fileExample.exists())
            fileExample.createNewFile();
    }
}