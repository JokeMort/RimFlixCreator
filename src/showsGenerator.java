
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class showsGenerator {
    public static void redButton(String sourceFolder, String targetFolder, List<Boolean> whatKindOfTvToInclude,
            String speed) {
        String folderPath = sourceFolder;
        String targetLocation = targetFolder;
        List<String> folderNames = listFolderNames(folderPath); // load all shows names

        List<String> tvTypes = List.of("Tube", "Flatscreen", "Megascreen", "Ultrascreen");
        List<String> thisShitGonnaGoInside = new ArrayList<>();
        for (int i = 0; i < 4; i++) { // create List of supported TVs
            if (Boolean.TRUE.equals(whatKindOfTvToInclude.get(i))) {
                thisShitGonnaGoInside.add(tvTypes.get(i));
            }
        } // yeach, it's shit but I have no idea how to make it better

        generateXmlFiles(folderNames, sourceFolder, targetLocation, thisShitGonnaGoInside, speed);
    }

    public static List<String> listFolderNames(String foldersPath) {

        List<String> folderNameList = new ArrayList<>();

        File folder = new File(foldersPath);

        if (folder.isDirectory()) { // check if Shows exists
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isDirectory()) {
                        folderNameList.add(file.getName());
                    }
                }
            } else {
                System.out.println("Folder is empty");
            }
        } else {
            System.out.println("Folder not found");
        }
        return folderNameList;
    }

    public static List<String> listFilesNames(String foldersPath) { // creating list of file names for creation of XML

        List<String> folderNameList = new ArrayList<>();

        File folder = new File(foldersPath);

        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();// creating list of pictures in directory

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) { // removing type from picture name
                        String fileName = file.getName();
                        int lastIndex = fileName.lastIndexOf(".");
                        if (lastIndex > 0) {
                            fileName = fileName.substring(0, lastIndex);
                        }
                        folderNameList.add(fileName);
                    }
                }
            } else {
                System.out.println("Folder is empty");
            }
        } else {
            System.out.println("Folder not found");
        }
        return folderNameList;
    }

    public static void generateXmlFiles(List<String> folderNames, String sourceLocation, String targetLocation,
            List<String> tvTypes, String speed) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        for (String folderName : folderNames) {

            String xmlFileName = targetLocation + File.separator + folderName + ".xml";

            try (FileWriter fileWriter = new FileWriter(xmlFileName)) {

                String folderPath = sourceLocation + File.separator + folderName;
                List<String> filesNames = listFilesNames(folderPath);

                XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);

                xmlStreamWriter.writeStartDocument("utf-8", "1.0");
                xmlStreamWriter.writeCharacters("\n\n");
                xmlStreamWriter.writeStartElement("Defs");
                xmlStreamWriter.writeCharacters("\n\n");
                xmlStreamWriter.writeStartElement("RimFlix.ShowDef");
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("defName");
                xmlStreamWriter.writeCharacters(folderName);
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("label");
                xmlStreamWriter.writeCharacters(folderName + "-Ultrascreen");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("description");
                xmlStreamWriter.writeCharacters(
                        "This is show called " + folderName + ". This description was generated automatically.");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("televisionDefs");
                xmlStreamWriter.writeCharacters("\n");
                for (String tvType : tvTypes) {
                    xmlStreamWriter.writeStartElement("li");
                    xmlStreamWriter.writeCharacters(tvType + "Television");
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters("\n");
                }
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("secondsBetweenFrames");
                xmlStreamWriter.writeCharacters(Float.toString(1 / Float.parseFloat(speed)));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("sound");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeStartElement("frames");
                xmlStreamWriter.writeCharacters("\n");
                for (String fileName : filesNames) {
                    xmlStreamWriter.writeStartElement("li");
                    xmlStreamWriter.writeCharacters("\n");
                    xmlStreamWriter.writeStartElement("texPath");
                    xmlStreamWriter.writeCharacters("Shows/" + folderName + "/" + fileName);
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters("\n");
                    xmlStreamWriter.writeStartElement("graphicClass");
                    xmlStreamWriter.writeCharacters("Graphic_Single");
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters("\n");
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters("\n");
                }

                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");

                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\n");
                xmlStreamWriter.writeEndDocument();

                xmlStreamWriter.close();

                System.out.println("File XML for folder: '" + folderName + "' was created: " + xmlFileName);
            } catch (XMLStreamException | java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}